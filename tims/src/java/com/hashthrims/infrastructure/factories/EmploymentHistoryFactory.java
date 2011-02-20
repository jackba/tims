/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.infrastructure.factories;

import com.hashthrims.domain.EmployeePosition;
import com.hashthrims.domain.EmploymentHistory;
import com.hashthrims.infrastructure.conf.GetContext;
import com.hashthrims.services.EmploymentHistoryService;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author abismail
 */
public class EmploymentHistoryFactory {
private EmploymentHistoryService employeeHistoryService;


private ApplicationContext ctx = GetContext.getApplicationContext();

    public EmploymentHistory createEmploymentHistory(){
        EmploymentHistory c = new EmploymentHistory();
        return c;
    }

    public EmploymentHistory loadEmploymentHistory(Long id) {
        employeeHistoryService = (EmploymentHistoryService) ctx.getBean("employeehistoryService");
        EmploymentHistory c = employeeHistoryService.find(id);
        return c;
    }
}
