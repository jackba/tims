/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.repository.jpa.impl;


import com.hashthrims.domain.regionlist.Province;
import com.hashthrims.repository.jpa.RegionDAO;
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
@Repository("regionDAO")
@Transactional
public class RegionDAOJPAImpl  implements RegionDAO {

    @PersistenceContext(type=PersistenceContextType.EXTENDED)
    private EntityManager em;

    @Override
    public Province find(Long id) {
        return em.find(Province.class, id);
    }

    @Override
    public void persist(Province entity) {
        em.persist(entity);
    }

    @Override
    public void merge(Province entity) {
        em.merge(entity);
    }

    @Override
    public void remove(Province entity) {
        Province acc =em.find(Province.class, entity.getId());
        em.remove(acc);
    }

    @Override
    public List<Province> findAll() {
        return (List<Province>) em.createQuery("SELECT a FROM Province a").getResultList();
    }

    @Override
    public List<Province> findInRange(int firstResult, int maxResults) {
        return em.createQuery("SELECT a from Province a").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Override
    public long count() {
         return (Long) em.createQuery("SELECT count(a) FROM Province a").getSingleResult();
    }

    @Override
    public Province getByPropertyName(String propertyName, String propertyValue) {
        List<Province> list = em.createQuery("SELECT e FROM  Province e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return (list.isEmpty()) ? null : list.get(0);
    }

    @Override
    public List<Province> getEntitiesByProperName(String propertyName, String propertyValue) {
          List<Province> list = em.createQuery("SELECT e FROM  Province e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return list;
    }
}
