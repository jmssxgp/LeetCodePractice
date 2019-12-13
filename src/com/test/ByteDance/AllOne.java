package com.test.ByteDance;

import java.util.HashMap;
import java.util.Map;

class AllOne {
    Map<String, Integer> map;
    /** Initialize your data structure here. */
    public AllOne() {
        map = new HashMap<>();
    }
    
    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    public void inc(String key) {
        map.put(key, map.getOrDefault(key, 0)+1);
    }
    
    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
    public void dec(String key) {
        int t = map.getOrDefault(key, -1);
        if (t==1){
            map.remove(key);
        }
        if (t>=2){
            map.put(key, t-1);
        }
    }
    
    /** Returns one of the keys with maximal value. */
    public String getMaxKey() {
        int max = 0;
        String res = "";
        for (Map.Entry<String, Integer> entry: map.entrySet()
             ) {
            if (entry.getValue()>max){
                max = entry.getValue();
                res = entry.getKey();
            }
        }
        return res;
    }
    
    /** Returns one of the keys with Minimal value. */
    public String getMinKey() {
        int min = Integer.MAX_VALUE;
        String res = "";
        for (Map.Entry<String, Integer> entry: map.entrySet()
        ) {
            if (entry.getValue()<min){
                min = entry.getValue();
                res = entry.getKey();
            }
        }
        return res;
    }


    public boolean validUtf8(int[] data) {
        if (data.length==0)return false;
        for (int i = 0; i < data.length; i++) {
            if ((data[i]>>>7)==0)
                continue;
            if ((data[i]>>>5)==6){
                i++;
                if (i>=data.length)
                    return false;
                if ((data[i]>>>6)!=2)
                    return false;
            }
            else if ((data[i]>>>4)==14){
                for (int j = 0; j < 2; j++) {
                    i++;
                    if (i>=data.length)
                        return false;
                    if ((data[i]>>>6)!=2)
                        return false;
                }
            }
            else if ((data[i]>>>3)==30){
                for (int j = 0; j < 3; j++) {
                    i++;
                    if (i>=data.length)
                        return false;
                    if ((data[i]>>>6)!=2)
                        return false;
                }
            }else
                return false;
        }
        return true;
    }
}