package com.hashthrims.repository.jpa.impl;


import com.hashthrims.domain.EmployeeCourses;
import com.hashthrims.repository.jpa.CompetencyDAO;
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
@Repository("competencyDAO")
@Transactional
public class CompetencyDAOJPAImpl  implements CompetencyDAO {

    @PersistenceContext(type=PersistenceContextType.EXTENDED)
    private EntityManager em;

    @Override
    public EmployeeCourses find(Long id) {
        return em.find(EmployeeCourses.class, id);
    }

    @Override
    public void persist(EmployeeCourses entity) {
        em.persist(entity);
    }

    @Override
    public void merge(EmployeeCourses entity) {
        em.merge(entity);
    }

    @Override
    public void remove(EmployeeCourses entity) {
        EmployeeCourses acc =em.find(EmployeeCourses.class, entity.getId());
        em.remove(acc);
    }

    @Override
    public List<EmployeeCourses> findAll() {
        return (List<EmployeeCourses>) em.createQuery("SELECT a FROM EmployeeCourses a").getResultList();
    }

    @Override
    public List<EmployeeCourses> findInRange(int firstResult, int maxResults) {
        return em.createQuery("SELECT a from EmployeeCourses a").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Override
    public long count() {
         return (Long) em.createQuery("SELECT count(a) FROM EmployeeCourses a").getSingleResult();
    }

    @Override
    public EmployeeCourses getByPropertyName(String propertyName, String propertyValue) {
        List<EmployeeCourses> list = em.createQuery("SELECT e FROM  EmployeeCoursese WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return (list.isEmpty()) ? null : list.get(0);
    }

    @Override
    public List<EmployeeCourses> getEntitiesByProperName(String propertyName, String propertyValue) {
          List<EmployeeCourses> list = em.createQuery("SELECT e FROM  EmployeeCourses e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return list;
    }
}