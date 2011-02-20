/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.repository.jpa.impl;

import com.hashthrims.domain.offices.FacilityType;
import com.hashthrims.repository.jpa.FacilityTypeDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author stud
 */
@Repository("facilityTypeDAO")
@Transactional
public class FacilityTypeDAOJPAImpl implements FacilityTypeDAO{

    @PersistenceContext
    private EntityManager em;

    @Override
    public FacilityType find(Long id) {
        return em.find(FacilityType.class, id);
    }

    @Override
    public void persist(FacilityType entity) {
        em.persist(entity);
    }

    @Override
    public void merge(FacilityType entity) {
        em.merge(entity);
    }

    @Override
    public void remove(FacilityType entity) {
        FacilityType acc =em.find(FacilityType.class, entity.getId());
        em.remove(acc);
    }

    @Override
    public List<FacilityType> findAll() {
        return (List<FacilityType>) em.createQuery("SELECT a FROM FacilityType a").getResultList();
    }

    @Override
    public List<FacilityType> findInRange(int firstResult, int maxResults) {
        return em.createQuery("SELECT a from FacilityType a").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Override
    public long count() {
         return (Long) em.createQuery("SELECT count(a) FROM FacilityType a").getSingleResult();
    }

    @Override
    public FacilityType getByPropertyName(String propertyName, String propertyValue) {
        List<FacilityType> list = em.createQuery("SELECT e FROM  FacilityType e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return (list.isEmpty()) ? null : list.get(0);
    }

    @Override
    public List<FacilityType> getEntitiesByProperName(String propertyName, String propertyValue) {
          List<FacilityType> list = em.createQuery("SELECT e FROM  FacilityType e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return list;
    }

}
