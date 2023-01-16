package com.api.utils;

import java.text.DateFormatSymbols;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

import java.time.LocalDateTime;


import org.apache.commons.lang3.RandomStringUtils;

public class RestUtil {


	public static String batchName() {
		return String.format("%s%s-%s-%s-%s", 
				ZonedDateTime.now().getDayOfMonth(), 
				new DateFormatSymbols().getShortMonths()[ZonedDateTime.now().getMonth().getValue()-1],
				"NinjaSpark",
				"SDET",RandomStringUtils.randomNumeric(3));
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
		return String.format("%s%s-%s-%s-%s", 
				ZonedDateTime.now().getDayOfMonth(), 
				new DateFormatSymbols().getShortMonths()[ZonedDateTime.now().getMonth().getValue()-1],
				"NinjaSpark",
				"SDET",RandomStringUtils.randomNumeric(3));
	}

	public static String programDescription() {
		String generateString = RandomStringUtils.randomAlphabetic(5);
		return (generateString);		
	}

	public static final String EXCEL = "C:/Users/cinth/eclipse-workspace/NinjaSparks_API_Hackathon/src/test/java/com/api/testdata/DataExcel.xlsx";

	public static String programNameForExcel() {
		String generateString = RandomStringUtils.randomNumeric(10);
		return ("Jan23-NinjaSpark-" + generateString);
	}

	public static String programDescriptionForExcel() {
		String generateString = RandomStringUtils.randomAlphabetic(10);
		return ("NinjaSpark-" + generateString);
	}


	public static String getDateTime() {
		LocalDateTime Time = LocalDateTime.now();
		String date = String.valueOf(Time);
		return date;
	}
}
