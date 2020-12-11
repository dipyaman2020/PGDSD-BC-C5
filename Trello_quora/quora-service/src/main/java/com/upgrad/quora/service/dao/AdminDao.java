package com.upgrad.quora.service.dao;

import com.upgrad.quora.service.entity.UsersEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Repository
public class AdminDao {

    // private EntityManagerFactory emf;

    @PersistenceContext
    private EntityManager entityManager;

    public String removeUser(String uuid){
        String status = null;
        try {
            //Users users = entityManager.createQuery("SELECT u from Users u WHERE u.uuid="+"'database_uuid1'", Users.class).getSingleResult();
            UsersEntity user = entityManager.find(UsersEntity.class,1026);
            TypedQuery<UsersEntity> q = entityManager.createQuery("SELECT u FROM UsersEntity u WHERE u.uuid = :uuid", UsersEntity.class);
            q.setParameter("uuid", uuid);
            UsersEntity users = q.getSingleResult();

            if(users!=null) {
                entityManager.remove(users);
                status = "USER SUCCESSFULLY DELETED";
            }
            else {
                status = "User with entered uuid to be deleted does not exist";
            }
        }catch (Exception e){
            System.out.println(e);
            status = "Failed";
        }finally {
            return status;
        }
    }

    /*public String removeUser1(String uuid){

        String status = null;
        try {
            Users user = entityManager.find(Users.class, uuid);
            if(user!=null) {
                entityManager.remove(user);
                status = "USER SUCCESSFULLY DELETED";
            }
            else {
                status = "User with entered uuid to be deleted does not exist";
            }
        } catch (Exception e) {
            System.out.println(e);
        }finally {
            return status;
        }
    }*/
}
