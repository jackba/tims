/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.repository.jpa.impl;

import com.hashthrims.domain.traininglist.OrganisationTrainers;
import com.hashthrims.repository.jpa.OrganisationTrainersDAO;
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

@Repository("organisationTrainersDAO")
@Transactional
public class OrganisationTrainersDAOJPAImpl  implements OrganisationTrainersDAO{

    @PersistenceContext(type=PersistenceContextType.EXTENDED)
    private EntityManager em;

    @Override
    public OrganisationTrainers find(Long id) {
        return em.find(OrganisationTrainers.class, id);
    }

    @Override
    public void persist(OrganisationTrainers entity) {
        em.persist(entity);
    }

    @Override
    public void merge(OrganisationTrainers entity) {
        em.merge(entity);
    }

    @Override
    public void remove(OrganisationTrainers entity) {
        OrganisationTrainers acc =em.find(OrganisationTrainers.class, entity.getId());
        em.remove(acc);
    }

    @Override
    public List<OrganisationTrainers> findAll() {
        return (List<OrganisationTrainers>) em.createQuery("SELECT a FROM OrganisationTrainers a").getResultList();
    }

    @Override
    public List<OrganisationTrainers> findInRange(int firstResult, int maxResults) {
        return em.createQuery("SELECT a from OrganisationTrainers a").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Override
    public long count() {
         return (Long) em.createQuery("SELECT count(a) FROM OrganisationTrainers a").getSingleResult();
    }

    @Override
    public OrganisationTrainers getByPropertyName(String propertyName, String propertyValue) {
        List<OrganisationTrainers> list = em.createQuery("SELECT e FROM  OrganisationTrainers e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return (list.isEmpty()) ? null : list.get(0);
    }

    @Override
    public List<OrganisationTrainers> getEntitiesByProperName(String propertyName, String propertyValue) {
          List<OrganisationTrainers> list = em.createQuery("SELECT e FROM  OrganisationTrainers e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return list;
    }

}
