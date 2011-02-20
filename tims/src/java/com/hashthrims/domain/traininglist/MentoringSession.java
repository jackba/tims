/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.domain.traininglist;

import com.hashthrims.domain.positions.Status;
import java.io.Serializable;
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
public class MentoringSession implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String sessionName;
    @OneToMany(orphanRemoval = true, cascade = {javax.persistence.CascadeType.ALL})
    @JoinColumn(name = "mentoringSession_id")
    private List<MentoringMentors> mentoringMentors;
    @OneToOne
    private MentoringSessionType  mentoringSessionType;
    @OneToOne
    private MentoringTheme mentoringTheme;
    @OneToOne
    private TrainingInstitution institutionName;
    @OneToOne
    private Status sessionStatus;
    private String mentoringNotes;
    @OneToMany(orphanRemoval = true, cascade = {javax.persistence.CascadeType.ALL})
    @JoinColumn(name = "mentoringSession_id")
    private List<MentoringFunders> mentoringFunders;
    @OneToMany(orphanRemoval = true, cascade = {javax.persistence.CascadeType.ALL})
    @JoinColumn(name = "mentoringSession_id")
    private List<MentoringCompetencies> mentoringCompetencies;


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
     * @return the mentoringTheme
     */
    public MentoringTheme getMentoringTheme() {
        return mentoringTheme;
    }

    /**
     * @param mentoringTheme the mentoringTheme to set
     */
    public void setMentoringTheme(MentoringTheme mentoringTheme) {
        this.mentoringTheme = mentoringTheme;
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
     * @return the mentoringSessionType
     */
    public MentoringSessionType getMentoringSessionType() {
        return mentoringSessionType;
    }

    /**
     * @param mentoringSessionType the mentoringSessionType to set
     */
    public void setMentoringSessionType(MentoringSessionType mentoringSessionType) {
        this.mentoringSessionType = mentoringSessionType;
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

    
}
