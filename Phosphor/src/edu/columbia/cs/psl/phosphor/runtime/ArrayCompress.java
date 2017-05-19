package edu.columbia.cs.psl.phosphor.runtime;

import org.roaringbitmap.RoaringBitmap;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by jianyu on 5/18/17.
 */
public class ArrayCompress {
    static byte[] Compress(Object[] arr) {
//        if (arr.size() < 5) {
//            return arr.toArray();
//        }
        RoaringBitmap mrb = RoaringBitmap.bitmapOf();
        for (Object i :
                arr) {
            mrb.add((Integer)i);
        }
        mrb.runOptimize(); //to improve compression
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(mrb.serializedSizeInBytes());
        try {
            mrb.serialize(new java.io.DataOutputStream(byteArrayOutputStream));
        } catch (IOException ioe) {
            // should never happen because we write to a byte array
            throw new RuntimeException("unexpected error while serializing to a byte array");
        }
//        arr.add(byteArrayOutputStream.toByteArray());
        return byteArrayOutputStream.toByteArray();
    }
}
