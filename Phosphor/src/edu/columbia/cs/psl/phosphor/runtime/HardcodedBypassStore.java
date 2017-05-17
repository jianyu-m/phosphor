package edu.columbia.cs.psl.phosphor.runtime;


import edu.columbia.cs.psl.phosphor.struct.ArrayList;

import java.util.concurrent.ConcurrentHashMap;


public class HardcodedBypassStore {
	static ArrayList<Object> vals = new ArrayList<Object>();

	static ConcurrentHashMap<Integer, Object> val = new ConcurrentHashMap<Integer, Object>();

	private static int count = 0;

//	private static ThreadLocal<ArrayList<Object>> vals = new ThreadLocal<ArrayList<Object>>() {
//		@Override
//		protected ArrayList<Object> initialValue() {
//			ArrayList<Object> arr = new ArrayList<Object>();
//			arr.add(null);
//			return arr;
//		}
//	};

	public static Object cast(Object obj) {
		return null;
	}

	static{
		vals.add(null);
	}

	public static synchronized int atomicOne() {
		count += 1;
		return count;
	}

	public static final Object get(int i)
	{
		if(i <= 0)
			return null;
		return val.get(i);
	}

	// old_key is not 0, then it has been allocated
	public static final int add(Object a, int old_key)
	{
		if(a == null)
			return (old_key < 0) ? old_key: -old_key;
		int key;
		if (old_key != 0) {
			key = Math.abs(old_key);
		} else {
			key = atomicOne();
		}
		val.put(key, a);
		return key;
//		synchronized (vals) {
//			vals.add(a);
//			return vals.size() - 1;
//		}
	}
}