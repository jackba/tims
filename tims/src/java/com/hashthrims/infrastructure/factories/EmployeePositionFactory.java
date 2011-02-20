/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.infrastructure.factories;

import com.hashthrims.domain.EmployeePosition;
import com.hashthrims.infrastructure.conf.GetContext;
import com.hashthrims.services.EmployeePositionService;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author abismail
 */
public class EmployeePositionFactory {
private EmployeePositionService employeePositionService;


private ApplicationContext ctx = GetContext.getApplicationContext();

    public EmployeePosition createEmployeePosition(){
        EmployeePosition c = new EmployeePosition();
        return c;
    }

    public EmployeePosition loadEmployeePosition(Long id) {
        employeePositionService = (EmployeePositionService) ctx.getBean("employeepositionService");
        EmployeePosition c = employeePositionService.find(id);
        return c;
    }
}
