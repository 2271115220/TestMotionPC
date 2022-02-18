package cn.jianke.socket.tcp;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/**
 * 解压文件
 *
 * @author wxisme
 * @time 2015-10-5 下午9:18:57
 */
public class Decompression {

    /**
     * 解压
     *
     * @param zipFilePath   待解压文件的路径
     * @param unzipFilePath 解压后的文件存储路径
     * @throws Exception
     */
    public static void unzip(String zipFilePath, String unzipFilePath) throws Exception {
        File zipFile = new File(zipFilePath);

        //创建解压缩文件保存的路径  
        File unzipFileDir = new File(unzipFilePath);
        if (!unzipFileDir.exists() || !unzipFileDir.isDirectory()) {
            unzipFileDir.mkdirs();
        }

        //开始解压  
        ZipEntry entry = null;
        String entryFilePath = null, entryDirPath = null;
        File entryFile = null, entryDir = null;
        int index = 0, count = 0;
        byte[] buffer = new byte[1024];
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        ZipFile zip = new ZipFile(zipFile);
        Enumeration<ZipEntry> entries = (Enumeration<ZipEntry>) zip.entries();
        //循环对压缩包里的每一个文件进行解压       
        while (entries.hasMoreElements()) {
            entry = entries.nextElement();
            boolean directory = entry.isDirectory();
            //构建压缩包中一个文件解压后保存的文件全路径  
            entryFilePath = unzipFilePath + File.separator + entry.getName();
            //构建解压后保存的文件夹路径  
            index = entryFilePath.lastIndexOf(File.separator);
            if (index != -1) {
                entryDirPath = entryFilePath.substring(0, index);
            } else {
                entryDirPath = "";
            }
            entryDir = new File(entryDirPath);

            if (entry.isDirectory()) {
                entryDir.mkdir();
            } else {
                entryDir.mkdirs();
            }
            //创建解压文件  
            entryFile = new File(entryFilePath);
            if (entryFile.exists()) {
//                //检测文件是否允许删除，如果不允许删除，将会抛出SecurityException
//                SecurityManager securityManager = new SecurityManager();
//                securityManager.checkDelete(entryFilePath);
//                //删除已存在的目标文件
//                entryFile.delete();
            }

            //写入文件  
            bos = new BufferedOutputStream(new FileOutputStream(entryFile));
            bis = new BufferedInputStream(zip.getInputStream(entry));
            while ((count = bis.read(buffer, 0, 1024)) != -1) {
                bos.write(buffer, 0, count);
            }
            bos.flush();
            bos.close();
        }
    }

    public static void zipUncompress(String inputFile) throws Exception {
        File srcFile = new File(inputFile);
        // 判断源文件是否存在
        if (!srcFile.exists()) {
            throw new Exception(srcFile.getPath() + "所指文件不存在");
        }
        String destDirPath = inputFile.replace(".zip", "");
        //创建压缩文件对象
        ZipFile zipFile = new ZipFile(srcFile);
        //开始解压
        Enumeration<?> entries = zipFile.entries();
        while (entries.hasMoreElements()) {
            ZipEntry entry = (ZipEntry) entries.nextElement();
            // 如果是文件夹，就创建个文件夹
            if (entry.isDirectory()) {
                String name = entry.getName();
                File f = new File(name);
                f.mkdirs();
                f = null;
            } else {
                // 如果是文件，就先创建一个文件，然后用io流把内容copy过去
                File targetFile = new File(destDirPath + "/" + entry.getName());
                // 保证这个文件的父文件夹必须要存在
                if (!targetFile.getParentFile().exists()) {
                    targetFile.getParentFile().mkdirs();
                }
                targetFile.createNewFile();
                // 将压缩文件内容写入到这个文件中
                InputStream is = zipFile.getInputStream(entry);
                FileOutputStream fos = new FileOutputStream(targetFile);
                int len;
                byte[] buf = new byte[1024];
                while ((len = is.read(buf)) != -1) {
                    fos.write(buf, 0, len);
                }
                // 关流顺序，先打开的后关闭
                fos.close();
                is.close();
            }
        }
    }

    public static void unzip(String zipPath, String targetPath, String charset) throws Exception {
        long totalSize = new File(zipPath).length();// 总大小
        long readSize = 0;
        try (ZipInputStream zipInput = new ZipInputStream(new FileInputStream(zipPath), Charset.forName(charset))) {
            for (ZipEntry zipItem = zipInput.getNextEntry(); zipItem != null; zipItem = zipInput.getNextEntry()) {
                if (!zipItem.isDirectory()) {
                    File file = new File(targetPath, zipItem.getName());
                    if (!file.exists()) {
                        new File(file.getParent()).mkdirs();// 创建此文件的上级目录
                    }
                    try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file))) {
                        byte[] b = new byte[1024];
                        for (int len = zipInput.read(b); len > 0; len = zipInput.read(b)) {
                            out.write(b, 0, len);
                        }
                    } catch (Exception e) {
                    }
                    Integer oldValue = (int) ((readSize * 1.0 / totalSize) * 100);// 已解压的字节大小占总字节的大小的百分比
                    readSize += zipItem.getCompressedSize();// 累加字节长度
                    Integer newValue = (int) ((readSize * 1.0 / totalSize) * 100);// 已解压的字节大小占总字节的大小的百分比

                    System.out.println("oldvalue" + oldValue + "     newvalue" + newValue);
                }
            }
        } catch (Exception e) {
        }
    }


    public static void main(String[] args) throws Exception {
        String from = "G:\\aaaa\\学习系统.zip";
        String to = "G:\\aaaa\\学习系统";
//        Decompression.zipUncompress(from);
        Decompression.unzip(from, to, "utf-8");
    }

}