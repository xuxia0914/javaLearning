package cn.xux.thread.concurrent;

/**
 * 卖票
 * 线程不安全
 */
public class SynchronizedTest01 {

    public static void main(String[] args) {
        UnsafeWeb12306 unsafeWeb12306 = new UnsafeWeb12306(10);
        Thread thread1 = new Thread(unsafeWeb12306, "用户1");
        Thread thread2 = new Thread(unsafeWeb12306, "用户2");
        Thread thread3 = new Thread(unsafeWeb12306, "用户3");
        thread1.start();
        thread2.start();
        thread3.start();
    }

}

class UnsafeWeb12306 implements Runnable {

    int ticketsNum;

    public UnsafeWeb12306(int n) {
        this.ticketsNum = n;
    }

    @Override
    public void run() {
        while(ticketsNum>0) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"-->"+ticketsNum--);
        }

    }

}
