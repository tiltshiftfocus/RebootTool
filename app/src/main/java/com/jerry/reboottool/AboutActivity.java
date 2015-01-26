package com.jerry.reboottool;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class AboutActivity extends ActionBarActivity {

    private Toolbar toolbar;
	private TextView versionNo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        if(pref.getBoolean("pref_darktheme",false)){
            setTheme(R.style.AppThemeBase);
        }else{
            setTheme(R.style.AppDarkTheme);
        }
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about_activity);

        // Set Support Toolbar
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        versionNo = (TextView) findViewById(R.id.version);
        try {
            String version = getPackageManager().getPackageInfo(getPackageName(),0).versionName;
            versionNo.setText(version);
        } catch (PackageManager.NameNotFoundException e) {

        }

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
