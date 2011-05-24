/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.repository.jpa.impl;


import com.hashthrims.domain.positions.GlobalPositions;
import com.hashthrims.repository.jpa.GlobalPositionsDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author boniface
 */
@Repository("globalPositionsDAO")
@Transactional
public class GlobalPositionsDAOJPAImpl  implements GlobalPositionsDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public GlobalPositions find(Long id) {
        return em.find(GlobalPositions.class, id);
    }

    @Override
    public void persist(GlobalPositions entity) {
        em.persist(entity);
    }

    @Override
    public void merge(GlobalPositions entity) {
        em.merge(entity);
    }

    @Override
    public void remove(GlobalPositions entity) {
        GlobalPositions acc =em.find(GlobalPositions.class, entity.getId());
        em.remove(acc);
    }

    @Override
    public List<GlobalPositions> findAll() {
        return (List<GlobalPositions>) em.createQuery("SELECT a FROM GlobalPositions a").getResultList();
    }

    @Override
    public List<GlobalPositions> findInRange(int firstResult, int maxResults) {
        return em.createQuery("SELECT a from GlobalPositions a").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Override
    public long count() {
         return (Long) em.createQuery("SELECT count(a) FROM GlobalPositions a").getSingleResult();
    }

    @Override
    public GlobalPositions getByPropertyName(String propertyName, String propertyValue) {
        List<GlobalPositions> list = em.createQuery("SELECT e FROM  GlobalPositions e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return (list.isEmpty()) ? null : list.get(0);
    }

    @Override
    public List<GlobalPositions> getEntitiesByProperName(String propertyName, String propertyValue) {
          List<GlobalPositions> list = em.createQuery("SELECT e FROM  GlobalPositions e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return list;
    }
}
