package com.qian.entity;

/**
 * 
 * @author Qian
 * @time 2014-8-18����5:45:37
 */
public class DayJobContent {

	/*����ID*/
	private int _id;
	
	/* ������Ϣid����� */
	private int workInfoID;

	/* Date */
	private String date;

	/* ���칤������ */
	private String jobContent;

	/* �����ܻ��� */
	private double totalCost;

	/* ���콻ͨ���� */
	private double transportCost;

	/* ������������ */
	private double otherCost;
	
	
	
	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public double getOtherCost() {
		return otherCost;
	}

	public void setOtherCost(double otherCost) {
		this.otherCost = otherCost;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public double getTransportCost() {
		return transportCost;
	}

	public void setTransportCost(double transportCost) {
		this.transportCost = transportCost;
	}

	public int getWorkInfoID() {
		return workInfoID;
	}

	public void setWorkInfoID(int workInfoID) {
		this.workInfoID = workInfoID;
	}

	public String getJobContent() {
		return jobContent;
	}

	public void setJobContent(String jobContent) {
		this.jobContent = jobContent;
	}

	public DayJobContent() {
		super();
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	// public DayJobContent(int workInfoID, String date, String jobContent) {
	// super();
	// this.workInfoID = workInfoID;
	// this.date = date;
	// this.jobContent = jobContent;
	// }

	@Override
	public String toString() {
		return "��        �ڣ�"+date + "\n" + "�������ݣ�" + jobContent + "\n��֧ͨ����"+transportCost+"\n����֧����"+otherCost+"\n��  ֧  ����"+totalCost;
	}

//	public DayJobContent(int workInfoID, String date, String jobContent,
//			double totalCost, double transportCost, double otherCost) {
//		super();
//		this.workInfoID = workInfoID;
//		this.date = date;
//		this.jobContent = jobContent;
//		this.totalCost = totalCost;
//		this.transportCost = transportCost;
//		this.otherCost = otherCost;
//	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + workInfoID;
		return result;
	}

	public DayJobContent(int _id, int workInfoID, String date,
			String jobContent, double totalCost, double transportCost,
			double otherCost) {
		super();
		this._id = _id;
		this.workInfoID = workInfoID;
		this.date = date;
		this.jobContent = jobContent;
		this.totalCost = totalCost;
		this.transportCost = transportCost;
		this.otherCost = otherCost;
	}
	public DayJobContent(int workInfoID, String date,
			String jobContent, double totalCost, double transportCost,
			double otherCost) {
		super();
		this.workInfoID = workInfoID;
		this.date = date;
		this.jobContent = jobContent;
		this.totalCost = totalCost;
		this.transportCost = transportCost;
		this.otherCost = otherCost;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DayJobContent other = (DayJobContent) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (workInfoID != other.workInfoID)
			return false;
		return true;
	}

	

}
