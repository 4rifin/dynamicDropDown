package com.testbaron.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testbaron.dao.TransactionDao;
import com.testbaron.model.Transaction;

@Service
@Transactional
public class TransactionService {

	@Autowired
	TransactionDao transactionDao;

	public Boolean save(Transaction transaction) {
		Boolean success = false;
		try {
			transactionDao.save(transaction);
			success = true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return success;
	}

	public void deleteById(long id) {

		transactionDao.deleteById(id);
	}

	public List<Transaction> findAllTransaction() {

		return (List<Transaction>) transactionDao.findAllTransaction();
	}

	public Optional<Transaction> findById(long id) {

		Optional<Transaction> transactionOptional = transactionDao.findById(id);
		return transactionOptional;
	}

	public Boolean isRecordFull() {
		List<Transaction> listTransaction = (List<Transaction>) findAllTransaction();
		if (listTransaction.size() >= 10) {
			return true;
		}
		return false;
	}

}
