package com.hashthrims.repository.jpa.impl;


import com.hashthrims.domain.Applications;
import com.hashthrims.repository.jpa.ApplicationsDAO;
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
@Repository("applicationsDAO")
@Transactional
public class ApplicationsDAOJPAImpl  implements ApplicationsDAO {

    @PersistenceContext(type=PersistenceContextType.EXTENDED)
    private EntityManager em;

    @Override
    public Applications find(Long id) {
        return em.find(Applications.class, id);
    }

    @Override
    public void persist(Applications entity) {
        em.persist(entity);
    }

    @Override
    public void merge(Applications entity) {
        em.merge(entity);
    }

    @Override
    public void remove(Applications entity) {
        Applications acc =em.find(Applications.class, entity.getId());
        em.remove(acc);
    }

    @Override
    public List<Applications> findAll() {
        return (List<Applications>) em.createQuery("SELECT a FROM Applications a").getResultList();
    }

    @Override
    public List<Applications> findInRange(int firstResult, int maxResults) {
        return em.createQuery("SELECT a from Applications a").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Override
    public long count() {
         return (Long) em.createQuery("SELECT count(a) FROM Applications a").getSingleResult();
    }

    @Override
    public Applications getByPropertyName(String propertyName, String propertyValue) {
        List<Applications> list = em.createQuery("SELECT e FROM  Applications e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return (list.isEmpty()) ? null : list.get(0);
    }

    @Override
    public List<Applications> getEntitiesByProperName(String propertyName, String propertyValue) {
          List<Applications> list = em.createQuery("SELECT e FROM  Applications e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return list;
    }
}