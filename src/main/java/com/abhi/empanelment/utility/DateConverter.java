package com.abhi.empanelment.utility;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.abhi.empanelment.service.UtilityService;

public class DateConverter {
	
	private final Logger logger = LoggerFactory.getLogger(DateConverter.class);
	
	public String DateSub(int daysDiff) {
		
		LocalDate newDate = LocalDate.now().minusDays(daysDiff);
		logger.info("updated date is: " + newDate);

		String convertedDate = DateTimeFormatter.ofPattern("yyyy/MM/dd", Locale.ENGLISH).format(newDate);
		logger.info(convertedDate);
		
		return convertedDate;
	}
	
	public String DateAdd(int daysDiff) {
		
		LocalDate newDate = LocalDate.now().plusDays(daysDiff);
		logger.info("updated date is: " + newDate);

		String convertedDate = DateTimeFormatter.ofPattern("yyyy/MM/dd", Locale.ENGLISH).format(newDate);
		logger.info(convertedDate);
		
		return convertedDate;
	}

}

