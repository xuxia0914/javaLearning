package cn.leetcode.xux.hard;

import cn.leetcode.xux.common.ListNode;

/**
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 * Example:
 * Input:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * Output: 1->1->2->3->4->4->5->6
 */
public class MergeKSortedLists {

    /**时间复杂度 O(k(length0+length1+length2+...length(k-1)))*/
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists==null||lists.length==0) {
            return null;
        }
        boolean areAllEmplty = true;
        for(int i=0;i<lists.length;i++) {
            if(lists[i]!=null) {
                areAllEmplty = false;
                break;
            }
        }
        if(areAllEmplty) {
            return null;
        }
        int min = Integer.MAX_VALUE;
        int index = 0;
        for(int i=0;i<lists.length;i++) {
            if(lists[i]!=null) {
                if(lists[i].val<min) {
                    min = lists[i].val;
                    index = i;
                }
            }
        }
        ListNode node = lists[index];
        lists[index] = lists[index].next;
        node.next = mergeKLists(lists);
        return node;
    }

    public static void main(String[] args) {
        ListNode node00 = new ListNode(1);
        ListNode node01 = new ListNode(4);
        ListNode node02 = new ListNode(5);
        node01.next = node02;
        node00.next = node01;
        ListNode node10 = new ListNode(1);
        ListNode node11 = new ListNode(3);
        ListNode node12 = new ListNode(4);
        node11.next = node12;
        node10.next = node11;
        ListNode node20 = new ListNode(2);
        ListNode node21 = new ListNode(6);
        node20.next = node21;
        ListNode result = new MergeKSortedLists().mergeKLists(new ListNode[]{node00, node10, node20});
        while (result!=null) {
            System.out.print(result.val + "->");
            result = result.next;
        }
    }

}
