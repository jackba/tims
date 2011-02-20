/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.repository.jpa.impl;


import com.hashthrims.domain.regionlist.Currency;
import com.hashthrims.repository.jpa.CurrencyDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author boniface
 */
@Repository("currencyDAO")
@Transactional
public class CurrencyDAOJPAImpl  implements CurrencyDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Currency find(Long id) {
        return em.find(Currency.class, id);
    }

    @Override
    public void persist(Currency entity) {
        em.persist(entity);
    }

    @Override
    public void merge(Currency entity) {
        em.merge(entity);
        em.flush();
    }

    @Override
    public void remove(Currency entity) {
        Currency acc =em.find(Currency.class, entity.getId());
        em.remove(acc);
    }

    @Override
    public List<Currency> findAll() {
        return (List<Currency>) em.createQuery("SELECT a FROM Currency a").getResultList();
    }

    @Override
    public List<Currency> findInRange(int firstResult, int maxResults) {
        return em.createQuery("SELECT a from Currency a").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Override
    public long count() {
         return (Long) em.createQuery("SELECT count(a) FROM Currency a").getSingleResult();
    }

    @Override
    public Currency getByPropertyName(String propertyName, String propertyValue) {
        List<Currency> list = em.createQuery("SELECT e FROM  Currency e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return (list.isEmpty()) ? null : list.get(0);
    }

    @Override
    public List<Currency> getEntitiesByProperName(String propertyName, String propertyValue) {
          List<Currency> list = em.createQuery("SELECT e FROM  Currency e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return list;
    }
}
