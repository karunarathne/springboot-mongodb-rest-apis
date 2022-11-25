package com.scl.devnest.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.scl.devnest.dto.SearchUserDto;
import com.scl.devnest.entity.User;

public interface IUserService {

	public abstract User findById(String id);
	
	public abstract User findByEmail(String email);
	
	public abstract User findByUuid(String uuid);
	
	public abstract Boolean hasUserByEmail(String email);
	
	public abstract Page<User> getPage(SearchUserDto criteria, Pageable pageable);
	
	public abstract User add(User entity);
	
	public abstract User update(User entity);
	
	public abstract void delete(String id);
}
