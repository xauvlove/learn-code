package com.xauv.algorithm.基本数据结构.前缀树;

import java.util.Set;

public interface Trie<V> {

	int size();

	boolean isEmpty();

	void clear();

	V add(String str, V value);

	V remove(String str);

	boolean contains(String str);

	V get(String str);

	boolean startWith(String prefix);

	Set<V> prefixValues(String prefix);
}
