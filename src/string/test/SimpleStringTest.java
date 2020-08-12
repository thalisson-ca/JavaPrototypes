package string.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import string.SimpleString;

class SimpleStringTest {

	@Test
	void test() {
		toCamelCase();
		stripAccents();
		removeSpecialCharacteres();
	}
	
	
	private void removeSpecialCharacteres() {
		
		assertEquals("Naoseioqueestafalando1111", SimpleString.removeNotAlphanumericCharactersAndStripAccentsAndSpaces("N„o sei o que est· falando 1111!!!."));
	}
	
	private void stripAccents() {
		assertEquals("NAO", SimpleString.stripAccents("N√O"));
		assertEquals("cAO", SimpleString.stripAccents("Á√O"));
		
	}
	
	private void toCamelCase() {
		assertEquals("Abacate", SimpleString.toCamelCase("abacate"));
		assertEquals("Abacate Verde", SimpleString.toCamelCase("abacate verde"));
		assertEquals("Abacate Verde", SimpleString.toCamelCase("aBaCaTe VeRdE"));

		
	}

}
