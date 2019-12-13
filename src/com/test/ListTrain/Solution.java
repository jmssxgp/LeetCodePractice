package com.test.ListTrain;



/**
 * @author xgp
 * @version 1.0
 * @date 2019/10/9 19:27
 */
public class Solution {
    /**
     * 寻找相交链表的交点，使用同时遍历的方法，间接得到两链表长度差值。
     * 也可使用另一种方法：连接某一条链表的头尾，问题转化为寻找带环链表的入环第一个节点。
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA==null||headB==null)
            return null;
        ListNode p = headA, q = headB;
        while (p!=null&&q!=null){
            p = p.next;
            q = q.next;
        }
        if (p==null){
            p = headB;
            while (q!=null){
                p = p.next;
                q = q.next;
            }
            q = headA;
        }else {
            q = headA;
            while (p!=null){
                p = p.next;
                q = q.next;
            }
            p = headB;
        }
        while (p!=null&&q!=null){
            if (p==q)return p;
            p = p.next;
            q = q.next;
        }
        return null;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head.next==null&&n==1)return null;
        ListNode t = head, q = head;
        while (n!=0){
            t = t.next;
            n--;
        }
        if (t==null)
            return head.next;
        while (t.next!=null){
            t = t.next;
            q = q.next;
        }
        q.next = q.next.next;
        return head;
    }

    public ListNode oddEvenList(ListNode head) {
        if (head==null||head.next==null)return head;
        ListNode p = new ListNode(-1), q = new ListNode(-1);
        ListNode r = p, t = q;
        int odd = 1;
        while (head!=null){
            if (odd%2==1){
                p.next = head;
                p = p.next;
                head = head.next;
                p.next = null;
            }else {
                q.next = head;
                q = q.next;
                head = head.next;
                q.next = null;
            }
            odd++;
        }
        p.next = t.next;
        return  r.next;
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode res = new ListNode(-1), p = res;
        while (l1!=null&&l2!=null){
            if (l1.val<=l2.val){
                p.next = l1;
                p = p.next;
                l1 = l1.next;
                p.next=null;
            }else {
                p.next = l2;
                p = p.next;
                l2 = l2.next;
                p.next=null;
            }
        }
        if (l1!=null){
            p.next=l1;
        }else {
            p.next=l2;
        }
        return res.next;
    }


    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode p = l1, q = l2, res = new ListNode(-1), t =res;
        while (p!=null&&q!=null){
            int sum = carry + p.val+q.val;
            t.next = new ListNode(sum%10);
            carry = sum/10;
            t = t.next;
            p = p.next;
            q = q.next;
        }
        if (p!=null){
            while (p!=null){
                int sum = carry + p.val;
                t.next = new ListNode(sum%10);
                carry = sum/10;
                t = t.next;
                p = p.next;
            }
        }else {
            while (q!=null){
                int sum = carry + q.val;
                t.next = new ListNode(sum%10);
                carry = sum/10;
                t = t.next;
                q = q.next;
            }
        }
        if (carry!=0){
            t.next = new ListNode(1);
        }
        return res.next;
    }


    public Node flatten(Node head) {
        Node res = new Node(), p = res;
        if (head == null)
            return head;
        while (head!=null){
            if (head.child==null){
                p.next = head;
                head = head.next;
                p = p.next;
            }else {
                p.next = head;
                p = p.next;
                Node t = head.next;
                p.next = flatten(head.child);
                if (p.next.next!=null){
                    p.next.next.prev = p.next;
                }
                head = t;
                while (p.next!=null){
                    p= p.next ;
                }

            }
        }
        return res.next;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) return null;
        if (k <= 0) return null;
        if (k == 1) return head;
        ListNode toHead = new ListNode(0);
        toHead.next = head;
        ListNode temp = toHead;
        while (temp.next != null) {
            temp = reverseSingleK(temp, k);
        }
        return toHead.next;
    }

    public ListNode reverseSingleK(ListNode before, int k) {
        ListNode pre = before.next;
        ListNode cur = pre.next;
        int flag = 0;
        for (int i = 1; i < k; i ++) {
            if (cur == null) {
                flag = i;
                break;
            }
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        if (flag != 0) {
            ListNode ret = pre;
            cur = pre.next;
            for (int i = 2; i < flag; i ++) {
                ListNode temp = cur.next;
                cur.next = pre;
                pre = cur;
                cur = temp;
            }
            ret.next = null;
            return ret;
        } else {
            ListNode ret = before.next;
            before.next = pre;
            ret.next = cur;
            return ret;
        }
    }

}
