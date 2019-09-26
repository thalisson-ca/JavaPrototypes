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
	
	public List<String[]> get(){
		return lines;
	}
	
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
