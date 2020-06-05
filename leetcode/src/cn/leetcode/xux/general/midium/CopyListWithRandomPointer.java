package cn.leetcode.xux.general.midium;

import cn.leetcode.xux.common.ListNodeWithPointer;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * 138. 复制带随机指针的链表
 * 给定一个链表，每个节点包含一个额外增加的随机指针，该指针可以指向链表中的任何节点或空节点。
 * 要求返回这个链表的 深拷贝。
 * 我们用一个由 n 个节点组成的链表来表示输入/输出中的链表。每个节点用一个 [val, random_index] 表示：
 * val：一个表示 Node.val 的整数。
 * random_index：随机指针指向的节点索引（范围从 0 到 n-1）；如果不指向任何节点，则为  null 。
 *
 * 提示：
 * -10000 <= Node.val <= 10000
 * Node.random 为空（null）或指向链表中的节点。
 * 节点数目不超过 1000 。
 */
public class CopyListWithRandomPointer {

    public ListNodeWithPointer copyRandomList(ListNodeWithPointer node) {
        if(node==null) {
            return null;
        }
        Queue<ListNodeWithPointer> queue = new LinkedList<ListNodeWithPointer>();
        ListNodeWithPointer newNode = new ListNodeWithPointer(node.val);
        Map<ListNodeWithPointer, ListNodeWithPointer> map = new HashMap<ListNodeWithPointer, ListNodeWithPointer>();
        map.put(node, newNode);
        queue.add(node);

        while(!queue.isEmpty()) {
            ListNodeWithPointer curr = queue.poll();
            if(curr.next!=null) {
                if(map.containsKey(curr.next)) {
                    map.get(curr).next = map.get(curr.next);
                }else {
                    ListNodeWithPointer newNext = new ListNodeWithPointer(curr.next.val);
                    map.get(curr).next = newNext;
                    map.put(curr.next, newNext);
                    queue.offer(curr.next);
                }
            }
            if(curr.point!=null) {
                if(map.containsKey(curr.point)) {
                    map.get(curr).point = map.get(curr.point);
                }else {
                    ListNodeWithPointer newPoint = new ListNodeWithPointer(curr.point.val);
                    map.get(curr).point = newPoint;
                    map.put(curr.point, newPoint);
                    queue.offer(curr.point);
                }
            }
        }
        return newNode;
    }

}
