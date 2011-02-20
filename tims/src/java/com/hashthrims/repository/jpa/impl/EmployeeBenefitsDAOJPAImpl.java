package com.hashthrims.repository.jpa.impl;


import com.hashthrims.domain.EmployeeBenefits;
import com.hashthrims.repository.jpa.EmployeeBenefitsDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author boniface
 */
@Repository("employeebenefitsDAO")
@Transactional
public class EmployeeBenefitsDAOJPAImpl  implements EmployeeBenefitsDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public EmployeeBenefits find(Long id) {
        return em.find(EmployeeBenefits.class, id);
    }

    @Override
    public void persist(EmployeeBenefits entity) {
        em.persist(entity);
    }

    @Override
    public void merge(EmployeeBenefits entity) {
        em.merge(entity);
    }

    @Override
    public void remove(EmployeeBenefits entity) {
        EmployeeBenefits acc =em.find(EmployeeBenefits.class, entity.getId());
        em.remove(acc);
    }

    @Override
    public List<EmployeeBenefits> findAll() {
        return (List<EmployeeBenefits>) em.createQuery("SELECT a FROM EmployeeBenefits a").getResultList();
    }

    @Override
    public List<EmployeeBenefits> findInRange(int firstResult, int maxResults) {
        return em.createQuery("SELECT a from EmployeeBenefits a").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Override
    public long count() {
         return (Long) em.createQuery("SELECT count(a) FROM EmployeeBenefits a").getSingleResult();
    }

    @Override
    public EmployeeBenefits getByPropertyName(String propertyName, String propertyValue) {
        List<EmployeeBenefits> list = em.createQuery("SELECT e FROM  EmployeeBenefits e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return (list.isEmpty()) ? null : list.get(0);
    }

    @Override
    public List<EmployeeBenefits> getEntitiesByProperName(String propertyName, String propertyValue) {
          List<EmployeeBenefits> list = em.createQuery("SELECT e FROM  EmployeeBenefits e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return list;
    }
}