package com.advjava.library.repository;

import org.springframework.data.repository.CrudRepository;

import com.advjava.library.model.Genre;

public interface GenreRepository extends CrudRepository<Genre, Integer> {

}
