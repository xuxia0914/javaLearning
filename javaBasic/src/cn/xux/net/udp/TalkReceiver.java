package cn.xux.net.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class TalkReceiver implements Runnable {

    private DatagramSocket server;

    public TalkReceiver(int port) {
        try {
            server = new DatagramSocket(port);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                byte[] container = new byte[1024 * 64];
                DatagramPacket packet = new DatagramPacket(container, 0, container.length);
                server.receive(packet);
                byte[] datas = packet.getData();
                String data = new String(datas);
                System.out.println("收到：" + data);
                if("bye".equals(data)) {
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        server.close();
    }

}
