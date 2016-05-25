/**
 * 
 */
package com.flatironschool.javacs;

import java.util.List;
import java.util.Map;

/**
 * Implementation of a HashMap using a collection of MyLinearMap and
 * resizing when there are too many entries.
 * 
 * @author downey
 * @param <K>
 * @param <V>
 *
 */
public class MyHashMap<K, V> extends MyBetterMap<K, V> implements Map<K, V> {
	
	// average number of entries per map before we rehash
	protected static final double FACTOR = 1.0;

	@Override
	public V put(K key, V value) {
		V oldValue = super.put(key, value);
		
		//System.out.println("Put " + key + " in " + map + " size now " + map.size());
		
		// check if the number of elements per map exceeds the threshold
		if (size() > maps.size() * FACTOR) {
			rehash();
		}
		return oldValue;
	}

	/**
	 * Doubles the number of maps and rehashes the existing entries.
	 */
	/**
	 * 
	 */
	protected void rehash() {
        // TODO: fill this in.
		int size = maps.size();
		//System.out.println("Size !!!! " + size);
		//System.out.println("After make map ! " + maps.size());
		for(MyLinearMap<K,V> currentMap : maps) {
			List<Entry> entries = (List)currentMap.getEntries();
			//System.out.println("List size : " + entries.size());
			for(Entry cur : entries) {
				//System.out.println("Current Entry Key : " + cur.getKey() + " Current Key value : " + cur.getValue());
				super.put((K)cur.getKey(), (V)cur.getValue());
			}
		}


	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Map<String, Integer> map = new MyHashMap<String, Integer>();
		for (int i=0; i<10; i++) {
			map.put(new Integer(i).toString(), i);
		}
		Integer value = map.get("3");
		System.out.println(value);
	}
}
