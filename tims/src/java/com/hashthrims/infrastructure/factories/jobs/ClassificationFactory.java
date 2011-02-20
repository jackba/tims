/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.infrastructure.factories.jobs;

import com.hashthrims.domain.jobs.Classifications;
import com.hashthrims.infrastructure.conf.GetContext;
import com.hashthrims.services.ClassificationsService;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author stud
 */
public class ClassificationFactory {

    private ClassificationsService classificationsService;
    private ApplicationContext ctx = GetContext.getApplicationContext();


     public Classifications createClassifications(String jobName, String jobDesc, String jobCode )
    {
        Classifications c = new Classifications();
        c.setJob_code(jobCode);
        c.setJob_name(jobName);
        c.setJob_desc(jobDesc);
        return c;
    }
    public Classifications loadClassification(Long id) {
        classificationsService = (ClassificationsService) ctx.getBean("cadresService");
        Classifications c = classificationsService.find(id);
        return c;
    }




}
