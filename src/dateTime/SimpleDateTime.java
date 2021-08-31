package dateTime;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Class with common date and time methodos
 * @author Thalisson Christiano de Almeida
 *
 */
public class SimpleDateTime {
	
	
	/**
	 * Calculate how many months there are between two Gregorian Calendar.
	 * @param first a gregorian calendar with the start period.
	 * @param last another gregorian calendar with the end period. 
	 * @return the quantity of months between two dates.
	 */
	public static int calculateMonthDifference(GregorianCalendar first, GregorianCalendar last) {
		if(last.before(first)) {
			GregorianCalendar aux = first;
			first = last;
			last = aux;
			
		}
		
		int yearDif = last.get(Calendar.YEAR)-first.get(Calendar.YEAR);
		int monthDif = last.get(Calendar.MONTH)-first.get(Calendar.MONTH);
		int dayDif = last.get(Calendar.DAY_OF_MONTH)-first.get(Calendar.DAY_OF_MONTH);
		
		if(monthDif<0) {
			yearDif--;
			monthDif+=12;
		}
		if(dayDif<0) {
			monthDif--;
		}
		
		
		return yearDif*12+monthDif;
	}
	
	
	/**
	 * Given the first day of a monthly period, get the last 1 month period in relation of the reference day
	 * @param firstDay day of the month to be the first day of the period. Must be a number between 1 and 28
	 * @param referenceDay the day to be the reference to calculate the last period
	 * @return a GregoryCalendar array with the first [0] and the last [1] day of the period
	 */
	public static GregorianCalendar[] getLastMonthlyTimePeriodWithReferenceDay(int firstDay, GregorianCalendar referenceDay) {
		if(firstDay<1 || firstDay>28) {
			throw new IllegalArgumentException();
		}
		
		
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
