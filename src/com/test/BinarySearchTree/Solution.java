package com.test.BinarySearchTree;

import com.test.HashTrain.TreeNode;

import java.util.TreeSet;

/**
 * @author xgp
 * @version 1.0
 * @date 2019/10/24 21:32
 */
public class Solution {


    public TreeNode searchBST(TreeNode root, int val) {
        if (root==null) return null;
        if (root.val<val){
            return searchBST(root.right, val);
        }else if (root.val>val){
            return searchBST(root.left, val);
        }else {
            return root;
        }
    }


    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root==null){
            return new TreeNode(val);
        }
        TreeNode t = root;
        while (true){
            if (t.val<val)
                if (t.right!=null){
                    t = t.right;
                }else {
                    t.right = new TreeNode(val);
                    return root;
                }
            else if (t.val>val){
                if (t.left!=null){
                    t = t.left;
                }else {
                    t.left = new TreeNode(val);
                    return root;
                }
            }
        }
    }


    public TreeNode deleteNode(TreeNode root, int key) {
        if (root==null)
            return root;
        if (root.val<key){
            root.right = deleteNode(root.right, key);
            return root;
        }else if (root.val>key){
            root.left = deleteNode(root.left, key);
            return root;
        }else {
            if (root.left == null){ //左为空直接返回右子树
                return root.right;
            }
            if (root.right==null){ //右为空 返回左子树
                return root.left;
            }

            TreeNode t = new TreeNode(minmum(root.right).val); //均不为空，找到其右子树最左节点也就是他的后继顶替他的位置
            t.left = root.left;                                //也可找到左子树最右节点也就是他的前驱来顶替位置
            t.right = removeMin(root.right);
            return t;
        }
    }
    private TreeNode minmum(TreeNode root){  //找到最左节点
        if (root.left==null)
            return root;
        return minmum(root.left);
    }
    private TreeNode removeMin(TreeNode root){  //除去最左节点
        if (root.left==null){
            return root.right;
        }
        root.left = removeMin(root.left);
        return root;
    }


    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            Integer f = set.ceiling(nums[i]);
            if (f!=null&&f<=nums[i]+t) return true;
            Integer g = set.floor(nums[i]);
            if (g!=null&&nums[i]<=g+t) return true;

            set.add(nums[i]);
            if (set.size()>k){
                set.remove(nums[i-k]);
            }
        }
        return false;
    }


}
