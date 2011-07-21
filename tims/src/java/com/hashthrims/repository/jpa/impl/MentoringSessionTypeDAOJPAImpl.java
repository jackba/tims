/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.repository.jpa.impl;

import com.hashthrims.domain.traininglist.SessionType;
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
    public SessionType find(Long id) {
        return em.find(SessionType.class, id);
    }

    @Override
    public void persist(SessionType entity) {
        em.persist(entity);
    }

    @Override
    public void merge(SessionType entity) {
        em.merge(entity);
    }

    @Override
    public void remove(SessionType entity) {
        SessionType acc =em.find(SessionType.class, entity.getId());
        em.remove(acc);
    }

    @Override
    public List<SessionType> findAll() {
        return (List<SessionType>) em.createQuery("SELECT a FROM SessionType a").getResultList();
    }

    @Override
    public List<SessionType> findInRange(int firstResult, int maxResults) {
        return em.createQuery("SELECT a from SessionType a").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Override
    public long count() {
         return (Long) em.createQuery("SELECT count(a) FROM SessionType a").getSingleResult();
    }

    @Override
    public SessionType getByPropertyName(String propertyName, String propertyValue) {
        List<SessionType> list = em.createQuery("SELECT e FROM  SessionType e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return (list.isEmpty()) ? null : list.get(0);
    }

    @Override
    public List<SessionType> getEntitiesByProperName(String propertyName, String propertyValue) {
          List<SessionType> list = em.createQuery("SELECT e FROM  SessionType e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return list;
    }

}
