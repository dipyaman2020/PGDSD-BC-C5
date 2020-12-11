package com.upgrad.quora.api.controller;

import com.upgrad.quora.api.model.*;
import com.upgrad.quora.service.business.AnswerBusinessService;
import com.upgrad.quora.service.entity.AnswerEntity;
import com.upgrad.quora.service.entity.UsersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/")
public class AnswerController {

    @Autowired
    AnswerBusinessService answerBusinessService;

    @RequestMapping(method = RequestMethod.POST, path = "/question/{questionId}/answer/create", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<AnswerResponse> createAnswer(@PathVariable("questionId") String questionId, final AnswerRequest answerRequest, HttpSession session){

        AnswerEntity answerEntity = new AnswerEntity();
        answerEntity.setAns(answerRequest.getAnswer());
        answerEntity.setDate(new Timestamp(System.currentTimeMillis()));
        answerEntity.setUuid(UUID.randomUUID().toString());
        // get logged in user entity
        UsersEntity user = new UsersEntity();// (UsersEntity) session.getAttribute("loggeduser");
        user.setId(1026);
        user.setUuid("database_uuid1");
        user.setFirstname("Abhi");
        user.setLastname("Mahajan");
        answerEntity.setUsersByUserId(user);

        // post question to DB through service
        AnswerEntity response = answerBusinessService.createAnswer(answerEntity, questionId);

        AnswerResponse answerResponse = new AnswerResponse().id(answerEntity.getUuid());
        return new ResponseEntity<AnswerResponse>(answerResponse, HttpStatus.OK);

    }

    // get all answers from DB by questionId
    @RequestMapping(method = RequestMethod.GET, path = "answer/all/{questionId}")
    public ResponseEntity<List<AnswerEntity>> getAllQuestionsByUserId(@PathVariable("questionId") final Integer questionId, HttpSession session){

        // get answers by questionId
        List<AnswerEntity> answerEntityList = answerBusinessService.getAllAnswersByQuestionId(questionId);
        return new ResponseEntity<List<AnswerEntity>>(answerEntityList, HttpStatus.OK);

    }

    // edit answers from DB by answerId
    @RequestMapping(method = RequestMethod.POST, path = "/answer/edit/{answerId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<AnswerEditResponse> updateQuestionsById(@PathVariable("answerId") final Integer answerId, final AnswerEditRequest answerEditRequest, HttpSession session){

        AnswerEntity answerEntity = new AnswerEntity();
        answerEntity.setAns(answerEditRequest.getContent());
        answerEntity.setId(answerId);
        // post question to DB through service
        AnswerEntity answerResp = answerBusinessService.updateAnswerById(answerEntity);

        AnswerEditResponse answerEditResponse = new AnswerEditResponse().id(answerResp.getUuid());
        return new ResponseEntity<AnswerEditResponse>(answerEditResponse, HttpStatus.OK);

    }

    // remove answer from DB by answerId
    @RequestMapping(method = RequestMethod.DELETE, path = "/answer/delete/{answerId}")
    public ResponseEntity<AnswerDeleteResponse> removeQuestionsById(@PathVariable("answerId") final Integer answerId, HttpSession session){

        // post question to DB through service
        String answerResp = answerBusinessService.removeAnswerById(answerId);

        AnswerDeleteResponse answerDeleteResponse = new AnswerDeleteResponse().id("").status(answerResp);
        return new ResponseEntity<AnswerDeleteResponse>(answerDeleteResponse, HttpStatus.OK);

    }

}

