package com.mindskip.xzs.utility;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {

    public static String now(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(new Date());
    }
}
