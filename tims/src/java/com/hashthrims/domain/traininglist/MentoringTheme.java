/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.domain.traininglist;

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
public class MentoringTheme implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String mentoringTheme;
    @OneToMany(orphanRemoval = true, cascade = {javax.persistence.CascadeType.ALL})
    @JoinColumn(name = "mentoringtheme_id")
    private List<MentoringSession> mentoringSession;
    @OneToOne
    private MentoringField mentoringField;

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
        if (!(object instanceof MentoringTheme)) {
            return false;
        }
        MentoringTheme other = (MentoringTheme) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hashthrims.domain.traininglist.MentoringTheme[id=" + id + "]";
    }

    /**
     * @return the mentoringTheme
     */
    public String getMentoringTheme() {
        return mentoringTheme;
    }

    /**
     * @param mentoringTheme the mentoringTheme to set
     */
    public void setMentoringTheme(String mentoringTheme) {
        this.mentoringTheme = mentoringTheme;
    }

    /**
     * @return the mentoringField
     */
    public MentoringField getMentoringField() {
        return mentoringField;
    }

    /**
     * @param mentoringField the mentoringField to set
     */
    public void setMentoringField(MentoringField mentoringField) {
        this.mentoringField = mentoringField;
    }

    /**
     * @return the mentoringSession
     */
    public List<MentoringSession> getMentoringSession() {
        return mentoringSession;
    }

    /**
     * @param mentoringSession the mentoringSession to set
     */
    public void setMentoringSession(List<MentoringSession> mentoringSession) {
        this.mentoringSession = mentoringSession;
    }
}
