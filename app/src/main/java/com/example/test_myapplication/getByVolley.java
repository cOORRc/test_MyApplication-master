package com.example.test_myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class getByVolley extends AppCompatActivity {
    private TextView mTextViewResult;
    private RequestQueue mQueue;
    Button buttonParse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_by_volley);

        mTextViewResult = findViewById(R.id.textView2);
        buttonParse = findViewById(R.id.button);

        mQueue = Volley.newRequestQueue(this);  //ประกาศใช้ volley ในหน้านี้
  //      jsonParse();       //เรียกใช้เมดธอท jsonParse()

        buttonParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonParse();
                Log.i("Click","get jsonParse");

            }
        });
    }

    private void jsonParse() {      //เมดธอท jsonParse()

        String url = "https://api.github.com/search/users?q=dreamt";        //api ที่เรียกใช้
        Log.i("ConnectAPI",url);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,    //เก็บค่าคำขอใช้ api
                new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {
                try {
                    //เอาข้อมูลที่อยู่ใน json ที่อยู่ในส่วน items เพื่อนำมาแสดงผล โดยจะเก็บไว้ในตัวแปร jsonArray
                    JSONArray jsonArray = response.getJSONArray("items");
                    Log.e("Cell json", String.valueOf(response));

                    for (int i = 0; i < jsonArray.length(); i++) {
                        // JSONObject dataName = jsonArray.getJSONObject(i);
                        // String NametextSomething = dataName.getString("nameInAPI");
                        // int age = dataName.getInt("how old");
                        // nameTextForShow.append(NametextSomething + ", " + String.valueOf(age) + "\n\n");  //ชื่อเดียวกับตัวแปรข้างบน (String NametextSomething)
                        JSONObject git = jsonArray.getJSONObject(i);
                        String user = git.getString("login");
                        String id = git.getString("id");
                        String node_id = git.getString("node_id");
                        String ava = git.getString("avatar_url");
                        String ww = git.getString("url");
                        String html = git.getString("html_url");
                        String followers = git.getString("followers_url");

                        mTextViewResult.append(user + ", " + id + ", "+ "\n" + node_id + "\n\n"
                                + ava+": "+"\n"
                                + ww + ": "+"\n"
                                + html + ": "+"\n"
                                + followers+": "+"\n\n\n" );
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("VolleyError","Error");
                error.printStackTrace();

            }
        });

        mQueue.add(request);
    }
}