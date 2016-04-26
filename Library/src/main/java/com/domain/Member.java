package com.domain;

import java.util.Set;

import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@javax.persistence.Entity
public class Member extends Entity {

	@OneToOne(mappedBy = "member")
	private MemberShip memberShip;
	@OneToMany(mappedBy = "member")
	private Set<Loan> loans;
	
	private String name;
	private String email;

	public Member() {

	}

	public String getEmail() {
		return email;
	}

	public String getName() {
		return name;
	}

	public MemberShip getMemberShip() {
		return memberShip;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setMemberShip(MemberShip memberShip) {
		this.memberShip = memberShip;
	}

	@Override
	public String toString() {
		return "Member [name=" + name + ", email=" + email + "]";
	}

	
}
