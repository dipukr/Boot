package com.common;

import java.util.List;

import jakarta.annotation.Nonnull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RequestBO {
	
	@Nonnull
	private List<Gender> gender;
}
