import java.util.*;

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
    leetcode 75
     */
    public void sortColors(int[] nums) {
        int high = nums.length-1, low = 0;
        for (int i = 0; i <= high; i++) {
            if(nums[i]==0&&i!=low){
                nums[i] = nums[low];
                nums[low++] = 0;
                i--;
            }else if(nums[i]==2){
                nums[i] = nums[high];
                nums[high--] = 2;
                i--;

            }
        }
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
        int i = 0;
        for (int n : nums) {
            if (i<2||n>nums[i-2]){ //n与i-2比较，保证至多两个相同，即使中间有只有一个的也能保证n一定大于i-2
                nums[i++] = n;
            }
        }
        return i;
    }

    /*
    leetcode 79
     */
    public boolean exist(char[][] board, String word) {      //dfs
        char[] w = word.toCharArray();
        if (board.length==0)
            return false;
        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j]==w[0])
                    if(search(board,i,j,w,0,visited))
                        return true;
            }
        }
        return false;
    }
    private boolean search(char[][] board,int i, int j, char[]w, int index,boolean[][] visited){
        if (index==w.length)return true;
        //不用分太细，直接像下面这样，把不符合要求的点直接返回false
        if(i<0||i>=board.length||j<0||j>=board[0].length||visited[i][j]||w[index]!=board[i][j])
            return false;
        visited[i][j]=true;
        if (search(board,i+1,j,w,index+1,visited)||search(board,i,j+1,w,index+1,visited)||search(board,i-1,j,w,index+1,visited)||search(board,i,j-1,w,index+1,visited))
            return true;
        visited[i][j]=false; //回溯，若未成功，将所有的点恢复为未访问
        return false;
    }

    /*
    leetcode 82
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head==null||head.next==null)return head;
        ListNode root = new ListNode(99999999);
        root.next = head;
        ListNode h = new ListNode(Integer.MIN_VALUE);
        ListNode k = h;
        ListNode p = root, q = root.next, r = q.next;
        while(true){
            if (r!=null&&p.val!=q.val&&q.val!=r.val){
                k.next = new ListNode(q.val);
                k = k.next;
                p = q;
                q = r;
                r = r.next;
            }else if(r==null&&p.val!=q.val){
                k.next = new ListNode(q.val);
                k = k.next;
                break;
            }else if(r==null){
                break;
            }else {
                p = q;
                q = r;
                r = r.next;
            }
        }
        return h.next;
    }

    public ListNode deleteDuplicates2(ListNode head) {
        if (head==null||head.next==null)return head;
        ListNode root = new ListNode(0);
        root.next = head;
        ListNode pre = root;
        ListNode cur = head;
        while (cur != null) {
            while (cur.next != null && cur.val == cur.next.val) {
                cur = cur.next;
            }
            if (pre.next == cur) { //if pre.next = cur; put cur in pre's next
                pre = pre.next;
            }else{
                pre.next = cur.next; // else delete between pre and cur
            }
            cur = cur.next;
        }
        return root.next;
    }

    /*
    leetcode 86
     */
    public ListNode partition(ListNode head, int x) {
        ListNode root = new ListNode(-1);
        ListNode res = new ListNode(-1); //摘下root中小于x的节点
        root.next = head;
        ListNode q = root, r = root.next; //遍历root
        ListNode p1 = res; //工作指针
        while(r!=null){
            if (r.val < x) {
                q.next = r.next; //摘下r
                r.next = null;
                //System.out.println(r.val);
                p1.next = r;
                p1 = p1.next;
                r = q.next;
            }else {
                q = r;
                r = r.next;
            }
        }
//        while (root!=null){
//            System.out.println(root.val);
//            root = root.next;
//        }
        p1.next = root.next;
        return res.next;
    }

    /*
    leetcode 89
     */
    public List<Integer> grayCode(int n) {
        List<Integer> list = new ArrayList<>();
        list.add(0);
        for (int i = 0; i < n; i++) {
            int add = 1<<i;
            for (int j = list.size()-1; j >= 0; j--){
                list.add(list.get(j)+add);
            }
        }
        return list; //考虑在已有的n-1的结果的最左边加0或1，加0值不变，因此必+1，再观察规律
    }
    public List<Integer> grayCode2(int n) { //格雷编码的公式，保留首位，其余与高一位做异或
        List<Integer> gray = new ArrayList<Integer>();
        for(int binary = 0;binary < 1 << n; binary++){
            gray.add(binary ^ binary >> 1);
        }
        return gray;
    }

    /**
     * @leetcode 71
     */
    //栈操作，可用栈或者双向链表代替栈等
    public String simplifyPath(String path) {
        StringBuilder res = new StringBuilder();
        LinkedList<String> stack = new LinkedList<>();
        for (String i: path.split("/")
        ) {
            if (i.equals("..")){
                if (!stack.isEmpty())
                    stack.removeLast();
            }else if(!i.equals("")&&!i.equals(".")){
                stack.add(i);
            }
        }
        if (stack.isEmpty()){
            res.append("/");
            return res.toString();
        }else {
            for (String i : stack) {
                res.append("/" + i);
            }
            return res.toString();
        }
    }

    /**
     * @leetcode 91
     */
    public int numDecodings(String s) {
        int[] c = new int[s.length()];
        char[] t = s.toCharArray();
        if(t.length==0)return 0;
        if(t.length==1&&t[0]!='0')
            return 1;
        else if (t.length==1||t[0]=='0'){
            return 0;
        }
        else if(t.length==2&&(t[0]-'0')*10+t[1]-'0'<=26&&t[1]!='0'){
            return 2;
        }else if(t.length==2&&(t[0]-'0')*10+t[1]-'0'<=26){
            return 1;
        }else{
            if (t[t.length-1]=='0'){ //末位为0
                c[t.length-1] = 0;
                if ((t[t.length-2]-'0')*10+t[t.length-1]-'0'>26||t[t.length-2]=='0'){
                    c[t.length-2]=0;
                }else {
                    c[t.length-2]=1;
                }
            }else {                  //末位不为0
                c[t.length-1] = 1;
                if (t[t.length-2]=='0')
                    c[t.length-2]=0;
                else if ((t[t.length-2]-'0')*10+t[t.length-1]-'0'>26){
                    c[t.length-2]=1;
                }else {
                    c[t.length-2]=2;
                }
            }
            for (int i = t.length-3; i >=0 ; i--) {
                int k = (t[i]-'0')*10+t[i+1]-'0';
                if(t[i]=='0')  //首字母为0，显然无对应解码
                    c[i]=0;
                else if(k<=26&&k!=0){
                    c[i] = c[i+1]+c[i+2];
                }else if((k)%10!=0){
                    c[i] = c[i+1];
                }else
                    return 0;
            }
        }
        return c[0];
    }

    /**
     * @leetcode 49
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, Integer> map = new HashMap<>();
        List<List<String>> res = new LinkedList<>();
        for (String s:strs
             ) {
            char[] t = s.toCharArray();
            Arrays.sort(t);
            String str = new String(t);
            if(!map.containsKey(str)){
                List<String> l = new LinkedList<>();
                l.add(s);
                res.add(l);
                map.put(str, res.size()-1);
            }else
                res.get(map.get(str)).add(s);
        }
        return res;
    }

    /**
     * @leetcode 31
     */
    public void nextPermutation(int[] nums) {
        int len = nums.length;
        if(len<2)return;
        int index = -1, min = Integer.MAX_VALUE;
        for (int i = len - 1; i > 0; i--) {
            if (nums[i] > nums[i - 1]) {
                for(int j = i; j<len;j++){
                    if (nums[j]<min&&nums[j]>=nums[i-1]){
                        index = j;
                        min = nums[j];
                    }
                }
                int t = nums[i-1];
                nums[i-1] = nums[index];
                nums[index] = t;
                System.out.println(i+""+ len);
                Arrays.sort(nums,i,len);
                return;
            }
        }
        Arrays.sort(nums);
    }


    /**
     * @leetcode 18
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result=new ArrayList<>();
        if(nums.length<4){
            return result;
        }
        Arrays.sort(nums);
        if(nums.length==4){
            int sum=nums[0]+nums[1]+nums[2]+nums[3];
            if(sum==target){
                result.add(Arrays.asList(nums[0],nums[1],nums[2],nums[3]));
                return result;
            }
        }
        int n = nums.length;
        for(int i=0;i<nums.length-3;i++){
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            if (nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) break;
            if (nums[i] + nums[n - 1] + nums[n - 2] + nums[n - 3] < target) continue;
            for(int j=i+1;j<nums.length-2;j++){
                if (j - i > 1 && nums[j] == nums[j - 1]) continue;
                if (nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) break;
                if (nums[i] + nums[j] + nums[n - 1] + nums[n - 2] < target) continue;
                int l=j+1;
                int r=nums.length-1;
                while(l<r){
                    int sum=nums[i]+nums[j]+nums[l]+nums[r];
                    if(sum>target){
                        while(l<r&&nums[r]==nums[r-1]){
                            r--;
                        }
                        r--;
                    }else if(sum<target){
                        while(l<r&&nums[l]==nums[l+1]){
                            l++;
                        }
                        l++;
                    }else{
                        result.add(Arrays.asList(nums[i],nums[j],nums[l],nums[r]));
                        while(l<r&&nums[r]==nums[r-1]){   //消除重复
                            r--;
                        }
                        while(l<r&&nums[l]==nums[l+1]){
                            l++;
                        }
                        r--;
                        l++;
                    }
                }
            }
        }
        return result;
    }
    /**
     * @leetcode 22
     */
    public List<String> generateParenthesis(int n) { //从当前使用了的左右括号的数量入手
        List<String> l = new LinkedList<>();
        char[] t = new char[n*2];
        t[0]='(';
        aidGenerateParenthesis(t,1,0,n,l);
        return l;
    }

    private void aidGenerateParenthesis(char[] t, int l, int r, int n, List<String> list1) {
        if(l==n){
            for (int i = r+l; i < 2*n; i++) {
                t[i]=')';
            }
            list1.add(new String(t));
        }
        else if(l==r){
            t[l+r]='(';
            aidGenerateParenthesis(t,l+1,r,n,list1);
        } else if (l > r) {
            t[l+r] ='(';
            aidGenerateParenthesis(t,l+1,r,n,list1);
            t[l+r] =')';
            aidGenerateParenthesis(t,l,r+1,n,list1);
        }
    }

    /**
     * @leetcode 33
     */
    public int search2(int[] nums, int t) {
        if(nums==null||nums.length==0)return -1;
        int low = 0, high = nums.length-1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] > nums[high]) {
                low = mid+1;
            }else {
                high = mid;
            }
        }
        if((low!=0&&t>nums[low-1])||(t<nums[low]))
            return -1;
        int l=0, h = low;
        while(l<=h){
            int mid = l + (h - l) / 2;
            if (nums[mid] < t) {
                l = mid+1;
            }else if(nums[mid]>t){
                h = mid-1;
            }else {
                return mid;
            }
        }
        l = low;
        h = nums.length-1;
        while(l<=h){
            int mid = l + (h - l) / 2;
            if (nums[mid] < t) {
                l = mid+1;
            }else if(nums[mid]>t){
                h = mid-1;
            }else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * @leetcode 93
     */
    List<String> res = new LinkedList<>();
    public List<String> restoreIpAddresses(String s) {
        aidRestoreIpAddresses(s, 0, 0, new StringBuilder());
        return res;
    }

    private void aidRestoreIpAddresses(String s, int index, int count, StringBuilder now) {
        if (count==4 && index == s.length()){
            res.add(now.toString());
            return;
        }else if (count==4){
            return;
        }
        if ((s.length()-now.length())*1.0/(4-count)>3){
            return;
        }
        for (int i = index+1; i <= index+3&&i<=s.length(); i++) {
            int val = Integer.valueOf(s.substring(index, i));
            if (val<=255&&val>=Math.pow(10,i-index-1)||(val>=0&&i==index+1)){
                now.append(s.charAt(i-1));
                StringBuilder cur = new StringBuilder(now);
                if(count!=3)
                cur.append(".");
                aidRestoreIpAddresses(s, i, count+1, cur);
            }
        }
    }

    /**
     * @leetcode 94
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> l = new LinkedList<>();
        if (root==null)
            return l;
        Stack<TreeNode> stack = new Stack<>();
        while (root!=null||!stack.empty()) {
            while (root!=null){
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            l.add(root.val);
            root =root.right;
        }
        return l;
    }

    /**
     * @leetcode 96
     */
    public int numTrees(int n) {
        /*
        n个节点时，选择第i个节点为根节点的种类设为f(i),则总数G(n) = f(n)+....+f(0)
        又有第i节点作根节点，则左子树i-1个点，右子树n-i个点，则f(i) = G(i-1)*G(n-i)
         */
        if (n<=1)return 1;
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            int temp = 0;
            for (int j = 0; j < i; j++) {
                temp += dp[i-j-1] * dp[j];
            }
            dp[i] = temp;
        }
        return dp[n];
    }

    /**
     * @leetcode 98
     */
    List<Integer> lint = new LinkedList<>();
    public boolean isValidBST(TreeNode root) {
        in(root);
        int len = lint.size();
        for (int i = 0; i < len-1; i++) {
            if (lint.get(i)>=lint.get(i+1))
                return false;
        }
        return true;
    }

    private void in(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        in(treeNode.left);
        lint.add(treeNode.val);
        in(treeNode.right);
    }

    /**
     * @leetcode 102
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root==null)return list;
        level(root, 0);
        return list;
    }

    private void level(TreeNode root, int level) {
        if (list.size() <= level&&root!=null) {
            List<Integer> l = new LinkedList<>();
            l.add(root.val);
            list.add(l);
        } else if (root == null) {
            return;
        }else {
            list.get(level).add(root.val);
        }
        level(root.left, level+1);
        level(root.right, level+1);
    }

    /**
     * @leetcode 103
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root==null)return list;
        level2(root, 0);
        return list;
    }
    private void level2(TreeNode root, int level) {
        if (list.size() <= level&&root!=null) {
            List<Integer> l = new LinkedList<>();
            l.add(root.val);
            list.add(l);
        } else if (root == null) {
            return;
        }else {
            if (level%2==0)
            list.get(level).add(root.val);
            else {
                list.get(level).add(0,root.val);
            }
        }
        level2(root.left, level+1);
        level2(root.right, level+1);
    }

    /**
     * @leetcode 113
     */
