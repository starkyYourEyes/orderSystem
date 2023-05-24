package com.task2group13.utils;

import java.io.File;
import java.io.FileInputStream;

public class FileToByteArray {
    /**
     * 文件File类型转byte[]
     *
     * @param file 文件对象
     * @return
     */
    public static byte[] fileToByte(File file) {
        byte[] fileBytes = null;
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            fileBytes = new byte[(int) file.length()];
            fis.read(fileBytes);
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileBytes;
    }

}
