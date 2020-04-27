package com.advjava.library.repository;

import org.springframework.data.repository.CrudRepository;

import com.advjava.library.model.BorrowedBy;

public interface BorrowedByRepository extends CrudRepository<BorrowedBy, Integer>  {

}
