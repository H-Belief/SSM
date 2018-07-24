package com.belief.Utils.config.DBCP;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.dbcp.BasicDataSource;

public class CustomDataSource extends BasicDataSource {

    public CustomDataSource() {
        super();
    }

    @Override
    public void setPassword(String password) {
        try {
            String decodePassword =  new String(Base64.decodeBase64(password.getBytes()));
            this.password =decodePassword;
        } catch (Exception e) {
        }
    }
}
