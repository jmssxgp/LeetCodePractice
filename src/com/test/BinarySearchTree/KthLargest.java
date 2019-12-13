package com.test.BinarySearchTree;

import com.test.HashTrain.TreeNode;

import java.util.Stack;

class KthLargest {
    int n;
    TreeNode root;
    public KthLargest(int k, int[] nums) {
        n = k;
        root = new TreeNode(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            add(nums[i]);
        }
    }
    
    public int add(int val) {
         TreeNode t = root;
         while (true){
             if (val<t.val){
                 if (t.left==null){
                     t.left = new TreeNode(val);
                     break;
                 }else {
                     t = t.left;
                 }
             }else {
                 if (t.right==null){
                     t.right = new TreeNode(val);
                     break;
                 }else {
                     t=t.right;
                 }
             }
         }
         return search();
    }

    private int search(){
        int count = 1;
        TreeNode t = root;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(t);
        while (!stack.isEmpty()||t!=null){
            if(t!=null){
                stack.push(t.left);
            }else {
                t = stack.pop();
                count++;
                if (count==n){
                    return t.val;
                }
                t =t.right;
            }
        }
        return t.val;
    }
}