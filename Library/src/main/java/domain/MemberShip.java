package domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class MemberShip extends domain.Entity{
	
	@OneToOne
	private Member member;
	private Date startDate;
	private Date endDate;
	private String memberShipType;
	public MemberShip() {
		// TODO Auto-generated constructor stub
	}
}
