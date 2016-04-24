package data_access;

import domain.Member;

public interface MemberDao extends Dao{

	public Member findByEmail(String email);
}
