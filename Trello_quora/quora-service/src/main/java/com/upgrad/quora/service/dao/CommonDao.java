package com.upgrad.quora.service.dao;

import com.upgrad.quora.service.entity.UsersEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CommonDao {

    @PersistenceContext
    private EntityManager entityManager;

    public UsersEntity getUserProfileById(Integer userId){
        try{
            return (UsersEntity) entityManager.createQuery("SELECT u FROM UsersEntity u WHERE u.id=:id").setParameter("id", userId).getSingleResult();

        }catch (Exception e){
            System.out.println(e);
            return new UsersEntity();
        }
    }
}
