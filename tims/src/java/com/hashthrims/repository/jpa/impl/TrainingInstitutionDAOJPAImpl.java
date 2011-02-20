/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.repository.jpa.impl;

import com.hashthrims.domain.traininglist.TrainingInstitution;
import com.hashthrims.repository.jpa.TrainingInstitutionDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author staff
 */
@Repository("trainingInstitutionDAO")
@Transactional
public class TrainingInstitutionDAOJPAImpl implements TrainingInstitutionDAO{

    @PersistenceContext
    private EntityManager em;

    @Override
    public TrainingInstitution find(Long id) {
        return em.find(TrainingInstitution.class, id);
    }

    @Override
    public void persist(TrainingInstitution entity) {
        em.persist(entity);
    }

    @Override
    public void merge(TrainingInstitution entity) {
        em.merge(entity);
    }

    @Override
    public void remove(TrainingInstitution entity) {
        TrainingInstitution acc =em.find(TrainingInstitution.class, entity.getId());
        em.remove(acc);
    }

    @Override
    public List<TrainingInstitution> findAll() {
        return (List<TrainingInstitution>) em.createQuery("SELECT a FROM TrainingInstitution a").getResultList();
    }

    @Override
    public List<TrainingInstitution> findInRange(int firstResult, int maxResults) {
        return em.createQuery("SELECT a from TrainingInstitution a").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Override
    public long count() {
         return (Long) em.createQuery("SELECT count(a) FROM TrainingInstitution a").getSingleResult();
    }

    @Override
    public TrainingInstitution getByPropertyName(String propertyName, String propertyValue) {
        List<TrainingInstitution> list = em.createQuery("SELECT e FROM  TrainingInstitution e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return (list.isEmpty()) ? null : list.get(0);
    }

    @Override
    public List<TrainingInstitution> getEntitiesByProperName(String propertyName, String propertyValue) {
          List<TrainingInstitution> list = em.createQuery("SELECT e FROM  TrainingInstitution e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return list;
    }

}
