package com.advjava.library.model;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.IdClass;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.ManyToOne;

@Entity
@IdClass(BookGenre.class)
public class BookGenre implements Serializable {
//	@Id
//	@GeneratedValue(strategy=GenerationType.AUTO)
//	private int genre_id;
//	@Id
//	@GeneratedValue(strategy=GenerationType.AUTO)
//	private int book_id;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn(name = "book_id", referencedColumnName = "id")
    private Book book;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn(name = "genre_id", referencedColumnName = "id")
    private Genre genre;

//	public int getGenre_id() {
//		return genre_id;
//	}
//
//	public void setGenre_id(int genre_id) {
//		this.genre_id = genre_id;
//	}

//	public int getBook_id() {
//		return book_id;
//	}
//
//	public void setBook_id(int book_id) {
//		this.book_id = book_id;
//	}
//
//	public Book getBook() {
//		return book;
//	}
//
//	public void setBook(Book book) {
//		this.book = book;
//	}
//
//	public Genre getGenre() {
//		return genre;
//	}
//
//	public void setGenre(Genre genre) {
//		this.genre = genre;
//	}
}
