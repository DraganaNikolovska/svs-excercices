package data_access;

import domain.Loan;

public interface LoanDao extends Dao{

	public Loan findById(Integer id);
}
