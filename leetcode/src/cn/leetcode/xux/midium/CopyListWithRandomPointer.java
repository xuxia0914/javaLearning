package cn.leetcode.xux.midium;

import cn.leetcode.xux.common.ListNodeWithPointer;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 *A linked list is given such that each node contains an additional random pointer
 * which could point to any node in the list or null.
 * Return a deep copy of the list.
 */
public class CopyListWithRandomPointer {

    public static ListNodeWithPointer solution(ListNodeWithPointer node) {
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
