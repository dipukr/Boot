package com.common;

import java.util.List;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;

public class CustomBeanSerializerModifier extends BeanSerializerModifier {
	private static final long serialVersionUID = 1L;

	@Override
	public List<BeanPropertyWriter> changeProperties(SerializationConfig config, BeanDescription beanDesc, List<BeanPropertyWriter> beanWriters) {
		for (int i = 0; i < beanWriters.size(); i++) {
			BeanPropertyWriter writer = beanWriters.get(i);
			if (writer.getAnnotation(Masked.class) != null)
				beanWriters.set(i, new CustomBeanPropertyWriter(writer));
		}
		return beanWriters;
	}
}
