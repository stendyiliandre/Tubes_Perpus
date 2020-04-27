package com.advjava.library.model;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
public class ReturnedIn {
	@Id
	private int borrowed_by_id;
	private Date date;
	private int charge;
	private String charge_details;
	
	@OneToOne(fetch = FetchType.EAGER)
    @MapsId
    private BorrowedBy borrowedBy;

	public int getBorrowed_by_id() {
		return borrowed_by_id;
	}

	public void setBorrowed_by_id(int borrowed_by_id) {
		this.borrowed_by_id = borrowed_by_id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getCharge() {
		return charge;
	}

	public void setCharge(int charge) {
		this.charge = charge;
	}

	public String getCharge_details() {
		return charge_details;
	}

	public void setCharge_details(String charge_details) {
		this.charge_details = charge_details;
	}

	public BorrowedBy getBorrowedBy() {
		return borrowedBy;
	}

	public void setBorrowedBy(BorrowedBy borrowedBy) {
		this.borrowedBy = borrowedBy;
	}
	
}
