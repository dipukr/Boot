package com.common;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Writer {

	@Id
	private Integer id;
	private String name;

	@ManyToMany(mappedBy = "writers")
	private List<Novel> novels;
}
