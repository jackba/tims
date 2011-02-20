/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.repository.jpa.impl;

import com.hashthrims.domain.employeelist.BenefitType;
import com.hashthrims.repository.jpa.BenefitTypeDAO;
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
@Repository("benefitTypeDAO")
@Transactional
public class BenefitTypeDAOJPAImpl implements BenefitTypeDAO {

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager em;

    @Override
    public BenefitType find(Long id) {
        return em.find(BenefitType.class, id);
    }

    @Override
    public void persist(BenefitType entity) {
        em.persist(entity);
    }

    @Override
    public void merge(BenefitType entity) {
        em.merge(entity);
    }

    @Override
    public void remove(BenefitType entity) {
        BenefitType acc = em.find(BenefitType.class, entity.getId());
        em.remove(acc);
    }

    @Override
    public List<BenefitType> findAll() {
        return (List<BenefitType>) em.createQuery("SELECT a FROM BenefitType a").getResultList();
    }

    @Override
    public List<BenefitType> findInRange(int firstResult, int maxResults) {
        return em.createQuery("SELECT a from BenefitType a").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Override
    public long count() {
        return (Long) em.createQuery("SELECT count(a) FROM BenefitType a").getSingleResult();
    }

    @Override
    public BenefitType getByPropertyName(String propertyName, String propertyValue) {
        List<BenefitType> list = em.createQuery("SELECT e FROM  BenefitType e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return (list.isEmpty()) ? null : list.get(0);
    }

    @Override
    public List<BenefitType> getEntitiesByProperName(String propertyName, String propertyValue) {
        List<BenefitType> list = em.createQuery("SELECT e FROM  BenefitType e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return list;
    }
}
