package com.cogent.estee.market.utility.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.spi.Terminable;



public class MarketDataUtility {
	
	 static final Logger log = LogManager.getLogger(MarketDataUtility.class);

	public static void main(String[] args) {

		/*if(args.length >0) {
			String esteeIds =args[0];
			String b =args[1];
			log.info("esteeIds : {} ",esteeIds);
			
			System.out.println("esteeIds : {} "+esteeIds);
			System.out.println("bb ===> "+b);
			executeCommand(esteeIds);
		}*/
		
		Treminal2.restart();
		
	}

	public static void executeCommand(String esteeIds) {

		try {
			ProcessBuilder processBuilder = new ProcessBuilder();
			processBuilder.command("bash", "-c", "source /home/estee/Platform/RUNME");
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
			processBuilder.command("bash", "-c", "/home/estee/Platform/Estee.MDTestUtility 9588 " + esteeIds + " 2");
			Process process = processBuilder.start();

			StringBuilder output = new StringBuilder();
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

	
			/*
			 * while ((line = reader.readLine()) != null) { output.append(line + "\n"); }
			 */
			/*int exitVal = process.waitFor();
			if (exitVal == 0) {
				log.info("Success!");
				log.info("output : {} ",output);
				process.destroy();

			} else {
				// abnormal...
				log.info("abnormal Error {} ", exitVal);
				process.destroy();
			}*/
			
			log.info("line read ==> {} ",reader.readLine());
			log.info("error stream : {} ",process.getErrorStream());
			Thread.sleep(1000);
			
			process.destroy();
			log.info("running process kill ===");
			
		} catch (Exception e) {
			log.error("Exception 2 : ",e);
		}
	}

}
