package cn.xux.thread.creat;

/**
 * 创建线程方式二：
 * 1、创建：实现Runable+重写run
 * 2、启动：创建实现类对象+Thread对象+start
 *
 * 推荐：避免单继承的局限性，优先使用接口
 * 方便共享资源
 *
 */
public class StartRunnable implements Runnable {

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
        //创建实现类线程
        StartRunnable sr = new StartRunnable();
        //创建代理类对象
        Thread t = new Thread(sr);
        //启动
        t.start();

        //合并三个步骤
        //new Thread(new StartRunable()).start();

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
