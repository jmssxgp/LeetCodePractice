package com.test.Recursion;

import com.test.HashTrain.TreeNode;
import com.test.ListTrain.ListNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author xgp
 * @version 1.0
 * @date 2019/11/6 11:24
 */
public class Solution {

    //递归反转链表
    public ListNode reverseList(ListNode head) {
        if (head==null||head.next==null)
            return head;
        //黑盒，默认已实现，已将head后所有节点翻转
        ListNode t = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return t;
    }

    public int fib(int N) {
        if (N<2){
            return N;
        }
        return fib(N-1)+fib(N-2);
    }

    public int climbStairs(int n) {
        if (n<=2)return n;
        int n1 = 1, n2 = 2, n3 = 3;
        for (int i = 3; i <= n; i++) {
            n3 = n1 + n2;
            n1 = n2;
            n2 = n3;
        }
        return n3;
    }

    public int kthGrammar(int N, int K) {
        if (N==1&&K==1)
            return 0;
        if (K>(1<<(N-2))){
            return kthGrammar(N-1, K-(1<<(N-2)))^1;
        }else {
            return kthGrammar(N-1, K);
        }
    }


    public List<TreeNode> generateTrees(int n) {
        if (n==0)
            return new ArrayList<>();
        return aidGenerateTrees(1, n);
    }
    private List<TreeNode> aidGenerateTrees(int low, int high){
        List<TreeNode> all_trees = new LinkedList<>();
        if (low>high){
            all_trees.add(null);
            return all_trees;
        }
        for (int i = low; i <= high; i++) {
            List<TreeNode> left = aidGenerateTrees(low, i-1);
            List<TreeNode> right = aidGenerateTrees(i+1, high);

            for (TreeNode j: left
            ) {
                for (TreeNode k: right
                     ) {
                    TreeNode l = new TreeNode(i);
                    l.right = k;
                    l.left = j;
                    all_trees.add(l);
                }
            }
        }
        return all_trees;
    }


}
