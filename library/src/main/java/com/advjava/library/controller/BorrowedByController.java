package com.advjava.library.controller;

import java.sql.Date;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.advjava.library.model.Book;
import com.advjava.library.model.BorrowedBy;
import com.advjava.library.model.Member;
import com.advjava.library.repository.BookRepository;
import com.advjava.library.repository.BorrowedByRepository;
import com.advjava.library.repository.MemberRepository;

@Controller
@RequestMapping(path="/library")
public class BorrowedByController {
	@Autowired
	private BorrowedByRepository borrowedByRepository;
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private MemberRepository memberRepository;
	
	@PostMapping(path="/BorrowedBy/add")
	public @ResponseBody ResponseEntity<BorrowedBy> addNewBorrowedBy (@RequestParam Date borrow_date, @RequestParam Date return_date, @RequestParam int book_id, @RequestParam int member_id) {
	    try {
	    	BorrowedBy borrowedByData = new BorrowedBy();
	    	Book book = bookRepository.findById(book_id).get();
	    	Member member = memberRepository.findById(member_id).get();
	    	
	    	LocalDateTime today = java.time.LocalDateTime.now();
	    	int age = (today.getYear() - member.getBirth_date().getYear() - 1900);
	    	long difference = ((borrow_date.getTime() - member.getExpired_date().getTime())/86400000) + 1;
	    	
	    	if (book.getStatus().equalsIgnoreCase("Borrowed")) {
	    		borrowedByData.setMessage("Book is borrowed by someone else");
		    	return ResponseEntity.ok(borrowedByData);
	    	}
	    	
	    	if (age < book.getAge_restriction()) {
	    		borrowedByData.setMessage("You're not old enough for this book");
		    	return ResponseEntity.ok(borrowedByData);
	    	}
	    	
	    	if (difference > 0) {
	    		borrowedByData.setMessage("Membership expired");
		    	return ResponseEntity.ok(borrowedByData);
	    	}
	    	
	    	book.setStatus("Borrowed");
	    	
	    	borrowedByData.setBorrow_date(borrow_date);
	    	borrowedByData.setReturn_date(return_date);
	    	borrowedByData.setMessage("Success");
	    	borrowedByData.setBook(book);
	    	borrowedByData.setMember(member);
	    	borrowedByRepository.save(borrowedByData);
	        return ResponseEntity.ok(borrowedByData); 
		}catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping(path="/BorrowedBy")
	public @ResponseBody ResponseEntity<Iterable<BorrowedBy>> getAllBorrowedBy() {
		try {
			return ResponseEntity.ok(borrowedByRepository.findAll()); 
		}catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping(path="/BorrowedBy/{id}")
	public @ResponseBody ResponseEntity<BorrowedBy> getBorrowedBys(@PathVariable int id) {
		try {
			BorrowedBy borrowedByData = borrowedByRepository.findById(id).get();
			return ResponseEntity.ok(borrowedByData); 
		}catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PutMapping(path="/BorrowedBy/{id}")
	public @ResponseBody ResponseEntity<BorrowedBy> updateBorrowedBy(@RequestParam Date borrow_date, @RequestParam Date return_date, @RequestParam int book_id, @RequestParam int member_id, @PathVariable int id) {
		try {
			BorrowedBy borrowedByData = new BorrowedBy();
	    	Book book = bookRepository.findById(book_id).get();
	    	Member member = memberRepository.findById(member_id).get();
	    	
	    	borrowedByData.setId(id);
	    	borrowedByData.setBorrow_date(borrow_date);
	    	borrowedByData.setReturn_date(return_date);
	    	borrowedByData.setBook(book);
	    	borrowedByData.setMember(member);
	    	borrowedByRepository.save(borrowedByData);
			return ResponseEntity.ok(borrowedByData);
		}catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping(path="/BorrowedBy/{id}")
	public @ResponseBody ResponseEntity<Void> deleteBorrowedBy(@PathVariable int id){
		try {
			borrowedByRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
}
