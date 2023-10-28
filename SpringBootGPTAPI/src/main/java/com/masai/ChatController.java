package com.masai;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.masai.model.Request;
import com.masai.model.Response;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/chat")
public class ChatController {

	@Value("${openai.model}")
    private String model;

    @Value(("${openai.api.url}"))
    private String apiURL;

	
    @Autowired
    private RestTemplate restTemplate;
    
	@GetMapping("/hello")
	public String dummyAPI() {
		return "Welcome to Spring Boot";
	}
	

	
	@GetMapping("/info")
    public List<Response.Choice> chat(@RequestParam("prompt") String prompt) {
    
		System.out.println(prompt);
		 Request request=new Request(model, prompt);
	        Response response = restTemplate.postForObject(apiURL, request, Response.class);
	        return response.getChoices();
    }

	
	
//    @PostMapping("/chat")
//    public ResponseEntity<String> chat(@RequestBody RequestBean rb) {
//    
// 
//    	 String apiUrl = "https://api.openai.com/v1/chat/completions";
//         
//         // Create request headers
//         HttpHeaders headers = new HttpHeaders();
//         headers.setContentType(MediaType.APPLICATION_JSON);
//         headers.setBearerAuth(apiKey); // Replace with your actual ChatGPT API key
//
//    
//
//         // Create the HTTP entity with headers and payload
//         HttpEntity<RequestBean> entity = new HttpEntity<>(rb, headers);
//
//         // Make the API request
////         RestTemplate restTemplate = new RestTemplate();
//         ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.POST, entity, String.class);
//
//         // Extract the API response body
//         String responseBody = response.getBody();
//
//         return new ResponseEntity<>(responseBody,HttpStatus.OK);
//    }


}

