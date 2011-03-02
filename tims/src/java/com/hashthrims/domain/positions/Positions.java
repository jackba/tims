/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.domain.positions;

import com.hashthrims.domain.jobs.Jobs;
import com.hashthrims.domain.offices.Department;
import com.hashthrims.domain.offices.Facility;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author boniface
 */
@Entity
public class Positions implements Serializable,Comparable<Positions> {

    private static long serialVersionUID = 1L;

    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * @param aSerialVersionUID the serialVersionUID to set
     */
    public static void setSerialVersionUID(long aSerialVersionUID) {
        serialVersionUID = aSerialVersionUID;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String positionCode;
    @OneToOne
    private PositionTypes positionType;
    @OneToOne
    private Status positionStatus;
    private String positionComments;
    @OneToOne
    private Facility facililty;
    @OneToMany
    @JoinColumn(name = "position_id")
    private List<Positions> subodinates;
    @ManyToOne
    private Positions supervisor;
    @OneToOne
    private Department department;
    @OneToOne
    private Jobs job;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Positions)) {
            return false;
        }
        Positions other = (Positions) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hashthrims.domain.positions.Positions[id=" + getId() + "]";
    }

    /**
     * @return the job
     */
    public Jobs getJob() {
        return job;
    }

    /**
     * @param job the job to set
     */
    public void setJob(Jobs job) {
        this.job = job;
    }

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
     * @return the positionComments
     */
    public String getPositionComments() {
        return positionComments;
    }

    /**
     * @param positionComments the positionComments to set
     */
    public void setPositionComments(String positionComments) {
        this.positionComments = positionComments;
    }

   
    /**
     * @return the facililty
     */
    public Facility getFacililty() {
        return facililty;
    }

    /**
     * @param facililty the facililty to set
     */
    public void setFacililty(Facility facililty) {
        this.facililty = facililty;
    }

    /**
     * @return the department
     */
    public Department getDepartment() {
        return department;
    }

    /**
     * @param department the department to set
     */
    public void setDepartment(Department department) {
        this.department = department;
    }


   
    /**
     * @return the positionStatus
     */
    public Status getPositionStatus() {
        return positionStatus;
    }

    /**
     * @param positionStatus the positionStatus to set
     */
    public void setPositionStatus(Status positionStatus) {
        this.positionStatus = positionStatus;
    }

 

    /**
     * @return the positionType
     */
    public PositionTypes getPositionType() {
        return positionType;
    }

    /**
     * @param positionType the positionType to set
     */
    public void setPositionType(PositionTypes positionType) {
        this.positionType = positionType;
    }

    /**
     * @return the subodinates
     */
    public List<Positions> getSubodinates() {
        return subodinates;
    }

    /**
     * @param subodinates the subodinates to set
     */
    public void setSubodinates(List<Positions> subodinates) {
        this.subodinates = subodinates;
    }

    /**
     * @return the supervisor
     */
    public Positions getSupervisor() {
        return supervisor;
    }

    /**
     * @param supervisor the supervisor to set
     */
    public void setSupervisor(Positions supervisor) {
        this.supervisor = supervisor;
    }

    @Override
    public int compareTo(Positions o) {
        return positionCode.compareTo(o.positionCode);
    }

}
