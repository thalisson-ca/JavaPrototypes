package http;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Class with commons implements for HTTP Requests
 * @author Thalisson Christiano de Almeida
 *
 */
public class SimpleHttpConnect {
	public static final String ENCODING_UTF8 = "UTF-8";
	
	/**
	 * Send a GET request
	 * @param url URL to be requested
	 * @param defaultResponseEncoding the default response encoding for the request. Must not be null.
	 * @param headers A map for HTTP header parameters
	 * @return a string with the body content
	 * @throws IOException if a problem with connection has occurred
	 * @throws HTTP401Exception if you get a HTTP 401 code
	 * @throws HTTPUnknowException if you didn't get a OK response and it is not the exception above
	 */
	public static String sendGET(String url, String defaultResponseEncoding, Map<String, String> headers) throws IOException, HTTP401Exception, HTTPUnknowException {
		return sendRequest(url, "GET", defaultResponseEncoding, headers);
	}
	
	/**
	 * Send a PUT request
	 * @param url URL to be requested
	 * @param defaultResponseEncoding the default response encoding for the request. Must not be null.
	 * @param headers A map for HTTP header parameters
	 * @return a string with the body content
	 * @throws IOException if a problem with connection has occurred
	 * @throws HTTP401Exception if you get a HTTP 401 code
	 * @throws HTTPUnknowException if you didn't get a OK response and it is not the exception above
	 */
	public static String sendPUT(String url, String defaultResponseEncoding, Map<String, String> headers) throws IOException, HTTP401Exception, HTTPUnknowException {
		return sendRequest(url, "PUT", defaultResponseEncoding, headers);

	}

	/**
	 * Send a HTTP request
	 * @param url URL to be requested
	 * @param method the method request like GET, POST, PUT, etc
	 * @param defaultResponseEncoding the default response encoding for the request. Must not be null.
	 * @param headers A map for HTTP header parameters
	 * @return a string with the body content
	 * @throws IOException if a problem with connection has occurred
	 * @throws HTTP401Exception if you get a HTTP 401 code
	 * @throws HTTPUnknowException if you didn't get a OK response and it is not the exception above
	 */
	public static String sendRequest(String url, String method, String defaultResponseEncoding, Map<String, String> headers) throws IOException, HTTP401Exception, HTTPUnknowException {
		if(defaultResponseEncoding==null) {
			throw new IllegalArgumentException("defaultResponseEncoding must not be null");
		}
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod(method);
		
		
		if(headers!=null) {
			for(Entry<String, String> entry: headers.entrySet()) {
				con.setRequestProperty(entry.getKey(), entry.getValue());
			}
		}
		
		
		int responseCode = con.getResponseCode();
		String encoding = con.getContentEncoding();
		if(encoding==null) {
			encoding = defaultResponseEncoding;
		}
		
		if (responseCode == HttpURLConnection.HTTP_OK) { 
			
			ByteBuffer response = ByteBuffer.allocate(con.getContentLength());
			InputStream is = con.getInputStream();
			int inputByte = -1;
			while ((inputByte = is.read()) != -1) {
				response.put((byte) inputByte);
			}
			
			return new String(response.array(), encoding);
		} else if(responseCode == HttpURLConnection.HTTP_UNAUTHORIZED){
			throw new HTTP401Exception("Acesso não autorizado para "+url, null);
		}else {
			throw new HTTPUnknowException("HTTP Response: "+responseCode,null);
		}
	}
	
	public static class HTTP401Exception extends Exception{

		private static final long serialVersionUID = 2345205184424849791L;

		public HTTP401Exception(String message, Throwable cause) {
			super(message, cause);
		}
	}
	
	public static class HTTPUnknowException extends Exception{

		private static final long serialVersionUID = -2904895002897967785L;

		public HTTPUnknowException(String message, Throwable cause) {
			super(message, cause);
		}
	}
	
		
}
