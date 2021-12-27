package com.cogent.estee.market.utility.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Treminal2 {
	
	
	 static final Logger log = LogManager.getLogger(Treminal2.class);
	
	public static void  restart() {
		
		try {
			ProcessBuilder processBuilder = new ProcessBuilder();
			processBuilder.command("bash", "-c", "pkill -f java.*market");
			log.info("First command executed");
			Process process = processBuilder.start();

			StringBuilder output = new StringBuilder();

			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

			String line;
			while ((line = reader.readLine()) != null) {
				output.append(line + "\n");
			}

			int exitVal = process.waitFor();
			if (exitVal == 0) {
				log.info("Success!");
				log.info("output : {} ",output);
				process.destroy();

			} else {
				// abnormal...
				log.info(" abnormal exitVal : {} ",exitVal);
				process.destroy();
			}
		} catch (Exception e) {
			log.error("Exception 1 : ",e);
		}
		
		try {
			log.info("Second command ");
			ProcessBuilder processBuilder = new ProcessBuilder();
			processBuilder.command("bash", "-c", "/home/cogent/liveproduct/estee/market/startApp.sh");
			
			Process process = processBuilder.start();

			StringBuilder output = new StringBuilder();

			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

			String line;
			while ((line = reader.readLine()) != null) {
				output.append(line + "\n");
			}

			int exitVal = process.waitFor();
			if (exitVal == 0) {
				log.info("Success!");
				log.info("output : {} ",output);
				process.destroy();

			} else {
				// abnormal...
				log.info(" abnormal exitVal : {} ",exitVal);
				process.destroy();
			}
		} catch (Exception e) {
			log.error("Exception 1 : ",e);
		}
		
	}

}
