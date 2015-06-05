package com.qian.entity;

/**
 * work content entity
 * @author Qian
 * @time   2014-8-18下午5:27:13
 */
public class Work {
	/*id*/
	private int workInfoID;
	/*客户名*/
	private String customerName;
	
	/*出差地点*/
	private String location;
	
	/*总花费*/
	private double cost;
	
	/*出差开始日期*/
	private String startDate;
	
	/*出差结束日期*/
	private String endDate;
	
	/*本次出差的工作内容*/
	private String workContent;

	
	public int getWorkInfoID() {
		return workInfoID;
	}

	public void setWorkInfoID(int workInfoID) {
		this.workInfoID = workInfoID;
	}

	public String getWorkContent() {
		return workContent;
	}

	public void setWorkContent(String workContent) {
		this.workContent = workContent;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
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
		return "客户名:" + customerName + "\n出差地： " + location
				+ "\n出差内容：" + workContent+"\n出差总花费：" + cost + "\n开始日：" + startDate + "\n结束日："
				+ endDate;
	}

	public Work() {
		super();
	}

	public Work(int workInfoID, String customerName, String location,
			double cost, String startDate, String endDate, String workContent) {
		super();
		this.workInfoID = workInfoID;
		this.customerName = customerName;
		this.location = location;
		this.cost = cost;
		this.startDate = startDate;
		this.endDate = endDate;
		this.workContent = workContent;
	}

	public Work(String customerName, String location,
			double cost, String startDate, String endDate, String workContent) {
		super();
		this.customerName = customerName;
		this.location = location;
		this.cost = cost;
		this.startDate = startDate;
		this.endDate = endDate;
		this.workContent = workContent;
	}

	
	
	
}
