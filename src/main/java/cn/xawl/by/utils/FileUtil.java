package cn.xawl.by.utils;

import java.io.*;
import java.util.UUID;

public class FileUtil {
    public static String upload(File file, String path, String suffix) {
        String name = UUID.randomUUID().toString().replaceAll("-", "");
        try {
            System.out.println("suffix:" + suffix);
            System.out.println("path:" + path);
            InputStream in = new FileInputStream(file);
            File dir = new File(path);
            if ( !dir.exists() ) {
                System.out.println("hehehheheheh");
                dir.mkdir();
            }
            path = path + "/" + name + suffix;
            System.out.println("path:" + path);
            File outFile = new File(path);
            OutputStream out = new FileOutputStream(outFile);
            byte buffer[] = new byte[1024];
            int length = 0;
            while ( (length = in.read(buffer)) > 0 ) {
                out.write(buffer, 0, length);
            }
            in.close();
            out.close();
            return name + suffix;
        } catch ( Exception e ) {
            e.printStackTrace();
            return null;
        }
    }
}
