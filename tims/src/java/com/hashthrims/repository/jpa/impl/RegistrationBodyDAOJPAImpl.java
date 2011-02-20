/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.repository.jpa.impl;

import com.hashthrims.domain.offices.RegistrationBody;
import com.hashthrims.repository.jpa.RegistrationBodyDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author stud
 */
@Repository("registrationBodyDAO")
@Transactional
public class RegistrationBodyDAOJPAImpl implements RegistrationBodyDAO{

    @PersistenceContext
    private EntityManager em;

    @Override
    public RegistrationBody find(Long id) {
        return em.find(RegistrationBody.class, id);
    }

    @Override
    public void persist(RegistrationBody entity) {
        em.persist(entity);
    }

    @Override
    public void merge(RegistrationBody entity) {
        em.merge(entity);
    }

    @Override
    public void remove(RegistrationBody entity) {
        RegistrationBody acc =em.find(RegistrationBody.class, entity.getId());
        em.remove(acc);
    }

    @Override
    public List<RegistrationBody> findAll() {
        return (List<RegistrationBody>) em.createQuery("SELECT a FROM RegistrationBody a").getResultList();
    }

    @Override
    public List<RegistrationBody> findInRange(int firstResult, int maxResults) {
        return em.createQuery("SELECT a from RegistrationBody a").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Override
    public long count() {
         return (Long) em.createQuery("SELECT count(a) FROM RegistrationBody a").getSingleResult();
    }

    @Override
    public RegistrationBody getByPropertyName(String propertyName, String propertyValue) {
        List<RegistrationBody> list = em.createQuery("SELECT e FROM  RegistrationBody e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return (list.isEmpty()) ? null : list.get(0);
    }

    @Override
    public List<RegistrationBody> getEntitiesByProperName(String propertyName, String propertyValue) {
          List<RegistrationBody> list = em.createQuery("SELECT e FROM  RegistrationBody e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return list;
    }

}
