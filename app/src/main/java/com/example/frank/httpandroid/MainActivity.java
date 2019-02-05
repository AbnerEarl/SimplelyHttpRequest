package com.example.frank.httpandroid;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;
    private String PATH="https://baike.baidu.com/item/%E5%8C%96%E5%AD%A6%E6%96%B9%E7%A8%8B%E5%BC%8F/1964669?fr=aladdin";
    private LinearLayout linearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayout=(LinearLayout)this.findViewById(R.id.test_html);

        progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("提示");
        progressDialog.setMessage("正在加载……");

        new MyTask().execute(PATH);
    }

    class MyTask extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... params) {
            String result=GetHttp.sendPostMethod(params[0],"utf-8");

            return result;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            TextView textView=new TextView(MainActivity.this);

            Spanned spanned= Html.fromHtml(s);
            textView.setText(spanned);
            //处理HTML中超链接事件
            //textView.setAutoLinkMask(0);
            textView.setMovementMethod(new LinkMovementMethod());
            linearLayout.addView(textView);
            progressDialog.dismiss();
        }
    }
}
