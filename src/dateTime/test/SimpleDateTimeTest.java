package dateTime.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.Test;

import dateTime.SimpleDateTime;

class SimpleDateTimeTest {

	@Test
	void test() {
		getLastMonthlyTimePeriodWithReferenceDay();
	}
	
	private void getLastMonthlyTimePeriodWithReferenceDay() {
		GregorianCalendar referenceDay;
		GregorianCalendar[] expected = new GregorianCalendar[2];
		GregorianCalendar[] actual;
		
		
		referenceDay = new GregorianCalendar(2010,10,5);
		expected[0] = new GregorianCalendar(2010,8,10);
		expected[1] = new GregorianCalendar(2010,9,9,23,59,59);
		expected[1].set(Calendar.MILLISECOND, 999);
		actual = SimpleDateTime.getLastMonthlyTimePeriodWithReferenceDay(10, referenceDay);
		assertArrayEquals(expected, actual);
		
		referenceDay = new GregorianCalendar(2010,10,15);
		expected[0] = new GregorianCalendar(2010,9,10);
		expected[1] = new GregorianCalendar(2010,10,9,23,59,59);
		expected[1].set(Calendar.MILLISECOND, 999);
		actual = SimpleDateTime.getLastMonthlyTimePeriodWithReferenceDay(10, referenceDay);
		assertArrayEquals(expected, actual);
	}

}
