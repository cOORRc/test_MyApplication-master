package com.example.test_myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


public class LifeCycle extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_cycle);
        Toast.makeText(LifeCycle.this,"lifecycle : onCreate invoked",Toast.LENGTH_LONG).show();
        Log.d("lifecycle","onCreate invoked");
    }
    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(LifeCycle.this,"lifecycle : onStart invoked",Toast.LENGTH_LONG).show();
        Log.d("lifecycle","onStart invoked");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(LifeCycle.this,"lifecycle : onResume invoked",Toast.LENGTH_LONG).show();
        Log.d("lifecycle","onResume invoked");
    }
    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(LifeCycle.this,"lifecycle : onPause invoked",Toast.LENGTH_LONG).show();
        Log.d("lifecycle","onPause invoked");
    }
    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(LifeCycle.this,"lifecycle : onStop invoked",Toast.LENGTH_LONG).show();
        Log.d("lifecycle","onStop invoked");
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(LifeCycle.this,"lifecycle : onRestart invoked",Toast.LENGTH_LONG).show();
        Log.d("lifecycle","onRestart invoked");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(LifeCycle.this,"lifecycle : onDestroy invoked",Toast.LENGTH_LONG).show();
        Log.d("lifecycle","onDestroy invoked");
    }
}
