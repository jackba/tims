package com.hashthrims.services.Impl;

import com.hashthrims.domain.positions.PositionSalarySources;
import com.hashthrims.repository.jpa.PositionSalarySourcesDAO;
import com.hashthrims.services.PositionSalarySourcesService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author stud
 */
@Service("positionSalarySourcesService")

public class PositionSalarySourcesImplService implements PositionSalarySourcesService {

    @Autowired
    private PositionSalarySourcesDAO positionSalarySourcesDAO;

    @Override
    public PositionSalarySources find(Long id) {
        if (id != null) {
            return getPositionSalarySourcesDAO().find(id);
        }
        return null;
    }

   
    @Override
    public void persist(PositionSalarySources entity) {
        getPositionSalarySourcesDAO().persist(entity);
    }

    @Override
    public void merge(PositionSalarySources entity) {
        getPositionSalarySourcesDAO().merge(entity);
    }

    @Override
    public void remove(PositionSalarySources entity) {
        getPositionSalarySourcesDAO().remove(entity);
    }

    @Override
    public List<PositionSalarySources> findAll() {
        return getPositionSalarySourcesDAO().findAll();
    }

    @Override
    public List<PositionSalarySources> findInRange(int firstResult, int maxResults) {
        return getPositionSalarySourcesDAO().findInRange(firstResult, maxResults);
    }

    @Override
    public long count() {
        return getPositionSalarySourcesDAO().count();
    }

    @Override
    public PositionSalarySources getByPropertyName(String propertyName, String propertyValue) {
        return getPositionSalarySourcesDAO().getByPropertyName(propertyName, propertyValue);
    }

    @Override
    public List<PositionSalarySources> getEntitiesByProperName(String propertyName, String propertyValue) {
        return getPositionSalarySourcesDAO().getEntitiesByProperName(propertyName, propertyValue);
    }

    /**
     * @return the positionSalarySourcesDAO
     */
    public PositionSalarySourcesDAO getPositionSalarySourcesDAO() {
        return positionSalarySourcesDAO;
    }

    /**
     * @param positionSalarySourcesDAO the positionSalarySourcesDAO to set
     */
    public void setPositionSalarySourcesDAO(PositionSalarySourcesDAO positionSalarySourcesDAO) {
        this.positionSalarySourcesDAO = positionSalarySourcesDAO;
    }
}
