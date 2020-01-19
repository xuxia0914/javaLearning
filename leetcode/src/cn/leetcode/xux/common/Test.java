package cn.leetcode.xux.common;

import javax.management.openmbean.InvalidOpenTypeException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Test {

    public boolean canJump(int[] nums) {
        int n = nums.length;
        int nearly = n-1;
        for(int i=n-1;i>=0;i--) {
            if(nearly-i<=nums[i]) {
                nearly = i;
            }
        }
        return nearly==0;
    }

    public static void main(String[] args) {
        Test test = new Test();
        System.out.println(test.canJump(new int[]{2,3,1,1,4}));
        System.out.println(test.canJump(new int[]{3,2,1,0,4}));
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
