/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.services.Impl;

import com.hashthrims.domain.Users;
import com.hashthrims.repository.jpa.UsersDAO;
import com.hashthrims.services.UsersService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 *
 * @author stud
 */
@Service("usersService")

public class UsersImplService implements UsersService{
     @Autowired
    private UsersDAO usersDAO;


    @Override
    public Users find(Long id) {
        if(id!= null){
        return usersDAO.find(id);
        }
        return null;
    }

  
    @Override
    public void persist(Users entity) {
        usersDAO.persist(entity);
    }

    @Override
    public void merge(Users entity) {
        usersDAO.merge(entity);
    }

    @Override
    public void remove(Users entity) {

        usersDAO.remove(entity);
    }

    @Override
    public List<Users> findAll() {
         return  usersDAO.findAll();
    }

    @Override
    public List<Users> findInRange(int firstResult, int maxResults) {
         return usersDAO.findInRange(firstResult, maxResults);
    }

    @Override
    public long count() {
         return  usersDAO.count();
    }

    @Override
    public Users getByPropertyName(String propertyName, String propertyValue) {
                return usersDAO.getByPropertyName(propertyName, propertyValue);
    }

    /**
     * @return the addressDAO
     */
    public UsersDAO getUsersDAO() {
        return usersDAO;
    }

    /**
     * @param addressDAO the addressDAO to set
     */
    public void setUsersDAO(UsersDAO usersDAO) {
        this.usersDAO = usersDAO;
    }

    @Override
    public List<Users> getEntitiesByProperName(String propertyName, String propertyValue) {
        return usersDAO.getEntitiesByProperName(propertyName, propertyValue);
    }


}
