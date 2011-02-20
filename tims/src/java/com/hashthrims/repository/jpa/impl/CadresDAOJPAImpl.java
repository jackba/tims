/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.repository.jpa.impl;

import com.hashthrims.domain.jobs.Cadres;
import com.hashthrims.repository.jpa.CadresDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author stud
 */
@Repository("cadresDAO")
@Transactional
public class CadresDAOJPAImpl implements CadresDAO{

    @PersistenceContext(type=PersistenceContextType.EXTENDED)
    private EntityManager em;

    @Override
    public Cadres find(Long id) {
        return em.find(Cadres.class, id);
    }

    @Override
    public void persist(Cadres entity) {
        em.persist(entity);
    }

    @Override
    public void merge(Cadres entity) {
        em.merge(entity);
    }

    @Override
    public void remove(Cadres entity) {
        Cadres acc =em.find(Cadres.class, entity.getId());
        em.remove(acc);
    }

    @Override
    public List<Cadres> findAll() {
        return (List<Cadres>) em.createQuery("SELECT a FROM Cadres a").getResultList();
    }

    @Override
    public List<Cadres> findInRange(int firstResult, int maxResults) {
        return em.createQuery("SELECT a from Cadres a").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Override
    public long count() {
         return (Long) em.createQuery("SELECT count(a) FROM Cadres a").getSingleResult();
    }

    @Override
    public Cadres getByPropertyName(String propertyName, String propertyValue) {
        List<Cadres> list = em.createQuery("SELECT e FROM  Cadres e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return (list.isEmpty()) ? null : list.get(0);
    }

    @Override
    public List<Cadres> getEntitiesByProperName(String propertyName, String propertyValue) {
          List<Cadres> list = em.createQuery("SELECT e FROM  Cadres e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return list;
    }

   

}
