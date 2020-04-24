package com.advjava.library.repository;

import org.springframework.data.repository.CrudRepository;

import com.advjava.library.model.Member;

public interface MemberRepository extends CrudRepository<Member, Integer> {

}