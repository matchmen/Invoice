package com.model;

public class Authority {
	
	private Integer id;
	
	private Integer comId;
	
	private Integer conId;
	
	private Integer empId;
	
	private Boolean abled;
	
	private Integer invId;

	public Integer getInvId() {
		return invId;
	}

	public void setInvId(Integer invId) {
		this.invId = invId;
	}

	public Boolean getAbled() {
		return abled;
	}

	public void setAbled(Boolean abled) {
		this.abled = abled;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getComId() {
		return comId;
	}

	public void setComId(Integer comId) {
		this.comId = comId;
	}

	public Integer getConId() {
		return conId;
	}

	public void setConId(Integer conId) {
		this.conId = conId;
	}

	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}
}
