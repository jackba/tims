/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.repository.jpa.impl;


import com.hashthrims.domain.Demography;
import com.hashthrims.repository.jpa.DemographyDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author boniface
 */
@Repository("demographyDAO")
@Transactional
public class DemographyDAOJPAImpl  implements DemographyDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Demography find(Long id) {
        return em.find(Demography.class, id);
    }

    @Override
    public void persist(Demography entity) {
        em.persist(entity);
    }

    @Override
    public void merge(Demography entity) {
        em.merge(entity);
    }

    @Override
    public void remove(Demography entity) {
        Demography acc =em.find(Demography.class, entity.getId());
        em.remove(acc);
    }

    @Override
    public List<Demography> findAll() {
        return (List<Demography>) em.createQuery("SELECT a FROM Demography a").getResultList();
    }

    @Override
    public List<Demography> findInRange(int firstResult, int maxResults) {
        return em.createQuery("SELECT a from Demography a").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Override
    public long count() {
         return (Long) em.createQuery("SELECT count(a) FROM Demography a").getSingleResult();
    }

    @Override
    public Demography getByPropertyName(String propertyName, String propertyValue) {
        List<Demography> list = em.createQuery("SELECT e FROM  Demography e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return (list.isEmpty()) ? null : list.get(0);
    }

    @Override
    public List<Demography> getEntitiesByProperName(String propertyName, String propertyValue) {
          List<Demography> list = em.createQuery("SELECT e FROM  Demography e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return list;
    }
}
