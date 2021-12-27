package com.cogent.estee.market.utility.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import org.apache.poi.util.IOUtils;

public class Terminal {
	
	public static void restartMarketJar() {
		
		Process process1 = null;
		Process process2 = null;
		Process process3 = null;

		Process p1 = null;
		Process p2 = null;
		Process p3 = null;

		InputStream input1 = null;
		OutputStream output1 = null;

		InputStream input2 = null;
		OutputStream output2 = null;
		
		
		boolean isServiceStop = false;
		try {
			String pid = null;


			p1 = Runtime.getRuntime().exec(new String[] { "ps", "-ef"}); 
			input1 = p1.getInputStream();
			p2 = Runtime.getRuntime().exec(new String[] { "grep", "java"});
			output1 = p2.getOutputStream();
			IOUtils.copy(input1, output1);
			output1.close();

			input2 = p2.getInputStream();
			p3 = Runtime.getRuntime().exec(new String[] { "grep", "market"});
			output2 = p3.getOutputStream();
			IOUtils.copy(input2, output2);
			output2.close();
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(p3.getInputStream()));
			String line= reader.readLine();				

			if(line!=null ) {
				
				while(line.contains("  ")) {
					line = line.replaceAll("  ", " ");
				}
				if(line.split(" ").length>=2) {

					System.out.println("Process Line: "+ line);   
					pid = line.split(" ")[1];//.split("     ")[0];
					System.out.println("Process Id: "+pid);

					process2 = Runtime.getRuntime().exec("sudo kill -9 "+pid);
					if(pid!=null && pid.trim().length()>0) {
						isServiceStop = true;
						System.out.println("Stopped the Service: "+ pid);  
					}
				}else {
					System.out.println("No Current Service is Running for Price Fetcher in founded Line: "+ pid); 
				}
			}else {
				System.out.println("No Current Service is Running for Price Fetcher: "+ pid); 
			}
			
			if(isServiceStop) {
				process3 = Runtime.getRuntime().exec("/home/cogent/liveproduct/estee/market/restart.sh"); 
				System.out.println("Restart the service Successfully "); 
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			if(process1!=null) {
				process1.destroy();
			}
			if(process2!=null) {
				process2.destroy();
			}
			if(process3!=null) {
				process3.destroy();
			}

			if(p1!=null) {
				p1.destroy();
			}
			if(p2!=null) {
				p2.destroy();
			}
			if(p3!=null) {
				p3.destroy();
			}


			try {
				if(input1!=null) input1.close();
				if(input2!=null) input2.close();
				if(output1!=null) output1.close();
				if(output2!=null) output2.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}  
		
	}

}
