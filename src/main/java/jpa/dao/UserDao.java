package jpa.dao;

import jpa.domain.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class UserDao extends AbstractJpaDao<Long, User> {

    private EntityManager manager;

    public UserDao(EntityManager manager){
        super(User.class);
        this.manager = manager;
    }

    public List<User> allUser(){
        Query query = this.manager.createNamedQuery("User.allUser");
        return query.getResultList();
    }

    public List<User> findByEmail(String mail){
        Query query = this.manager.createNamedQuery("User.findByEmail");
        query.setParameter("mail", mail);
        return query.getResultList();
    }

    /*
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
    */



}