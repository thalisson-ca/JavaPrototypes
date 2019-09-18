package files;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class SimpleFile {
	
	/**
	 * Get a String from a text file in Java Resource
	 * @param resource name of the resource
	 * @return a string with the file content
	 * @throws IOException if it has problems with the file reading
	 */
	public static String getTextFileFromResource(String resource) throws IOException{
		StringBuilder stringBuilder = new StringBuilder();
		InputStream stream = SimpleFile.class.getResourceAsStream(resource);
		try(BufferedReader reader = new BufferedReader(new InputStreamReader(stream))){
			String line;
			while((line = reader.readLine())!=null) {
				stringBuilder.append(line);
			}
		}
		
		return stringBuilder.toString();
	}
	
		
}
