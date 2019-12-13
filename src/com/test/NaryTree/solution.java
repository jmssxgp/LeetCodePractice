package com.test.NaryTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author xgp
 * @version 1.0
 * @date 2019/10/28 11:01
 */
public class solution {

    List<Integer> list = new ArrayList<>();
    public List<Integer> preorder(Node root) {
        if (root!=null){
            list.add(root.val);
            for (Node t:root.children){
                preorder(t);
            }
        }
        return list;
    }

    public List<Integer> postorder(Node root) {
        if (root!=null){
            for (Node t:root.children){
                postorder(t);
            }
            list.add(root.val);
        }
        return list;
    }
}
