/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.repository.jpa.impl;

import com.hashthrims.domain.traininglist.MentoringAreasList;
import com.hashthrims.repository.jpa.MentoringAreasListDAO;
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

@Repository("mentoringAreasListDAO")
@Transactional
public class MentoringAreasListDAOJPAImpl  implements MentoringAreasListDAO{

    @PersistenceContext(type=PersistenceContextType.EXTENDED)
    private EntityManager em;

    @Override
    public MentoringAreasList find(Long id) {
        return em.find(MentoringAreasList.class, id);
    }

    @Override
    public void persist(MentoringAreasList entity) {
        em.persist(entity);
    }

    @Override
    public void merge(MentoringAreasList entity) {
        em.merge(entity);
    }

    @Override
    public void remove(MentoringAreasList entity) {
        MentoringAreasList acc =em.find(MentoringAreasList.class, entity.getId());
        em.remove(acc);
    }

    @Override
    public List<MentoringAreasList> findAll() {
        return (List<MentoringAreasList>) em.createQuery("SELECT a FROM MentoringAreasList a").getResultList();
    }

    @Override
    public List<MentoringAreasList> findInRange(int firstResult, int maxResults) {
        return em.createQuery("SELECT a from MentoringAreasList a").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Override
    public long count() {
         return (Long) em.createQuery("SELECT count(a) FROM MentoringAreasList a").getSingleResult();
    }

    @Override
    public MentoringAreasList getByPropertyName(String propertyName, String propertyValue) {
        List<MentoringAreasList> list = em.createQuery("SELECT e FROM  MentoringAreasList e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return (list.isEmpty()) ? null : list.get(0);
    }

    @Override
    public List<MentoringAreasList> getEntitiesByProperName(String propertyName, String propertyValue) {
          List<MentoringAreasList> list = em.createQuery("SELECT e FROM  MentoringAreasList e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return list;
    }

}
