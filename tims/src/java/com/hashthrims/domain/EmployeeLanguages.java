/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.domain;

import com.hashthrims.domain.employeelist.Language;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author boniface
 */
@Entity
public class EmployeeLanguages implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private Language language;
    private String writing;
    private String reading;
    private String speaking;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmployeeLanguages)) {
            return false;
        }
        EmployeeLanguages other = (EmployeeLanguages) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hashthrims.domain.Languages[id=" + id + "]";
    }

    /**
     * @return the language
     */
    public Language getLanguage() {
        return language;
    }

    /**
     * @param language the language to set
     */
    public void setLanguage(Language language) {
        this.language = language;
    }

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
