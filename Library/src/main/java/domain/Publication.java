package domain;

import java.util.Set;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

@javax.persistence.Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn (discriminatorType = DiscriminatorType.STRING)
public abstract class Publication extends Entity {

	private String title;
	@OneToMany(mappedBy = "publication")
	private Set<Loan> loans;
	public Publication() {
		super();
	}

	public Publication(String title) {
		super();
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
