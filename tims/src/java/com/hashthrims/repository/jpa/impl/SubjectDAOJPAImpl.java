/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.repository.jpa.impl;


import com.hashthrims.domain.Subject;
import com.hashthrims.repository.jpa.SubjectDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author boniface
 */

@Repository("subjectDAO")
@Transactional
public class SubjectDAOJPAImpl  implements SubjectDAO{

    @PersistenceContext
    private EntityManager em;

    @Override
    public Subject find(Long id) {
        return em.find(Subject.class, id);
    }

    @Override
    public void persist(Subject entity) {
        em.persist(entity);
    }

    @Override
    public void merge(Subject entity) {
        em.merge(entity);
    }

    @Override
    public void remove(Subject entity) {
        Subject acc =em.find(Subject.class, entity.getId());
        em.remove(acc);
    }

    @Override
    public List<Subject> findAll() {
        return (List<Subject>) em.createQuery("SELECT a FROM Subject a").getResultList();
    }

    @Override
    public List<Subject> findInRange(int firstResult, int maxResults) {
        return em.createQuery("SELECT a from Subject a").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Override
    public long count() {
         return (Long) em.createQuery("SELECT count(a) FROM Subject a").getSingleResult();
    }

    @Override
    public Subject getByPropertyName(String propertyName, String propertyValue) {
        List<Subject> list = em.createQuery("SELECT e FROM  Subject e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return (list.isEmpty()) ? null : list.get(0);
    }

    @Override
    public List<Subject> getEntitiesByProperName(String propertyName, String propertyValue) {
          List<Subject> list = em.createQuery("SELECT e FROM  Subject e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return list;
    }

}
