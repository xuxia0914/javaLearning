package cn.leetcode.xux.general.midium;

import cn.leetcode.xux.common.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * We are given head, the head node of a linked list containing unique integer values.
 * We are also given the list G, a subset of the values in the linked list.
 * Return the number of connected components in G, where two values are connected if they appear consecutively in the linked list.
 * Example 1:
 * Input:
 * head: 0->1->2->3
 * G = [0, 1, 3]
 * Output: 2
 * Explanation:
 * 0 and 1 are connected, so [0, 1] and [3] are the two connected components.
 * Example 2:
 * Input:
 * head: 0->1->2->3->4
 * G = [0, 3, 1, 4]
 * Output: 2
 * Explanation:
 * 0 and 1 are connected, 3 and 4 are connected, so [0, 1] and [3, 4] are the two connected components.
 * Note:
 * If N is the length of the linked list given by head, 1 <= N <= 10000.
 * The value of each node in the linked list will be in the range [0, N - 1].
 * 1 <= G.length <= 10000.
 * G is a subset of all values in the linked list.
 */
public class LinkedListComponents {
    /**
     * Runtime: 7 ms, faster than 81.21% of Java online submissions for Linked List Components.
     * Memory Usage: 41.2 MB, less than 47.73% of Java online submissions for Linked List Components.
     * @param head
     * @param G
     * @return
     */
    public int numComponents(ListNode head, int[] G) {
        if(head==null||G==null||G.length==0) {
            return 0;
        }
        Set<Integer> set = new HashSet<>();
        for(int i : G) {
            set.add(i);
        }
        boolean flag = false;
        int res = 0;
        for(ListNode node=head;node!=null;node=node.next) {
            if(!flag&&!set.add(node.val)) {
                flag = true;
            }
            if(flag&&(set.add(node.val)||node.next==null)) {
                flag = false;
                res++;
            }
        }
        return res;
    }


    /**
     * Runtime: 38 ms, faster than 8.64% of Java online submissions for Linked List Components.
     * Memory Usage: 40 MB, less than 93.47% of Java online submissions for Linked List Components.
     * @param head
     * @param G
     * @return
     */
    public int numComponents1(ListNode head, int[] G) {
        if(head==null||G==null||G.length==0) {
            return 0;
        }
        boolean flag = false;
        int res = 0;
        for(ListNode node=head;node!=null;node=node.next) {
            if(!flag&&helper(node.val, G)) {
                flag = true;
            }
            if(flag&&(!helper(node.val, G)||node.next==null)) {
                flag =  false;
                res++;
            }
        }
        return res;
    }

    public boolean helper(int val, int[] G) {
        for(int i : G) {
            if(i==val) {
                return true;
            }
        }
        return false;
    }

}
