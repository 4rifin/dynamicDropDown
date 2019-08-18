package com.testbaron.rest.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.tomcat.util.codec.binary.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.testbaron.model.Operator;
import com.testbaron.model.Transaction;
import com.testbaron.model.User;
import com.testbaron.model.Voucher;
import com.testbaron.rest.model.MessageInfo;
import com.testbaron.service.OperatorService;
import com.testbaron.service.TransactionService;
import com.testbaron.service.UserService;
import com.testbaron.service.VoucherService;

@Service
@Transactional
public class RestService {

	@Autowired
	UserService userService;

	@Autowired
	OperatorService operatorService;

	@Autowired
	VoucherService voucherService;
	
	@Autowired
	TransactionService transactionService;

	public ResponseEntity<MessageInfo> login(String type, String username, String password, MessageInfo messageInfo) {

		User userLogin = userService.findByUserName(username);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=utf-8");

		String body = "";
		Gson gson = new Gson();
		if (userLogin == null || !userLogin.getPassword().equals(password)) {
			body = "null";
		} else {
			body = gson.toJson(userLogin);
		}

		if (userLogin == null) {
			messageInfo.setCode(HttpStatus.BAD_REQUEST.toString());
			messageInfo.setStatus(HttpStatus.BAD_REQUEST);
			messageInfo.setMessage("UserName Not Valid");
			messageInfo.setResult(body);
			return new ResponseEntity<>(messageInfo, HttpStatus.OK);
		}

		if (!userLogin.getPassword().equals(password)) {
			messageInfo.setCode(HttpStatus.BAD_REQUEST.toString());
			messageInfo.setStatus(HttpStatus.BAD_REQUEST);
			messageInfo.setMessage("Password Not Valid");
			messageInfo.setResult(body);
			return new ResponseEntity<>(messageInfo, HttpStatus.OK);

		}

		if (userLogin != null) {
			messageInfo.setCode(HttpStatus.OK.toString());
			messageInfo.setStatus(HttpStatus.OK);
			messageInfo.setMessage("login sukses");
			messageInfo.setResult(userLogin);
			return new ResponseEntity<>(messageInfo, HttpStatus.OK);
		}

		return null;
	}

	public ResponseEntity<MessageInfo> operator(String type, MessageInfo messageInfo) {

		String status = "0";
		List<Operator> operator = new LinkedList<>();
		if ("operator".equalsIgnoreCase(type)) {
			status = "1";
			operator = operatorService.findAllOperatorActive(status);
		}

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=utf-8");

		String body = "";
		Gson gson = new Gson();
		if (operator == null) {
			body = "null";
		} else {
			body = gson.toJson(operator);
		}

		if (operator.isEmpty()) {
			messageInfo.setCode(HttpStatus.BAD_REQUEST.toString());
			messageInfo.setStatus(HttpStatus.BAD_REQUEST);
			messageInfo.setMessage("failed");
			messageInfo.setResult(body);
			return new ResponseEntity<>(messageInfo, HttpStatus.OK);
		}

		if (operator != null) {
			messageInfo.setCode(HttpStatus.OK.toString());
			messageInfo.setStatus(HttpStatus.OK);
			messageInfo.setMessage("data ditemukan");
			messageInfo.setResult(operator);
			return new ResponseEntity<>(messageInfo, HttpStatus.OK);
		}

		return null;
	}
	
	public ResponseEntity<MessageInfo> voucher(String type, String operator, MessageInfo messageInfo) {

		String status = "0";
		List<Voucher> voucher = new LinkedList<>();
		if ("voucher".equalsIgnoreCase(type)) {
			status = "1";
			voucher = voucherService.findAllVoucherActive(status, operator);
		}

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=utf-8");

		String body = "";
		Gson gson = new Gson();
		if (voucher == null) {
			body = "null";
		} else {
			body = gson.toJson(voucher);
		}

		if (voucher.isEmpty()) {
			messageInfo.setCode(HttpStatus.BAD_REQUEST.toString());
			messageInfo.setStatus(HttpStatus.BAD_REQUEST);
			messageInfo.setMessage("failed");
			messageInfo.setResult(body);
			return new ResponseEntity<>(messageInfo, HttpStatus.OK);
		}

		if (voucher != null) {
			messageInfo.setCode(HttpStatus.OK.toString());
			messageInfo.setStatus(HttpStatus.OK);
			messageInfo.setMessage("data ditemukan");
			messageInfo.setResult(voucher);
			return new ResponseEntity<>(messageInfo, HttpStatus.OK);
		}

		return null;
	}

