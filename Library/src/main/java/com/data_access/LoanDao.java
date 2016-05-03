package com.data_access;

import com.domain.Loan;
import com.domain.Publication;

public interface LoanDao extends Dao{

	public Loan findById(Integer id);

	public void unregisterLoan(Publication publication);
}
