package com.belief.service.impl;

import com.belief.dao.IUserDao;
import com.belief.model.User;
import com.belief.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userService")
public class UserServiceImpl implements IUserService {

        @Resource
        private IUserDao userDao;

        public User selectUser(long userId) {
            return this.userDao.selectUser(userId);
        }

}

