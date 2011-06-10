/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.repository.jpa.impl;

import com.hashthrims.domain.offices.Clusters;
import com.hashthrims.repository.jpa.ClustersDAO;
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

@Repository("clustersDAO")
@Transactional
public class ClustersDAOJPAImpl  implements ClustersDAO{

    @PersistenceContext(type=PersistenceContextType.EXTENDED)
    private EntityManager em;

    @Override
    public Clusters find(Long id) {
        return em.find(Clusters.class, id);
    }

    @Override
    public void persist(Clusters entity) {
        em.persist(entity);
    }

    @Override
    public void merge(Clusters entity) {
        em.merge(entity);
    }

    @Override
    public void remove(Clusters entity) {
        Clusters acc =em.find(Clusters.class, entity.getId());
        em.remove(acc);
    }

    @Override
    public List<Clusters> findAll() {
        return (List<Clusters>) em.createQuery("SELECT a FROM Clusters a").getResultList();
    }

    @Override
    public List<Clusters> findInRange(int firstResult, int maxResults) {
        return em.createQuery("SELECT a from Clusters a").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Override
    public long count() {
         return (Long) em.createQuery("SELECT count(a) FROM Clusters a").getSingleResult();
    }

    @Override
    public Clusters getByPropertyName(String propertyName, String propertyValue) {
        List<Clusters> list = em.createQuery("SELECT e FROM  Clusters e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return (list.isEmpty()) ? null : list.get(0);
    }

    @Override
    public List<Clusters> getEntitiesByProperName(String propertyName, String propertyValue) {
          List<Clusters> list = em.createQuery("SELECT e FROM  Clusters e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return list;
    }

}
