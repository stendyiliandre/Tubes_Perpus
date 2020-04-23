package com.advjava.library.repository;

import org.springframework.data.repository.CrudRepository;

import com.advjava.library.model.Book;

public interface BookRepository extends CrudRepository<Book, Integer> {

}