package com.line.unzipexample.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Created by lance on 16/5/19.
 * 文件压缩和解压
 */
public class UnzipUtil {

    /**
     * 根据文件路径提取压缩文件内容到指定目录, 支持多级目录解压
     * @param zipPath
     * @param extractDirectoryPath
     * @return
     * @throws IOException
     */
    public static boolean unzip(String zipPath, String extractDirectoryPath) {

        File zipFile = new File(zipPath);
        File extractDirectory = new File(extractDirectoryPath);

        return unzip(zipFile, extractDirectory);
    }

    /**
     * 解压文件到指定目录
     * @param zipFile
     * @param extractDirectory 全路径
     * @return
     * @throws IOException
     */
    public static boolean unzip(File zipFile, File extractDirectory) {

        ZipInputStream zis = null;

        try {

            zis = new ZipInputStream(new BufferedInputStream(new FileInputStream(zipFile)));
            ZipEntry ze;
            int count;
            byte[] buffer = new byte[8192];

            while ((ze = zis.getNextEntry()) != null) {
                File file = new File(extractDirectory, ze.getName());
                File dir = ze.isDirectory() ? file : file.getParentFile();

                if (dir.isDirectory() == false && dir.mkdirs() == false) {
                    throw new FileNotFoundException("Failed to ensure directory: " + dir.getAbsolutePath());
                }

                if (ze.isDirectory()) {
                    continue;
                }

                FileOutputStream fout = new FileOutputStream(file);

                try {
                    while ((count = zis.read(buffer)) != -1) {
                        fout.write(buffer, 0, count);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    fout.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (zis != null) {
                try {
                    zis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return true;
    }

}
