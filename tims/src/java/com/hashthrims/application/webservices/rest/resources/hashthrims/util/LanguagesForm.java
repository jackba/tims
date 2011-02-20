/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims.util;

import java.io.*;
import javax.xml.bind.annotation.XmlRootElement;
/**
 *
 * @author administrator.dat
 */
@XmlRootElement
public class LanguagesForm implements Serializable{
private String writing;
    private String reading;
    private String speaking;

    /**
     * @return the writing
     */
    public String getWriting() {
        return writing;
    }

    /**
     * @param writing the writing to set
     */
    public void setWriting(String writing) {
        this.writing = writing;
    }

    /**
     * @return the reading
     */
    public String getReading() {
        return reading;
    }

    /**
     * @param reading the reading to set
     */
    public void setReading(String reading) {
        this.reading = reading;
    }

    /**
     * @return the speaking
     */
    public String getSpeaking() {
        return speaking;
    }

    /**
     * @param speaking the speaking to set
     */
    public void setSpeaking(String speaking) {
        this.speaking = speaking;
    }
}
