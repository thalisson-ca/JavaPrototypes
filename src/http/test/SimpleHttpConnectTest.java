package http.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

import http.SimpleHttpConnect;
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
		
		try {
			HashMap<String, String> header = new HashMap<String, String>();
			header.put("test", "test123");
			SimpleHttpConnect.sendGETForString("https://aleatorio.cxsssas.c",StandardCharsets.UTF_8, header);
			fail("Este host era para não existir");
		}catch(UnknownHostException e) {
			System.out.println("Unknown Host Exception");
		}catch(IOException e) {
			fail(e.getMessage());
		}catch(HTTPException e) {
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
