public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode head = new ListNode(1);
        ListNode p = head;
        int[] t = {4,3,2,5,2};
        for (int i:t
             ) {
            p.next = new ListNode(i);
            p = p.next;
        }
        ListNode r = solution.partition(head,3);
        while(r!=null){
            System.out.println(r.val);
            r=r.next;
        }
    }
}
