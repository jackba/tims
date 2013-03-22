/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.services.Impl;


import com.hashthrims.domain.regionlist.AddressType;
import com.hashthrims.repository.jpa.AddressTypeDAO;
import com.hashthrims.services.AddressTypeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author stud
 */
@Service("addressTypeService")

public class AddressTypeImplService implements AddressTypeService{
     @Autowired
    private AddressTypeDAO addressTypeDAO;


    @Override
    public AddressType find(Long id) {
        if(id!= null){
        return addressTypeDAO.find(id);
        }
        return null;
    }

  
    @Override
    public void persist(AddressType entity) {
        addressTypeDAO.persist(entity);
    }

    @Override
    public void merge(AddressType entity) {
        addressTypeDAO.merge(entity);
    }

    @Override
    public void remove(AddressType entity) {

        addressTypeDAO.remove(entity);
    }

    @Override
    public List<AddressType> findAll() {
         return  addressTypeDAO.findAll();
    }

    @Override
    public List<AddressType> findInRange(int firstResult, int maxResults) {
         return addressTypeDAO.findInRange(firstResult, maxResults);
    }

    @Override
    public long count() {
         return  addressTypeDAO.count();
    }

    @Override
    public AddressType getByPropertyName(String propertyName, String propertyValue) {
                return addressTypeDAO.getByPropertyName(propertyName, propertyValue);
    }

    /**
     * @return the addressDAO
     */
    public AddressTypeDAO getAddressTypeDAO() {
        return addressTypeDAO;
    }

    /**
     * @param addressDAO the addressDAO to set
     */
    public void setAddressTypeDAO(AddressTypeDAO AddressTypeDAO) {
        this.addressTypeDAO = AddressTypeDAO;
    }

    @Override
    public List<AddressType> getEntitiesByProperName(String propertyName, String propertyValue) {
        return addressTypeDAO.getEntitiesByProperName(propertyName, propertyValue);
    }


}
