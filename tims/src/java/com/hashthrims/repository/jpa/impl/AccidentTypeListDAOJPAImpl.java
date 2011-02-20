/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.repository.jpa.impl;



import com.hashthrims.domain.employeelist.AccidentTypeList;
import com.hashthrims.repository.jpa.AccidentTypeListDAO;
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
@Repository("accidentTypeListDAO")
@Transactional
public class AccidentTypeListDAOJPAImpl  implements AccidentTypeListDAO {

    @PersistenceContext(type=PersistenceContextType.EXTENDED)
    private EntityManager em;

    @Override
    public AccidentTypeList find(Long id) {
        return em.find(AccidentTypeList.class, id);
    }

    @Override
    public void persist(AccidentTypeList entity) {
        em.persist(entity);
    }

    @Override
    public void merge(AccidentTypeList entity) {
        em.merge(entity);
    }

    @Override
    public void remove(AccidentTypeList entity) {
        AccidentTypeList acc =em.find(AccidentTypeList.class, entity.getId());
        em.remove(acc);
    }

    @Override
    public List<AccidentTypeList> findAll() {
        return (List<AccidentTypeList>) em.createQuery("SELECT a FROM AccidentTypeList a").getResultList();
    }

    @Override
    public List<AccidentTypeList> findInRange(int firstResult, int maxResults) {
        return em.createQuery("SELECT a from AccidentTypeList a").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Override
    public long count() {
         return (Long) em.createQuery("SELECT count(a) FROM AccidentTypeList a").getSingleResult();
    }

    @Override
    public AccidentTypeList getByPropertyName(String propertyName, String propertyValue) {
        List<AccidentTypeList> list = em.createQuery("SELECT e FROM  AccidentTypeList e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return (list.isEmpty()) ? null : list.get(0);
    }

    @Override
    public List<AccidentTypeList> getEntitiesByProperName(String propertyName, String propertyValue) {
          List<AccidentTypeList> list = em.createQuery("SELECT e FROM  AccidentTypeList e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return list;
    }
}
