package main;

import java.util.Arrays;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RestApiTest {

	private static Logger logger = LoggerFactory.getLogger(RestApiTest.class);
	
	private static HttpHeaders getHeaders(){
    	String plainCredentials="user:user";
    	String base64Credentials = new String(Base64.encodeBase64(plainCredentials.getBytes()));
    	HttpHeaders headers = new HttpHeaders();
    	headers.add("Authorization", "Basic " + base64Credentials);
    	headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    	return headers;
    }

	public static void getEquipments() {
		// logger.debug("send data to cloud");
		String url = "http://localhost:8080/ii/equipment/";
		// String url = "http://139.224.56.216:8080/ii/equipment/";
		// String url = "http://www.jmtis.com/ii/equipment/";
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<String> request = new HttpEntity<String>(getHeaders());
        
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
		logger.info("returned equipmentId:{}", response);
		
		// List<LinkedHashMap<String, Object>> usersMap = (List<LinkedHashMap<String, Object>>)response.getBody();
        
        
	}
	
	public static void main(String[] args) {
		getEquipments();
	}
}
