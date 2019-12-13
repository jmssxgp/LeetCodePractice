package com.test.QueueTrain;

import java.util.*;

/**
 * @author xgp
 * @version 1.0
 * @date 2019/10/8 10:09
 */
public class Solution {

    /**
     * BFS
     * @param deadends
     * @param target
     * @return
     */
    public int openLock(String[] deadends, String target) {
        boolean[] set = new boolean[10000];
        boolean[] fob = new boolean[10000];
        for (String s: deadends
             ) {
            fob[Integer.parseInt(s)] = true;
        }
        Queue<int[]> queue = new LinkedList<>();
        int level = 0;
        queue.offer(new int[]{0,level});
        while (!queue.isEmpty()){
            int[] t = queue.poll();
            String s = String.format("%04d",t[0]);
            if (s.equals(target)) return t[1];
            if (fob[t[0]]){
                continue;
            }
            char[] c = s.toCharArray();
            for (int i=0;i<c.length;i++){
                char temp = c[i];
                c[i] = (char)((c[i]-'0'+1)%10+'0');
                int a1 = Integer.valueOf(new String(c));
                if(!set[a1]){
                    set[a1]=true;
                    queue.offer(new int[]{a1,t[1]+1});
                }
                c[i] = (char)((c[i]-'0'+8)%10+'0');
                int a2 = Integer.valueOf(new String(c));
                if(!set[a2]){
                    set[a2]=true;
                    queue.offer(new int[]{a2,t[1]+1});
                }
                c[i]=temp;
            }
        }
        return -1;
    }

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        char[] t = s.toCharArray();
        for (char i:t) {
            if (i=='('||i=='['||i=='{'){
                stack.push(i);
            }else {
                if (i==')'){
                    if(stack.isEmpty()||stack.pop()!='(')
                        return false;
                }
                if (i=='}'){
                    if(stack.isEmpty()||stack.pop()!='{')
                        return false;
                }
                if (i==']'){
                    if(stack.isEmpty()||stack.pop()!='[')
                        return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public int[] dailyTemperatures(int[] T) {
        int[] res = new int[T.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < T.length; i++) {
            while (!stack.isEmpty()&&T[i]>T[stack.peek()]){
                res[stack.peek()]=i-stack.peek();
                stack.pop();
            }
            stack.push(i);
        }
        return res;
    }


    /**
     * DFS 遍历复制图
     * @param node
     * @return
     */
    public Node cloneGraph(Node node) {
        Map<Node, Node> map = new HashMap<>();
        return dfs(node,map);
    }
    private Node dfs(Node root, Map<Node, Node> map){
        if (root==null)return root;
        if (map.containsKey(root))return map.get(root);
        Node clone = new Node(root.val, new ArrayList<>());
        map.put(root, clone);
        for (Node node: root.neighbors
             ) {
            clone.neighbors.add(dfs(node,map));
        }
        return clone;
    }

    /**
     *目标和，dfs，遍历所有的路径
     */
    int count;
    public int findTargetSumWays(int[] nums, int S) {
        int[] flag = new int[nums.length];
        aidFindTargetSumWays(nums,S,flag,0,0);
        return count;
    }
    private void aidFindTargetSumWays(int[] nums, int S, int[] flag, int now,int depth){
        System.out.println(now);
        if (now==S&&depth==nums.length){
            count++;
            return;
        }
        if (depth<nums.length) {
            aidFindTargetSumWays(nums,S,flag,now+nums[depth],depth+1);
            aidFindTargetSumWays(nums,S,flag,now-nums[depth],depth+1);
        }
    }


    public String decodeString(String s) {
        Stack<Character> stack = new Stack<>();
        Stack<Integer> num_stack = new Stack<>();
        char[] t = s.toCharArray();
        for (char c:t
             ) {
            if (c>='0'&&c<='9'){
                num_stack.push(c-'0');
            }else if (c!=']'){
                if (c=='[')
                    num_stack.push(-1);
                stack.push(c);
            }else {
                StringBuilder sb = new StringBuilder();
                while (stack.peek()!='['){
                    sb.insert(0,stack.pop());
                }
                stack.pop();
                int n = 0,z=1;
                if (num_stack.peek()==-1)
                    num_stack.pop();
                while (!num_stack.empty()&&num_stack.peek()!=-1){
                    n += z*num_stack.pop();
                    z*=10;
                }
                StringBuilder sb2 = new StringBuilder();
                for (int i = 0; i < n; i++) {
                    sb2.append(sb);
                }
                char[] sb_c = sb2.toString().toCharArray();
                for (char i:sb_c){
                    stack.push(i);
                }
            }
        }
        StringBuilder res = new StringBuilder();
        while (!stack.isEmpty()){
            res.insert(0, stack.pop());
        }
        return res.toString();
    }

    /**
     * 对整个数组先上，左更新，再下右更新
     */
    int MAXINT = Integer.MAX_VALUE - 100000;
    public int[][] updateMatrix(int[][] matrix) {
        int row = matrix.length;
        if (row == 0) return matrix;
        int col = matrix[0].length;
        for(int i = 0; i < row; i++){
            for (int j = 0; j < col; j++){
                if (matrix[i][j] == 0)
                    continue;
                int up = i == 0 ? MAXINT : (matrix[i-1][j] + 1);
                int left = j == 0 ? MAXINT : (matrix[i][j-1] + 1);
                matrix[i][j] = Math.min(up, left);
            }
        }
        for (int i = row - 1; i >= 0; --i){
            for (int j = col - 1; j >= 0; --j){
                if (matrix[i][j] == 0)
                    continue;
                int down = i == row - 1 ? MAXINT : (matrix[i+1][j] + 1);
                int right = j == col - 1 ? MAXINT : (matrix[i][j+1] + 1);
                matrix[i][j] = Math.min(matrix[i][j], Math.min(down, right));
            }
        }
        return matrix;
    }


    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        boolean[] flag = new boolean[rooms.size()];
        flag[0]=true;
        Queue<Integer> queue = new LinkedList<>();
        for (int i:rooms.get(0)){
            queue.offer(i);
            flag[i]=true;
        }
        while (!queue.isEmpty()){
            int t = queue.poll();
            for (int i: rooms.get(t)){
                if (!flag[i])
                    queue.offer(i);
                flag[i]=true;
            }
        }
        for (boolean t: flag
             ) {
            if (!t)
                return false;
        }
        return true;
    }


    int max = 0;
    int[][]grid;
    int[][] t = {{1,0},{-1,0},{0,1},{0,-1}};
    public int getMaximumGold(int[][] grid) {
        this.grid = grid;
        for (int i=0; i<grid.length;i++){
            for (int j=0;j<grid[i].length;j++){
                if (grid[i][j]!=0){
                    aidGetMaximumGold(i,j,grid[i][j]);
                }
            }
        }
        return max;
    }

    private void aidGetMaximumGold(int x, int y, int now){
        int r = grid.length, c = grid[0].length;
        int v = grid[x][y];
        grid[x][y]=0;   //借用原矩阵做标识矩阵，将后路堵死，避免走重复路线。
        for(int[] i:t){
            int x1 = x+i[0];
            int y1 = y+i[1];
            if (x1>=0&&x1<r&&y1>=0&&y1<c){
                if (grid[x1][y1]!=0){
                    int now1 = now+grid[x1][y1];
                    aidGetMaximumGold(x1,y1,now1);
                }else {
                    if (now>max)
                        max = now;
                }
            }else {
                if (now>max)
                    max = now;
            }
        }
        grid[x][y]=v;
        //System.out.println(now);
    }
}












