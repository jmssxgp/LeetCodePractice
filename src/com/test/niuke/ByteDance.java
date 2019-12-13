package com.test.niuke;

import java.util.*;

/**
 * @author xgp
 * @version 1.0
 * @date 2019/11/6 16:37
 */
public class ByteDance {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] t = new int[n+1];
        for (int i = 1; i <= n; i++) {
            t[i] = sc.nextInt();
        }
        howManySteps(t);
    }

    private static String solveStrings(String s){
        char[] t = s.toCharArray();
        int[] count = new int[t.length];
        count[0] =1;
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < t.length; i++) {
            if (t[i]==t[i-1]){
                count[i] = count[i-1]+1;
            }else {
                count[i] = 1;
            }
        }
        boolean flag = false;
        for (int i = 0; i < t.length; i++) {
            if (count[i]<2){
                sb.append(t[i]);
                if (i>0&&count[i-1]==1&&flag){
                    flag = false;
                }
            }
            if (count[i]==2){
                if (!flag){
                    flag = true;
                    sb.append(t[i]);
                }else {
                    flag = false;
                }
            }
        }
        return sb.toString();
    }


    private static int countForSpy(int[]t, int N, int D){
        int sum = 0;
        for (int i = 0; i < t.length-2; i++) {
            int max = t[i]+D;
            int n = search(t, max)-i;
            if (n >= 2) {
                long s = (long)n*(n-1)/2;
                sum+=s%99997867;
                sum%=99997867;
            }
        }
        return sum;
    }
    private static int search(int[] t, int target){
        int low = 0, high = t.length-1;
        while (low<=high){
            int mid = (low+high)>>1;
            //System.out.println(mid);
            if (t[mid]<=target){
                low = mid+1;
            }else {
                high = mid -1;
            }
        }
        return low-1;
    }

    static HashMap<String, Integer> map = new HashMap<>();
    static int max = 0;
    private static void countMomi(int[][] t){
        HashMap<String, Integer> hashMap = new HashMap<>();
        for (int []i : t
             ) {
            String key = i[0]+"*"+i[1];
            hashMap.put(key,map.getOrDefault(key, 0)+1);
            max = Math.max(max, hashMap.get(key));
        }
        map.clear();
        map.putAll(hashMap);
    }


    private static void maxPoint(int[][] t){
        Arrays.sort(t,Comparator.comparingInt((int[] a)->a[0]));
        List<int[]> l = new ArrayList<>();
        int max = -100;
        for (int i = t.length-1; i >= 0; i--) {
            if (t[i][1]>max){
                max = t[i][1];
                l.add(t[i]);
            }
        }
        for (int i = l.size()-1; i >= 0; i--) {
            int[] s = l.get(i);
            System.out.println(s[0]+" "+s[1]);
        }
    }

    private static void maxProduct(int[] t){
        int ans = 0;
        for (int i = 100; i >=1 ; i--) {
            int min = 101, sum = 0;
            for (int j = 0; j < t.length; j++) {
                if (t[j]<i){
                    ans = Math.max(ans, sum*min);
                    min = 101;
                    sum = 0;
                }else {
                    sum += t[j];
                    min = Math.min(min, t[j]);
                }
            }
        }
        System.out.println(ans);
    }


    private static void howCanWin(int n, int k, int d1, int d2){
        int[][] t = {{1,1},{1,-1},{-1,-1},{-1,1}};
        int z, x, y, h, s, max;
        boolean flag = false;
        for (int[] i: t
             ) {
            z = k + i[0]*d1+ i[1]*d2;
            if (z%3==0){
                y = z/3;
                x = y - i[0]*d1;
                h = y - i[1]*d2;
                max = Math.max(Math.max(y, x), h);
                s = n-k-(max*3-x-y-h);
                if (x>=0&&y>=0&&h>=0){
                    if (s>=0&&s%3==0){
                        flag = true;
                    }
                }
            }
        }
        if (flag){
            System.out.println("yes");
        }else {
            System.out.println("no");
        }
    }

    static int[] count = new int[50004];
    private static boolean aidSearchMaxLength(int step, int n, int m){
        for (int i = 1; i+step < n; i++) {
            if (m>=step+1-(count[i+step]-count[i-1]))return true;
            if (m>=count[i+step]-count[i-1])return true;
        }
        return false;
    }
    private static void searchMaxLength(String s, int m, int n){
        int sum = 0;
        char[] t = s.toCharArray();
        for (int i = 0; i < t.length; i++) {
            if (t[i]=='a'){
                count[i] = ++sum;
            }else {
                count[i] = sum;
            }
        }

        int l =0, r = n-1;
        while (l < r) {
            int mid = (l+r)>>1;
            if (aidSearchMaxLength(mid,n,m)){
                l=mid+1;
            }else {
                r = mid;
            }
        }
        System.out.println(Math.max(0, l));
    }

    private static void howManySteps(int[] t){
        long[] dp = new long[t.length+1];
        for (int i = 2; i < dp.length; i++) {
            dp[i] = (2*dp[i-1] - dp[t[i-1]] + 2)%1000000007;
        }
        System.out.println(dp[dp.length-1]<0?dp[dp.length-1]+1000000007:dp[dp.length-1]);
    }

}
