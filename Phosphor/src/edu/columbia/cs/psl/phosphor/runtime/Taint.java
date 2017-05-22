package edu.columbia.cs.psl.phosphor.runtime;

import edu.columbia.cs.psl.phosphor.Configuration;
import edu.columbia.cs.psl.phosphor.TaintUtils;
import edu.columbia.cs.psl.phosphor.struct.*;
import edu.columbia.cs.psl.phosphor.struct.LinkedList;

import java.io.Serializable;
import java.util.*;

// new design of Taint
// a taint is composed of a label(null or content, if null, then it has dependency)
// a dependency is just a list of dependent taint

public class Taint implements Serializable {

	private static final long serialVersionUID = 0x1111;

	public static boolean IGNORE_TAINTING;

	public static final int RAW_DEPENDENCY = -1;

	public static final int RESOLVE_DEPENDENCY = -2;

	public static final Taint copyTaint(Taint in)
	{
		return in;
//		if(in == null)
//			return null;
//		Taint ret = new Taint();
//		ret.copyFrom(in);
//		return ret;
	}

	protected void copyFrom(Taint in) {
		lbl = in.lbl;
		dependencies = in.dependencies;
		lbl_list = in.lbl_list;
	}
	public Taint copy()
	{
		return this;
//		if(IGNORE_TAINTING)
//			return this;
//		Taint ret = new Taint();
//		ret.lbl = lbl;
//		ret.dependencies = dependencies;
//		return ret;
	}
//	public Object clone()  {
//		try {
//			Object ret = super.clone();
//			Taint r = (Taint) ret;
//			r.dependencies = (LinkedList<Taint>) dependencies.clone();
//			return ret;
//		} catch (CloneNotSupportedException e) {
//			e.printStackTrace();
//
//			return null;
//		}
//	}
//	@Override
	public String toString() {
		return "Taint lbl=" + lbl;
//		String depStr=" deps = [";
//		if(dependencies != null)
//		{
//			Node dep = dependencies.getFirst();
//			while(dep != null)
//			{
//				if(dep.entry != null)
//				depStr += dep.entry + " ";
//				dep = dep.next;
//			}
//		}
//		depStr += "]";
//		return "Taint [lbl=" + lbl + " "+depStr+"]";
	}

	public int lbl = RAW_DEPENDENCY;

	public Integer[] lbl_list;

	transient public LinkedList<EnqueuedTaint> enqueuedInControlFlow;

	transient public Pair dependencies = null;

//	private void getHelper(HashSet<Object> return_set, Taint t) {
//		if (t.lbl != null) {
//			if (Object[].class.isInstance(t.lbl))
//				for (Object obj:
//					 (Object[])t.lbl) {
//					return_set.add(obj);
//				}
//			else
//				return_set.add(t.lbl);
//			return;
//		}
//		Pair<Taint> pair = t.dependencies;
//		if (pair == null)
//			return;
//		if (pair._1 != null) {
//			getHelper(return_set ,pair._1);
//		}
//		if (pair._2 != null) {
//			getHelper(return_set ,pair._2);
//		}
//	}

	public Integer[] getOriginalTag() {
		HashSet<Integer> return_set = new HashSet<Integer>();
//		getHelper(return_set, this);
		java.util.LinkedList<Taint> toProcess = new java.util.LinkedList<>();
		toProcess.add(this);
		Taint top;
		Pair pair;
		while (!toProcess.isEmpty()) {
			top = toProcess.pop();
			if (top.lbl >= 0) {
//				if (Object[].class.isInstance(top.lbl)) {
//					for (Object obj:
//							(Object[]) top.lbl)
//						return_set.add(obj);
//				} else {
				return_set.add(top.lbl);
//				}
			} else if (top.lbl == RAW_DEPENDENCY){
				pair = top.dependencies;
				if (pair._1 != null)
					toProcess.add((Taint)pair._1);
				if (pair._2 != null)
					toProcess.add((Taint)pair._2);
			} else {
				for (Integer l :
						top.lbl_list) {
					return_set.add(l);
				}
			}
		}

		Integer[] taints = return_set.toArray(new Integer[0]);
		if (taints.length > 0)
			lbl_list = taints;
		lbl = RESOLVE_DEPENDENCY;
		dependencies = null;
		return taints;
	}

	// put the original tag to lbl
	public void preSerialization() {
		if (!Object[].class.isInstance(lbl)) {
			getOriginalTag();
		}
	}
	public Taint(Taint t, int i, TaintSentinel ts) {
		this.lbl = i;
	}
	public Taint(int lbl) {
		this.lbl = lbl;
	}

