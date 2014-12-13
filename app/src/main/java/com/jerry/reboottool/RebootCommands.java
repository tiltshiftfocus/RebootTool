package com.jerry.reboottool;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
//import android.widget.EditText;
import android.preference.PreferenceManager;
import android.widget.Toast;

public class RebootCommands {
	
	private Context context;
	private RunAsRoot run1;
    private SharedPreferences pref;
	
	
	public RebootCommands(Context context){
		this.context = context;
		run1 = new RunAsRoot();
        pref = PreferenceManager.getDefaultSharedPreferences(context);
	}
	
	public void sendCommand(final String command){

		if(command.isEmpty()){
			Toast.makeText(context, "Enter a command!", Toast.LENGTH_SHORT).show();
		}else{
			if(pref.getBoolean("pref_prompt", false)==true){
				AlertDialog.Builder builder = new AlertDialog.Builder(context);
				builder.setMessage("Are you sure you want to send command: " + command + "?")
				.setCancelable(false)
				.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						// fire an intent go to your next activity
						run1.Run(command);
						createSendToast(command);
					}
				})
				.setNegativeButton("No", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});
				AlertDialog alert = builder.create();
				alert.show();
			}else{
				run1.Run(command);
				createSendToast(command);
			}
		}


	}

	public void hotReboot(final String[] commands){
		if(pref.getBoolean("pref_prompt", false)==true){

			AlertDialog.Builder builder = new AlertDialog.Builder(context);
			builder.setMessage("Are you sure you want to hot reboot?")
			.setCancelable(false)
			.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					// fire an intent go to your next activity
					run1.Run(commands);
				}
			})
			.setNegativeButton("No", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					dialog.cancel();
				}
			});
			AlertDialog alert = builder.create();
			alert.show();

		}else{
			run1.Run(commands);
		}
	}

	public void reboot(final String[] commands){
		if(pref.getBoolean("pref_prompt", false)==true){

			AlertDialog.Builder builder = new AlertDialog.Builder(context);
			builder.setMessage("Are you sure you want to reboot?")
			.setCancelable(false)
			.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					// fire an intent go to your next activity
					run1.Run(commands);
				}
			})
			.setNegativeButton("No", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					dialog.cancel();
				}
			});
			AlertDialog alert = builder.create();
			alert.show();

		}else{
			run1.Run(commands);
		}
	}

	public void rebootBootLoader(final String[] commands){
		if(pref.getBoolean("pref_prompt", false)==true){

			AlertDialog.Builder builder = new AlertDialog.Builder(context);
			builder.setMessage("Are you sure you want to reboot to bootloader?")
			.setCancelable(false)
			.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					// fire an intent go to your next activity
					run1.Run(commands);
				}
			})
			.setNegativeButton("No", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					dialog.cancel();
				}
			});
			AlertDialog alert = builder.create();
			alert.show();

		}else{
			run1.Run(commands);
		}
	}

	public void rebootRecovery(final String[] commands){
		if(pref.getBoolean("pref_prompt", false)==true){

			AlertDialog.Builder builder = new AlertDialog.Builder(context);
			builder.setMessage("Are you sure you want to reboot to recovery?")
			.setCancelable(false)
			.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					// fire an intent go to your next activity
					run1.Run(commands);
				}
			})
			.setNegativeButton("No", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					dialog.cancel();
				}
			});
			AlertDialog alert = builder.create();
			alert.show();

		}else{
			run1.Run(commands);
		}
	}

	public void shutdownPhone(final String[] commands){
		if(pref.getBoolean("pref_prompt", false)==true){

			AlertDialog.Builder builder = new AlertDialog.Builder(context);
			builder.setMessage("Are you sure you want to shutdown?")
			.setCancelable(false)
			.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					// fire an intent go to your next activity
					run1.Run(commands);
				}
			})
			.setNegativeButton("No", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					dialog.cancel();
				}
			});
			AlertDialog alert = builder.create();
			alert.show();

		}else{
			run1.Run(commands);
		}
	}

	public void createSendToast(String command){
		Toast.makeText(context, "Command '"+command+"' sent", Toast.LENGTH_SHORT).show();
	}
}
