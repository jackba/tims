/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.repository.jpa.impl;



import com.hashthrims.domain.employeelist.MaritalStatusList;
import com.hashthrims.repository.jpa.MaritalStatusListDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author boniface
 */
@Repository("maritalStatusListDAO")
@Transactional
public class MaritalStatusListDAOImpl  implements MaritalStatusListDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public MaritalStatusList find(Long id) {
        return em.find(MaritalStatusList.class, id);
    }

    @Override
    public void persist(MaritalStatusList entity) {
        em.persist(entity);
    }

    @Override
    public void merge(MaritalStatusList entity) {
        em.merge(entity);
    }

    @Override
    public void remove(MaritalStatusList entity) {
        MaritalStatusList acc =em.find(MaritalStatusList.class, entity.getId());
        em.remove(acc);
    }

    @Override
    public List<MaritalStatusList> findAll() {
        return (List<MaritalStatusList>) em.createQuery("SELECT a FROM MaritalStatusList a").getResultList();
    }

    @Override
    public List<MaritalStatusList> findInRange(int firstResult, int maxResults) {
        return em.createQuery("SELECT a from MaritalStatusList a").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Override
    public long count() {
         return (Long) em.createQuery("SELECT count(a) FROM MaritalStatusList a").getSingleResult();
    }

    @Override
    public MaritalStatusList getByPropertyName(String propertyName, String propertyValue) {
        List<MaritalStatusList> list = em.createQuery("SELECT e FROM  MaritalStatusList e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return (list.isEmpty()) ? null : list.get(0);
    }

    @Override
    public List<MaritalStatusList> getEntitiesByProperName(String propertyName, String propertyValue) {
          List<MaritalStatusList> list = em.createQuery("SELECT e FROM  MaritalStatusList e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return list;
    }
}
