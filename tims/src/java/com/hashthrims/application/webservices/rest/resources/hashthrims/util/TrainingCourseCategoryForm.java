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
public class TrainingCourseCategoryForm implements Serializable{
    
    private String categoryName;

    /**
     * @return the categoryName
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * @param categoryName the categoryName to set
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

}
