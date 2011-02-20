/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.repository.jpa.impl;


import com.hashthrims.domain.positions.SalarySources;
import com.hashthrims.repository.jpa.SalarySourcesDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author boniface
 */
@Repository("salarySourcesDAO")
@Transactional
public class SalarySourcesDAOJPAImpl  implements SalarySourcesDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public SalarySources find(Long id) {
        return em.find(SalarySources.class, id);
    }

    @Override
    public void persist(SalarySources entity) {
        em.persist(entity);
    }

    @Override
    public void merge(SalarySources entity) {
        em.merge(entity);
    }

    @Override
    public void remove(SalarySources entity) {
        SalarySources acc =em.find(SalarySources.class, entity.getId());
        em.remove(acc);
    }

    @Override
    public List<SalarySources> findAll() {
        return (List<SalarySources>) em.createQuery("SELECT a FROM SalarySources a").getResultList();
    }

    @Override
    public List<SalarySources> findInRange(int firstResult, int maxResults) {
        return em.createQuery("SELECT a from SalarySources a").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Override
    public long count() {
         return (Long) em.createQuery("SELECT count(a) FROM SalarySources a").getSingleResult();
    }

    @Override
    public SalarySources getByPropertyName(String propertyName, String propertyValue) {
        List<SalarySources> list = em.createQuery("SELECT e FROM  SalarySources e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return (list.isEmpty()) ? null : list.get(0);
    }

    @Override
    public List<SalarySources> getEntitiesByProperName(String propertyName, String propertyValue) {
          List<SalarySources> list = em.createQuery("SELECT e FROM  SalarySources e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return list;
    }
}
