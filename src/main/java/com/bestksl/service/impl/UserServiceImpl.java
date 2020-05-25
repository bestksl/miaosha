package com.bestksl.service.impl;

import com.bestksl.dao.UserDOMapper;
import com.bestksl.dao.UserPasswordDOMapper;
import com.bestksl.dataobject.UserDO;
import com.bestksl.dataobject.UserPasswordDO;
import com.bestksl.service.UserService;
import com.bestksl.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDOMapper userDOMapper;
    @Autowired
    UserPasswordDOMapper userPasswordDOMapper;

    @Override
    public UserModel getUserById(int id) {
        UserDO userDO = userDOMapper.selectByPrimaryKey(id);
        //拿到密码, 放到user model中
        UserPasswordDO userPasswordDO = userPasswordDOMapper.selectByPrimaryKey(id);
        return convertFromDataObject(userDO, userPasswordDO);
    }


    private UserModel convertFromDataObject(UserDO userDO, UserPasswordDO userPasswordDO) {
        if (userDO == null) {
            return null;
        }
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userDO, userModel);
        if (userPasswordDO != null) {
            userModel.setEncryptPassword(userPasswordDO.getEncryptPassword());
        }
        return userModel;
    }
}
