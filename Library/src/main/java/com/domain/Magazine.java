package com.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("magazine")
public class Magazine extends Publication {

	private String issn;

	public Magazine() {
		super();
	}

	public Magazine(String issn) {
		super();
		this.issn = issn;
	}

	public String getIssn() {
		return issn;
	}

	public void setIssn(String issn) {
		this.issn = issn;
	}

	@Override
	public String toString() {
		return "Magazine [issn=" + issn + ", Title=" + getTitle() + "]";
	}

	

}
