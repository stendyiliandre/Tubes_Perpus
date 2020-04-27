package com.advjava.library.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity // This tells Hibernate to make a table out of this class
public class Book {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String name;
	private int price;
	private String author;
	private int age_restriction;
	private int published_year;
	private String status;
	
	@OneToMany(targetEntity = BorrowedBy.class, mappedBy = "book", fetch = FetchType.LAZY)
	@JsonIgnore
    private Set<BorrowedBy> borrowedBys;
	
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
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getAge_restriction() {
		return age_restriction;
	}
	public void setAge_restriction(int age_restriction) {
		this.age_restriction = age_restriction;
	}
	public int getPublished_year() {
		return published_year;
	}
	public void setPublished_year(int published_year) {
		this.published_year = published_year;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Set<BorrowedBy> getBorrowedBys() {
		return borrowedBys;
	}
//	public void setBorrowedBys(Set<BorrowedBy> borrowedBys) {
//		this.borrowedBys = borrowedBys;
//	}
	
	public void setBorrowedBys(Set<BorrowedBy> borrowedBys) {
		this.borrowedBys = borrowedBys;
        for (BorrowedBy borrowedBy : borrowedBys) {
        	borrowedBy.setBook(this);
        }
	}
}