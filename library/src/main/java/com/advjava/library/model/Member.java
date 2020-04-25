package com.advjava.library.model;

import java.sql.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Member {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String name;
	private Date birth_date;
	private String address;
	private Date membership_date;
	private Date expired_date;
	
	@OneToMany(
        mappedBy = "member",
        cascade = CascadeType.PERSIST,
        fetch = FetchType.LAZY
    )
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
	public Date getBirth_date() {
		return birth_date;
	}
	public void setBirth_date(Date birth_date) {
		this.birth_date = birth_date;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getMembership_date() {
		return membership_date;
	}
	public void setMembership_date(Date membership_date) {
		this.membership_date = membership_date;
	}
	public Date getExpired_date() {
		return expired_date;
	}
	public void setExpired_date(Date expired_date) {
		this.expired_date = expired_date;
	}
	public Set<BorrowedBy> getBorrowedBys() {
		return borrowedBys;
	}
	public void setBorrowedBys(Set<BorrowedBy> borrowedBys) {
		this.borrowedBys = borrowedBys;
        for (BorrowedBy borrowedBy : borrowedBys) {
        	borrowedBy.setMember(this);
        }
	}
	
}
