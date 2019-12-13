import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author xgp
 * @version 1.0
 * @date 2019/9/3 16:55
 */
public class Easy {
    /**
     * leetcode 210
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] count = new int[numCourses];
        int[] res = new int[numCourses];
        boolean[] f = new boolean[numCourses];
        int now = 0;
        List<List<Integer>> list = new ArrayList<>();
        for (int i=0; i< numCourses;i++){
            list.add(new LinkedList<>());
        }
        for (int[] t: prerequisites){
            count[t[0]]++;
            list.get(t[1]).add(t[0]);
        }
        boolean flag = true;
        for (int i = 0; i < numCourses; i++) {
            flag = false;
            for (int j=0;j<numCourses;j++){
                if (count[j]==0&&!f[j]){
                    flag = true;
                    f[j] = true;
                    res[now++]=j;
                    List<Integer> l = list.get(j);
                    for (int k:l){
                        count[k]--;
                    }
                }
            }
        }
        if (flag)
            return res;
        else{
            return new int[0];
        }
    }

    /**
     * leetcode 216
     * @param k
     * @param n
     * @return
     */
    List<List<Integer>> list = new ArrayList<>();
    public List<List<Integer>> combinationSum3(int k, int n) {
        if (k*9<n)
            return list;
        aidCombinationSum3(k,n,1,0,new ArrayList<>());
        return list;
    }
    private void aidCombinationSum3(int k, int n, int i, int sum, List<Integer> l){
        if (sum==n&&l.size()==k){
            list.add(new ArrayList<>(l));
            return;
        }
        if (l.size()==k)
            return;
        for (int j=i;j<=9;j++){
            if (sum+j<=n){
                l.add(j);
                aidCombinationSum3(k,n,j+1,sum+j,l);
            }else {
                return;
            }
            l.remove(l.size()-1);
        }
    }

    /**
     * leetcode 220
     * @param nums
     * @param k
     * @param t
     * @return
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if(nums.length==0)return false;

        for (int i = 1; i <= k; i++) {
            for (int j=0; j<nums.length-i;j++){
                long sum;
                sum = (long)nums[i+j] - nums[j];
                if (Math.abs(sum)<=t)
                    return true;
            }
        }
        return false;
    }


    public int findMin(int[] nums) {
        int left = 0, right = nums.length-1,mid;
        if (nums.length==1)return nums[0];
        if (nums[right]>nums[0])
            return nums[0];
        while (right>=left){
            mid = left + (right - left)/2;
            if (nums[mid]>nums[mid+1]){
                return nums[mid+1];
            }
            if (nums[mid]<nums[mid-1])
                return nums[mid];
            if (nums[mid]>nums[0]){
                left=mid+1;
            }else {
                right=mid-1;
            }
        }
        return -1;
    }

    /**
     * 166
     * @param numerator
     * @param denominator
     * @return
     */
    public String fractionToDecimal(int numerator, int denominator) {
        StringBuilder sb = new StringBuilder();
        boolean flag = false;
        while (true){
            if (numerator>denominator){
                sb.append(numerator/denominator);
            }else if (!flag){
                sb.append('.');
                numerator*=10;
                sb.append(numerator/denominator);
                flag=true;
            }else {
                numerator*=10;
                sb.append(numerator/denominator);
            }
            if (numerator==0)break;
            if (sb.length()==100)break;
        }
        return sb.toString();
    }

    /**
     * 279
     * @param n
     * @return
     */
    public int numSquares(int n) {
        int[] dp = new int[n+1];
        for (int i=1;i<=n;i++){
            dp[i]=i;
            for (int j=1;j<i;j++){
                dp[i]=Math.min(dp[i],dp[i-j*j]+1);
            }
        }
        return dp[n];
    }


}
