package com.neomind.fusion.custom.jobs;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class SimpleDateTime {
	
	public static GregorianCalendar[] getLastMonthlyTimePeriodWithReferenceDay(int firstDay, GregorianCalendar referenceDay) {
		GregorianCalendar[] period = new GregorianCalendar[2];
		
		period[1] = new GregorianCalendar(referenceDay.get(Calendar.YEAR), 
				referenceDay.get(Calendar.MONTH), 
				firstDay, 
				23, 59, 59);
		period[1].set(Calendar.MILLISECOND, 999);
		period[1].add(Calendar.DAY_OF_MONTH, -1);
		if(period[1].after(referenceDay)) {
			period[1].add(Calendar.MONTH, -1);
		}
		
		period[0] = new GregorianCalendar(period[1].get(Calendar.YEAR), 
				period[1].get(Calendar.MONTH), 
				firstDay);
		period[0].add(Calendar.MONTH, -1);
		
		return period;
	}
}
