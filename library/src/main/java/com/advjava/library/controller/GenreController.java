package com.advjava.library.controller;

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

import com.advjava.library.model.Genre;
import com.advjava.library.repository.GenreRepository;

@Controller
@RequestMapping(path="/library")
public class GenreController {
	@Autowired
	private GenreRepository genreRepository;

	@PostMapping(path="/Genre/add")
	public @ResponseBody ResponseEntity<Genre> addNewGenre (@RequestParam String name) {
		try {
			Genre genreData = new Genre();
			genreData.setName(name);
			genreRepository.save(genreData);
			return ResponseEntity.ok(genreData); 
		}catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping(path="/Genre")
	public @ResponseBody ResponseEntity<Iterable<Genre>> getAllGenres() {
		try {
			return ResponseEntity.ok(genreRepository.findAll()); 
		}catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping(path="/Genre/{id}")
	public @ResponseBody ResponseEntity<Genre> getGenre(@PathVariable int id) {
		try {
			Genre genreData = genreRepository.findById(id).get();
			return ResponseEntity.ok(genreData); 
		}catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
  
	@PutMapping(path="/Genre/{id}")
	public @ResponseBody ResponseEntity<Genre> updateGenre(@RequestParam String name, @PathVariable int id) {
		try {
			Genre genreData = new Genre();
			genreData.setId(id);
			genreData.setName(name);
			genreRepository.save(genreData);
			return ResponseEntity.ok(genreData);
		}catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
  
	@DeleteMapping(path="/Genre/{id}")
	public @ResponseBody ResponseEntity<Void> deleteGenre(@PathVariable int id){
		try {
			genreRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
}