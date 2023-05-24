package com.task2group13.utils;


import org.jetbrains.annotations.NotNull;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Objects;

/**
 * @ClassName MultipartFileToFile
 * @Description MultipartFile转fie
 **/
public class MultipartFileToFile {

    /**
     * MultipartFile 转 File
     *
     * @param file
     * @throws Exception
     */
    public static File multipartFileToFile(@NotNull MultipartFile file) throws Exception {

        File toFile = null;
        if (file.equals("") || file.getSize() <= 0) {
            file = null;
        } else {
            InputStream ins = null;
            ins = file.getInputStream();
            toFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
            inputStreamToFile(ins, toFile);
            ins.close();
        }
        return toFile;
    }

    //获取流文件
    private static void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 删除本地临时文件
     * @param file
     */
    public static void delteTempFile(File file) {
        if (file != null) {
            File del = new File(file.toURI());
            del.delete();
        }
    }
    public static BufferedReader multipartFileToBufferedReader(MultipartFile picFile, String charsetName) {
        // 判断是否为空
        if (picFile.isEmpty()) {
            return null;
        }
        try {
            InputStream inputStream = picFile.getInputStream();
            InputStreamReader is = new InputStreamReader(inputStream, charsetName);
            BufferedReader br = new BufferedReader(is);
            return br;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}