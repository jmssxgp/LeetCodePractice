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
    List<List<Integer>> list = new ArrayList<>();
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

    /*
    leetcode 63
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int r = obstacleGrid.length, c = obstacleGrid[0].length;
        int[][] t = new int[r][c];
        boolean f = false;
        for (int i = 0; i < r; i++) {
            if(obstacleGrid[i][0]==1)
                f = true;
            if(f)
                t[i][0]=0;
            else
                t[i][0]=1;
        }
        f = false;
        for (int i = 0; i < c; i++) {
            if(obstacleGrid[0][i]==1)
                f = true;
            if(f)
                t[0][i]=0;
            else
                t[0][i]=1;
        }
        for (int i = 1; i < r; i++) {
            for (int j = 1; j < c; j++) {
                if (obstacleGrid[i][j]==1)
                    t[i][j] = 0;
                else if(obstacleGrid[i-1][j]!=1&&obstacleGrid[i][j-1]!=1)
                t[i][j] = t[i-1][j]+t[i][j-1];
                else if(obstacleGrid[i-1][j]!=1){
                    t[i][j] = t[i-1][j];
                }else{
                    t[i][j] =t[i][j-1];
                }
            }
        }
        return t[r-1][c-1];
    }

    /*
    leetcode 64
     */
    public int minPathSum(int[][] grid) {
        int r = grid.length, c = grid[0].length;
        int[][] res = new int[r][c];
        res[0][0] = grid[0][0];
        for (int i = 1; i < r; i++) {
            res[i][0] = res[i-1][0] + grid[i][0];
        }
        for (int i = 1; i < c; i++) {
            res[0][i] = res[0][i-1] + grid[0][i];
        }
        for (int i = 1; i < r; i++) {
            for (int j = 1; j < c; j++) {
                res[i][j] = Math.min(res[i-1][j], res[i][j-1])+grid[i][j];
            }
        }
        return res[r-1][c-1];
    }

    /*
    leetcode 73
     */
    public void setZeroes(int[][] matrix) {
        int r = matrix.length, c = matrix[0].length;
        int[] r_0 = new int[r], c_0 = new int[c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if(matrix[i][j]==0){
                    r_0[i]=1;
                    c_0[j]=1;
                }
            }
        }
        for (int i = 0; i < r; i++) {
            if(r_0[i]==1){
                for (int j = 0; j < c; j++) {
                    matrix[i][j]=0;
                }
            }
        }
        for (int i = 0; i < c; i++) {
            if(c_0[i]==1){
                for (int j = 0; j < r; j++) {
                    matrix[j][i]=0;
                }
            }
        }
    }

    /*
    leetcode 74
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int r = matrix.length;
        if (r==0)return false;
        int c = matrix[0].length;
        if (r==1)return search(matrix[0], target);
        else{
            for(int i = 0; i < r-1; i++){
                if(matrix[i+1][0]>target){
                    return search(matrix[i], target);
                }
            }
        }
        if(matrix[r-1][0]<target)
            return search(matrix[r-1], target);
        else return false;
    }
    public boolean search(int[] t, int target){
        int high = t.length-1, low = 0;
        while(low<=high){
            int mid = (low + high) / 2;
            if(t[mid]==target)return true;
            else if(t[mid]<target){
                low = mid+1;
            }else{
                high = mid-1;
            }
        }
        return false;
    }

    /*
    leetcode 75
     */
    public void sortColors(int[] nums) {

    }

    /*
    leetcode 77
     */
    public List<List<Integer>> combine(int n, int k) {
        aid(new ArrayList<>(),n, k, 0);
        return list;
    }

    private void aid(List<Integer> l, int n, int k, int t) {
        if(l.size()==k){
            list.add(l);
            return;
        }
        //i的临界值不难判断即，list中第i个位置的最大取值，比如n=4,k=2,第一个值最大取值为3；
        for (int i = (l.size()==0?1:l.get(l.size()-1)+1); i <= n - k + 1 + t; i++) {
            l.add(i);
            aid(l, n, k, t+1);
            l.remove(l.size()-1);
            //若l.size()==k，返回后，将添加的最后一个值移除，避免重复添加到同一个
            //list中，这种情况也可以通过每次new一个新的list解决，但是消耗内存较大
        }
    }

    /*
    leetcode 78
     */
    public List<List<Integer>> subsets(int[] nums) {
        for (int i = 0; i <= nums.length; i++) {
            aid(new ArrayList<>(), nums.length, i, nums, 0);
        }
        return list;
    }
    private void aid(List<Integer> l, int n, int k, int[] nums, int t){
        if(l.size()==k){
            list.add(l);
            return;
        }
        for (int i = (l.size()==0?1:t); i <= n; i++) {
            List<Integer> l1 = new ArrayList(l);
            l1.add(nums[i-1]);
            aid(l1, n, k, nums, i+1);
            //通过每次new一个新的list解决，但是消耗内存较大
        }
    }

    /*
    leetcode 80
     */
    public int removeDuplicates(int[] nums) {
        int len = nums.length, index = 0, count = 0;
        for (int i = 0; i < len; i++) {
            if(nums[i]==nums[index]){
                count++;
            }else if((nums[i]!=nums[index]||i==len-1)&&count<2){
                index+=count;
                nums[index] = nums[i];
                count = 1;
            }else if((nums[i]!=nums[index]||i==len-1)&&count>=2){
                index += 2;
                nums[index] = nums[i];
                nums[index+1] = nums[i];
                count = 1;
            }
        }
        if(nums[len-1]!=nums[index-1]&&count>=2)
        return  index+2;
        else if(nums[len-1]!=nums[index-1]&&count<2)
            return index+1;
        else
            return index;
    }
}






