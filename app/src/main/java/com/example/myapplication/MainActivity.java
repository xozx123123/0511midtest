package com.example.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.ScrollingMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView output;
    EditText input;
    Button set,clear,small,big,display;
    int[] color1=new int[]{255,0,0  ,0,250,154   ,0,0,255   ,255,0,255   ,0,245,255};//五種顏色(rgb)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        input=findViewById(R.id.editTextInput); //輸入欄
        output=findViewById(R.id.textViewOutput); //輸出欄
        set =findViewById(R.id.buttonSet); //Set按紐
        clear =findViewById(R.id.buttonClear); //Clear按紐
        small =findViewById(R.id.buttonS); //字體縮小按紐
        big =findViewById(R.id.buttonB); //字體放大按紐
        display =findViewById(R.id.buttonDis); //Set按紐
        output.setBackgroundColor(Color.rgb(255,228,225)); //輸入欄背景顏色
        output.setMovementMethod(new ScrollingMovementMethod()); //輸入欄滾動設定
    }
    String[]t =new String[100]; //儲存每次輸入的字串
    int t1=-1; //計算String[] t已經存到第幾個陣列
    int[] red=new int[100]; //儲存每個字串rgb中的紅色數值
    int[] green=new int[100]; //儲存每個字串rgb中的r數值
    int[] blue=new int[100]; //儲存每個字串rgb中的g數值
    int[] txtsize=new int[100];//儲存每個字串rgb中的b數值
    int size=15; //字體大小初始值
    int ts=0; //紀錄每一段字串開始於第ts個字元
    SpannableStringBuilder builder = new SpannableStringBuilder(""); //一開始的字串順序
    SpannableStringBuilder displayer = new SpannableStringBuilder(""); //相反的字串順序
    public void set(View v){ //Set Button的funtion
        t1=t1+1;
        color(); //字串顏色設定
        t[t1]=input.getText().toString()+" "; //儲存輸入欄的文字(" "留空格方便觀看)
        txtsize[t1]=(int)input.getTextSize(); //儲存輸入的字體大小
        builder.insert(0,t[t1]); //將字串插入最前面的位置
        displayer.insert(displayer.length(),t[t1]); //將字串插入最後面的位置
        builder.clearSpans();
        for(int i=t1;i>=0;i--){
            builder.setSpan(new ForegroundColorSpan(Color.rgb(red[i],green[i],blue[i])), ts, ts+t[i].length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);//設定每段字串字體顏色
            builder.setSpan(new AbsoluteSizeSpan(txtsize[i]),ts, ts+t[i].length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); //設定每段字串字體大小
            ts=ts+t[i].length();
            }
        output.setText(builder);
        ts=0;
    }
    int compare=0; //判斷是要用builder(0)或displayer(1)
    public void dis(View v){ //Display Button的funtion
        displayer.clearSpans();
        for(int i=0;i<=t1;i++){
            displayer.setSpan(new ForegroundColorSpan(Color.rgb(red[i],green[i],blue[i])), ts, ts+t[i].length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);//設定每段字串字體顏色
            displayer.setSpan(new AbsoluteSizeSpan(txtsize[i]),ts, ts+t[i].length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);//設定每段字串字體大小
            ts=ts+t[i].length();
        }
        ts=0;
        if(compare==0){
            output.setText(displayer);
            compare=1;
        }
        else {
            output.setText(builder);
            compare=0;
        }
    }
    int colorn=0; //紀錄用到int[] color1的第幾個元素

    public void color(){
        red[t1]=color1[colorn];
        green[t1]=color1[colorn+1];
        blue[t1]=color1[colorn+2];
        colorn=colorn+3;
        if(colorn>=14){
            colorn=0;
        }
    }
    public void clear(View v){
        input.setText("");
    }

    public void big(View v){
        if(size<=40){
          size=size+5;
          input.setTextSize(size);}
    }
    public void small(View v){
        if(size>=10){
            size=size-5;
         input.setTextSize(size);}
    }
}
