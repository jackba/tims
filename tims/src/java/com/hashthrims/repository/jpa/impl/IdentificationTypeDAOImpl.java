/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.repository.jpa.impl;



import com.hashthrims.domain.employeelist.IdentificationType;
import com.hashthrims.repository.jpa.IdentificationTypeDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author boniface
 */
@Repository("identificationTypeDAO")
@Transactional
public class IdentificationTypeDAOImpl  implements IdentificationTypeDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public IdentificationType find(Long id) {
        return em.find(IdentificationType.class, id);
    }

    @Override
    public void persist(IdentificationType entity) {
        em.persist(entity);
    }

    @Override
    public void merge(IdentificationType entity) {
        em.merge(entity);
    }

    @Override
    public void remove(IdentificationType entity) {
        IdentificationType acc =em.find(IdentificationType.class, entity.getId());
        em.remove(acc);
    }

    @Override
    public List<IdentificationType> findAll() {
        return (List<IdentificationType>) em.createQuery("SELECT a FROM IdentificationType a").getResultList();
    }

    @Override
    public List<IdentificationType> findInRange(int firstResult, int maxResults) {
        return em.createQuery("SELECT a from IdentificationType a").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Override
    public long count() {
         return (Long) em.createQuery("SELECT count(a) FROM IdentificationType a").getSingleResult();
    }

    @Override
    public IdentificationType getByPropertyName(String propertyName, String propertyValue) {
        List<IdentificationType> list = em.createQuery("SELECT e FROM  IdentificationType e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return (list.isEmpty()) ? null : list.get(0);
    }

    @Override
    public List<IdentificationType> getEntitiesByProperName(String propertyName, String propertyValue) {
          List<IdentificationType> list = em.createQuery("SELECT e FROM  IdentificationType e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return list;
    }
}
