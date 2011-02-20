/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.infrastructure.factories;

import com.hashthrims.domain.positions.Status;
import com.hashthrims.infrastructure.conf.GetContext;
import com.hashthrims.services.StatusService;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author abismail
 */
public class StatusFactory {

      private ApplicationContext ctx = GetContext.getApplicationContext();
    private StatusService statusService;

    public Status createStatus(String status) {
        Status s = new Status();
        s.setStatus(status);
        return s;
    }

    public Status loadStatus(Long id) {
        statusService = (StatusService) ctx.getBean("statusService");
        Status s = statusService.find(id);
        return s;
    }

    public Status updateStatus(String statusName, Long statusId) {
        Status s = loadStatus(statusId);
        s.setStatus(statusName);
        return s;
    }

}
