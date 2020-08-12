package string;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.StringJoiner;

/**
 * Class with commons String manipulations
 * @author Thalisson Christiano de Almeida
 *
 */
public class SimpleString {
	/**
	 * Join each array element into a single array
	 * @param array a string array to join
	 * @param separator a string which will separate each string to be joined
	 * @return a string with each element joined
	 */
	public static String join(String[] array, String separator) {

		StringJoiner stringJoiner = new StringJoiner(separator);
		for(String string:array) {
			stringJoiner.add(string);
		}

		return stringJoiner.toString();
	}

	/**
	 * Split a string using a separator character, but won't split if the separator character is between quotes ("")
	 * @param string String to be split
	 * @param separator a character which will determine where to split. Exception if the separator is inside quotes, which will be ignored
	 * @return a String array with the split strings
	 */
	public static String[] splitUnlessQuote(String string, char separator) {
		String[] result;
		if(string.contains("\"")){
			ArrayList<String> response = new ArrayList<>();
			StringBuilder sb = new StringBuilder();
			boolean quote = false;
			for(int i=0;i<string.length();i++){
				char currentChar = string.charAt(i);
				if(currentChar==separator) {
					if(quote){
						sb.append(currentChar);
					}else{
						response.add(sb.toString());
						sb = new StringBuilder();
					}
				}else if(currentChar=='"') {
					quote = !quote;
				}else {
					sb.append(currentChar);
				}
			}

			result = response.toArray(new String[0]);

		}else{
			result = string.split(separator+"");
		}

		return result;
	}

	/**
	 * Transform a string to Camel Case. e.g. "green apple" to "Green Apple"
	 * @param string a string to be transformed into Camel Case
	 * @return a string in Camel Case format
	 */
	public static String toCamelCase(final String string) {
		if (string == null)
			return null;

		final StringBuilder ret = new StringBuilder(string.length());

		for (final String word : string.split(" ")) {
			if (!word.isEmpty()) {
				ret.append(Character.toUpperCase(word.charAt(0)));
				ret.append(word.substring(1).toLowerCase());
			}
			if (!(ret.length() == string.length()))
				ret.append(" ");
		}

		return ret.toString();
	}
	
	/**
	 * Remove accents from characters. i.g. "Hélicoptère" to "Helicoptere"
	 * @param string a string
	 * @return the same string without accents
	 */
	public static String stripAccents(final String string) {
		if(string==null) 
			return null;
					
		char[] out = new char[string.length()];
		String normalizedString = Normalizer.normalize(string, Normalizer.Form.NFD);
		 int j = 0;
		    for (int i = 0, n = normalizedString.length(); i < n; ++i) {
		        char c = normalizedString.charAt(i);
		        if (c <= '\u007F') out[j++] = c;
		    }
		return (new String(out)).trim();
		
	}
	
	
	/**
	 * Remove accents from characters and non-alphanumerics characters (including spaces)
	 * @param string a string
	 * @return the same string without accents and without special characters, including spaces
	 */
	public static String removeNotAlphanumericCharactersAndStripAccentsAndSpaces(final String string) {
		if(string==null) 
			return null;
		
		char[] out = new char[string.length()];
		String normalizedString = Normalizer.normalize(string, Normalizer.Form.NFD);
		 int j = 0;
		    for (int i = 0, n = normalizedString.length(); i < n; ++i) {
		        char c = normalizedString.charAt(i);
		        if ((c >= '\u0030' && c <= '\u0039')
		        		|| (c >= '\u0041' && c <= '\u005A')
		        		|| (c >= '\u0061' && c <= '\u007A')) { 
		        	out[j++] = c;
		        }
		    }
		return new String(out).trim();
		
	}

}
