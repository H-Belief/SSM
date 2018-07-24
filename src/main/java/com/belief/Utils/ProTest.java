package com.belief.Utils;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.web.context.ConfigurableWebApplicationContext;

import javax.annotation.Resource;
import java.io.*;
import java.net.URLDecoder;
import java.util.Map;
import java.util.Properties;

public class ProTest {


    /*public static void main(String[] args) throws Exception{

        String propFileName="application.properties";
        // 读取配置文件
        FileInputStream inputStream = new FileInputStream("D:\\workspace\\internetHospital-new\\src\\main\\resources\\application.properties");
        Properties prop = new Properties();
        // 加载
        prop.load(inputStream);
        // 获取
        prop.getProperty("MultipleChoice");
        // 设置
        prop.setProperty("MultipleChoice", "10");
        // 写到配置文件
        FileOutputStream outputStream = new FileOutputStream("D:\\workspace\\internetHospital-new\\src\\main\\resources\\application.properties");
        prop.store(outputStream, "update message");
        outputStream.close();
        System.out.println("修改成功");
        //重新加载配置文件

    }*/




    public static void main(String[] args) throws  Exception{
       // String path = ProTest.class.getClassLoader().getResource("application.properties").getPath();

        String path = Thread.currentThread().getContextClassLoader()
                .getResource("dataSource/jdbc.c3p0.properties").getPath();


        FileInputStream inputStream = new FileInputStream(path);
        System.out.println("文件所在的路径"+path);
        Properties prop = new Properties();
        // 加载
        prop.load(inputStream);

        // 设置
        prop.setProperty("test","test1");
        // 写到配置文件
        FileOutputStream outputStream = new FileOutputStream(path);
        prop.store(outputStream, "update message");
        //关流
        outputStream.close();
        System.out.println("修改成功");

    }


}
