package com.domain;

import java.util.Date;

public class LoanModel {

	private String memberEmail;
	private Date startDate;
	private Date endDate;
	private String isbn;
	public String getMemberEmail() {
		return memberEmail;
	}
	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	@Override
	public String toString() {
		return "LoanModel [memberEmail=" + memberEmail + ", startDate=" + startDate + ", endDate=" + endDate + ", isbn="
				+ isbn + "]";
	}
	
	
}
