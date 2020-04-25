package com.advjava.library.controller;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.advjava.library.model.Book;
import com.advjava.library.model.BookGenre;
import com.advjava.library.model.Genre;
import com.advjava.library.repository.BookRepository;
import com.advjava.library.repository.BookGenreRepository;
import com.advjava.library.repository.GenreRepository;

@Controller
@RequestMapping(path="/library")
public class BookGenreController {
	@Autowired
	private BookGenreRepository bookGenreRepository;
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private GenreRepository genreRepository;
	
	@PostMapping(path="/BookGenre/add")
	public @ResponseBody ResponseEntity<BookGenre> addNewBookGenre (@RequestParam int book_id, @RequestParam int genre_id) {
	    try {
	    	BookGenre bookGenreData = new BookGenre();
//	    	Book book = bookRepository.findById(book_id).get();
//	    	Genre genre = genreRepository.findById(genre_id).get();
	    	
//	    	bookGenreData.setBook_id(book_id);
//	    	bookGenreData.setGenre_id(genre_id);
//	    	bookGenreData.setBook(book);
//	    	bookGenreData.setGenre(genre);
	    	bookGenreRepository.save(bookGenreData);
	        return ResponseEntity.ok(bookGenreData); 
		}catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
}
