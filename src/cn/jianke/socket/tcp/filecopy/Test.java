package cn.jianke.socket.tcp.filecopy;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Test {


    private List<String> fileList;

    public Test() {
        fileList = new ArrayList<>();
    }

    public static void main(String[] args) throws IOException {

        String fromPath = "H:\\学习系统\\小学同步\\综合学习";
        String toPath = "D:\\小学同步\\综合学习";
        Test test = new Test();

        test.filterFile(fromPath, "");
        for (int i = 0; i < test.fileList.size(); i++) {
            String s = test.fileList.get(i);

            s = s.replace(fromPath, toPath);
            System.out.println(s);


//            System.out.println(toPath + s1.substring(1, s1.length()));
        }
    }


    class FilterFileTask implements Runnable {

        private String from;

        public FilterFileTask(String from) {
            this.from = from;
        }

        @Override
        public void run() {

        }
    }

    public void filterFile(String fromPath, String path) throws IOException {

        File file = new File(fromPath);
        if (!file.exists()) {
            return;
        }
        if (file.isDirectory()) {
            if (file.listFiles().length == 0) {
                return;
            } else {
                File[] files = file.listFiles();
                for (int i = 0; i < files.length; i++) {
                    File file1 = files[i];
                    if (file1.isDirectory()) {
                        path = path + "/" + file1.getName();
                        filterFile(file1.getAbsolutePath(), path);
                    } else {
                        fileList.add(file1.getAbsolutePath());
                    }
                }
            }
        } else {
            fileList.add(file.getAbsolutePath());
        }
    }
}
