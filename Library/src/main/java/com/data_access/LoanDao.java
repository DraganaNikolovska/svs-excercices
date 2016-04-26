package com.data_access;

import com.domain.Loan;

public interface LoanDao extends Dao{

	public Loan findById(Integer id);
}
