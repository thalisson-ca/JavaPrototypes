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
		calculateMonthDifference();
	}
	
	
	private void calculateMonthDifference() {
		GregorianCalendar start;
		GregorianCalendar end;
		
		start = new GregorianCalendar(2010,10,5);
		end = new GregorianCalendar(2010,11,5);
		assertEquals(1, SimpleDateTime.calculateMonthDifference(start, end));
		
		end = new GregorianCalendar(2010,11,4);
		assertEquals(0, SimpleDateTime.calculateMonthDifference(start, end));
		
		end = new GregorianCalendar(2010,11,6);
		assertEquals(1, SimpleDateTime.calculateMonthDifference(start, end));
		
		end = new GregorianCalendar(2010,10,5);
		assertEquals(0, SimpleDateTime.calculateMonthDifference(start, end));
		
		end = new GregorianCalendar(2010,10,6);
		assertEquals(0, SimpleDateTime.calculateMonthDifference(start, end));
		
		end = new GregorianCalendar(2010,10,4);
		assertEquals(0, SimpleDateTime.calculateMonthDifference(start, end));
				
		end = new GregorianCalendar(2011,10,5);
		assertEquals(12, SimpleDateTime.calculateMonthDifference(start, end));

		end = new GregorianCalendar(2011,11,5);
		assertEquals(13, SimpleDateTime.calculateMonthDifference(start, end));
		
		end = new GregorianCalendar(2011,11,4);
		assertEquals(12, SimpleDateTime.calculateMonthDifference(start, end));
		
		end = new GregorianCalendar(2011,11,6);
		assertEquals(13, SimpleDateTime.calculateMonthDifference(start, end));
		
		end = new GregorianCalendar(2009,10,4);
		assertEquals(12, SimpleDateTime.calculateMonthDifference(start, end));

	}
	
	private void getLastMonthlyTimePeriodWithReferenceDay() {
		GregorianCalendar referenceDay;
		GregorianCalendar[] expected = new GregorianCalendar[2];
		GregorianCalendar[] actual;
		
		
		referenceDay = new GregorianCalendar(2010,10,5);
		expected[0] = new GregorianCalendar(2010,8,1);
		expected[1] = new GregorianCalendar(2010,9,31,23,59,59);
		expected[1].set(Calendar.MILLISECOND, 999);
		actual = SimpleDateTime.getLastMonthlyTimePeriodWithReferenceDay(1, referenceDay);
		assertArrayEquals(expected, actual);
		
		expected[0] = new GregorianCalendar(2010,8,28);
		expected[1] = new GregorianCalendar(2010,9,27,23,59,59);
		expected[1].set(Calendar.MILLISECOND, 999);
		actual = SimpleDateTime.getLastMonthlyTimePeriodWithReferenceDay(28, referenceDay);
		assertArrayEquals(expected, actual);
		
		try {
			SimpleDateTime.getLastMonthlyTimePeriodWithReferenceDay(0, referenceDay);
			fail("Should throw Illegal Argument Exception");
		}catch(IllegalArgumentException e) {
			System.out.println("getLastMonthlyTimePeriodWithReferenceDay: firstDay = 0 passed");
		}catch(Exception e) {
			fail("Should throw Illegal Argument Exception");
		}
		
		try {
			SimpleDateTime.getLastMonthlyTimePeriodWithReferenceDay(29, referenceDay);
			fail("Should throw Illegal Argument Exception");
		}catch(IllegalArgumentException e) {
			System.out.println("getLastMonthlyTimePeriodWithReferenceDay: firstDay = 29 passed");
		}catch(Exception e) {
			fail("Should throw Illegal Argument Exception");
		}
	}

}
