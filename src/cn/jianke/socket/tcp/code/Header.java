package cn.jianke.socket.tcp.code;


import java.io.Serializable;

public class Header implements Serializable {
    public byte version = 1;//1
    public byte format = 1;//1
    public short header_len = 16;//2
    public int cmd_id = 1;//4
    public int body_len;//4
    public int seqid =4;//4

    //共16个字节

    byte[] object = new byte[16];

    public byte[] getBytes() throws Exception {
        object[0] = version;
        object[1] = format;

        byte[] shortEncodArray = NetEncode.EncodeShort(header_len);
        object[3] = shortEncodArray[1];
        object[2] = shortEncodArray[0];  //nVal /256

        byte[] intEncodArray = NetEncode.EncodeInt(cmd_id);
        object[7] = intEncodArray[3];
        object[6] = intEncodArray[2];
        object[5] = intEncodArray[1];
        object[4] = intEncodArray[0];

        byte[] intEncodArray2 = NetEncode.EncodeInt(body_len);
        object[11] = intEncodArray2[3];
        object[10] = intEncodArray2[2];
        object[9] = intEncodArray2[1];
        object[8] = intEncodArray2[0];

        byte[] intEncodArray3 = NetEncode.EncodeInt(seqid);
        object[15] = intEncodArray3[3];
        object[14] = intEncodArray3[2];
        object[13] = intEncodArray3[1];
        object[12] = intEncodArray3[0];
        return object;
    }

    @Override
    public String toString() {
        return "Header{" +
                "version=" + version +
                ", format=" + format +
                ", header_len=" + header_len +
                ", cmd_id=" + cmd_id +
                ", body_len=" + body_len +
                ", seqid=" + seqid +
                '}';
    }
}

//        object[3] = (byte) (header_len & 0xff);
//        object[2] = (byte) (header_len >> 8);  //nVal /256
//
//        object[7] = (byte) (cmd_id & 0xff);
//        object[6] = (byte) (cmd_id >> 8);
//        object[5] = (byte) (cmd_id >> 16);
//        object[4] = (byte) (cmd_id >> 24);
//
//        object[11] = (byte) (body_len & 0xff);
//        object[10] = (byte) (body_len >> 8);
//        object[9] = (byte) (body_len >> 16);
//        object[8] = (byte) (body_len >> 24);
//
//        object[15] = (byte) (seqid & 0xff);
//        object[14] = (byte) (seqid >> 8);
//        object[13] = (byte) (seqid >> 16);
//        object[12] = (byte) (seqid >> 24);
//
