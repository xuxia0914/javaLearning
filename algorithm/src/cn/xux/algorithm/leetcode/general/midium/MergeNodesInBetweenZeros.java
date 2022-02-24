package cn.xux.algorithm.leetcode.general.midium;

import cn.xux.algorithm.common.ListNode;

/**
 * 2181. 合并零之间的节点
 * 给你一个链表的头节点 head ，该链表包含由 0 分隔开的一连串整数。
 * 链表的 开端 和 末尾 的节点都满足 Node.val == 0 。
 * <p>
 * 对于每两个相邻的 0 ，请你将它们之间的所有节点合并成一个节点，
 * 其值是所有已合并节点的值之和。
 * 然后将所有 0 移除，修改后的链表不应该含有任何 0 。
 * <p>
 * 返回修改后链表的头节点 head 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：head = [0,3,1,0,4,5,2,0]
 * 输出：[4,11]
 * 解释：
 * 上图表示输入的链表。修改后的链表包含：
 * - 标记为绿色的节点之和：3 + 1 = 4
 * - 标记为红色的节点之和：4 + 5 + 2 = 11
 * 示例 2：
 * <p>
 * <p>
 * 输入：head = [0,1,0,3,0,2,2,0]
 * 输出：[1,3,4]
 * 解释：
 * 上图表示输入的链表。修改后的链表包含：
 * - 标记为绿色的节点之和：1 = 1
 * - 标记为红色的节点之和：3 = 3
 * - 标记为黄色的节点之和：2 + 2 = 4
 * <p>
 * <p>
 * 提示：
 * <p>
 * 列表中的节点数目在范围 [3, 2 * 105] 内
 * 0 <= Node.val <= 1000
 * 不 存在连续两个 Node.val == 0 的节点
 * 链表的 开端 和 末尾 节点都满足 Node.val == 0
 */
public class MergeNodesInBetweenZeros {

    public ListNode mergeNodes(ListNode head) {
        ListNode ans = new ListNode(0);
        ListNode tail = ans;
        ListNode curr = head.next;
        int sum = 0;
        while (curr != null) {
            if (curr.val == 0) {
                tail.next = new ListNode(sum);
                tail = tail.next;
                sum = 0;
            } else {
                sum += curr.val;
            }
            curr = curr.next;
        }
        return ans.next;
    }

}
