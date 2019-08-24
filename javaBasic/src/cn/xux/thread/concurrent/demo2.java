package cn.xux.thread.concurrent;

/**
 * 火车票购票网
 */
public class demo2 {

    public static void main(String[] args) {
        Web12306 web = new Web12306(5, "购票系统");
        new Passenger(web, "用户1", 4).start();
        new Passenger(web, "用户2", 1).start();
        new Passenger(web, "用户3", 1).start();
        new Passenger(web, "用户4", 1).start();
        new Passenger(web, "用户5", 1).start();
    }

}

class Web12306 implements Runnable {
    int ticketsNum;
    String name;


    public Web12306(int ticketsNum, String name) {
        this.ticketsNum = ticketsNum;
        this.name = name;
    }

    @Override
    public void run() {
        Passenger passenger = (Passenger)Thread.currentThread();
        boolean flag;
        if(this.ticketsNum<passenger.buyNum) {
            flag = false;
        }else {
            flag = this.buy(passenger.buyNum);
        }
        if(flag) {
            System.out.println(passenger.getName()+"购买"+passenger.buyNum+"张票，剩余"+this.ticketsNum+"张，");
        }else {
            System.out.println(passenger.getName()+"购买"+passenger.buyNum+"张票，购买失败，余票不足");
        }
    }

    public synchronized boolean buy(int buyNum) {
        if(this.ticketsNum<buyNum) {
            return false;
        }
        this.ticketsNum -= buyNum;
        return true;
    }

}

class Passenger extends Thread {

    int buyNum;

    public Passenger(Runnable target, String name, int buyNum) {
        super(target, name);
        this.buyNum = buyNum;
    }

}

