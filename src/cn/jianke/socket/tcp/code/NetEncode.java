package cn.jianke.socket.tcp.code;


/**
 * 网络编码
 */
public class NetEncode {

    /**
     * 将short转换为网络字节序
     */
    public static byte[] EncodeShort(short data) {
        byte[] array = new byte[2];
        array[1] = (byte) (data & 0xff);//取最低8位
        array[0] = (byte) (data >> 8);  //nVal /256

        return array;
    }

    /**
     * 将Int转换为网络字节序
     */
    public static byte[] EncodeInt(int data) {
        byte[] array = new byte[4];
        array[3] = (byte) (data & 0xff);
        array[2] = (byte) (data >> 8);
        array[1] = (byte) (data >> 16);
        array[0] = (byte) (data >> 24);
        return array;
    }

    public static void main(String[] args) {

    }
}
