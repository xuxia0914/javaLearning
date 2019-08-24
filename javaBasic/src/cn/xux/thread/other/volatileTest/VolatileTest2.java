package cn.xux.thread.other.volatileTest;

/**
 * volatile用于保证数据的同步，也就是可见性
 * “轻量级synchronized”
 */
public class VolatileTest2 {

    private volatile static int num = 0;

    public static void main(String[] args) {

        new Thread(()->{
            while(num==0) {

            }
        }).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            num = 1;
        }).start();

    }

}
