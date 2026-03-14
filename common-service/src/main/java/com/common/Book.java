package com.common;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Book {

	@Id
	private Integer id;
	private String name;

	@ManyToOne
	@JoinColumn
	private Author author;
}
