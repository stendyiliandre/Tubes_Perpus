package com.advjava.library.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity // This tells Hibernate to make a table out of this class
public class Genre {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String name;
	
	@OneToMany(
	        mappedBy = "genre",
	        cascade = CascadeType.PERSIST,
	        fetch = FetchType.LAZY
	)
	private Set<BookGenre> bookGenres;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
//	public Set<BookGenre> getBookGenres() {
//		return bookGenres;
//	}
//	public void setBookGenres(Set<BookGenre> bookGenres) {
//		this.bookGenres = bookGenres;
//        for (BookGenre bookGenre : bookGenres) {
//        	bookGenre.setGenre(this);
//        }
//	}
	
}