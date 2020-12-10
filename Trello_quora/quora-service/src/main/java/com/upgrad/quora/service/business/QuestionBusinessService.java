package com.upgrad.quora.service.business;

import com.upgrad.quora.service.dao.QuestionDao;
import com.upgrad.quora.service.entity.QuestionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class QuestionBusinessService {

    @Autowired
    QuestionDao questionDao;

    // create question
    @Transactional(propagation = Propagation.REQUIRED)
    public QuestionEntity createQuestion(final QuestionEntity questionEntity){
        return questionDao.createQuestion(questionEntity);
    }

    // get all question
    @Transactional(propagation = Propagation.REQUIRED)
    public List<QuestionEntity> getAllQuestion(){
        return questionDao.getAllQuestion();
    }

    // get all question by userId
    @Transactional(propagation = Propagation.REQUIRED)
    public List<QuestionEntity> getAllQuestionsByUserId(final Integer userId){
        return questionDao.getAllQuestionByUser(userId);
    }

    // remove question By id
    @Transactional(propagation = Propagation.REQUIRED)
    public String removeQuestionById(final Integer questionId){
        return questionDao.removeQuestionById(questionId);
    }

    // edit/update question By id
    @Transactional(propagation = Propagation.REQUIRED)
    public QuestionEntity updateQuestionById(final QuestionEntity question){
        return questionDao.updateQuestionById(question);
    }

}

