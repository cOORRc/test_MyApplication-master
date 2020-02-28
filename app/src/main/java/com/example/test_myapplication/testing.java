package com.example.test_myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class testing extends AppCompatActivity {
    Button butt_testing;
    RequestQueue mQueue;
    EditText Euser,Epass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mQueue = Volley.newRequestQueue(this);

        Euser = (EditText)findViewById(R.id.editText);
        Epass = (EditText)findViewById(R.id.editText2);

        butt_testing = (Button)findViewById(R.id.button2);
        butt_testing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Euser != null && Epass!= null){
                    ClickLogin();
                    Toast.makeText(testing.this,"เรียบร้อบ",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(testing.this,"กรุณากรอกข้อมูล",Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void ClickLogin() {      //เมดธอท jsonParse()
        String url = "https://api.albatrossthai.com/app/login.php";        //api ที่เรียกใช้
                Map<String, String> data = new HashMap<String, String>();
                //  params.put("ชื่อฟิวที่จะใส่ใน db(ตัวรับในdb)", java id ใน andriodstu(ตัวส่ง) .getText().toString());
//                params.put("username", user.getText().toString());
//                params.put("password", pass.getText().toString());
//                params.put("username", Euser.getText().toString().trim());
//                params.put("password", Epass.getText().toString().trim());

                data.put("rrr","ghg");
//                JSONObject data = new JSONObject(params);


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url,new JSONObject(data) ,    //เก็บค่าคำขอใช้ api
                new Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("Rest Response : " , response.toString());
//                        try {
//                            //อยู่ใใน try{}
//                            //เอาข้อมูลที่อยู่ใน json ที่อยู่ในส่วน items เพื่อนำมาแสดงผล โดยจะเก็บไว้ในตัวแปร jsonArray
//                            JSONArray jsonArray = response.getJSONArray("array");
//                            for (int i = 0; i < jsonArray.length(); i++) {
//                                JSONObject git = jsonArray.getJSONObject(i);
//                                String status = git.getString("status");
//                                String message = git.getString("message");
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
                        Toast.makeText(testing.this,response.toString(),Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("VolleyError","Error");
                error.printStackTrace();

            }
        })
        {

//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                Map<String,String> header = new HashMap<String, String>();
//                header.put("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
//                return header;
//            }
            @Override
            public String getBodyContentType(){
                return "application/x-www-form-urlencoded";
            }

        }
        ;
        mQueue.add(request);
    }
}
