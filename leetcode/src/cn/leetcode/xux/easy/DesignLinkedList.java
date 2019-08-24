package cn.leetcode.xux.easy;

/**
 * Design your implementation of the linked list.
 * You can choose to use the singly linked list or the doubly linked list.
 * A node in a singly linked list should have two attributes: val and next.
 * val is the value of the current node, and next is a pointer/reference to the next node.
 * If you want to use the doubly linked list,
 * you will need one more attribute prev to indicate the previous node in the linked list.
 * Assume all nodes in the linked list are 0-indexed.
 * Implement these functions in your linked list class:
 * get(index) : Get the value of the index-th node in the linked list. If the index is invalid, return -1.
 * addAtHead(val) : Add a node of value val before the first element of the linked list.
 * After the insertion, the new node will be the first node of the linked list.
 * addAtTail(val) : Append a node of value val to the last element of the linked list.
 * addAtIndex(index, val) : Add a node of value val before the index-th node in the linked list.
 * If index equals to the length of linked list, the node will be appended to the end of linked list.
 * If index is greater than the length, the node will not be inserted.
 * deleteAtIndex(index) : Delete the index-th node in the linked list, if the index is valid.
 * Example:
 * MyLinkedList linkedList = new MyLinkedList();
 * linkedList.addAtHead(1);
 * linkedList.addAtTail(3);
 * linkedList.addAtIndex(1, 2);  // linked list becomes 1->2->3
 * linkedList.get(1);            // returns 2
 * linkedList.deleteAtIndex(1);  // now the linked list is 1->3
 * linkedList.get(1);            // returns 3
 * Note:
 * All values will be in the range of [1, 1000].
 * The number of operations will be in the range of [1, 1000].
 * Please do not use the built-in LinkedList library.
 */
public class DesignLinkedList {

    public static void main(String[] args) {
        /*MyLinkedList linkedList = new MyLinkedList();
        linkedList.addAtHead(1);
        linkedList.addAtTail(3);
        linkedList.addAtIndex(1, 2);  // linked list becomes 1->2->3
        System.out.println(linkedList.get(1));            // returns 2
        linkedList.deleteAtIndex(1);  // now the linked list is 1->3
        System.out.println(linkedList.get(1));            // returns 3*/
        /*["MyLinkedList","addAtHead","addAtIndex","addAtIndex","addAtTail","addAtHead","addAtIndex","addAtIndex","addAtIndex","addAtTail","addAtTail","deleteAtIndex"]
            [[],[0],[1,9],[1,5],[7],[1],[5,8],[5,2],[3,0],[1],[0],[6]]*/
        MyLinkedList linkedList = new MyLinkedList();
        linkedList.addAtHead(0);
        linkedList.addAtIndex(1, 9);
        linkedList.addAtIndex(1, 5);
        linkedList.addAtTail(7);
        linkedList.addAtHead(1);
        linkedList.addAtIndex(5, 8);
        linkedList.addAtIndex(5, 2);
        linkedList.addAtIndex(3, 0);
        linkedList.addAtTail(1);
        linkedList.addAtTail(0);
        linkedList.deleteAtIndex(6);
    }

}

class MyLinkedList {

    Node head = null, tail = null;
    int size = 0;


    /** Initialize your data structure here. */
    public MyLinkedList() {

    }

    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        if(index>=0&&index<size) {
            Node curr = this.head;
            while(curr!=null&&index>0) {
                curr = curr.next;
                index--;
            }
            if(curr!=null) {
                return curr.val;
            }
        }
        return -1;
    }

    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        Node newHead = new Node(val);
        if(this.head==null) {
            this.tail = newHead;
        }
        newHead.next = this.head;
        this.head = newHead;
        this.size++;
    }

    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        Node newTail = new Node(val);
        if(this.tail==null) {
            this.head = newTail;
            this.tail = newTail;
        }else {
            this.tail.next = newTail;
            this.tail = newTail;
        }
        this.size++;
    }

    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        if(index<=0) {
            addAtHead(val);
        }else if(index==this.size) {
            addAtTail(val);
        }else if(index>0&&index<this.size) {
            Node curr = this.head;
            for(int i=index;i>1;i--) {
                curr = curr.next;
            }
            Node newNode = new Node(val);
            newNode.next = curr.next;
            curr.next = newNode;
            this.size++;
        }
    }

    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        if(index<0||index>=this.size) {
            return;
        }
        if(this.size==1) {
            this.tail=null;
            this.head=null;
        }else if(index<=0) {
            this.head = this.head.next;
        }else {
            Node curr = this.head;
            for(int i=index;i>1;i--) {
                curr = curr.next;
            }
            curr.next = curr.next.next;
            if(curr.next==null) {
                this.tail = curr;
            }
        }
        this.size--;
    }
}

class Node {
    int val;
    Node next;

    Node(int val) {
        this.val = val;
    }

}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */