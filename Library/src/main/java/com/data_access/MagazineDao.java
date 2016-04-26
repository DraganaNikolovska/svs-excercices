package com.data_access;

import com.domain.Magazine;

public interface MagazineDao extends Dao{
	public Magazine fineByIssn(String issn);
}
