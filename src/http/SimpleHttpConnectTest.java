package http;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

import http.SimpleHttpConnect.HTTP401Exception;
import http.SimpleHttpConnect.HTTPUnknowException;

class SimpleHttpConnectTest {

	@Test
	void testSendGET() {
		try {
			HashMap<String, String> header = new HashMap<String, String>();
			header.put("test", "test123");
			String str = SimpleHttpConnect.sendGET("https://jsonplaceholder.typicode.com/todos/1", SimpleHttpConnect.ENCODING_UTF8, header);
			System.out.println(str);
		} catch (IOException | HTTP401Exception | HTTPUnknowException e) {
			fail(e.getMessage());
		}
	}

	@Test
	void testSendPUT() {
		fail("Not yet implemented");
	}

	@Test
	void testSendRequest() {
		fail("Not yet implemented");
	}

}
