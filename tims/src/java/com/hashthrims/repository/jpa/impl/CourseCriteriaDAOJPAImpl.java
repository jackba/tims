/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.repository.jpa.impl;

import com.hashthrims.domain.traininglist.CourseCriteria;
import com.hashthrims.repository.jpa.CourseCriteriaDAO;
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

@Repository("courseCriteriaDAO")
@Transactional
public class CourseCriteriaDAOJPAImpl  implements CourseCriteriaDAO{

    @PersistenceContext(type=PersistenceContextType.EXTENDED)
    private EntityManager em;

    @Override
    public CourseCriteria find(Long id) {
        return em.find(CourseCriteria.class, id);
    }

    @Override
    public void persist(CourseCriteria entity) {
        em.persist(entity);
    }

    @Override
    public void merge(CourseCriteria entity) {
        em.merge(entity);
    }

    @Override
    public void remove(CourseCriteria entity) {
        CourseCriteria acc =em.find(CourseCriteria.class, entity.getId());
        em.remove(acc);
    }

    @Override
    public List<CourseCriteria> findAll() {
        return (List<CourseCriteria>) em.createQuery("SELECT a FROM CourseCriteria a").getResultList();
    }

    @Override
    public List<CourseCriteria> findInRange(int firstResult, int maxResults) {
        return em.createQuery("SELECT a from CourseCriteria a").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Override
    public long count() {
         return (Long) em.createQuery("SELECT count(a) FROM CourseCriteria a").getSingleResult();
    }

    @Override
    public CourseCriteria getByPropertyName(String propertyName, String propertyValue) {
        List<CourseCriteria> list = em.createQuery("SELECT e FROM  CourseCriteria e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return (list.isEmpty()) ? null : list.get(0);
    }

    @Override
    public List<CourseCriteria> getEntitiesByProperName(String propertyName, String propertyValue) {
          List<CourseCriteria> list = em.createQuery("SELECT e FROM  CourseCriteria e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return list;
    }

}
