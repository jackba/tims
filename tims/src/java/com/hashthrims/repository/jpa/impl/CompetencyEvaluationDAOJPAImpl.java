/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.repository.jpa.impl;

import com.hashthrims.domain.employeelist.CompetencyEvaluation;
import com.hashthrims.repository.jpa.CompetencyEvaluationDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author stud
 */
@Repository("competencyEvaluationDAO")
@Transactional
public class CompetencyEvaluationDAOJPAImpl implements CompetencyEvaluationDAO{

    @PersistenceContext(type=PersistenceContextType.EXTENDED)
    private EntityManager em;

    @Override
    public CompetencyEvaluation find(Long id) {
        return em.find(CompetencyEvaluation.class, id);
    }

    @Override
    public void persist(CompetencyEvaluation entity) {
        em.persist(entity);
    }

    @Override
    public void merge(CompetencyEvaluation entity) {
        em.merge(entity);
    }

    @Override
    public void remove(CompetencyEvaluation entity) {
        CompetencyEvaluation acc =em.find(CompetencyEvaluation.class, entity.getId());
        em.remove(acc);
    }

    @Override
    public List<CompetencyEvaluation> findAll() {
        return (List<CompetencyEvaluation>) em.createQuery("SELECT a FROM CompetencyEvaluation a").getResultList();
    }

    @Override
    public List<CompetencyEvaluation> findInRange(int firstResult, int maxResults) {
        return em.createQuery("SELECT a from CompetencyEvaluation a").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Override
    public long count() {
         return (Long) em.createQuery("SELECT count(a) FROM CompetencyEvaluation a").getSingleResult();
    }

    @Override
    public CompetencyEvaluation getByPropertyName(String propertyName, String propertyValue) {
        List<CompetencyEvaluation> list = em.createQuery("SELECT e FROM  CompetencyEvaluation e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return (list.isEmpty()) ? null : list.get(0);
    }

    @Override
    public List<CompetencyEvaluation> getEntitiesByProperName(String propertyName, String propertyValue) {
          List<CompetencyEvaluation> list = em.createQuery("SELECT e FROM  CompetencyEvaluation e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return list;
    }

}
