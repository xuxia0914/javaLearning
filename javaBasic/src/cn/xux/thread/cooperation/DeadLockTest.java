package cn.xux.thread.cooperation;

/**
 * 死锁是这样一种情形：多个线程由于抢夺资源或者相互通信同时被阻塞，它们中的一个或者全部都在等待某个资源被释放
 * 由于线程被无限期地阻塞，因此程序不可能正常终止。
 *
 * java 死锁产生的四个必要条件：
 * 1、互斥使用，即当资源被一个线程使用(占有)时，别的线程不能使用
 * 2、不可抢占，资源请求者不能强制从资源占有者手中夺取资源，资源只能由资源占有者主动释放。
 * 3、请求和保持，即当资源请求者在请求其他的资源的同时保持对原有资源的占有。
 * 4、循环等待，即存在一个等待队列：P1占有P2的资源，P2占有P3的资源，P3占有P1的资源。这样就形成了一个等待环路。
 */
public class DeadLockTest {

    public static void main(String[] args) {
        Lipstick lipstick = new Lipstick();
        Mirror mirror = new Mirror();
        Girl girl1 = new Girl(1, lipstick, mirror);
        Girl girl2 = new Girl(2, lipstick, mirror);
        girl1.start();
        girl2.start();
    }

}

//模拟化妆

class Girl extends Thread {
    int id;
    Lipstick lipstick;
    Mirror mirror;

    Girl(int id, Lipstick lipstick, Mirror mirror) {
        this.id = id;
        this.lipstick = lipstick;
        this.mirror = mirror;
    }

    @Override
    public void run() {
        if(this.id==1) {
            synchronized(this.lipstick) {
                System.out.println("姑娘"+this.id+"获得口红");
                synchronized(this.mirror) {
                    System.out.println("姑娘"+this.id+"获得口红和镜子");
                }
            }
        }else {
            synchronized(this.mirror) {
                System.out.println("姑娘"+this.id+"获得镜子");
                synchronized(this.lipstick) {
                    System.out.println("姑娘"+this.id+"获得口红和镜子");
                }
            }
        }
    }
}

//口红
class Lipstick {

}

//镜子
class Mirror {

}