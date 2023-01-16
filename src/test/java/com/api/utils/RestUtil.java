package com.api.utils;

import java.time.LocalDateTime;

import org.apache.commons.lang3.RandomStringUtils;

public class RestUtil {

	public static final String EXCEL = "C:/Users/cinth/eclipse-workspace/restapi/src/test/java/com/api/config/DataExcel.xlsx";

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
