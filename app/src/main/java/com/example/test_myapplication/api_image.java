package com.example.test_myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class api_image extends AppCompatActivity {
    ImageView imgUser;
    Button butt;
    RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api_image);

        mQueue = Volley.newRequestQueue(this);
        imgUser = (ImageView)findViewById(R.id.imageView2);
        butt = (Button)findViewById(R.id.butt);
        butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    deimage();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void deimage() throws JSONException {
        final String url = "http://api.albatrossthai.com/images/mainimg.png";        //api ที่เรียกใช้
        Log.i("ConnectAPI","API");
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,    //เก็บค่าคำขอใช้ api
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject jsonObject = new JSONObject();
                            Log.i("jsonObject = ", String.valueOf(jsonObject));
                            final String url_detail_img = jsonObject.getString("bg");
                            //imgUser = (ImageView)findViewById(R.id.imageView2);
//                            Picasso.get()
//                                    .load(url_detail_img)
//                                    .placeholder(R.drawable.placeholder)
//                                    .error(R.drawable.er)
//                                    .into(imgUser);

                            Log.d("detail image = ", url_detail_img);

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
