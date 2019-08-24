package cn.xux.thread.state;

/**
 * sleep模拟休息
 */
public class BlockedSleep02 implements Runnable {

    private String winner;  //胜利者

    @Override
    public void run() {
        for(int steps=1;steps<=100;steps++) {
            //模拟休息
            if(Thread.currentThread().getName().equals("rabbit")&&steps%10==0) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

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
        BlockedSleep02 racer = new BlockedSleep02();
        new Thread(racer, "tortoise").start();
        new Thread(racer, "rabbit").start();
    }

}
