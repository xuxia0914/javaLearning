package cn.xux.thread.state;

/**
 * 终止线程的两种方式
 * 1、线程正常执行完毕
 * 2、外部干涉-->加入标识
 * 不要是用stop()  destory()
 */
public class TerminateThread implements Runnable {
    //加入标识，标识线程体是否可以运行
    private boolean flag = true;

    private String name;

    public TerminateThread(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        int i=0;
        //关联标识
        while(flag) {
            System.out.println(name+"-->"+i++);
        }
    }

    //对外提供方法改变标识
    public void terminate() {
        this.flag = false;
    }

    public static void main(String[] args) {
        TerminateThread tt = new TerminateThread("线程1");
        new Thread(tt).start();

        for(int i=0;i<=99;i++) {
            if(i==88) {
                tt.terminate();
            }
            System.out.println("main++"+i);
        }
    }

}
