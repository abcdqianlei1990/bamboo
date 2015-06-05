package com.qian.entity;

/**
 * transportation information entity
 * show by a dailog
 * @author Qian
 * @time   2014-8-19上午9:18:38
 */
public class TransportationInfo {

	/*主键*/
	private int _id;
	
	/*每日明细ID*/
	private int jobID;
	
	/*Date*/
	private String date;
	
	/*时间*/
	private String time;
	
	/*每天交通使用情况*/
	private String transpotationType; 
	
	/*每天交通花费*/
	private double transpotationCost;

	
	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTranspotationType() {
		return transpotationType;
	}

	public void setTranspotationType(String transpotationType) {
		this.transpotationType = transpotationType;
	}

	public double getTranspotationCost() {
		return transpotationCost;
	}

	public void setTranspotationCost(double transpotationCost) {
		this.transpotationCost = transpotationCost;
	}
	public int getDayJobID() {
		return jobID;
	}

	public void setDayJobID(int dayJobID) {
		this.jobID = dayJobID;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public TransportationInfo() {
		super();
	}

	public TransportationInfo(String date, String time, String transpotationType,
			double transpotationCost) {
		super();
		this.date = date;
		this.time = time;
		this.transpotationType = transpotationType;
		this.transpotationCost = transpotationCost;
	}
	
	

	

	public TransportationInfo(int _id, int dayJobID, String date, String time,
			String transpotationType, double transpotationCost) {
		super();
		this._id = _id;
		this.jobID = dayJobID;
		this.date = date;
		this.time = time;
		this.transpotationType = transpotationType;
		this.transpotationCost = transpotationCost;
	}

	@Override
	public String toString() {
		return time
				+ "##" + transpotationType
				+ "##" + transpotationCost;
	}
	


}
