package com.testbaron.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.testbaron.model.Operator;

public interface OperatorDao  extends CrudRepository<Operator, Long> {
	@Query("select u from Operator u where u.id = ?1")
	public Operator findByOperatorId(Long id);
	
	@Query("select u from Operator u where u.name = ?1")
	public Operator findByName(String username);
	
	@Query("select u from Operator u where u.status = ?1")
	public List<Operator> findAllOperatorActive(String status);
}
