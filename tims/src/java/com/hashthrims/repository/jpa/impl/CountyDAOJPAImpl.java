/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.repository.jpa.impl;


import com.hashthrims.domain.regionlist.County;
import com.hashthrims.repository.jpa.CountyDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author boniface
 */
@Repository("countyDAO")
@Transactional
public class CountyDAOJPAImpl  implements CountyDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public County find(Long id) {
        return em.find(County.class, id);
    }

    @Override
    public void persist(County entity) {
        em.persist(entity);
    }

    @Override
    public void merge(County entity) {
        em.merge(entity);
    }

    @Override
    public void remove(County entity) {
        County acc =em.find(County.class, entity.getId());
        em.remove(acc);
    }

    @Override
    public List<County> findAll() {
        return (List<County>) em.createQuery("SELECT a FROM County a").getResultList();
    }

    @Override
    public List<County> findInRange(int firstResult, int maxResults) {
        return em.createQuery("SELECT a from County a").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Override
    public long count() {
         return (Long) em.createQuery("SELECT count(a) FROM County a").getSingleResult();
    }

    @Override
    public County getByPropertyName(String propertyName, String propertyValue) {
        List<County> list = em.createQuery("SELECT e FROM  County e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return (list.isEmpty()) ? null : list.get(0);
    }

    @Override
    public List<County> getEntitiesByProperName(String propertyName, String propertyValue) {
          List<County> list = em.createQuery("SELECT e FROM  County e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return list;
    }
}
