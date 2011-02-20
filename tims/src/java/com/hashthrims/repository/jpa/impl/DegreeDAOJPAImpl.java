/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.repository.jpa.impl;

import com.hashthrims.domain.employeelist.Degree;
import com.hashthrims.repository.jpa.DegreeDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author stud
 */
@Repository("degreeDAO")
@Transactional
public class DegreeDAOJPAImpl implements DegreeDAO{

    @PersistenceContext
    private EntityManager em;

    @Override
    public Degree find(Long id) {
        return em.find(Degree.class, id);
    }

    @Override
    public void persist(Degree entity) {
        em.persist(entity);
    }

    @Override
    public void merge(Degree entity) {
        em.merge(entity);
    }

    @Override
    public void remove(Degree entity) {
        Degree acc =em.find(Degree.class, entity.getId());
        em.remove(acc);
    }

    @Override
    public List<Degree> findAll() {
        return (List<Degree>) em.createQuery("SELECT a FROM Degree a").getResultList();
    }

    @Override
    public List<Degree> findInRange(int firstResult, int maxResults) {
        return em.createQuery("SELECT a from Degree a").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Override
    public long count() {
         return (Long) em.createQuery("SELECT count(a) FROM Degree a").getSingleResult();
    }

    @Override
    public Degree getByPropertyName(String propertyName, String propertyValue) {
        List<Degree> list = em.createQuery("SELECT e FROM  Degree e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return (list.isEmpty()) ? null : list.get(0);
    }

    @Override
    public List<Degree> getEntitiesByProperName(String propertyName, String propertyValue) {
          List<Degree> list = em.createQuery("SELECT e FROM  Degree e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return list;
    }

}
