package cn.jianke.socket.tcp.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDP_client {
    public static void main(String[] args) throws IOException {
        //1.创建对象
        //构造数据报套接字并将其绑定到本地主机上任何可用的端口。
        DatagramSocket socket = new DatagramSocket();
        //2.打包

        byte[] arr = (InetAddress.getLocalHost() + "客户端：哈哈。。。。").getBytes();
        //四个参数: 包的数据  包的长度  主机对象  端口号
        DatagramPacket packet = new DatagramPacket
                (arr, arr.length, InetAddress.getByName("255.255.255.255"), 4000);

        //3.发送
        for (int i = 0; i < 10; i++) {
            socket.send(packet);
        }

        //4.关闭资源
//        socket.close();
    }

}