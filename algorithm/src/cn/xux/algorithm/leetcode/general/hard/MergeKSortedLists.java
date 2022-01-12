package cn.xux.algorithm.leetcode.general.hard;

import cn.xux.algorithm.common.ListNode;
import cn.xux.algorithm.common.TreeNode;

import java.util.Comparator;
import java.util.PriorityQueue;

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

    int ans = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        if(root==null) {
            return 0;
        }
        int curr = root.val;
        int left = dfs(root.left);
        int right = dfs(root.right);
        curr += Math.max(0, left)+ Math.max(0, right);
        ans = Math.max(ans, curr);
        maxPathSum(root.left);
        maxPathSum(root.right);
        return ans;
    }

    private int dfs(TreeNode node) {
        if(node==null) {
            return 0;
        }
        return Math.max(node.val, node.val+Math.max(dfs(node.left),dfs(node.right)));
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if(lists==null||lists.length==0) {
            return null;
        }
        PriorityQueue<ListNode> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));
        for(ListNode node : lists) {
            if(node!=null) {
                queue.offer(node);
            }
        }
        ListNode head = new ListNode(0);
        ListNode tail = head;
        while(!queue.isEmpty()) {
            ListNode curr = queue.poll();
            tail.next = new ListNode(curr.val);
            tail = tail.next;
            curr = curr.next;
            if(curr!=null) {
                queue.offer(curr);
            }
        }
        return head.next;
    }

    /**时间复杂度 O(k(length0+length1+length2+...length(k-1)))*/
    public ListNode mergeKLists1(ListNode[] lists) {
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
