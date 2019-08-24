package cn.xux.thread.creat;

/**
 * 模拟龟兔赛跑
 */
public class RacerRunnable implements Runnable {

    private String winner;  //胜利者

    @Override
    public void run() {
        for(int steps=1;steps<=100;steps++) {
            System.out.println(Thread.currentThread().getName()+"-->"+steps);
            //判断比赛是否结束
            boolean flag = gameOver(steps);
            if(flag) {
                break;
            }
        }
    }

    private boolean gameOver(int steps) {
        if(winner!=null) {
            return true;
        }else {
            if(steps==100) {
                winner  = Thread.currentThread().getName();
                System.out.println("winner==>"+winner);
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        RacerRunnable racer = new RacerRunnable();
        new Thread(racer, "tortoise").start();
        new Thread(racer, "rabbit").start();
    }

}
