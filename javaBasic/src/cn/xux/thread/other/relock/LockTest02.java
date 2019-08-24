package cn.xux.thread.other.relock;

/**
 * 不可重入锁：锁不可以延续使用
 */
public class LockTest02 {

    Lock02 lock = new Lock02();

    public void a() {
        lock.lock();
        b();
        lock.unlock();
    }

    //不可重入
    public void b() {
        lock.lock();
        //.............
        lock.unlock();
    }

    public static void main(String[] args) {
        LockTest02 lock = new LockTest02();
        lock.a();
    }

}

//不可重入锁
class Lock02 {
    //是否占有
    private boolean isLocked = false;
    //使用锁
    public synchronized void lock() {
        while(isLocked) {
            try {
                System.out.println("lock.isLocked=true-->wait");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("lock.isLocked=false-->update");
        isLocked = true;
    }
    //释放锁
    public synchronized void unlock() {
        System.out.println("unlock()");
        isLocked = false;
        notifyAll();
    }

}