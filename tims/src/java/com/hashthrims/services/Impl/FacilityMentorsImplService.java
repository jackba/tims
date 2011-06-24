/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.services.Impl;

import com.hashthrims.domain.offices.FacilityMentors;
import com.hashthrims.repository.jpa.FacilityMentorsDAO;
import com.hashthrims.services.FacilityMentorsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author stud
 */
@Service("facilityMentorsService")
@Transactional
public class FacilityMentorsImplService implements FacilityMentorsService {

    @Autowired
    private FacilityMentorsDAO facilityMentorsDAO;

    @Override
    public FacilityMentors find(Long id) {
        if (id != null) {
            return facilityMentorsDAO.find(id);
        }
        return null;
    }

    @Transactional(readOnly = false)
    @Override
    public void persist(FacilityMentors entity) {
        facilityMentorsDAO.persist(entity);
    }

    @Override
    public void merge(FacilityMentors entity) {
        facilityMentorsDAO.merge(entity);
    }

    @Override
    public void remove(FacilityMentors entity) {

        facilityMentorsDAO.remove(entity);
    }

    @Override
    public List<FacilityMentors> findAll() {
        return facilityMentorsDAO.findAll();
    }

    @Override
    public List<FacilityMentors> findInRange(int firstResult, int maxResults) {
        return facilityMentorsDAO.findInRange(firstResult, maxResults);
    }

    @Override
    public long count() {
        return facilityMentorsDAO.count();
    }

    @Override
    public FacilityMentors getByPropertyName(String propertyName, String propertyValue) {
        return facilityMentorsDAO.getByPropertyName(propertyName, propertyValue);
    }

    /**
     * @return the addressDAO
     */
    public FacilityMentorsDAO getFacilityMentorsDAO() {
        return facilityMentorsDAO;
    }

    /**
     * @param addressDAO the addressDAO to set
     */
    public void setFacilityMentorsDAO(FacilityMentorsDAO FacilityMentorsDAO) {
        this.facilityMentorsDAO = FacilityMentorsDAO;
    }

    @Override
    public List<FacilityMentors> getEntitiesByProperName(String propertyName, String propertyValue) {
        return facilityMentorsDAO.getEntitiesByProperName(propertyName, propertyValue);
    }
}
