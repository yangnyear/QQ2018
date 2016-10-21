package com.swpuiot.qq2018;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;

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
                if (!ages.matches("^\\+?[1-9][0-9]*$")) {
                    Toast.makeText(Build_Activity.this, "您输入的年龄不合法，请重新输入", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(passw!=repasswd){
                    Toast.makeText(Build_Activity.this, "您输入的两次密码不匹配，请重新输入", Toast.LENGTH_SHORT).show();
                }
//                int ageage = Integer.parseInt(ages);
                String mottos = motto.getText().toString();
                RequestParams bildimformation = new RequestParams();
                AsyncHttpClient builtext = new AsyncHttpClient();
                bildimformation.add("name", name);
                bildimformation.add("password", passw);
                bildimformation.add("age",ages);
                bildimformation.add("text", mottos);
                builtext.post("http://114.215.144.204:9090/register", bildimformation, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int i, Header[] headers, byte[] bytes) {
                        Toast.makeText(Build_Activity.this, "注册成功，可以登录了", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {

                    }
                });

            }
        });
    }
}
