package edu.columbia.cs.psl.phosphor.struct;

import java.io.Serializable;

public class Pair<T> implements Serializable {
	public T _1;
	public T _2;
	public Pair(T a, T b) {
		_1 = a;
		_2 = b;
	}
}
