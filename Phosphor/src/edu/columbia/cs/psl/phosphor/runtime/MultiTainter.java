package edu.columbia.cs.psl.phosphor.runtime;

import edu.columbia.cs.psl.phosphor.Configuration;
import edu.columbia.cs.psl.phosphor.struct.*;
import edu.columbia.cs.psl.phosphor.struct.multid.MultiDTaintedArrayWithObjTag;

import java.lang.reflect.Field;

public final class MultiTainter {
	public static ControlTaintTagStack getControlFlow()
	{
		throw new IllegalStateException();
	}
	public static ControlTaintTagStack getControlFlow$$PHOSPHORTAGGED(ControlTaintTagStack ctrl)
	{
		return ctrl;
	}
	public static boolean taintedBoolean(boolean in, Taint t) {
		throw new IllegalStateException("Calling uninstrumented Phosphor stubs!");
	}
	public static char taintedChar(char in, Taint t) {
		throw new IllegalStateException("Calling uninstrumented Phosphor stubs!");
	}
	public static byte taintedByte(byte in, Taint t) {
		throw new IllegalStateException("Calling uninstrumented Phosphor stubs!");
	}
	public static int taintedInt(int in, Taint t) {
		throw new IllegalStateException("Calling uninstrumented Phosphor stubs!");
	}
	public static long taintedLong(long in, Taint t) {
		throw new IllegalStateException("Calling uninstrumented Phosphor stubs!");
	}
	public static short taintedShort(short in, Taint t) {
		throw new IllegalStateException("Calling uninstrumented Phosphor stubs!");
	}
	public static double taintedDouble(double in, Taint t) {
		throw new IllegalStateException("Calling uninstrumented Phosphor stubs!");
	}
	public static float taintedFloat(float in, Taint t) {
		throw new IllegalStateException("Calling uninstrumented Phosphor stubs!");
	}
	public static boolean taintedBoolean(boolean in, Object lbl)
	{
		throw new IllegalStateException("Calling uninstrumented Phosphor stubs!");
	}
	public static byte taintedByte(byte in, Object lbl)
	{
		throw new IllegalStateException("Calling uninstrumented Phosphor stubs!");
	}
	public static char taintedChar(char in, Object lbl)
	{
		throw new IllegalStateException("Calling uninstrumented Phosphor stubs!");
	}
	public static double taintedDouble(double in, Object lbl)
	{
		throw new IllegalStateException("Calling uninstrumented Phosphor stubs!");
	}
	public static float taintedFloat(float in, Object lbl)
	{
		throw new IllegalStateException("Calling uninstrumented Phosphor stubs!");
	}
	public static long taintedLong(long in, Object lbl)
	{
		throw new IllegalStateException("Calling uninstrumented Phosphor stubs!");
	}
	public static int taintedInt(int in, Object lbl)
	{
		throw new IllegalStateException("Calling uninstrumented Phosphor stubs!");
	}
	public static short taintedShort(short in, Object lbl)
	{
		throw new IllegalStateException("Calling uninstrumented Phosphor stubs!");
	}
	public static boolean[] taintedBooleanArray(boolean[] in, Object lbl)
	{
		throw new IllegalStateException("Calling uninstrumented Phosphor stubs!");
	}
	public static byte[] taintedByteArray(byte[] in, Object lbl)
	{
		throw new IllegalStateException("Calling uninstrumented Phosphor stubs!");
	}
	public static char[] taintedCharArray(char[] in, Object lbl)
	{
		throw new IllegalStateException("Calling uninstrumented Phosphor stubs!");
	}
	public static double[] taintedDoubleArray(double[] in, Object lbl)
	{
		throw new IllegalStateException("Calling uninstrumented Phosphor stubs!");
	}
	public static float[] taintedFloatArray(float[] in, Object lbl)
	{
		throw new IllegalStateException("Calling uninstrumented Phosphor stubs!");
	}
	public static long[] taintedLongArray(long[] in, Object lbl)
	{
		throw new IllegalStateException("Calling uninstrumented Phosphor stubs!");
	}
	public static int[] taintedIntArray(int[] in, Object lbl)
	{
		throw new IllegalStateException("Calling uninstrumented Phosphor stubs!");
	}
	public static short[] taintedShortArray(short[] in, Object lbl)
	{
		throw new IllegalStateException("Calling uninstrumented Phosphor stubs!");
	}
	public static Taint getTaint(boolean in)
	{
		throw new IllegalStateException("Calling uninstrumented Phosphor stubs!");
	}
	public static Taint getTaint(byte in)
	{
		throw new IllegalStateException("Calling uninstrumented Phosphor stubs!");
	}
	public static Taint getTaint(char in)
	{
		throw new IllegalStateException("Calling uninstrumented Phosphor stubs!");
	}
	public static Taint getTaint(double in)
	{
		throw new IllegalStateException("Calling uninstrumented Phosphor stubs!");
	}
	public static Taint getTaint(float in)
	{
		throw new IllegalStateException("Calling uninstrumented Phosphor stubs!");
	}
	public static Taint getTaint(int in)
	{
		throw new IllegalStateException("Calling uninstrumented Phosphor stubs!");
	}
	public static Taint getTaint(long in)
	{
		throw new IllegalStateException("Calling uninstrumented Phosphor stubs!");
	}
	public static Taint getTaint(short in)
	{
		throw new IllegalStateException("Calling uninstrumented Phosphor stubs!");
	}
	
