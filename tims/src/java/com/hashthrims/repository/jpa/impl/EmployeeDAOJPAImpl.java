package com.hashthrims.repository.jpa.impl;


import com.hashthrims.domain.Employee;
import com.hashthrims.repository.jpa.EmployeeDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author boniface
 */
@Repository("employeeDAO")
@Transactional
public class EmployeeDAOJPAImpl  implements EmployeeDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Employee find(Long id) {
        return em.find(Employee.class, id);
    }

    @Override
    public void persist(Employee entity) {
        em.persist(entity);
    }

    @Override
    public void merge(Employee entity) {
        em.merge(entity);
    }

    @Override
    public void remove(Employee entity) {
        Employee acc =em.find(Employee.class, entity.getId());
        em.remove(acc);
    }

    @Override
    public List<Employee> findAll() {
        return (List<Employee>) em.createQuery("SELECT a FROM Employee a").getResultList();
    }

    @Override
    public List<Employee> findInRange(int firstResult, int maxResults) {
        return em.createQuery("SELECT a from Employee a").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Override
    public long count() {
         return (Long) em.createQuery("SELECT count(a) FROM Employee a").getSingleResult();
    }

    @Override
    public Employee getByPropertyName(String propertyName, String propertyValue) {
        List<Employee> list = em.createQuery("SELECT e FROM  Employee e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return (list.isEmpty()) ? null : list.get(0);
    }

    
    public Employee getByPropertyName(String propertyName, Object propertyValue) {
        List<Employee> list = em.createQuery("SELECT e FROM  Employee e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return (list.isEmpty()) ? null : list.get(0);
    }

    @Override
    public List<Employee> getEntitiesByProperName(String propertyName, String propertyValue) {
          List<Employee> list = em.createQuery("SELECT e FROM  Employee e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return list;
    }
}