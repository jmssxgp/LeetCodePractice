package com.test.ByteDance;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xgp
 * @version 1.0
 * @date 2019/10/28 15:01
 */
public class solution {

    public String longestCommonPrefix(String[] strs) {
        String t = strs[0];
        for (String s: strs
             ) {
            while (s.indexOf(t)!=0){
                t = t.substring(0, t.length()-1);
            }
        }
        return t;
    }


    public boolean checkInclusion(String s1, String s2) {
        if (s1.length()>s2.length())return false;
        int[] t = new int[26];
        int[] t1 = new int[26];
        char[] chars1 = s2.toCharArray();
        char[] chars = s1.toCharArray();
        for (char c: chars
             ) {
            t[c-'a']++;
        }
        for (int i = 0; i <= chars1.length; i++) {
            if (i<chars.length){
                t1[chars1[i]-'a']++;
            }else {
                boolean f = false;
                for (int j = 0; j < 26; j++) {
                    if (t[j]!=t1[j]){
                        t1[chars1[i-chars.length]-'a']--;
                        if (i!=chars1.length)
                            t1[chars1[i]-'a']++;
                        else
                            return false;
                        f = true;
                        break;
                    }
                }
                if (!f)
                    return true;
            }
        }
        return false;
    }


    public String multiply(String num1, String num2) {
        if(num1.equals("0")||num2.equals("0")) return "0";
        char[] t1 = num1.toCharArray(), t2 = num2.toCharArray();
        int[] t = new int[t1.length+t2.length];
        for (int i = t1.length-1; i >=0 ; i--) {
            for (int j = t2.length-1; j >=0 ; j--) {
                int sum = t[i+j+1]+(t1[i]-'0')*(t2[j]-'0');
                t[i+j+1] += sum%10;
                t[i+j] = sum/10;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < t.length; i++) {
            System.out.println(t[i]);
            if (i==0&&t[i]==0)continue;
            sb.append(t[i]);
        }
        return sb.toString();
    }

    /**
     * 以grid数组自身为标记矩阵，将计算过的位置置为零。
     */
    int[][] g;
    int count;
    public int maxAreaOfIsland(int[][] grid) {
        if (grid.length==0)return 0;
        g = grid;
        int max =0;
        for (int i = 0; i < g.length; i++) {
            for (int j = 0; j < g[i].length; j++) {
                if (g[i][j]==1){
                    g[i][j]=0;
                    count = 1;
                    max = Math.max(max, DFS(i, j));
                }
            }
        }
        return max;
    }
    private int DFS(int x, int y){
        int[][] t = {{1,0},{-1,0},{0,1},{0,-1}};
        for (int[] i: t
        ) {
            int x1 = x + i[0];
            int y1 = y + i[1];
            if (x1>=0&&x1<g.length&&y1>=0&&y1<g[0].length){
                if (g[x1][y1]==1){
                    g[x1][y1]=0;
                    count++;
                    DFS(x1, y1);
                }
            }
        }
        return count;
    }


    public int longestConsecutive(int[] nums) {
        Arrays.sort(nums);
        if (nums.length<=1)return nums.length;
        int max = 1, l = 0, high = 1, count = 1;
        while (high<nums.length){
            if (nums[l]+1==nums[high]){
                count++;
                l = high;
                high++;
            }else if (nums[l]==nums[high]){
                high++;
            }else {
                l = high;
                high++;
                count = 1;
            }
            if (count>max){
                max = count;
            }
        }
        return max;
    }


    public String getPermutation(int n, int k) {
        char[] res = new char[n];
        int[] l = new int[n];
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            list.add(i);
        }
        l[0]=1;
        for (int i = 1; i < n; i++) {
            l[i] = l[i-1] * i;
        }
        k--;
        for (int i = 0; i < n; i++) {
            res[i] = Character.forDigit(list.remove(k/(l[n-i-1])), 10);
            k = k % (l[n-i-1]);
        }
        return new String(res);
    }


    //temp记录每次连通分量包括的节点，避免重复，每次递归结束返回一个连通分量
    //本题的目的即求图的连通分量个数
    //与之前不同的是，之前是以数组代替图，相当于网格图，而本题给的是邻接矩阵，指示本节点的临近节点
    public int findCircleNum(int[][] M) {
        int[] temp = new int[M.length];
        int count = 0;
        for (int i = 0; i < M.length; i++) {
            if (temp[i]==0){
                dfs(M, temp, i);
                count++;
            }
        }
        return count;
    }

    private void dfs(int[][] M, int[] temp, int i){
        for (int j = 0; j < M.length; j++) {
            if (M[i][j]==1&&temp[j]==0){
                temp[j] = 1;
                dfs(M, temp, j);
            }
        }
    }

    public int trap(int[] height) {
        if (height.length<=3)return 0;
        int[] max1 = new int[height.length];
        int[] max2 = new int[height.length];
        int max = 0, sum = 0;
        for (int i = 0; i < height.length; i++) {
            max = Math.max(height[i], max);
            max1[i] = max;
        }
        max = 0;
        for (int i = height.length-1; i >=0 ; i--) {
            max = Math.max(height[i], max);
            max2[i] = max;
        }
        for (int i = 0; i < height.length-1; i++) {
            sum += Math.min(max1[i], max2[i]) - height[i];
        }
        return sum;
    }


    public int maxSubArray(int[] nums) {
        int sum = 0, max = Integer.MIN_VALUE;
        for (int i: nums
             ) {
            if(sum<0){
                sum = i;
            }else {
                sum+=i;
            }
            max = Math.max(sum, max);
        }
        return max;
    }


    public int minimumTotal(List<List<Integer>> triangle) {
        int len = triangle.size();
        int[] t = new int[len+1];
        if (len==0)return 0;
        for (int i = len-1; i >=0 ; i--) {
            List<Integer> l = triangle.get(i);
            for (int j = 0; j < l.size(); j++) {
                t[j] = l.get(j) + Math.min(t[j], t[j+1]);
            }
        }
        return t[0];
    }


    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int max = 0;
        Arrays.fill(dp, 1);
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j]<nums[i]){
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }


    public int lengthOfLIS2(int[] nums) {
        int[] top = new int[nums.length];
        int piles = 0;
        for (int i = 0; i < nums.length; i++) {
            int poker = nums[i];
            int left = 0, right = piles;
            while (left < right) {
                int mid = (left+right)>>1;
                if (top[mid] > poker) {
                    right = mid;
                } else if (top[mid] < poker) {
                    left = mid + 1;
                }else {
                    right = mid;
                }
            }

            if (left == piles) {
                piles++;
            }
            top[left] = nums[i];
        }
        return piles;
    }



    public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, (int[]a, int[]b)->a[0]==b[0]?b[1]-a[1]:a[0]-b[0]);
        int[] top = new int[envelopes.length];
        int piles = 0;
        for (int i = 0; i < envelopes.length; i++) {
            int poker = envelopes[i][1];
            int left = 0, right = piles;
            while (left < right) {
                int mid = (left + right)>>1;
                if (top[mid]>=poker){
                    right = mid;
                }else {
                    left = mid + 1;
                }
            }
            if (left==piles){
                piles++;
            }
            top[left]=poker;
        }
        return piles;
    }
}
