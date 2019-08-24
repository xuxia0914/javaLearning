package cn.xux.thread.state;

/**
 * sleep 模拟网络延时，方法了发生问题的概率
 */
public class BlockedSleep01 implements Runnable  {

    //票数
    private int  tichetNums = 99;

    @Override
    public void run() {
        while(true) {
            if(tichetNums<=0) {
                break;
            }
            //模拟延时
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
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
        BlockedSleep01 w = new BlockedSleep01();
        new Thread(w, "代理1").start();
        new Thread(w, "代理2").start();
        new Thread(w, "代理3").start();
    }

}
