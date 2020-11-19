package cn.xux.net.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * 接收端
 * 1、使用DatagramSocket创建接收端 指定端口
 * 2、准备容器封装成DatagramPacket包裹
 * 3、阻塞式接收包裹 receive(DatagramPacket p)
 * 4、分析数据 byte[] getData() getLength()
 * 5、释放资源
 */
public class UdpServer {

    public static void main(String[] args) throws Exception {
        System.out.println("接收方启动中...");
        // 1、使用DatagramSocket创建接收端 指定端口
        DatagramSocket server = new DatagramSocket(9999);
        // 2、准备容器封装成DatagramPacket包裹
        byte[] container = new byte[1024*64];
        DatagramPacket packet = new DatagramPacket(container, 0, container.length);
        // 3、阻塞式接收包裹 receive(DatagramPacket p)
        server.receive(packet);
        // 4、分析数据 byte[] getData() getLength()
        System.out.println("收到数据："+new String(packet.getData()));
        // 5、释放资源
        server.close();
    }



}
