package com.example.test_myapplication.pin;

import android.graphics.Color;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.andrognito.pinlockview.PinLockView;
import com.example.test_myapplication.R;
import com.goodiebag.pinview.Pinview;

public class PinViewText_Activity extends AppCompatActivity {

	private PinLockView mPinLockView;
	String TAG="";
	private Pinview pinview1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pinview);
		this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

		Pinview pinview1 = findViewById(R.id.pinview1);
		pinview1.setPinViewEventListener(new Pinview.PinViewEventListener() {
			@Override
			public void onDataEntered(Pinview pinview, boolean fromUser) {
				Toast.makeText(PinViewText_Activity.this, pinview.getValue(), Toast.LENGTH_SHORT).show();
			}
		});

		// pinView Customize
		Pinview pinview5 = findViewById(R.id.pinview5);
		pinview5.setCursorShape(R.drawable.cursor);
//        pinview5.setCursorColor(Color.BLUE);
		pinview5.setTextSize(12);
		pinview5.setTextColor(Color.BLACK);
		pinview5.showCursor(true);

	}

//		mPinLockView = (PinLockView) findViewById(R.id.pin_lock_view);
//		mPinLockView.setPinLockListener(mPinLockListener);

//	}
//	private PinLockListener mPinLockListener = new PinLockListener() {
//		@Override
//		public void onComplete(String pin) {
//			Log.d(TAG, "Pin complete: " + pin);
//		}
//
//		@Override
//		public void onEmpty() {
//			Log.d(TAG, "Pin empty");
//		}
//
//		@Override
//		public void onPinChange(int pinLength, String intermediatePin) {
//			Log.d(TAG, "Pin changed, new length " + pinLength + " with intermediate pin " + intermediatePin);
//		}
//	};
}
