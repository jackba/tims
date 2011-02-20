/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.repository.jpa.impl;

import com.hashthrims.domain.regionlist.City;
import com.hashthrims.repository.jpa.CityDAO;
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

@Repository("cityDAO")
@Transactional
public class CityDAOJPAImpl  implements CityDAO{

    @PersistenceContext(type=PersistenceContextType.EXTENDED)
    private EntityManager em;

    @Override
    public City find(Long id) {
        return em.find(City.class, id);
    }

    @Override
    public void persist(City entity) {
        em.persist(entity);
    }

    @Override
    public void merge(City entity) {
        em.merge(entity);
    }

    @Override
    public void remove(City entity) {
        City acc =em.find(City.class, entity.getId());
        em.remove(acc);
    }

    @Override
    public List<City> findAll() {
        return (List<City>) em.createQuery("SELECT a FROM City a").getResultList();
    }

    @Override
    public List<City> findInRange(int firstResult, int maxResults) {
        return em.createQuery("SELECT a from City a").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Override
    public long count() {
         return (Long) em.createQuery("SELECT count(a) FROM City a").getSingleResult();
    }

    @Override
    public City getByPropertyName(String propertyName, String propertyValue) {
        List<City> list = em.createQuery("SELECT e FROM  City e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return (list.isEmpty()) ? null : list.get(0);
    }

    @Override
    public List<City> getEntitiesByProperName(String propertyName, String propertyValue) {
          List<City> list = em.createQuery("SELECT e FROM  City e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return list;
    }

}
