package cn.xux.thread.creat;

import java.util.concurrent.*;

/**
 * 了解创建线程的方法3，实现callable接口
 */
public class CallableDownloader implements Callable<Boolean> {

    private String url; //远程路径
    private String name;    //存储名字

    public CallableDownloader(String url, String name) {
        this.url = url;
        this.name = name;
    }

    @Override
    public Boolean call() throws Exception {
        WebDownloader wd = new WebDownloader();
        wd.download(url, name);
        System.out.println(name);
        return true;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CallableDownloader cd1 = new CallableDownloader("http://images.ali213.net/picfile/pic/2013/03/27/927_ckxt%20(9).jpg", "Thread/images/ac1.jpg");
        CallableDownloader cd2 = new CallableDownloader("https://c-ssl.duitang.com/uploads/item/201403/21/20140321005036_JSW2f.jpeg", "Thread/images/ac2.jpg");
        CallableDownloader cd3 = new CallableDownloader("http://images.ali213.net/picfile/pic/2013/03/27/927_ckxt (15).jpg", "Thread/images/ac3.jpg");

        //常见执行服务
        ExecutorService ser = Executors.newFixedThreadPool(3);
        //执行提交
        Future<Boolean> result1 = ser.submit(cd1);
        Future<Boolean> result2 = ser.submit(cd2);
        Future<Boolean> result3 = ser.submit(cd3);

        //获取结果
        boolean r1 = result1.get();
        boolean r2 = result2.get();
        boolean r3 = result3.get();

        //关闭服务
        ser.shutdownNow();

    }

}
