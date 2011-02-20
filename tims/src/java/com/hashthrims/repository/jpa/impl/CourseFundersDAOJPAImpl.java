/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.repository.jpa.impl;

import com.hashthrims.domain.traininglist.CourseFunders;
import com.hashthrims.repository.jpa.CourseFundersDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author stud
 */
@Repository("courseFundersDAO")
@Transactional
public class CourseFundersDAOJPAImpl implements CourseFundersDAO{

    @PersistenceContext
    private EntityManager em;

    @Override
    public CourseFunders find(Long id) {
        return em.find(CourseFunders.class, id);
    }

    @Override
    public void persist(CourseFunders entity) {
        em.persist(entity);
    }

    @Override
    public void merge(CourseFunders entity) {
        em.merge(entity);
    }

    @Override
    public void remove(CourseFunders entity) {
        CourseFunders acc =em.find(CourseFunders.class, entity.getId());
        em.remove(acc);
        em.flush();
    }

    @Override
    public List<CourseFunders> findAll() {
        return (List<CourseFunders>) em.createQuery("SELECT a FROM CourseFunders a").getResultList();
    }

    @Override
    public List<CourseFunders> findInRange(int firstResult, int maxResults) {
        return em.createQuery("SELECT a from CourseFunders a").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Override
    public long count() {
         return (Long) em.createQuery("SELECT count(a) FROM CourseFunders a").getSingleResult();
    }

    @Override
    public CourseFunders getByPropertyName(String propertyName, String propertyValue) {
        List<CourseFunders> list = em.createQuery("SELECT e FROM  CourseFunders e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return (list.isEmpty()) ? null : list.get(0);
    }

    @Override
    public List<CourseFunders> getEntitiesByProperName(String propertyName, String propertyValue) {
          List<CourseFunders> list = em.createQuery("SELECT e FROM  CourseFunders e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return list;
    }

   

}
