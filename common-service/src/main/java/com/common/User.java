package com.common;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "users")
public class User {

	@Id
	private Integer id;
	private String name;
	private String email;
	private String password;

	@OneToOne(mappedBy = "user")
	private Profile profile;
}
