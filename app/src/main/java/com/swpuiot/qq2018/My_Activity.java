package com.swpuiot.qq2018;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class My_Activity extends ActionBarActivity {
    private TextView showname;
    private TextView showid;
    private TextView showage;
    private TextView showtext;
    private Button Log_out;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_my_);
        showname= (TextView) findViewById(R.id.show_name);
        showid= (TextView) findViewById(R.id.show_id);
        showage= (TextView) findViewById(R.id.show_age);
        showtext= (TextView) findViewById(R.id.show_text);
        UserEntity user= (UserEntity) getIntent().getSerializableExtra("UserEntity_data");
        showname.setText(user.getName());
        showid.setText(user.getId()+"");
        showage.setText(user.getAge()+"");
        showtext.setText(user.getText());
        Log_out= (Button) findViewById(R.id.btn_remove);
        Log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(My_Activity.this,Log_In.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
