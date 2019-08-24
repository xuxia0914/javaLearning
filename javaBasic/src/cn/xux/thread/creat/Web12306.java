package cn.xux.thread.creat;

/**
 * Runable实现的线程方便资源共享
 */
public class Web12306 implements Runnable {
    //票数
    private int  tichetNums = 99;

    @Override
    public void run() {
        while(true) {
            if(tichetNums<=0) {
                break;
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"-->"+tichetNums--);
        }
    }

    public static void main(String[] args) {
        //一份资源多个代理
        Web12306 w = new Web12306();
        new Thread(w, "代理1").start();
        new Thread(w, "代理2").start();
        new Thread(w, "代理3").start();
    }

}
