package cn.xux.thread.creat;

public class RunnableDownloader implements Runnable {

    private String url; //远程路径
    private String name;    //存储名字

    public RunnableDownloader(String url, String name) {
        this.url = url;
        this.name = name;
    }

    @Override
    public void run() {
        WebDownloader wd = new WebDownloader();
        wd.download(url, name);
    }

    public static void main(String[] args) {

        //启动三个线程
        new Thread(new RunnableDownloader("http://images.ali213.net/picfile/pic/2013/03/27/927_ckxt%20(9).jpg", "Thread/images/ac1.jpg")).start();
        new Thread(new RunnableDownloader("https://c-ssl.duitang.com/uploads/item/201403/21/20140321005036_JSW2f.jpeg", "Thread/images/ac2.jpg")).start();
        new Thread(new RunnableDownloader("http://images.ali213.net/picfile/pic/2013/03/27/927_ckxt (15).jpg", "Thread/images/ac3.jpg")).start();
    }

}
