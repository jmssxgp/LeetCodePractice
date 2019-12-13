package com.test.BinaryTree;

import com.test.HashTrain.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        StringBuilder res = new StringBuilder("[");
        q.offer(root);
        while (!q.isEmpty()){
            TreeNode t = q.poll();
            if (t==null){
                res.append("null");
            }else {
                res.append(t.val);
                q.offer(t.left);
                q.offer(t.right);
            }
            if (!q.isEmpty())
                res.append(",");
        }
        res.append("]");
        return res.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        data = data.substring(1,data.length()-1);
        Queue<TreeNode> q = new LinkedList<>();
        String[] t = data.split(",");
        TreeNode root;
        int count = 1;
        if (t[0].equals("null")){
            return null;
        }else {
            root = new TreeNode(Integer.parseInt(t[0]));
            q.offer(root);
            while (!q.isEmpty()){
                TreeNode p = q.poll();
                if(t[count].equals("null")){
                    p.left = null;
                    count++;
                }else {
                    TreeNode left = new TreeNode(Integer.parseInt(t[count++]));
                    p.left = left;
                    q.offer(left);
                }
                if(t[count].equals("null")){
                    p.right = null;
                    count++;
                }else {
                    TreeNode right = new TreeNode(Integer.parseInt(t[count++]));
                    p.right = right;
                    q.offer(right);
                }
            }
        }
        return root;
    }
}