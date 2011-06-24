/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.domain.traininglist;

import com.hashthrims.domain.positions.Status;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author boniface
 */
@Entity
public class TrainingCourses implements Serializable,Comparable<TrainingCourses> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String courseName;
    @OneToOne
    private TrainingCourseCategory courseCategory;
    private String courseTopic;
    @OneToOne
    private TrainingInstitution institutionName;
    @OneToOne
    private Status courseStatus;
    private String courseNotes;
    @OneToOne
    private CourseTypeName courseType;
    @OneToMany(orphanRemoval = true, cascade = {javax.persistence.CascadeType.ALL})
    @JoinColumn(name = "trainingcourses_id")
    private List<CourseFunders> courseFunders = new ArrayList<CourseFunders>();
    @OneToMany(orphanRemoval = true, cascade = {javax.persistence.CascadeType.ALL})
    @JoinColumn(name = "trainingcourses_id")
    private List<CourseCompetencies> courseCompetencies = new ArrayList<CourseCompetencies>();

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
        if (!(object instanceof TrainingCourses)) {
            return false;
        }
        TrainingCourses other = (TrainingCourses) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hashthrims.domain.traininglist.TrainingCourses[id=" + id + "]";
    }

    /**
     * @return the courseName
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * @param courseName the courseName to set
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    /**
     * @return the courseTopic
     */
    public String getCourseTopic() {
        return courseTopic;
    }

    /**
     * @param courseTopic the courseTopic to set
     */
    public void setCourseTopic(String courseTopic) {
        this.courseTopic = courseTopic;
    }

    /**
     * @return the courseNotes
     */
    public String getCourseNotes() {
        return courseNotes;
    }

    /**
     * @param courseNotes the courseNotes to set
     */
    public void setCourseNotes(String courseNotes) {
        this.courseNotes = courseNotes;
    }

    /**
     * @return the courseCategory
     */
    public TrainingCourseCategory getCourseCategory() {
        return courseCategory;
    }

    /**
     * @param courseCategory the courseCategory to set
     */
    public void setCourseCategory(TrainingCourseCategory courseCategory) {
        this.courseCategory = courseCategory;
    }

    /**
     * @return the institutionName
     */
    public TrainingInstitution getInstitutionName() {
        return institutionName;
    }

    /**
     * @param institutionName the institutionName to set
     */
    public void setInstitutionName(TrainingInstitution institutionName) {
        this.institutionName = institutionName;
    }

    /**
     * @return the courseStatus
     */
    public Status getCourseStatus() {
        return courseStatus;
    }

    /**
     * @param courseStatus the courseStatus to set
     */
    public void setCourseStatus(Status courseStatus) {
        this.courseStatus = courseStatus;
    }

    /**
     * @return the courseType
     */
    public CourseTypeName getCourseType() {
        return courseType;
    }

    /**
     * @param courseType the courseType to set
     */
    public void setCourseType(CourseTypeName courseType) {
        this.courseType = courseType;
    }

    /**
     * @return the courseCompetencies
     */
    public List<CourseCompetencies> getCourseCompetencies() {
        return courseCompetencies;
    }

    /**
     * @param courseCompetencies the courseCompetencies to set
     */
    public void setCourseCompetencies(List<CourseCompetencies> courseCompetencies) {
        this.courseCompetencies = courseCompetencies;
    }

    /**
     * @return the courseFunders
     */
    public List<CourseFunders> getCourseFunders() {
        return courseFunders;
    }

    /**
     * @param courseFunders the courseFunders to set
     */
    public void setCourseFunders(List<CourseFunders> courseFunders) {
        this.courseFunders = courseFunders;
    }

    @Override
    public int compareTo(TrainingCourses o) {
       return courseName.compareTo(o.courseName);
    }
}
