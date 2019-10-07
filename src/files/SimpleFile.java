package files;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

/**
 * Class with common file manipulation
 * @author Thalisson Christiano de Almeida
 *
 */
public class SimpleFile {
	
	/**
	 * Write bytes into a file
	 * @param path the file path 
	 * @param bytes a byte array with the file content 
	 * @return the file with the content written
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static File writeToFileWithByteArray(String path, byte[] bytes) throws FileNotFoundException, IOException {
		File file =  new File(path);
		return  writeToFileWithByteArray(file, bytes);
	}
	
	/**
	 * Write bytes into a file
	 * @param file a file which will be written 
	 * @param bytes a byte array with the file content 
	 * @return the file with the content written
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static File writeToFileWithByteArray(File file, byte[] bytes) throws FileNotFoundException, IOException {
		try(OutputStream os = new FileOutputStream(file)){
			 os.write(bytes);
		}
		return file;
	}
	
	/**
	 * Get the resource content in a byte array
	 * @param resource path of the resource
	 * @return an byte array with the content
	 * @throws IOException
	 */
	public static byte[] getFileBytesFromResource(String resource) throws IOException{
		InputStream stream = SimpleFile.class.getResourceAsStream(resource);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int currentByte = -1;
		while((currentByte = stream.read())!=-1) {
			baos.write(currentByte);
		}
		
	    return baos.toByteArray();
			
	}
	
	
	/**
	 * Get a String from a text file in Java Resource
	 * @param path of the resource
	 * @param the resource encoding
	 * @return a string with the file content
	 * @throws IOException if it has problems with the file reading
	 */
	public static String getTextFileFromResource(String resource, String encoding) throws IOException{
		if(encoding==null) {
			return new String(getFileBytesFromResource(resource));
		}else {
			return new String(getFileBytesFromResource(resource),encoding);
		}
	}
	
	/**
	 * Get a String from a text file in Java Resource
	 * @param resource name of the resource
	 * @return a string with the file content
	 * @throws IOException if it has problems with the file reading
	 */
	public static String getTextFileFromResource(String resource) throws IOException{
		return new String(getFileBytesFromResource(resource));
		
	}
	
	/**
	 * Write a text into a file
	 * @param path  path the file path 
	 * @param fileData a string to be written
	 * @param encoding the file encoding
	 * @return a file reference
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	public static File writeTextFileWithString(String path,String fileData, String encoding) throws FileNotFoundException, UnsupportedEncodingException, IOException {
		if(encoding==null) {
			return writeToFileWithByteArray(path,fileData.getBytes());
		}else {
			return writeToFileWithByteArray(path,fileData.getBytes(encoding));
		}
		
	}
	
	/**
	 * Write a text into a file
	 * @param file  a file object 
	 * @param fileData a string to be written
	 * @param encoding the file encoding
	 * @return a file reference
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	public static File writeTextFileWithString(File file,String fileData, String encoding) throws FileNotFoundException, UnsupportedEncodingException, IOException {
		if(encoding==null) {
			return writeToFileWithByteArray(file,fileData.getBytes());
		}else {
			return writeToFileWithByteArray(file,fileData.getBytes(encoding));
		}
		
	}
		
}
