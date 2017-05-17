package edu.columbia.cs.psl.phosphor.struct;

import java.util.HashMap;
import java.util.Vector;
import java.util.WeakHashMap;

/**
 * Created by jianyu on 5/13/17.
 */
public class ThreadVector {
    //    private HashMap<Integer, Object> hashMap;
    private Object[] hashMap;
    private int count;
    static private int MAX_KEY = 1000000;
    public ThreadVector() {
        hashMap = new Object[MAX_KEY];
        count = 0;
    }
    public int push(Object o) {
        count = (count + 1) % MAX_KEY;
        hashMap[count] = o;
        return count;
    }

    public Object pop(int key) {
        return hashMap[key];
    }
}