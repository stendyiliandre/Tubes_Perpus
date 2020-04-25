package com.advjava.library.repository;

import org.springframework.data.repository.CrudRepository;
import com.advjava.library.model.BookGenre;


public interface BookGenreRepository extends CrudRepository<BookGenre, Integer>  {

}
