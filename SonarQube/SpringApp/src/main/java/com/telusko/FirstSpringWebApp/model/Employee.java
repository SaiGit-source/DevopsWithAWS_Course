package com.telusko.FirstSpringWebApp.model;

public class Employee
{
	
	private Integer id;
	
	private String ename;
	
	private String ecity="Bengaluru";
	
	private Double esalary;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getEcity() {
		return ecity;
	}

	public void setEcity(String ecity) {
		this.ecity = ecity;
	}

	public Double getEsalary() {
		return esalary;
	}

	public void setEsalary(Double esalary) {
		this.esalary = esalary;
	}

	public Employee(Integer id, String ename, String ecity, Double esalary) {
		super();
		this.id = id;
		this.ename = ename;
		this.ecity = ecity;
		this.esalary = esalary;
	}

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", ename=" + ename + ", ecity=" + ecity + ", esalary=" + esalary + "]";
	}
	
	

}
