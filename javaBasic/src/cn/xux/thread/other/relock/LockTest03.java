package cn.xux.thread.other.relock;

/**
 * 可重入锁：锁可以延续使用+计数器
 */
public class LockTest03 {

    Lock03 lock = new Lock03();

    public void a() {
        lock.lock();
        b();
        lock.unlock();
    }

    //可重入
    public void b() {
        lock.lock();
        //.............
        lock.unlock();
    }

    public static void main(String[] args) {
        LockTest03 lock = new LockTest03();
        //lock.a();
        for(int i=0;i<5;i++) {
            new Thread(()->lock.a()).start();
        }
    }

}

//不可重入锁
class Lock03 {
    //是否占有
    private boolean isLocked = false;
    Thread holdBy = null;
    private int holdCnt = 0;
    //使用锁
    public synchronized void lock() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while(isLocked&&holdBy!=Thread.currentThread()) {
            try {
                System.out.println(Thread.currentThread().getName() + " hold by other thread-->wait");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        isLocked = true;
        holdBy = Thread.currentThread();
        holdCnt++;
        System.out.println(Thread.currentThread().getName() + " lock-->holdCnt="+holdCnt);
    }
    //释放锁
    public synchronized void unlock() {
        if(isLocked) {
            if(holdBy==Thread.currentThread()) {
                holdCnt--;
                System.out.println(Thread.currentThread().getName() + " unlock-->holdCnt="+holdCnt);
                if(holdCnt==0) {
                    isLocked = false;
                    holdBy = null;
                    notifyAll();
                }
            }else {
                System.out.println(Thread.currentThread().getName() + "hold by other thread");
            }
        }else {
            System.out.println(Thread.currentThread().getName() + "is unlocked");
        }
    }

}