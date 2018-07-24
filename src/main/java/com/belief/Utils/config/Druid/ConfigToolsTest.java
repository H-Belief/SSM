package com.belief.Utils.config.Druid;

import com.alibaba.druid.filter.config.ConfigTools;

public class ConfigToolsTest {

    public static void main(String[] args) throws Exception {
        String password = "admin";
        String encodePassword =  ConfigTools.encrypt(password);
        System.out.println("明文密码: " + encodePassword);
        System.out.println("密文密码: " + ConfigTools.decrypt(encodePassword));
    }
}
