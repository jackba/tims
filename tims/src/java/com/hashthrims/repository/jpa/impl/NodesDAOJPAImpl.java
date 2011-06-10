/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.repository.jpa.impl;

import com.hashthrims.domain.offices.Nodes;
import com.hashthrims.repository.jpa.NodesDAO;
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

@Repository("nodesDAO")
@Transactional
public class NodesDAOJPAImpl  implements NodesDAO{

    @PersistenceContext(type=PersistenceContextType.EXTENDED)
    private EntityManager em;

    @Override
    public Nodes find(Long id) {
        return em.find(Nodes.class, id);
    }

    @Override
    public void persist(Nodes entity) {
        em.persist(entity);
    }

    @Override
    public void merge(Nodes entity) {
        em.merge(entity);
    }

    @Override
    public void remove(Nodes entity) {
        Nodes acc =em.find(Nodes.class, entity.getId());
        em.remove(acc);
    }

    @Override
    public List<Nodes> findAll() {
        return (List<Nodes>) em.createQuery("SELECT a FROM Nodes a").getResultList();
    }

    @Override
    public List<Nodes> findInRange(int firstResult, int maxResults) {
        return em.createQuery("SELECT a from Nodes a").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Override
    public long count() {
         return (Long) em.createQuery("SELECT count(a) FROM Nodes a").getSingleResult();
    }

    @Override
    public Nodes getByPropertyName(String propertyName, String propertyValue) {
        List<Nodes> list = em.createQuery("SELECT e FROM  Nodes e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return (list.isEmpty()) ? null : list.get(0);
    }

    @Override
    public List<Nodes> getEntitiesByProperName(String propertyName, String propertyValue) {
          List<Nodes> list = em.createQuery("SELECT e FROM  Nodes e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return list;
    }

}
