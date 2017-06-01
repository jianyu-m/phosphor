package edu.columbia.cs.psl.phosphor.struct;


import edu.columbia.cs.psl.phosphor.runtime.Taint;

import java.io.Serializable;

public class LinkedList<T> implements Cloneable, Serializable {
	public static class Node<Z> implements Cloneable, Serializable{
		public Z entry;
		public Node<Z> next;
	}
	public Object clone() throws CloneNotSupportedException
	{
		return super.clone();
	}
	private Node<T> first;
	private Node<T> last;
	public boolean addUnique(T o)
	{
		int step = 0;
		Node<T> i = first.next;
		while(i != null)
		{
			step++;
			if(i.entry == o) {
				Taint.incStep(step);
				return false;
			}
			i = i.next;
		}
		Node<T> n = new Node<T>();
		n.entry = o;
		last.next=n;
		last = n;
		Taint.incStep(step);
		return true;
	}
	public boolean addAll(LinkedList<T> o)
	{
		boolean added = false;
		Node<T> i = o.getFirst();
		while(i != null)
		{
			if(i.entry != null)
				added |= addUnique(i.entry);
//			Node<T> n = new Node<T>();
//			n.entry = i.entry;
//			last.next=n;
//			last = n;
			i = i.next;
		}
		return added;
	}
	public void addFast(T o)
	{
		Node<T> n = new Node<T>();
		n.entry = o;
		if(first.next == null)
		{
			first.next=n;
			last = n;
		}
		else
		{
			n.next = first.next;
			first.next = n;
		}
		
	}
	public void add(T o)
	{
		addUnique(o);
	}
	public Node<T> getFirst()
	{
		return first.next;
	}
	public LinkedList()
	{
		clear();
	}
	@Override
	public String toString() {
	    StringBuilder ret = new StringBuilder();
		ret.append("[");
		Node<T> e = getFirst();
		while(e != null)
		{
			ret.append(e.entry);
			ret.append(",");
			e = e.next;
		}
		ret.append("]");
		return ret.toString();
	}
	public void clear() {
		first = new Node<T>();
		last = first;
	}
}