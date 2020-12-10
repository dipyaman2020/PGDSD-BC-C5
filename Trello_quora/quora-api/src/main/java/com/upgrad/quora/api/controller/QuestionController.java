package com.upgrad.quora.api.controller;

import com.upgrad.quora.api.model.*;
import com.upgrad.quora.service.business.QuestionBusinessService;
import com.upgrad.quora.service.entity.QuestionEntity;
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
public class QuestionController {

    @Autowired
    QuestionBusinessService questionBusinessService;

    @RequestMapping(method = RequestMethod.POST, path = "/question/create", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<QuestionResponse> createQuestion(final QuestionRequest questionRequest, HttpSession session){

        QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.setContent(questionRequest.getContent());
        questionEntity.setDate(new Timestamp(System.currentTimeMillis()));
        questionEntity.setUuid(UUID.randomUUID().toString());
        // get logged in user entity
        UsersEntity user = (UsersEntity) session.getAttribute("loggeduser");
        questionEntity.setUsersByUserId(user);
        // post question to DB through service
        QuestionEntity response = questionBusinessService.createQuestion(questionEntity);

        QuestionResponse questionResponse = new QuestionResponse().id(questionEntity.getUuid());
        return new ResponseEntity<QuestionResponse>(questionResponse, HttpStatus.OK);

    }

    // get all question from DB
    @RequestMapping(method = RequestMethod.GET, path = "/question/all", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<QuestionEntity>> getAllQuestions(HttpSession session){

        // post question to DB through service
        List<QuestionEntity> questionEntityList = questionBusinessService.getAllQuestion();
        return new ResponseEntity<List<QuestionEntity>>(questionEntityList, HttpStatus.OK);

    }

    // get all question from DB by UserId
    @RequestMapping(method = RequestMethod.GET, path = "/question/all/{userId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<QuestionEntity>> getAllQuestionsByUserId(@PathVariable("userId") final Integer userId, HttpSession session){

        // get logged in user entity
        UsersEntity user = (UsersEntity) session.getAttribute("loggeduser");
        // post question to DB through service
        List<QuestionEntity> questionEntityList = questionBusinessService.getAllQuestionsByUserId(userId);

        return new ResponseEntity<List<QuestionEntity>>(questionEntityList, HttpStatus.OK);

    }

    // edit question from DB by questionId
    @RequestMapping(method = RequestMethod.POST, path = "/question/edit/{questionId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<QuestionEditResponse> updateQuestionsById(@PathVariable("questionId") final Integer questionId, final QuestionEditRequest questionRequest, HttpSession session){

        QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.setContent(questionRequest.getContent());
        questionEntity.setId(questionId);
        // post question to DB through service
        QuestionEntity questionResp = questionBusinessService.updateQuestionById(questionEntity);

        QuestionEditResponse questionResponse = new QuestionEditResponse().id(questionResp.getUuid());
        return new ResponseEntity<QuestionEditResponse>(questionResponse, HttpStatus.OK);

    }

    // remove question from DB by questionId
    @RequestMapping(method = RequestMethod.DELETE, path = "/question/delete/{questionId}")
    public ResponseEntity<QuestionDeleteResponse> removeQuestionsById(@PathVariable("questionId") final Integer questionId, HttpSession session){

        // post question to DB through service
        String questionResp = questionBusinessService.removeQuestionById(questionId);

        QuestionDeleteResponse questionResponse = new QuestionDeleteResponse().id("").status(questionResp);
        return new ResponseEntity<QuestionDeleteResponse>(questionResponse, HttpStatus.OK);

    }
}
