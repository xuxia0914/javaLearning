package cn.xux.net.udp;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

/**
 * 发送端
 *  1、使用DatagramSocket创建发送端 指定端口
 *  2、准备数据一定要转成字节数组
 *  3、封装成DatagramPacket包裹，需要指定目的地
 *  4、发送包裹 send(封装成DatagramPacket包裹 p)，指定目的地
 *  5、释放资源
 */
public class UdpTalkClient {

    public static void main(String[] args) throws Exception {
        System.out.println("发送方启动中...");
        // 1、使用DatagramSocket创建发送端 指定端口
        DatagramSocket client = new DatagramSocket(8888);
        // 2、准备数据一定要转成字节数组
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        byte[] data = reader.readLine().getBytes();
        // 3、封装成DatagramPackage包裹，需要指定目的地
        DatagramPacket packet = new DatagramPacket(data, 0, data.length,
                new InetSocketAddress("localhost", 9999));
        // 4、发送包裹 send(DataGramPackage p)，指定目的地
        client.send(packet);
        // 5、释放资源
        client.close();
    }

}
