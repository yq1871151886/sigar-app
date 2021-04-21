package com.tiamaes.cloud.sigar;

import java.io.File;

/**
 * @author yangqigong
 * @version 1.0
 * @date 2020/11/20 15:00
 */
public class SigarUtil {

    /**
     * set sigar package system variable
     */
    public static final void setSystemVariable() {
        try {
            String classPath = System.getProperty("user.dir") + File.separator + "conf";
            String path = System.getProperty("java.library.path");
            /*if (OsCheck.getOperatingSystemType() == OsCheck.OSType.Windows) {
                path += ";" + classPath;
            } else {
                path += ":" + classPath;
            }*/
            System.setProperty("java.library.path", path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
