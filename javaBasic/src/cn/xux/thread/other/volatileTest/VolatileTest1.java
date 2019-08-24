package cn.xux.thread.other.volatileTest;

/**
 * volatile用于保证数据的同步，也就是可见性
 * “轻量级synchronized”
 */
public class VolatileTest1 {

    private static int num = 0;

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

        num = 1;

    }

}
