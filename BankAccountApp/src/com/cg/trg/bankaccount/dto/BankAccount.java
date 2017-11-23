package com.cg.trg.bankaccount.dto;

import java.sql.Date;
import java.time.LocalDate;

public class BankAccount {
	int complaintId;
	String raiser;
	private Date raisedDate;
	public String status;
	String priority;
	public int getComplaintId() {
		return complaintId;
	}
	public void setComplaintId(int complaintId) {
		this.complaintId = complaintId;
	}
	public String getRaiser() {
		return raiser;
	}
	public void setRaiser(String raiser) {
		this.raiser = raiser;
	}
	public Date getRaisedDate() {
		return raisedDate;
	}
	public void setRaisedDate(Date raisedDate) {
		this.raisedDate = raisedDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	@Override
	public String toString() {
		return "BankAccount [complaintId=" + complaintId + ", raiser=" + raiser
				+ ", raisedDate=" + raisedDate + ", status=" + status
				+ ", priority=" + priority + "]";
	}
	

}