	public ResponseEntity<MessageInfo> transaction(String type, String userId, String operator, String harga, MessageInfo messageInfo) {
		
		if (harga.equals("")||harga.equals(null)) {
			messageInfo.setCode(HttpStatus.BAD_REQUEST.toString());
			messageInfo.setStatus(HttpStatus.BAD_REQUEST);
			messageInfo.setMessage("failed");
			messageInfo.setResult(null);
			return new ResponseEntity<>(messageInfo, HttpStatus.OK);
		}
		
		if (operator.equals("")||harga.equals(null)) {
			messageInfo.setCode(HttpStatus.BAD_REQUEST.toString());
			messageInfo.setStatus(HttpStatus.BAD_REQUEST);
			messageInfo.setMessage("failed");
			messageInfo.setResult(null);
			return new ResponseEntity<>(messageInfo, HttpStatus.OK);
		}
		
		Transaction transaction = new Transaction();
		transaction.setUserId(userId);
		transaction.setOperator(operator);
		transaction.setHarga(harga);
		
		if (transactionService.isRecordFull()) {
			messageInfo.setCode(HttpStatus.BAD_REQUEST.toString());
			messageInfo.setStatus(HttpStatus.BAD_REQUEST);
			messageInfo.setMessage("Full Transaction");
			messageInfo.setResult("");
			return new ResponseEntity<>(messageInfo, HttpStatus.OK);
		}
		
		Boolean resultTransaction = transactionService.save(transaction);
		
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=utf-8");

		String body = "";
		Gson gson = new Gson();
		if (transaction.equals(false)) {
			body = "null";
		} else {
			body = gson.toJson(transaction);
		}

		if (resultTransaction.equals(false)) {
			messageInfo.setCode(HttpStatus.BAD_REQUEST.toString());
			messageInfo.setStatus(HttpStatus.BAD_REQUEST);
			messageInfo.setMessage("failed");
			messageInfo.setResult(body);
			return new ResponseEntity<>(messageInfo, HttpStatus.OK);
		}

		if (resultTransaction.equals(true)) {
			messageInfo.setCode(HttpStatus.OK.toString());
			messageInfo.setStatus(HttpStatus.OK);
			messageInfo.setMessage("sukses");
			messageInfo.setResult(transaction);
			return new ResponseEntity<>(messageInfo, HttpStatus.OK);
		}

		return null;
	}
	
	public ResponseEntity<MessageInfo> transactionList(String type, MessageInfo messageInfo) {

		List<Transaction> transactionList = new LinkedList<>();
		transactionList = transactionService.findAllTransaction();

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=utf-8");

		String body = "";
		Gson gson = new Gson();
		if (transactionList == null) {
			body = "null";
		} else {
			body = gson.toJson(transactionList);
		}

		if (transactionList.isEmpty()) {
			messageInfo.setCode(HttpStatus.BAD_REQUEST.toString());
			messageInfo.setStatus(HttpStatus.BAD_REQUEST);
			messageInfo.setMessage("failed");
			messageInfo.setResult(body);
			return new ResponseEntity<>(messageInfo, HttpStatus.OK);
		}

		if (transactionList != null) {
			messageInfo.setCode(HttpStatus.OK.toString());
			messageInfo.setStatus(HttpStatus.OK);
			messageInfo.setMessage("data ditemukan");
			messageInfo.setResult(transactionList);
			return new ResponseEntity<>(messageInfo, HttpStatus.OK);
		}

		return null;
	}
	
	public ResponseEntity<MessageInfo> transactionDelete(String type,String id, MessageInfo messageInfo) {

		Transaction transaction = new Transaction();
		Optional<Transaction> transactionOptional = transactionService.findById(Long.parseLong(id));
        if (transactionOptional.isPresent()) {
        	transaction = transactionOptional.get();
        	transactionService.deleteById(transactionOptional.get().getId());
        } else {
            System.out.println("Value not available.");
        }
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=utf-8");

		String body = "";
		Gson gson = new Gson();
		if (transaction == null) {
			body = "null";
		} else {
			body = gson.toJson(transaction);
		}

		if (transaction == null) {
			messageInfo.setCode(HttpStatus.BAD_REQUEST.toString());
			messageInfo.setStatus(HttpStatus.BAD_REQUEST);
			messageInfo.setMessage("failed");
			messageInfo.setResult(body);
			return new ResponseEntity<>(messageInfo, HttpStatus.OK);
		}

		if (transaction != null) {
			messageInfo.setCode(HttpStatus.OK.toString());
			messageInfo.setStatus(HttpStatus.OK);
			messageInfo.setMessage("data didelete");
			messageInfo.setResult(transaction);
			return new ResponseEntity<>(messageInfo, HttpStatus.OK);
		}

		return null;
	}

}
