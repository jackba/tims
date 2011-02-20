/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.repository.jpa.impl;

import com.hashthrims.domain.employeelist.GenderList;
import com.hashthrims.repository.jpa.GenderListDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author stud
 */
@Repository("genderListDAO")
@Transactional
public class GenderListDAOJPAImpl implements GenderListDAO{

    @PersistenceContext
    private EntityManager em;

    @Override
    public GenderList find(Long id) {
        return em.find(GenderList.class, id);
    }

    @Override
    public void persist(GenderList entity) {
        em.persist(entity);
    }

    @Override
    public void merge(GenderList entity) {
        em.merge(entity);
    }

    @Override
    public void remove(GenderList entity) {
        GenderList acc =em.find(GenderList.class, entity.getId());
        em.remove(acc);
    }

    @Override
    public List<GenderList> findAll() {
        return (List<GenderList>) em.createQuery("SELECT a FROM GenderList a").getResultList();
    }

    @Override
    public List<GenderList> findInRange(int firstResult, int maxResults) {
        return em.createQuery("SELECT a from GenderList a").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Override
    public long count() {
         return (Long) em.createQuery("SELECT count(a) FROM GenderList a").getSingleResult();
    }

    @Override
    public GenderList getByPropertyName(String propertyName, String propertyValue) {
        List<GenderList> list = em.createQuery("SELECT e FROM  GenderList e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return (list.isEmpty()) ? null : list.get(0);
    }

    @Override
    public List<GenderList> getEntitiesByProperName(String propertyName, String propertyValue) {
          List<GenderList> list = em.createQuery("SELECT e FROM  GenderList e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return list;
    }

   

}
