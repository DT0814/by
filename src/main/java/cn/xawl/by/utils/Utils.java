package cn.xawl.by.utils;

import java.util.Random;

public class Utils {
    public static String CreateID() {
        Random random = new Random();
        String UID = System.currentTimeMillis() % 100000000000L + "" + (random.nextInt(100) + 10) % 100;
        if ( UID.length() < 13 ) {
            UID += random.nextInt(10);
        }
        return UID;
    }

}
