/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.clients.web.vaadin.models;

/**
 *
 * @author stud
 */
public class TrainingFunderBean {
private String nameTrainer;
    private String county;
    private String district;

    /**
     * @return the nameTrainer
     */
    public String getNameTrainer() {
        return nameTrainer;
    }

    /**
     * @param nameTrainer the nameTrainer to set
     */
    public void setNameTrainer(String nameTrainer) {
        this.nameTrainer = nameTrainer;
    }

    /**
     * @return the country
     */
    public String getCounty() {
        return county;
    }

    /**
     * @param coutry the country to set
     */
    public void setCounty(String county) {
        this.county = county;
    }

    /**
     * @return the district
     */
    public String getDistrict() {
        return district;
    }

    /**
     * @param district the district to set
     */
    public void setDistrict(String district) {
        this.district = district;
    }
}
