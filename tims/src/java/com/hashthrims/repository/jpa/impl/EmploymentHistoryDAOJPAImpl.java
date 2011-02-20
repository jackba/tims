/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.repository.jpa.impl;

import com.hashthrims.repository.jpa.EmploymentHistoryDAO;
import com.hashthrims.domain.EmploymentHistory;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author abismail
 */
@Repository("employmenthistoryDAO")
@Transactional
public class EmploymentHistoryDAOJPAImpl  implements EmploymentHistoryDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public EmploymentHistory find(Long id) {
        return em.find(EmploymentHistory.class, id);
    }

    @Override
    public void persist(EmploymentHistory entity) {
        em.persist(entity);
    }

    @Override
    public void merge(EmploymentHistory entity) {
        em.merge(entity);
    }

    @Override
    public void remove(EmploymentHistory entity) {
        EmploymentHistory acc =em.find(EmploymentHistory.class, entity.getId());
        em.remove(acc);
    }

    @Override
    public List<EmploymentHistory> findAll() {
        return (List<EmploymentHistory>) em.createQuery("SELECT a FROM EmploymentHistory a").getResultList();
    }

    @Override
    public List<EmploymentHistory> findInRange(int firstResult, int maxResults) {
        return em.createQuery("SELECT a from EmploymentHistory a").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Override
    public long count() {
         return (Long) em.createQuery("SELECT count(a) FROM EmploymentHistory a").getSingleResult();
    }

    @Override
    public EmploymentHistory getByPropertyName(String propertyName, String propertyValue) {
        List<EmploymentHistory> list = em.createQuery("SELECT e FROM  EmploymentHistory e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return (list.isEmpty()) ? null : list.get(0);
    }

    @Override
    public List<EmploymentHistory> getEntitiesByProperName(String propertyName, String propertyValue) {
          List<EmploymentHistory> list = em.createQuery("SELECT e FROM  EmploymentHistory e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return list;
    }
}
