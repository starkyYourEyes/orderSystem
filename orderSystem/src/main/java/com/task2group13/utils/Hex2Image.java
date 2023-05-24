package com.task2group13.utils;

import javax.imageio.stream.FileImageOutputStream;
import java.io.*;
public class Hex2Image {
    public static void main(String[] args) throws Exception {
        String str=null;
        try
        {
            StringBuffer sb = new StringBuffer();
            FileInputStream fis = new FileInputStream("D:\\111.jpg");//图片路径
            //BufferedInputStream bis = new BufferedInputStream(fis);
            java.io.ByteArrayOutputStream bos = new java.io.ByteArrayOutputStream();
            byte[] buff = new byte[1024];
            int len = 0;
            while ((len = fis.read(buff)) != -1)
            {
                bos.write(buff, 0, len);
            }
            // 得到图片的字节数组
            byte[] result = bos.toByteArray();
            // 字节数组转成十六进制
            str = toHexString(result);
            PrintWriter pw = new PrintWriter(
                    new FileWriter("aaa.txt"));
            pw.println(str);
            pw.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        System.out.println(str);
        byte[] t=fromHexString(str);
        byte2image(t, "D:\\sfz10.jpg");//存放图片路径
    }

    static public String toHexString(byte[] ba) {
        StringBuilder sbuf = new StringBuilder();
        for (byte b : ba) {
            String s = Integer.toHexString((int) (b & 0xff));
            if (s.length() == 1) {
                sbuf.append('0');
            }
            sbuf.append(s);
        }
        return sbuf.toString();
    }

    public static byte[] fromHexString(String s) {
        int length = s.length() / 2;
        byte[] bytes = new byte[length];
        for (int i = 0; i < length; i++) {
            bytes[i] = (byte) ((Character.digit(s.charAt(i * 2), 16) << 4) | Character
                    .digit(s.charAt((i * 2) + 1), 16));
        }
        return bytes;
    }

    public static void byte2image(byte[] data,String path){
        if(data.length<3||path.equals("")) return;

        try{
            FileImageOutputStream imageOutput = new FileImageOutputStream(new File(path));
            imageOutput.write(data, 0, data.length);
            imageOutput.close();
            System.out.println("Make Picture success,Please find image in " + path);
        } catch(Exception ex) {
            System.out.println("Exception: " + ex);
            ex.printStackTrace();
        }
    }
}