package com.upgrad.quora.service.business;

import com.upgrad.quora.service.dao.CommonDao;
import com.upgrad.quora.service.entity.UsersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommonBusinessService {

    @Autowired
    CommonDao commonDao;

    public UsersEntity getUserProfileById(Integer userId){
        return commonDao.getUserProfileById(userId);
    }
}
