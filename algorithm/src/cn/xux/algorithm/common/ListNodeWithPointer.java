package cn.xux.algorithm.common;

/**
 * 链表,每个节点有一个额外的属性，指向链表中的任意一个节点或者null
 */
public class ListNodeWithPointer {
    public int val;
    public ListNodeWithPointer next;
    public ListNodeWithPointer point;

    public ListNodeWithPointer(int i) {
        this.val=i;
    }

    public int val() {
        return val;
    }

}
