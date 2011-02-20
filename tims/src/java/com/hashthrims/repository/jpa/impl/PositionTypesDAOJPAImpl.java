/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.repository.jpa.impl;



import com.hashthrims.domain.positions.PositionTypes;
import com.hashthrims.repository.jpa.PositionTypesDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author boniface
 */
@Repository("positionTypesDAO")
@Transactional
public class PositionTypesDAOJPAImpl  implements PositionTypesDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public PositionTypes find(Long id) {
        return em.find(PositionTypes.class, id);
    }

    @Override
    public void persist(PositionTypes entity) {
        em.persist(entity);
    }

    @Override
    public void merge(PositionTypes entity) {
        em.merge(entity);
    }

    @Override
    public void remove(PositionTypes entity) {
        PositionTypes acc =em.find(PositionTypes.class, entity.getId());
        em.remove(acc);
    }

    @Override
    public List<PositionTypes> findAll() {
        return (List<PositionTypes>) em.createQuery("SELECT a FROM PositionTypes a").getResultList();
    }

    @Override
    public List<PositionTypes> findInRange(int firstResult, int maxResults) {
        return em.createQuery("SELECT a from PositionTypes a").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Override
    public long count() {
         return (Long) em.createQuery("SELECT count(a) FROM PositionTypes a").getSingleResult();
    }

    @Override
    public PositionTypes getByPropertyName(String propertyName, String propertyValue) {
        List<PositionTypes> list = em.createQuery("SELECT e FROM  PositionTypes e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return (list.isEmpty()) ? null : list.get(0);
    }

    @Override
    public List<PositionTypes> getEntitiesByProperName(String propertyName, String propertyValue) {
          List<PositionTypes> list = em.createQuery("SELECT e FROM  PositionTypes e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return list;
    }
}
