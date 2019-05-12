package sortedlinklist;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null)return head;
        if(head.next.val == head.val){
            head.next = head.next.next;
            deleteDuplicates(head);
        }else {
            deleteDuplicates(head.next);
        }
        return head;
    }
    public void scan(ListNode node){
        if(node != null){
            System.out.printf(String.valueOf(node.val).concat(","));
            scan(node.next);
        }
    }

    public static void main(String[] args) {
        int ii[] = new int[]{1,1,1};
        Solution solution = new Solution();
        ListNode root = solution.new ListNode(ii[0]);
        ListNode curr = root;
        for (int i = 1; i < ii.length; i++) {
            curr.next = solution.new ListNode(ii[i]);
            curr = curr.next;
        }
        solution.deleteDuplicates(root);
        solution.scan(root);
    }
}