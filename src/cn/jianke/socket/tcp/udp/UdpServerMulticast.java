package cn.jianke.socket.tcp.udp;

import java.io.IOException;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class UdpServerMulticast {


    private String groupIp;
    MulticastSocket socket;

    //开始发送
    public void start() {
        try {
            socket = new MulticastSocket();
            InetAddress inetAddress = InetAddress.getByName(groupIp);
            socket.joinGroup(inetAddress);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

    }

}
