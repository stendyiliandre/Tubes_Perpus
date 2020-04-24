package com.advjava.library.controller;

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

import com.advjava.library.model.Book;
import com.advjava.library.repository.BookRepository;

@Controller // This means that this class is a Controller
@RequestMapping(path="/library") // This means URL's start with /demo (after Application path)
public class BookController {
  @Autowired // This means to get the bean called BookRepository
 // Which is auto-generated by Spring, we will use it to handle the data
  private BookRepository bookRepository;

  @PostMapping(path="/Book/add") // Map ONLY POST Requests
  public @ResponseBody ResponseEntity<Book> addNewBook (@RequestParam String name, @RequestParam double price, @RequestParam String author, @RequestParam int age_restriction, @RequestParam Year published_year, @RequestParam String status) {
    // @ResponseBody means the returned String is the response, not a view name
 //@RequestParam means it is a parameter from the GET or POST request

    try {
    	Book BookData = new Book();
    	BookData.setName(name);
    	BookData.setPrice(price);
    	BookData.setAuthor(author);
    	BookData.setAge_restriction(age_restriction);
    	BookData.setPublished_year(published_year);
    	BookData.setStatus(status);
        bookRepository.save(BookData);
        return ResponseEntity.ok(BookData); 
	}catch (Exception e) {
		return ResponseEntity.notFound().build();
	}
  }

  @GetMapping(path="/Book")
  public @ResponseBody ResponseEntity<Iterable<Book>> getAllBooks() {
    // This returns a JSON or XML with the Books
	  try {
			return ResponseEntity.ok(bookRepository.findAll()); 
		}catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
  }

  @GetMapping(path="/Book/{id}")
  public @ResponseBody ResponseEntity<Book> getBook(@PathVariable int id) {
	try {
		Book BookData = bookRepository.findById(id).get();
		return ResponseEntity.ok(BookData); 
	}catch (Exception e) {
		return ResponseEntity.notFound().build();
	}
  }
  
  @PutMapping(path="/Book/{id}")
  public @ResponseBody ResponseEntity<Book> updateBook(@RequestParam String name, @RequestParam double price, @RequestParam String author, @RequestParam int age_restriction, @RequestParam Year published_year, @RequestParam String status, @PathVariable int id) {
	  try {
		  Book BookData = new Book();
		  BookData.setId(id);
		  BookData.setName(name);
		  BookData.setPrice(price);
		  BookData.setAuthor(author);
		  BookData.setAge_restriction(age_restriction);
		  BookData.setPublished_year(published_year);
		  BookData.setStatus(status);
		  bookRepository.save(BookData);
		  return ResponseEntity.ok().build();
		}catch (Exception e) {
		  return ResponseEntity.notFound().build();
		}
  }
  
  @DeleteMapping(path="/Book/{id}")
  public @ResponseBody ResponseEntity<Void> deleteBook(@PathVariable int id){
	try {
	  bookRepository.deleteById(id);
	  return ResponseEntity.ok().build();
	}catch (Exception e) {
	  return ResponseEntity.notFound().build();
	}
	  
  }
}