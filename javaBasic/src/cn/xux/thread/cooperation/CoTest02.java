package cn.xux.thread.cooperation;

/**
 * 协作模型：生产者和消费者实现方式一：管程法
 */
public class CoTest02 {

    public static void main(String[] args) {
        Container02 container = new Container02();
        new Thread(new Productor02(container)).start();
        new Thread(new Consumer02(container)).start();
    }

}

//缓存
class Container02 {
    int cnt = 0;
    Bread02[] breads = new Bread02[10];

    synchronized void push(Bread02 bread) {
        //缓存已满，不能生产
        if(this.cnt==this.breads.length) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.breads[this.cnt++] = bread;
        System.out.println("放入面包-->"+bread.id+"，当前面包数-->"+this.cnt);
        //已有资源，唤醒消费者
        this.notifyAll();

    }

    synchronized Bread02 pop() {
        //没有资源，进入等待
        if(this.cnt==0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Bread02 bread = this.breads[this.cnt-1];
        this.breads[this.cnt-1] = null;
        this.cnt--;
        System.out.println("取出面包-->"+bread.id+"，当前面包数-->"+this.cnt);
        //缓存有空间，唤醒生产者
        this.notifyAll();
        return bread;
    }

}

//生产者
class Productor02 implements Runnable {

    Container02 container;

    Productor02(Container02 container) {
        this.container = container;
    }

    @Override
    public void run() {
        for(int i=1;i<=100;i++) {
            Bread02 bread = new Bread02(i);
            this.container.push(bread);
        }
    }

}

//消费者
class Consumer02 implements Runnable {

    Container02 container;

    Consumer02(Container02 container) {
        this.container = container;
    }

    @Override
    public void run() {
        for(int i=1;i<=100;i++) {
            this.container.pop();
        }
    }

}

//资源
class Bread02 {

    int id;

    Bread02(int id) {
        this.id = id;
    }

}

