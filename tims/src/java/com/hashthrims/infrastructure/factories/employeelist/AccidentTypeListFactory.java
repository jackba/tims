/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.infrastructure.factories.employeelist;

import com.hashthrims.domain.employeelist.AccidentTypeList;
import com.hashthrims.infrastructure.conf.GetContext;
import com.hashthrims.services.AccidentTypeListService;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author boniface
 */
public class AccidentTypeListFactory {

    private AccidentTypeListService accidentTypeListService;
    private ApplicationContext ctx = GetContext.getApplicationContext();

    public AccidentTypeList createAccidentType(String accidentTypeListName) {
        AccidentTypeList e = new AccidentTypeList();
        e.setAccidentName(accidentTypeListName);
        return e;
    }

    public AccidentTypeList loadAccidentType(Long id) {
        accidentTypeListService = (AccidentTypeListService) ctx.getBean("accidentTypeListService");
        AccidentTypeList e = accidentTypeListService.find(id);
        return e;
    }

    public AccidentTypeList updatedAccidentTypeList(String accidentName, Long id) {
        AccidentTypeList atl = loadAccidentType(id);
        atl.setAccidentName(accidentName);
        return atl;
    }
}
