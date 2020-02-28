package com.example.test_myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


public class login extends AppCompatActivity {
    EditText user,pass;
    Button button;
    TextView show1;
    private RequestQueue mQueue;
    //String boundary = "*****" + Long.toString(System.currentTimeMillis()) + "*****";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        show1 = (TextView) findViewById(R.id.textView4);
        user = (EditText)findViewById(R.id.editText);
        pass = (EditText)findViewById(R.id.editText2);
        mQueue = Volley.newRequestQueue(this);  //ประกาศใช้ volley ในหน้านี้



        button = (Button)findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // hide keyboard
                InputMethodManager imm = (InputMethodManager) getSystemService(login.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);



                if (user.getText().toString().trim().length()>0 && pass.getText().toString().trim().length() > 0){
                    Toast.makeText(getApplicationContext(), "รอสักครู่", Toast.LENGTH_LONG).show();
                    sendRequests();

                }
                else {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลอีกครั้ง", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void sendRequests(){
        mQueue.getCache().clear();
        String url = "https://api.albatrossthai.com/app/login.php";
        String username = user.getText().toString().trim();
        String password = pass.getText().toString().trim();
        Log.i("input_text","pass = "+password);

        HashMap<String, String> params = new HashMap<>();
        params.put("username",username);
        params.put("password",password);
        Log.i("HashMap_Parame", String.valueOf(params));
        JSONObject data = new JSONObject(params);

//        HashMap<String, HashMap<String, String>> data = new HashMap<>();
//        data.put("data",params);
//        Log.i("HashMap", String.valueOf(data));

//        JSONObject postparams = new JSONObject();
//        postparams.put("username", user.getText().toqString());
//        postparams.put("password", pass.getText().toString());

        JsonObjectRequest request = new JsonObjectRequest(url, data, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONObject jsonObj = new JSONObject();
                Log.i("VolleyResponse", String.valueOf(response));
                int status = 0;
                try {
                    status = jsonObj.getInt("status");
                    String message = jsonObj.getString("message");
                    if (status == 0){
                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Log.e("VolleyError", String.valueOf(error));


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

//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                Map<String,String> header = new HashMap<String, String>();
//                header.put("Content-Type", "application/json; charset=utf-8");
//                return header;
//            }
            @Override
            public String getBodyContentType(){
                return  "application/json";
                //return  "multipart/form-data; boundary= "+boundary;
                //return "multipart/form-data";

            }
        }


        ;
        mQueue.add(request);
//        mQueue.cancelAll(request); //ยกเลิกคำขอทั้งหมด
        Log.i("VolleyReq", String.valueOf(request));
        //Toast.makeText(this,"บันทึกข้อมูลเรียบร้อย",Toast.LENGTH_SHORT).show();

        request.setRetryPolicy(new DefaultRetryPolicy(20000, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

    }

}