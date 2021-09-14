package jpa.dao;

import jpa.domain.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class UserDao {

    private EntityManager manager;

    public UserDao(EntityManager manager){
        this.manager = manager;
    }

    public void addUser(User user){
        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        try {
            manager.persist(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        tx.commit();
    }


}