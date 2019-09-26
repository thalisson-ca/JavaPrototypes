package http;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

import http.SimpleHttpConnect.HTTPException;

class SimpleHttpConnectTest {

	@Test
	void testSendGET() {
		try {
			HashMap<String, String> header = new HashMap<String, String>();
			header.put("test", "test123");
			String str = SimpleHttpConnect.sendGETForString("https://jsonplaceholder.typicode.com/todos/1",StandardCharsets.UTF_8, header);
			System.out.println(str);
		} catch (IOException | HTTPException e) {
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
