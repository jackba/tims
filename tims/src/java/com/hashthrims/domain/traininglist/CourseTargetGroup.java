/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.domain.traininglist;

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
public class CourseTargetGroup implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String targetGroup;
    private Long targerGroupId;

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
        if (!(object instanceof CourseTargetGroup)) {
            return false;
        }
        CourseTargetGroup other = (CourseTargetGroup) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hashthrims.domain.traininglist.CourseTargetGroup[ id=" + id + " ]";
    }

    /**
     * @return the targetGroup
     */
    public String getTargetGroup() {
        return targetGroup;
    }

    /**
     * @param targetGroup the targetGroup to set
     */
    public void setTargetGroup(String targetGroup) {
        this.targetGroup = targetGroup;
    }

    /**
     * @return the targerGroupId
     */
    public Long getTargerGroupId() {
        return targerGroupId;
    }

    /**
     * @param targerGroupId the targerGroupId to set
     */
    public void setTargerGroupId(Long targerGroupId) {
        this.targerGroupId = targerGroupId;
    }
    
}
