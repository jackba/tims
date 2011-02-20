/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.repository.jpa.impl;

import com.hashthrims.domain.traininglist.Mentors;
import com.hashthrims.repository.jpa.MentorsDAO;
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

@Repository("mentorsDAO")
@Transactional
public class MentorsDAOJPAImpl  implements MentorsDAO{

    @PersistenceContext(type=PersistenceContextType.EXTENDED)
    private EntityManager em;

    @Override
    public Mentors find(Long id) {
        return em.find(Mentors.class, id);
    }

    @Override
    public void persist(Mentors entity) {
        em.persist(entity);
    }

    @Override
    public void merge(Mentors entity) {
        em.merge(entity);
    }

    @Override
    public void remove(Mentors entity) {
        Mentors acc =em.find(Mentors.class, entity.getId());
        em.remove(acc);
    }

    @Override
    public List<Mentors> findAll() {
        return (List<Mentors>) em.createQuery("SELECT a FROM Mentors a").getResultList();
    }

    @Override
    public List<Mentors> findInRange(int firstResult, int maxResults) {
        return em.createQuery("SELECT a from Mentors a").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Override
    public long count() {
         return (Long) em.createQuery("SELECT count(a) FROM Mentors a").getSingleResult();
    }

    @Override
    public Mentors getByPropertyName(String propertyName, String propertyValue) {
        List<Mentors> list = em.createQuery("SELECT e FROM  Mentors e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return (list.isEmpty()) ? null : list.get(0);
    }

    @Override
    public List<Mentors> getEntitiesByProperName(String propertyName, String propertyValue) {
          List<Mentors> list = em.createQuery("SELECT e FROM  Mentors e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return list;
    }

}
