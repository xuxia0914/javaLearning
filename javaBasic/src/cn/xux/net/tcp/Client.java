package cn.xux.net.tcp;

import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 熟悉流程
 * 创建客户端
 * 1、创建连接：使用 Socket 创建客户端+服务器ip和端口
 * 2、操作：输入输出流操作
 * 3、释放资源
 */
public class Client {

    public static void main(String[] args) throws Exception {
        System.out.println("------client-------");
        // 1、创建连接：使用 Socket 创建客户端+服务器ip和端口
        Socket client = new Socket("localhost", 9999);
        System.out.println("一个客户端建立了连接");
        // 2、操作：输入输出流操作
        DataOutputStream dos = new DataOutputStream(client.getOutputStream());
        String data = "hello";
        dos.writeUTF(data);
        dos.flush();
        // 3、释放资源
        dos.close();
        client.close();
    }

}
