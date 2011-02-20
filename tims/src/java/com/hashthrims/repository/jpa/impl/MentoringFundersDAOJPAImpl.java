/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.repository.jpa.impl;

import com.hashthrims.domain.traininglist.MentoringFunders;
import com.hashthrims.repository.jpa.MentoringFundersDAO;
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

@Repository("mentoringFundersDAO")
@Transactional
public class MentoringFundersDAOJPAImpl  implements MentoringFundersDAO{

    @PersistenceContext(type=PersistenceContextType.EXTENDED)
    private EntityManager em;

    @Override
    public MentoringFunders find(Long id) {
        return em.find(MentoringFunders.class, id);
    }

    @Override
    public void persist(MentoringFunders entity) {
        em.persist(entity);
    }

    @Override
    public void merge(MentoringFunders entity) {
        em.merge(entity);
    }

    @Override
    public void remove(MentoringFunders entity) {
        MentoringFunders acc =em.find(MentoringFunders.class, entity.getId());
        em.remove(acc);
    }

    @Override
    public List<MentoringFunders> findAll() {
        return (List<MentoringFunders>) em.createQuery("SELECT a FROM MentoringFunders a").getResultList();
    }

    @Override
    public List<MentoringFunders> findInRange(int firstResult, int maxResults) {
        return em.createQuery("SELECT a from MentoringFunders a").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Override
    public long count() {
         return (Long) em.createQuery("SELECT count(a) FROM MentoringFunders a").getSingleResult();
    }

    @Override
    public MentoringFunders getByPropertyName(String propertyName, String propertyValue) {
        List<MentoringFunders> list = em.createQuery("SELECT e FROM  MentoringFunders e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return (list.isEmpty()) ? null : list.get(0);
    }

    @Override
    public List<MentoringFunders> getEntitiesByProperName(String propertyName, String propertyValue) {
          List<MentoringFunders> list = em.createQuery("SELECT e FROM  MentoringFunders e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return list;
    }

}
