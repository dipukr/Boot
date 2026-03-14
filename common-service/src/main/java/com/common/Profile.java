package com.common;

import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Profile {

	@Id
	private Integer id;
	private int age;
	
	@Convert(converter = GenderConverter.class)
	private Gender gender;

	@OneToOne
	@JoinColumn
	private User user;
}
