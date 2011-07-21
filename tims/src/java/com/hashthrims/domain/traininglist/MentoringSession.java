/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.domain.traininglist;

import com.hashthrims.domain.offices.Facility;
import com.hashthrims.domain.positions.Status;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author boniface
 */
@Entity
public class MentoringSession implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String sessionName;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date sessionDate;
    @OneToMany(orphanRemoval = true, cascade = {javax.persistence.CascadeType.ALL})
    @JoinColumn(name = "mentoringSession_id")
    private List<MentoringMentors> mentoringMentors = new ArrayList<MentoringMentors>();
    @OneToMany(orphanRemoval = true, cascade = {javax.persistence.CascadeType.ALL})
    @JoinColumn(name = "mentoringSession_id")
    private List<MentoringSessionType> mentoringSessionType= new ArrayList<MentoringSessionType>();
    @OneToMany(orphanRemoval = true, cascade = {javax.persistence.CascadeType.ALL})
    @JoinColumn(name = "mentoringSession_id")
    private List<MentoringSessionTheme> mentoringSessionTheme = new ArrayList<MentoringSessionTheme>();
    @OneToOne
    private TrainingInstitution institutionName;
    @OneToOne
    private Status sessionStatus;
    private String mentoringNotes;
    @OneToMany(orphanRemoval = true, cascade = {javax.persistence.CascadeType.ALL})
    @JoinColumn(name = "mentoringSession_id")
    private List<MentoringFunders> mentoringFunders = new ArrayList<MentoringFunders>();
    @OneToMany(orphanRemoval = true, cascade = {javax.persistence.CascadeType.ALL})
    @JoinColumn(name = "mentoringSession_id")
    private List<MentoringCompetencies> mentoringCompetencies = new ArrayList<MentoringCompetencies>();
    @OneToOne
    private Facility mentoringVenue;
    @OneToMany(orphanRemoval = true, cascade = {javax.persistence.CascadeType.ALL})
    @JoinColumn(name = "mentoringSession_id")
    private List<MentoringSessionObjective> mentoringObjective = new ArrayList<MentoringSessionObjective>();
    private Long MentoringSubjectArea_CompetencyType;
    
    
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
        if (!(object instanceof MentoringSession)) {
            return false;
        }
        MentoringSession other = (MentoringSession) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hashthrims.domain.traininglist.MentoringSession[id=" + id + "]";
    }

    /**
     * @return the sessionName
     */
    public String getSessionName() {
        return sessionName;
    }

    /**
     * @param sessionName the sessionName to set
     */
    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
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
     * @return the sessionStatus
     */
    public Status getSessionStatus() {
        return sessionStatus;
    }

    /**
     * @param sessionStatus the sessionStatus to set
     */
    public void setSessionStatus(Status sessionStatus) {
        this.sessionStatus = sessionStatus;
    }

    /**
     * @return the mentoringNotes
     */
    public String getMentoringNotes() {
        return mentoringNotes;
    }

    /**
     * @param mentoringNotes the mentoringNotes to set
     */
    public void setMentoringNotes(String mentoringNotes) {
        this.mentoringNotes = mentoringNotes;
    }

    /**
     * @return the mentoringFunders
     */
    public List<MentoringFunders> getMentoringFunders() {
        return mentoringFunders;
    }

    /**
     * @param mentoringFunders the mentoringFunders to set
     */
    public void setMentoringFunders(List<MentoringFunders> mentoringFunders) {
        this.mentoringFunders = mentoringFunders;
    }

    /**
     * @return the mentoringCompetencies
     */
    public List<MentoringCompetencies> getMentoringCompetencies() {
        return mentoringCompetencies;
    }

    /**
     * @param mentoringCompetencies the mentoringCompetencies to set
     */
    public void setMentoringCompetencies(List<MentoringCompetencies> mentoringCompetencies) {
        this.mentoringCompetencies = mentoringCompetencies;
    }

    /**
     * @return the mentoringMentors
     */
    public List<MentoringMentors> getMentoringMentors() {
        return mentoringMentors;
    }

    /**
     * @param mentoringMentors the mentoringMentors to set
     */
    public void setMentoringMentors(List<MentoringMentors> mentoringMentors) {
        this.mentoringMentors = mentoringMentors;
    }

    /**
     * @return the sessionDate
     */
    public Date getSessionDate() {
        return sessionDate;
    }

    /**
     * @param sessionDate the sessionDate to set
     */
    public void setSessionDate(Date sessionDate) {
        this.sessionDate = sessionDate;
    }

    /**
     * @return the mentoringVenue
     */
    public Facility getMentoringVenue() {
        return mentoringVenue;
    }

    /**
     * @param mentoringVenue the mentoringVenue to set
     */
    public void setMentoringVenue(Facility mentoringVenue) {
        this.mentoringVenue = mentoringVenue;
    }

   

    /**
     * @return the mentoringSessionType
     */
    public List<MentoringSessionType> getMentoringSessionType() {
        return mentoringSessionType;
    }

    /**
     * @param mentoringSessionType the mentoringSessionType to set
     */
    public void setMentoringSessionType(List<MentoringSessionType> mentoringSessionType) {
        this.mentoringSessionType = mentoringSessionType;
    }

    

    /**
     * @return the MentoringSubjectArea_CompetencyType
     */
    public Long getMentoringSubjectArea_CompetencyType() {
        return MentoringSubjectArea_CompetencyType;
    }

    /**
     * @param MentoringSubjectArea_CompetencyType the MentoringSubjectArea_CompetencyType to set
     */
    public void setMentoringSubjectArea_CompetencyType(Long MentoringSubjectArea_CompetencyType) {
        this.MentoringSubjectArea_CompetencyType = MentoringSubjectArea_CompetencyType;
    }

    /**
     * @return the mentoringObjective
     */
    public List<MentoringSessionObjective> getMentoringObjective() {
        return mentoringObjective;
    }

    /**
     * @param mentoringObjective the mentoringObjective to set
     */
    public void setMentoringObjective(List<MentoringSessionObjective> mentoringObjective) {
        this.mentoringObjective = mentoringObjective;
    }

    /**
     * @return the mentoringSessionTheme
     */
    public List<MentoringSessionTheme> getMentoringSessionTheme() {
        return mentoringSessionTheme;
    }

    /**
     * @param mentoringSessionTheme the mentoringSessionTheme to set
     */
    public void setMentoringSessionTheme(List<MentoringSessionTheme> mentoringSessionTheme) {
        this.mentoringSessionTheme = mentoringSessionTheme;
    }
}
