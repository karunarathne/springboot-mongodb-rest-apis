package com.scl.devnest.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@Document(collection = "users")
public class User {

	@Id
	private String id;
	
	@Field(name = "uuid")
	private String uuid;
	
	@Field(name = "email")
	private String email;
	
	@Field(name = "password")
	private String password;
	
	@Field(name = "token")
	private String token;
	
	@Field(name = "first_name")
	private String firstName;
	
	@Field(name = "last_name")
	private String lastName;
	
	@Field(name = "status")
	private String status;
	
	@Field(name = "role")
	private String role;
	
	@Field(name = "createdts")
    @CreatedDate
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime createdTs;

    @Field(name = "updated_ts")
    @LastModifiedDate
    private LocalDateTime updatedTs;
}
