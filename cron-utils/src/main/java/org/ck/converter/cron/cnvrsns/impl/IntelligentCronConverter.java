package org.ck.converter.cron.cnvrsns.impl;

import org.ck.converter.cron.CronExpConverter;
import org.ck.converter.cron.cnvrsns.Converter;

public class IntelligentCronConverter implements Converter{

	@Override
	public String convertSeconds(String secondsValue) {
		// Just Not intrested in any conversion on seconds
		//As there will not be much of a difference even after
		//Conversion
		return secondsValue;
	}

	@Override
	public String convertMinutes(String[] inar, CronExpConverter callHelper, String inputTimeZone) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String convertHours(String[] inar, String inputTimeZone,CronExpConverter calHelper) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String convertDaysOfMonth(String[] inar, String inputTimeZone,  CronExpConverter calHelper) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String convertWeekDayOfMonth(String[] inar, String inputTimeZone,  CronExpConverter calHelper) {
		// TODO Auto-generated method stub
		return null;
	}

}
