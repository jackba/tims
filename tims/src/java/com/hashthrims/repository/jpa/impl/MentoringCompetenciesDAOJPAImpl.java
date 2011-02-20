/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.repository.jpa.impl;

import com.hashthrims.domain.traininglist.MentoringCompetencies;
import com.hashthrims.repository.jpa.MentoringCompetenciesDAO;
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

@Repository("mentoringCompetenciesDAO")
@Transactional
public class MentoringCompetenciesDAOJPAImpl  implements MentoringCompetenciesDAO{

    @PersistenceContext(type=PersistenceContextType.EXTENDED)
    private EntityManager em;

    @Override
    public MentoringCompetencies find(Long id) {
        return em.find(MentoringCompetencies.class, id);
    }

    @Override
    public void persist(MentoringCompetencies entity) {
        em.persist(entity);
    }

    @Override
    public void merge(MentoringCompetencies entity) {
        em.merge(entity);
    }

    @Override
    public void remove(MentoringCompetencies entity) {
        MentoringCompetencies acc =em.find(MentoringCompetencies.class, entity.getId());
        em.remove(acc);
    }

    @Override
    public List<MentoringCompetencies> findAll() {
        return (List<MentoringCompetencies>) em.createQuery("SELECT a FROM MentoringCompetencies a").getResultList();
    }

    @Override
    public List<MentoringCompetencies> findInRange(int firstResult, int maxResults) {
        return em.createQuery("SELECT a from MentoringCompetencies a").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Override
    public long count() {
         return (Long) em.createQuery("SELECT count(a) FROM MentoringCompetencies a").getSingleResult();
    }

    @Override
    public MentoringCompetencies getByPropertyName(String propertyName, String propertyValue) {
        List<MentoringCompetencies> list = em.createQuery("SELECT e FROM  MentoringCompetencies e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return (list.isEmpty()) ? null : list.get(0);
    }

    @Override
    public List<MentoringCompetencies> getEntitiesByProperName(String propertyName, String propertyValue) {
          List<MentoringCompetencies> list = em.createQuery("SELECT e FROM  MentoringCompetencies e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return list;
    }

}
