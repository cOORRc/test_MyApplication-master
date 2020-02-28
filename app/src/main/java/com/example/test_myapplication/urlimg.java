package com.example.test_myapplication;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class urlimg extends AppCompatActivity {
    ImageView imgforTime;
    String url_img = "http://api.albatrossthai.com/images/mainimg.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_urlimg);
        imgforTime = (ImageView)findViewById(R.id.imageView);
        Picasso.get()
                .load(url_img)
                .centerCrop()
                .into(imgforTime);
    }
}
