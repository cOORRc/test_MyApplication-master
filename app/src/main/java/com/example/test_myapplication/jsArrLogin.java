package com.example.test_myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class jsArrLogin extends AppCompatActivity {

    Button butt_testing;
    RequestQueue mQueue;
    EditText Euser,Epass;
    String TAG = "TAG";

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
                    try {
                        ClickLogin();
                        Toast.makeText(getApplicationContext(),"เรียบร้อบ",Toast.LENGTH_LONG).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(),"กรุณากรอกข้อมูล",Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void ClickLogin() throws JSONException {
        String url = getString(R.string.api_login);
        HashMap<String, String> params = new HashMap<>();
        params.put("username",Euser.getText().toString().trim());
        params.put("password",Epass.getText().toString().trim());

        HashMap<String, HashMap<String, String>> data = new HashMap<>();
        data.put("data",params);

        JsonArrayRequest arr = new JsonArrayRequest(Request.Method.GET,url,new JSONArray(), new Response.Listener<JSONArray>(){
            @Override
            public void onResponse(JSONArray response) {
                Log.d(TAG, response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.e("VolleyError","Error");
                error.printStackTrace();

                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                    Toast.makeText(getApplicationContext(), "Communication Error!", Toast.LENGTH_SHORT).show();

                } else if (error instanceof AuthFailureError) {
                    Toast.makeText(getApplicationContext(), "Authentication Error!", Toast.LENGTH_SHORT).show();
                    Log.e("VolleyAuthFailureError", String.valueOf((error instanceof AuthFailureError)));

                } else if (error instanceof ServerError) {
                    Toast.makeText(getApplicationContext(), "Server Side Error!", Toast.LENGTH_SHORT).show();
                    Log.e("VolleyServerError", String.valueOf((error instanceof ServerError)));

                } else if (error instanceof ServerError || error.getCause() instanceof ServerError) {
                    Toast.makeText(getApplicationContext(), "Server is not responding.", Toast.LENGTH_SHORT).show();
                    Log.e("VolleyServerError2", String.valueOf((error instanceof ServerError)|| error.getCause() instanceof ServerError));

                } else if (error instanceof NetworkError) {
                    Toast.makeText(getApplicationContext(), "Network Error!", Toast.LENGTH_SHORT).show();
                    Log.e("VolleyNetworkError", String.valueOf((error instanceof NetworkError)));

                } else if (error instanceof ParseError) {
                    Toast.makeText(getApplicationContext(), "Parse Error!", Toast.LENGTH_SHORT).show();
                    Log.e("VolleyParseError", String.valueOf((error instanceof ParseError)));
                }

            }
        })
        {
            @Override
            public Map<String, String> getHeaders(){
                Map<String,String> header = new HashMap<String, String>();
                //header.put("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
                return header;
            }
            @Override
            public String getBodyContentType(){
                return "application/x-www-form-urlencoded";
            }
        }

        ;


        mQueue.add(arr);
//        mQueue.cancelAll(request); //ยกเลิกคำขอทั้งหมด
        Log.e("VolleyReq", String.valueOf(arr));
        Toast.makeText(this,"บันทึกข้อมูลเรียบร้อย",Toast.LENGTH_SHORT).show();

        arr.setRetryPolicy(new DefaultRetryPolicy(20000, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

    }
}
