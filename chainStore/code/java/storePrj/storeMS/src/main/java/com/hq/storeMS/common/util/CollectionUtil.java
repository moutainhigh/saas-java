package com.hq.storeMS.common.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;

public class CollectionUtil {
	/**
	 * a与b差集
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static <T> Set<T> subtract(final Set<T> a, final Set<T> b) {
		Set<T> set = new HashSet<T>(a);
		set.removeAll(b);
		return set;
	}

	public static <T> List<T> subtract(final List<T> a, final List<T> b) {
		List<T> list = new ArrayList<T>(a);
		for (Iterator<T> it = b.iterator(); it.hasNext();) {
			list.remove(it.next());
		}
		return list;
	}

	/**
	 * a与b并集
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static <T> Set<T> addAll(final Set<T> a, final Set<T> b) {
		Set<T> set = new HashSet<T>(a);
		set.addAll(b);
		return set;
	}

	public static <T> List<T> addAll(final List<T> a, final List<T> b) {
		List<T> list = new ArrayList<T>(a);
		list.addAll(b);
		return list;
	}

	/**
	 * a与b交集
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static <T> Set<T> retainAll(final Set<T> a, final Set<T> b) {
		Set<T> set = new HashSet<T>(a);
		set.retainAll(b);
		return set;
	}

	public static <T> List<T> retainAll(final List<T> a, final List<T> b) {
		List<T> list = new ArrayList<T>();
		for (Iterator<T> iter = a.iterator(); iter.hasNext();) {
			T obj = iter.next();
			if (b.contains(obj)) {
				list.add(obj);
			}
		}
		return list;
	}
	
	public static <T> T getObjFromSet(final Set<T> a) {
		if(CollectionUtils.isEmpty(a)) {
			return null;
		}
		return a.iterator().next();
	}

	public static void main(String[] args) throws Exception {
		// sessionId2Token();
		Set<Long> applyStoreIds = new HashSet<Long>();
		applyStoreIds.add(2L);
		Set<Long> applyStoreIds2 = new HashSet<Long>();
		applyStoreIds2.add(4L);
		applyStoreIds2.add(2L);
		applyStoreIds2.add(7L);

		System.out.println(retainAll(applyStoreIds2, applyStoreIds));
		System.out.println(getObjFromSet(applyStoreIds));
		System.out.println(applyStoreIds2);
	}

}
