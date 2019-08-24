package cn.xux.thread.concurrent;

/**
 * 卖票
 * 线程安全：数据准确性
 * synchronized
 * 1、修饰方法
 * 即锁定当前对象
 */
public class SynchronizedTest02 {

    public static void main(String[] args) {
        SafeWeb12306 safeWeb12306 = new SafeWeb12306(10);
        Thread thread1 = new Thread(safeWeb12306, "用户1");
        Thread thread2 = new Thread(safeWeb12306, "用户2");
        Thread thread3 = new Thread(safeWeb12306, "用户3");
        thread1.start();
        thread2.start();
        thread3.start();
    }

}

class SafeWeb12306 implements Runnable {

    int ticketsNum;
    boolean flag = true;

    public SafeWeb12306(int n) {
        this.ticketsNum = n;
    }

    @Override
    public void run() {
        while(flag) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            test();
            test1();
        }

    }

    public synchronized void test() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"-->"+ticketsNum--);

    }

    //尽可能锁定合理的范围
    public void test1() {
        //考虑没有票的情况
        if(ticketsNum<=0) {
            flag = false;
            return;
        }
        synchronized(this) {
            //考虑最后一张票的情况
            if(ticketsNum<=0) {
                flag = false;
                return;
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"-->"+ticketsNum--);
        }


    }

}
