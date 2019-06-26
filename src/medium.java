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
     * @leetcode 90
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i <= nums.length; i++) {
            aidSubsetWithDup(new ArrayList<>(), nums, i, 0); //此处的0无意义
        }
        return list;
    }
    private void aidSubsetWithDup(List<Integer> l, int[] nums, int k, int t){ //k为子集中数字个数, t为添加的下一个数字下标开始
        if(l.size()==k){
            list.add(l);
            return;
        }
        for (int i = (l.size() == 0 ? 1 : t); i <= nums.length; i++) {
            if((i<nums.length&&nums[i-1]<nums[i])||(i==nums.length)){
                List<Integer> l1 = new ArrayList<>(l);
                l1.add(nums[i-1]);
                aidSubsetWithDup(l1, nums, k, i + 1); //下一个添加从(i+1)-1开始，若存在重复数字，那么每个重复数字应该只用一次
            }else{
                l.add(nums[i-1]);
            }
        }
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
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> list = new ArrayList<>();
        if (root==null)return list;

        aidPathSum(root,sum,new LinkedList<>(), list);
        return list;
    }

    private void aidPathSum(TreeNode root, int sum, List<Integer> l,List<List<Integer>> list) {
        if(root==null)return;
        l.add(root.val);
        if (sum==root.val&&root.left==null&&root.right==null){
            List<Integer> l1 = new ArrayList<>(l);
            list.add(l1);
        }
        if (root.left != null) {
            aidPathSum(root.left, sum-root.val, l, list);
            //l.remove(l.size()-1);
        }
        //l.remove(l.size()-1);
        if (root.right != null) {
            aidPathSum(root.right, sum-root.val, l, list);
            //l.remove(l.size()-1);
        }
        l.remove(l.size()-1);
    }
}






