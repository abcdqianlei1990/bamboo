package com.qian.entity;

public class Condition {
	private String customerName;
	private String startDate;
	private String endDate;
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	@Override
	public String toString() {
		return "Condition [customerName=" + customerName + ", startDate="
				+ startDate + ", endDate=" + endDate + "]";
	}
	public Condition() {
		super();
	}
	public Condition(String customerName, String startDate, String endDate) {
		super();
		this.customerName = customerName;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	
}
