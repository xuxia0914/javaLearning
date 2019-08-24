package cn.xux.thread.cooperation;

/**
 * 生产者和消费者模式
 */
public class CoTest01 {

    public static void main(String[] args) {
        Container01 container = new Container01();
        new Thread(new Productor01(container)).start();
        new Thread(new Consumer01(container)).start();
    }

}

//缓存
class Container01 {
    int cnt = 0;
    Bread01[] breads = new Bread01[10];

    void push(Bread01 bread) {
        this.breads[this.cnt++] = bread;
        System.out.println("放入面包-->"+bread.id+"，当前面包数-->"+this.cnt);

    }

    Bread01 pop() {
        Bread01 bread = this.breads[this.cnt-1];
        this.breads[this.cnt-1] = null;
        this.cnt--;
        System.out.println("取出面包-->"+bread.id+"，当前面包数-->"+this.cnt);
        return bread;
    }

}

//生产者
class Productor01 implements Runnable {

    Container01 container;

    Productor01(Container01 container) {
        this.container = container;
    }

    @Override
    public void run() {
        for(int i=1;i<=100;i++) {
            Bread01 bread = new Bread01(i);
            this.container.push(bread);
        }
    }

}

//消费者
class Consumer01 implements Runnable {

    Container01 container;

    Consumer01(Container01 container) {
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
class Bread01 {

    int id;

    Bread01(int id) {
        this.id = id;
    }

}

