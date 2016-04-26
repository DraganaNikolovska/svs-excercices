package com.seavus.domain;

public class Calculation {

	private int operand1;
	private int operand2;
	private String operator;
	private int result;

	public Calculation(int operand1, int operand2, String operator, int result) {
		super();
		this.operand1 = operand1;
		this.operand2 = operand2;
		this.operator = operator;
		this.result = result;
	}

	@Override
	public String toString() {
		return "Calculation [operand1=" + operand1 + ", operand2=" + operand2 + ", operator=" + operator + ", result="
				+ result + "]";
	}

}
