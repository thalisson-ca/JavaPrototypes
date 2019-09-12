package http;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.Map;
import java.util.Map.Entry;

public class SimpleHttpConnect {
	public static final String ENCODING_UTF8 = "UTF-8";
	
	public static String sendGET(String url, String defaultResponseEncoding, Map<String, String> headers) throws IOException, HTTP401Exception, HTTPUnknowException {
		return sendRequest(url, "GET", defaultResponseEncoding, headers);
	}
	
	public static String sendPUT(String url, String defaultResponseEncoding, Map<String, String> headers) throws IOException, HTTP401Exception, HTTPUnknowException {
		return sendRequest(url, "PUT", defaultResponseEncoding, headers);

	}

	
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
