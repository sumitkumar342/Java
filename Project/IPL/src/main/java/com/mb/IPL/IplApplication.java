package com.mb.IPL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.reflect.Array;
import java.util.Arrays;

@SpringBootApplication
public class IplApplication {
	private static final Logger logger = LoggerFactory.getLogger(IplApplication.class);
	public static void main(String[] args) {
		logger.info("Argument passed: {}", Arrays.toString(args));
		if(args[1].equals("true")){
			logger.info("Start stand alone project ..!!");
			StandAlone standAlone = new StandAlone();
			standAlone.standAlone(args);
		} else{
			System.out.println("Start web project..!!");
			SpringApplication.run(IplApplication.class, args);
		}

	}

}
