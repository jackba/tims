package com.hashthrims.services.Impl;

import com.hashthrims.domain.traininglist.CourseTypeName;
import com.hashthrims.repository.jpa.CourseTypeNameDAO;
import com.hashthrims.services.CourseTypeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author stud
 */
@Service("courseTypeNameService")
@Transactional
public class CourseTypeNameImplService implements CourseTypeService{
     @Autowired
    private CourseTypeNameDAO CourseTypeNameDAO;


    @Override
    public CourseTypeName find(Long id) {
        if(id!= null){
        return CourseTypeNameDAO.find(id);
        }
        return null;
    }

    @Transactional(readOnly= false)
    @Override
    public void persist(CourseTypeName entity) {
        CourseTypeNameDAO.persist(entity);
    }

    @Override
    public void merge(CourseTypeName entity) {
        CourseTypeNameDAO.merge(entity);
    }

    @Override
    public void remove(CourseTypeName entity) {

        CourseTypeNameDAO.remove(entity);
    }

    @Override
    public List<CourseTypeName> findAll() {
         return  CourseTypeNameDAO.findAll();
    }

    @Override
    public List<CourseTypeName> findInRange(int firstResult, int maxResults) {
         return CourseTypeNameDAO.findInRange(firstResult, maxResults);
    }

    @Override
    public long count() {
         return  CourseTypeNameDAO.count();
    }

    @Override
    public CourseTypeName getByPropertyName(String propertyName, String propertyValue) {
                return CourseTypeNameDAO.getByPropertyName(propertyName, propertyValue);
    }

    /**
     * @return the addressDAO
     */
    public CourseTypeNameDAO getCourseTypeNameDAO() {
        return CourseTypeNameDAO;
    }

    /**
     * @param addressDAO the addressDAO to set
     */
    public void setCourseTypeNameDAO(CourseTypeNameDAO CourseTypeNameDAO) {
        this.CourseTypeNameDAO = CourseTypeNameDAO;
    }

    @Override
    public List<CourseTypeName> getEntitiesByProperName(String propertyName, String propertyValue) {
        return CourseTypeNameDAO.getEntitiesByProperName(propertyName, propertyValue);
    }


}