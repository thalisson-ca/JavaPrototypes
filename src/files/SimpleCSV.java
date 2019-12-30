package files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import string.SimpleString;

/**
 * Class to simplify the manipulation for CSV files
 * @author Thalisson Christiano de Almeida
 *
 */
public class SimpleCSV {
	ArrayList<String[]> lines;
	
	public SimpleCSV() {
		lines = null;
		
	}
	
	/**
	 * Get the CSV content
	 * @return a list of String array. Each entry of the list is a row from the file and each entry inside the array are the columns
	 */
	public List<String[]> get(){
		return lines;
	}
	
	/**
	 * Method to read the CSV File
	 * @param csvFile a file object to the CSV File
	 * @param separator the separator character used in the CSV file
	 * @param ignoreFirstLine if you must ignore the first line of the file (headers per example)
	 * @return a list of String array. Each entry of the list is a row from the file and each entry inside the array are the columns
	 * @throws FileNotFoundException if the file was not found
	 * @throws IOException if there is an IO error.
	 */
	public List<String[]> readFile(File csvFile, char separator, boolean ignoreFirstLine) throws FileNotFoundException, IOException{
		lines = new ArrayList<String[]>();
		try(BufferedReader reader = new BufferedReader(new FileReader(csvFile))){
			String line = "";
			if(ignoreFirstLine) {
				reader.readLine();
			}
			
			while ((line = reader.readLine()) != null) {
				String[] newLine = SimpleString.splitUnlessQuote(line,separator);
				lines.add(newLine);
			}
		}
		return lines;
		
	}
	
}
