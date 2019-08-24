package cn.xux.thread.other.timer;

import java.util.*;

public class TimerTest {

    public static void main(String[] args) {
        Timer timer = new Timer();
//        timer.schedule(new MyTimerTask("job1"), 3000l);    //3秒后执行一次
//        timer.schedule(new MyTimerTask("job2"), new Date(System.currentTimeMillis()+5000l));    //5秒后执行一次
//        timer.schedule(new MyTimerTask("job3"), 5000l, 3000l);    //5秒后开始, 每隔3秒执行一次
        timer.schedule(new MyTimerTask("job3"), new Date(System.currentTimeMillis()+5000l), 3000l);    //5秒后开始, 每隔3秒执行一次
    }

}

class MyTimerTask extends TimerTask {

    String name;

    MyTimerTask(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("----任务：" + this.name + "开始----");
        for(int i=1;i<=10;i++) {
            System.out.println("任务：" + this.name + "执行进度-->"+i);
        }
        System.out.println("----任务：" + this.name + "结束----");
    }
}