//    public TreeNode buildTree(int[] preorder, int[] inorder) {
//        aidBuildTree(preorder, inorder, 0, inorder.length-1);
//    }
//
//    private TreeNode aidBuildTree(int[] preorder, int[] inorder,int left, int right) {
//        int t = preorder[left];
//        TreeNode root = new TreeNode(t);
//        for (int i = 0; i < inorder.length; i++) {
//            if (inorder[i]==t){
//                root.left = aidBuildTree(preorder,inorder,1, i+1);
//                root.right = aidBuildTree(preorder,inorder,i+2,preorder.length-1);
//            }
//        }
//        return root;
//    }

    public boolean parseBoolExpr(String expression) {
        Stack<Character> exp_stack = new Stack<>();
        Stack<Character> characters = new Stack<>();
        expression = expression.replaceAll(",", "");
        char[] t = expression.toCharArray();
        for (char i : t) {
            if (i == '!' || i == '&' || i == '|') {
                exp_stack.push(i);
            } else if (i != ')') {
                characters.push(i);
            } else {
                boolean f = characters.pop() == 't';
                char e = exp_stack.pop();
                if (e == '!') {
                    characters.pop();
                    if (f) {
                        characters.push('f');
                    } else {
                        characters.push('t');
                    }
                } else if (e == '&') {
                    while (characters.peek() != '(') {
                        boolean f1 = characters.pop() == 't';
                        f = f && f1;
                    }
                    characters.pop();
                    if (f) {
                        characters.push('t');
                    } else {
                        characters.push('f');
                    }
                } else {
                    while (characters.peek() != '(') {
                        boolean f1 = characters.pop() == 't';
                        f = f || f1;
                    }
                    characters.pop();
                    if (f) {
                        characters.push('t');
                    } else {
                        characters.push('f');
                    }
                }
            }
        }
        return characters.pop() == 't';
    }
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> list = new ArrayList<>();
        if (root==null)return list;

        aidPathSum(root,sum,new LinkedList<>(), list);
        return list;
    }

    private void aidPathSum(TreeNode root, int sum, List<Integer> l,List<List<Integer>> list){
            if (root == null) return;
            l.add(root.val);
            if (sum == root.val && root.left == null && root.right == null) {
                List<Integer> l1 = new ArrayList<>(l);
                list.add(l1);
            }
            if (root.left != null) {
                aidPathSum(root.left, sum - root.val, l, list);
                //l.remove(l.size()-1);
            }
            //l.remove(l.size()-1);
            if (root.right != null) {
                aidPathSum(root.right, sum-root.val, l, list);
                //l.remove(l.size()-1);
            }
            l.remove(l.size()-1);
        }
    /*
    动态规划，dp[i]记录放i本书的最小高度，放置第i+1本时(下标问题，第i+1本高度宽度下标为i)，进入次循环，从
    第i+1本向前追溯，将他们尽可能放在一层，也就是说如果目前放置第六本，那就试试第六本第五本第四本。。等能不能放在一起，
    直到宽度够了，比如到第四本，那么现在在一层的三本最高高度就是h，而之前三本的所有高度，就在dp[3]中存储。
    那么dp[6] = Math.min(dp[6], dp[3]+h);  min是必需的，以为在j向前遍历时，每次会遍历到宽度大于书架宽度，所以dp可能更新
     */
    public int minHeightShelves(int[][] books, int shelf_width){
        int len = books.length;
        int[] dp = new int[len + 1];
        for (int i = 0; i <= len; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        dp[0] = 0;
        for (int i = 0; i < len; i++) {
            int w = 0, h = 0;
            for (int j = i; j >= 0; j--) {
                w += books[j][0];
                h = Math.max(h, books[j][1]);
                if (w > shelf_width)
                    break;
                dp[i + 1] = Math.min(dp[i + 1], dp[j] + h);
            }
        }
        return dp[len];
    }

    /**
     * @leetcode 116 117
     */
    public Node connect(Node root) {
        List<List<Node>> l = new ArrayList<>();
        aidConnect2(root,0,l);
        return root;
    }

    private void aidConnect2(Node root, int level, List<List<Node>> l) {
        if (l.size()-1<level){
            l.add(new ArrayList<>());
        }
        List<Node> l1 = l.get(level);
        if (root != null) {
            if (l1.size()==0){
                l1.add(root);
            }else{
                Node t = l1.get(l1.size()-1);
                t.next = root;
                l1.add(root);
            }
            aidConnect2(root.left,level+1,l);
            aidConnect2(root.right,level+1,l);
        }
    }
    private void aidConnect(Node parent, Node root, int flag) {
        if (parent!=null&&root!=null) {
            if (flag == 1) {
                root.next = parent.right;
            } else if (flag == 2 && parent.next != null) {
                root.next = parent.next.left;
            }
        }else if (root==null){
            return;
        }
        aidConnect(root, root.left,1);
        aidConnect(root,root.right, 2);
    }

    /**
     * leetcode contest double 3 D
     * @param A
     * @return maxgrade
     */
    public int maximumMinimumPath(int[][] A) {
        int[][] direct = {{1,0},{0,1},{-1,0},{0,-1}};
        int r = A.length, c = A[0].length;
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0,0,A[0][0]});
        int[][] dp = new int[r][c];
        dp[0][0] = A[0][0];
        while (!queue.isEmpty()){
            int[] k = queue.poll();
            for (int[] d : direct) {
                int nx = d[0]+k[0];
                int ny = d[1]+k[1];
                if (nx >= 0 && nx < r && ny >= 0 && ny < c) {
                    int min = Math.min(k[2], A[nx][ny]);
                    if (min>dp[nx][ny]){
                        dp[nx][ny]=min;
                        queue.offer(new int[]{nx,ny,min});
                    }
                }
            }
        }
        return dp[r-1][c-1];
    }

    /**
     * @leetcode 137
     */
