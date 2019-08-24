package cn.xux.thread.concurrent;

/**
 * 取钱
 * synchronized
 * 2、修饰块
 * 线程安全,锁定account
 */
public class SynchronizedTest04 {

    public static void main(String[] args) {
        Account account = new Account(100);
        SafeATM atm1 = new SafeATM(account, 50);
        SafeATM atm2 = new SafeATM(account, 50);
        SafeATM atm3 = new SafeATM(account, 60);
        new Thread(atm1, "用户1").start();
        new Thread(atm2, "用户2").start();
        new Thread(atm3, "用户3").start();
    }

}

class SafeATM implements Runnable {

    int get;
    Account account;

    public SafeATM(Account account, int get) {
        this.account = account;
        this.get = get;
    }

    @Override
    public void run() {
        if(account.total<this.get) {
            return;
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (account) {
            if(account.total<this.get) {
                return;
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            account.total -= this.get;
            System.out.println(Thread.currentThread().getName()+"-取走->"+this.get+",剩余->"+account.total);
        }
    }

}
