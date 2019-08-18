package com.testbaron.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.testbaron.model.Transaction;

public interface TransactionDao  extends CrudRepository<Transaction, Long> {
	@Query("select u from Transaction u")
	public List<Transaction> findAllTransaction();
}
