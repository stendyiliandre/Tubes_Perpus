package com.advjava.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.advjava.library.repository.BorrowedByRepository;
import com.advjava.library.repository.ReturnedInRepository;

@Controller
@RequestMapping(path="/library")
public class ReturnedInController {
	@Autowired
	private ReturnedInRepository returnedInRepository;
	@Autowired
	private BorrowedByRepository borrowedByRepository;
}
