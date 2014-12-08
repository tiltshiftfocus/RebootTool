package com.jerry.reboottool;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends ActionBarActivity {
	
	private EditText editText1;
	private Button sendButton;
	private String PACKAGE_NAME;
	private RebootCommands rC;

    private Toolbar toolbar;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		PACKAGE_NAME = this.getPackageName();
		rC = new RebootCommands(this, PACKAGE_NAME);

		
		SharedPreferences pref = this.getSharedPreferences(PACKAGE_NAME, Context.MODE_PRIVATE);
		if(pref.getBoolean("darktheme", false)==true){
			setTheme(R.style.AppTheme);
		}else{
			setTheme(R.style.AppTheme);
		}			
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
        toolbar = (Toolbar)findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
		
		editText1 = (EditText)findViewById(R.id.editText1);
		sendButton = (Button)findViewById(R.id.button5);
		
		if(pref.getBoolean("advanced", false)==true){
			editText1.setVisibility(View.VISIBLE);
			sendButton.setVisibility(View.VISIBLE);
		}else{
			editText1.setVisibility(View.INVISIBLE);
			sendButton.setVisibility(View.INVISIBLE);
		}
			
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		
		SharedPreferences pref = this.getSharedPreferences(PACKAGE_NAME, Context.MODE_PRIVATE);
		
		boolean advPref = pref.getBoolean("advanced", false);
		boolean promptPref = pref.getBoolean("prompt", false);
		boolean themePref = pref.getBoolean("darktheme", false);
		
		
		menu.findItem(R.id.advanced).setChecked(advPref);
		menu.findItem(R.id.prompt).setChecked(promptPref);
		
		
		
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection
		SharedPreferences pref = this.getSharedPreferences(PACKAGE_NAME, Context.MODE_PRIVATE);
		Editor editor = pref.edit();


		switch (item.getItemId()) {

		case R.id.advanced:
			if(item.isChecked()){
				item.setChecked(false);
				editor.putBoolean("advanced", false);
				editor.commit();
				editText1.setVisibility(View.INVISIBLE);
				sendButton.setVisibility(View.INVISIBLE);
			}else{
				item.setChecked(true);
				editor.putBoolean("advanced", true);
				editor.commit();
				editText1.setVisibility(View.VISIBLE);
				sendButton.setVisibility(View.VISIBLE);
			}
			return true;
		case R.id.prompt:
			if(item.isChecked()){
				item.setChecked(false);
				editor.putBoolean("prompt", false);
				editor.commit();
			}else{
				item.setChecked(true);
				editor.putBoolean("prompt", true);
				editor.commit();
			}
			return true;
			
		

		case R.id.about:
			Intent i = new Intent(MainActivity.this, AboutActivity.class);
			startActivity(i);
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	public void shutdownPhone(View view){
		final String[] commands = {"poweroff"};
		rC.shutdownPhone(commands);
	}
	
	public void rebootRecovery(View view){
		final String[] commands = {"reboot recovery"};
		rC.rebootRecovery(commands);
	}
	
	public void rebootBootLoader(View view){
		final String[] commands = {"reboot bootloader"};
		rC.rebootBootLoader(commands);
	}
	
	public void reboot(View view){
		final String[] commands = {"reboot"};
		rC.reboot(commands);
	}
	
	public void hotReboot(View view){
		final String[] commands = {"killall system_server"};
		rC.hotReboot(commands);
	}
	
	public void sendCommand(View view){
		final String command = editText1.getText().toString();
		rC.sendCommand(command);
	}
	

}
