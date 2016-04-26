package com.data_access;

import com.domain.Member;

public interface MemberDao extends Dao{

	public Member findByEmail(String email);
}
