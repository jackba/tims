/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.domain.jobs;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author boniface
 */
@Entity
public class Classifications implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String jobName;
    private String jobDesc;
    private String jobCode;



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
        if (!(object instanceof Classifications)) {
            return false;
        }
        Classifications other = (Classifications) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hashthrims.domain.jobs.Classifications[id=" + id + "]";
    }

    /**
     * @return the job_name
     */
    public String getJob_name() {
        return jobName;
    }

    /**
     * @param job_name the job_name to set
     */
    public void setJob_name(String job_name) {
        this.jobName = job_name;
    }

    /**
     * @return the job_desc
     */
    public String getJob_desc() {
        return jobDesc;
    }

    /**
     * @param job_desc the job_desc to set
     */
    public void setJob_desc(String job_desc) {
        this.jobDesc = job_desc;
    }

    /**
     * @return the job_code
     */
    public String getJob_code() {
        return jobCode;
    }

    /**
     * @param job_code the job_code to set
     */
    public void setJob_code(String job_code) {
        this.jobCode = job_code;
    }

}
