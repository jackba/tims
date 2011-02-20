/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.repository.jpa.impl;

import com.hashthrims.domain.traininglist.MentoringField;
import com.hashthrims.repository.jpa.MentoringFieldDAO;
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

@Repository("mentoringFieldDAO")
@Transactional
public class MentoringFieldDAOJPAImpl  implements MentoringFieldDAO{

    @PersistenceContext(type=PersistenceContextType.EXTENDED)
    private EntityManager em;

    @Override
    public MentoringField find(Long id) {
        return em.find(MentoringField.class, id);
    }

    @Override
    public void persist(MentoringField entity) {
        em.persist(entity);
    }

    @Override
    public void merge(MentoringField entity) {
        em.merge(entity);
    }

    @Override
    public void remove(MentoringField entity) {
        MentoringField acc =em.find(MentoringField.class, entity.getId());
        em.remove(acc);
    }

    @Override
    public List<MentoringField> findAll() {
        return (List<MentoringField>) em.createQuery("SELECT a FROM MentoringField a").getResultList();
    }

    @Override
    public List<MentoringField> findInRange(int firstResult, int maxResults) {
        return em.createQuery("SELECT a from MentoringField a").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Override
    public long count() {
         return (Long) em.createQuery("SELECT count(a) FROM MentoringField a").getSingleResult();
    }

    @Override
    public MentoringField getByPropertyName(String propertyName, String propertyValue) {
        List<MentoringField> list = em.createQuery("SELECT e FROM  MentoringField e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return (list.isEmpty()) ? null : list.get(0);
    }

    @Override
    public List<MentoringField> getEntitiesByProperName(String propertyName, String propertyValue) {
          List<MentoringField> list = em.createQuery("SELECT e FROM  MentoringField e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return list;
    }

}