	public static Taint getTaint$$PHOSPHORTAGGED(Taint t, boolean b)
	{
		return t;
	}
	public static Taint getTaint$$PHOSPHORTAGGED(Taint t, byte b)
	{
		return t;
	}
	public static Taint getTaint$$PHOSPHORTAGGED(Taint t, char b)
	{
		return t;
	}
	public static Taint getTaint$$PHOSPHORTAGGED(Taint t, double b)
	{
		return t;
	}
	public static Taint getTaint$$PHOSPHORTAGGED(Taint t, float b)
	{
		return t;
	}
	public static Taint getTaint$$PHOSPHORTAGGED(Taint t, int b)
	{
		return t;
	}
	public static Taint getTaint$$PHOSPHORTAGGED(Taint t, long b)
	{
		return t;
	}
	public static Taint getTaint$$PHOSPHORTAGGED(Taint t, short b)
	{
		return t;
	}
	
	public static TaintedBooleanWithObjTag taintedBoolean$$PHOSPHORTAGGED(Taint oldTag, boolean in, Object lbl, TaintedBooleanWithObjTag ret)
	{
		ret.taint = new Taint(lbl);
		ret.val = in;
		return ret;
	}
	public static TaintedByteWithObjTag taintedByte$$PHOSPHORTAGGED(Taint oldTag, byte in, Object lbl, TaintedByteWithObjTag ret)
	{
		ret.taint = new Taint(lbl);
		ret.val = in;
		return ret;
	}
	public static TaintedCharWithObjTag taintedChar$$PHOSPHORTAGGED(Taint oldTag, char in, Object lbl, TaintedCharWithObjTag ret)
	{
		ret.taint = new Taint(lbl);
		ret.val = in;
		return ret;
	}
	public static TaintedDoubleWithObjTag taintedDouble$$PHOSPHORTAGGED(Taint oldTag, double in, Object lbl, TaintedDoubleWithObjTag ret)
	{
		ret.taint = new Taint(lbl);
		ret.val = in;
		return ret;
	}
	public static TaintedFloatWithObjTag taintedFloat$$PHOSPHORTAGGED(Taint oldTag, float in, Object lbl, TaintedFloatWithObjTag ret)
	{
		ret.taint = new Taint(lbl);
		ret.val = in;
		return ret;
	}
	public static TaintedIntWithObjTag taintedInt$$PHOSPHORTAGGED(Taint oldTag, int in, Object lbl, TaintedIntWithObjTag ret)
	{
		ret.taint = new Taint(lbl);
		ret.val = in;
		return ret;
	}
	public static TaintedLongWithObjTag taintedLong$$PHOSPHORTAGGED(Taint oldTag, long in, Object lbl, TaintedLongWithObjTag ret)
	{
		ret.taint = new Taint(lbl);
		ret.val = in;
		return ret;
	}
	public static TaintedShortWithObjTag taintedShort$$PHOSPHORTAGGED(Taint oldTag, short in, Object lbl, TaintedShortWithObjTag ret)
	{
		ret.taint = new Taint(lbl);
		ret.val = in;
		return ret;
	}
	public static LazyBooleanArrayObjTags taintedBooleanArray$$PHOSPHORTAGGED(LazyBooleanArrayObjTags ret, boolean[] in, Object lbl)
	{
		if(ret.taints == null)
			ret.taints = new Taint[in.length];
		for(int i =0 ; i < in.length; i++)
			ret.taints[i] = new Taint(lbl);
		return ret;
	}
	public static LazyByteArrayObjTags taintedByteArray$$PHOSPHORTAGGED(LazyByteArrayObjTags ret, byte[] in, Object lbl)
	{
		if(ret.taints == null)
			ret.taints = new Taint[in.length];
		for(int i =0 ; i < in.length; i++)
			ret.taints[i] = new Taint(lbl);
		return ret;
	}
	public static LazyCharArrayObjTags taintedCharArray$$PHOSPHORTAGGED(LazyCharArrayObjTags ret, char[] in, Object lbl)
	{
		if(ret.taints == null)
			ret.taints = new Taint[in.length];
		for(int i =0 ; i < in.length; i++)
			ret.taints[i] = new Taint(lbl);
		return ret;
	}
	public static LazyDoubleArrayObjTags taintedDoubleArray$$PHOSPHORTAGGED(LazyDoubleArrayObjTags ret, double[] in, Object lbl)
	{
		if(ret.taints == null)
			ret.taints = new Taint[in.length];
		for(int i =0 ; i < in.length; i++)
			ret.taints[i] = new Taint(lbl);
		return ret;
	}
	public static LazyFloatArrayObjTags taintedFloatArray$$PHOSPHORTAGGED(LazyFloatArrayObjTags ret, float[] in, Object lbl)
	{
		if(ret.taints == null)
			ret.taints = new Taint[in.length];
		for(int i =0 ; i < in.length; i++)
			ret.taints[i] = new Taint(lbl);
		return ret;
	}
	public static LazyIntArrayObjTags taintedIntArray$$PHOSPHORTAGGED(LazyIntArrayObjTags ret, int[] in, Object lbl)
	{
		if(ret.taints == null)
			ret.taints = new Taint[in.length];
		for(int i =0 ; i < in.length; i++)
			ret.taints[i] = new Taint(lbl);
		return ret;
	}
	public static LazyShortArrayObjTags taintedShortArray$$PHOSPHORTAGGED(LazyShortArrayObjTags ret, short[] in, Object lbl)
	{
		if(ret.taints == null)
			ret.taints = new Taint[in.length];
		for(int i =0 ; i < in.length; i++)
			ret.taints[i] = new Taint(lbl);
		return ret;
	}
	public static LazyLongArrayObjTags taintedLongArray$$PHOSPHORTAGGED(LazyLongArrayObjTags ret, long[] in, Object lbl)
	{
		if(ret.taints == null)
			ret.taints = new Taint[in.length];
		for(int i =0 ; i < in.length; i++)
			ret.taints[i] = new Taint(lbl);
		return ret;
	}
	public static TaintedBooleanWithObjTag taintedBoolean$$PHOSPHORTAGGED(Taint oldTag, boolean in, Taint t, TaintedBooleanWithObjTag ret)
	{
		ret.taint = t.copy();
		ret.val = in;
		return ret;
	}
	public static TaintedCharWithObjTag taintedChar$$PHOSPHORTAGGED(Taint oldTag, char in, Taint t, TaintedCharWithObjTag ret)
	{
		ret.taint = t.copy();
		ret.val = in;
		return ret;
	}
	public static TaintedByteWithObjTag taintedByte$$PHOSPHORTAGGED(Taint oldTag, byte in, Taint t, TaintedByteWithObjTag ret)
	{
		ret.taint = t.copy();
		ret.val = in;
		return ret;
	}
	public static TaintedIntWithObjTag taintedInt$$PHOSPHORTAGGED(Taint oldTag, int in, Taint t, TaintedIntWithObjTag ret)
	{
		ret.taint = t.copy();
		ret.val = in;
		return ret;
	}
	public static TaintedLongWithObjTag taintedLong$$PHOSPHORTAGGED(Taint oldTag, long in, Taint t, TaintedLongWithObjTag ret)
	{
		ret.taint = t.copy();
		ret.val = in;
		return ret;
	}
	public static TaintedShortWithObjTag taintedShort$$PHOSPHORTAGGED(Taint oldTag, short in, Taint t, TaintedShortWithObjTag ret)
	{
		ret.taint = t.copy();
		ret.val = in;
		return ret;
	}
	public static TaintedDoubleWithObjTag taintedDouble$$PHOSPHORTAGGED(Taint oldTag, double in, Taint t, TaintedDoubleWithObjTag ret)
	{
		ret.taint = t.copy();
		ret.val = in;
		return ret;
	}
	public static TaintedFloatWithObjTag taintedFloat$$PHOSPHORTAGGED(Taint oldTag, float in, Taint t, TaintedFloatWithObjTag ret)
	{
		ret.taint = t.copy();
		ret.val = in;
		return ret;
	}
	public static Taint getTaint$$PHOSPHORTAGGED(Object obj)
	{
		return getTaint(obj);
	}

