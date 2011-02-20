/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.repository.jpa.impl;


import com.hashthrims.domain.Users;
import com.hashthrims.repository.jpa.UsersDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author boniface
 */
@Repository("usersDAO")
@Transactional
public class UsersDAOJPAImpl  implements UsersDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Users find(Long id) {
        return em.find(Users.class, id);
    }

    @Override
    public void persist(Users entity) {
        em.persist(entity);
    }

    @Override
    public void merge(Users entity) {
        em.merge(entity);
    }

    @Override
    public void remove(Users entity) {
        Users acc =em.find(Users.class, entity.getId());
        em.remove(acc);
    }

    @Override
    public List<Users> findAll() {
        return (List<Users>) em.createQuery("SELECT a FROM Users a").getResultList();
    }

    @Override
    public List<Users> findInRange(int firstResult, int maxResults) {
        return em.createQuery("SELECT a from Users a").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Override
    public long count() {
         return (Long) em.createQuery("SELECT count(a) FROM Users a").getSingleResult();
    }

    @Override
    public Users getByPropertyName(String propertyName, String propertyValue) {
        List<Users> list = em.createQuery("SELECT e FROM  Users e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return (list.isEmpty()) ? null : list.get(0);
    }

    @Override
    public List<Users> getEntitiesByProperName(String propertyName, String propertyValue) {
          List<Users> list = em.createQuery("SELECT e FROM  Users e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return list;
    }
}
