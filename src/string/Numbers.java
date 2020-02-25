package string;

public class Numbers {
	
	/**
	 * Remove irrelevant non-numeric characters like %, currency symbols, etc. It will maintain the following non-numeric characters: dot(.), comma(,) and minus(-)
	 * @param value a string object 
	 * @return null if value is null. The "value" string without any character besides numbers, dot, comma and minus   
	 */
	public static String removeIrrelevantNonNumericCharacters(String value) {
		if(value==null) {
			return null;
		}

		return value.replaceAll("[^(\\d|\\.|,|\\-)]", "");
	}
	
	
	
}
