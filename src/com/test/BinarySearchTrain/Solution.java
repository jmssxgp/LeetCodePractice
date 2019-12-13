package com.test.BinarySearchTrain;

import com.test.HashTrain.TreeNode;

import java.util.*;

/**
 * @author xgp
 * @version 1.0
 * @date 2019/10/14 20:00
 */
public class Solution {
    public int mySqrt(int x) {
        if(x==0||x==1)return x;
        int low = 0, high = 46340;
        while (low<high){
            int mid = low + (high - low+1) / 2;
            if (mid*mid>x){
                high = mid -1;
            }else if(mid*mid<x){
                low = mid ;
            }else {
                return mid;
            }
        }
        return low;
    }

    public int firstBadVersion(int n) {
        int low = 1, high = n;
        while (low<high){
            int mid = low+(high-low)/2;
            if (!isBadVersion(mid)){
                low = mid +1;
            }else {
                high = mid;
            }
        }
        if(low<=n&&isBadVersion(low))
            return low;
        return -1;
    }
    boolean isBadVersion(int version){
        return false;
    }

    public int findPeakElement(int[] nums) {
        int low = 0, high = nums.length;
        if (high==1)return 0;
        while (low<high){
            int mid = low + (high-low)/2;
            if (mid==0) {
                if (nums[mid]>nums[mid+1]){
                    return mid;
                }
            }
            else if (mid==nums.length-1){
                if (nums[mid]>nums[mid-1])
                    return mid;
                else
                    high = mid-1;
            }else {
                if (nums[mid]>nums[mid-1]&&nums[mid]>nums[mid+1])
                    return mid;
                else if (nums[mid]<nums[mid-1]){
                    high = mid;
                }else if (nums[mid]<nums[mid+1]){
                    low = mid+1;
                }
            }
        }
        return low;
    }


    public int[] searchRange(int[] nums, int target) {
        int low = 0, high = nums.length;    //二分法查找某一值在数组中的左边界
        int[] res = {-1,-1};
        if(high==0)return res;
        while (low<high){
            int mid = (low + high)/2;
            if (nums[mid]==target){
                high = mid;
            }else if (nums[mid]<target){
                low = mid+1;
            }else {
                high = mid;
            }
        }
        if (low==nums.length||nums[low]!=target)return res;
        else res[0] = low;
        low = 0;
        high = nums.length;                          //二分法查找某一值在数组中的右边界
        while (low<high){
            int mid = (low + high)/2;
            if (nums[mid]==target){
                low = mid+1;
            }else if (nums[mid]<target){
                low = mid+1;
            }else {
                high = mid;
            }
        }
        res[1] = low - 1;
        return res;
    }


    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> res = new ArrayList<>();
        int low = 0, high = arr.length;
        if (arr[0]>=x){
            for (int i = 0; i < k; i++) {
                res.add(arr[i]);
            }
            return res;
        }
        if (arr[arr.length-1]<=x){
            for (int i = 1; i <= k; i++) {
                res.add(arr[arr.length-i]);
            }
            return res;
        }

        while (low < high){
            int mid = (low + high) / 2;
            if (arr[mid]==x){
                low = mid;
                break;
            }else if (arr[mid]>x){
                high = mid;
            }else {
                low = mid + 1;
            }
        }
        int index = low;
        low = Math.max(0, index-k-1);
        high = Math.min(arr.length-1, index+k-1);
        while (high-low>k-1){
            if (low<0||(x-arr[low])<=(arr[high]-x)){
                high--;
            }else if (high>arr.length-1||(x-arr[low]>(arr[high]-x))){
                low++;
            }
        }
        for (int i = low; i <= high; i++) {
            res.add(arr[i]);
        }
        return res;
    }


    public double myPow(double x, int n) {
        long N = n;
        if (N < 0){
            N = -N;
            x = 1/x;
        }
        double res = 1, cursion = x;
        for (;N>0;N/=2){
            if (N%2==1){
                res*=cursion;
            }
            cursion*=cursion;
        }
        return res;
    }


    public char nextGreatestLetter(char[] letters, char target) {
        int len = letters.length, low = 0;
        while (low<len){
            int mid = (low + len)/2;
            if (letters[mid]>target){
                len = mid;
            }else{
                low = mid+1;
            }
        }
        if (low!=letters.length&&letters[low]!=target){
            return letters[low];
        }else if(low == letters.length){
            return letters[0];
        }else {
            if (low<letters.length-1){
                return letters[low+1];
            }else
                return letters[0];
        }
    }


    public int findMin(int[] nums) {
        int low = 0, high = nums.length;
        while (low<high){
            int mid = (low + high)/2;
            if (nums[mid]>nums[high]){
                low = mid+1;
            }else if(nums[mid]<nums[high]){
                high = mid;
            }else {
                high--;
            }
        }
        return nums[low];
    }


    public int findDuplicate(int[] nums) {
        int t = 0, s = 0;
        do{
            t = nums[t];
            s = nums[nums[s]];
        }while (s!=t);
        s = 0;
        while (s!=t){
            t = nums[t];
            s = nums[s];
        }
        return s;
    }

    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        int low = 0, high = nums[nums.length-1]-nums[0];
        while (low<high){
            System.out.println(low);
            int mid = low +(high-low)/2;
            int count = 0;
            int left = 0;
            for (int right = 0; right<nums.length; right++){
                while(nums[right]-nums[left]>mid)
                    left++;
                count+=right-left;
            }
            if (count>=k){
                high = mid;
            }else {
                low = mid+1;
            }
        }
        return low;
    }

    public int splitArray(int[] nums, int m) {
        long sum = 0, max = 0;
        for (int i:nums
        ) {
            max = Math.max(i, max);
            sum += i;
        }
        long low = max, high = sum, count = 0;
        if (m==nums.length)return (int)max;
        if (nums.length==1)return (int)sum;
        while (low<high){
            long mid = (low+high)/2;
            sum = 0;
            count = 1;
            for (int i: nums
            ) {
                sum+=i;
                if (sum>mid){
                    count++;
                    sum = i;
                }
            }
            if (count>m){
                low = mid+1;
            }else {
                high = mid;
            }
        }
        return (int)low;
    }
}
