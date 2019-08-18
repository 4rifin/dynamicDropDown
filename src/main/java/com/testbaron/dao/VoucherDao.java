package com.testbaron.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.testbaron.model.Voucher;

public interface VoucherDao  extends CrudRepository<Voucher, Long> {
	@Query("select u from Voucher u where u.id = ?1")
	public Voucher findByVoucherId(Long id);
	
	@Query("select u from Voucher u where u.operator = ?1")
	public Voucher findByName(String operator);
	
	@Query("select u from Voucher u where u.status = ?1 and u.operator = ?2")
	public List<Voucher> findAllVoucherActive(String status,String operator);
	
	@Query("select u from Voucher u where u.status = ?1 and u.operator = ?2 and u.id = ?3")
	public Voucher findHargaVoucherActive(String status,String operator,long pulsa);
}
