package string.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.Test;

import dateTime.SimpleDateTime;
import string.Numbers;

class NumbersTest {

	@Test
	void test() {
		removeIrrelevantNonNumericCharacters();
	}
	
	private void removeIrrelevantNonNumericCharacters() {
		 
		assertEquals("0", Numbers.removeIrrelevantNonNumericCharacters("0"));
		assertEquals("2.3", Numbers.removeIrrelevantNonNumericCharacters("2.3"));
		assertEquals("14,56", Numbers.removeIrrelevantNonNumericCharacters("14,56"));
		assertEquals("1.789,00", Numbers.removeIrrelevantNonNumericCharacters("1.789,00"));
		assertEquals("-1.000,00", Numbers.removeIrrelevantNonNumericCharacters("-1.000,00"));
		assertEquals("10,00", Numbers.removeIrrelevantNonNumericCharacters("R$10,00"));
		assertEquals("10,00", Numbers.removeIrrelevantNonNumericCharacters("10,00%"));
		assertNull(Numbers.removeIrrelevantNonNumericCharacters(null));
		assertEquals("", Numbers.removeIrrelevantNonNumericCharacters(""));
		assertEquals(".", Numbers.removeIrrelevantNonNumericCharacters("."));
		assertEquals(",", Numbers.removeIrrelevantNonNumericCharacters(","));
		assertEquals("-", Numbers.removeIrrelevantNonNumericCharacters("-"));
		assertEquals("", Numbers.removeIrrelevantNonNumericCharacters("%"));
		assertEquals("", Numbers.removeIrrelevantNonNumericCharacters("R$"));
		
	}

}
