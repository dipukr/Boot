package com.common;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;

@Component
public class CustomBeanPropertyWriter extends BeanPropertyWriter {
	private static final long serialVersionUID = 1L;
	private static final CustomSerializer MASK_SERIALIZER = new CustomSerializer();

	public CustomBeanPropertyWriter(BeanPropertyWriter base) {
		super(base);
	}

	@Override
	public void serializeAsField(Object bean, JsonGenerator gen, SerializerProvider prov) throws Exception {
		if (getAnnotation(Masked.class) != null) {
			gen.writeFieldName(getName());
			MASK_SERIALIZER.serialize(get(bean), gen, prov);
			return;
		}
		super.serializeAsField(bean, gen, prov);
	}
}
