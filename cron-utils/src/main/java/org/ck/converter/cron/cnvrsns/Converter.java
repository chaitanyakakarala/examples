package org.ck.converter.cron.cnvrsns;

import org.ck.converter.cron.CronExpConverter;

public interface Converter {

	String convertSeconds(String secondsValue);
	
	String convertMinutes(String[] inar, CronExpConverter callHelper, String inputTimeZone);
	
	String convertHours(String[] inar, String inputTimeZone, CronExpConverter calHelper);
	
	String convertDaysOfMonth(String[] inar, String inputTimeZone, CronExpConverter calHelper);
	
	String convertWeekDayOfMonth(String[] inar, String inputTimeZone, CronExpConverter calHelper);
	
}
