package edu.columbia.cs.psl.phosphor.struct;

import edu.columbia.cs.psl.phosphor.runtime.Taint;


public abstract class LazyArrayObjTags implements Cloneable {
	public Taint[] taints;

	public LazyArrayObjTags(Taint[] taints) {
		this.taints = taints;
	}

	public LazyArrayObjTags() {
	}

	public Taint lengthTaint;

	public Taint getLengthTaint() {
		return lengthTaint;
	}


	public abstract int getLength();
	public void setTaints(Taint tag) {
		if(taints == null)
			taints = new Taint[getLength()];
		for(int i = 0; i < taints.length; i++)
		{
			taints[i]=tag.copy();
		}
	}

	public abstract Object getVal();
}
