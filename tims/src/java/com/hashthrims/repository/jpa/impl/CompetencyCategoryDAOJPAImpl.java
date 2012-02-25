/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.repository.jpa.impl;


import com.hashthrims.domain.nimart.CompetencyCategory;
import com.hashthrims.repository.jpa.CompetencyCategoryDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author boniface
 */
@Repository("competencyCategoryDAO")
@Transactional
public class CompetencyCategoryDAOJPAImpl  implements CompetencyCategoryDAO {

   @PersistenceContext(type=PersistenceContextType.EXTENDED)
    private EntityManager em;

    @Override
    public CompetencyCategory find(Long id) {
        return em.find(CompetencyCategory.class, id);
    }

    @Override
    public void persist(CompetencyCategory entity) {
        em.persist(entity);
    }

    @Override
    public void merge(CompetencyCategory entity) {
        em.merge(entity);
        
    }

    @Override
    public void remove(CompetencyCategory entity) {
        CompetencyCategory acc =em.find(CompetencyCategory.class, entity.getId());
        em.remove(acc);
    }

    @Override
    public List<CompetencyCategory> findAll() {
        return (List<CompetencyCategory>) em.createQuery("SELECT a FROM CompetencyCategory a").getResultList();
    }

    @Override
    public List<CompetencyCategory> findInRange(int firstResult, int maxResults) {
        return em.createQuery("SELECT a from CompetencyCategory a").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Override
    public long count() {
         return (Long) em.createQuery("SELECT count(a) FROM CompetencyCategory a").getSingleResult();
    }

    @Override
    public CompetencyCategory getByPropertyName(String propertyName, String propertyValue) {
        List<CompetencyCategory> list = em.createQuery("SELECT e FROM  CompetencyCategory e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return (list.isEmpty()) ? null : list.get(0);
    }

    @Override
    public List<CompetencyCategory> getEntitiesByProperName(String propertyName, String propertyValue) {
          List<CompetencyCategory> list = em.createQuery("SELECT e FROM  CompetencyCategory e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return list;
    }
}
