package com.swacorp.rpa.utils;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.*;
import java.util.Date;

public class Utility extends DriverFactory {
    private static String screenshotName;
    private WebDriver driver;
    public Utility() throws IOException {
       driver=getDriver();
        }

    /***EXTENT REPORT****************************************************************/
    public static String returnDateStamp(String fileExtension) {
        Date d = new Date();
        String date = d.toString().replace(":", "_").replace(" ", "_") + fileExtension;
        return date;
    }

    public void captureScreenshot() throws IOException, InterruptedException {
        File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        screenshotName = returnDateStamp(".jpg");
        FileUtils.copyFile(srcFile, new File(System.getProperty("user.dir") + "\\output\\imgs\\" + screenshotName));
    }

    public static String returnScreenshotName() {
        return (System.getProperty("user.dir") + "\\output\\imgs\\" + screenshotName).toString();
    }

    private static void copyFileUsingStream(File source, File dest) throws IOException {
        InputStream is = null;
        OutputStream os = null;

        try {
            is = new FileInputStream(source);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;

            while((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }

        } finally {
            is.close();
            os.close();
        }
    }

    public static void copyLatestExtentReport() throws IOException {
        Date d = new Date();
        String date = d.toString().replace(":", "_").replace(" ", "_");
        File source = new File(System.getProperty("user.dir") + "\\output\\report.html");
        File dest = new File(System.getProperty("user.dir") + "\\output\\" + date.toString() + ".html");
        copyFileUsingStream(source, dest);
    }

}
