package com.testbaron.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testbaron.dao.OperatorDao;
import com.testbaron.model.Operator;
import com.testbaron.rest.model.OperatorDTO;

@Service
@Transactional
public class OperatorService {
	
	@Autowired
	OperatorDao operatorDao;
	
	public Operator findByOperatorId(long id) {

		return operatorDao.findByOperatorId(id);
	}
	
	public Operator findByName(String userName) {

		return operatorDao.findByName(userName);
	}
	
	public void save(Operator operator) {
			operatorDao.save(operator);
	}

	public List<Operator> findAllOperatorActive(String status) {

		return operatorDao.findAllOperatorActive(status);
	}
	
	public void deleteById(long id) {

		operatorDao.deleteById(id);
	}
	
	public void saveOperator(OperatorDTO operatorDTO) {
		Operator operator = new Operator();
		operator.setName(operatorDTO.getName());
		operator.setStatus(operatorDTO.getStatus());
		save(operator);
	}
	
	
	public Boolean isRecordFull(){
		List<Operator> listOperator = (List<Operator>) operatorDao.findAll();
		if(listOperator.size() >= 10){
			return true;
		}
		return false;
	}
	
}
