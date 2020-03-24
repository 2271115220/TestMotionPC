package cn.jianke.socket.tcp;

import cn.jianke.socket.tcp.bean.Action;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TcpSocketServer {

    private static MyMouseController myMouseController;
    // 端口号
    private final static int serverPort = 9999;
    // tcp套接字列表
    private List<Socket> mList = new ArrayList<Socket>();
    // 套接字服务
    private ServerSocket server = null;
    // 线程池
    private ExecutorService mExecutorService = null;

    private static Action action;



    /**
     * 启动tcp套接字服务
     *
     * @param
     * @return
     * @author leibing
     * @createTime 2016/10/06
     * @lastModify 2016/10/06
     */
    public TcpSocketServer() {
        try {
            server = new ServerSocket(serverPort);
            System.out.print("server start ...");
            Socket client = null;
            //create a thread pool
            mExecutorService = Executors.newCachedThreadPool();
            while (true) {
                System.out.println("1111111111");
                client = server.accept();
                mList.add(client);
                //start a new thread to handle the connection
                mExecutorService.execute(new TcpSocketService(client));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @className: TcpSocketService
     * @classDescription: tcp套接字服务
     * @author: leibing
     * @createTime: 2016/10/06
     */
    class TcpSocketService implements Runnable {
        // 套接字
        private Socket socket;
        // 缓冲区读取
        private BufferedReader in = null;
        // 消息
        private String msg = "";

        public TcpSocketService(Socket socket) {
            this.socket = socket;
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                msg = "tips: user" + this.socket.getInetAddress() + " come";
//                    this.sendmsg();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            try {
                while (true) {
                    msg = in.readLine();
                    if (msg != null) {
                        if (msg.equals("exit")) {
                            mList.remove(socket);
                            in.close();
                            msg = "tips: user" + this.socket.getInetAddress() + " exit";
                            socket.close();
//                                this.sendmsg();
                            break;
                        } else {
//                                msg = socket.getInetAddress() + ":" + msg;
                            action = (Action) JSONObject.parseObject(msg, Action.class);
                            int code = action.getAction();
                            if (code == 0) {//点击事件
                                myMouseController.click(action);
                            } else if (code == 2) {//移动事件

                                myMouseController.MoveToPosition(action);
                            }
//                                this.sendmsg();
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void sendmsg() {
            System.out.println(msg);
            int num = mList.size();
            for (int index = 0; index < num; index++) {
                Socket mSocket = mList.get(index);
                PrintWriter pout = null;
                try {
                    pout = new PrintWriter(new BufferedWriter(
                            new OutputStreamWriter(mSocket.getOutputStream())), true);
                    pout.println(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        action = new Action();
        myMouseController = new MyMouseController();
        // 启动tcp套接字服务
        new TcpSocketServer();
    }
}