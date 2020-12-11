package com.upgrad.quora.service.dao;

import com.upgrad.quora.service.entity.QuestionEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public class QuestionDao {

    @PersistenceContext
    private EntityManager entityManager;

    public QuestionEntity createQuestion(QuestionEntity questionEntity) throws Exception{
//        DriverManager.registerDriver(new org.postgresql.Driver());
//        Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/quora");
        try {
//            PreparedStatement stmt = c.prepareStatement("INSERT INTO question (uuid, content, date, user_id) " +
//                    "VALUES (UUID.randomUUID().toString(),questionEntity.getContent(),new Date(),questionEntity.getUsersByUserId().getId())");

                    //stmt.executeUpdate();
            entityManager.createNativeQuery("INSERT INTO question (uuid, content, date, user_id) VALUES (?,?,?,?)")
                    .setParameter(1, UUID.randomUUID().toString())
                    .setParameter(2, questionEntity.getContent())
                    .setParameter(3, new Date())
                    .setParameter(4, questionEntity.getUsersByUserId().getId())
                    .executeUpdate();
//            stmt.close();
//            c.close();
            //entityManager.persist(questionEntity);
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
