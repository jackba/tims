/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.repository.jpa.impl;

import com.hashthrims.domain.traininglist.MentoringMentors;
import com.hashthrims.repository.jpa.MentoringMentorsDAO;
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

@Repository("mentoringMentorsDAO")
@Transactional
public class MentoringMentorsDAOJPAImpl  implements MentoringMentorsDAO{

    @PersistenceContext(type=PersistenceContextType.EXTENDED)
    private EntityManager em;

    @Override
    public MentoringMentors find(Long id) {
        return em.find(MentoringMentors.class, id);
    }

    @Override
    public void persist(MentoringMentors entity) {
        em.persist(entity);
    }

    @Override
    public void merge(MentoringMentors entity) {
        em.merge(entity);
    }

    @Override
    public void remove(MentoringMentors entity) {
        MentoringMentors acc =em.find(MentoringMentors.class, entity.getId());
        em.remove(acc);
    }

    @Override
    public List<MentoringMentors> findAll() {
        return (List<MentoringMentors>) em.createQuery("SELECT a FROM MentoringMentors a").getResultList();
    }

    @Override
    public List<MentoringMentors> findInRange(int firstResult, int maxResults) {
        return em.createQuery("SELECT a from MentoringMentors a").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Override
    public long count() {
         return (Long) em.createQuery("SELECT count(a) FROM MentoringMentors a").getSingleResult();
    }

    @Override
    public MentoringMentors getByPropertyName(String propertyName, String propertyValue) {
        List<MentoringMentors> list = em.createQuery("SELECT e FROM  MentoringMentors e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return (list.isEmpty()) ? null : list.get(0);
    }

    @Override
    public List<MentoringMentors> getEntitiesByProperName(String propertyName, String propertyValue) {
          List<MentoringMentors> list = em.createQuery("SELECT e FROM  MentoringMentors e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return list;
    }

}
