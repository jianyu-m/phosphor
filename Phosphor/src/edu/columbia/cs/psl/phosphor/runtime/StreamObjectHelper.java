package edu.columbia.cs.psl.phosphor.runtime;

import edu.columbia.cs.psl.phosphor.struct.LazyArrayIntTags;
import edu.columbia.cs.psl.phosphor.struct.LazyIntArrayIntTags;
import sun.misc.Unsafe;

/**
 * Created by jianyu on 4/7/17.
 */
public class StreamObjectHelper {
    public static Object getCastObject(Unsafe unsafe, Object o, Class c) {
        // check if it is array
        if (c == Object.class && int[].class.isAssignableFrom(o.getClass())) {
            return new LazyIntArrayIntTags((int[])o);
        }
        return o;
    }
    public static void putObjectWrapper(Unsafe unsafe, Object o, long key, Object val, Class c) {
        if (c == Object.class && int[].class.isAssignableFrom(o.getClass())) {
            val = new LazyIntArrayIntTags((int[])val);
        }
        unsafe.putObject(o, key, val);
    }
    public static void putObjectWrapper$$PHOSPHORTAGGED(Unsafe unsafe, Object o, int key_tag,long key, Object val, Class c) {
        if (val != null && !c.isAssignableFrom(val.getClass())) {
            val = ((LazyArrayIntTags)val).getVal();
        }
        unsafe.putObject(o, key, val);
    }
}
