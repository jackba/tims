/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims.util;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.*;
/**
 *
 * @author abismail
 */
@XmlRootElement
public class DisciplineActionTypeListForm implements Serializable{
   
    private String disciplineAction;

    /**
     * @return the disciplineAction
     */
    public String getDisciplineAction() {
        return disciplineAction;
    }

    /**
     * @param disciplineAction the disciplineAction to set
     */
    public void setDisciplineAction(String disciplineAction) {
        this.disciplineAction = disciplineAction;
    }

   
}
