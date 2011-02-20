/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.repository.jpa.impl;


import com.hashthrims.domain.positions.Positions;
import com.hashthrims.repository.jpa.PositionsDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author boniface
 */
@Repository("positionsDAO")
@Transactional
public class PositionsDAOJPAImpl  implements PositionsDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Positions find(Long id) {
        return em.find(Positions.class, id);
    }

    @Override
    public void persist(Positions entity) {
        em.persist(entity);
    }

    @Override
    public void merge(Positions entity) {
        em.merge(entity);
    }

    @Override
    public void remove(Positions entity) {
        Positions acc =em.find(Positions.class, entity.getId());
        em.remove(acc);
    }

    @Override
    public List<Positions> findAll() {
        return (List<Positions>) em.createQuery("SELECT a FROM Positions a").getResultList();
    }

    @Override
    public List<Positions> findInRange(int firstResult, int maxResults) {
        return em.createQuery("SELECT a from Positions a").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Override
    public long count() {
         return (Long) em.createQuery("SELECT count(a) FROM Positions a").getSingleResult();
    }

    @Override
    public Positions getByPropertyName(String propertyName, String propertyValue) {
        List<Positions> list = em.createQuery("SELECT e FROM  Positions e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return (list.isEmpty()) ? null : list.get(0);
    }

    @Override
    public List<Positions> getEntitiesByProperName(String propertyName, String propertyValue) {
          List<Positions> list = em.createQuery("SELECT e FROM  Positions e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return list;
    }
}
