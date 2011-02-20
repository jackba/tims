package com.hashthrims.repository.jpa.impl;


import com.hashthrims.domain.DisciplinaryAction;
import com.hashthrims.repository.jpa.DisciplinaryActionDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author boniface
 */
@Repository("disciplinaryactionDAO")
@Transactional
public class DisciplinaryActionDAOJPAImpl  implements DisciplinaryActionDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public DisciplinaryAction find(Long id) {
        return em.find(DisciplinaryAction.class, id);
    }

    @Override
    public void persist(DisciplinaryAction entity) {
        em.persist(entity);
    }

    @Override
    public void merge(DisciplinaryAction entity) {
        em.merge(entity);
    }

    @Override
    public void remove(DisciplinaryAction entity) {
        DisciplinaryAction acc =em.find(DisciplinaryAction.class, entity.getId());
        em.remove(acc);
    }

    @Override
    public List<DisciplinaryAction> findAll() {
        return (List<DisciplinaryAction>) em.createQuery("SELECT a FROM DisciplinaryAction a").getResultList();
    }

    @Override
    public List<DisciplinaryAction> findInRange(int firstResult, int maxResults) {
        return em.createQuery("SELECT a from DisciplinaryAction a").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Override
    public long count() {
         return (Long) em.createQuery("SELECT count(a) FROM DisciplinaryAction a").getSingleResult();
    }

    @Override
    public DisciplinaryAction getByPropertyName(String propertyName, String propertyValue) {
        List<DisciplinaryAction> list = em.createQuery("SELECT e FROM  DisciplinaryAction e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return (list.isEmpty()) ? null : list.get(0);
    }

    @Override
    public List<DisciplinaryAction> getEntitiesByProperName(String propertyName, String propertyValue) {
          List<DisciplinaryAction> list = em.createQuery("SELECT e FROM  DisciplinaryAction e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return list;
    }
}