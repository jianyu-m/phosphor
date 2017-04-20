package edu.columbia.cs.psl.phosphor.instrumenter;

import edu.columbia.cs.psl.phosphor.Configuration;
import edu.columbia.cs.psl.phosphor.runtime.Taint;
import edu.columbia.cs.psl.phosphor.runtime.Tainter;
import edu.columbia.cs.psl.phosphor.struct.*;
import sun.misc.Unsafe;

import java.io.ObjectInputStream;
import java.lang.reflect.Field;

/**
 * Created by jianyu on 4/7/17.
 */
public class StreamObjectHelper {

    static private Class<?> taintedArraySupperClass =
            (Configuration.MULTI_TAINTING) ? LazyArrayObjTags.class : LazyArrayIntTags.class;

    public static Object getCastObject(Unsafe unsafe, Object o, Class c) {
        // check if it is array
        if (c == Object.class && int[].class.isAssignableFrom(o.getClass())) {
            return new LazyIntArrayIntTags((int[])o);
        }
        return o;
    }
    public static void putObjectWrapper(Unsafe unsafe, Object o, long key, Object val, Class c) {
        if (c != null && val != null && !c.isAssignableFrom(val.getClass())) {
            val = ((LazyArrayIntTags)val).getVal();
        }
        unsafe.putObject(o, key, val);
    }
    public static void putObjectWrapper$$PHOSPHORTAGGED(Unsafe unsafe, Object o, int key_tag, long key, Object val, Class c) {
        if (c != null && val != null && !c.isAssignableFrom(val.getClass())) {
            val = ((LazyArrayIntTags)val).getVal();
        }
        unsafe.putObject(o, key, val);
    }
    public static void putObjectWrapper$$PHOSPHORTAGGED(Unsafe unsafe, Object o, Taint tag, long key, Object val, Class c) {
        if (c != null && !c.isAssignableFrom(val.getClass())) {
            val = ((LazyArrayObjTags)val).getVal();
        }
        unsafe.putObject(o, key, val);
    }

    // only for int tag
    public static void resolveObjectWrapper$$PHOSPHORTAGGED(ObjectInputStream ois, Object obj) {
        resolveObjectWrapper(ois, obj);
    }

    public static void resolveObjectWrapper(ObjectInputStream ois, Object obj) {
        if (obj == null)
            return;

        Class c = obj.getClass();
        Field[] fs = c.getFields();
        for (Field f:
                fs) {
			/* we only need to modify the tag for array */
            String field_name = f.getName();
            if (field_name.endsWith("PHOSPHOR_TAG") &&
                    taintedArraySupperClass.isAssignableFrom(f.getType())) {
                String original_name = f.getName().substring(0, field_name.length() - 12);
                try {
                    f.setAccessible(true);
                    Field of = c.getDeclaredField(original_name);
                    of.setAccessible(true);
                    Object ob = of.get(obj);
                    if (ob == null) continue;
                    if (f.get(obj) != null) continue;
                    if (taintedArraySupperClass.isAssignableFrom(ob.getClass())) {
                        f.setObjectWrapper(obj, ob);
                    } else if (taintedArraySupperClass == LazyArrayIntTags.class) {
                        Class tc = ob.getClass();
                        Object to = null;
                        if (int[].class.isAssignableFrom(tc)) {
                            to = new LazyIntArrayIntTags((int[])ob);
                        } else if (short[].class.isAssignableFrom(tc)) {
                            to = new LazyShortArrayIntTags((short[])ob);
                        } else if (long[].class.isAssignableFrom(tc)) {
                            to = new LazyLongArrayIntTags((long[])ob);
                        } else if (double[].class.isAssignableFrom(tc)) {
                            to = new LazyDoubleArrayIntTags((double[])ob);
                        } else if (float[].class.isAssignableFrom(tc)) {
                            to = new LazyFloatArrayIntTags((float[])ob);
                        } else if (char[].class.isAssignableFrom(tc)) {
                            to = new LazyCharArrayIntTags((char[])ob);
                        } else if (byte[].class.isAssignableFrom(tc)) {
                            to = new LazyByteArrayIntTags((byte[])ob);
                        } else if (boolean[].class.isAssignableFrom(tc)) {
                            to = new LazyBooleanArrayIntTags((boolean[])ob);
                        } else {
                            throw new IllegalAccessException("wrong type");
                        }
                        f.setObjectWrapper(obj, to);
                    } else if (taintedArraySupperClass == LazyArrayObjTags.class) {
                        Class tc = ob.getClass();
                        Object to = null;
                        if (int[].class.isAssignableFrom(tc)) {
                            to = new LazyIntArrayObjTags((int[])ob);
                        } else if (short[].class.isAssignableFrom(tc)) {
                            to = new LazyShortArrayObjTags((short[])ob);
                        } else if (long[].class.isAssignableFrom(tc)) {
                            to = new LazyLongArrayObjTags((long[])ob);
                        } else if (double[].class.isAssignableFrom(tc)) {
                            to = new LazyDoubleArrayObjTags((double[])ob);
                        } else if (float[].class.isAssignableFrom(tc)) {
                            to = new LazyFloatArrayObjTags((float[])ob);
                        } else if (char[].class.isAssignableFrom(tc)) {
                            to = new LazyCharArrayObjTags((char[])ob);
                        } else if (byte[].class.isAssignableFrom(tc)) {
                            to = new LazyByteArrayObjTags((byte[])ob);
                        } else if (boolean[].class.isAssignableFrom(tc)) {
                            to = new LazyBooleanArrayObjTags((boolean[])ob);
                        } else {
                            throw new IllegalAccessException("wrong type");
                        }
                        f.setObjectWrapper(obj, to);
                    } else {
                        throw new IllegalAccessException("error array taint type");
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
