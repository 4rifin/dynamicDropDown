package com.testbaron.rest.ws.controller;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.testbaron.rest.model.MessageInfo;
import com.testbaron.rest.service.RestService;

@Controller
@RestController
public class WebServiceController {

	static Logger log = Logger.getLogger(WebServiceController.class.getName());

	@Autowired
	RestService restService;

	@RequestMapping(value = "/ws/login", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<MessageInfo> wsLogin(@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "username", required = false) String username,
			@RequestParam(value = "password", required = false) String password,
			@Valid MessageInfo messageInfo) {
		try {
			ResponseEntity<MessageInfo> messageResult = restService.login(type,username,password,messageInfo);
			return messageResult;
		} catch (Exception e) {
			throw new UserIsSelfException("message");
		}
	}
	
	@RequestMapping(value = "/ws/operator", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<MessageInfo> wsOperator(@RequestParam(value = "type", required = false) String type,
			@Valid MessageInfo messageInfo) {
		try {
			ResponseEntity<MessageInfo> messageResult = restService.operator(type, messageInfo);
			return messageResult;
		} catch (Exception e) {
			throw new UserIsSelfException("message");
		}
	}
	
	@RequestMapping(value = "/ws/voucher", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<MessageInfo> wsVoucher(@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "operator", required = false) String operator,
			@Valid MessageInfo messageInfo) {
		try {
			ResponseEntity<MessageInfo> messageResult = restService.voucher(type,operator, messageInfo);
			return messageResult;
		} catch (Exception e) {
			throw new UserIsSelfException("message");
		}
	}
	
	@RequestMapping(value = "/ws/transaction", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<MessageInfo> wsTransaction(@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "userid", required = false) String userId,
			@RequestParam(value = "operator", required = false) String operator,
			@RequestParam(value = "harga", required = false) String harga,
			@Valid MessageInfo messageInfo) {
		try {
			ResponseEntity<MessageInfo> messageResult = restService.transaction(type, userId, operator, harga, messageInfo);
			return messageResult;
		} catch (Exception e) {
			throw new UserIsSelfException("message");
		}
	}

	@RequestMapping(value = "/ws/transaction/list", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<MessageInfo> wsTransactionList(@RequestParam(value = "type", required = false) String type,
			@Valid MessageInfo messageInfo) {
		try {
			ResponseEntity<MessageInfo> messageResult = restService.transactionList(type, messageInfo);
			return messageResult;
		} catch (Exception e) {
			throw new UserIsSelfException("message");
		}
	}
	
	@RequestMapping(value = "/ws/transaction/delete", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<MessageInfo> wsTransactionDelete(@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "id", required = false) String id,
			@Valid MessageInfo messageInfo) {
		try {
			ResponseEntity<MessageInfo> messageResult = restService.transactionDelete(type,id,messageInfo);
			return messageResult;
		} catch (Exception e) {
			throw new UserIsSelfException("message");
		}
	}
	
	@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "Can't add yourself") // 403
	public class UserIsSelfException extends RuntimeException {
		private static final long serialVersionUID = -6871307095006922960L;

		public UserIsSelfException(String message) {
			super(message);
		}
	}
}
