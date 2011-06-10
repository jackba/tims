/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.repository.jpa.impl;

import com.hashthrims.domain.MentorExpertiseArea;
import com.hashthrims.repository.jpa.MentorExpertiseAreaDAO;
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

@Repository("mentorExpertiseAreaDAO")
@Transactional
public class MentorExpertiseAreaDAOJPAImpl  implements MentorExpertiseAreaDAO{

    @PersistenceContext(type=PersistenceContextType.EXTENDED)
    private EntityManager em;

    @Override
    public MentorExpertiseArea find(Long id) {
        return em.find(MentorExpertiseArea.class, id);
    }

    @Override
    public void persist(MentorExpertiseArea entity) {
        em.persist(entity);
    }

    @Override
    public void merge(MentorExpertiseArea entity) {
        em.merge(entity);
    }

    @Override
    public void remove(MentorExpertiseArea entity) {
        MentorExpertiseArea acc =em.find(MentorExpertiseArea.class, entity.getId());
        em.remove(acc);
    }

    @Override
    public List<MentorExpertiseArea> findAll() {
        return (List<MentorExpertiseArea>) em.createQuery("SELECT a FROM MentorExpertiseArea a").getResultList();
    }

    @Override
    public List<MentorExpertiseArea> findInRange(int firstResult, int maxResults) {
        return em.createQuery("SELECT a from MentorExpertiseArea a").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Override
    public long count() {
         return (Long) em.createQuery("SELECT count(a) FROM MentorExpertiseArea a").getSingleResult();
    }

    @Override
    public MentorExpertiseArea getByPropertyName(String propertyName, String propertyValue) {
        List<MentorExpertiseArea> list = em.createQuery("SELECT e FROM  MentorExpertiseArea e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return (list.isEmpty()) ? null : list.get(0);
    }

    @Override
    public List<MentorExpertiseArea> getEntitiesByProperName(String propertyName, String propertyValue) {
          List<MentorExpertiseArea> list = em.createQuery("SELECT e FROM  MentorExpertiseArea e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return list;
    }

}
