package com.hashthrims.repository.jpa.impl;


import com.hashthrims.domain.WorkPlaceAccidents;
import com.hashthrims.repository.jpa.WorkPlaceAccidentsDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author boniface
 */
@Repository("workplaceaccidentsDAO")
@Transactional
public class WorkPlaceAccidentsDAOJPAImpl  implements WorkPlaceAccidentsDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public WorkPlaceAccidents find(Long id) {
        return em.find(WorkPlaceAccidents.class, id);
    }

    @Override
    public void persist(WorkPlaceAccidents entity) {
        em.persist(entity);
    }

    @Override
    public void merge(WorkPlaceAccidents entity) {
        em.merge(entity);
    }

    @Override
    public void remove(WorkPlaceAccidents entity) {
        WorkPlaceAccidents acc =em.find(WorkPlaceAccidents.class, entity.getId());
        em.remove(acc);
    }

    @Override
    public List<WorkPlaceAccidents> findAll() {
        return (List<WorkPlaceAccidents>) em.createQuery("SELECT a FROM WorkPlaceAccidents a").getResultList();
    }

    @Override
    public List<WorkPlaceAccidents> findInRange(int firstResult, int maxResults) {
        return em.createQuery("SELECT a from WorkPlaceAccidents a").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Override
    public long count() {
         return (Long) em.createQuery("SELECT count(a) FROM WorkPlaceAccidents a").getSingleResult();
    }

    @Override
    public WorkPlaceAccidents getByPropertyName(String propertyName, String propertyValue) {
        List<WorkPlaceAccidents> list = em.createQuery("SELECT e FROM  WorkPlaceAccidents e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return (list.isEmpty()) ? null : list.get(0);
    }

    @Override
    public List<WorkPlaceAccidents> getEntitiesByProperName(String propertyName, String propertyValue) {
          List<WorkPlaceAccidents> list = em.createQuery("SELECT e FROM  WorkPlaceAccidents e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return list;
    }
}