//    public int singleNumber(int[] nums) {
//        Arrays.sort(nums);
//        for (int i = 0; i < nums.length-2; i+=3) {
//            if (nums[i]!=nums[i+2])
//                return nums[i];
//        }
//        return nums[nums.length-1];
//    }
    public int singleNumber(int[] nums) {
        int one = 0, two = 0, three = 0;
        for (int i : nums) {
            two =two| one & i;   //two记录出现两次的，即i与one同时为1,与two或操作保留目前为2次的位
            one = one ^ i;   //one记录出现一次，与i不同时为1，因为同时为1的出现两次，已经记录在two中
            three = one & two;  //three 即 one 和two同时为1
            one = one&~three;   //three 为1的位要在one和three中清除
            two = two&~three;   //同上
        }
        return one;
    }

    /**
     * @leetcode 120
     */
    /*
    自底向上更新
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        int len = triangle.size();
        int[] res = new int[len+1];
        if (len==0)return 0;
        for (int i = len - 1; i >= 0; i--) {
            List<Integer> l = triangle.get(i);
            for (int j = 0; j < l.size(); j++) {
                res[j] = l.get(j) + Math.min(res[j],res[j+1]);
            }
        }
        return res[0];
    }

    /**
     * @leetcode 129
     */
    int sum = 0;
    public int sumNumbers(TreeNode root) {
        aidSumNumbers(root,0);
        return sum;
    }

    private void aidSumNumbers(TreeNode root, int now) {
        if (root==null)
            return;
        if (root.left == null && root.right == null) {
            sum += now*10 + root.val;
            return;
        }
        if (root.left != null) {
            aidSumNumbers(root.left,now * 10 + root.val);
        }
        if (root.right != null) {
            aidSumNumbers(root.right,now * 10 + root.val);
        }
    }

    /**
     * @leetcode 144
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> l = new ArrayList<>();
        if (root==null) return l;
        stack.push(root);
        while (!stack.empty()) {
            TreeNode t = stack.pop();
            l.add(t.val);
            if (t.right!=null)
                stack.push(t.right);
            if (t.left!=null)
                stack.push(t.left);
        }
        return l;
    }

    /**
     * @leetcode 143
     */
    public void reorderList(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast!=null&&fast.next!=null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode t = slow.next, p = t.next;
        slow.next=null;
        ListNode q = head.next;
        head.next=null;
        while (t!=null){
            t.next=head.next;
            head.next=t;
            t = p;
            if (p!=null)
            p =p.next;
        }
    }

    /**
     * @leetcode 147
     */
    public ListNode insertionSortList(ListNode head) {
        ListNode root = new ListNode(Integer.MIN_VALUE);
        if(head==null)return head;
        ListNode q = head.next;
        while(head!=null){
            head.next=null;
            ListNode p = root;
            if (p.next==null){
                p.next = head;
            }else {
                while (p.next != null && p.next.val < head.val) {
                    p = p.next;
                }
                head.next=p.next;
                p.next=head;
            }
            head = q;
            if(q!=null)
            q = q.next;
        }
        return root.next;
    }

    /**
     * @leetcode 150
     */
    public int evalRPN(String[] tokens) {
        int[] nums = new int[tokens.length];
        int top = -1;
        for (String s : tokens) {
            if (s.equals("+")||s.equals("-")||s.equals("*")||s.equals("/")){
                int num1 = nums[top--];
                int num2 = nums[top--];
                int res = 0;
                switch (s.toCharArray()[0]){
                    case '+': res = num1+num2;break;
                    case '-': res = num2-num1;break;
                    case '/': res = num2/num1;break;
                    case '*': res = num2*num1;break;
                }
                nums[++top]=res;
            }else {
                nums[++top] = Integer.parseInt(s);
            }
        }
        return nums[0];
    }

    /**
     * @leetcode 151
     */
    public String reverseWords(String s) {
        String[] strs = s.trim().split("\\s+");
        StringBuilder res = new StringBuilder();
        for (int i = strs.length-1; i >=0 ; i--) {
            res.append(strs[i]);
            if (i != 0) {
                res.append(" ");
            }
        }
        return res.toString();
    }

    /**
     * @leetcode 152
     */
    public int maxProduct(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = Integer.MIN_VALUE;
        for (int i = 1; i < nums.length; i++) {
            int t = nums[i];
            if (t>0){
                if (dp[i-1]>0){
                    dp[i]=dp[i-1]*t;
                }else{
                    dp[i]=t;
                }
            }else {
                if (t==0){
                    dp[i]=0;
                }else {
                    for (int j=i-1;j>=0;j--){
                        t*=nums[j];
                        if (t>dp[i]){
                            dp[i]=t;
                        }
                    }
                }
            }
            if (dp[i]>max){
                max=dp[i];
            }
        }
        return max>dp[0]?max:dp[0];
    }
    /*
    标签：动态规划
    遍历数组时计算当前最大值，不断更新
    令imax为当前最大值，则当前最大值为 imax = max(imax * nums[i], nums[i])
    由于存在负数，那么会导致最大的变最小的，最小的变最大的。因此还需要维护当前最小值imin，imin = min(imin * nums[i], nums[i])
    当负数出现时则imax与imin进行交换再进行下一步计算
    时间复杂度：O(n)
     */
    public int maxProduct2(int[] nums) {
        int max = Integer.MIN_VALUE, imax = 1, imin = 1;
        for (int i : nums) {
            if (i < 0) {
                int temp = imax;
                imax = imin;
                imin = temp;
            }
            imax = Math.max(imax*i, i);
            imin = Math.min(i*imin, i);

            max = Math.max(max,imax);
        }
        return max;
    }

    /**
     * @leetcode 114
     */
    public void flatten(TreeNode root) {
        aidFlatten(root);
    }

    private TreeNode aidFlatten(TreeNode root) {
        if (root==null)
            return null;
        TreeNode right = root.right;
        TreeNode f1 = aidFlatten(root.left);
        root.left = null;
        root.right = f1;
        while (f1 != null && f1.right != null) {
            f1 = f1.right;
        }
        if (f1!=null){
            f1.right = aidFlatten(right);
        }else {
            root.right = aidFlatten(right);
        }
        return root;
    }

    /**
     * @leetcode 92
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if(m==n)return head;
        int count = 0;
        ListNode root = new ListNode(-1);
        root.next = head;
        ListNode p = root, q = head;  //翻转
        ListNode h = root, r = root; //保存头尾
        while (q != null && count < m) {
            h = p;
            p = q;
            q = q.next;
            count++;
        }
        r = p;
        h.next = null;
        while (count <= n) {
            p.next = h.next;
            h.next = p;
            p = q;
            if(q!=null)
            q = q.next;
            count++;
        }
        r.next = p;
        return root.next;
    }

    /**
     * leetcode contest 3 double
     */
    public int twoSumLessThanK(int[] A, int K) {
        Arrays.sort(A);
        int max = 0;
        if(A.length<=1||A[0]+A[1]>=K)return -1;
        else {
            int low =0, high = A.length-1;
            while(low<high){
                if (A[low]+A[high]>=K)
                    high--;
                else {
                    if(A[low]+A[high]>max)
                        max=A[low]+A[high];
                    low++;
                }
            }
            return max;
        }
    }

    public int numKLenSubstrNoRepeats(String S, int K) {
        if (S==null||S.length()<K)
            return 0;
        int len = S.length();
        int count = 0;
        for (int i = 0; i <= len - K; i++) {
            if (aidNumKLenSubstrNoRepeats(S.substring(i,i+K)))
                count++;
        }
        return count;
    }
    private boolean aidNumKLenSubstrNoRepeats(String s){
        int[] t = new int[26];
        char[] c = s.toCharArray();
        for (char i : c) {
            if(t[i-'a']==0)
                t[i-'a']++;
            else
                return false;
        }
        return true;
    }

    public int earliestAcq(int[][] logs, int N) {
        Arrays.sort(logs,Comparator.comparingInt(a->(a[0])));
        int[] t = new int[N];
        for (int i = 0; i < N; i++) {
            t[i]=-1;
        }
        for (int[] i : logs) {
            boolean flag = true;
            if (i[1]<i[2]&&t[i[1]]==-1&&t[i[2]]==-1){
                t[i[1]] =i[2];
                t[i[2]] =i[2];
            }else if(i[1]>i[2]&&t[i[1]]==-1&&t[i[2]]==-1) {
                t[i[1]] = i[1];
                t[i[2]] = i[1];
            }else if (t[i[1]]==-1&&t[i[2]]!=-1){ //有一个有所属阵营
                if (i[1]<t[i[2]])
                t[i[1]] = t[i[2]];
                else {
                    t[i[1]] = i[1];
                    int s = t[i[2]];
                    for (int j = 0; j < N; j++) {
                        if (t[j]==s){
                            t[j] = i[1];
                        }
                    }
                }
            }else if(t[i[1]]!=-1&&t[i[2]]==-1) {
                if (i[2]<t[i[1]])
                    t[i[2]] = t[i[1]];
                else {
                    t[i[2]] = i[2];
                    int s = t[i[1]];
                    for (int j = 0; j < N; j++) {
                        if (t[j]==s){
                            t[j] = i[2];
                        }
                    }
                }
            }else {
                int max = Math.max(t[i[1]],t[i[2]]);
                int s = t[i[1]], z = t[i[2]];
                for (int j = 0; j < N; j++) {
                    if (t[j]==s||t[j]==z){
                        t[j] = max;
                    }
                }
            }
            for (int j = 0; j < N; j++) {
                System.out.println(t[j]);
                if(t[j]!=N-1)
                    flag = false;
            }
            if (flag)
                return i[0];
        }
        return -1;
    }

    /**
     * @leetcode contest 143
     */
    public int[] distributeCandies(int candies, int num_people) {
        int[] t = new int[num_people];
        int count = 1;
        for (int i = 0; i < num_people; i++) {
            if (candies>=count){
                t[i]+=count;
                candies-=count;
                count++;
            }else {
                t[i]+=candies;
                break;
            }
            if (i==num_people-1){
                i=-1;
            }
        }
        return t;
    }

    public List<Integer> pathInZigZagTree(int label) {
        List<Integer> res = new ArrayList<>();
        int level = 0;
        for(;;level++){
            if (Math.pow(2,level)>=label){
                break;
            }
        }
        if (Math.pow(2,level)>label)
            level--;
        System.out.println(level);
        while (label!=1){
            res.add(0,label);
            label = (int)Math.pow(2,level)-(label-(int)Math.pow(2,level))/2-1;
            level--;
        }
        res.add(0,1);
        return res;
    }


    /**
     * 179 主要是实现新的排序方法，其核心比较规则在于，将两个数进行前后连接，比较连接后的数的大小，来确定哪一个数应该在前边。
     */
    private class LargerNumberComparator implements Comparator<String>{
        @Override
        public int compare(String o1, String o2) {
            String order1 = o1+o2;
            String order2 = o2+o1;
            return order2.compareTo(order1);
        }
    }
    public String largestNumber(int[] nums) {
        String[] s = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            s[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(s, new LargerNumberComparator());
        StringBuilder res = new StringBuilder();
        for (String i : s) {
            //System.out.println(i);
            res.append(i);
        }
        return res.toString();
    }

    /**
     * 47 含有重复数字的全排列，使用flag数组来标记已经使用过的数字，如果当前数字与前一个数字相同，并且在本次排列中
     * 前一个数字还未被使用，那么使用当前数字进行排列的话，会与之前的排列重复，因此进行剪枝，跳过。
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        boolean[] flag = new boolean[nums.length];
        dfs(new ArrayList<>(), flag, nums);
        return list;
    }
    private void dfs(List<Integer> l,boolean[] flag, int[] nums){
        if (l.size()==nums.length){
            list.add(new ArrayList(l));//传递引用，重新new一个对象保存
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (flag[i]){
                continue;
            }

            if (i>0&&nums[i-1]==nums[i]&&!flag[i-1]){
                continue;
            }

            l.add(nums[i]);
            flag[i]=true;
            dfs(l,flag,nums);
            flag[i]=false;
            l.remove(l.size()-1);
        }
    }

    /**
     * 40,由上一题进行修改，组合的和为target，存在问题是会多出一些相同答案的重排列，增加一个参数j，规定只能向后查找
     * 而不是从0查找，取消掉相同数字的重复排列
     */

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        boolean[] flag = new boolean[candidates.length];
        dfs2(new ArrayList<>(), candidates, target, 0, flag, 0);
        return list;
    }

    private void dfs2(List<Integer> l, int[] candidates, int target, int sum, boolean[] flag, int j) {
        if (sum == target) {
            list.add(new ArrayList<>(l));
            return;
        }

        for (int i = j; i < candidates.length; i++) {
            if (flag[i])
                continue;

            if (i>0&&candidates[i-1]==candidates[i]&&!flag[i-1]){
                continue;
            }
            if(sum+candidates[i]>target){
                return;
            }
            flag[i] = true;
            l.add(candidates[i]);
            dfs2(l,candidates,target,sum+candidates[i],flag,i);
            //sum-=candidates[i];
            l.remove(l.size()-1);
            flag[i]=false;
        }
    }

    /**
     * 60 时间过长
     */
    public String getPermutation(int n, int k) {
        boolean[] flag = new boolean[n+1];
        dfs3(n, k, new ArrayList<>(), flag);
        StringBuilder res = new StringBuilder();
        for (int i:list.get(k-1)
             ) {
            res.append(i);
        }
        return res.toString();
    }

    private void dfs3(int n, int k, List<Integer> l, boolean[] flag) {
        if (l.size() == n) {
            list.add(new ArrayList<>(l));
            return;
        }
        if (list.size()==k)
            return;
        for (int i = 1; i <= n; i++) {
            if (flag[i])
                continue;
            flag[i]=true;
            l.add(i);
            dfs3(n, k, l, flag);
            l.remove(l.size()-1);
            flag[i]=false;
        }
    }

    /**
     * 90 有重复数字的数组的子集求解
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        boolean[] flag = new boolean[nums.length];
        Arrays.sort(nums);
        dfs4(nums, new ArrayList<>(), flag, 0);
        return list;
    }

    private void dfs4(int[] nums, List<Integer> l, boolean[] flag, int j) {
        if (l.size()<=nums.length){
            list.add(new ArrayList<>(l));
        }

        for (int i = j; i < nums.length; i++) {
            if (flag[i]){
                continue;
            }
            //去重部分
            if (i>0&&nums[i-1]==nums[i]&&!flag[i-1]){
                continue;
            }
            l.add(nums[i]);
            flag[i]=true;
            dfs4(nums,l,flag,i);
            flag[i]=false;
            l.remove(l.size()-1);
        }
    }

    /**
     * 207
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        for (int i = 0; i < numCourses; i++) {
            list.add(new ArrayList<>());
        }
        int[] n = new int[numCourses];
        boolean[] flag = new boolean[numCourses];
        for (int[] i : prerequisites) {
            list.get(i[0]).add(i[1]);
            n[i[1]]++;
        }
        aidCanFinish(n, flag);
//        for (int i:n){
//            System.out.println(i);
//        }
        for (int i : n) {
            if (i!=0)
                return false;
        }
        return true;
    }

    private void aidCanFinish(int[] n, boolean[] flag) {
        boolean change = false;
        for (int i = 0; i < n.length; i++) {
            if (n[i] == 0&&!flag[i]) {
                flag[i] = true;
                for (int j:list.get(i)){
                    n[j]--;
                }
                change = true;
            }
        }
        if (change) {
            aidCanFinish(n,flag);
        }
    }
}






