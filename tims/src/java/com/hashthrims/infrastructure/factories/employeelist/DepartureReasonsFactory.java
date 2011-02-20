/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.infrastructure.factories.employeelist;

import com.hashthrims.domain.employeelist.DepartureReasons;
import com.hashthrims.infrastructure.conf.GetContext;
import com.hashthrims.services.DepartureReasonsService;
import org.springframework.context.ApplicationContext;


/**
 *
 * @author boniface
 */
public class DepartureReasonsFactory {

    private DepartureReasonsService departureReasonsService;
    private ApplicationContext ctx = GetContext.getApplicationContext();

    public DepartureReasons createDepartureReasons(String reasonName) {
        DepartureReasons e = new DepartureReasons();
        e.setReason_name(reasonName);
        return e;
    }

    public DepartureReasons loadDepartureReasons(Long id) {
        departureReasonsService = (DepartureReasonsService) ctx.getBean("departureReasonsService");
        DepartureReasons e = departureReasonsService.find(id);
        return e;
    }
    public DepartureReasons updatedDepartureReasons(String reasonName, Long id) {
        DepartureReasons dr = loadDepartureReasons(id);
        dr.setReason_name(reasonName);
        return dr;
    }

   
}
