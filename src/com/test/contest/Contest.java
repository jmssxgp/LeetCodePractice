package com.test.contest;

import java.util.*;

/**
 * @author xgp
 * @version 1.0
 * @date 2019/9/2 9:42
 */

public class Contest {
    /**
     * contest 152 D
     * @param words
     * @param puzzles
     * @return
     */
    public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
        List<Integer> l = new ArrayList<>();
        for (int i=0; i<puzzles.length;i++){
            l.add(0);
        }
        Map<Integer, Integer> map = new HashMap<>();
        int k;
        for (int i = 0; i < words.length; i++) {
            char[] word = words[i].toCharArray();
            k=0;
            for (int j=0;j<word.length;j++){
                k|=1<<word[j]-'a';
            }
            if (map.containsKey(k)){
                map.put(k,map.get(k)+1);
            }else {
                map.put(k,1);
            }
        }

        for (int i = 0; i < puzzles.length; i++) {
            char[] puzzle = puzzles[i].toCharArray();
            k=0;
            for (int j = 0; j < puzzle.length; j++) {
                k|=1<<puzzle[j]-'a';
            }
            for (int j = k; j!=0; j=j-1&k){
                int z = j>>>puzzle[0]-'a';
                if ((z&1)>0&&map.containsKey(j))
                    l.set(i,map.get(j)+l.get(i));
            }
        }
        return l;
    }

    /**
     * 153 C
     * @param arr
     * @return
     */
    public int maximumSum(int[] arr) {
        int ans = arr[0];
        int[] f = new int[arr.length];
        int[] g = new int[arr.length];
        f[0] = ans;
        g[0] = -1000000;
        for (int i=1; i<arr.length; i++){
            f[i] = Math.max(f[i-1]+arr[i], arr[i]);
            g[i] = Math.max(g[i-1]+arr[i],f[i-1]);
            ans = Math.max(ans, Math.max(f[i], g[i]));
        }
        return ans;
    }

    /**
     * contest155 B
     * @param n
     * @param a
     * @param b
     * @param c
     * @return
     */
    public int nthUglyNumber(int n, int a, int b, int c) {
        long ab = (long)a*b/gcd(a,b),bc=(long)b*c/gcd(b,c),ac=(long)a*c/gcd(a,c),abc=ab*c/gcd(ab,c),l=0,s,r=200000000,mid;
        while (l+1<r){
            mid = l+r>>1;
            s = mid/a+mid/b+mid/c-mid/ab-mid/bc-mid/ac+mid/abc;
            if (s<n)l=mid;
            else r=mid;
        }
        return (int)r;
    }

    private long gcd(long x, long y) {
        return y != 0 ? gcd(y, x % y) : x;
    }

    /**
     * contest155 C
     * @param s
     * @param pairs
     * @return
     */
    List<List<Integer>> list = new ArrayList<>();
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        char[]res = s.toCharArray();
        boolean[] flag = new boolean[s.length()];
        for (int i = 0; i < s.length(); i++) {
            list.add(new ArrayList<>());
        }
        for (List<Integer> l:pairs){
            list.get(l.get(0)).add(l.get(1));
            list.get(l.get(1)).add(l.get(0));
        }
        for (int i = 0; i < flag.length; i++) {
            List<Integer> l1 = new ArrayList<>();
            if (!flag[i]){
                l1.add(i);
                flag[i]=true;
                aidSmallestStringWithSwaps(i,l1,flag);
                StringBuilder sb = new StringBuilder();
                for (int j:l1){
                    //System.out.println(j);
                    sb.append(res[j]);

                }
                //System.out.println(i+"---"+sb);
                char[] t = sb.toString().toCharArray();
                Arrays.sort(t);
                Collections.sort(l1);
                for (int j=0;j<l1.size();j++){
                    res[l1.get(j)]=t[j];
                }
            }
        }
        return new String(res);

    }
    private void aidSmallestStringWithSwaps(int i, List<Integer> l, boolean[] flag){
        List<Integer> t = list.get(i);
        int len = t.size();
        for (int j = 0; j<len;j++){
            if (!flag[t.get(j)]){
                l.add(t.get(j));
                flag[t.get(j)]=true;
                aidSmallestStringWithSwaps(t.get(j),l,flag);
            }
        }
    }


    /**
     * contest double 11 B
     */
    public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
        Arrays.sort(slots1, Comparator.comparingInt((int[]a) -> a[0]));
        Arrays.sort(slots2, Comparator.comparingInt((int[]a) -> a[0]));
        List<Integer> res = new ArrayList<>();
        int i = 0, j = 0;
        while (i<slots1.length&&j<slots2.length){
            if (slots1[i][1]<slots2[j][0])i++;
            else if (slots2[j][1]<slots1[i][0])j++;
            else {
                int l = Math.max(slots1[i][0], slots2[j][0]);
                int h = Math.min(slots1[i][1], slots2[j][1]);
                int t = h-l;
                if (t>=duration){
                    res.add(l);
                    res.add(l+duration);
                    return res;
                }
                if (slots1[i][1]>slots2[j][1])j++;
                else i++;
            }
        }
        return res;
    }


    /**
     * contest double 11 C
     */
    public double probabilityOfHeads(double[] prob, int target) {
        int len = prob.length;
        double[][] dp = new double[1005][1005];
        double[] p = new double[1005];
        for (int i = 0; i < len; i++) {
            p[i+1] = prob[i];
        }
        dp[1][0] = 1 - p[1];
        dp[1][1] = p[1];
        for (int i=2; i<=len;i++){
            for (int j=0; j<=i;j++){
                dp[i][j]+=dp[i-1][j]*(1-p[i]);
                if (j!=0){
                    dp[i][j]+=dp[i-1][j-1]*p[i];
                }
            }
        }
        return dp[len][target];
    }


    /**
     * contest double 11 D
     */
    public int maximizeSweetness(int[] sweetness, int K) {
        int low=10000, high = 0;
        K+=1;
        for (int i: sweetness
             ) {
            low = low<i?low:i;
            high+=i;
        }
        while (low<high){
            int mid = (low+high)/2;
            System.out.println("low"+low +"high"+high+"mid"+mid);
            int count = 0,sum = 0;
            for (int i: sweetness
                 ) {
                sum+=i;
                if (sum>=mid){
                    count++;
                    sum=0;
                }
            }
            if (count>=K){  //组数太多，还可以提高
                low = mid;
            }else {
                high = mid-1;
            }
        }
        return low;
    }

    int[][] b;
    public int balancedString(String s) {
        char[] t = s.toCharArray();
        int[] s2int = new int[s.length()];
        b = new int[t.length+1][4];
        for (int i = 0; i < t.length; i++) {
            if (t[i]=='Q') s2int[i]=0;
            if (t[i]=='W') s2int[i]=1;
            if (t[i]=='E') s2int[i]=2;
            if (t[i]=='R') s2int[i]=3;
        }
        for (int i = 1; i <=t.length ; i++) {
            b[i] = Arrays.copyOf(b[i-1], 4);
            b[i][s2int[i-1]]++;
        }
        int low = 0, high = t.length-1, res = t.length;
        while (low<=high){
            int mid = (low+high)/2;
            //System.out.println(mid);
            if (aidBalancedString(mid, t.length)){
                res = mid;
                high = mid-1;
            }else {
                low = mid + 1;
            }
        }
        return res;

    }

    private boolean aidBalancedString(int m, int n){
        for (int i=1; i+m-1<=n; i++){
            int j = i+m-1;
            int[]t = Arrays.copyOf(b[n],4);
            for (int k = 0; k < 4; k++) {
                t[k]-= (b[j][k] - b[i-1][k]);
            }
            Arrays.sort(t);
            if (t[3]<=n/4)return true;
        }
        return false;
    }


    /**
     * contest double 12 A
     * @param arr
     * @return
     */
    public List<Integer> transformArray(int[] arr) {
        boolean flag = false;
        while (!flag){
            flag = true;
            int[] t = arr.clone();
            for (int i = 1; i < arr.length-1; i++) {
                if (arr[i]>arr[i-1]&&arr[i]>arr[i+1]){
                    t[i]--;
                    flag=false;
                }
                if (arr[i]<arr[i-1]&&arr[i]<arr[i+1]){
                    t[i]++;
                    flag = false;
                }
            }
            arr = t.clone();
        }
        List<Integer> res = new ArrayList<>();
        for (int i: arr
             ) {
            res.add(i);
        }
        return res;
    }

    /**
     * contest double 12 C
     */
    int max = 0, index = 0;
    public int treeDiameter(int[][] edges) {
        for (int i = 0; i < edges.length+1; i++) {
            list.add(new ArrayList<>());
        }
        for (int[] i: edges
             ) {
            list.get(i[0]).add(i[1]);
            list.get(i[1]).add(i[0]);
        }
        dfs(0, -1, 0);
        dfs(index, -1, 0);
        return max;
    }

    private void dfs(int u, int p, int sum){
        List<Integer> l = list.get(u);
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i) == p)
                continue;
            dfs(l.get(i), u, sum+1);
        }

        if (sum>max){
            max = sum;
            index = u;
        }
    }


    /**
     * contest double 12 D
     * @param arr
     * @return
     */
    public int minimumMoves(int[] arr) {
        int[][] dp = new int[105][105];
        for (int i=0; i<arr.length;i++){
            dp[i][i] = 1;
        }
        for (int i = 0; i < arr.length-1; i++) {
            if (arr[i]==arr[i+1]){
                dp[i][i+1] = 1;
            }else {
                dp[i][i+1] = 2;
            }
        }

        for (int len = 2; len < arr.length; len++) {
            for (int i = 0; i < arr.length-len; i++) {
                dp[i][i+len] = len+1;
                for (int j = i; j < i+len; j++) {
                    if (dp[i][i+len]>dp[i][j]+dp[j+1][i+len]){
                        dp[i][i+len]=dp[i][j]+dp[j+1][i+len];
                    }
                }
                if (arr[i]==arr[i+len]&&dp[i][i+len]>dp[i+1][i+len-1]){
                    dp[i][i+len] = dp[i+1][i+len-1];
                }
            }
        }
        return dp[0][arr.length-1];
    }


    public int minimumSwap(String s1, String s2) {
        int[] t = new int[2];
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        for (int i = 0; i < c1.length; i++) {
            if (c1[i]=='x'&&c2[i]=='y'){
                t[0]++;
            }
            if (c1[i]=='y'&&c2[i]=='x'){
                t[1]++;
            }
        }
        if ((t[0]+t[1])%2==1)
            return -1;
        else {
            return t[0]/2+t[1]/2+t[0]%2+t[1]%2;
        }
    }


    public int numberOfSubarrays(int[] nums, int k) {
        int count=0, sum = 0, left = 0, right = -1, low = 0,high = 0;
        while (right<nums.length-1&&count<k){
            right++;
            if ((nums[right]&1)==1){
                count++;
            }
        }
        if (count<k)return 0;
        while (right<nums.length){
            while ((nums[left]&1)!=1){
                low++;
                left++;
            }
            while (right<nums.length&&(nums[right+1]&1)!=1){
                right++;
                high++;
            }
            sum+=low*high+low+high+1;
            left++;
            if (high!=0)
                right++;
            low =0;
            high=0;
        }
        return sum;
    }



    public String minRemoveToMakeValid(String s) {
        char[] t = s.toCharArray();
        int count = 0;
        List<Integer> l = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (char c: t
             ) {
            if (c=='('){
                count++;
                sb.append(c);
                l.add(sb.length()-1);
            }else if (c==')'){
                if (count==0){
                    continue;
                }
                sb.append(c);
                l.remove(l.size()-1);
                count--;
            }else {
                sb.append(c);
            }
        }
        if (count>0){
            for (int i = sb.length()-1; i >= 0; i--) {
                sb.deleteCharAt(l.get(i));
            }
        }
        return sb.toString();
    }


    public boolean isGoodArray(int[] nums) {
        int t = nums[0];
        for (int i: nums
             ) {
            t = gcd(t, i);
        }
        if (t==1)return true;
        else return false;
    }
    private int gcd(int a, int b){
        return b==0?a:gcd(b, a%b);
    }


    public int maxSumDivThree(int[] nums) {
        int sum = 0;
        int[] t = new int[4], s = new int[4];
        Arrays.fill(t, Integer.MAX_VALUE);
        for (int i = 0; i <nums.length; i++) {
            sum+=nums[i];
            if (nums[i]%3==1){
                if (nums[i]<t[0]){
                    t[1] = t[0];
                    t[0] = nums[i];
                }else if (nums[i]<t[1]){
                    t[1] = nums[i];
                }
            }else if (nums[i]%3==2){
                if (nums[i]<t[2]){
                    t[3] = t[2];
                    t[2] = nums[i];
                }else if (nums[i]<t[3]){
                    t[3] = nums[i];
                }
            }
        }
        if (sum%3==0)
            return sum;
        for (int i = 0; i < t.length; i++) {
            System.out.println(t[i]);
            if (t[i]==Integer.MAX_VALUE){
                t[i] = 0;
            }
        }
        s[0] = sum -t[0];
        s[1] = t[0] - t[1];
        s[2] = sum -t[2];
        s[3] = t[2] - t[3];
        Arrays.sort(s);
        for (int i = 3; i >=0 ; i--) {
            if (s[i]%3==0)
                return s[i];
        }
        return 0;
    }

    /**
     * contest 164 a
     * @param points
     * @return
     */
    public int minTimeToVisitAllPoints(int[][] points) {
        if(points.length<=1)return 0;
        int count = 0;
        for (int i = 1; i < points.length; i++) {
            int x = Math.abs(points[i][0]-points[i-1][0]);
            int y = Math.abs(points[i][1]-points[i-1][1]);
            count += Math.max(x,y);
        }
        return count;
    }

    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        Arrays.sort(products);
        List<List<String>> list = new ArrayList<>();
        int len = searchWord.length();
        for (int i = 0; i < len; i++) {
            String s = searchWord.substring(0,i+1);
            List<String> l = new ArrayList<>();
            for (int j = 0; j < products.length; j++) {
                if (products[j].indexOf(s)==0){
                    l.add(products[j]);
                }
                if (l.size()==3){
                    break;
                }
            }
            list.add(l);
        }
        return list;
    }

    public int numWays(int steps, int arrLen) {
        int l = Math.min(steps, arrLen)+1;
        long[] dp = new long[l];
        dp[0]=1;
        for (int i = 1; i <= steps; i++) {
            long[] t = dp.clone();
            Arrays.fill(dp, 0);
            for (int j = 0; j < l; j++) {
                if (j-1>=0){
                    dp[j]+=t[j-1];
                }
                dp[j]+=t[j];
                if (j+1<l){
                    dp[j]+=t[j+1];
                }
                dp[j]%=1000000007;
            }
        }
        return (int)dp[0];
    }
}
