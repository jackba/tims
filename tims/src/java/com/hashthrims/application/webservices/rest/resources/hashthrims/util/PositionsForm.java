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
public class PositionsForm implements Serializable {

    private String positionCode;
    private String positionTitle;
    private String positionDesc;

    /**
     * @return the positionCode
     */
    public String getPositionCode() {
        return positionCode;
    }

    /**
     * @param positionCode the positionCode to set
     */
    public void setPositionCode(String positionCode) {
        this.positionCode = positionCode;
    }

    /**
     * @return the positionTitle
     */
    public String getPositionTitle() {
        return positionTitle;
    }

    /**
     * @param positionTitle the positionTitle to set
     */
    public void setPositionTitle(String positionTitle) {
        this.positionTitle = positionTitle;
    }

    /**
     * @return the positionDesc
     */
    public String getPositionDesc() {
        return positionDesc;
    }

    /**
     * @param positionDesc the positionDesc to set
     */
    public void setPositionDesc(String positionDesc) {
        this.positionDesc = positionDesc;
    }
}
