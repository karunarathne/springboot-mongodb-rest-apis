package com.scl.devnest.service;

import static com.scl.devnest.entity.QUser.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.scl.devnest.dto.SearchUserDto;
import com.scl.devnest.entity.User;
import com.scl.devnest.repository.UserRepository;
import com.scl.devnest.util.CommonUtil;

@Service
public class UserService implements IUserService {
	
	@Autowired
	private UserRepository repository;

	@Override
	public Page<User> getPage(SearchUserDto criteria, Pageable pageable) {
		BooleanExpression predicate = user.id.isNotNull();
		
		if(!CommonUtil.isEmpty(criteria.getName())) {
			predicate.and(user.firstName.startsWith(criteria.getName()).or(user.lastName.startsWith(criteria.getName())));
		}
		
		if(!CommonUtil.isEmpty(criteria.getEmail())) {
			predicate.and(user.email.startsWith(criteria.getEmail()));
		}
		
		if(!CommonUtil.isEmpty(criteria.getStatus())) {
			predicate.and(user.status.startsWith(criteria.getStatus()));
		}
		
		if(!CommonUtil.isEmpty(criteria.getRole())) {
			predicate.and(user.role.startsWith(criteria.getRole()));
		}		
		
		return repository.findAll(predicate, pageable);
	}
	
	public User findById(String id) {
		Optional<User> userOptional = repository.findById(id);
		return userOptional.isPresent()? userOptional.get() : null;
	}
	
	public User add(User user) {
		return repository.save(user);
	}
	
	public User update(User user) {
		return repository.save(user);
	}
	
	public void delete(String id) {
		repository.deleteById(id);
	}

	@Override
	public User findByEmail(String email) {
		Optional<User> userOptional = repository.findByEmail(email);
		return userOptional.isPresent()? userOptional.get() : null;
	}

	@Override
	public User findByUuid(String uuid) {
		Optional<User> userOptional = repository.findByUuid(uuid);
		return userOptional.isPresent()? userOptional.get() : null;
	}

	@Override
	public Boolean hasUserByEmail(String email) {
		return repository.existsByEmail(email);
	}
}
