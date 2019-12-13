package com.test.HashTrain;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

class MyHashSet {
    int[][] t;
    /** Initialize your data structure here. */
    public MyHashSet() {
        t = new int[101][100];
        for(int i = 0; i<101;i++){
            Arrays.fill(t[i], -1);
        }
    }
    
    public void add(int key) {
        int r = key%100;
        for (int i = 0; i < t[r].length; i++) {
            if (t[r][i]==key){
                break;
            }
            if (t[r][i]==-1){
                t[r][i]=key;
                break;
            }
        }
    }
    
    public void remove(int key) {
        int r = key%100;
        for (int i = 0; i < t[r].length; i++) {
            if (t[r][i]==key){
                t[r][i]=-1;
                break;
            }
        }
    }
    
    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        int r = key%100;
        for (int i = 0; i < t[r].length; i++) {
            if (t[r][i]==key){
                return true;
            }
        }
        return false;
    }
}