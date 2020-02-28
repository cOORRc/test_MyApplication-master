package com.example.test_myapplication.pin;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.andrognito.pinlockview.IndicatorDots;
import com.andrognito.pinlockview.PinLockListener;
import com.andrognito.pinlockview.PinLockView;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.test_myapplication.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class pin_PFLockScreen extends AppCompatActivity {

	public static final String TAG = "PinLockView";
	public static String pass = "pass";
	RequestQueue requestQueue;

	private PinLockView mPinLockView;
	private IndicatorDots mIndicatorDots;

	@SuppressLint("ResourceAsColor")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestQueue = Volley.newRequestQueue(this);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_pin__pflock_screen);

		mIndicatorDots = (IndicatorDots) findViewById(R.id.indicator_dots);
		mPinLockView = (PinLockView) findViewById(R.id.pin_lock_view);

		mPinLockView.attachIndicatorDots(mIndicatorDots);
		mPinLockView.setPinLockListener(mPinLockListener);
		//mPinLockView.setCustomKeySet(new int[]{2, 3, 1, 5, 9, 6, 7, 0, 8, 4});
		//mPinLockView.enableLayoutShuffling();

		mPinLockView.setPinLength(6);

//		mPinLockView.setTextColor(ContextCompat.getColor(this, R.color.white));
		mIndicatorDots.setIndicatorType(IndicatorDots.IndicatorType.FILL_WITH_ANIMATION);

	}

	private PinLockListener mPinLockListener = new PinLockListener() {
		@Override
		public void onComplete(String pin) {

			Log.d(TAG, "Pin complete: " + pin);
			pass = pin;

			Toast.makeText(getApplicationContext(),"key : " + pin ,Toast.LENGTH_SHORT).show();
			Log.d(TAG,"Pin conver to string pass");
			json_pin(pass);
		}

		@Override
		public void onEmpty() {
			Log.d(TAG, "Pin empty");
		}

		@Override
		public void onPinChange(int pinLength, String intermediatePin) {
			Log.d(TAG, "Pin changed, new length " + pinLength + " with intermediate pin " + intermediatePin);
		}
	};

	private void json_pin(String pass) {
		String url_pin = "http://172.30.5.38/test_app/json/json_login.php";
		final String Pin_pass = pass;
		Log.d(TAG, "connect " + Pin_pass);
		JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url_pin, new Response.Listener<JSONArray>() {
			@Override
			public void onResponse(JSONArray response) {
				Log.d(TAG, "Yo" +response);
//				try {
//					JSONArray jsonArray = response.getJSONArray(1);
//					Log.d(TAG, "jsonArray : " + jsonArray );
//					ArrayList<String> all_pass = new ArrayList<String>();
//
//				} catch (JSONException e) {
//					e.printStackTrace();
//				}

				try {
					JSONArray jsonArray = new JSONArray(String.valueOf(response));
					Log.d(TAG, "jsonArray : " + jsonArray );
					JSONArray Contents = jsonArray.getJSONArray(0);
					Log.d(TAG, "Contents : " + Contents );
					ArrayList<String> all_pass = new ArrayList<String>();
					for (int i = 0; i < Contents.length(); i++) {
						JSONObject obj_db = Contents.getJSONObject(i);
						String db_Pass = obj_db.getString("f_pass");
						all_pass.add(db_Pass);
						String forceChagePass = all_pass.get(0);
					}

				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {

			}
		});

		requestQueue.add(jsonArrayRequest);
	}

	public void onClick(View view) {
	}
}
