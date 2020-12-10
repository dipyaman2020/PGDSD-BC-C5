package com.upgrad.quora.api.controller;


import com.upgrad.quora.api.model.UserDeleteResponse;
import com.upgrad.quora.service.business.AdminBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class AdminController {

    @Autowired
    AdminBusinessService adminBusinessService;

    @RequestMapping(method = RequestMethod.DELETE, path = "/admin/user/{userId}")
    public ResponseEntity<UserDeleteResponse> removeUser(@PathVariable("userId") final String userId){

        String userResponse = adminBusinessService.removeUser(userId);
        if(userResponse != null) {
            UserDeleteResponse userDeleteResponse = new UserDeleteResponse().id(userId.toString()).status(userResponse);
            return new ResponseEntity<UserDeleteResponse>(userDeleteResponse, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<UserDeleteResponse>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}
