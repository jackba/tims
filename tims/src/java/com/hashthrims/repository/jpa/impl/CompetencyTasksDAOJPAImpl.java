/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.repository.jpa.impl;


import com.hashthrims.domain.nimart.CompetencyTasks;
import com.hashthrims.repository.jpa.CompetencyTasksDAO;
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
@Repository("competencyTasksDAO")
@Transactional
public class CompetencyTasksDAOJPAImpl  implements CompetencyTasksDAO {

   @PersistenceContext(type=PersistenceContextType.EXTENDED)
    private EntityManager em;

    @Override
    public CompetencyTasks find(Long id) {
        return em.find(CompetencyTasks.class, id);
    }

    @Override
    public void persist(CompetencyTasks entity) {
        em.persist(entity);
    }

    @Override
    public void merge(CompetencyTasks entity) {
        em.merge(entity);
        
    }

    @Override
    public void remove(CompetencyTasks entity) {
        CompetencyTasks acc =em.find(CompetencyTasks.class, entity.getId());
        em.remove(acc);
    }

    @Override
    public List<CompetencyTasks> findAll() {
        return (List<CompetencyTasks>) em.createQuery("SELECT a FROM CompetencyTasks a").getResultList();
    }

    @Override
    public List<CompetencyTasks> findInRange(int firstResult, int maxResults) {
        return em.createQuery("SELECT a from CompetencyTasks a").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Override
    public long count() {
         return (Long) em.createQuery("SELECT count(a) FROM CompetencyTasks a").getSingleResult();
    }

    @Override
    public CompetencyTasks getByPropertyName(String propertyName, String propertyValue) {
        List<CompetencyTasks> list = em.createQuery("SELECT e FROM  CompetencyTasks e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return (list.isEmpty()) ? null : list.get(0);
    }

    @Override
    public List<CompetencyTasks> getEntitiesByProperName(String propertyName, String propertyValue) {
          List<CompetencyTasks> list = em.createQuery("SELECT e FROM  CompetencyTasks e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return list;
    }
}
