package com.testbaron.rest.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.testbaron.rest.model.MessageResponse;
import com.testbaron.rest.model.OperatorDTO;
import com.testbaron.rest.model.VoucherDTO;

@Service
@Transactional
public class RestClientService {
	
	@Value("${REST.SERVICE.URI}")
    public String REST_SERVICE_URI;
	
	@Value("${API.LOGIN}")
    public String API_LOGIN;
	
	@Value("${API.OPERATOR}")
    public String API_OPERATOR;
	
	@Value("${API.ADD.OPERATOR}")
    public String API_ADD_OPERATOR;
	
	@Value("${API.VOUCHER}")
    public String API_VOUCHER;
	
	@Value("${API.VOUCHER}")
    public String API_ADD_VOUCHER;
	
	@Value("${API.TRANSACTION}")
    public String API_TRANSACTION;
	
	@Value("${API.TRANSACTION.LIST}")
    public String API_TRANSACTION_LIST;
	
	@Value("${API.TRANSACTION.DELETE}")
    public String API_TRANSACTION_DELETE;
	
	
	public MessageResponse login(String type,String username,String password){
		System.out.println("\nTesting Login API----------");
		
		RestTemplate restTemplate = new RestTemplate(); 
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		headers.add("Authorization", null);
		HttpEntity<Object> request = new HttpEntity<Object>(headers);
		ResponseEntity<MessageResponse> responseEntity = restTemplate.exchange(REST_SERVICE_URI+API_LOGIN+"?type="+type+"&username="+username+"&password="+password, HttpMethod.GET, request,MessageResponse.class);
		MessageResponse messageResponse = responseEntity.getBody();
		return messageResponse;
	}
	
	public MessageResponse operator(String type){
		System.out.println("\nTesting Operator API----------");
		
		RestTemplate restTemplate = new RestTemplate(); 
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		headers.add("Authorization", null);
		HttpEntity<Object> request = new HttpEntity<Object>(headers);
		ResponseEntity<MessageResponse> responseEntity = restTemplate.exchange(REST_SERVICE_URI+API_OPERATOR+"?type="+type, HttpMethod.GET, request,MessageResponse.class);
		MessageResponse messageResponse = responseEntity.getBody();
		return messageResponse;
	}
	
	public MessageResponse voucher(String type,String operator){
		System.out.println("\nTesting Voucher API----------");
		
		RestTemplate restTemplate = new RestTemplate(); 
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		headers.add("Authorization", null);
		HttpEntity<Object> request = new HttpEntity<Object>(headers);
		ResponseEntity<MessageResponse> responseEntity = restTemplate.exchange(REST_SERVICE_URI+API_VOUCHER+"?type="+type+"&operator="+operator, HttpMethod.GET, request,MessageResponse.class);
		MessageResponse messageResponse = responseEntity.getBody();
		return messageResponse;
	}
	
	public MessageResponse transaction(String type,String userid,String operator,String harga){
		System.out.println("\nTesting Transaction API----------");
		
		RestTemplate restTemplate = new RestTemplate(); 
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		headers.add("Authorization", null);
		HttpEntity<Object> request = new HttpEntity<Object>(headers);
		ResponseEntity<MessageResponse> responseEntity = restTemplate.exchange(REST_SERVICE_URI+API_TRANSACTION+"?type="+type+"&userid="+userid+"&operator="+operator+"&harga="+harga, HttpMethod.GET, request,MessageResponse.class);
		MessageResponse messageResponse = responseEntity.getBody();
		return messageResponse;
	}
	
	public MessageResponse transactionList(String type){
		System.out.println("\nTesting Transaction List API----------");
		
		RestTemplate restTemplate = new RestTemplate(); 
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		headers.add("Authorization", null);
		HttpEntity<Object> request = new HttpEntity<Object>(headers);
		ResponseEntity<MessageResponse> responseEntity = restTemplate.exchange(REST_SERVICE_URI+API_TRANSACTION_LIST+"?type="+type, HttpMethod.GET, request,MessageResponse.class);
		MessageResponse messageResponse = responseEntity.getBody();
		return messageResponse;
	}

	public MessageResponse transactionDelete(String type,String id){
		System.out.println("\nTesting Delete API----------");
		
		RestTemplate restTemplate = new RestTemplate(); 
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		headers.add("Authorization", null);
		HttpEntity<Object> request = new HttpEntity<Object>(headers);
		ResponseEntity<MessageResponse> responseEntity = restTemplate.exchange(REST_SERVICE_URI+API_TRANSACTION_DELETE+"?type="+type+"&id="+id, HttpMethod.GET, request,MessageResponse.class);
		MessageResponse messageResponse = responseEntity.getBody();
		return messageResponse;
	}
	
	public MessageResponse addOperator(OperatorDTO operatorDTO){
		System.out.println("\nTesting create Operator API----------");
		
		RestTemplate restTemplate = new RestTemplate(); 
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		headers.add("Authorization", null);
		HttpEntity<Object> request = new HttpEntity<Object>(operatorDTO, headers);
		ResponseEntity<MessageResponse> responseEntity = restTemplate.exchange(REST_SERVICE_URI+API_ADD_OPERATOR, HttpMethod.POST, request,MessageResponse.class);
		MessageResponse messageResponse = responseEntity.getBody();
		return messageResponse;
	}
	
	public MessageResponse addVoucher(VoucherDTO voucherDTO){
		System.out.println("\nTesting create Voucher API----------");
		
		RestTemplate restTemplate = new RestTemplate(); 
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		headers.add("Authorization", null);
		HttpEntity<Object> request = new HttpEntity<Object>(voucherDTO, headers);
		ResponseEntity<MessageResponse> responseEntity = restTemplate.exchange(REST_SERVICE_URI+API_ADD_OPERATOR, HttpMethod.POST, request,MessageResponse.class);
		MessageResponse messageResponse = responseEntity.getBody();
		return messageResponse;
	}
	
}
