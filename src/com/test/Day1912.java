package com.test;

import com.test.HashTrain.TreeNode;

import java.util.*;

/**
 * @author xgp
 * @version 1.0
 * @date 2019/12/4 10:17
 */
public class Day1912 {

    public int rob(int[] nums) {
        if (nums.length==0)return 0;
        if (nums.length==1)return nums[0];
        return Math.max(myRob(Arrays.copyOfRange(nums,0, nums.length-1)),
                myRob(Arrays.copyOfRange(nums,1,nums.length)));
    }
    private int myRob(int[] nums){
        int max = 0, preMax = 0;
        for (int num: nums){
            int t = max;
            max = Math.max(num + preMax, max);
            preMax = t;
        }
        return max;
    }

    public List<String> summaryRanges(int[] nums) {
        int start = -1, cur = 0;
        List<String> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (start==-1){
                start = nums[i];
                cur = start;
            }else {
                if (nums[i]==cur+1){
                    cur++;
                }else {
                    if (cur == start){
                        res.add(""+cur);
                    }else {
                        res.add(start+"->"+cur);
                    }
                    cur = nums[i];
                    start = nums[i];
                }
            }
        }
        if (cur!=-1 && cur == start){
            res.add(""+cur);
        }else if (cur!=-1){
            res.add(start+"->"+cur);
        }
        return res;
    }

    int val = 0;
    int count =0;
    public int kthSmallest(TreeNode root, int k) {
        inorder(root, k);
        return val;
    }
    private void inorder(TreeNode root, int k){
        if (root==null){
            return ;
        }
        inorder(root.left, k);
        if (k== ++count) {
            val = root.val;
        }else if (count>k){
            return;
        }
        inorder(root.right, k);
    }

    public int[] productExceptSelf(int[] nums) {
        int[] t = new int[nums.length];
        int k = 1;
        for (int i = 0; i < nums.length; i++) {
            t[i] = k;
            k *= nums[i];
        }
        k = 1;
        for (int i = t.length-1; i >=0 ; i--) {
            t[i] *= k;
            k *= nums[i];
        }
        return t;
    }

    public List<Integer> diffWaysToCompute(String input) {
        return partition(input);
    }

    private List<Integer> partition(String input){
        List<Integer> res = new ArrayList<>();
        if (!input.contains("-")&&!input.contains("+")&&!input.contains("*")){
            res.add(Integer.parseInt(input));
            return res;
        }

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i)=='-'||input.charAt(i)=='+'||input.charAt(i)=='*'){
                for (int left : partition(input.substring(0, i))){
                    for (int right : partition(input.substring(i+1))){
                        if (input.charAt(i)=='-'){
                            res.add(left-right);
                        }
                        if (input.charAt(i)=='+'){
                            res.add(left+right);
                        }
                        if (input.charAt(i)=='*'){
                            res.add(left*right);
                        }
                    }
                }
            }
        }
        return res;
    }


    public int[] singleNumber(int[] nums) {
        int t = nums[0];
        int[] res = new int[2];
        for (int i = 1; i < nums.length; i++) {
            t ^= nums[i];
        }
        t &= -t;
        for (int i:nums) {
            if ((t & i) ==0){
                res[0]^=i;
            }else {
                res[1]^=i;
            }
        }
        return res;
    }

    public int nthUglyNumber(int n) {
        int[] dp = new int[n];
        dp[0] = 1;
        int p2 = 0, p3 = 0, p5 = 0, count = 1;
        while (count<n){
            int min = Math.min(dp[p2]*2, Math.min(dp[p3]*3, dp[p5]*5));
            System.out.println(min);
            dp[count++] = min;
            if (min == dp[p2]*2){
                p2++;
            }
            if (min == dp[p3]*3){
                p3++;
            }
            if (min == dp[p5]*5){
                p5++;
            }
        }
        return dp[n-1];
    }


    /**
     * leetcode 309
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int free = 0, hold = Integer.MIN_VALUE, cd = 0;
        for (int i: prices
             ) {
            int preCd = cd;
            cd = hold + i;
            hold = Math.max(free - i, hold);
            free = Math.max(preCd, free);
        }
        return Math.max(free, cd);
    }

}












