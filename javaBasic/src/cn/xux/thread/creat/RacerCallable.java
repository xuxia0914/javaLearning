package cn.xux.thread.creat;

import java.util.concurrent.*;

/**
 * 模拟龟兔赛跑， 实现callable
 */
public class RacerCallable implements Callable<Integer> {

    private String winner;  //胜利者

    @Override
    public Integer call() throws Exception {
        for(int steps=1;steps<=100;steps++) {
            System.out.println(Thread.currentThread().getName()+"-->"+steps);
            //判断比赛是否结束
            boolean flag = gameOver(steps);
            if(flag) {
                return steps;
            }
        }
        return null;
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

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        RacerCallable racer = new RacerCallable();

        //常见执行服务
        ExecutorService ser = Executors.newFixedThreadPool(2);
        //执行提交
        Future<Integer> result1 = ser.submit(racer);
        Future<Integer> result2 = ser.submit(racer);

        //获取结果
        Integer r1 = result1.get();
        Integer r2 = result2.get();

        System.out.println(r1+"-->"+r2);

        //关闭服务
        ser.shutdownNow();
    }

}
