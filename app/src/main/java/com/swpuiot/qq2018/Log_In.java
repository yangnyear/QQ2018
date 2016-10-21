package com.swpuiot.qq2018;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestHandle;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;

public class Log_In extends Activity {
    public Button register;
    public Button log_in;
    private EditText username;
    private EditText passward;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.log_in_layout);
        username= (EditText) findViewById(R.id.text_username);
        passward= (EditText) findViewById(R.id.text_password);
        log_in= (Button) findViewById(R.id.login);
        register= (Button) findViewById(R.id.build);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Log_In.this, Build_Activity.class);
                startActivity(intent);
            }
        });
        log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = username.getText().toString();
                String pasw = passward.getText().toString();
                RequestParams logimformation = new RequestParams();
                AsyncHttpClient test=new AsyncHttpClient();
                logimformation.add("name",name);
                logimformation.add("password",pasw);
                RequestHandle post = test.post("http://114.215.144.204:9090/login", logimformation,new AsyncHttpResponseHandler() {

                    @Override
                    public void onSuccess(int i, Header[] headers, byte[] bytes) {
                        Toast.makeText(Log_In.this, "succese", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {

                    }
                });

            }
        });
    }
}
