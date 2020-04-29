package com.advjava.library.controller;

import java.sql.Date;

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
import com.advjava.library.model.ReturnedIn;
import com.advjava.library.repository.BookRepository;
import com.advjava.library.repository.BorrowedByRepository;
import com.advjava.library.repository.ReturnedInRepository;

@Controller
@RequestMapping(path="/library")
public class ReturnedInController {
	@Autowired
	private ReturnedInRepository returnedInRepository;
	@Autowired
	private BorrowedByRepository borrowedByRepository;
	@Autowired
	private BookRepository bookRepository;
	
	@PostMapping(path="/ReturnedIn/add")
	public @ResponseBody ResponseEntity<ReturnedIn> addNewReturnedIn (@RequestParam int borrowed_by_id, @RequestParam Date date) {
	    try {
	    	ReturnedIn returnedInData = new ReturnedIn();
	    	BorrowedBy borrowedBy = borrowedByRepository.findById(borrowed_by_id).get();
	    	Book book = bookRepository.findById(borrowedBy.getBook().getId()).get();
	    	book.setStatus("Available");
	    	
	    	long difference = ((date.getTime() - borrowedBy.getReturn_date().getTime())/86400000) + 1;
	    	
	    	returnedInData.setBorrowedBy(borrowedBy);
	    	returnedInData.setDate(date);
	    	if (difference > 0) {
		    	returnedInData.setCharge((int)difference*1000);
		    	returnedInData.setCharge_details("Late");
	    	} else {
	    		returnedInData.setCharge(0);
		    	returnedInData.setCharge_details("On Time");
	    	}
	    	returnedInRepository.save(returnedInData);
	    	bookRepository.save(book);
	        return ResponseEntity.ok(returnedInData); 
		}catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping(path="/ReturnedIn")
	public @ResponseBody ResponseEntity<Iterable<ReturnedIn>> getAllReturnedIns() {
		try {
			return ResponseEntity.ok(returnedInRepository.findAll()); 
		}catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping(path="/ReturnedIn/{borrowed_by_id}")
	public @ResponseBody ResponseEntity<ReturnedIn> getReturnedIns(@PathVariable int borrowed_by_id) {
		try {
			ReturnedIn returnedInData = returnedInRepository.findById(borrowed_by_id).get();
			return ResponseEntity.ok(returnedInData); 
		}catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PutMapping(path="/ReturnedIn/{borrowed_by_id}")
	public @ResponseBody ResponseEntity<ReturnedIn> updateReturnedIn(@RequestParam Date date, @RequestParam int charge, @RequestParam String charge_details, @PathVariable int borrowed_by_id) {
		try {
			ReturnedIn returnedInData = new ReturnedIn();
	    	BorrowedBy borrowedBy = borrowedByRepository.findById(borrowed_by_id).get();
	    	
	    	returnedInData.setBorrowedBy(borrowedBy);
	    	returnedInData.setDate(date);
	    	returnedInData.setCharge(charge);
	    	returnedInData.setCharge_details(charge_details);
	    	returnedInRepository.save(returnedInData);
			return ResponseEntity.ok(returnedInData);
		}catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping(path="/ReturnedIn/{borrowed_by_id}")
	public @ResponseBody ResponseEntity<Void> deleteReturnedIn(@PathVariable int borrowed_by_id){
		try {
			borrowedByRepository.deleteById(borrowed_by_id);
			return ResponseEntity.ok().build();
		}catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
}
