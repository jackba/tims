/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.repository.jpa.impl;

import com.hashthrims.domain.traininglist.CourseTargetGroup;
import com.hashthrims.repository.jpa.CourseTargetGroupDAO;
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

@Repository("courseTargetGroupDAO")
@Transactional
public class CourseTragetGroupDAOJPAImpl  implements CourseTargetGroupDAO{

    @PersistenceContext(type=PersistenceContextType.EXTENDED)
    private EntityManager em;

    @Override
    public CourseTargetGroup find(Long id) {
        return em.find(CourseTargetGroup.class, id);
    }

    @Override
    public void persist(CourseTargetGroup entity) {
        em.persist(entity);
    }

    @Override
    public void merge(CourseTargetGroup entity) {
        em.merge(entity);
    }

    @Override
    public void remove(CourseTargetGroup entity) {
        CourseTargetGroup acc =em.find(CourseTargetGroup.class, entity.getId());
        em.remove(acc);
    }

    @Override
    public List<CourseTargetGroup> findAll() {
        return (List<CourseTargetGroup>) em.createQuery("SELECT a FROM CourseTargetGroup a").getResultList();
    }

    @Override
    public List<CourseTargetGroup> findInRange(int firstResult, int maxResults) {
        return em.createQuery("SELECT a from CourseTargetGroup a").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Override
    public long count() {
         return (Long) em.createQuery("SELECT count(a) FROM CourseTargetGroup a").getSingleResult();
    }

    @Override
    public CourseTargetGroup getByPropertyName(String propertyName, String propertyValue) {
        List<CourseTargetGroup> list = em.createQuery("SELECT e FROM  CourseTargetGroup e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return (list.isEmpty()) ? null : list.get(0);
    }

    @Override
    public List<CourseTargetGroup> getEntitiesByProperName(String propertyName, String propertyValue) {
          List<CourseTargetGroup> list = em.createQuery("SELECT e FROM  CourseTargetGroup e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return list;
    }

}
