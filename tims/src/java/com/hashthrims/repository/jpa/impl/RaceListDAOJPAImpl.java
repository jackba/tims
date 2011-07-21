/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.repository.jpa.impl;

import com.hashthrims.domain.employeelist.RaceList;
import com.hashthrims.repository.jpa.RaceListDAO;
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

@Repository("raceListDAO")
@Transactional
public class RaceListDAOJPAImpl  implements RaceListDAO{

    @PersistenceContext(type=PersistenceContextType.EXTENDED)
    private EntityManager em;

    @Override
    public RaceList find(Long id) {
        return em.find(RaceList.class, id);
    }

    @Override
    public void persist(RaceList entity) {
        em.persist(entity);
    }

    @Override
    public void merge(RaceList entity) {
        em.merge(entity);
    }

    @Override
    public void remove(RaceList entity) {
        RaceList acc =em.find(RaceList.class, entity.getId());
        em.remove(acc);
    }

    @Override
    public List<RaceList> findAll() {
        return (List<RaceList>) em.createQuery("SELECT a FROM RaceList a").getResultList();
    }

    @Override
    public List<RaceList> findInRange(int firstResult, int maxResults) {
        return em.createQuery("SELECT a from RaceList a").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Override
    public long count() {
         return (Long) em.createQuery("SELECT count(a) FROM RaceList a").getSingleResult();
    }

    @Override
    public RaceList getByPropertyName(String propertyName, String propertyValue) {
        List<RaceList> list = em.createQuery("SELECT e FROM  RaceList e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return (list.isEmpty()) ? null : list.get(0);
    }

    @Override
    public List<RaceList> getEntitiesByProperName(String propertyName, String propertyValue) {
          List<RaceList> list = em.createQuery("SELECT e FROM  RaceList e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return list;
    }

}
