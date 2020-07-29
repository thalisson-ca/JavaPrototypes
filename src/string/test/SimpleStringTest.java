package string.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.Test;

import dateTime.SimpleDateTime;
import string.Numbers;
import string.SimpleString;

class SimpleStringTest {

	@Test
	void test() {
		toCamelCase();
	}
	
	private void toCamelCase() {
		assertEquals("Abacate", SimpleString.toCamelCase("abacate"));
		assertEquals("Abacate Verde", SimpleString.toCamelCase("abacate verde"));
		assertEquals("Abacate Verde", SimpleString.toCamelCase("aBaCaTe VeRdE"));

		
	}

}
