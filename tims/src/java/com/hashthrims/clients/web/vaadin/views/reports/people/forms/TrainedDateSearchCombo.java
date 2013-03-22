/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.reports.people.forms;

import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.domain.jobs.Jobs;
import com.hashthrims.domain.traininglist.TrainingCourses;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.VerticalLayout;
import java.text.DateFormat;
import java.util.List;


/**
 *
 * @author boniface
 */
public class TrainedDateSearchCombo extends VerticalLayout {

    private Button searchButton = new Button("Get Report");
    private PopupDateField startDate;
    private PopupDateField endDate;
    private DateFormat dateFormatter = DateFormat.getDateInstance(DateFormat.SHORT);
    private ComboBox positionsCombo = new ComboBox();
    private ComboBox topicCombo = new ComboBox();
    private static ClientDataService data = new ClientDataService();
    private final HorizontalLayout topicLayot = new HorizontalLayout();
    private final HorizontalLayout dateLayot = new HorizontalLayout();

    public TrainedDateSearchCombo() {
        setSizeFull();
        topicLayot.setSizeFull();
        dateLayot.setSizeFull();
        startDate = new PopupDateField();
        endDate = new PopupDateField();
        startDate.setInputPrompt("Start Date");
        endDate.setInputPrompt("End  Date");

        List<Jobs> jobs = data.getJobService().findAll();
        for (Jobs job : jobs) {
            positionsCombo.addItem(job.getId());
            positionsCombo.setItemCaption(job.getId(), job.getJob_tittle());
        }

        List<TrainingCourses> courses = data.getTrainingCoursesService().findAll();
        for (TrainingCourses course : courses) {
            topicCombo.addItem(course.getId());
            topicCombo.setItemCaption(course.getId(), course.getCourseName());
        }

        // Set the correct resolution
        startDate.setResolution(PopupDateField.RESOLUTION_DAY);
        endDate.setResolution(PopupDateField.RESOLUTION_DAY);

        Label startDateLabel = new Label("Start Date: ");
        Label endDateLable = new Label("End Date: ");
        Label positionLabel = new Label("Profesion : ");
        Label topicLabel = new Label("Topic : ");

        topicLayot.addComponent(positionLabel);
        topicLayot.addComponent(positionsCombo);
        topicLayot.addComponent(topicLabel);
        topicLayot.addComponent(topicCombo);


        dateLayot.addComponent(startDateLabel);
        dateLayot.addComponent(startDate);
        dateLayot.addComponent(endDateLable);
        dateLayot.addComponent(endDate);

        dateLayot.addComponent(searchButton);


        addComponent(topicLayot);
        addComponent(new Label("<hr/>", Label.CONTENT_XHTML));
        addComponent(dateLayot);

    }

    /**
     * @return the searchButton
     */
    public Button getSearchButton() {
        return searchButton;
    }

    /**
     * @param searchButton the searchButton to set
     */
    public void setSearchButton(Button searchButton) {
        this.searchButton = searchButton;
    }

    /**
     * @return the startDate
     */
    public PopupDateField getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(PopupDateField startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public PopupDateField getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(PopupDateField endDate) {
        this.endDate = endDate;
    }

    /**
     * @return the positionsCombo
     */
    public ComboBox getPositionsCombo() {
        return positionsCombo;
    }

    /**
     * @param positionsCombo the positionsCombo to set
     */
    public void setPositionsCombo(ComboBox positionsCombo) {
        this.positionsCombo = positionsCombo;
    }

    /**
     * @return the topicCombo
     */
    public ComboBox getTopicCombo() {
        return topicCombo;
    }

    /**
     * @param topicCombo the topicCombo to set
     */
    public void setTopicCombo(ComboBox topicCombo) {
        this.topicCombo = topicCombo;
    }
}
