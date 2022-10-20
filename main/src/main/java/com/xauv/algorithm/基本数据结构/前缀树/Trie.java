package com.xauv.algorithm.基本数据结构.前缀树;

public interface Trie<V> {

	int size();

	boolean isEmpty();

	void clear();

	V add(String str, V value);

	V remove(String str);

	boolean contains(String str);

	V get(String str);

	boolean startWith(String prefix);
}
