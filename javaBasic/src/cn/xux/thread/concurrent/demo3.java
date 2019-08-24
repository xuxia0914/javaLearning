package cn.xux.thread.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 线程安全的容器的使用
 */
public class demo3 {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        for(int i=0;i<100;i++) {
            new Thread(()->{
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                synchronized (list){
                    list.add(Thread.currentThread().getName());
//                }
            }).start();
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(list.size());
        System.out.println(list);
        /*List<Integer> list = new CopyOnWriteArrayList<>();
        for(int i=0;i<10;i++) {
            new Thread(()->list.add(1)).start();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(list.size());
        System.out.println(list);*/
    }

}