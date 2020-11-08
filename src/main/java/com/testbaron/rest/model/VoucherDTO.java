package com.testbaron.rest.model;

public class VoucherDTO{

	private long id;

	private String operator;

	private String pulsa;

	private String harga;

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

	public VoucherDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

}
