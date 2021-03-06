package com.testbaron.rest.ws.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.testbaron.model.Operator;
import com.testbaron.model.Voucher;
import com.testbaron.rest.model.MessageInfo;
import com.testbaron.rest.model.OperatorDTO;
import com.testbaron.rest.model.VoucherDTO;
import com.testbaron.rest.service.RestService;

/**
 * response from rest service client
 */

@Controller
@RestController
public class WebServiceController {
	private static String messageType = "info";
	private static String message = "failed";
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

	@RequestMapping(value = "/ws/add/operator", method = RequestMethod.POST)
	public ResponseEntity<MessageInfo> wsAddOperator(@RequestBody OperatorDTO params,
			@Valid MessageInfo messageInfo) {

		try {
			ResponseEntity<MessageInfo> messageResult = restService.createOperator(params, messageInfo);
			return messageResult;
		} catch (Exception e) {
			throw new UserIsSelfException("message");
		}
	}
	
	@RequestMapping(value = "/ws/add/voucher", method = RequestMethod.POST)
	public ResponseEntity<MessageInfo> wsAddVoucher(@RequestBody VoucherDTO params,
			@Valid MessageInfo messageInfo) {

		try {
			ResponseEntity<MessageInfo> messageResult = restService.createVoucher(params, messageInfo);
			return messageResult;
		} catch (Exception e) {
			throw new UserIsSelfException("message");
		}
	}
	
	@RequestMapping(value = "/add/operator/{namaOperator}/{status}", method = RequestMethod.GET)
	public String addOperator(@PathVariable("namaOperator") String namaOperator, @PathVariable("status") String status,
			Model model, HttpServletRequest httpServletRequest) {
		try {
			Operator operator = new Operator();
			operator.setName(namaOperator);
			operator.setStatus(status);
			restService.getOperatorService().save(operator);
		} catch (Exception e) {
			e.getMessage();
		}
		return "jsp" + "/" + "transaction";
	}
	
	@RequestMapping(value = "/add/voucher/{namaOperator}/{harga}/{pulsa}/{status}", method = RequestMethod.GET)
	public String addVoucher(@PathVariable("namaOperator") String namaOperator, @PathVariable("harga") String harga,
			@PathVariable("pulsa") String pulsa, @PathVariable("status") String status, Model model,
			HttpServletRequest httpServletRequest) {
		try {
			Voucher voucher = new Voucher();
			voucher.setOperator(namaOperator);
			voucher.setHarga(harga);
			voucher.setPulsa(pulsa + "Ribu");
			voucher.setStatus(status);
			restService.getVoucherService().save(voucher);
		} catch (Exception e) {
			e.getMessage();
		}
		return "jsp" + "/" + "transaction";
	}
	
	@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "Can't add yourself") // 403
	public class UserIsSelfException extends RuntimeException {
		private static final long serialVersionUID = -6871307095006922960L;

		public UserIsSelfException(String message) {
			super(message);
		}
	}
}
