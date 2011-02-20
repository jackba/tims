/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.services.Impl;

import com.hashthrims.domain.Person;
import com.hashthrims.repository.jpa.PersonDAO;
import com.hashthrims.services.PersonService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author abismail
 */
@Service("personService")
@Transactional
public class PersonImplService implements PersonService{
     @Autowired
    private PersonDAO personDAO;


    @Override
    public Person find(Long id) {
        if(id!= null){
        return personDAO.find(id);
        }
        return null;
    }

    @Transactional(readOnly= false)
    @Override
    public void persist(Person entity) {
        personDAO.persist(entity);
    }

    @Override
    public void merge(Person entity) {
        personDAO.merge(entity);
    }

    @Override
    public void remove(Person entity) {

        personDAO.remove(entity);
    }

    @Override
    public List<Person> findAll() {
         return  personDAO.findAll();
    }

    @Override
    public List<Person> findInRange(int firstResult, int maxResults) {
         return personDAO.findInRange(firstResult, maxResults);
    }

    @Override
    public long count() {
         return  personDAO.count();
    }

    @Override
    public Person getByPropertyName(String propertyName, String propertyValue) {
                return personDAO.getByPropertyName(propertyName, propertyValue);
    }

    /**
     * @return the addressDAO
     */
    public PersonDAO getPersonDAO() {
        return personDAO;
    }

    /**
     * @param addressDAO the addressDAO to set
     */
    public void setPersonDAO(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Override
    public List<Person> getEntitiesByProperName(String propertyName, String propertyValue) {
        return personDAO.getEntitiesByProperName(propertyName, propertyValue);
    }
}
