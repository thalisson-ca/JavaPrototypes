package http;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Class with commons implements for HTTP Requests
 * @author Thalisson Christiano de Almeida
 *
 */
public class SimpleHttpConnect {

	/**
	 * Send a GET Request to receive a string
	 * @param url url which request will be made
	 * @param responseCharset the response charset
	 * @param headers headers value to be send
	 * @return a string response
	 * @throws IOException
	 * @throws HTTPException
	 */
	public static String sendGETForString(String url, Charset responseCharset, Map<String, String> headers) throws IOException, HTTPException {
		return sendRequestForString(url, "GET", responseCharset, headers,null);
	}
	
	/**
	 * Send a PUT Request to receive a string
	 * @param url url which request will be made
	 * @param responseCharset the response charset
	 * @param headers headers value to be send
	 * @return a string response
	 * @throws IOException
	 * @throws HTTPException
	 */
	public static String sendPUTForString(String url, Charset responseCharset, Map<String, String> headers) throws IOException, HTTPException {
		return sendRequestForString(url, "PUT", responseCharset, headers,null);

	}
	
	/**
	 * Send a PUT request with JSON format string as the body
	 * @param url url which request will be made
	 * @param headers headers value to be send
	 * @param json a string with JSON Format
	 * @param jsonCharset the string charset
	 * @return an String with the request response
	 * @throws IOException
	 * @throws HTTPException
	 */
	public static String sendPUTWithJSON(String url, Map<String, String> headers, String json, Charset jsonCharset) throws IOException, HTTPException {
		if(headers==null) {
			headers = new HashMap<String,String>();
		}
		headers.put("Content-Type", "application/json; "+jsonCharset);
		return new String(sendRequest(url, "PUT", headers,json.getBytes(jsonCharset)));

	}		

	/**
	 * Send a HTML request to receive a string as response
	 * @param url url which request will be made
	 * @param method request method (GET, POST, PUT, etc)
	 * @param responseCharset the response charset
	 * @param headers headers value to be send
	 * @param body content to be send
	 * @return a string format response
	 * @throws IOException
	 * @throws HTTPException
	 */
	public static String sendRequestForString(String url, String method, Charset responseCharset, Map<String, String> headers, byte[] body) throws IOException, HTTPException {
		if(responseCharset==null) {
			throw new IllegalArgumentException("responseCharset must not be null");
		}
		
		return new String(sendRequest(url,method,headers,body), responseCharset);
		
	}
	
	/**
	 * Send a HTML request to receive a string as response
	 * @param url url which request will be made
	 * @param method request method (GET, POST, PUT, etc)
	 * @param headers headers value to be send
	 * @param body content to be send
	 * @return a string format response
	 * @throws IOException
	 * @throws HTTPException
	 */
	public static String sendRequestForString(String url, String method, Map<String, String> headers, byte[] body) throws IOException, HTTPException {
		return new String(sendRequest(url,method,headers,body));
		
	}
	
	/**
	 * Send a HTML request
	 * @param url url which request will be made
	 * @param method request method (GET, POST, PUT, etc)
	 * @param headers headers value to be send
	 * @param body content to be send
	 * @return a byte array with the response content body
	 * @throws IOException
	 * @throws HTTPException
	 */
	public static byte[] sendRequest(String url, String method, Map<String, String> headers, byte[] body) throws IOException, HTTPException {
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod(method);
		
		if(headers!=null) {
			for(Entry<String, String> entry: headers.entrySet()) {
				con.setRequestProperty(entry.getKey(), entry.getValue());
			}
		}
		
		if(body!=null) {
			con.setDoOutput(true);
			try(OutputStream os = con.getOutputStream()) {
				os.write(body, 0, body.length);
			}
		}
		
		int responseCode = con.getResponseCode();
		if (responseCode == HttpURLConnection.HTTP_OK) { 
			ByteBuffer response = ByteBuffer.allocate(con.getContentLength());
			InputStream is = con.getInputStream();
			int inputByte = -1;
			while ((inputByte = is.read()) != -1) {
				response.put((byte) inputByte);
			}
			
			return response.array();
		} else {
			switch(responseCode) {
				case HttpURLConnection.HTTP_UNAUTHORIZED:
					throw new HTTPException("Acesso não autorizado para "+url, null);
			
				default:
					throw new HTTPException("HTTP Response: "+responseCode+" para "+url,null);

			}
			
		}

	}
	
	/**
	 * Exception class for HTML response which is not ok
	 * @author Thalisson Christano de Almeida
	 *
	 */
	public static class HTTPException extends Exception{

		private static final long serialVersionUID = -2904895002897967785L;

		public HTTPException(String message, Throwable cause) {
			super(message, cause);
		}
	}
		
}
