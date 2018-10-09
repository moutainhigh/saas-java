package com.hq.payms.common.utils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 创建指定容量Map<br>
 * Map到了指定容量，会以FIFO算法移除最老的key和value
 * 
 * @author: wujunwei
 * @version: v1.0
 * @since: JDK 1.8
 */
public class CachedMapFactory {

	public static <K, V> Map<K, V> newMap(int maximumSize) {

		return new LinkedHashMap<K, V>() {
			private static final long serialVersionUID = 1L;

			@Override
			protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
				return size() > maximumSize;
			}
		};
	}

}
