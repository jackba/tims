/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.repository.jpa.impl;

import com.hashthrims.domain.traininglist.CourseCompetencies;
import com.hashthrims.repository.jpa.CourseCompetenciesDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author stud
 */
@Repository("courseCompetenciesDAO")
@Transactional
public class CourseCompetenciesDAOJPAImpl implements CourseCompetenciesDAO{

    @PersistenceContext
    private EntityManager em;

    @Override
    public CourseCompetencies find(Long id) {
        return em.find(CourseCompetencies.class, id);
    }

    @Override
    public void persist(CourseCompetencies entity) {
        em.persist(entity);
    }

    @Override
    public void merge(CourseCompetencies entity) {
        em.merge(entity);
    }

    @Override
    public void remove(CourseCompetencies entity) {
        CourseCompetencies acc =em.find(CourseCompetencies.class, entity.getId());
        em.remove(acc);
        em.flush();
    }

    @Override
    public List<CourseCompetencies> findAll() {
        return (List<CourseCompetencies>) em.createQuery("SELECT a FROM CourseCompetencies a").getResultList();
    }

    @Override
    public List<CourseCompetencies> findInRange(int firstResult, int maxResults) {
        return em.createQuery("SELECT a from CourseCompetencies a").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Override
    public long count() {
         return (Long) em.createQuery("SELECT count(a) FROM CourseCompetencies a").getSingleResult();
    }

    @Override
    public CourseCompetencies getByPropertyName(String propertyName, String propertyValue) {
        List<CourseCompetencies> list = em.createQuery("SELECT e FROM  CourseCompetencies e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return (list.isEmpty()) ? null : list.get(0);
    }

    @Override
    public List<CourseCompetencies> getEntitiesByProperName(String propertyName, String propertyValue) {
          List<CourseCompetencies> list = em.createQuery("SELECT e FROM  CourseCompetencies e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return list;
    }

   

}
