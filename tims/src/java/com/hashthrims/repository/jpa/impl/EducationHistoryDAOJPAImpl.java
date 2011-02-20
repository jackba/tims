package com.hashthrims.repository.jpa.impl;


import com.hashthrims.domain.EducationHistory;
import com.hashthrims.repository.jpa.EducationHistoryDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author boniface
 */
@Repository("educationhistoryDAO")
@Transactional
public class EducationHistoryDAOJPAImpl  implements EducationHistoryDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public EducationHistory find(Long id) {
        return em.find(EducationHistory.class, id);
    }

    @Override
    public void persist(EducationHistory entity) {
        em.persist(entity);
    }

    @Override
    public void merge(EducationHistory entity) {
        em.merge(entity);
    }

    @Override
    public void remove(EducationHistory entity) {
        EducationHistory acc =em.find(EducationHistory.class, entity.getId());
        em.remove(acc);
    }

    @Override
    public List<EducationHistory> findAll() {
        return (List<EducationHistory>) em.createQuery("SELECT a FROM EducationHistory a").getResultList();
    }

    @Override
    public List<EducationHistory> findInRange(int firstResult, int maxResults) {
        return em.createQuery("SELECT a from EducationHistory a").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Override
    public long count() {
         return (Long) em.createQuery("SELECT count(a) FROM EducationHistory a").getSingleResult();
    }

    @Override
    public EducationHistory getByPropertyName(String propertyName, String propertyValue) {
        List<EducationHistory> list = em.createQuery("SELECT e FROM  EducationHistory e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return (list.isEmpty()) ? null : list.get(0);
    }

    @Override
    public List<EducationHistory> getEntitiesByProperName(String propertyName, String propertyValue) {
          List<EducationHistory> list = em.createQuery("SELECT e FROM  EducationHistory e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return list;
    }
}