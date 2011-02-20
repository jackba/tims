/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.domain;

import com.hashthrims.domain.employeelist.CompetencyEvaluation;
import com.hashthrims.domain.traininglist.TrainingCourseRequestors;
import com.hashthrims.domain.traininglist.TrainingCourses;
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
public class EmployeeCourses implements Serializable {
    private static long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateRequested;
    private String retraining;
    private String trainingStatus;
    @OneToOne
    private TrainingCourseRequestors requestor;
    @OneToOne
    private CompetencyEvaluation evaluation;
    @Temporal(value = javax.persistence.TemporalType.DATE)
    private Date lastEvaluated;
    private String competencyName;
    private String competencyNotes;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date courseStartDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date courseEndDate;
    @OneToOne
    private TrainingCourses course;

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
        if (!(object instanceof EmployeeCourses)) {
            return false;
        }
        EmployeeCourses other = (EmployeeCourses) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hashthrims.domain.Competency[id=" + getId() + "]";
    }

    /**
     * @return the competencyName
     */
    public String getCompetencyName() {
        return competencyName;
    }

    /**
     * @param competencyName the competencyName to set
     */
    public void setCompetencyName(String competencyName) {
        this.competencyName = competencyName;
    }

    /**
     * @return the competencyNotes
     */
    public String getCompetencyNotes() {
        return competencyNotes;
    }

    /**
     * @param competencyNotes the competencyNotes to set
     */
    public void setCompetencyNotes(String competencyNotes) {
        this.competencyNotes = competencyNotes;
    }

   


    /**
     * @return the lastEvaluated
     */
    public Date getLastEvaluated() {
        return lastEvaluated;
    }

    /**
     * @param lastEvaluated the lastEvaluated to set
     */
    public void setLastEvaluated(Date lastEvaluated) {
        this.lastEvaluated = lastEvaluated;
    }

    /**
     * @return the dateRequested
     */
    public Date getDateRequested() {
        return dateRequested;
    }

    /**
     * @param dateRequested the dateRequested to set
     */
    public void setDateRequested(Date dateRequested) {
        this.dateRequested = dateRequested;
    }

    /**
     * @return the retraining
     */
    public String getRetraining() {
        return retraining;
    }

    /**
     * @param retraining the retraining to set
     */
    public void setRetraining(String retraining) {
        this.retraining = retraining;
    }

    /**
     * @return the trainingStatus
     */
    public String getTrainingStatus() {
        return trainingStatus;
    }

    /**
     * @param trainingStatus the trainingStatus to set
     */
    public void setTrainingStatus(String trainingStatus) {
        this.trainingStatus = trainingStatus;
    }

    /**
     * @return the requestor
     */
    public TrainingCourseRequestors getRequestor() {
        return requestor;
    }

    /**
     * @param requestor the requestor to set
     */
    public void setRequestor(TrainingCourseRequestors requestor) {
        this.requestor = requestor;
    }

    /**
     * @return the evaluation
     */
    public CompetencyEvaluation getEvaluation() {
        return evaluation;
    }

    /**
     * @param evaluation the evaluation to set
     */
    public void setEvaluation(CompetencyEvaluation evaluation) {
        this.evaluation = evaluation;
    }

    /**
     * @return the courseStartDate
     */
    public Date getCourseStartDate() {
        return courseStartDate;
    }

    /**
     * @param courseStartDate the courseStartDate to set
     */
    public void setCourseStartDate(Date courseStartDate) {
        this.courseStartDate = courseStartDate;
    }

    /**
     * @return the courseEndDate
     */
    public Date getCourseEndDate() {
        return courseEndDate;
    }

    /**
     * @param courseEndDate the courseEndDate to set
     */
    public void setCourseEndDate(Date courseEndDate) {
        this.courseEndDate = courseEndDate;
    }

    /**
     * @return the course
     */
    public TrainingCourses getCourse() {
        return course;
    }

    /**
     * @param course the course to set
     */
    public void setCourse(TrainingCourses course) {
        this.course = course;
    }

   
}
