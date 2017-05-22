package edu.columbia.cs.psl.phosphor.struct;

import edu.columbia.cs.psl.phosphor.runtime.Taint;

import java.io.Serializable;

public class Pair implements Serializable {
	public Taint _1;
	public Taint _2;
	public Pair(Taint a, Taint b) {
		_1 = a;
		_2 = b;
	}
}
