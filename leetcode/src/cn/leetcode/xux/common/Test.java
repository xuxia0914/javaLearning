package cn.leetcode.xux.common;

import javax.management.openmbean.InvalidOpenTypeException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Test {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head==null) {
            return null;
        }
        ListNode newHead = new ListNode(0);
        newHead.next = head;
        ListNode left = newHead;
        ListNode right = newHead;
        while(n-->0) {
            right = right.next;
        }
        while(right.next!=null) {
            left = left.next;
            right = right.next;
        }
        left.next = left.next.next;
        return newHead.next;
    }

    public boolean isValid(String s) {
        if(s==null) {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        for(char c : s.toCharArray()) {
            if(c=='('||c=='['||c=='{') {
                stack.push(c);
            }else {
                switch (c) {
                    case ')' :
                        if(stack.isEmpty()||stack.pop()!='(') {
                            return false;
                        }
                        break;
                    case ']' :
                        if(stack.isEmpty()||stack.pop()!='[') {
                            return false;
                        }
                        break;
                    case '}' :
                        if(stack.isEmpty()||stack.pop()!='{') {
                            return false;
                        }
                        break;
                    default :
                        return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        Test test = new Test();
        /*Chopsticks chopsticks = new Chopsticks();
        Bowl bowl = new Bowl();
        User user1 = new User(chopsticks, bowl, false);
        User user2 = new User(chopsticks, bowl, true);
        new Thread(user1).start();
        new Thread(user2).start();*/
    }

}

class Chopsticks {
    boolean status = false;
}

class Bowl {
    boolean status = false;
}

class User implements Runnable {

    boolean flag;

    Chopsticks chopsticks;

    Bowl bowl;

    User(Chopsticks chopsticks, Bowl bowl, boolean flag) {
        this.chopsticks = chopsticks;
        this.bowl = bowl;
        this.flag = flag;
    }

    void getChopsticks() {
        while(this.chopsticks.status) {
            System.out.println(Thread.currentThread()+"chopsticks is used, can't get");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.chopsticks.status = true;
        System.out.println(Thread.currentThread()+"get chopsticks");
    }

    void getBowl() {
        while(this.bowl.status) {
            System.out.println(Thread.currentThread()+"bowl is used, can't get");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.bowl.status = true;
        System.out.println(Thread.currentThread()+"get bowl");
    }

    @Override
    public void run() {
        if(this.flag) {
            getChopsticks();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            getBowl();
        }else {
            getBowl();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            getChopsticks();
        }
        System.out.println(Thread.currentThread()+"end");
    }

}
