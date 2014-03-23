package edu.columbia.cs.psl.phosphor.struct.multid;

import java.io.IOException;
import java.io.Serializable;

import edu.columbia.cs.psl.phosphor.TaintUtils;
import edu.columbia.cs.psl.phosphor.org.objectweb.asm.Type;

public final class MultiDTaintedLongArray extends MultiDTaintedArray implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1632040273380781122L;
	public long[] val;

	public MultiDTaintedLongArray(int[] taint, long[] val) {
		super(taint, Type.LONG);
		this.val = val;
	}

	@Override
	public Object getVal() {
		return val;
	}

	@Override
	public Object clone() {
		return new MultiDTaintedLongArray(taint.clone(), val.clone());
	}

	private void writeObject(java.io.ObjectOutputStream stream) throws IOException {
		if (val == null) {
			stream.writeObject(null);
			return;
		}
		stream.writeInt(val.length);
		for (int i = 0; i < val.length; i++) {
			if (TaintUtils.TAINT_THROUGH_SERIALIZATION)
				stream.writeInt(taint[i]);
			stream.writeLong(val[i]);
		}
	}

	private void readObject(java.io.ObjectInputStream stream) throws IOException, ClassNotFoundException {
		int len = stream.readInt();
		val = new long[len];
		taint = new int[len];
		for (int i = 0; i < len; i++) {
			if (TaintUtils.TAINT_THROUGH_SERIALIZATION)
				taint[i] = stream.readInt();
			val[i] = stream.readLong();
		}
	}
}
