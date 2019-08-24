package cn.xux.thread.creat;

public class ThreadDownloader extends Thread {

    private String url; //远程路径
    private String name;    //存储名字

    public ThreadDownloader(String url, String name) {
        this.url = url;
        this.name = name;
    }

    @Override
    public void run() {
        WebDownloader wd = new WebDownloader();
        wd.download(url, name);
    }

    public static void main(String[] args) {
        ThreadDownloader td1 = new ThreadDownloader("https://c-ssl.duitang.com/uploads/item/201805/11/20180511172033_5yjMX.jpeg", "Thread/images/haizeiwang.jpeg");
        ThreadDownloader td2 = new ThreadDownloader("http://img.golue.com/pic/1411/111576.jpg", "Thread/images/huoyingrenzhe.jpg");
        ThreadDownloader td3 = new ThreadDownloader("http://yikeyz.com/wp-content/uploads/f3ccdd27d2000e3f9255a7e3e2c48800-355.jpg", "Thread/images/xiangzenan.jpg");

        //启动三个线程
        td1.start();
        td2.start();
        td3.start();
    }

}
