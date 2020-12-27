package com.upgrad.quora.service.business;

import com.upgrad.quora.service.dao.AnswerDao;
import com.upgrad.quora.service.entity.AnswerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AnswerBusinessService {

    @Autowired
    AnswerDao answerDao;

    // create question
    @Transactional(propagation = Propagation.REQUIRED)
    public AnswerEntity createAnswer(final AnswerEntity answerEntity, String questionId){
        try {
            return answerDao.createAnswer(answerEntity, questionId);
        }catch (Exception e){
            System.out.println(e);
            return new AnswerEntity();
        }
    }

    // get all answer by userId
    @Transactional(propagation = Propagation.REQUIRED)
    public List<AnswerEntity> getAllAnswersByQuestionId(final Integer questionId){
        return answerDao.getAllAnswersByQuestion(questionId);
    }

    // remove answer By id
    @Transactional(propagation = Propagation.REQUIRED)
    public String removeAnswerById(final Integer answerId){
        return answerDao.removeAnswerById(answerId);
    }

    // edit/update answer By id
    @Transactional(propagation = Propagation.REQUIRED)
    public AnswerEntity updateAnswerById(final AnswerEntity answer){
        return answerDao.updateAnswerById(answer);
    }

}


