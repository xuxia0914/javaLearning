package cn.xux.thread.creat;

/**
 * 创建线程方式一：
 * 1、创建：继承Thread+重写run
 * 2、启动：创建子类对象+start
 */
public class StartThread extends Thread {

    /**
     * 线程入口点
     */
    @Override
    public void run() {
        for(int i=0;i<20;i++) {
            System.out.println("一边听歌");
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        //创建子类线程
        StartThread st = new StartThread();
        //启动
        st.start();

        for(int i=0;i<20;i++) {
            System.out.println("一边coding");
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
