package com.advjava.library.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class BookGenre {
	
	@Embeddable
	static class BookGenrePK implements Serializable {
	   private int book_id;
	   private int genre_id;
	   private static final long serialVersionUID = 1L;
	}
	
	@EmbeddedId
	private BookGenrePK bookGenrePK;
	
	@MapsId("book_id")
	@ManyToOne(optional = false)
	@JoinColumn(name = "book_id", referencedColumnName = "id")
	private Book book;

	@MapsId("genre_id")
	@ManyToOne(optional = false)
	@JoinColumn(name = "genre_id", referencedColumnName = "id")
	private Genre genre;

	public BookGenrePK getBookGenrePK() {
		return bookGenrePK;
	}

	public void setBookGenrePK(BookGenrePK bookGenrePK) {
		this.bookGenrePK = bookGenrePK;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}
	
	

}
