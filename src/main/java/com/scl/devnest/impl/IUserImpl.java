package com.scl.devnest.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.scl.devnest.dto.SearchUserDto;
import com.scl.devnest.dto.UserDto;

public interface IUserImpl {

	public abstract UserDto findById(String id);
	
	public abstract UserDto findByEmail(String email);
	
	public abstract Boolean hasUserByEmail(String email);
	
	public abstract Page<UserDto> getPage(SearchUserDto dto, Pageable pageable);
	
	public abstract UserDto add(UserDto dto);
	
	public abstract UserDto update(UserDto dto);
	
	public abstract void delete(String id);
}
