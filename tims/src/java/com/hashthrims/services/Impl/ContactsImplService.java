package com.hashthrims.services.Impl;

import com.hashthrims.domain.Contacts;
import com.hashthrims.repository.jpa.ContactsDAO;
import com.hashthrims.services.ContactsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author stud
 */
@Service("contactsService")

public class ContactsImplService implements ContactsService{
     @Autowired
    private ContactsDAO contactsDAO;


    @Override
    public Contacts find(Long id) {
        if(id!= null){
        return contactsDAO.find(id);
        }
        return null;
    }

 
    @Override
    public void persist(Contacts entity) {
        contactsDAO.persist(entity);
    }

    @Override
    public void merge(Contacts entity) {
        contactsDAO.merge(entity);
    }

    @Override
    public void remove(Contacts entity) {

        contactsDAO.remove(entity);
    }

    @Override
    public List<Contacts> findAll() {
         return  contactsDAO.findAll();
    }

    @Override
    public List<Contacts> findInRange(int firstResult, int maxResults) {
         return contactsDAO.findInRange(firstResult, maxResults);
    }

    @Override
    public long count() {
         return  contactsDAO.count();
    }

    @Override
    public Contacts getByPropertyName(String propertyName, String propertyValue) {
                return contactsDAO.getByPropertyName(propertyName, propertyValue);
    }

    /**
     * @return the addressDAO
     */
    public ContactsDAO getContactsDAO() {
        return contactsDAO;
    }

    /**
     * @param addressDAO the addressDAO to set
     */
    public void setContactsDAO(ContactsDAO contactsDAO) {
        this.contactsDAO = contactsDAO;
    }

    @Override
    public List<Contacts> getEntitiesByProperName(String propertyName, String propertyValue) {
        return contactsDAO.getEntitiesByProperName(propertyName, propertyValue);
    }


}