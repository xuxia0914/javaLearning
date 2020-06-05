package cn.leetcode.xux.general.midium;

import cn.leetcode.xux.common.ListNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * We are given a linked list with head as the first node.  Let's number the nodes in the list: node_1, node_2, node_3, ... etc.
 * Each node may have a next larger value: for node_i, next_larger(node_i) is the node_j.val such that j > i, node_j.val > node_i.val, and j is the smallest possible choice.  If such a j does not exist, the next larger value is 0.
 * Return an array of integers answer, where answer[i] = next_larger(node_{i+1}).
 * Note that in the example inputs (not outputs) below, arrays such as [2,1,5] represent the serialization of a linked list with a head node value of 2, second node value of 1, and third node value of 5.
 * Example 1:
 * Input: [2,1,5]
 * Output: [5,5,0]
 * Example 2:
 * Input: [2,7,4,3,5]
 * Output: [7,0,5,5,0]
 * Example 3:
 * Input: [1,7,5,1,9,2,5,1]
 * Output: [7,9,9,9,0,5,0,0]
 * Note:
 * 1 <= node.val <= 10^9 for each node in the linked list.
 * The given list has length in the range [0, 10000].
 */
public class NextGreaterNodeInLinkedList {
    /**
     * Runtime: 1069 ms, faster than 11.99% of Java online submissions for Next Greater Node In Linked List.
     * Memory Usage: 40.2 MB, less than 96.62% of Java online submissions for Next Greater Node In Linked List.
     * Next challenges:
     * @param head
     * @return
     */
    public int[] nextLargerNodes(ListNode head) {
        if(head==null) {
            return null;
        }
        ListNode tail = head;
        int n = 1;
        while(tail.next!=null) {
            tail = tail.next;
            n++;
        }
        int[] res = new int[n];
        int i = 0;
        ListNode curr = head;
        while(curr!=null) {
            ListNode tmp = curr;
            while(tmp!=null&&tmp.val<=curr.val) {
                tmp = tmp.next;
            }
            if(tmp==null) {
                res[i] = 0;
            }else {
                res[i] = tmp.val;
            }
            i++;
            curr = curr.next;
        }
        return res;
    }

    /**
     * Runtime: 39 ms, faster than 65.70% of Java online submissions for Next Greater Node In Linked List.
     * Memory Usage: 40.7 MB, less than 96.10% of Java online submissions for Next Greater Node In Linked List.
     * @param head
     * @return
     */
    public int[] nextLargerNodes1(ListNode head) {
        if(head==null) {
            return null;
        }
        List<Integer> nums = new ArrayList<>();
        for(ListNode node=head;node!=null;node=node.next) {
            nums.add(node.val);
        }
        int[] res = new int[nums.size()];
        Stack<Integer> stack = new Stack<>();
        for(int i=0;i<res.length;i++) {
            while(!stack.isEmpty()&&nums.get(stack.peek())<nums.get(i)) {
                res[stack.pop()] = nums.get(i);
            }
            stack.push(i);
        }
        return res;
    }

}
