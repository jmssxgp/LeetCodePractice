package com.test.BinarySearchTree;

import com.test.HashTrain.TreeNode;

import java.util.ArrayList;
import java.util.List;

class BSTIterator {
    List<Integer> list;
    int count;
    public BSTIterator(TreeNode root) {
        list = new ArrayList<>();
        count = 0;
        aidBST(root);
    }

    private void aidBST(TreeNode root){
        if (root==null)return;
        if (root.left!=null)
            aidBST(root.left);
        list.add(root.val);
        if (root.right!=null)
            aidBST(root.right);
    }
    
    /** @return the next smallest number */
    public int next() {
        return list.get(count++);
    }
    
    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return count<list.size();
    }
}