package edu.columbia.cs.psl.phosphor.struct;


import java.io.Serializable;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class LinkedList<T> implements Cloneable, Serializable {

	// static storage for combined tag

//	public HashSet<T> set;

//	static ConcurrentHashMap<Integer, Object> storage = new ConcurrentHashMap<Integer, Object>();

	static class IntWrapper {
		int count = 0;
	}

	private static ThreadLocal<java.util.LinkedList<Object>> storage = new ThreadLocal<java.util.LinkedList<Object>>() {
		@Override
		protected java.util.LinkedList<Object> initialValue() {
			return new java.util.LinkedList<Object>();
		}
	};

	private static ThreadLocal<IntWrapper> count = new ThreadLocal<IntWrapper>() {
		@Override
		protected IntWrapper initialValue() {
			return new IntWrapper();
		}
	};

	private static int id = 0;

	private static int automicId() {
		IntWrapper i = count.get();
		i.count += 1;
		return i.count;
	}

//	HashSet<T> set;

	Object currentValue = null;

	public static class Node<Z> implements Cloneable, Serializable{
		public Z entry;
		public Node<Z> next;
	}

	public static class Tuple<G, H> {
		public G first;
		public H second;
		Tuple(G a, H b) {
			first = a;
			second = b;
		}
	}

	public Object clone() throws CloneNotSupportedException
	{
		return super.clone();
	}
//	private Node<T> first;
//	private Node<T> last;
	public boolean addUnique(Object o)
	{
//		return set.add(o);
		if (currentValue == o)
			return true;
		if (o == null)
			return false;
		if (currentValue == null)
			currentValue = o;
		int key = automicId();
		currentValue = key;
//		storage.get().add(new Tuple(key, currentValue));
		return true;
//		Node<T> i = first.next;
//		while(i != null)
//		{
//			if(i.entry == o)
//				return false;
//			i = i.next;
//		}
//		Node<T> n = new Node<T>();
//		n.entry = o;
//		last.next=n;
//		last = n;
//		return true;
	}
	public boolean addAll(LinkedList<T> o)
	{
		return addUnique(o.currentValue);

//		if (o.set != null) {
//			return set.addAll(o.set);
//		}
//		return false;
//		boolean added = false;
//		Node<T> i = o.getFirst();
//		while(i != null)
//		{
//			if(i.entry != null)
//				added |= addUnique(i.entry);
////			Node<T> n = new Node<T>();
////			n.entry = i.entry;
////			last.next=n;
////			last = n;
//			i = i.next;
//		}
//		return added;
	}
	public void addFast(T o)
	{
		addUnique(o);
//		set.add(o);
//		Node<T> n = new Node<T>();
//		n.entry = o;
//		if(first.next == null)
//		{
//			first.next=n;
//			last = n;
//		}
//		else
//		{
//			n.next = first.next;
//			first.next = n;
//		}
		
	}
	public void add(T o)
	{
		addUnique(o);
	}
	public Node<T> getFirst()
	{
		if (currentValue == null)
			return null;
		return new Node<T>();
//		return first.next;
	}
	public LinkedList()
	{
//		clear();
	}
//	@Override
//	public String toString() {
//	    StringBuilder ret = new StringBuilder();
//		ret.append("[");
//		Node<T> e = getFirst();
//		while(e != null)
//		{
//			ret.append(e.entry);
//			ret.append(",");
//			e = e.next;
//		}
//		ret.append("]");
//		return ret.toString();
//	}
	public void clear() {
		currentValue = null;
	}
}