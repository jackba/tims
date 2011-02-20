/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.repository.jpa.impl;

import com.hashthrims.domain.employeelist.CompetencyType;
import com.hashthrims.repository.jpa.CompetencyTypeDAO;
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
@Repository("competencyTypeDAO")
@Transactional
public class CompetencyTypeDAOJPAImpl implements CompetencyTypeDAO{

    @PersistenceContext(type=PersistenceContextType.EXTENDED)
    private EntityManager em;

    @Override
    public CompetencyType find(Long id) {
        return em.find(CompetencyType.class, id);
    }

    @Override
    public void persist(CompetencyType entity) {
        em.persist(entity);
    }

    @Override
    public void merge(CompetencyType entity) {
        em.merge(entity);
    }

    @Override
    public void remove(CompetencyType entity) {
        CompetencyType acc =em.find(CompetencyType.class, entity.getId());
        em.remove(acc);
    }

    @Override
    public List<CompetencyType> findAll() {
        return (List<CompetencyType>) em.createQuery("SELECT a FROM CompetencyType a").getResultList();
    }

    @Override
    public List<CompetencyType> findInRange(int firstResult, int maxResults) {
        return em.createQuery("SELECT a from CompetencyType a").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Override
    public long count() {
         return (Long) em.createQuery("SELECT count(a) FROM CompetencyType a").getSingleResult();
    }

    @Override
    public CompetencyType getByPropertyName(String propertyName, String propertyValue) {
        List<CompetencyType> list = em.createQuery("SELECT e FROM  CompetencyType e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return (list.isEmpty()) ? null : list.get(0);
    }

    @Override
    public List<CompetencyType> getEntitiesByProperName(String propertyName, String propertyValue) {
          List<CompetencyType> list = em.createQuery("SELECT e FROM  CompetencyType e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return list;
    }

}
