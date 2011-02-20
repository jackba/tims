/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.repository.jpa.impl;


import com.hashthrims.domain.EmployeePosition;
import com.hashthrims.repository.jpa.EmployeePositionDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author abismail
 */
@Repository("employeepositionDAO")
@Transactional
public class EmployeePositionDAOJPAImpl  implements EmployeePositionDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public EmployeePosition find(Long id) {
        return em.find(EmployeePosition.class, id);
    }

    @Override
    public void persist(EmployeePosition entity) {
        em.persist(entity);
    }

    @Override
    public void merge(EmployeePosition entity) {
        em.merge(entity);
    }

    @Override
    public void remove(EmployeePosition entity) {
        EmployeePosition acc =em.find(EmployeePosition.class, entity.getId());
        em.remove(acc);
    }

    @Override
    public List<EmployeePosition> findAll() {
        return (List<EmployeePosition>) em.createQuery("SELECT a FROM EmployeePosition a").getResultList();
    }

    @Override
    public List<EmployeePosition> findInRange(int firstResult, int maxResults) {
        return em.createQuery("SELECT a from EmployeePosition a").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Override
    public long count() {
         return (Long) em.createQuery("SELECT count(a) FROM EmployeePosition a").getSingleResult();
    }

    @Override
    public EmployeePosition getByPropertyName(String propertyName, String propertyValue) {
        List<EmployeePosition> list = em.createQuery("SELECT e FROM  EmployeePosition e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return (list.isEmpty()) ? null : list.get(0);
    }

    @Override
    public List<EmployeePosition> getEntitiesByProperName(String propertyName, String propertyValue) {
        List<EmployeePosition> list = em.createQuery("SELECT e FROM  EmployeePosition e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return list;
    }
}
