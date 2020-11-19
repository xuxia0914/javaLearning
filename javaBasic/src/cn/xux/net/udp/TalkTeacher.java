package cn.xux.net.udp;

public class TalkTeacher {

    public static void main(String[] args) {
        new Thread(new TalkReceiver(8888)).start();
        new Thread(new TalkSender(7777, "localhost", 6666)).start();

    }

}
