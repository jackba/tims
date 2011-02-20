/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.repository.jpa.impl;

import com.hashthrims.domain.traininglist.ContiuingEducationCourse;
import com.hashthrims.repository.jpa.ContiuingEducationCourseDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author stud
 */
@Repository("contiuingEducationCourseDAO")
@Transactional
public class ContiuingEducationCourseDAOJPAImpl implements ContiuingEducationCourseDAO{

    @PersistenceContext
    private EntityManager em;

    @Override
    public ContiuingEducationCourse find(Long id) {
        return em.find(ContiuingEducationCourse.class, id);
    }

    @Override
    public void persist(ContiuingEducationCourse entity) {
        em.persist(entity);
    }

    @Override
    public void merge(ContiuingEducationCourse entity) {
        em.merge(entity);
    }

    @Override
    public void remove(ContiuingEducationCourse entity) {
        ContiuingEducationCourse acc =em.find(ContiuingEducationCourse.class, entity.getId());
        em.remove(acc);
    }

    @Override
    public List<ContiuingEducationCourse> findAll() {
        return (List<ContiuingEducationCourse>) em.createQuery("SELECT a FROM ContiuingEducationCourse a").getResultList();
    }

    @Override
    public List<ContiuingEducationCourse> findInRange(int firstResult, int maxResults) {
        return em.createQuery("SELECT a from ContiuingEducationCourse a").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Override
    public long count() {
         return (Long) em.createQuery("SELECT count(a) FROM ContiuingEducationCourse a").getSingleResult();
    }

    @Override
    public ContiuingEducationCourse getByPropertyName(String propertyName, String propertyValue) {
        List<ContiuingEducationCourse> list = em.createQuery("SELECT e FROM  ContiuingEducationCourse e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return (list.isEmpty()) ? null : list.get(0);
    }

    @Override
    public List<ContiuingEducationCourse> getEntitiesByProperName(String propertyName, String propertyValue) {
          List<ContiuingEducationCourse> list = em.createQuery("SELECT e FROM  ContiuingEducationCourse e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return list;
    }

   

}
