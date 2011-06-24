/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.repository.jpa.impl;

import com.hashthrims.domain.offices.FacilityMentors;
import com.hashthrims.repository.jpa.FacilityMentorsDAO;
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

@Repository("facilityMentorsDAO")
@Transactional
public class FacilityMentorsDAOJPAImpl  implements FacilityMentorsDAO{

    @PersistenceContext(type=PersistenceContextType.EXTENDED)
    private EntityManager em;

    @Override
    public FacilityMentors find(Long id) {
        return em.find(FacilityMentors.class, id);
    }

    @Override
    public void persist(FacilityMentors entity) {
        em.persist(entity);
    }

    @Override
    public void merge(FacilityMentors entity) {
        em.merge(entity);
    }

    @Override
    public void remove(FacilityMentors entity) {
        FacilityMentors acc =em.find(FacilityMentors.class, entity.getId());
        em.remove(acc);
    }

    @Override
    public List<FacilityMentors> findAll() {
        return (List<FacilityMentors>) em.createQuery("SELECT a FROM FacilityMentors a").getResultList();
    }

    @Override
    public List<FacilityMentors> findInRange(int firstResult, int maxResults) {
        return em.createQuery("SELECT a from FacilityMentors a").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Override
    public long count() {
         return (Long) em.createQuery("SELECT count(a) FROM FacilityMentors a").getSingleResult();
    }

    @Override
    public FacilityMentors getByPropertyName(String propertyName, String propertyValue) {
        List<FacilityMentors> list = em.createQuery("SELECT e FROM  FacilityMentors e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return (list.isEmpty()) ? null : list.get(0);
    }

    @Override
    public List<FacilityMentors> getEntitiesByProperName(String propertyName, String propertyValue) {
          List<FacilityMentors> list = em.createQuery("SELECT e FROM  FacilityMentors e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return list;
    }

}
