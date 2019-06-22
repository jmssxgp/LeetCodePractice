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
     * @leetcode 47
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length-1; i++) {
            if (nums[i]!=nums[i+1]||(i==nums.length-1&&nums[i]!=nums[i-1])){
                List<Integer> l = new LinkedList<>();
                l.add(nums[i]);
                add(nums,i, l);
            }
        }
        return list;
    }
    private void add(int[] nums, int j, List<Integer> l){
        if(l.size()==nums.length)
            return;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i]!=nums[i+1]||(i==nums.length-1&&nums[i]!=nums[i-1])&&i!=j){
                List<Integer> l1 = new ArrayList<>(l);
                l1.add(nums[i]);
                add(nums,i,l1);
            }
        }
    }
}






