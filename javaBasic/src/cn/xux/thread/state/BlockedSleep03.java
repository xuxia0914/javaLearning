package cn.xux.thread.state;

/**
 * sleep模拟倒计时
 */
public class BlockedSleep03 {

    public static void main(String[] args) {
        //倒数10个数，1秒1个
        int num = 10;
        while(num>=0) {
            try {
                System.out.println(num--);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
