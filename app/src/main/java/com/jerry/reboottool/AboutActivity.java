package com.jerry.reboottool;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class AboutActivity extends Activity {
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		SharedPreferences pref = this.getSharedPreferences("com.jerry.reboottool", Context.MODE_PRIVATE);
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about_activity);		
	}
	
	public void sendEmail(View v){
		Intent i = new Intent(Intent.ACTION_SEND);
		i.setType("text/html");
		i.putExtra(Intent.EXTRA_EMAIL, "limzming@gmail.com");
		i.putExtra(Intent.EXTRA_SUBJECT, "Reboot Tool");
		i.putExtra(Intent.EXTRA_TEXT, "Feedback");

		startActivity(Intent.createChooser(i, "Send Email"));
	}

}
