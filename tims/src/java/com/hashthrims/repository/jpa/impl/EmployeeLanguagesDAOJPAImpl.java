package com.hashthrims.repository.jpa.impl;


import com.hashthrims.domain.EmployeeLanguages;
import com.hashthrims.repository.jpa.EmployeeLanguagesDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author boniface
 */
@Repository("employeeLanguagesDAO")
@Transactional
public class EmployeeLanguagesDAOJPAImpl  implements EmployeeLanguagesDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public EmployeeLanguages find(Long id) {
        return em.find(EmployeeLanguages.class, id);
    }

    @Override
    public void persist(EmployeeLanguages entity) {
        em.persist(entity);
    }

    @Override
    public void merge(EmployeeLanguages entity) {
        em.merge(entity);
    }

    @Override
    public void remove(EmployeeLanguages entity) {
        EmployeeLanguages acc =em.find(EmployeeLanguages.class, entity.getId());
        em.remove(acc);
    }

    @Override
    public List<EmployeeLanguages> findAll() {
        return (List<EmployeeLanguages>) em.createQuery("SELECT a FROM EmployeeLanguages a").getResultList();
    }

    @Override
    public List<EmployeeLanguages> findInRange(int firstResult, int maxResults) {
        return em.createQuery("SELECT a from EmployeeLanguages a").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Override
    public long count() {
         return (Long) em.createQuery("SELECT count(a) FROM EmployeeLanguages a").getSingleResult();
    }

    @Override
    public EmployeeLanguages getByPropertyName(String propertyName, String propertyValue) {
        List<EmployeeLanguages> list = em.createQuery("SELECT e FROM  EmployeeLanguages e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return (list.isEmpty()) ? null : list.get(0);
    }

    @Override
    public List<EmployeeLanguages> getEntitiesByProperName(String propertyName, String propertyValue) {
          List<EmployeeLanguages> list = em.createQuery("SELECT e FROM  EmployeeLanguages e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return list;
    }
}