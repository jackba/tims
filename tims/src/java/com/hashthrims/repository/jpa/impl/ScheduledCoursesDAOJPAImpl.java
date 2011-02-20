/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.repository.jpa.impl;

import com.hashthrims.domain.traininglist.ScheduledCourses;
import com.hashthrims.repository.jpa.ScheduledCoursesDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author stud
 */
@Repository("scheduledCoursesDAO")
@Transactional
public class ScheduledCoursesDAOJPAImpl implements ScheduledCoursesDAO{

    @PersistenceContext
    private EntityManager em;

    @Override
    public ScheduledCourses find(Long id) {
        return em.find(ScheduledCourses.class, id);
    }

    @Override
    public void persist(ScheduledCourses entity) {
        em.persist(entity);
    }

    @Override
    public void merge(ScheduledCourses entity) {
        em.merge(entity);
    }

    @Override
    public void remove(ScheduledCourses entity) {
        ScheduledCourses acc =em.find(ScheduledCourses.class, entity.getId());
        em.remove(acc);
    }

    @Override
    public List<ScheduledCourses> findAll() {
        return (List<ScheduledCourses>) em.createQuery("SELECT a FROM ScheduledCourses a").getResultList();
    }

    @Override
    public List<ScheduledCourses> findInRange(int firstResult, int maxResults) {
        return em.createQuery("SELECT a from ScheduledCourses a").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Override
    public long count() {
         return (Long) em.createQuery("SELECT count(a) FROM ScheduledCourses a").getSingleResult();
    }

    @Override
    public ScheduledCourses getByPropertyName(String propertyName, String propertyValue) {
        List<ScheduledCourses> list = em.createQuery("SELECT e FROM  ScheduledCourses e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return (list.isEmpty()) ? null : list.get(0);
    }

    @Override
    public List<ScheduledCourses> getEntitiesByProperName(String propertyName, String propertyValue) {
          List<ScheduledCourses> list = em.createQuery("SELECT e FROM  ScheduledCourses e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return list;
    }

}
