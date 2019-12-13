package com.test.HashTrain;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

class RandomizedSet {
    Map<Integer,Integer> indexMap;
    Map<Integer,Integer> valMap;
    int size;
    /** Initialize your data structure here. */
    public RandomizedSet() {
        indexMap = new HashMap<>();
        valMap = new HashMap<>();
        size = 0;
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if(indexMap.containsKey(val))return false;
        indexMap.put(val, ++size);
        valMap.put(size, val);
        return true;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (!indexMap.containsKey(val))return false;
        int cur = indexMap.get(val);
        if (cur == size){
            indexMap.remove(val);
            valMap.remove(size--);
        }else {
            indexMap.remove(val);
            valMap.remove(cur);
            int lastVal = valMap.get(size);
            valMap.put(cur, lastVal);
            valMap.remove(size--);
            indexMap.put(lastVal,cur);
        }
        return true;
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        int rand = new Random().nextInt(size)+1;
        return valMap.get(rand);
    }
}
