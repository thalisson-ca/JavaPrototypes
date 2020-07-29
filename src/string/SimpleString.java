package string;

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

	public static String toCamelCase(final String init) {
		if (init == null)
			return null;

		final StringBuilder ret = new StringBuilder(init.length());

		for (final String word : init.split(" ")) {
			if (!word.isEmpty()) {
				ret.append(Character.toUpperCase(word.charAt(0)));
				ret.append(word.substring(1).toLowerCase());
			}
			if (!(ret.length() == init.length()))
				ret.append(" ");
		}

		return ret.toString();
	}

}
