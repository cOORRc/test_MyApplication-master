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
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.android.volley.Request.Method.GET;

public class loginT1 extends AppCompatActivity {
    EditText etUname,etPass;
    Button btn;
    TextView show1;
    RequestQueue queue;
//    String boundary = "*****" + Long.toString(System.currentTimeMillis()) + "*****";
    private final String twoHyphens = "--";
    private final String lineEnd = "\r\n";
    String boundary = "apiclient-" + System.currentTimeMillis();
    String multipart_fordata = "multipart/form-data;boundary=" + boundary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        show1 = (TextView) findViewById(R.id.textView4);
        etUname = findViewById(R.id.editText);
        etPass = findViewById(R.id.editText2);


        queue = Volley.newRequestQueue(this);
        btn = findViewById(R.id.button2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etUname.getText().toString().trim().length()>0 && etPass.getText().toString().trim().length() > 0){
                    Toast.makeText(getApplicationContext(), "รอสักครู่", Toast.LENGTH_LONG).show();
                    loginUser();
                }
                else {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลอีกครั้ง", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private void loginUser() {
//trim() ตัดช่องว่าง
        String url ="https://api.albatrossthai.com/app/login.php";

        String username = etUname.getText().toString().trim();
        String password = etPass.getText().toString().trim();

        HashMap<String, String> params = new HashMap<>();
            params.put("username",username);
            params.put("password",password);
        JSONObject data = new JSONObject(params);
        Log.i("HashMap_Parame", String.valueOf(params));

// Request a string response from the provided URL.
        JsonObjectRequest req = new JsonObjectRequest(url, data,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("Rest Response : " , response.toString());
                        JSONObject jObj = new JSONObject();
                        try {
                            int status = jObj.getInt("status");
                            String message = jObj.getString("message");
                            show1.setText(message);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
//                            try {
//                                JSONObject jsonObject = new JSONObject();
//                                if (jsonObject.getString("status").equals("true")) {
//                                    JSONArray dataArray = jsonObject.getJSONArray("data");
//                                    for (int i = 0; i < dataArray.length(); i++) {
//
//                                        JSONObject dataobj = dataArray.getJSONObject(i);
//                                        String status = dataobj.getString("status");
//                                        String message = dataobj.getString("message");
//                                        Log.i("data :", status+message);
//
//                                    }
//
//                                    Intent intent = new Intent(loginT1.this,MainActivity.class);
//                                    startActivity(intent);
//                                    finish();
//                                }
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }

                        Toast.makeText(getApplicationContext(), "ชื่อผู้ใช้หรือรหัสผ่านไม่ถูกต้อง", Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("VolleyError", String.valueOf(error));
                error.printStackTrace();
            }
        })

        {
//private
//            public String createPostBody(Map<String, String> params) {
//
//                Log.i("creatPostBody ::: ", String.valueOf(params));
//                StringBuilder sbPost = new StringBuilder();
//                Log.i("StringBuilder ::: ", String.valueOf(sbPost));
//                if (params != null) {
//                    for (String key : params.keySet()) {
//                        if (params.get(key) != null) {
//                            sbPost.append("\r\n" + "--" + boundary + "\r\n");
//                            sbPost.append("Content-Disposition: form-data; name=\"" + key + "\"" + "\r\n\r\n");
//                            sbPost.append(params.get(key).toString());
//                        }
//                    }
//                }
//                return sbPost.toString();
//            }


            private void buildTextPart(DataOutputStream dataOutputStream, String parameterName, String parameterValue) throws IOException {
                dataOutputStream.writeBytes(twoHyphens + boundary + lineEnd);
                dataOutputStream.writeBytes("Content-Disposition: form-data; name=\"" + parameterName + "\"" + lineEnd);
                dataOutputStream.writeBytes("Content-Type: text/plain; charset=UTF-8" + lineEnd);
                dataOutputStream.writeBytes(lineEnd);
                dataOutputStream.writeBytes(parameterValue + lineEnd);
            }

            @Override
            public String getBodyContentType() {
                return multipart_fordata ;
            }

//            public byte[] getBody() {
//                try {
//                    return createPostBody(getParams()).getBytes();
//                } catch (AuthFailureError authFailureError) {
//                    authFailureError.printStackTrace();
//                }
//                return new byte[0];
//            }
        }
        ;

// Add the request to the RequestQueue.
        queue.add(req);
        Log.i("multi",String.valueOf(multipart_fordata));
//  Remove map
//        params.remove(username);
//        params.remove(password);

    }
}
