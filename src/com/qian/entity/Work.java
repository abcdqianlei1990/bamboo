package com.qian.entity;

/**
 * work content entity
 * @author Qian
 * @time   2014-8-18����5:27:13
 */
public class Work {
	/*id*/
	private int workInfoID;
	/*�ͻ���*/
	private String customerName;
	
	/*����ص�*/
	private String location;
	
	/*�ܻ���*/
	private double cost;
	
	/*���ʼ����*/
	private String startDate;
	
	/*�����������*/
	private String endDate;
	
	/*���γ���Ĺ�������*/
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
		return "�ͻ���:" + customerName + "\n����أ� " + location
				+ "\n�������ݣ�" + workContent+"\n�����ܻ��ѣ�" + cost + "\n��ʼ�գ�" + startDate + "\n�����գ�"
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
