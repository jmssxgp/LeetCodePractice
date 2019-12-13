package com.test.HashTrain;

import java.util.*;
import java.util.stream.Stream;

/**
 * @author xgp
 * @version 1.0
 * @date 2019/10/11 17:16
 */
public class Solution {
    int[] hash = {2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97,101};
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<Integer, Integer> map = new HashMap<>();
        List<List<String>> res = new ArrayList<>();
        for (String s: strs
             ) {
            int h = aidHash(s);
            if (!map.containsKey(h)){
                List<String> l = new ArrayList<>();
                l.add(s);
                res.add(l);
                map.put(h, res.size()-1);
            }else {
                res.get(map.get(h)).add(s);
            }
        }
        return res;
    }

    private int aidHash(String s){
        char[] t = s.toCharArray();
        int res = 1;
        for (char i: t
             ) {
            res = (res*hash[i-'a']);  //单词长度过长时可能溢出，可以选择取余等操作
        }
        return res;
    }

    /**
     * 将路径序列化为字符串，使用map去重
     * String也可以作为map的键，应该是equals的作用。
     * @param root
     * @return
     */
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> res = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        if (root==null)return res;
        aidFind(root, res, map);
        return res;
    }
    private String aidFind(TreeNode root, List<TreeNode> res, Map<String, Integer> map){
        if (root==null)return "";

        String r = root.val + aidFind(root.left, res, map)+","+aidFind(root.right,res,map);
        if (map.get(r)!=null&&map.get(r)==1){
            res.add(root);
        }
        map.put(r, map.getOrDefault(r,0)+1);
        return r;
    }

    public int numJewelsInStones(String J, String S) {
        Set<Character> set = new HashSet<>();
        char[]t = J.toCharArray();
        int res = 0;
        for (char i:t
             ) {
            set.add(i);
        }
        char[]c = S.toCharArray();
        for (char i:c
             ) {
            if (set.contains(i))
                res++;
        }
        return res;
    }

    public int lengthOfLongestSubstring(String s) {
        int[] flag = new int[300];
        Arrays.fill(flag, -1);
        int max = 0, count=0;
        char[] t = s.toCharArray();
        for (int i=0; i<t.length;i++
             ) {
            if (flag[t[i]]==-1){
                count++;
                flag[t[i]]= i;
            }else {
                i = flag[t[i]];
                if (count>max){
                    max = count;
                }
                count = 0;
                Arrays.fill(flag, -1);
            }
        }
        if (count>max){
            max = count;
        }
        return max;
    }


    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer, Integer> map = new HashMap<>();
        int count=0;
        for (int a:A){
            for (int b: B
                 ) {
                map.put(a+b, map.getOrDefault(a+b, 0)+1);
            }
        }
        for (int c:C
             ) {
            for (int d:D
                 ) {
                count+=map.getOrDefault(-c-d, 0);
            }
        }
        return count;
    }

    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map= new HashMap<>();
        List<Integer> res = new ArrayList<>();
        for (int i: nums
             ) {
            map.put(i, map.getOrDefault(i, 0)+1);
        }
        List<Map.Entry<Integer,Integer>> list = new ArrayList<Map.Entry<Integer,Integer>>(map.entrySet());
        list.sort(Comparator.comparing(o -> -o.getValue()));
        for (Map.Entry<Integer,Integer> entry: list
             ) {
            res.add(entry.getKey());
            if (res.size()==k){
                break;
            }
        }
        return res;
    }


}
