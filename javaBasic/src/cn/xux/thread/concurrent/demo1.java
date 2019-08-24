package cn.xux.thread.concurrent;

import java.util.ArrayList;
import java.util.List;

/**
 * 电影院购票
 */
public class demo1 {

    public static void main(String[] args) {
        List<Integer> totalTickets = new ArrayList<>();
        totalTickets.add(1);
        totalTickets.add(2);
        totalTickets.add(3);
        totalTickets.add(4);
        Cinema cinema = new Cinema(totalTickets);

        List<Integer> buyTickets1 = new ArrayList<>();
        buyTickets1.add(1);
        buyTickets1.add(2);
        buyTickets1.add(3);
        User user1 = new User(cinema, "用户1", buyTickets1);

        List<Integer> buyTickets2 = new ArrayList<>();
        buyTickets2.add(3);
        buyTickets2.add(4);
        User user2 = new User(cinema, "用户2", buyTickets2);

        new Thread(user1).start();
        new Thread(user2).start();
    }

}

class Cinema {
    List<Integer> totalTickets;

    Cinema(List<Integer> totalTickets) {
        this.totalTickets = totalTickets;
    }

}

class User implements Runnable {
    Cinema cinema;
    String name;
    List<Integer> buyTickets;

    User(Cinema cinema, String name, List<Integer> buyTickets) {
        this.cinema = cinema;
        this.name = name;
        this.buyTickets = buyTickets;
    }


    @Override
    public void run() {
        List<Integer> copy = new ArrayList<>(this.cinema.totalTickets);
        copy.removeAll(this.buyTickets);
        if(copy.size()+this.buyTickets.size()!=this.cinema.totalTickets.size()) {
            System.out.println(this.name+"买票"+this.buyTickets+",余票不足");
            return;
        }
        synchronized (this.cinema) {
            List<Integer> copy1 = new ArrayList<>(this.cinema.totalTickets);
            copy1.removeAll(this.buyTickets);
            if(copy1.size()+this.buyTickets.size()!=this.cinema.totalTickets.size()) {
                System.out.println(this.name+"买票"+this.buyTickets+",余票不足");
                return;
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.cinema.totalTickets.removeAll(this.buyTickets);
            System.out.println(this.name+"买票"+this.buyTickets+"，剩余"+this.cinema.totalTickets);
        }
    }

}
