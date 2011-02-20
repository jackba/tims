/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.repository.jpa.impl;

import com.hashthrims.domain.jobs.SalaryGrade;
import com.hashthrims.repository.jpa.SalaryGradesDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author stud
 */
@Repository("salaryGradesDAO")
@Transactional
public class SalaryGradesDAOJPAImpl implements SalaryGradesDAO{

    @PersistenceContext
    private EntityManager em;

    @Override
    public SalaryGrade find(Long id) {
        return em.find(SalaryGrade.class, id);
    }

    @Override
    public void persist(SalaryGrade entity) {
        em.persist(entity);
    }

    @Override
    public void merge(SalaryGrade entity) {
        em.merge(entity);
    }

    @Override
    public void remove(SalaryGrade entity) {
        SalaryGrade acc =em.find(SalaryGrade.class, entity.getId());
        em.remove(acc);
    }

    @Override
    public List<SalaryGrade> findAll() {
        return (List<SalaryGrade>) em.createQuery("SELECT a FROM SalaryGrade a").getResultList();
    }

    @Override
    public List<SalaryGrade> findInRange(int firstResult, int maxResults) {
        return em.createQuery("SELECT a from SalaryGrade a").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Override
    public long count() {
         return (Long) em.createQuery("SELECT count(a) FROM SalaryGrade a").getSingleResult();
    }

    @Override
    public SalaryGrade getByPropertyName(String propertyName, String propertyValue) {
        List<SalaryGrade> list = em.createQuery("SELECT e FROM  SalaryGrade e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return (list.isEmpty()) ? null : list.get(0);
    }

    @Override
    public List<SalaryGrade> getEntitiesByProperName(String propertyName, String propertyValue) {
          List<SalaryGrade> list = em.createQuery("SELECT e FROM  SalaryGrade e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return list;
    }

   

}
