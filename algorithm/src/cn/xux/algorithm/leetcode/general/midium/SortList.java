package cn.xux.algorithm.leetcode.general.midium;

import cn.xux.algorithm.common.ListNode;

/**
 * 148. 排序链表
 * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
 *
 * 进阶：
 * 你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？
 *
 * 示例 1：
 * 输入：head = [4,2,1,3]
 * 输出：[1,2,3,4]
 *
 * 示例 2：
 * 输入：head = [-1,5,3,4,0]
 * 输出：[-1,0,3,4,5]
 *
 * 示例 3：
 * 输入：head = []
 * 输出：[]
 *
 * 提示：
 * 链表中节点的数目在范围 [0, 5 * 104] 内
 * -105 <= Node.val <= 105
 */
public class SortList {

    public ListNode sortList(ListNode head) {
        if(head==null||head.next==null) {
            return head;
        }
        ListNode left=head, right=head, mid=head, res = new ListNode(0), curr=res;
        while(right.next!=null&&right.next.next!=null) {
            right=right.next.next;
            mid = mid.next;
        }
        right = mid.next;
        mid.next = null;
        left = sortList(left);
        right = sortList(right);
        while(left!=null||right!=null) {
            while(left==null) {
                curr.next = right;
                return res.next;
            }
            while(right==null) {
                curr.next = left;
                return res.next;
            }
            if(left.val<right.val) {
                curr.next = left;
                curr = curr.next;
                left = left.next;
            }else{
                curr.next = right;
                curr = curr.next;
                right = right.next;
            }
        }
        return res.next;
    }

}
