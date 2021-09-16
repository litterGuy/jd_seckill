package com.jd.seckill;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;

public class YmlUtil {

    public static String getQrcodePath() {
        Yaml yaml = new Yaml();
        // 获取先取当前目录，测试换取/src/main/resources/
        String firstPath = System.getProperty("user.dir") + "/application.yml";
        if (!new File(firstPath).exists()) {
            firstPath = System.getProperty("user.dir") + "/src/main/resources/application.yml";
        }
        try {
            Map dev = yaml.load(new FileInputStream(firstPath));
            if (dev.containsKey("qrcode") && dev.get("qrcode") != null) {
                String qrcode = dev.get("qrcode").toString();
                return qrcode;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return "QCode.png";
    }

    public static String getPid() {
        Yaml yaml = new Yaml();
        // 获取先取当前目录，测试换取/src/main/resources/
        String firstPath = System.getProperty("user.dir") + "/application.yml";
        if (!new File(firstPath).exists()) {
            firstPath = System.getProperty("user.dir") + "/src/main/resources/application.yml";
        }
        try {
            Map dev = yaml.load(new FileInputStream(firstPath));
            String pid = dev.get("pid").toString();
            return pid;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return "100012043978";
    }

    public static int getOknum() {
        Yaml yaml = new Yaml();
        // 获取先取当前目录，测试换取/src/main/resources/
        String firstPath = System.getProperty("user.dir") + "/application.yml";
        if (!new File(firstPath).exists()) {
            firstPath = System.getProperty("user.dir") + "/src/main/resources/application.yml";
        }
        try {
            Map dev = yaml.load(new FileInputStream(firstPath));
            if (dev.containsKey("oknum") && dev.get("oknum") != null) {
                String str = dev.get("oknum").toString();
                return Integer.parseInt(str);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return 2;
    }
}
