/*/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.repository.jpa.impl;

import com.hashthrims.domain.employeelist.BenefitFrequency;
import com.hashthrims.repository.jpa.BenefitFrequencyDAO;
import com.hashthrims.repository.jpa.BenefitFrequencyDAO;
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
@Repository("benefitFrequencyDAO")
@Transactional
public class BenefitFrequencyDAOJPAImpl implements BenefitFrequencyDAO {

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager em;

    @Override
    public BenefitFrequency find(Long id) {
        return em.find(BenefitFrequency.class, id);
    }

    @Override
    public void persist(BenefitFrequency entity) {
        em.persist(entity);
    }

    @Override
    public void merge(BenefitFrequency entity) {
        em.merge(entity);
    }

    @Override
    public void remove(BenefitFrequency entity) {
        BenefitFrequency acc = em.find(BenefitFrequency.class, entity.getId());
        em.remove(acc);
    }

    @Override
    public List<BenefitFrequency> findAll() {
        return (List<BenefitFrequency>) em.createQuery("SELECT a FROM BenefitFrequency a").getResultList();
    }

    @Override
    public List<BenefitFrequency> findInRange(int firstResult, int maxResults) {
        return em.createQuery("SELECT a from BenefitFrequency a").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Override
    public long count() {
        return (Long) em.createQuery("SELECT count(a) FROM BenefitFrequency a").getSingleResult();
    }

    @Override
    public BenefitFrequency getByPropertyName(String propertyName, String propertyValue) {
        List<BenefitFrequency> list = em.createQuery("SELECT e FROM  BenefitFrequency e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return (list.isEmpty()) ? null : list.get(0);
    }

    @Override
    public List<BenefitFrequency> getEntitiesByProperName(String propertyName, String propertyValue) {
        List<BenefitFrequency> list = em.createQuery("SELECT e FROM  BenefitFrequency e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return list;
    }
}
