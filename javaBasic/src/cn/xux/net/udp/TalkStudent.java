package cn.xux.net.udp;

public class TalkStudent {

    public static void main(String[] args) {
        new Thread(new TalkReceiver(6666)).start();
        new Thread(new TalkSender(9999, "localhost", 8888)).start();
    }

}
