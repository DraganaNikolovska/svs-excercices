package domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("book")
public class Book extends Publication {

	@Column(name = "isbn")
	private String isbn;

	public Book() {
		super();
	}

	public Book(String isbn) {
		super();
		this.isbn = isbn;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	@Override
	public String toString() {

		return isbn + "\t" + getTitle();
	}
}
