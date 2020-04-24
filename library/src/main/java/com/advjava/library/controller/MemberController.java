package com.advjava.library.controller;

import java.sql.Date;
import java.time.Year;

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

import com.advjava.library.model.Member;
import com.advjava.library.repository.MemberRepository;

@Controller
@RequestMapping(path="/library")
public class MemberController {
	@Autowired
	private MemberRepository memberRepository;
	
	@PostMapping(path="/Member/add")
	public @ResponseBody ResponseEntity<Member> addNewMember (@RequestParam String name, @RequestParam Date birth_date, @RequestParam String address, @RequestParam Date membership_date, @RequestParam Date expired_date) {
	    try {
	    	Member MemberData = new Member();
	    	MemberData.setName(name);
	    	MemberData.setBirth_date(birth_date);
	    	MemberData.setAddress(address);
	    	MemberData.setMembership_date(membership_date);
	    	MemberData.setExpired_date(expired_date);
	        memberRepository.save(MemberData);
	        return ResponseEntity.ok(MemberData); 
		}catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping(path="/Member")
	public @ResponseBody ResponseEntity<Iterable<Member>> getAllMembers() {
		try {
			return ResponseEntity.ok(memberRepository.findAll()); 
		}catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping(path="/Member/{id}")
	public @ResponseBody ResponseEntity<Member> getMember(@PathVariable int id) {
		try {
			Member MemberData = memberRepository.findById(id).get();
			return ResponseEntity.ok(MemberData); 
		}catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PutMapping(path="/Member/{id}")
	public @ResponseBody ResponseEntity<Member> updateMember(@RequestParam String name, @RequestParam Date birth_date, @RequestParam String address, @RequestParam Date membership_date, @RequestParam Date expired_date, @PathVariable int id) {
		try {
			Member MemberData = new Member();
			MemberData.setId(id);
			MemberData.setName(name);
	    	MemberData.setBirth_date(birth_date);
	    	MemberData.setAddress(address);
	    	MemberData.setMembership_date(membership_date);
	    	MemberData.setExpired_date(expired_date);
	        memberRepository.save(MemberData);
			return ResponseEntity.ok().build();
		}catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping(path="/Member/{id}")
	public @ResponseBody ResponseEntity<Void> deleteMember(@PathVariable int id){
		try {
			memberRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
}
