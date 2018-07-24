package com.belief.dao;

import com.belief.model.User;

public interface IUserDao {

    User selectUser(long id);

}