package com.hashthrims.repository.jpa.impl;


import com.hashthrims.domain.ProfessionalRegistration;
import com.hashthrims.repository.jpa.ProfessionalRegistrationDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author boniface
 */
@Repository("professionalregistrationDAO")
@Transactional
public class ProfessionalRegistrationDAOJPAImpl  implements ProfessionalRegistrationDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public ProfessionalRegistration find(Long id) {
        return em.find(ProfessionalRegistration.class, id);
    }

    @Override
    public void persist(ProfessionalRegistration entity) {
        em.persist(entity);
    }

    @Override
    public void merge(ProfessionalRegistration entity) {
        em.merge(entity);
    }

    @Override
    public void remove(ProfessionalRegistration entity) {
        ProfessionalRegistration acc =em.find(ProfessionalRegistration.class, entity.getId());
        em.remove(acc);
    }

    @Override
    public List<ProfessionalRegistration> findAll() {
        return (List<ProfessionalRegistration>) em.createQuery("SELECT a FROM ProfessionalRegistration a").getResultList();
    }

    @Override
    public List<ProfessionalRegistration> findInRange(int firstResult, int maxResults) {
        return em.createQuery("SELECT a from ProfessionalRegistration a").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Override
    public long count() {
         return (Long) em.createQuery("SELECT count(a) FROM ProfessionalRegistration a").getSingleResult();
    }

    @Override
    public ProfessionalRegistration getByPropertyName(String propertyName, String propertyValue) {
        List<ProfessionalRegistration> list = em.createQuery("SELECT e FROM  ProfessionalRegistration e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return (list.isEmpty()) ? null : list.get(0);
    }

    @Override
    public List<ProfessionalRegistration> getEntitiesByProperName(String propertyName, String propertyValue) {
          List<ProfessionalRegistration> list = em.createQuery("SELECT e FROM  ProfessionalRegistration e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return list;
    }
}