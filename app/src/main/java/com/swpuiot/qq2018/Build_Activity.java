package com.swpuiot.qq2018;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;

import java.io.IOException;

public class Build_Activity extends Activity {
    private EditText new_name;
    private EditText new_passw;
    private EditText repassw;
    private EditText age;
    private EditText motto;
    private Button bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_build_);
        new_name= (EditText) findViewById(R.id.text_new_username);
        new_passw= (EditText) findViewById(R.id.text_new_userpassword);
        repassw= (EditText) findViewById(R.id.text_reinport_password);
        age= (EditText) findViewById(R.id.text_age);
        motto= (EditText) findViewById(R.id.text_motto);
        bd= (Button) findViewById(R.id.btn_build);
        bd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = new_name.getText().toString().trim();
                String passw = new_passw.getText().toString().trim();
                String repasswd = repassw.getText().toString().trim();
                String ages = age.getText().toString().trim();
                String mottos = motto.getText().toString();
                if("".equals(name)||"".equals(passw)||"".equals(repasswd)||"".equals(ages)||"".equals(mottos)){
                    Toast.makeText(Build_Activity.this, "请补全信息", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!passw.equals(repasswd)){
                    Toast.makeText(Build_Activity.this, "您输入的两次密码不匹配，请重新输入", Toast.LENGTH_SHORT).show();
                    new_passw.setText("");
                    repassw.setText("");

                }
                if (!ages.matches("^\\+?[1-9][0-9]*$")) {
                    Toast.makeText(Build_Activity.this, "您输入的年龄不合法，请重新输入", Toast.LENGTH_SHORT).show();
                    age.setText("");
                    return;
                }


                RequestParams bildimformation = new RequestParams();
                AsyncHttpClient builtext = new AsyncHttpClient();
                bildimformation.add("name", name);
                bildimformation.add("password", passw);
                bildimformation.add("age",ages);
                bildimformation.add("text", mottos);
                builtext.post("http://114.215.144.204:9090/register", bildimformation, new AsyncHttpResponseHandler() {
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
                            Toast.makeText(Build_Activity.this, "注册失败，请重新注册", Toast.LENGTH_SHORT).show();
                           new_name.setText("");
                            new_passw.setText("");
                            repassw.setText("");
                            age.setText("");
                            motto.setText("");
                            bd.setText("");
                            return;
                        }
                        Toast.makeText(Build_Activity.this, "注册成功，可以登录了", Toast.LENGTH_SHORT).show();
                        finish();
                        Intent intent=new Intent(Build_Activity.this,Log_In.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                        Toast.makeText(Build_Activity.this, "网络异常，请检查网络", Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });
    }
}
