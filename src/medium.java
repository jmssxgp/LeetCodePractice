import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class medium {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.uniquePaths(10,10));
    }
}
class Solution{
    Solution(){

    }
    /*
    leetcode 56
     */
    public int[][] merge(int[][] intervals) {
        if(intervals == null || intervals.length < 2)
            return intervals;
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        }); // 重写排序规则
        List<int[]> res = new ArrayList<>();
        int len = intervals.length;
        for (int i = 0; i < len; i++) {
            int[] tmp = new int[2];
            tmp[0] = intervals[i][0];
            tmp[1] = intervals[i][1];
            while (i < len - 1 && tmp[1] >= intervals[i + 1][0]) {
                tmp[1] = Math.max(tmp[1], intervals[i + 1][1]);
                i++;
            }
            res.add(tmp);
        }
        int[][]arr = new int[res.size()][2];
        res.toArray(arr);
        return arr;
    }

    /*
    leetcode 59
     */
    public int[][] generateMatrix(int n) {
        int t = 1;
        int[][] res = new int[n][n];
        for (int i = 0; i <= n / 2; i++) {
            for (int j = i; j < n - i; j++) {
                if(j!=n-i-1)
                res[i][j] = t++;
                else{
                    for (int k = i; k < n - i - 1; k++) {
                        res[k][j] = t++;
                    }
                }
            }
            for (int j = n - i - 1; j >= i; j--) {
                if (j != i) {
                    res[n-i-1][j] = t++;
                } else {
                    for (int k = n - i - 1; k > i; k--) {
                        res[k][j] = t++;
                    }
                }
            }
        }
        if(n%2==1)
            res[n/2][n/2]=t;
        return res;
    }

    /*
    leetcode 55
     */
    public boolean canJump(int[] nums) {
        int len = nums.length; //依次考虑前面的点是否能到现在的last点，若能到，则更新last。
        if(len==0||len==1)return true;
        int last = len - 1;
        for(int i = len-1; i>=0; i--){
            if(nums[i]+i>=last)
                last = i;
        }
        return last == 0;
    }

    /*
    leetcode  62
     */
    public int uniquePaths(int m, int n) {
        // 排列组合也可解，问题在于溢出和效率
        // 动态规划或者递归也可解，明显f(m,n) = f(m-1,n)+f(m,n-1)
        int[][] t = new int[m][n];
        for (int i = 0; i < m; i++) {
            t[i][0]=1;
        }
        for (int i = 0; i < n; i++) {
            t[0][i]=1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                t[i][j] = t[i-1][j]+t[i][j-1];
            }
        }
        return t[m-1][n-1];
    }
}
