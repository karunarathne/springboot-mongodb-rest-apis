package com.scl.devnest.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.scl.devnest.entity.User;

public interface UserRepository extends MongoRepository<User, String>, QuerydslPredicateExecutor<User> {

	public abstract Optional<User> findByEmail(String email);
	
	public abstract Optional<User> findByUuid(String uuid);
	
	public abstract Boolean existsByEmail(String email);
}
