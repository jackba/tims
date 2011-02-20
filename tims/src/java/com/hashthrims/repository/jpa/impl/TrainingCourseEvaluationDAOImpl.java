/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.repository.jpa.impl;

import com.hashthrims.domain.traininglist.TrainingCourseEvaluation;
import com.hashthrims.repository.jpa.TrainingCourseEvaluationDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author stud
 */
@Repository("trainingCourseEvaluationDAO")
@Transactional
public class TrainingCourseEvaluationDAOImpl implements TrainingCourseEvaluationDAO{

    @PersistenceContext
    private EntityManager em;

    @Override
    public TrainingCourseEvaluation find(Long id) {
        return em.find(TrainingCourseEvaluation.class, id);
    }

    @Override
    public void persist(TrainingCourseEvaluation entity) {
        em.persist(entity);
    }

    @Override
    public void merge(TrainingCourseEvaluation entity) {
        em.merge(entity);
    }

    @Override
    public void remove(TrainingCourseEvaluation entity) {
        TrainingCourseEvaluation acc =em.find(TrainingCourseEvaluation.class, entity.getId());
        em.remove(acc);
    }

    @Override
    public List<TrainingCourseEvaluation> findAll() {
        return (List<TrainingCourseEvaluation>) em.createQuery("SELECT a FROM TrainingCourseEvaluation a").getResultList();
    }

    @Override
    public List<TrainingCourseEvaluation> findInRange(int firstResult, int maxResults) {
        return em.createQuery("SELECT a from TrainingCourseEvaluation a").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Override
    public long count() {
         return (Long) em.createQuery("SELECT count(a) FROM TrainingCourseEvaluation a").getSingleResult();
    }

    @Override
    public TrainingCourseEvaluation getByPropertyName(String propertyName, String propertyValue) {
        List<TrainingCourseEvaluation> list = em.createQuery("SELECT e FROM  TrainingCourseEvaluation e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return (list.isEmpty()) ? null : list.get(0);
    }

    @Override
    public List<TrainingCourseEvaluation> getEntitiesByProperName(String propertyName, String propertyValue) {
          List<TrainingCourseEvaluation> list = em.createQuery("SELECT e FROM  TrainingCourseEvaluation e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return list;
    }
}
