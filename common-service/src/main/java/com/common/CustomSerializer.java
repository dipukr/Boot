package com.common;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

@Component
public class CustomSerializer extends JsonSerializer<Object> {

	@Override
	public void serialize(Object val, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		if (val == null) gen.writeNull();
		else gen.writeString("********");
	}
}
