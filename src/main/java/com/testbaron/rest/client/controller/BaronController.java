package com.testbaron.rest.client.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.testbaron.model.Operator;
import com.testbaron.model.Transaction;
import com.testbaron.model.Voucher;
import com.testbaron.rest.model.MessageInfo;
import com.testbaron.rest.model.MessageResponse;
import com.testbaron.rest.model.OperatorDTO;
import com.testbaron.rest.model.VoucherDTO;
import com.testbaron.rest.service.RestClientService;
import com.testbaron.service.VoucherService;

@Controller
public class BaronController {
	private static String messageType = "info";
	private static String message = "failed";

	@Autowired
	RestClientService restClientService;

	@Autowired
	VoucherService voucherService;

	public static final String path = "jsp";
	private static final String PARAM_ID = "id";

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String Show(Model model, HttpServletRequest httpServletRequest) {
		return "redirect:" + "/" + "transaction/list";
	}
	
	@RequestMapping(value = "/transaction/new", method = RequestMethod.GET)
	public String ShowTransaction(Model model, HttpServletRequest httpServletRequest) {
		try {
			MessageResponse messageResponseOperator = restClientService.operator("operator");
			List <Operator> listOperator = (List<Operator>) messageResponseOperator.getResult();
			model.addAttribute("listOperator", listOperator);
		} catch (Exception e) {
			e.getMessage();
		}
		return path + "/" + "transaction";
	}
	
