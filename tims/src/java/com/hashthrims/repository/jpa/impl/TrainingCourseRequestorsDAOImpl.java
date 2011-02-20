/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.repository.jpa.impl;

import com.hashthrims.domain.traininglist.TrainingCourseRequestors;
import com.hashthrims.repository.jpa.TrainingCourseRequestorsDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author stud
 */
@Repository("trainingCourseRequestorsDAO")
@Transactional
public class TrainingCourseRequestorsDAOImpl implements TrainingCourseRequestorsDAO{
@PersistenceContext
    private EntityManager em;

    @Override
    public TrainingCourseRequestors find(Long id) {
        return em.find(TrainingCourseRequestors.class, id);
    }

    @Override
    public void persist(TrainingCourseRequestors entity) {
        em.persist(entity);
    }

    @Override
    public void merge(TrainingCourseRequestors entity) {
        em.merge(entity);
    }

    @Override
    public void remove(TrainingCourseRequestors entity) {
        TrainingCourseRequestors acc =em.find(TrainingCourseRequestors.class, entity.getId());
        em.remove(acc);
    }

    @Override
    public List<TrainingCourseRequestors> findAll() {
        return (List<TrainingCourseRequestors>) em.createQuery("SELECT a FROM TrainingCourseRequestors a").getResultList();
    }

    @Override
    public List<TrainingCourseRequestors> findInRange(int firstResult, int maxResults) {
        return em.createQuery("SELECT a from TrainingCourseRequestors a").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Override
    public long count() {
         return (Long) em.createQuery("SELECT count(a) FROM TrainingCourseRequestors a").getSingleResult();
    }

    @Override
    public TrainingCourseRequestors getByPropertyName(String propertyName, String propertyValue) {
        List<TrainingCourseRequestors> list = em.createQuery("SELECT e FROM  TrainingCourseRequestors e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return (list.isEmpty()) ? null : list.get(0);
    }

    @Override
    public List<TrainingCourseRequestors> getEntitiesByProperName(String propertyName, String propertyValue) {
          List<TrainingCourseRequestors> list = em.createQuery("SELECT e FROM  TrainingCourseRequestors e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return list;
    }

}
