package com.example.test_myapplication.pin;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.alimuzaffar.lib.pin.PinEntryEditText;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.test_myapplication.R;

public class PinActivity extends AppCompatActivity {

	private PinEntryEditText pinEntry;
	private Button butt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pin);
//	c  	this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

//		Pinview pinview1 = findViewById(R.id.pinview1);
//		pinview1.setPinViewEventListener(new Pinview.PinViewEventListener() {
//			@Override
//			public void onDataEntered(Pinview pinview, boolean fromUser) {
//				Toast.makeText(getApplicationContext(), pinview.getValue(), Toast.LENGTH_SHORT).show();
//			}
//		});
		ImageView img = (ImageView)findViewById(R.id.imgvw);
		Animation aniSlide = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate);
		img.startAnimation(aniSlide);

		butt = (Button)findViewById(R.id.button6);
		butt.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
//				Animation butt_anima = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate);
//				butt_anima.setDuration(200);
////				butt_anima.setFillAfter(true);
//				butt.startAnimation(butt_anima);

				YoYo.with(Techniques.ZoomIn)
						.duration(1000)
						.repeat(0)
						.playOn(findViewById(R.id.button6));

			}
		});


		pinEntry = findViewById(R.id.txt_pin_entry2);
		if (pinEntry != null) {
			pinEntry.setAnimateText(true);
			pinEntry.setOnPinEnteredListener(new PinEntryEditText.OnPinEnteredListener() {
				@Override
				public void onPinEntered(CharSequence str) {
					if (str.toString().equals("123456")) {
						Toast.makeText(getApplicationContext(), "SUCCESS", Toast.LENGTH_SHORT).show();
					} else {
						pinEntry.setError(true);
						Toast.makeText(getApplicationContext(), "FAIL", Toast.LENGTH_SHORT).show();
						pinEntry.postDelayed(new Runnable() {
							@Override
							public void run() {
								pinEntry.setText(null);
							}
						}, 1000);
					}
				}
			});
		}

	}
}
