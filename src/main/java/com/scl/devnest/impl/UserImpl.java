package com.scl.devnest.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.scl.devnest.dto.SearchUserDto;
import com.scl.devnest.dto.UserDto;
import com.scl.devnest.entity.User;
import com.scl.devnest.enums.ErrorCode;
import com.scl.devnest.exceptions.DevnestException;
import com.scl.devnest.service.IUserService;
import com.scl.devnest.util.CommonUtil;

@Service
public class UserImpl implements IUserImpl {
	
	@Autowired
	private IUserService userService;

	@Override
	public UserDto findById(String id) {
		return convertToDto(new UserDto(), userService.findById(id), true);
	}

	@Override
	public UserDto findByEmail(String email) {
		return convertToDto(new UserDto(), userService.findByEmail(email), true);
	}

	@Override
	public Boolean hasUserByEmail(String email) {
		return userService.hasUserByEmail(email);
	}

	@Override
	public Page<UserDto> getPage(SearchUserDto dto, Pageable pageable) {
		return convertToDto(userService.getPage(dto, pageable));
	}

	@Override
	public UserDto add(UserDto dto) {
		if(userService.hasUserByEmail(dto.getEmail())) {
			throw new DevnestException(ErrorCode.EMAIL_IS_ALREADY_IN_USE);
		}
		
		User entity = convertToEntity(new User(), dto);
		
		entity.setUuid(UUID.randomUUID().toString());
		entity = userService.add(entity);
		return convertToDto(new UserDto(), entity, true);
	}

	@Override
	public UserDto update(UserDto dto) {		
		User entity = userService.findByEmail(dto.getEmail());
		
		if(entity != null && !entity.getId().equals(dto.getId())) {
			throw new DevnestException(ErrorCode.EMAIL_IS_ALREADY_IN_USE);
		}
		
		entity = userService.findById(dto.getId());
		
		if(entity == null) {
			throw new DevnestException(ErrorCode.USER_DOES_NOT_EXIST);
		}
		
		entity = convertToEntity(entity, dto);
		entity = userService.update(entity);
		
		return convertToDto(new UserDto(), entity, true);
	}

	@Override
	public void delete(String id) {
		userService.delete(id);
	}
	
	private Page<UserDto> convertToDto(Page<User> page) {
		return new PageImpl<>(convertToDto(page.getContent()), 
				page.getPageable(), page.getTotalElements());
	}
	
	private List<UserDto> convertToDto(List<User> entities) {
		List<UserDto> dtos = new ArrayList<>();
		
		if(entities != null && !entities.isEmpty()) {
			entities.stream().forEach(entity -> {
				dtos.add(convertToDto(new UserDto(), entity, false));
			});
		}
		
		return dtos;
	}
	
	private User convertToEntity(User entity, UserDto dto) {
		if(dto == null) {
			return null;
		}
		
		entity.setId(dto.getId());
		entity.setFirstName(dto.getFirstName());
		entity.setLastName(dto.getLastName());
		entity.setEmail(dto.getEmail());
		entity.setRole(dto.getRole());
		entity.setStatus(dto.getStatus());
		
		if(!CommonUtil.isEmpty(dto.getUuid())) {
			entity.setUuid(dto.getUuid());
		}
		
		if(!CommonUtil.isEmpty(dto.getPassword())) {
			entity.setPassword(dto.getPassword());
		}
		
		return entity;
	}
	
	private UserDto convertToDto(UserDto dto, User entity, boolean fetchAssociations) {
		if(entity == null) {
			return null;
		}
		
		dto.setId(entity.getId());
		dto.setFirstName(entity.getFirstName());
		dto.setLastName(entity.getLastName());
		dto.setEmail(entity.getEmail());
		dto.setRole(entity.getRole());
		dto.setStatus(entity.getStatus());
		dto.setUuid(entity.getUuid());
		
		return dto;
	}
}
