/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.repository.jpa.impl;



import com.hashthrims.domain.Identities;
import com.hashthrims.repository.jpa.*;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author abismail
 */
@Repository("identitiesDAO")
@Transactional
public class IdentitesDAOJPAImpl implements IdentitiesDAO{

    @PersistenceContext
    private EntityManager em;

    @Override
    public Identities find(Long id) {
        return em.find(Identities.class, id);
    }

    @Override
    public void persist(Identities entity) {
        em.persist(entity);
    }

    @Override
    public void merge(Identities entity) {
        em.merge(entity);
        
    }

    @Override
    public void remove(Identities entity) {
        Identities id =em.find(Identities.class, entity.getId());
        em.remove(id);
    }

    @Override
    public List<Identities> findAll() {
        return (List<Identities>) em.createQuery("SELECT a FROM Identities a").getResultList();
    }

    @Override
    public List<Identities> findInRange(int firstResult, int maxResults) {
        return em.createQuery("SELECT a from Identities a").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Override
    public long count() {
         return (Long) em.createQuery("SELECT count(a) FROM Identities a").getSingleResult();
    }

    @Override
    public Identities getByPropertyName(String propertyName, String propertyValue) {
        List<Identities> list = em.createQuery("SELECT e FROM  Identities e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return (list.isEmpty()) ? null : list.get(0);
    }

    @Override
    public List<Identities> getEntitiesByProperName(String propertyName, String propertyValue) {
        List<Identities> list = em.createQuery("SELECT e FROM  Identities e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return list;
    }

}
