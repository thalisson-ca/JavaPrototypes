package string;

import java.util.ArrayList;

/**
 * Class with commons String manipulations
 * @author Thalisson Christiano de Almeida
 *
 */
public class SimpleString {
	
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
}
