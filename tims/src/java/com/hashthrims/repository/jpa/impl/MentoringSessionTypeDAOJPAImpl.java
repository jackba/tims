/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.repository.jpa.impl;

import com.hashthrims.domain.traininglist.MentoringSessionType;
import com.hashthrims.repository.jpa.MentoringSessionTypeDAO;
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

@Repository("mentoringSessionTypeDAO")
@Transactional
public class MentoringSessionTypeDAOJPAImpl  implements MentoringSessionTypeDAO{

    @PersistenceContext(type=PersistenceContextType.EXTENDED)
    private EntityManager em;

    @Override
    public MentoringSessionType find(Long id) {
        return em.find(MentoringSessionType.class, id);
    }

    @Override
    public void persist(MentoringSessionType entity) {
        em.persist(entity);
    }

    @Override
    public void merge(MentoringSessionType entity) {
        em.merge(entity);
    }

    @Override
    public void remove(MentoringSessionType entity) {
        MentoringSessionType acc =em.find(MentoringSessionType.class, entity.getId());
        em.remove(acc);
    }

    @Override
    public List<MentoringSessionType> findAll() {
        return (List<MentoringSessionType>) em.createQuery("SELECT a FROM MentoringSessionType a").getResultList();
    }

    @Override
    public List<MentoringSessionType> findInRange(int firstResult, int maxResults) {
        return em.createQuery("SELECT a from MentoringSessionType a").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Override
    public long count() {
         return (Long) em.createQuery("SELECT count(a) FROM MentoringSessionType a").getSingleResult();
    }

    @Override
    public MentoringSessionType getByPropertyName(String propertyName, String propertyValue) {
        List<MentoringSessionType> list = em.createQuery("SELECT e FROM  MentoringSessionType e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return (list.isEmpty()) ? null : list.get(0);
    }

    @Override
    public List<MentoringSessionType> getEntitiesByProperName(String propertyName, String propertyValue) {
          List<MentoringSessionType> list = em.createQuery("SELECT e FROM  MentoringSessionType e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return list;
    }

}
