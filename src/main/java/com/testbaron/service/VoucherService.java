package com.testbaron.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testbaron.dao.VoucherDao;
import com.testbaron.model.Voucher;

@Service
@Transactional
public class VoucherService {
	
	@Autowired
	VoucherDao voucherDao;
	
	public Voucher findByVoucherId(long id) {

		return voucherDao.findByVoucherId(id);
	}
	
	public Voucher findByName(String userName) {

		return voucherDao.findByName(userName);
	}
	
	public void save(Voucher voucher) {
			voucherDao.save(voucher);
	}

	public List<Voucher> findAllVoucherActive(String status,String operator) {

		return voucherDao.findAllVoucherActive(status,operator);
	}
	
	public Voucher findHargaVoucherActive(String status,String operator,String pulsa) {

		return voucherDao.findHargaVoucherActive(status, operator, Long.parseLong(pulsa));
	}
	
	public void deleteById(long id) {

		voucherDao.deleteById(id);
	}
	
	
	public Boolean isRecordFull(){
		List<Voucher> listVoucher = (List<Voucher>) voucherDao.findAll();
		if(listVoucher.size() >= 10){
			return true;
		}
		return false;
	}
	
}
