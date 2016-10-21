package com.swpuiot.qq2018;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;


public class WelcomeActivity extends Activity {
    public static final int SHOW_ACTIVITY=0;
    private Handler handler = new Handler(){
        public void handleMessage(Message msg) {

            switch (msg.what){
                case SHOW_ACTIVITY:
                    Intent intent=new Intent(WelcomeActivity.this,Log_In.class);
                    startActivity(intent);
            }



        }
    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_welcome);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message message=new Message();
                message.what=SHOW_ACTIVITY;
                handler.sendMessage(message);


            }
        }).start();
    }
}
