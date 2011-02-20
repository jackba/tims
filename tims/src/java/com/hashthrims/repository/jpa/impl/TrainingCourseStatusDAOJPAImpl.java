/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.repository.jpa.impl;

import com.hashthrims.domain.traininglist.TrainingCourseStatus;
import com.hashthrims.repository.jpa.TrainingCourseStatusDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author stud
 */
@Repository("trainingCourseStatusDAO")
@Transactional
public class TrainingCourseStatusDAOJPAImpl implements TrainingCourseStatusDAO{

    @PersistenceContext
    private EntityManager em;

    @Override
    public TrainingCourseStatus find(Long id) {
        return em.find(TrainingCourseStatus.class, id);
    }

    @Override
    public void persist(TrainingCourseStatus entity) {
        em.persist(entity);
    }

    @Override
    public void merge(TrainingCourseStatus entity) {
        em.merge(entity);
    }

    @Override
    public void remove(TrainingCourseStatus entity) {
        TrainingCourseStatus acc =em.find(TrainingCourseStatus.class, entity.getId());
        em.remove(acc);
    }

    @Override
    public List<TrainingCourseStatus> findAll() {
        return (List<TrainingCourseStatus>) em.createQuery("SELECT a FROM TrainingCourseStatus a").getResultList();
    }

    @Override
    public List<TrainingCourseStatus> findInRange(int firstResult, int maxResults) {
        return em.createQuery("SELECT a from TrainingCourseStatus a").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Override
    public long count() {
         return (Long) em.createQuery("SELECT count(a) FROM TrainingCourseStatus a").getSingleResult();
    }

    @Override
    public TrainingCourseStatus getByPropertyName(String propertyName, String propertyValue) {
        List<TrainingCourseStatus> list = em.createQuery("SELECT e FROM  TrainingCourseStatus e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return (list.isEmpty()) ? null : list.get(0);
    }

    @Override
    public List<TrainingCourseStatus> getEntitiesByProperName(String propertyName, String propertyValue) {
          List<TrainingCourseStatus> list = em.createQuery("SELECT e FROM  TrainingCourseStatus e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return list;
    }

}
