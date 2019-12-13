package com.test.array;

import java.util.Arrays;

/**
 * @author xgp
 * @version 1.0
 * @date 2019/9/27 16:39
 */
public class solution {
    /**
     * 探索,中心索引
     * @param nums
     * @return
     */
    public int pivotIndex(int[] nums) {
        int sum =0;
        int now = 0;
        for (int i:nums
             ) {
            sum+=i;
        }
        for (int i=0;i<nums.length;i++){
            sum-=nums[i];
            if (i==0){
                if (sum==0)
                    return 0;
            }else {
                now+=nums[i-1];
                if (i==nums.length-1){
                    if (now==0)
                        return i;
                }else {
                    if (now==sum){
                        return i;
                    }
                }
            }
        }
        return -1;
    }

    public int dominantIndex(int[] nums) {
        int[] index = new int[100];
        for (int i = 0; i < nums.length; i++) {
            index[nums[i]]=i;
        }
        Arrays.sort(nums);
        if (nums[nums.length-1]<nums[nums.length-2]*2)
            return -1;
        else return index[nums[nums.length-1]];
    }

    public int[] plusOne(int[] digits) {
        int carry=0;
        for (int i=digits.length-1;i>=0;i--){
            if (i==digits.length-1){
                carry = (digits[i]+1)/10;
                digits[i]=(digits[i]+1)%10;
            }else {
                int z = digits[i]+carry;
                carry = z/10;
                digits[i]=z%10;
                if (carry==0)
                    break;
            }
        }
        if (carry==0)
            return digits;
        else {
            int[] t = new int[digits.length+1];
            t[0]=1;
            System.arraycopy(digits,0,t,1,digits.length);
            return t;
        }
    }
}
