/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.repository.jpa.impl;

import com.hashthrims.domain.traininglist.TargetGroup;
import com.hashthrims.repository.jpa.TargetGroupDAO;
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

@Repository("targetGroupDAO")
@Transactional
public class TargetGroupDAOJPAImpl  implements TargetGroupDAO{

    @PersistenceContext(type=PersistenceContextType.EXTENDED)
    private EntityManager em;

    @Override
    public TargetGroup find(Long id) {
        return em.find(TargetGroup.class, id);
    }

    @Override
    public void persist(TargetGroup entity) {
        em.persist(entity);
    }

    @Override
    public void merge(TargetGroup entity) {
        em.merge(entity);
    }

    @Override
    public void remove(TargetGroup entity) {
        TargetGroup acc =em.find(TargetGroup.class, entity.getId());
        em.remove(acc);
    }

    @Override
    public List<TargetGroup> findAll() {
        return (List<TargetGroup>) em.createQuery("SELECT a FROM TargetGroup a").getResultList();
    }

    @Override
    public List<TargetGroup> findInRange(int firstResult, int maxResults) {
        return em.createQuery("SELECT a from TargetGroup a").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Override
    public long count() {
         return (Long) em.createQuery("SELECT count(a) FROM TargetGroup a").getSingleResult();
    }

    @Override
    public TargetGroup getByPropertyName(String propertyName, String propertyValue) {
        List<TargetGroup> list = em.createQuery("SELECT e FROM  TargetGroup e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return (list.isEmpty()) ? null : list.get(0);
    }

    @Override
    public List<TargetGroup> getEntitiesByProperName(String propertyName, String propertyValue) {
          List<TargetGroup> list = em.createQuery("SELECT e FROM  TargetGroup e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return list;
    }

}
