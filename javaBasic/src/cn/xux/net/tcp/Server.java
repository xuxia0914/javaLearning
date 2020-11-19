package cn.xux.net.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 熟悉流程
 * 创建服务器
 * 1、使用 ServerSocket 创建服务器
 * 2、阻塞式等待连接 accept
 * 3、操作：输入输出流操作
 * 4、释放资源
 */
public class Server {

    public static void main(String[] args) throws Exception {
        System.out.println("------server-------");
        // 1、使用 ServerSocket 创建服务器
        ServerSocket server = new ServerSocket(9999);
        // 2、阻塞式等待连接 accept
        Socket client = server.accept();
        System.out.println("一个客户端建立了连接");
        // 3、操作：输入输出流操作
        DataInputStream dis = new DataInputStream(client.getInputStream());
        String data = dis.readUTF();
        System.out.println(data);
        // 4、释放资源
        dis.close();
        client.close();

        server.close();
    }

}
