package com.upgrad.quora.api.controller;

import com.upgrad.quora.api.model.UserDetailsResponse;
import com.upgrad.quora.service.business.CommonBusinessService;
import com.upgrad.quora.service.entity.UsersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class CommonController {

    @Autowired
    CommonBusinessService commonBusinessService;

    @RequestMapping(method = RequestMethod.GET, path = "/userprofile/{userId}")
    public ResponseEntity<UserDetailsResponse> getUserProfileById(@PathVariable("userId") final Integer userId){

        UsersEntity usersEntity = commonBusinessService.getUserProfileById(userId);

        UserDetailsResponse userDetailsResponse = new UserDetailsResponse().firstName(usersEntity.getFirstname())
                .lastName(usersEntity.getLastname())
                .userName(usersEntity.getUsername());
        return new ResponseEntity<UserDetailsResponse>(userDetailsResponse, HttpStatus.OK);
    }
}
