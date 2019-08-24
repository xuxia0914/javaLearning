package cn.xux.thread.state;

/**
 * join：合并线程，插队线程
 */
public class BlockedJoin02 {

    public static void main(String[] args) {
        System.out.println("爸爸和儿子买烟的故事");
        new Thread(new Father()).start();
    }

}

class Father extends Thread {

    public void run() {
        System.out.println("想抽烟，发现没了");
        System.out.println("让儿子去买烟");
        Thread t = new Thread(new Son());
        t.start();
        try {
            t.join();   //father被阻塞
            System.out.println("老爸接过烟，把零钱给了儿子。。。");
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("儿子走丢了，老爸出去找儿子去了。。。");
        }
    }

}

class Son extends Thread {

    public void run() {
        System.out.println("儿子拿着老爸的钱出去了。。。");
        System.out.println("路边有个游戏厅，玩了10秒");
        for(int i=0;i<10;i++) {
            System.out.println(i+"秒过去了");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("赶紧买烟去...");
        System.out.println("手拿一包烟回家了...");
    }

}