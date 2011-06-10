/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.repository.jpa.impl;

import com.hashthrims.domain.Mentees;
import com.hashthrims.repository.jpa.MenteesDAO;
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

@Repository("menteesDAO")
@Transactional
public class MenteesDAOJPAImpl  implements MenteesDAO{

    @PersistenceContext(type=PersistenceContextType.EXTENDED)
    private EntityManager em;

    @Override
    public Mentees find(Long id) {
        return em.find(Mentees.class, id);
    }

    @Override
    public void persist(Mentees entity) {
        em.persist(entity);
    }

    @Override
    public void merge(Mentees entity) {
        em.merge(entity);
    }

    @Override
    public void remove(Mentees entity) {
        Mentees acc =em.find(Mentees.class, entity.getId());
        em.remove(acc);
    }

    @Override
    public List<Mentees> findAll() {
        return (List<Mentees>) em.createQuery("SELECT a FROM Mentees a").getResultList();
    }

    @Override
    public List<Mentees> findInRange(int firstResult, int maxResults) {
        return em.createQuery("SELECT a from Mentees a").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Override
    public long count() {
         return (Long) em.createQuery("SELECT count(a) FROM Mentees a").getSingleResult();
    }

    @Override
    public Mentees getByPropertyName(String propertyName, String propertyValue) {
        List<Mentees> list = em.createQuery("SELECT e FROM  Mentees e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return (list.isEmpty()) ? null : list.get(0);
    }

    @Override
    public List<Mentees> getEntitiesByProperName(String propertyName, String propertyValue) {
          List<Mentees> list = em.createQuery("SELECT e FROM  Mentees e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return list;
    }

}
