/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.repository.jpa.impl;


import com.hashthrims.domain.regionlist.District;
import com.hashthrims.repository.jpa.DistrictDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author boniface
 */
@Repository("districtDAO")
@Transactional
public class DistrictDAOJPAImpl  implements DistrictDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public District find(Long id) {
        return em.find(District.class, id);
    }

    @Override
    public void persist(District entity) {
        em.persist(entity);
    }

    @Override
    public void merge(District entity) {
        em.merge(entity);
    }

    @Override
    public void remove(District entity) {
        District acc =em.find(District.class, entity.getId());
        em.remove(acc);
    }

    @Override
    public List<District> findAll() {
        return (List<District>) em.createQuery("SELECT a FROM District a").getResultList();
    }

    @Override
    public List<District> findInRange(int firstResult, int maxResults) {
        return em.createQuery("SELECT a from District a").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Override
    public long count() {
         return (Long) em.createQuery("SELECT count(a) FROM District a").getSingleResult();
    }

    @Override
    public District getByPropertyName(String propertyName, String propertyValue) {
        List<District> list = em.createQuery("SELECT e FROM  District e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return (list.isEmpty()) ? null : list.get(0);
    }

    @Override
    public List<District> getEntitiesByProperName(String propertyName, String propertyValue) {
          List<District> list = em.createQuery("SELECT e FROM  District e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return list;
    }
}
