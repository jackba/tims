/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.infrastructure.factories;

import com.hashthrims.domain.EducationHistory;
import com.hashthrims.domain.employeelist.Degree;
import com.hashthrims.domain.regionlist.Country;
import com.hashthrims.infrastructure.conf.GetContext;
import com.hashthrims.services.EducationHistoryService;
import java.util.Date;
import org.springframework.context.ApplicationContext;
import javax.swing.*;

/**
 *
 * @author abismail
 */
public class EducationHistoryFactory {
private EducationHistoryService educationHistoryService;


    private ApplicationContext ctx = GetContext.getApplicationContext();
JComboBox j = new JComboBox();

//TODO: the education class must be completed for this factory and ultimately the GUI to be complete
    public EducationHistory createEducationHistory(Degree degreeType, Date graduationDate, String institutionName, Country location, String major){
        EducationHistory c = new EducationHistory();
        c.setDegreeType(degreeType);
        c.setGraduation(graduationDate);
        c.setInstituteNamwe(institutionName);
        c.setLocation(location);
        c.setMajor(major);

        return c;
    }

    public EducationHistory loadEducationHistory(Long id) {
        educationHistoryService = (EducationHistoryService) ctx.getBean("educationhistoryService");
        EducationHistory c = educationHistoryService.find(id);
        return c;
    }
}
