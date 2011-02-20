/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.repository.jpa.impl;

import com.hashthrims.domain.Person;
import com.hashthrims.repository.jpa.PersonDAO;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author abismail
 */
@Repository("personDAO")
@Transactional
public class PersonDAOJPAImpl implements PersonDAO{

    @PersistenceContext(type=PersistenceContextType.EXTENDED)
    private EntityManager em;

    @Override
    public Person find(Long id) {
        return em.find(Person.class, id);
    }

    @Override
    public void persist(Person entity) {
        em.persist(entity);
    }

    @Override
    public void merge(Person entity) {
        em.merge(entity);
    }

    @Override
    public void remove(Person entity) {
        Person id =em.find(Person.class, entity.getId());
        em.remove(id);
    }

    @Override
    public List<Person> findAll() {
        return (List<Person>) em.createQuery("SELECT a FROM Person a").getResultList();
    }

    @Override
    public List<Person> findInRange(int firstResult, int maxResults) {
        return em.createQuery("SELECT a from Person a").setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Override
    public long count() {
         return (Long) em.createQuery("SELECT count(a) FROM Person a").getSingleResult();
    }

    @Override
    public Person getByPropertyName(String propertyName, String propertyValue) {
        List<Person> list = em.createQuery("SELECT e FROM  Person e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return (list.isEmpty()) ? null : list.get(0);
    }

    @Override
    public List<Person> getEntitiesByProperName(String propertyName, String propertyValue) {
        List<Person> list = em.createQuery("SELECT e FROM  Person e WHERE e." + propertyName + "=?1").setParameter(1, propertyValue).getResultList();
        return list;
    }
}
