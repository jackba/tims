/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.domain;

import com.hashthrims.domain.offices.RegistrationBody;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author boniface
 */
@Entity
public class ProfessionalRegistration implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private RegistrationBody registrationBody;
    private String registrationNumber;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date registrationDate;
    private String licenceNumber;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date expirationDate;


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
        if (!(object instanceof ProfessionalRegistration)) {
            return false;
        }
        ProfessionalRegistration other = (ProfessionalRegistration) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hashthrims.domain.ProfessionalRegistration[id=" + id + "]";
    }

    /**
     * @return the registrationBody
     */
    public RegistrationBody getRegistrationBody() {
        return registrationBody;
    }

    /**
     * @param registrationBody the registrationBody to set
     */
    public void setRegistrationBody(RegistrationBody registrationBody) {
        this.registrationBody = registrationBody;
    }

    /**
     * @return the registrationNumber
     */
    public String getRegistrationNumber() {
        return registrationNumber;
    }

    /**
     * @param registrationNumber the registrationNumber to set
     */
    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

   
    /**
     * @return the licenceNumber
     */
    public String getLicenceNumber() {
        return licenceNumber;
    }

    /**
     * @param licenceNumber the licenceNumber to set
     */
    public void setLicenceNumber(String licenceNumber) {
        this.licenceNumber = licenceNumber;
    }

    /**
     * @return the registrationDate
     */
    public Date getRegistrationDate() {
        return registrationDate;
    }

    /**
     * @param registrationDate the registrationDate to set
     */
    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    /**
     * @return the expirationDate
     */
    public Date getExpirationDate() {
        return expirationDate;
    }

    /**
     * @param expirationDate the expirationDate to set
     */
    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

   

}
