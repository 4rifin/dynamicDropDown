package com.testbaron.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Voucher")
public class Voucher implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3986055557800361972L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "operator")
	private String operator;

	@Column(name = "pulsa")
	private String pulsa;

	@Column(name = "harga")
	private String harga;

	@Column(name = "status")
	private String status;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getPulsa() {
		return pulsa;
	}

	public void setPulsa(String pulsa) {
		this.pulsa = pulsa;
	}

	public String getHarga() {
		return harga;
	}

	public void setHarga(String harga) {
		this.harga = harga;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Voucher() {
		super();
		// TODO Auto-generated constructor stub
	}

}
