package cn.xux.thread.concurrent;

/**
 * 取钱
 * 线程不安全,synchronized修饰方法，锁定当前对象，锁定对象不对，应该锁定account
 */
public class SynchronizedTest03 {

    public static void main(String[] args) {
        Account account = new Account(100);
        ATM atm1 = new ATM(account, 50);
        ATM atm2 = new ATM(account, 20);
        ATM atm3 = new ATM(account, 60);
        new Thread(atm1, "用户1").start();
        new Thread(atm2, "用户2").start();
        new Thread(atm3, "用户3").start();
    }

}

class ATM implements Runnable {

    int get;
    Account account;

    public ATM(Account account, int get) {
        this.account = account;
        this.get = get;
    }

    @Override
    public void run() {
        if(account.total<this.get) {
            return;
        }
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        test();
    }

    synchronized void test() {
        account.total -= this.get;
        System.out.println(Thread.currentThread().getName()+"-取走->"+this.get+",剩余->"+account.total);
    }

}
