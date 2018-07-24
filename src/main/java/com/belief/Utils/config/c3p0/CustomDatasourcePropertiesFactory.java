package com.belief.Utils.config.c3p0;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class CustomDatasourcePropertiesFactory {

    private static final String PASSWORD_PROPERTY = "password";

    private static final Logger logger = LoggerFactory
            .getLogger(CustomDatasourcePropertiesFactory.class);

    public static Properties getProperties(String password) throws Exception {
        Properties properties = new Properties();
        try {
            /**
             * 解密密码
             */
            String decodePassword = new String(Base64.decodeBase64(password.getBytes()));
            properties.setProperty(PASSWORD_PROPERTY, decodePassword);
        } catch (Exception e) {
            logger.error("CustomDatasourcePropertiesFactory#getProperties" + e.getMessage(), e);
        }
        return properties;
    }



    public static void main(String args[]){

        String a = "admin";

        String encodePassword = new String(Base64.encodeBase64(a.getBytes()));

        System.out.println(encodePassword);

        String decodePassword = new String(Base64.decodeBase64(encodePassword.getBytes()));

        System.out.println(decodePassword);
    }


}
