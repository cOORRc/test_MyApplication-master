package com.example.test_myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.os.Handler;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    TextView show1,show2;
    ProgressBar prg;
    private static final int REFRESH_SCREEN = 1;
    private EditText et;
    private Button ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        show1 = (TextView) findViewById(R.id.textView);
        prg =  (ProgressBar) findViewById(R.id.progressBar);
        startScan(); // Sleep
        et = findViewById(R.id.et);
        ok = findViewById(R.id.butt);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et.getText().toString().trim().length()>0){
                    Toast.makeText(getApplicationContext(),"not null",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getApplicationContext(),"null",Toast.LENGTH_LONG).show();
                }
            }
        });




        OkHttpClient client = new OkHttpClient();
        //String url = "http://" + getString(R.string.url_web) + ".ngrok.io/getall";
        String url = "https://api.albatrossthai.com/app/hello.php";
        final Request request = new Request.Builder() //เชื่อมต่อ api
                .url(url)
                .build();

//        client.newCall(request).enqueue(new Callback() {  //ฝั่งไคแอนเรียกใช้ api
//            @Override
//            public void onFailure(Call call, IOException e) {  //สร้าง string เพื่อเอาไว้ดึงข้อมูลใน api มาแสดง
//                String mMessage = e.getMessage().toString();
//                Log.w("failure Response", mMessage);
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                if (response.isSuccessful()){ //เรียกใช้ได้สำเสร็จ
//                    final String mMessage = response.body().string(); // กำหนดให้เอาข้อมูลใน api ในส่วนข้อมูลที่อยู่ในรุป string มาแสดง
//                    MainActivity.this.runOnUiThread(new Runnable() {   //กำหนดให้แสดงข้อมูลในหน้า MainActivity
//                        @Override
//                        public void run() {
//                            show1.setText(mMessage);   //กำหนดว่าให้แสดงข้อความที่ text ที่สร้างขึ้น
//                        }
//                    });
//                }
//            }
//        });
    }

    private void startScan() {
        new Thread() {
            public void run() {
                try{
                    Thread.sleep(5000);
                    hRefresh.sendEmptyMessage(REFRESH_SCREEN);
                }catch(Exception e){
                }
            }
        }.start();
    }
    @SuppressLint("HandlerLeak") //พึ่งเพิ่ง 08.15 10/10/19
    Handler hRefresh = new Handler(){
        public void handleMessage(Message msg) {
            switch(msg.what){
                case REFRESH_SCREEN:
                    prg.setVisibility(View.INVISIBLE); // Hide ProgressBar
//                    show1.setText("Welcome to My App");
//                    show1.setVisibility(View.VISIBLE); // Show Text
                    break;
                default:
                    break;
            }
        }
    };
}
