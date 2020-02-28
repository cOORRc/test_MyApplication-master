package com.example.test_myapplication.pin;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.test_myapplication.R;
import com.nightonke.blurlockview.BlurLockView;
import com.nightonke.blurlockview.Password;

public class Pin_BlurLockView extends AppCompatActivity {

	BlurLockView blurLockView;
	ImageView img_bg;
	private String TAG = "check_pin";

	private Password getPasswordType() {
		if ("PASSWORD_NUMBER".equals(getIntent().getStringExtra("PASSWORD_TYPE")))
			return Password.NUMBER;
		else if ("PASSWORD_NUMBER".equals(getIntent().getStringExtra("PASSWORD_TYPE")))
			return Password.TEXT;
		return Password.NUMBER;
	}
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pin__blur_lock_view);
		blurLockView = findViewById(R.id.blurlockview);
		img_bg = findViewById(R.id.img_bg);

		// Set the view that need to be blurred
		blurLockView.setBlurredView(img_bg);

// Set the password
		blurLockView.setCorrectPassword("0000");

		blurLockView.setTypeface(Typeface.DEFAULT);
		blurLockView.setType(Password.NUMBER,false);

//		blurLockView.setCorrectPassword(getIntent().getStringExtra("PASSWORD"));
//		blurLockView.setTitle(getIntent().getStringExtra("TITLE"));
		blurLockView.setLeftButton(getIntent().getStringExtra("LEFT_BUTTON"));
		blurLockView.setRightButton(getIntent().getStringExtra("RIGHT_BUTTON"));
//		blurLockView.setTypeface(getTypeface());
//		blurLockView.setType(getPasswordType(), false);




		blurLockView.setOnPasswordInputListener(new BlurLockView.OnPasswordInputListener() {
			@Override
			public void correct(String inputPassword) {
				Toast.makeText(getApplicationContext(),"correct : "+ inputPassword ,Toast.LENGTH_SHORT).show();
//				blurLockView.hide(1000, HideType.FADE_OUT, EaseType.EaseInBounce);
			}

			@Override
			public void incorrect(String inputPassword) {
				Toast.makeText(getApplicationContext(),"incorrect : " ,Toast.LENGTH_SHORT).show();
			}

			@Override
			public void input(String inputPassword)  {
				Log.d(TAG, "Pin empty");
			}
		});



	}
}
