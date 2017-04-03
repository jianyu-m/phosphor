package edu.columbia.cs.psl.phosphor.runtime;

import edu.columbia.cs.psl.phosphor.TaintUtils;
import edu.columbia.cs.psl.phosphor.struct.*;

import java.lang.reflect.Array;
import java.lang.reflect.Field;

/**
 * Created by max on 3/4/2017.
 */

public class UninstTainter {

    private static boolean isArrayOrPrimitive(Class c) {
        return (c == Integer.class || c == Short.class || c == Long.class || c == Double.class
            || c == Float.class || c == Byte.class || c == Character.class || c == int.class
            || c == double.class || c == byte.class || c == float.class || c == short.class
            || c == long.class || c == char.class || c == String.class
            || c == int[].class || c == double[].class || c == byte[].class || c == float[].class
            || c == short[].class || c == long[].class || c == char[].class || c == String[].class
            || Array.class.isAssignableFrom(c) || LazyArrayIntTags.class.isAssignableFrom(c)
            || TaintedPrimitiveWithIntTag.class.isAssignableFrom(c));
    }

    public static void fixStreamObject(Object obj) {
        // usd declared or not
        if (obj == null) return;
        Class c = obj.getClass();
        Field[] fs = c.getDeclaredFields();
        for (Field f:
                fs) {
			/* we only need to modify the tag for array */
            String field_name = f.getName();
            // we need to fix problem recusively
            Class dc = f.getType();
            if (!isArrayOrPrimitive(dc) && !field_name.endsWith("PHOSPHOR_TAG")) {
                try {
                    f.setAccessible(true);
                    fixStreamObject(f.get(obj));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            if (field_name.endsWith("PHOSPHOR_TAG") && LazyArrayIntTags.class.isAssignableFrom(f.getType())) {
                String original_name = f.getName().substring(0, field_name.length() - 12);
                try {
                    f.setAccessible(true);
                    Field of = c.getDeclaredField(original_name);
                    of.setAccessible(true);
                    Object ob = of.get(obj);
                    // change it????
                    f.set(obj, of.get(obj));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}