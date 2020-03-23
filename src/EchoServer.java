import cn.jianke.socket.tcp.MyMouseController;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

    private final ServerSocket mServerSocket;
    MyMouseController mmc;

    public EchoServer(int port) throws IOException {
        // 1. 创建一个 ServerSocket 并监听端口 port
        mServerSocket = new ServerSocket(port);

        mmc = new MyMouseController();


    }

    public void run() throws IOException {
        // 2. 开始接受客户连接
        Socket client = mServerSocket.accept();
        handleClient(client);
    }

    private void handleClient(Socket socket) {
        // 3. 使用 socket 进行通信 ...
        InputStream in = null;
        try {
            in = socket.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] buffer = new byte[1024];
        int n = 0;
        while (true) {
            try {
                int code = in.read(buffer);
                if (!(code > 0)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String testStr = new String(buffer);
        System.out.println(testStr);
        mmc.Move2(Integer.parseInt(testStr));//坐标为相对坐标

    }
    public static void main(String[] argv) {
        try {
            EchoServer server = new EchoServer(9877);
            server.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}