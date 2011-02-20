/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.infrastructure.factories;

import com.hashthrims.domain.Subject;
import com.hashthrims.infrastructure.conf.GetContext;
import com.hashthrims.services.SubjectService;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author abismail
 */
public class SubjectFactory {
    private SubjectService subjectService;
    private ApplicationContext ctx = GetContext.getApplicationContext();

    public Subject createSubject(String subjectName, String subjectGrade) {
        Subject e = new Subject();
        e.setSubjectName(subjectName);
        e.setSubjectGrade(subjectGrade);
        return e;
    }

    public Subject loadSubject(Long id) {
        subjectService = (SubjectService) ctx.getBean("subjectService");
        Subject e = subjectService.find(id);
        return e;
    }
}
