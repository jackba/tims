/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.repository.jpa.impl;


import com.hashthrims.domain.regionlist.Country;
import com.hashthrims.repository.jpa.CountryDAO;
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
@Repository("countryDAO")
@Transactional
public class CountryDAOJPAImpl  implements CountryDAO {

   @PersistenceContext(type=PersistenceContextType.EXTENDED)
    private EntityManager em;

    @Override
    public Country find(Long id) {
        return em.find(Country.class, id);
    }

    @Override
    public void persist(Country entity) {
        em.persist(entity);
    }

    @Override
    public void merge(Country entity) {
        em.merge(entity);
       
    }

    @Override
    public void remove(Country entity) {
        Country acc =em.find(Country.class, entity.getId());
        em.remove(acc);
    }

    @Override
    public List<Country> findAll() {
        return (List<Country>) em.createQuery("SELECT a FROM Country a").getResultList();
    }

    @Override
    public List<Country> findInRange(int firstResult, int maxResults) {
        return em.createQuery("SELECT a from Country a").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Override
    public long count() {
         return (Long) em.createQuery("SELECT count(a) FROM Country a").getSingleResult();
    }

    @Override
    public Country getByPropertyName(String propertyName, String propertyValue) {
        List<Country> list = em.createQuery("SELECT e FROM  Country e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return (list.isEmpty()) ? null : list.get(0);
    }

    @Override
    public List<Country> getEntitiesByProperName(String propertyName, String propertyValue) {
          List<Country> list = em.createQuery("SELECT e FROM  Country e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return list;
    }
}