	public Taint(long lbl) {
		this.lbl = Long.valueOf(lbl).intValue();
	}

	public Object getLabel() {
		return lbl;
	}
	public Pair getDependencies() {
		return dependencies;
	}
	public Taint(Taint t1)
	{
		dependencies = t1.dependencies;
		lbl = t1.lbl;
		lbl_list = t1.lbl_list;
//		if(Configuration.derivedTaintListener != null)
//			Configuration.derivedTaintListener.singleDepCreated(t1, this);
	}
	public Taint(Taint t1, Taint t2)
	{
//		if (true)
//			throw new IllegalArgumentException("error " + t1.toString() + " " + t2.toString());
		dependencies = new Pair(t1, t2);
//		if(Configuration.derivedTaintListener != null)
//			Configuration.derivedTaintListener.doubleDepCreated(t1, t2, this);
	}
	public Taint() {

	}
	public boolean addDependency(Taint d)
	{
		try {
			throw new IllegalAccessException();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return true;
//		if(d == null)
//			return false;
//		boolean added = false;
//		if(d.lbl != null)
//			added = dependencies.addUnique(d.lbl);
//		added |= dependencies.addAll(d.dependencies);
//		return added;
	}
	public TaintedBooleanWithObjTag hasNoDependencies$$PHOSPHORTAGGED(ControlTaintTagStack ctrl, TaintedBooleanWithObjTag ret)
	{
		ret.val = hasNoDependencies();
		ret.taint = null;
		return ret;
	}
	public TaintedBooleanWithObjTag hasNoDependencies$$PHOSPHORTAGGED(TaintedBooleanWithObjTag ret)
	{
		ret.val = hasNoDependencies();
		ret.taint = null;
		return ret;
	}
	public boolean hasNoDependencies() {
		return dependencies == null;
	}
	public static  void combineTagsInPlace(Object obj, Taint t1)
	{
		if(obj == null || t1 == null || IGNORE_TAINTING)
			return;
		@SuppressWarnings("unchecked")
		Taint t = (Taint) TaintUtils.getTaintObj(obj);
		if(t == null)
		{
			MultiTainter.taintedObject(obj, t1);
		}
		else
			t.addDependency(t1);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Taint) {
			return (lbl == RAW_DEPENDENCY && dependencies == ((Taint)obj).dependencies) || (lbl >= 0 && lbl == ((Taint)obj).lbl);
		}
		return false;
	}

	public static  Taint combineTags(Taint t1, Taint t2)
	{
		if(t1 == null && t2 == null)
			return null;
		if(t2 == null)
			return t1;
		if(t1 == null)
			return t2;
		if(t1.equals(t2))
			return t1;
//		if(t1.lbl == null && t1.hasNoDependencies())
//			return t2;
//		if(t2.lbl == null && t2.hasNoDependencies())
//			return t1;
//		if(IGNORE_TAINTING)
//			return t1;
//		if(Configuration.derivedTaintListener != null)
//			Configuration.derivedTaintListener.doubleDepCreated(t1, t2, r);
		return new Taint(t1, t2);
	}
	@SuppressWarnings("unchecked")
	public static  Taint combineTags(Taint t1, ControlTaintTagStack tags){
		if(t1 == null && tags.isEmpty())
			return null;
		else if(t1 == null)
		{
//			if(tags.isEmpty())
//				return null;
			return tags.taint;
		}
		else if(tags.isEmpty())
		{
//			if(t1.lbl == null && t1.hasNoDependencies())
//				return null;
			return t1;
		}
		else if(t1 == tags.taint)
			return t1;
		if(IGNORE_TAINTING)
			return t1;
		return new Taint((Taint) t1, tags.taint);
	}
	@SuppressWarnings("rawtypes")
	public static void combineTagsOnObject(Object o, ControlTaintTagStack tags)
	{
		if(tags.isEmpty() || IGNORE_TAINTING)
			return;
		if(o instanceof TaintedWithObjTag)
		{
			if(o instanceof String)
			{
				Taint onObj  = (Taint) ((TaintedWithObjTag)o).getPHOSPHOR_TAG();
				((String) o).PHOSPHOR_TAG = Taint.combineTags(onObj, tags);
//				for(int i = 0; i < ((String) o).length(); i++)
//				{
//					((String)o).valuePHOSPHOR_TAG[i] = Taint.combineTags(((String)o).valuePHOSPHOR_TAG[i], tags);
//				}
			}
			else
				((TaintedWithObjTag) o).setPHOSPHOR_TAG(Taint.combineTags((Taint) ((TaintedWithObjTag)o).getPHOSPHOR_TAG(), tags));
	
		}
	}

}
