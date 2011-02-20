package com.hashthrims.repository.jpa.impl;


import com.hashthrims.domain.Education;
import com.hashthrims.repository.jpa.EducationDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author boniface
 */
@Repository("educationDAO")
@Transactional
public class EducationDAOJPAImpl  implements EducationDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Education find(Long id) {
        return em.find(Education.class, id);
    }

    @Override
    public void persist(Education entity) {
        em.persist(entity);
    }

    @Override
    public void merge(Education entity) {
        em.merge(entity);
    }

    @Override
    public void remove(Education entity) {
        Education acc =em.find(Education.class, entity.getId());
        em.remove(acc);
    }

    @Override
    public List<Education> findAll() {
        return (List<Education>) em.createQuery("SELECT a FROM Education a").getResultList();
    }

    @Override
    public List<Education> findInRange(int firstResult, int maxResults) {
        return em.createQuery("SELECT a from Education a").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Override
    public long count() {
         return (Long) em.createQuery("SELECT count(a) FROM Education a").getSingleResult();
    }

    @Override
    public Education getByPropertyName(String propertyName, String propertyValue) {
        List<Education> list = em.createQuery("SELECT e FROM  Education e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return (list.isEmpty()) ? null : list.get(0);
    }

    @Override
    public List<Education> getEntitiesByProperName(String propertyName, String propertyValue) {
          List<Education> list = em.createQuery("SELECT e FROM  Education e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return list;
    }
}