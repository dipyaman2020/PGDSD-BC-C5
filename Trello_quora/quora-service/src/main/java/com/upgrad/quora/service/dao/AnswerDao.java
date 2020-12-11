package com.upgrad.quora.service.dao;

import com.upgrad.quora.service.entity.AnswerEntity;
import com.upgrad.quora.service.entity.QuestionEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public class AnswerDao {

    @PersistenceContext
    private EntityManager entityManager;

    public AnswerEntity createAnswer(AnswerEntity answerEntity, String questionId) throws Exception{
        try {
            QuestionEntity question = (QuestionEntity) entityManager.createQuery("SELECT q FROM QuestionEntity q WHERE q.id=:questionId").setParameter("questionId",questionId).getSingleResult();
            entityManager.createNativeQuery("INSERT INTO answer (uuid, ans, date, user_id, question_id) VALUES (?,?,?,?,?)")
                    .setParameter(1, UUID.randomUUID().toString())
                    .setParameter(2, answerEntity.getAns())
                    .setParameter(3, new Date())
                    .setParameter(4, answerEntity.getUsersByUserId().getId())
                    .setParameter(5, question.getId())
                    .executeUpdate();
        }catch (Exception e){
            System.out.println(e);
        }finally {
            return answerEntity;
        }
    }

    // get all answer list by question id
    public List<AnswerEntity> getAllAnswersByQuestion(Integer questionId){
        List<AnswerEntity> answerEntityList = new ArrayList<>();
        try {
            QuestionEntity question = (QuestionEntity) entityManager.createQuery("SELECT q FROM QuestionEntity q WHERE q.id=:questionId").setParameter("questionId",questionId).getSingleResult();

            answerEntityList = entityManager.createQuery("SELECT a FROM AnswerEntity a WHERE a.questionByQuestionId=:question").setParameter("question", question).getResultList();
        }catch (Exception e){
            System.out.println(e);
        }finally {
            return answerEntityList;
        }
    }

    // remove answer by id
    public String removeAnswerById(Integer answerId){
        String status = "";
        try {
            AnswerEntity answerEntity = (AnswerEntity) entityManager.createQuery("SELECT a FROM AnswerEntity a WHERE a.id=:answerId").setParameter("answerId", answerId).getSingleResult();
            entityManager.remove(answerEntity);
            status = "SUCCESSFULLY REMOVED";

        }catch (Exception e){
            System.out.println(e);
            status ="FAILED";
        }finally {
            return status;
        }
    }

    // edit answer by id
    public AnswerEntity updateAnswerById(AnswerEntity answer){
        try {
            AnswerEntity answerEntity = (AnswerEntity)entityManager.find(AnswerEntity.class ,answer.getId());
            answerEntity.setAns(answer.getAns());

        }catch (Exception e){
            System.out.println(e);
        }finally {
            return answer;
        }
    }

}
