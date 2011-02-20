package com.hashthrims.repository.jpa.impl;


import com.hashthrims.domain.traininglist.CourseTypeName;
import com.hashthrims.repository.jpa.CourseTypeNameDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author boniface
 */
@Repository("courseTypeNameDAO")
@Transactional
public class CourseTypeDAOJPAImpl  implements CourseTypeNameDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public CourseTypeName find(Long id) {
        return em.find(CourseTypeName.class, id);
    }

    @Override
    public void persist(CourseTypeName entity) {
        em.persist(entity);
    }

    @Override
    public void merge(CourseTypeName entity) {
        em.merge(entity);
    }

    @Override
    public void remove(CourseTypeName entity) {
        CourseTypeName acc =em.find(CourseTypeName.class, entity.getId());
        em.remove(acc);
    }

    @Override
    public List<CourseTypeName> findAll() {
        return (List<CourseTypeName>) em.createQuery("SELECT a FROM CourseTypeName a").getResultList();
    }

    @Override
    public List<CourseTypeName> findInRange(int firstResult, int maxResults) {
        return em.createQuery("SELECT a from CourseTypeName a").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Override
    public long count() {
         return (Long) em.createQuery("SELECT count(a) FROM CourseTypeName a").getSingleResult();
    }

    @Override
    public CourseTypeName getByPropertyName(String propertyName, String propertyValue) {
        List<CourseTypeName> list = em.createQuery("SELECT e FROM  CourseTypeName e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return (list.isEmpty()) ? null : list.get(0);
    }

    @Override
    public List<CourseTypeName> getEntitiesByProperName(String propertyName, String propertyValue) {
          List<CourseTypeName> list = em.createQuery("SELECT e FROM  CourseTypeName e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return list;
    }
}