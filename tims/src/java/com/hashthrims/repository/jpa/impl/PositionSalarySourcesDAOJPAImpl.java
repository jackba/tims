/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.repository.jpa.impl;

import com.hashthrims.domain.positions.PositionSalarySources;
import com.hashthrims.repository.jpa.PositionSalarySourcesDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author boniface
 */

@Repository("positionSalarySourcesDAO")
@Transactional
public class PositionSalarySourcesDAOJPAImpl  implements PositionSalarySourcesDAO{

    @PersistenceContext
    private EntityManager em;

    @Override
    public PositionSalarySources find(Long id) {
        return em.find(PositionSalarySources.class, id);
    }

    @Override
    public void persist(PositionSalarySources entity) {
        em.persist(entity);
    }

    @Override
    public void merge(PositionSalarySources entity) {
        em.merge(entity);
    }

    @Override
    public void remove(PositionSalarySources entity) {
        PositionSalarySources acc =em.find(PositionSalarySources.class, entity.getId());
        em.remove(entity);
    }

    @Override
    public List<PositionSalarySources> findAll() {
        return (List<PositionSalarySources>) em.createQuery("SELECT a FROM PositionSalarySources a").getResultList();
    }

    @Override
    public List<PositionSalarySources> findInRange(int firstResult, int maxResults) {
        return em.createQuery("SELECT a from PositionSalarySources a").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Override
    public long count() {
         return (Long) em.createQuery("SELECT count(a) FROM PositionSalarySources a").getSingleResult();
    }

    @Override
    public PositionSalarySources getByPropertyName(String propertyName, String propertyValue) {
        List<PositionSalarySources> list = em.createQuery("SELECT e FROM  PositionSalarySources e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return (list.isEmpty()) ? null : list.get(0);
    }

    @Override
    public List<PositionSalarySources> getEntitiesByProperName(String propertyName, String propertyValue) {
          List<PositionSalarySources> list = em.createQuery("SELECT e FROM  PositionSalarySources e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return list;
    }

}
