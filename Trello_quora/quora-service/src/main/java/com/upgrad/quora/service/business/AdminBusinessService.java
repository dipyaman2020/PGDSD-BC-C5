package com.upgrad.quora.service.business;

import com.upgrad.quora.service.dao.AdminDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdminBusinessService {

    @Autowired
    AdminDao adminDao;

    @Transactional(propagation = Propagation.REQUIRED)
    public String removeUser(final String userUuid){

        String userDeleteResponse = adminDao.removeUser(userUuid);
        return userDeleteResponse;
    }
}
