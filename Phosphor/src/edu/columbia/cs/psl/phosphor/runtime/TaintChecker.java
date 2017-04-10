package edu.columbia.cs.psl.phosphor.runtime;

import edu.columbia.cs.psl.phosphor.struct.ControlTaintTagStack;
import edu.columbia.cs.psl.phosphor.struct.LazyArrayIntTags;
import edu.columbia.cs.psl.phosphor.struct.LazyArrayObjTags;
import edu.columbia.cs.psl.phosphor.struct.LazyCharArrayIntTags;
import edu.columbia.cs.psl.phosphor.struct.LazyCharArrayObjTags;
import edu.columbia.cs.psl.phosphor.struct.TaintedWithIntTag;
import edu.columbia.cs.psl.phosphor.struct.TaintedWithObjTag;

import java.io.*;
import java.util.HashSet;

public class TaintChecker {

	// ignore the tag in specific location
	static private int ignoreTags = ~0;

	// ignore all tags if specific tag appears
	static private int ignoreAllTags = 0;

	static private boolean checkTaintOn = false;

	public static void setCheckTaintOn(Boolean on) {
		checkTaintOn = on;
	}

	public static void setIgnoreAllTags(int tags) {
		ignoreAllTags = tags;
	}

	public static void setIgnoreTags(int ignore) {
		ignoreTags = ~ignore;
	}

	public static void setIgnoreTags$$PHOSPHORTAGGED(int tag, int ignore) {
		ignoreTags = ~ignore;
	}

	public static void addIgnoreTags(int position) {
		ignoreTags &= ~(1 << position);
	}

	public static void addIgnoreTags$$PHOSPHORTAGGED(int tag, int position) {
		ignoreTags &= ~(1 << position);
	}

	private static int removeIgnores(int tag) {
		if ((tag & ignoreAllTags) != 0) {
			return 0;
		}
		return tag & ignoreTags;
	}

	static Taint removeIgnores(Taint t) {
		throw new IllegalAccessError("not support");
	}

	public static void checkTaint(int tag)
	{
		if (!checkTaintOn)
			return;
		if (removeIgnores(tag) != 0)
			throw new IllegalAccessError("Argument carries taint " + tag);
	}
	public static void checkTaint(Taint tag)
	{
		if(tag != null)
			throw new IllegalAccessError("Argument carries taint " + tag);
	}
	public static void checkTaint(Object obj) {
		if (!checkTaintOn) {
			return;
		}
		if(obj == null)
			return;
		if (obj instanceof TaintedWithIntTag) {
			if (removeIgnores(((TaintedWithIntTag) obj).getPHOSPHOR_TAG()) != 0)
				throw new IllegalAccessError("Argument carries taint " + ((TaintedWithIntTag) obj).getPHOSPHOR_TAG());
		}
		else if (obj instanceof TaintedWithObjTag) {
			if (((TaintedWithObjTag) obj).getPHOSPHOR_TAG() != null)
				throw new IllegalAccessError("Argument carries taint " + ((TaintedWithObjTag) obj).getPHOSPHOR_TAG());
		}

		else if(obj instanceof int[])
		{
			for(int i : ((int[])obj))
			{
				if(removeIgnores(i) > 0)
					throw new IllegalAccessError("Argument carries taints - example: " +i);
			}
		}
		else if(obj instanceof LazyArrayIntTags)
		{
			LazyArrayIntTags tags = ((LazyArrayIntTags) obj);
			if (tags.taints != null)
				for (int i : tags.taints) {
					if (removeIgnores(i) > 0)
						throw new IllegalAccessError("Argument carries taints - example: " + i);
				}
		}
		else if(obj instanceof LazyArrayObjTags)
		{
			LazyArrayObjTags tags = ((LazyArrayObjTags) obj);
			if (tags.taints != null)
				for (Object i : tags.taints) {
					if (i != null)
						throw new IllegalAccessError("Argument carries taints - example: " + i);
				}
		}
		else if(obj instanceof Object[])
		{
			for(Object o : ((Object[]) obj))
				checkTaint(o);
		}
		else if(obj instanceof ControlTaintTagStack)
		{
			ControlTaintTagStack ctrl = (ControlTaintTagStack) obj;
			if(ctrl.taint != null && !ctrl.isEmpty())
			{
				throw new IllegalAccessError("Current control flow carries taints:  " + ctrl.taint);
			}
		}
		else if(obj instanceof Taint)
		{
			throw new IllegalAccessError("Argument carries taints:  " + obj);
		}
	}

	public static boolean hasTaints(int[] tags) {
		if(tags == null)
			return false;
		for (int i : tags) {
			if (i != 0)
				return true;
		}
		return false;
	}
	public static void setTaints(Object obj, int tag) {
		if(obj == null)
			return;
		if (obj instanceof TaintedWithIntTag) {
			((TaintedWithIntTag) obj).setPHOSPHOR_TAG(tag);
		} else if (obj instanceof LazyArrayIntTags){
			((LazyArrayIntTags)obj).setTaints(tag);
		} else if (obj.getClass().isArray()) {
			
				Object[] ar = (Object[]) obj;
				for (Object o : ar)
					setTaints(o, tag);
			
		}
		if(obj instanceof Iterable)
		{
			for(Object o : ((Iterable)obj))
				setTaints(o, tag);
		}
	}
	public static void setTaints(LazyCharArrayObjTags tags, Object tag) {
		if(tags.val.length == 0)
			return;
		tags.taints = new Taint[tags.val.length];
		for (int i = 0; i < tags.val.length; i++)
			tags.taints[i] = (Taint) tag;
	}
	public static void setTaints(Object obj, Taint tag) {
		if(obj == null)
			return;
		if (obj instanceof TaintedWithObjTag) {
			((TaintedWithObjTag) obj).setPHOSPHOR_TAG(tag);
		} else if (obj instanceof LazyArrayObjTags){
			((LazyArrayObjTags)obj).setTaints(tag);
		} else if (obj.getClass().isArray()) {
			
				Object[] ar = (Object[]) obj;
				for (Object o : ar)
					setTaints(o, tag);
			
		}
		if(obj instanceof Iterable)
		{
			for(Object o : ((Iterable)obj))
				setTaints(o, tag);
		}
	}

	public static void setTaints(LazyCharArrayIntTags tags, int tag) {
		if(tags.val.length == 0)
			return;
		tags.taints = new int[tags.val.length];
		for (int i = 0; i < tags.val.length; i++)
			tags.taints[i] = tag;
	}
	public static void setTaints(Taint[] array, Taint tag) {
		if(array == null)
			return;
		for (int i = 0; i < array.length; i++)
			array[i] = tag;
	}
}
