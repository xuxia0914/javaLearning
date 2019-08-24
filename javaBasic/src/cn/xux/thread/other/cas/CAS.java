package cn.xux.thread.other.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 悲观锁：总是假设最坏的情况，每次去拿数据的时候都认为别人会修改，所以每次在拿数据的时候都会上锁
 * 这样别人想拿这个数据就会阻塞直到它拿到锁。传统的关系型数据库里边就用到了很多这种锁机制，
 * 比如行锁，表锁等，读锁，写锁等，都是在做操作之前先上锁
 * 再比如Java里面的同步原语synchronized关键字的实现也是悲观锁。
 * 悲观锁机制存在以下问题：　　
 *      1. 在多线程竞争下，加锁、释放锁会导致比较多的上下文切换和调度延时，引起性能问题。
 * 　　　2. 一个线程持有锁会导致其它所有需要此锁的线程挂起。
 * 　　　3. 如果一个优先级高的线程等待一个优先级低的线程释放锁会导致优先级倒置，引起性能风险。
 * 乐观锁：顾名思义，就是很乐观，每次去拿数据的时候都认为别人不会修改，所以不会上锁，
 * 但是在更新的时候会判断一下在此期间别人有没有去更新这个数据，可以使用版本号等机制。
 * 乐观锁适用于多读的应用类型，这样可以提高吞吐量，
 * 像数据库提供的类似于write_condition机制，其实都是提供的乐观锁。
 * 在Java中java.util.concurrent.atomic包下面的原子变量类就是使用了乐观锁的一种实现方式CAS实现的。
 * CAS缺点：
 * 　　　1. ABA问题： 从Java1.5开始JDK的atomic包里提供了一个类AtomicStampedReference来解决ABA问题，
 *              这个类的compareAndSet方法作用是首先检查当前引用是否等于预期引用，
 *              并且当前标志是否等于预期标志，如果全部相等，则以原子方式将该引用和该标志的值设置为给定的更新值。
 *      2. 循环时间长开销大：自旋CAS（不成功，就一直循环执行，直到成功）
 *      3. 只能保证一个共享变量的原子操作：Java1.5开始JDK提供了AtomicReference类来保证引用对象之间的原子性，你可以把多个变量放在一个对象里来进行CAS操作。
 *
 * CAS与Synchronized的使用情景：　　　
 * 　　　　1、对于资源竞争较少（线程冲突较轻）的情况，使用synchronized同步锁进行线程阻塞和唤醒切换以及用户态内核态间的切换操作额外浪费消耗cpu资源；而CAS基于硬件实现，不需要进入内核，不需要切换线程，操作自旋几率较少，因此可以获得更高的性能。
 * 　　　 2、对于资源竞争严重（线程冲突严重）的情况，CAS自旋的概率会比较大，从而浪费更多的CPU资源，效率低于synchronized。
 *
 * cas : compare and swap 比较并替换
 */
public class CAS {
    //库存
    private static AtomicInteger stock = new AtomicInteger(5);

    public static void main(String[] args) {
        for(int i=0;i<5;i++) {
            new Thread(()->{
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Integer left = stock.decrementAndGet();
                System.out.println(Thread.currentThread().getName()+"抢到了一件商品，剩余-->"+left+"件商品");
                if(left<1) {
                    System.out.println("抢完了！");
                }
            }).start();
        }
    }

}
