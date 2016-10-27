package com.swpuiot.qq2018;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestHandle;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;

import java.io.IOException;

public class Log_In extends Activity {
    public Button register;
    public Button log_in;
    private EditText username;
    private EditText passward;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private CheckBox remember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.log_in_layout);
        username = (EditText) findViewById(R.id.text_username);
        passward = (EditText) findViewById(R.id.text_password);
        log_in = (Button) findViewById(R.id.login);
        register = (Button) findViewById(R.id.build);
        remember= (CheckBox) findViewById(R.id.box_load);

        pref= PreferenceManager.getDefaultSharedPreferences(this);
        boolean isremenber=pref.getBoolean("remember_password",false);
        if (isremenber){
            //将账号和密码都保留在文本框中
            String name=pref.getString("name","");
            String pwd=pref.getString("password","");
            username.setText(name);
            passward.setText(pwd);
            remember.setChecked(true);
        }

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Log_In.this, Build_Activity.class);
                startActivity(intent);
                finish();
            }
        });

        log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = username.getText().toString();
                String pasw = passward.getText().toString();
                RequestParams logimformation = new RequestParams();
                AsyncHttpClient test = new AsyncHttpClient();

                editor=pref.edit();
                //检查复选框是否选中
                if (remember.isChecked()){
                    editor.putBoolean("remember_password",true);
                    editor.putString("name", name);
                    editor.putString("password",pasw);
                }else
                {
                    editor.clear();
                }
                editor.commit();

                logimformation.add("name", name);
                logimformation.add("password", pasw);

                RequestHandle post = test.post("http://114.215.144.204:9090/login", logimformation, new AsyncHttpResponseHandler() {

                    @Override
                    public void onSuccess(int i, Header[] headers, byte[] bytes) {
                        ObjectMapper objectMapper = new ObjectMapper();

                        ResponseEntity responseEntity = null;
                        try {
                            responseEntity = objectMapper.readValue(bytes, ResponseEntity.class);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        int code = responseEntity.getCode();
                        if (code != 1) {
                            Toast.makeText(Log_In.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        UserEntity user = responseEntity.getData();
                        Intent intent = new Intent(Log_In.this, My_Activity.class);
                        intent.putExtra( "UserEntity_data", user);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                        Toast.makeText(Log_In.this, "网络异常，请检查网络", Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });
    }
}