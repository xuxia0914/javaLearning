package cn.xux.thread.other.volatileTest;

/**
 * 指令重排：代码执行顺序与预期不一样
 * 目的：提高性能
 */
public class HappenBefore {
    static int a = 0;
    static boolean flag = false;

    public static void main(String[] args) {
        for(int i=0;i<20;i++) {
            a = 0;
            flag = false;
            Thread t1 = new Thread(() -> {
                a = 1;
                flag = true;
            });

            Thread t2 = new Thread(() -> {
                if (flag) {
                    System.out.println("flag=" + flag);
                }
                if (a == 0) {
                    System.out.println("a=" + a);
                }
            });

            t1.start();
            t2.start();

            try {
                t1.join();
                t2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
