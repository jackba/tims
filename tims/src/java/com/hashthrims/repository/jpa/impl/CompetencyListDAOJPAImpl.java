/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.repository.jpa.impl;

import com.hashthrims.domain.employeelist.CompetencyList;
import com.hashthrims.repository.jpa.CompetencyListDAO;
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
@Repository("competencyListDAO")
@Transactional
public class CompetencyListDAOJPAImpl implements CompetencyListDAO{

    @PersistenceContext(type=PersistenceContextType.EXTENDED)
    private EntityManager em;

    @Override
    public CompetencyList find(Long id) {
        return em.find(CompetencyList.class, id);
    }

    @Override
    public void persist(CompetencyList entity) {
        em.persist(entity);
    }

    @Override
    public void merge(CompetencyList entity) {
        em.merge(entity);
    }

    @Override
    public void remove(CompetencyList entity) {
        CompetencyList acc =em.find(CompetencyList.class, entity.getId());
        em.remove(acc);
    }

    @Override
    public List<CompetencyList> findAll() {
        return (List<CompetencyList>) em.createQuery("SELECT a FROM CompetencyList a").getResultList();
    }

    @Override
    public List<CompetencyList> findInRange(int firstResult, int maxResults) {
        return em.createQuery("SELECT a from CompetencyList a").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Override
    public long count() {
         return (Long) em.createQuery("SELECT count(a) FROM CompetencyList a").getSingleResult();
    }

    @Override
    public CompetencyList getByPropertyName(String propertyName, String propertyValue) {
        List<CompetencyList> list = em.createQuery("SELECT e FROM  CompetencyList e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return (list.isEmpty()) ? null : list.get(0);
    }

    @Override
    public List<CompetencyList> getEntitiesByProperName(String propertyName, String propertyValue) {
          List<CompetencyList> list = em.createQuery("SELECT e FROM  CompetencyList e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return list;
    }

}
