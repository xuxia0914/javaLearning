package cn.leetcode.xux.common;

/**
 * 链表
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int i) {
        this.val=i;
    }

    public ListNode(int[] nums) {
        if(nums==null||nums.length==0) {
            return;
        }
        if(nums.length==1) {
            this.val = nums[0];
            return;
        }
        int curr = nums[0];
        int[] post = new int[nums.length-1];
        for(int i=0;i<nums.length-1;i++) {
            post[i] = nums[i+1];
        }
        this.val = curr;
        this.next = new ListNode(post);
    }

    public int val() {
        return val;
    }

    /**
     * 翻转链表
     * @param node
     * @return
     */
    public static ListNode reverse1(ListNode node) {
        ListNode pre = null;
        while(node!=null) {
            ListNode tmp = node.next;
            node.next = pre;
            pre = node;
            node = tmp;
        }
        return pre;
    }

    /**
     * 翻转链表 递归
     * @param node
     * @return
     */
    public static ListNode reverse2(ListNode node) {
        if(node==null||node.next==null) {
            return node;
        }
        ListNode pre = reverse2(node.next);
        node.next.next = node;
        node.next = null;
        return pre;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        ListNode curr = node1;
        while (curr!=null) {
            System.out.print(curr.val + "->");
            curr = curr.next;
        }
        System.out.println("");
        ListNode reList = reverse1(node1);
        while (reList!=null) {
            System.out.print(reList.val + "->");
            reList = reList.next;
        }
    }

}
