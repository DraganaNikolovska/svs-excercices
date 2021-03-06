package com.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Loan extends com.domain.Entity {

	@ManyToOne
	private Member member;
	@ManyToOne
	private Publication publication;
	private Date startDate;
	private Date endDate;

	public Loan() {

	}

	public Member getMember() {
		return member;
	}

	public Publication getPublication() {
		return publication;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public void setPublication(Publication publication) {
		this.publication = publication;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Override
	public String toString() {
		return "Loan [member=" + member + ", publication=" + publication + ", startDate=" + startDate + ", endDate="
				+ endDate + "]";
	}

	

}