	@RequestMapping(value = "/transactionList", method = RequestMethod.GET)
	public String ShowTransactionList(Model model, HttpServletRequest httpServletRequest) {
		
		return path + "/" + "listTransaction";
	}


	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String Login(RedirectAttributes redirectAttrs, HttpServletRequest httpServletRequest, Model model,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "username", required = false) String username,
			@RequestParam(value = "password", required = false) String password) {
		try {
			MessageResponse messageResponse = restClientService.login(type, username, password);
			if (messageResponse.getMessage().contains("login sukses")) {
				messageType = "success";
				message = messageResponse.getMessage() + " Response Api " + messageResponse.getStatus()
						+ messageResponse.getStatus().getReasonPhrase();
			} else {
				messageType = "failed";
				message = messageResponse.getMessage() + " Response Api " + messageResponse.getStatus()
						+ messageResponse.getStatus().getReasonPhrase();
			}
			
			MessageResponse messageResponseOperator = restClientService.operator("operator");
			List <Operator> listOperator = (List<Operator>) messageResponseOperator.getResult();
			model.addAttribute("listOperator", listOperator);
			model.addAttribute("userId", username);
			model.addAttribute("message", message);
			model.addAttribute("messageType", messageType);
		} catch (Exception e) {
			e.getMessage();
		}

		if (messageType.equalsIgnoreCase("success")) {
			return path + "/" + "transaction";
		} else {
			redirectAttrs.addFlashAttribute("message", message);
			redirectAttrs.addFlashAttribute("messageType", messageType);
			return "redirect:" + "/";
		}

	}

	@RequestMapping(value = "/transaction", method = RequestMethod.GET)
	public String operator(RedirectAttributes redirectAttrs, HttpServletRequest httpServletRequest, Model model,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "userid", required = false) String userid,
			@RequestParam(value = "operator", required = false) String operator,
			@RequestParam(value = "harga", required = false) String harga) {
		try {
			MessageResponse messageResponse = restClientService.transaction(type, userid, operator, harga);
			if (messageResponse.getMessage().contains("sukses")) {
				messageType = "success";
				message = messageResponse.getMessage() + " Response Api " + messageResponse.getStatus()
						+ messageResponse.getStatus().getReasonPhrase();
			} else {
				messageType = "failed";
				message = messageResponse.getMessage() + " Response Api " + messageResponse.getStatus()
						+ messageResponse.getStatus().getReasonPhrase();
			}
			model.addAttribute("message", message);
			model.addAttribute("messageType", messageType);
		} catch (Exception e) {
			e.getMessage();
		}

		if (messageType.equalsIgnoreCase("success")) {
			MessageResponse messageResponseOperator = restClientService.operator("operator");
			List <Operator> listOperator = (List<Operator>) messageResponseOperator.getResult();
			model.addAttribute("listOperator", listOperator);
			model.addAttribute("userId", userid);
			redirectAttrs.addFlashAttribute("message", message);
			redirectAttrs.addFlashAttribute("messageType", messageType);
			return "redirect:" + "/" + "transaction/new";
		} else {
			redirectAttrs.addFlashAttribute("message", message);
			redirectAttrs.addFlashAttribute("messageType", messageType);
			return "redirect:" + "/" + "transaction/new";
		}

	}
	
	
	@RequestMapping(value = "/voucher", method = RequestMethod.GET)
	public @ResponseBody List<Voucher> voucher(RedirectAttributes redirectAttrs, HttpServletRequest httpServletRequest, Model model,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "operator", required = false) String operator) {
		MessageResponse messageResponse = new MessageResponse();
		try {
			messageResponse = restClientService.voucher(type, operator);
			
		} catch (Exception e) {
			e.getMessage();
		}
		return messageResponse.getMessage().equalsIgnoreCase("failed") ? null : (List<Voucher>) messageResponse.getResult();

	}
	
	@RequestMapping(value = "/harga", method = RequestMethod.GET)
	public @ResponseBody String harga(RedirectAttributes redirectAttrs, HttpServletRequest httpServletRequest, Model model,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "operator", required = false) String operator,
			@RequestParam(value = "pulsa", required = false) String pulsa) {
		Voucher resultVoucher = new Voucher();
		try {
			resultVoucher = voucherService.findHargaVoucherActive("1", operator, pulsa);
			
		} catch (Exception e) {
			e.getMessage();
		}
		return StringUtils.isEmpty(resultVoucher) ? "-" : resultVoucher.getHarga();

	}
	
	@RequestMapping(value = "/transaction/list", method = RequestMethod.GET)
	public String transactionShow(RedirectAttributes redirectAttrs, HttpServletRequest httpServletRequest, Model model,
			@RequestParam(value = "type", required = false) String type) {
		try {
			MessageResponse messageResponse = restClientService.transactionList(type);
			if (messageResponse.getMessage().contains("data ditemukan")) {
				messageType = "success";
				message = messageResponse.getMessage() + " Response Api " + messageResponse.getStatus()
						+ messageResponse.getStatus().getReasonPhrase();
			} else {
				messageType = "failed";
				message = messageResponse.getMessage() + " Response Api " + messageResponse.getStatus()
						+ messageResponse.getStatus().getReasonPhrase();
			}
			model.addAttribute("message", message);
			model.addAttribute("messageType", messageType);
		} catch (Exception e) {
			e.getMessage();
		}

		if (messageType.equalsIgnoreCase("success")) {
			MessageResponse messageResponseOperator = restClientService.transactionList("transaction");
			List <Transaction> listTransaction = (List<Transaction>) messageResponseOperator.getResult();
			model.addAttribute("listTransaction", listTransaction);
			redirectAttrs.addFlashAttribute("message", message);
			redirectAttrs.addFlashAttribute("messageType", messageType);
			return path + "/" + "listTransaction";
		} else {
			redirectAttrs.addFlashAttribute("message", message);
			redirectAttrs.addFlashAttribute("messageType", messageType);
			return path + "/" + "listTransaction";
		}

	}
	
	@RequestMapping(value = "/transaction/delete", method = RequestMethod.GET)
	public String transactionDelete(RedirectAttributes redirectAttrs, HttpServletRequest httpServletRequest, Model model,
			@RequestParam(value = "type", required = false) String type,@RequestParam(value = "id", required = false) String id) {
		try {
			MessageResponse messageResponse = restClientService.transactionDelete(type, id);
			if (messageResponse.getMessage().contains("data didelete")) {
				messageType = "success";
				message = messageResponse.getMessage() + " Response Api " + messageResponse.getStatus()
						+ messageResponse.getStatus().getReasonPhrase();
			} else {
				messageType = "failed";
				message = messageResponse.getMessage() + " Response Api " + messageResponse.getStatus()
						+ messageResponse.getStatus().getReasonPhrase();
			}
			model.addAttribute("message", message);
			model.addAttribute("messageType", messageType);
		} catch (Exception e) {
			e.getMessage();
		}

		if (messageType.equalsIgnoreCase("success")) {
			MessageResponse messageResponseOperator = restClientService.transactionList("transaction");
			List <Transaction> listTransaction = (List<Transaction>) messageResponseOperator.getResult();
			model.addAttribute("listTransaction", listTransaction);
			redirectAttrs.addAttribute("message", message);
			redirectAttrs.addAttribute("messageType", messageType);
			return path + "/" + "listTransaction";
		} else {
			redirectAttrs.addAttribute("message", message);
			redirectAttrs.addAttribute("messageType", messageType);
			return path + "/" + "listTransaction";
		}

	}
	
	@PostMapping("/add/operator")
	public String addOperator(@Valid MessageInfo messageInfo, @RequestBody OperatorDTO params,RedirectAttributes redirectAttrs) {
		MessageResponse messageResponse = new MessageResponse();
		try {
			messageResponse = restClientService.addOperator(params);
			if(messageResponse.getMessage().contains("Success")){
				messageType = "success";
				message = messageResponse.getMessage() + " Response Api "+ messageResponse.getStatus() + messageResponse.getStatus().getReasonPhrase();
			}else{
				messageType = "failed";
				message = messageResponse.getMessage() + " Response Api "+ messageResponse.getStatus() + messageResponse.getStatus().getReasonPhrase();
			}
			redirectAttrs.addFlashAttribute("message" , message);
			redirectAttrs.addFlashAttribute("messageType" , messageType);
		}catch (Exception e) {
			e.getMessage();
		}
		return message;
	}
	
	@PostMapping("/add/voucher")
	public String addVoucher(@Valid MessageInfo messageInfo, @RequestBody VoucherDTO params,RedirectAttributes redirectAttrs) {
		MessageResponse messageResponse = new MessageResponse();
		try {
			messageResponse = restClientService.addVoucher(params);
			if(messageResponse.getMessage().contains("Success")){
				messageType = "success";
				message = messageResponse.getMessage() + " Response Api "+ messageResponse.getStatus() + messageResponse.getStatus().getReasonPhrase();
			}else{
				messageType = "failed";
				message = messageResponse.getMessage() + " Response Api "+ messageResponse.getStatus() + messageResponse.getStatus().getReasonPhrase();
			}
			redirectAttrs.addFlashAttribute("message" , message);
			redirectAttrs.addFlashAttribute("messageType" , messageType);
		}catch (Exception e) {
			e.getMessage();
		}
		return message;
	}
	
}
