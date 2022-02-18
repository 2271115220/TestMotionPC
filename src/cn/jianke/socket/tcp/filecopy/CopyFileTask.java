package cn.jianke.socket.tcp.filecopy;

import java.io.*;

public class CopyFileTask implements Runnable {

    private String from, to;

    private int bufferSize = 1 * 1024;

    public CopyFileTask(String from, String to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public void run() {
        try {
            File fromFile = new File(from);
            File toFile = new File(to);
            if (fromFile.exists()) {

            }
            FileInputStream fileInputStream = new FileInputStream(fromFile);
            FileOutputStream fileOutputStream = new FileOutputStream(toFile);

            byte[] buffer = new byte[bufferSize];

            int length;
            while ((length = fileInputStream.read(buffer)) > 0) {
                fileOutputStream.write(buffer, 0, length);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
