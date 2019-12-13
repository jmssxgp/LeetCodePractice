package com.test.niuke;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * @author xgp
 * @version 1.0
 * @date 2019/11/11 16:02
 */
public class Tencent {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m=sc.nextInt();
        char[][] t = new char[n][m];
        sc.nextLine();
        for (int i = 0; i < n; i++) {
            String s = sc.nextLine();
            t[i] = s.toCharArray();
        }
    }
    private static void theSumOfFisrtN(int n, int m){
        long t = n/(2*m);
        System.out.println(t*(m*m));
    }

    private static void theSumofWAndG(int[] t){
        Arrays.sort(t);
        int c = 1, sum = 0;
        for (int i = t.length-1; i >=0 ; i--) {
            sum+=c*t[i];
            c*=-1;
        }
        System.out.println(sum);
    }

    private static void howManyHeCanEat(int n, int m){
        int l = 0, h = m;
        while (l<h){
            int mid = (l+h)>>1;
            if (judge(n,mid,m)){
                l = mid +1;
            }else {
                h = mid;
            }
        }
        if (l==m)
            System.out.println(m);
        else
            System.out.println(l-1);
    }
    private static boolean judge(int n, int t, int m){
        int sum = t;
        for (int i = 1; i < n; i++) {
            sum+=(t+1)/2;
            t = (t+1)/2;
        }
        if (sum>m){
            return false;
        }else {
            return true;
        }
    }

    private static void howManyCount(int t, int n1, int m1, int n2, int m2){
        long sum = 0;
        for (int i = 0; i <= n1; i++) {
            int s = t - i * m1;
            if (s%m2==0&&s/m2<=n2){
                sum+=aidHowManyCount(n1, i)*aidHowManyCount(n2, s/m2);
                sum%=1000000007;
            }
        }
        System.out.println(sum);
    }
    private static int aidHowManyCount(int n, int m){
        long t = 1;
        for (int i = 0; i < m; i++) {
            t*=(n-i);
        }
        for (int i = m; i >=1 ; i--) {
            t/=i;
        }
        return (int)t%100000007;
    }


}
