package com.jerry.reboottool;

import java.io.DataOutputStream;

public class RunAsRoot {
	
	public RunAsRoot(){}
	
	public void Run(String[] cmds){
		try {
			Process p = Runtime.getRuntime().exec("su");
			DataOutputStream os = new DataOutputStream(p.getOutputStream());
			for (String tmpCmd : cmds) {
				os.writeBytes(tmpCmd + "\n");
			}
			os.writeBytes("exit\n");
			os.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void Run(String cmd){
		try {
			Process p = Runtime.getRuntime().exec("su");
			DataOutputStream os = new DataOutputStream(p.getOutputStream());
			/*for (String tmpCmd : cmds) {
				os.writeBytes(tmpCmd + "\n");
			}*/
			os.writeBytes(cmd + "\n");
			os.writeBytes("exit\n");
			os.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
