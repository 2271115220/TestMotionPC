package cn.jianke.socket.tcp.udp;

import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.*;
               import java.util.Arrays;


public class UDP_server {
    public static void main(String[] args) throws IOException {
        UDP_server udp_server = new UDP_server();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "server");
        jsonObject.put("msg", "我是服务端,我接收到了局域网内有新成员加入的消息，我对指定IP成员，做出回应");
        udp_server.sendMessage("192.168.1.255", jsonObject.toString().trim());
    }

    public void reviceMessage() {


        //1
        DatagramSocket serverSocket = null;
        try {
            serverSocket = new DatagramSocket(4000);
            //2
            byte[] arr = new byte[1024];
            DatagramPacket packet = new DatagramPacket(arr, arr.length);
            while (true) {
                //3 当程序运行起来之后,receive方法会一直处于监听状态
                serverSocket.receive(packet);
                //从包中将数据取出
                byte[] arr1 = packet.getData();
                InetAddress inetAddress = packet.getAddress();
                String clientIP = inetAddress.getHostAddress();
                String json = new String(arr1);
                String newJsonStr = URLDecoder.decode(json, "utf-8").trim();
                System.out.print(newJsonStr.trim());
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("type", "server");
                jsonObject.put("msg", "我是服务端,我接收到了局域网内有新成员加入的消息，我对指定IP成员，做出回应");
                /* udp_server.sendMessage(clientIP, jsonObject.toString());*/
            }
            //4
//        serverSocket.close();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void sendMessage(String ip, String msg) {
        //1.创建对象
        //构造数据报套接字并将其绑定到本地主机上任何可用的端口。
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket();
            //2.打包
            msg = URLEncoder.encode(msg, "utf-8");
            byte[] arr = msg.getBytes();
            //四个参数: 包的数据  包的长度  主机对象  端口号
            DatagramPacket packet = new DatagramPacket
                    (arr, arr.length, InetAddress.getByName(ip), 4000);
            //3.发送
            socket.send(packet);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}