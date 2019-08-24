package cn.xux.thread.lambda;

/**
 * Lambdab表达式 简化线程(使用一次)的使用
 *
 */
public class LambdaThread {

    //静态内部类
    static class Test1 implements Runnable {
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
    }

    public static void main(String[] args) {

        new Thread(new Test1()).start();

        //局部内部类
        class Test2 implements Runnable {
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
        }
        new Thread(new Test2()).start();

        //匿名内部类 必须借助接口或者父类
        new Thread(new Runnable() {
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
        }).start();

        //jdk8 简化 lambda
        new Thread(()->{
            for(int i=0;i<20;i++) {
                System.out.println("一边听歌");
                try {
                    Thread.sleep(40);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

}