	public static final Taint getTaint(Object obj)
	{
		if(obj instanceof MultiDTaintedArrayWithObjTag)
			obj = ((MultiDTaintedArrayWithObjTag) obj).getVal();
		if(Configuration.taintTagFactory == null)
			return null;
		if(obj instanceof TaintedWithObjTag)
		{
			Taint ret = (Taint) ((TaintedWithObjTag) obj).getPHOSPHOR_TAG();
			return ret;
		}
		else if(obj != null && ArrayHelper.engaged == 1)
		{
			Taint ret = ArrayHelper.getTag(obj);
			return ret;
		}
		else
			return null;
	}
	public static Taint getTaint$$PHOSPHORTAGGED(Object obj, ControlTaintTagStack ctrl)
	{
		return getTaint(obj);
	}
	public static void taintedObject(Object obj, Taint tag)
	{
		if(obj instanceof MultiDTaintedArrayWithObjTag)
			obj = ((MultiDTaintedArrayWithObjTag) obj).getVal();
		if(obj instanceof TaintedWithObjTag) {
			((TaintedWithObjTag) obj).setPHOSPHOR_TAG(tag);
		}
//
//		if(obj instanceof MultiDTaintedArrayWithObjTag)
//			obj = ((MultiDTaintedArrayWithObjTag) obj).getVal();
//		if(obj instanceof TaintedWithObjTag) {
//			((TaintedWithObjTag) obj).setPHOSPHOR_TAG(tag);
//			Field[] fields = obj.getClass().getDeclaredFields();
//			for (Field f:
//					fields) {
//				try {
//					f.setAccessible(true);
//					Object ob = f.get(obj);
//
//					if (ob == null) continue;
//
//					// Do nothing for normal fields
//					if (f.getName().contains("PHOSPHOR_TAG")) {
//						Class t = f.getType();
//						if (t == Taint.class) {
//							f.set(obj, tag);
//						} else if (LazyArrayObjTags.class.isAssignableFrom(t)) {
//							((LazyArrayObjTags)ob).setTaints(tag);
//							f.set(obj, ob);
//						}
////						Object m = f.get(obj);
//					}
//				} catch (IllegalAccessException e) {
//					//ignore
//				} catch (IllegalArgumentException k) {
//					//ignore
//				}
//			}
//		}
//		else if(obj != null && ArrayHelper.engaged == 1)
//			ArrayHelper.setTag(obj, tag);
	}
	public static void taintedObject$$PHOSPHORTAGGED(Object obj, Taint tag, ControlTaintTagStack ctrl)
	{
		taintedObject(obj, tag);
	}
	public static void taintedObject$$PHOSPHORTAGGED(Object obj, Taint tag)
	{
		taintedObject(obj, tag);
	}
	public static void taintedString(String s, Taint tag) {
		if (s != null) {
			Object ob = (Object)s;
			((TaintedWithObjTag)ob).setPHOSPHOR_TAG(tag);
		}

//		try {
//			Field f = String.class.getDeclaredField("valuePHOSPHOR_TAG");
//			f.setAccessible(true);
//			Object ob = f.get(s);
//			((LazyArrayObjTags)ob).setTaints(tag);
//			f.set(s, ob);
//		} catch (NoSuchFieldException e) {
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			e.printStackTrace();
//		}
	}
	public static void taintedString$$PHOSPHORTAGGED(String s, Taint tag) {
		taintedString(s, tag);
	}

}
