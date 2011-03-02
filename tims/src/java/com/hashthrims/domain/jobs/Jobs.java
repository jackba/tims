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
import javax.persistence.OneToOne;

/**
 *
 * @author boniface
 */
@Entity
public class Jobs implements Serializable, Comparable<Jobs>{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String jobTittle;
    private String jobCode;
    private String jobDesc;

    @OneToOne
    private SalaryGrade salaryGrades;
    @OneToOne
    private Classifications classications;
    @OneToOne
    private Cadres cadres;



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
        if (!(object instanceof Jobs)) {
            return false;
        }
        Jobs other = (Jobs) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hashthrims.domain.jobs.Jobs[id=" + id + "]";
    }
    
    /**
     * @return the job_tittle
     */
    public String getJob_tittle() {
        return jobTittle;
    }

    /**
     * @param job_tittle the job_tittle to set
     */
    public void setJob_tittle(String job_tittle) {
        this.jobTittle = job_tittle;
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

    /**
     * @return the 
     */
    public String getJob_desc() {
        return jobDesc;
    }

    /**
     * @param  the  to set
     */
    public void setJob_desc(String job_desc) {
        this.jobDesc = job_desc;
    }

    /**
     * @return the salaryGrades
     */
    public SalaryGrade getSalaryGrades() {
        return salaryGrades;
    }

    /**
     * @param salaryGrades the salaryGrades to set
     */
    public void setSalaryGrades(SalaryGrade salaryGrades) {
        this.salaryGrades = salaryGrades;
    }

    /**
     * @return the 
     */
    public Classifications getClassications() {
        return classications;
    }

    /**
     * @param classications the  to set
     */
    public void setClassications(Classifications classications) {
        this.classications = classications;
    }

    /**
     * @return the 
     */
    public Cadres getCadres() {
        return cadres;
    }

    /**
     * @param cadres the  to set
     */
    public void setCadres(Cadres cadres) {
        this.cadres = cadres;
    }

    @Override
    public int compareTo(Jobs o) {
        return jobTittle.compareTo(o.jobTittle);
    }

    

}
