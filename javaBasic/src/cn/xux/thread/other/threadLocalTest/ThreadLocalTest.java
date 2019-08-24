package cn.xux.thread.other.threadLocalTest;

/**
 * ThreadLocal的作用是提供线程内的局部变量，这种变量在线程的生命周期内起作用
 * 减少同一个线程内多个函数或者组件之间一些公共变量的传递的复杂度。
 * get()/set()/initialValue
 *
 * 每个线程自身变量的修改不会影响其他线程
 *
 * ThreadLocal的核心机制：
 *  * 每个Thread线程内部都有一个ThreadLocalMap。
 *  * ThreadLocalMap里面存储线程本地对象（ThreadLocal）和线程的变量副本（value）
 *  * 但是，Thread内部的Map是由ThreadLocal维护的，由ThreadLocal负责向map获取和设置线程的变量值。
 *
 * 每个ThreadLocal只能保存一个变量副本，如果想要上线一个线程能够保存多个副本以上，就需要创建多个ThreadLocal。
 * ThreadLocal内部的ThreadLocalMap键为弱引用，会有内存泄漏的风险。
 * 适用于无状态，副本变量独立后不影响业务逻辑的高并发场景。如果如果业务逻辑强依赖于副本变量，则不适合用ThreadLocal解决，需要另寻解决方案。
 *
 */
public class ThreadLocalTest {

    //private static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
    /*private static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>(){
        protected Integer initialValue() {
            return 200;
        }
    };*/
    private static ThreadLocal<Integer> threadLocal1 = ThreadLocal.withInitial(()->200);
    private static ThreadLocal<String> threadLocal2 = ThreadLocal.withInitial(()->"");

    public static void main(String[] args) {
        //threadLocal.set(1);
        System.out.println(Thread.currentThread().getName()+"-->"+threadLocal1.get());
        System.out.println(Thread.currentThread().getName()+"-->"+threadLocal2.get());
        /*new Thread(()-> {
            //threadLocal.set(2);
            System.out.println(Thread.currentThread().getName()+"-->"+threadLocal.get());
        }).start();*/

        for(int i=0;i<5;i++) {
            new Thread(new MyRun()).start();
        }

    }

    public static class MyRun implements Runnable {

        /*MyRun() {
            threadLocal.set(1);
            System.out.println(Thread.currentThread().getName()+"-->"+threadLocal.get());
        }*/

        @Override
        public void run() {
            //threadLocal.set(2);
            System.out.println(Thread.currentThread().getName()+"-->"+threadLocal1.get());
            threadLocal1.set(threadLocal1.get()-1);
            System.out.println(Thread.currentThread().getName()+"-->"+threadLocal1.get());

            System.out.println(Thread.currentThread().getName()+"-->"+threadLocal2.get());
            threadLocal2.set("hello");
            System.out.println(Thread.currentThread().getName()+"-->"+threadLocal2.get());
        }
    }

}