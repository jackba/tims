/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.repository.jpa.impl;

import com.hashthrims.domain.regionlist.AddressType;
import com.hashthrims.repository.jpa.AddressTypeDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author stud
 */
@Repository("addressTypeDAO")
@Transactional
public class AddressTypeDAOJPAImpl implements AddressTypeDAO{

    @PersistenceContext(type=PersistenceContextType.EXTENDED)
    private EntityManager em;

    @Override
    public AddressType find(Long id) {
        return em.find(AddressType.class, id);
    }

    @Override
    public void persist(AddressType entity) {
        em.persist(entity);
    }

    @Override
    public void merge(AddressType entity) {
        em.merge(entity);
    }

    @Override
    public void remove(AddressType entity) {
        AddressType acc =em.find(AddressType.class, entity.getId());
        em.remove(acc);
    }

    @Override
    public List<AddressType> findAll() {
        return (List<AddressType>) em.createQuery("SELECT a FROM AddressType a").getResultList();
    }

    @Override
    public List<AddressType> findInRange(int firstResult, int maxResults) {
        return em.createQuery("SELECT a from AddressType a").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Override
    public long count() {
         return (Long) em.createQuery("SELECT count(a) FROM AddressType a").getSingleResult();
    }

    @Override
    public AddressType getByPropertyName(String propertyName, String propertyValue) {
        List<AddressType> list = em.createQuery("SELECT e FROM  AddressType e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return (list.isEmpty()) ? null : list.get(0);
    }

    @Override
    public List<AddressType> getEntitiesByProperName(String propertyName, String propertyValue) {
          List<AddressType> list = em.createQuery("SELECT e FROM  AddressType e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return list;
    }

   

}
