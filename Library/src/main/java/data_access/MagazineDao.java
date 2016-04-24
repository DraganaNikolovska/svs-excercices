package data_access;

import domain.Magazine;

public interface MagazineDao extends Dao{
	public Magazine fineByIssn(String issn);
}
