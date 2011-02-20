/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.repository.jpa.impl;

import com.hashthrims.domain.employeelist.DisciplineActionTypeList;
import com.hashthrims.repository.jpa.DisciplineActionTypeListDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author stud
 */
@Repository("disciplineActionTypeListDAO")
@Transactional
public class DisciplineActionTypeListDAOJPAImpl1 implements DisciplineActionTypeListDAO{

    @PersistenceContext
    private EntityManager em;

    @Override
    public DisciplineActionTypeList find(Long id) {
        return em.find(DisciplineActionTypeList.class, id);
    }

    @Override
    public void persist(DisciplineActionTypeList entity) {
        em.persist(entity);
    }

    @Override
    public void merge(DisciplineActionTypeList entity) {
        em.merge(entity);
    }

    @Override
    public void remove(DisciplineActionTypeList entity) {
        DisciplineActionTypeList acc =em.find(DisciplineActionTypeList.class, entity.getId());
        em.remove(acc);
    }

    @Override
    public List<DisciplineActionTypeList> findAll() {
        return (List<DisciplineActionTypeList>) em.createQuery("SELECT a FROM DisciplineActionTypeList a").getResultList();
    }

    @Override
    public List<DisciplineActionTypeList> findInRange(int firstResult, int maxResults) {
        return em.createQuery("SELECT a from DisciplineActionTypeList a").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Override
    public long count() {
         return (Long) em.createQuery("SELECT count(a) FROM DisciplineActionTypeList a").getSingleResult();
    }

    @Override
    public DisciplineActionTypeList getByPropertyName(String propertyName, String propertyValue) {
        List<DisciplineActionTypeList> list = em.createQuery("SELECT e FROM  DisciplineActionTypeList e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return (list.isEmpty()) ? null : list.get(0);
    }

    @Override
    public List<DisciplineActionTypeList> getEntitiesByProperName(String propertyName, String propertyValue) {
          List<DisciplineActionTypeList> list = em.createQuery("SELECT e FROM  DisciplineActionTypeList e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return list;
    }

}
