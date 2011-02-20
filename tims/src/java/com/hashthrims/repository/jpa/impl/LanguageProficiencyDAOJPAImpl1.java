/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.repository.jpa.impl;



import com.hashthrims.domain.employeelist.LanguageProficiency;
import com.hashthrims.repository.jpa.LanguageProficiencyDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author boniface
 */
@Repository("languageProficiencyDAO")
@Transactional
public class LanguageProficiencyDAOJPAImpl1  implements LanguageProficiencyDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public LanguageProficiency find(Long id) {
        return em.find(LanguageProficiency.class, id);
    }

    @Override
    public void persist(LanguageProficiency entity) {
        em.persist(entity);
    }

    @Override
    public void merge(LanguageProficiency entity) {
        em.merge(entity);
    }

    @Override
    public void remove(LanguageProficiency entity) {
        LanguageProficiency acc =em.find(LanguageProficiency.class, entity.getId());
        em.remove(acc);
    }

    @Override
    public List<LanguageProficiency> findAll() {
        return (List<LanguageProficiency>) em.createQuery("SELECT a FROM LanguageProficiency a").getResultList();
    }

    @Override
    public List<LanguageProficiency> findInRange(int firstResult, int maxResults) {
        return em.createQuery("SELECT a from LanguageProficiency a").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Override
    public long count() {
         return (Long) em.createQuery("SELECT count(a) FROM LanguageProficiency a").getSingleResult();
    }

    @Override
    public LanguageProficiency getByPropertyName(String propertyName, String propertyValue) {
        List<LanguageProficiency> list = em.createQuery("SELECT e FROM  LanguageProficiency e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return (list.isEmpty()) ? null : list.get(0);
    }

    @Override
    public List<LanguageProficiency> getEntitiesByProperName(String propertyName, String propertyValue) {
          List<LanguageProficiency> list = em.createQuery("SELECT e FROM  LanguageProficiency e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return list;
    }
}
