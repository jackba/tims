/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.repository.jpa.impl;

import com.hashthrims.domain.traininglist.TrainingCourses;
import com.hashthrims.repository.jpa.TrainingCoursesDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author staff
 */
@Repository("trainingCoursesDAO")
@Transactional
public class TrainingCoursesDAOJPAImpl implements TrainingCoursesDAO{

    @PersistenceContext(type=PersistenceContextType.EXTENDED)
    private EntityManager em;

    @Override
    public TrainingCourses find(Long id) {
        return em.find(TrainingCourses.class, id);
    }

    @Override
    public void persist(TrainingCourses entity) {
        em.persist(entity);
    }

    @Override
    public void merge(TrainingCourses entity) {
        em.merge(entity);
    }

    @Override
    public void remove(TrainingCourses entity) {
        TrainingCourses acc =em.find(TrainingCourses.class, entity.getId());
        em.remove(acc);
    }

    @Override
    public List<TrainingCourses> findAll() {
        return (List<TrainingCourses>) em.createQuery("SELECT a FROM TrainingCourses a").getResultList();
    }

    @Override
    public List<TrainingCourses> findInRange(int firstResult, int maxResults) {
        return em.createQuery("SELECT a from TrainingCourses a").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Override
    public long count() {
         return (Long) em.createQuery("SELECT count(a) FROM TrainingCourses a").getSingleResult();
    }

    @Override
    public TrainingCourses getByPropertyName(String propertyName, String propertyValue) {
        List<TrainingCourses> list = em.createQuery("SELECT e FROM  TrainingCourses e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return (list.isEmpty()) ? null : list.get(0);
    }

    @Override
    public List<TrainingCourses> getEntitiesByProperName(String propertyName, String propertyValue) {
          List<TrainingCourses> list = em.createQuery("SELECT e FROM  TrainingCourses e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return list;
    }

}
