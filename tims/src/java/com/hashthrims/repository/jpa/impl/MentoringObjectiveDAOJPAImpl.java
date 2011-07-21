/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.repository.jpa.impl;

import com.hashthrims.domain.traininglist.MentoringObjective;
import com.hashthrims.repository.jpa.MentoringObjectiveDAO;
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

@Repository("mentoringObjectiveDAO")
@Transactional
public class MentoringObjectiveDAOJPAImpl  implements MentoringObjectiveDAO{

    @PersistenceContext(type=PersistenceContextType.EXTENDED)
    private EntityManager em;

    @Override
    public MentoringObjective find(Long id) {
        return em.find(MentoringObjective.class, id);
    }

    @Override
    public void persist(MentoringObjective entity) {
        em.persist(entity);
    }

    @Override
    public void merge(MentoringObjective entity) {
        em.merge(entity);
    }

    @Override
    public void remove(MentoringObjective entity) {
        MentoringObjective acc =em.find(MentoringObjective.class, entity.getId());
        em.remove(acc);
    }

    @Override
    public List<MentoringObjective> findAll() {
        return (List<MentoringObjective>) em.createQuery("SELECT a FROM MentoringObjective a").getResultList();
    }

    @Override
    public List<MentoringObjective> findInRange(int firstResult, int maxResults) {
        return em.createQuery("SELECT a from MentoringObjective a").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Override
    public long count() {
         return (Long) em.createQuery("SELECT count(a) FROM MentoringObjective a").getSingleResult();
    }

    @Override
    public MentoringObjective getByPropertyName(String propertyName, String propertyValue) {
        List<MentoringObjective> list = em.createQuery("SELECT e FROM  MentoringObjective e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return (list.isEmpty()) ? null : list.get(0);
    }

    @Override
    public List<MentoringObjective> getEntitiesByProperName(String propertyName, String propertyValue) {
          List<MentoringObjective> list = em.createQuery("SELECT e FROM  MentoringObjective e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return list;
    }

}
