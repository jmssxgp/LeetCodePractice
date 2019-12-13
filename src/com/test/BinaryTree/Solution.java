package com.test.BinaryTree;

import com.test.HashTrain.TreeNode;

import java.util.*;

/**
 * @author xgp
 * @version 1.0
 * @date 2019/10/17 20:40
 */
public class Solution {

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        if (root==null)return res;
        stack.push(root);
        while (!stack.empty()){
            TreeNode t = stack.pop();
            if (t.left!=null){
                stack.push(t.left);
            }
            if (t.right!=null){
                stack.push(t.right);
            }
            res.add(0,t.val);
        }
        return res;
    }


    public boolean isSymmetric(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        if (root==null)return true;
        queue.offer(root.left);
        queue.offer(root.right);
        while (!queue.isEmpty()){
            TreeNode t1 = queue.poll();
            TreeNode t2 = queue.poll();
            if (t1==null&&t2==null)continue;
            if (t1==null||t2==null)return false;
            if (t1.val!=t2.val)return false;
            queue.offer(t1.left);
            queue.offer(t2.right);
            queue.offer(t1.right);
            queue.offer(t2.left);
        }
        return true;
    }


    public boolean hasPathSum(TreeNode root, int sum) {
        if(root==null)return false;
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<Integer> q = new LinkedList<>();
        queue.offer(root);
        q.offer(sum - root.val);
        while (!queue.isEmpty()){
            TreeNode t = queue.poll();
            int new_sum = q.poll();
            if (new_sum==0&&t.right==null&&t.left==null)
                return true;
            if (t.left!=null){
                queue.offer(t.left);
                q.offer(new_sum - t.left.val);
            }
            if (t.right!=null){
                queue.offer(t.right);
                q.offer(new_sum - t.right.val);
            }
        }
        return false;
    }

    int[] pre;
    int[] in;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        pre = preorder;
        in = inorder;
        return aidBuildTree(0,preorder.length,0, inorder.length);
    }

    private TreeNode aidBuildTree(int p_left, int p_right, int i_left, int i_right){
        TreeNode root = null;
        if (p_left<p_right||i_left<i_right){
            root = new TreeNode(pre[p_left]);
            for (int i = i_left; i < i_right; i++) {
                if (in[i]==pre[p_left]){
                    root.left = aidBuildTree(p_left+1, p_left+1+i-i_left, i_left, i);
                    root.right= aidBuildTree(p_left+1+i-i_left,p_right,i+1, i_right);
                    break;
                }
            }
        }
        return root;
    }


    TreeNode ans = null;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        aidLowestCommonAncestor(root, p, q);
        return ans;
    }

    private boolean aidLowestCommonAncestor(TreeNode cur, TreeNode p, TreeNode q){
        if (cur == null)
            return false;

        int left = aidLowestCommonAncestor(cur.left, p, q)?1:0;
        int right = aidLowestCommonAncestor(cur.right, p, q)?1:0;
        int mid = (cur==q||cur==p)?1:0;

        if (left+right+mid>=2){
            ans = cur;
        }

        return left+right+mid>0;
    }


    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null)return null;
        if(root==q||root==p)return root;
        TreeNode left = lowestCommonAncestor2(root.left, p, q);
        TreeNode right = lowestCommonAncestor2(root.right, p, q);
        if(left!=null&&right!=null){
            return root;
        }else if(left!=null){
            return left;
        }else if(right!=null){
            return right;
        }
        return null;
    }


    public int countNodes(TreeNode root) {
        if (root==null)return 0;
        int left = level(root.left);
        int right = level(root.right);
        if(left==right){
            return countNodes(root.right) + (1<<left);
        }else {
            return countNodes(root.left)+ (1<<right);
        }
    }

    private int level(TreeNode t){
        int level = 0;
        while (t!=null){
            level++;
            t = t.left;
        }
        return level;
    }

    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        if (A>E){
            return computeArea(E,F,G,H,A,B,C,D);
        }
        if (B>=H||D<=F||C<=E){
            return (A-C)*(B-D)+(E-G)*(F-H);
        }
        int down = Math.max(A,E);
        int up = Math.min(H,D);
        int left=Math.max(B,F);
        int right = Math.min(C,G);
        return (A-C)*(B-D)+(E-G)*(F-H)-(right-down)*(left-up);
    }

}
