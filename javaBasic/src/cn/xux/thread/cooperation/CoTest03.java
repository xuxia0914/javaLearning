package cn.xux.thread.cooperation;

/**
 * 协作模型：生产者和消费者实现方式一：信号灯法
 * 添加标志位
 */
public class CoTest03 {

    public static void main(String[] args) {
        Paper paper = new Paper();
        new Writer(paper).start();
        new Reader(paper).start();
    }

}

//写入者
class Writer extends Thread {

    Paper paper;

    Writer(Paper paper) {
        this.paper = paper;
    }

    @Override
    public void run() {
        for(int i=0;i<20;i++) {
            this.paper.write(String.valueOf(i+1));
        }
    }

}

//阅读者
class Reader extends Thread {

    Paper paper;

    Reader(Paper paper) {
        this.paper = paper;
    }

    @Override
    public void run() {
        for(int i=0;i<20;i++) {
            this.paper.read();
        }
    }

}

//纸
class Paper {

    String content;
    //T 写字 F 阅读
    boolean flag = true;

    //写入内容
    synchronized void write(String content) {
        if(!this.flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("在纸上写上了：" + content);
        this.content = content;
        this.notifyAll();
        this.flag = !this.flag;
    }

    //阅读内容
    synchronized String read() {
        if(this.flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("从纸上读到了：" + this.content);
        this.notifyAll();
        this.flag = !this.flag;
        return this.content;
    }

}
