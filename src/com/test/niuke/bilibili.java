package com.test.niuke;

import java.util.Scanner;

/**
 * @author xgp
 * @version 1.0
 * @date 2019/11/26 11:52
 */
public class bilibili {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s1 = sc.nextLine();
        String s2 = sc.nextLine();
        multi(s1,s2);
    }

    private static void multi(String s, String t){
        int[] t1 = solve(s);
        int[] t2 = solve(t);
        int[] k = new int[2];
        k[0] = t1[0]*t2[0]-t1[1]*t2[1];
        k[1] = t1[1]*t2[0]+t1[0]*t2[1];
        System.out.println(k[0]+"+"+k[1]+"i");
    }

    private static int[] solve(String s){
        String[] t = s.split("\\+");
        int[] res = new int[2];
        for (int i = 0; i < t.length; i++) {
            int sum = 0, f = 1;
            for (int j = 0; j < t[i].length(); j++) {
                if (t[i].charAt(j)=='-'&&j==0){
                    f = -1;
                    continue;
                }
                if(t[i].charAt(j)=='i')
                    break;
                sum += f*(t[i].charAt(j)-'0')+ sum*10;
            }
            res[i] = sum;
        }
        return res;
    }
}
