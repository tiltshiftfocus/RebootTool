package com.jerry.reboottool;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.media.audiofx.BassBoost;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jerry.reboottool.settings.SettingsActivity;

public class MainActivity extends ActionBarActivity {

    private EditText editText1;
    private Button sendButton;
    private String PACKAGE_NAME;
    private RebootCommands rC;

    private Toolbar toolbar;
    private final int SETTINGS_RESULT = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        PACKAGE_NAME = this.getPackageName();
        rC = new RebootCommands(this);


        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        if (pref.getBoolean("pref_darktheme", false)) {
            setTheme(R.style.AppThemeBase);
        } else {
            setTheme(R.style.AppDarkTheme);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        editText1 = (EditText) findViewById(R.id.editText1);
        sendButton = (Button) findViewById(R.id.button5);

        if (pref.getBoolean("pref_advanced", false) == true) {
            editText1.setVisibility(View.VISIBLE);
            sendButton.setVisibility(View.VISIBLE);
        } else {
            editText1.setVisibility(View.INVISIBLE);
            sendButton.setVisibility(View.INVISIBLE);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection


        switch (item.getItemId()) {

            case R.id.settings:
                Intent i = new Intent(this, SettingsActivity.class);
                startActivityForResult(i,SETTINGS_RESULT);
                return true;

            case R.id.about:
                Intent aboutIntent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(aboutIntent);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void shutdownPhone(View view) {
        final String[] commands = {"poweroff"};
        rC.shutdownPhone(commands);
    }

    public void rebootRecovery(View view) {
        final String[] commands = {"reboot recovery"};
        rC.rebootRecovery(commands);
    }

    public void rebootBootLoader(View view) {
        final String[] commands = {"reboot bootloader"};
        rC.rebootBootLoader(commands);
    }

    public void reboot(View view) {
        final String[] commands = {"reboot"};
        rC.reboot(commands);
    }

    public void hotReboot(View view) {
        final String[] commands = {"killall system_server"};
        rC.hotReboot(commands);
    }

    public void sendCommand(View view) {
        final String command = editText1.getText().toString();
        rC.sendCommand(command);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == this.SETTINGS_RESULT){
            this.recreate();
        }
    }
}
