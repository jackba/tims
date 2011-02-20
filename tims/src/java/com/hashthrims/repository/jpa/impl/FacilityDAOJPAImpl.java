/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.repository.jpa.impl;

import com.hashthrims.domain.offices.Facility;
import com.hashthrims.repository.jpa.FacilityDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author stud
 */
@Repository("facilityDAO")
@Transactional
public class FacilityDAOJPAImpl implements FacilityDAO{

    @PersistenceContext
    private EntityManager em;

    @Override
    public Facility find(Long id) {
        return em.find(Facility.class, id);
    }

    @Override
    public void persist(Facility entity) {
        em.persist(entity);
    }

    @Override
    public void merge(Facility entity) {
        em.merge(entity);
    }

    @Override
    public void remove(Facility entity) {
        Facility acc =em.find(Facility.class, entity.getId());
        em.remove(acc);
    }

    @Override
    public List<Facility> findAll() {
        return (List<Facility>) em.createQuery("SELECT a FROM Facility a").getResultList();
    }

    @Override
    public List<Facility> findInRange(int firstResult, int maxResults) {
        return em.createQuery("SELECT a from Facility a").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Override
    public long count() {
         return (Long) em.createQuery("SELECT count(a) FROM Facility a").getSingleResult();
    }

    @Override
    public Facility getByPropertyName(String propertyName, String propertyValue) {
        List<Facility> list = em.createQuery("SELECT e FROM  Facility e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return (list.isEmpty()) ? null : list.get(0);
    }

    @Override
    public List<Facility> getEntitiesByProperName(String propertyName, String propertyValue) {
          List<Facility> list = em.createQuery("SELECT e FROM  Facility e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return list;
    }

}
