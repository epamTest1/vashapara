package com.couple.services.external;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

import com.couple.services.external.User.Sex;

class SexDeserializer extends JsonDeserializer<Sex>{
	private static final List<Sex> sexByCodeIndex = Arrays.asList(Sex.NOT_SET, Sex.FEMALE, Sex.MALE);
	
	@Override
	public Sex deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		int code = jp.getIntValue();
		if (code < sexByCodeIndex.size()) {
			return sexByCodeIndex.get(code);
		} else {
			throw ctxt.weirdNumberException(Integer.class, "Unknown code for User.Sex enum.");
		}
	}
	
}
