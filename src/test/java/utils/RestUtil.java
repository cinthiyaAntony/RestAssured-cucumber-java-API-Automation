package utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;

import cucumber.api.cli.Main;

public class RestUtil {

	public static String batchName() {
		String generateString = RandomStringUtils.randomNumeric(3);
		return ("Jan23-NinjaSpark-SDET-SDET01-"+generateString);		
	}

	public static String batchNoOfClasses() {
		String generateString = RandomStringUtils.randomNumeric(2);
		return (generateString);		
	}

	public static String batchDescription() {
		String generateString = RandomStringUtils.randomAlphabetic(5);
		return (generateString);		
	}


	public static String programName() {
		String generateString = RandomStringUtils.randomNumeric(3);
		return ("Jan23-NinjaSpark-SDET-SDET01-"+generateString);		
	}

	public static String programDescription() {
		String generateString = RandomStringUtils.randomAlphabetic(5);
		return (generateString);		
	}


	public static String getDateTime() {
		LocalDateTime Time = LocalDateTime.now();
		String date = String.valueOf(Time);
		return date;
	}
}
