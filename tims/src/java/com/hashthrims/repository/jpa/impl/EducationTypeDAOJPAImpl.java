/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.repository.jpa.impl;

import com.hashthrims.domain.employeelist.EducationType;
import com.hashthrims.repository.jpa. EducationTypeDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author stud
 */
@Repository("educationTypeDAO")
@Transactional
public class EducationTypeDAOJPAImpl implements  EducationTypeDAO{

    @PersistenceContext
    private EntityManager em;

    @Override
    public  EducationType find(Long id) {
        return em.find( EducationType.class, id);
    }

    @Override
    public void persist( EducationType entity) {
        em.persist(entity);
    }

    @Override
    public void merge( EducationType entity) {
        em.merge(entity);
    }

    @Override
    public void remove( EducationType entity) {
         EducationType acc =em.find( EducationType.class, entity.getId());
        em.remove(acc);
    }

    @Override
    public List< EducationType> findAll() {
        return (List< EducationType>) em.createQuery("SELECT a FROM  EducationType a").getResultList();
    }

    @Override
    public List< EducationType> findInRange(int firstResult, int maxResults) {
        return em.createQuery("SELECT a from  EducationType a").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Override
    public long count() {
         return (Long) em.createQuery("SELECT count(a) FROM  EducationType a").getSingleResult();
    }

    @Override
    public  EducationType getByPropertyName(String propertyName, String propertyValue) {
        List< EducationType> list = em.createQuery("SELECT e FROM   EducationType e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return (list.isEmpty()) ? null : list.get(0);
    }

    @Override
    public List< EducationType> getEntitiesByProperName(String propertyName, String propertyValue) {
          List< EducationType> list = em.createQuery("SELECT e FROM   EducationType e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return list;
    }

}
