/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.repository.jpa.impl;

import com.hashthrims.domain.employeelist.MentorsRoles;
import com.hashthrims.repository.jpa.MentorsRolesDAO;
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

@Repository("mentorsRolesDAO")
@Transactional
public class MentorsRolesDAOJPAImpl  implements MentorsRolesDAO{

    @PersistenceContext(type=PersistenceContextType.EXTENDED)
    private EntityManager em;

    @Override
    public MentorsRoles find(Long id) {
        return em.find(MentorsRoles.class, id);
    }

    @Override
    public void persist(MentorsRoles entity) {
        em.persist(entity);
    }

    @Override
    public void merge(MentorsRoles entity) {
        em.merge(entity);
    }

    @Override
    public void remove(MentorsRoles entity) {
        MentorsRoles acc =em.find(MentorsRoles.class, entity.getId());
        em.remove(acc);
    }

    @Override
    public List<MentorsRoles> findAll() {
        return (List<MentorsRoles>) em.createQuery("SELECT a FROM MentorsRoles a").getResultList();
    }

    @Override
    public List<MentorsRoles> findInRange(int firstResult, int maxResults) {
        return em.createQuery("SELECT a from MentorsRoles a").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Override
    public long count() {
         return (Long) em.createQuery("SELECT count(a) FROM MentorsRoles a").getSingleResult();
    }

    @Override
    public MentorsRoles getByPropertyName(String propertyName, String propertyValue) {
        List<MentorsRoles> list = em.createQuery("SELECT e FROM  MentorsRoles e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return (list.isEmpty()) ? null : list.get(0);
    }

    @Override
    public List<MentorsRoles> getEntitiesByProperName(String propertyName, String propertyValue) {
          List<MentorsRoles> list = em.createQuery("SELECT e FROM  MentorsRoles e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return list;
    }

}
