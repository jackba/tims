/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.repository.jpa.impl;

import com.hashthrims.domain.traininglist.TrainingCourseCategory;
import com.hashthrims.repository.jpa.TrainingCourseCategoryDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author stud
 */
@Repository("trainingCourseCategoryDAO")
@Transactional
public class TrainingCourseCategoryDAOJPAImpl implements TrainingCourseCategoryDAO{

    @PersistenceContext
    private EntityManager em;

    @Override
    public TrainingCourseCategory find(Long id) {
        return em.find(TrainingCourseCategory.class, id);
    }

    @Override
    public void persist(TrainingCourseCategory entity) {
        em.persist(entity);
    }

    @Override
    public void merge(TrainingCourseCategory entity) {
        em.merge(entity);
    }

    @Override
    public void remove(TrainingCourseCategory entity) {
        TrainingCourseCategory acc =em.find(TrainingCourseCategory.class, entity.getId());
        em.remove(acc);
    }

    @Override
    public List<TrainingCourseCategory> findAll() {
        return (List<TrainingCourseCategory>) em.createQuery("SELECT a FROM TrainingCourseCategory a").getResultList();
    }

    @Override
    public List<TrainingCourseCategory> findInRange(int firstResult, int maxResults) {
        return em.createQuery("SELECT a from TrainingCourseCategory a").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Override
    public long count() {
         return (Long) em.createQuery("SELECT count(a) FROM TrainingCourseCategory a").getSingleResult();
    }

    @Override
    public TrainingCourseCategory getByPropertyName(String propertyName, String propertyValue) {
        List<TrainingCourseCategory> list = em.createQuery("SELECT e FROM  TrainingCourseCategory e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return (list.isEmpty()) ? null : list.get(0);
    }

    @Override
    public List<TrainingCourseCategory> getEntitiesByProperName(String propertyName, String propertyValue) {
          List<TrainingCourseCategory> list = em.createQuery("SELECT e FROM  TrainingCourseCategory e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return list;
    }

}
