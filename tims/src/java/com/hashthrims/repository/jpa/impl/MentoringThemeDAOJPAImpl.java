/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.repository.jpa.impl;

import com.hashthrims.domain.traininglist.MentoringTheme;
import com.hashthrims.repository.jpa.MentoringThemeDAO;
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

@Repository("mentoringThemeDAO")
@Transactional
public class MentoringThemeDAOJPAImpl  implements MentoringThemeDAO{

    @PersistenceContext(type=PersistenceContextType.EXTENDED)
    private EntityManager em;

    @Override
    public MentoringTheme find(Long id) {
        return em.find(MentoringTheme.class, id);
    }

    @Override
    public void persist(MentoringTheme entity) {
        em.persist(entity);
    }

    @Override
    public void merge(MentoringTheme entity) {
        em.merge(entity);
    }

    @Override
    public void remove(MentoringTheme entity) {
        MentoringTheme acc =em.find(MentoringTheme.class, entity.getId());
        em.remove(acc);
    }

    @Override
    public List<MentoringTheme> findAll() {
        return (List<MentoringTheme>) em.createQuery("SELECT a FROM MentoringTheme a").getResultList();
    }

    @Override
    public List<MentoringTheme> findInRange(int firstResult, int maxResults) {
        return em.createQuery("SELECT a from MentoringTheme a").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Override
    public long count() {
         return (Long) em.createQuery("SELECT count(a) FROM MentoringTheme a").getSingleResult();
    }

    @Override
    public MentoringTheme getByPropertyName(String propertyName, String propertyValue) {
        List<MentoringTheme> list = em.createQuery("SELECT e FROM  MentoringTheme e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return (list.isEmpty()) ? null : list.get(0);
    }

    @Override
    public List<MentoringTheme> getEntitiesByProperName(String propertyName, String propertyValue) {
          List<MentoringTheme> list = em.createQuery("SELECT e FROM  MentoringTheme e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return list;
    }

}
