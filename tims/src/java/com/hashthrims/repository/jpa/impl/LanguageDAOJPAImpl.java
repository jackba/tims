/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.repository.jpa.impl;


import com.hashthrims.domain.employeelist.Language;
import com.hashthrims.repository.jpa.LanguageDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author boniface
 */
@Repository("languageDAO")
@Transactional
public class LanguageDAOJPAImpl  implements LanguageDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Language find(Long id) {
        return em.find(Language.class, id);
    }

    @Override
    public void persist(Language entity) {
        em.persist(entity);
    }

    @Override
    public void merge(Language entity) {
        em.merge(entity);
    }

    @Override
    public void remove(Language entity) {
        Language acc =em.find(Language.class, entity.getId());
        em.remove(acc);
    }

    @Override
    public List<Language> findAll() {
        return (List<Language>) em.createQuery("SELECT a FROM Language a").getResultList();
    }

    @Override
    public List<Language> findInRange(int firstResult, int maxResults) {
        return em.createQuery("SELECT a from Language a").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Override
    public long count() {
         return (Long) em.createQuery("SELECT count(a) FROM Language a").getSingleResult();
    }

    @Override
    public Language getByPropertyName(String propertyName, String propertyValue) {
        List<Language> list = em.createQuery("SELECT e FROM  Language e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return (list.isEmpty()) ? null : list.get(0);
    }

    @Override
    public List<Language> getEntitiesByProperName(String propertyName, String propertyValue) {
          List<Language> list = em.createQuery("SELECT e FROM  Language e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return list;
    }
}
