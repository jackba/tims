/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.domain.traininglist;

import com.hashthrims.domain.employeelist.CompetencyEvaluation;
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
public class TrainingCourseEvaluation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String evaluationName;
    @OneToOne
    private CompetencyEvaluation competeEvaluation;

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
        if (!(object instanceof TrainingCourseEvaluation)) {
            return false;
        }
        TrainingCourseEvaluation other = (TrainingCourseEvaluation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hashthrims.domain.traininglist.TrainingCourseEvaluation[id=" + id + "]";
    }

    /**
     * @return the evaluationName
     */
    public String getEvaluationName() {
        return evaluationName;
    }

    /**
     * @param evaluationName the evaluationName to set
     */
    public void setEvaluationName(String evaluationName) {
        this.evaluationName = evaluationName;
    }

    /**
     * @return the competeEvaluation
     */
    public CompetencyEvaluation getCompeteEvaluation() {
        return competeEvaluation;
    }

    /**
     * @param competeEvaluation the competeEvaluation to set
     */
    public void setCompeteEvaluation(CompetencyEvaluation competeEvaluation) {
        this.competeEvaluation = competeEvaluation;
    }

}
