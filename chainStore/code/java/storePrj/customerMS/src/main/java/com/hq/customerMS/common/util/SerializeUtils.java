package com.hq.customerMS.common.util;

import java.io.Serializable;

import org.apache.commons.lang3.SerializationUtils;
import org.apache.shiro.codec.Base64;

public class SerializeUtils extends SerializationUtils {

	public static String serializeToString(Serializable obj) {
		try {
			byte[] value = serialize(obj);
			return Base64.encodeToString(value);
		} catch (Exception e) {
			throw new RuntimeException("serialize session error", e);
		}
	}

	public static <T> T deserializeFromString(String base64) {
		try {
			byte[] objectData = Base64.decode(base64);
			return deserialize(objectData);
		} catch (Exception e) {
			throw new RuntimeException("deserialize session error", e);
		}
	}
}