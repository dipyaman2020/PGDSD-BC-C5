package com.upgrad.quora.service.dao;

import com.upgrad.quora.service.entity.QuestionEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Repository
public class QuestionDao {

    @PersistenceContext
    private EntityManager entityManager;

    public QuestionEntity createQuestion(QuestionEntity questionEntity){
        try {
            /*entityManager.createNativeQuery("INSERT INTO Question (uuid, content, date, user_id) VALUES (?,?,?,?)")
                    .setParameter(1, UUID.randomUUID().toString())
                    .setParameter(2, questionEntity.getContent())
                    .setParameter(3, ZonedDateTime.now())
                    .setParameter(3, questionEntity.getUsersByUserId().getId())
                    .executeUpdate();*/
            entityManager.persist(questionEntity);
        }catch (Exception e){
            System.out.println(e);
        }finally {
            return questionEntity;
        }
    }

    // get all question list
    public List<QuestionEntity> getAllQuestion(){
        List<QuestionEntity> questionEntityList = new ArrayList<>();
        try {
            questionEntityList = entityManager.createQuery("SELECT q FROM QuestionEntity q").getResultList();
        }catch (Exception e){
            System.out.println(e);
        }finally {
            return questionEntityList;
        }
    }

    // get all question list by user id
    public List<QuestionEntity> getAllQuestionByUser(Integer userId){
        List<QuestionEntity> questionEntityList = new ArrayList<>();
        try {
            questionEntityList = entityManager.createQuery("SELECT q FROM QuestionEntity q WHERE q.usersByUserId=:userId").setParameter("userId", userId).getResultList();
        }catch (Exception e){
            System.out.println(e);
        }finally {
            return questionEntityList;
        }
    }

    // remove question by id
    public String removeQuestionById(Integer questionId){
        String status = "";
        try {
            QuestionEntity questionEntity = (QuestionEntity) entityManager.createQuery("SELECT q FROM QuestionEntity q WHERE q.id=:questionId").setParameter("questionId", questionId).getSingleResult();
            entityManager.remove(questionEntity);
            status = "SUCCESSFULLY REMOVED";

        }catch (Exception e){
            System.out.println(e);
            status ="FAILED";
        }finally {
            return status;
        }
    }

    // edit question by id
    public QuestionEntity updateQuestionById(QuestionEntity question){
        try {
            QuestionEntity questionEntity = (QuestionEntity)entityManager.find(QuestionEntity.class ,question.getId());
            questionEntity.setContent(question.getContent());

        }catch (Exception e){
            System.out.println(e);
        }finally {
            return question;
        }
    }

}
