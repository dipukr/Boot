package com.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class CustomObjectMapperHolder {
	private static final ObjectMapper OBJECT_MAPPER;

	static {
		var mapper = new ObjectMapper();
		var module = new SimpleModule();
		module.setSerializerModifier(new CustomBeanSerializerModifier());
		mapper.registerModule(module);
		OBJECT_MAPPER = mapper;
	}

	public static ObjectMapper getMapper() {
		return OBJECT_MAPPER;
	}
}
