/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.managementoring.views;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.managetraining.util.PeopleUtil;
import com.hashthrims.domain.Person;
import com.hashthrims.domain.offices.Facility;
import com.hashthrims.domain.offices.FacilityGrouping;
import com.hashthrims.domain.traininglist.MentoringSession;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.TwinColSelect;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;
import java.util.Date;
import java.util.List;

/**
 *
 * @author boniface
 */
public class ReportMentoringViewPage extends VerticalLayout implements ValueChangeListener {

    private final ClientDataService data = new ClientDataService();
    private final Table mentoringSessions = new Table();
    private final HorizontalLayout clusterLabel = new HorizontalLayout();
    private final HorizontalLayout twinColumn = new HorizontalLayout();
    private final HorizontalLayout footer = new HorizontalLayout();
    private final TwinColSelect twinCol = new TwinColSelect();
    private final Button enrollIntoMentoring = new Button("Enroll Into Mentoring Program");
    private static String clusterName;

    public ReportMentoringViewPage(HashThrimsMain main) {
        setSizeFull();
        clusterLabel.setSizeFull();
        twinColumn.setSizeFull();
        footer.setSizeFull();
        enrollIntoMentoring.setSizeFull();
        List<MentoringSession> scheduledSessions = data.getMentoringSessionService().findAll();
        setUpTable(mentoringSessions, scheduledSessions);
        addComponent(mentoringSessions);

        addComponent(clusterLabel);
        addComponent(twinColumn);
        addComponent(footer);


    }

    private void setUpTable(Table table, List<MentoringSession> scheduledSessions) {
        table.setSizeFull();
        table.setSelectable(true);
        table.setImmediate(true);
        table.addListener((ValueChangeListener) this);
        table.addContainerProperty("Session Name", String.class, null);
        table.addContainerProperty("Training Institution", String.class, null);
        table.addContainerProperty("Session Date", Date.class, null);
        table.addContainerProperty("Session Venue", String.class, null);

        for (MentoringSession mentoringSession : scheduledSessions) {

            table.addItem(new Object[]{mentoringSession.getSessionName(),
                        mentoringSession.getInstitutionName().getTrainingInstitution(),
                        mentoringSession.getSessionDate(),
                        mentoringSession.getMentoringVenue().getFacilityName()
                    }, mentoringSession.getId());

        }

    }

    private Panel getPanel() {
        Panel p = new Panel();

        p.setSizeFull();
        final Label label = new Label("The Cluster Name: " + clusterName);
        label.setStyleName(Reindeer.LABEL_H1);
        p.addComponent(label);
        return p;
    }

    @Override
    public void valueChange(ValueChangeEvent event) {
        final Property property = event.getProperty();
        if (property == mentoringSessions) {
            Long sessionId = new Long(property.getValue().toString());
            clusterLabel.removeAllComponents();
            footer.removeAllComponents();
            setTwinColumnAndClusterName(property.getValue());

            TwinColSelect tc = getTwinColum(twinCol, sessionId);
            Panel panel = getPanel();
            clusterLabel.addComponent(panel);
            twinColumn.addComponent(tc);
            footer.addComponent(enrollIntoMentoring);

        }
    }

    /**
     * @return the clusterName
     */
    public String getClusterName() {
        return clusterName;
    }

    /**
     * @param clusterName the clusterName to set
     */
    public void setClusterName(String cn) {
        clusterName = cn;
    }

    private void setTwinColumnAndClusterName(Object value) {
        final MentoringSession c = data.getMentoringSessionService().find(new Long(value.toString()));
        Facility facility = c.getMentoringVenue();

        setTheFAcilityCluster(facility.getFacilityGrouping());
    }

    private void setTheFAcilityCluster(FacilityGrouping facilityGrouping) {
        if (facilityGrouping != null) {

            setClusterName(facilityGrouping.getCluster());
        } else {
            setClusterName("No Cluster Name Set For This Facility");
        }
    }

    private TwinColSelect getTwinColum(TwinColSelect twinCol, Long sessionId) {
        List<Person> menteesInCluster = new PeopleUtil().getClusterPeopleWithPendingActionPlan(sessionId);
        twinCol.setSizeFull();
        
        for (Person person : menteesInCluster) {
            twinCol.addItem(person.getId());
            twinCol.setItemCaption(person.getId(), person.getPersonName()+" "+person.getPersonSurname());  
        }
        twinCol.setRows(7);
        twinCol.setNullSelectionAllowed(true);
        twinCol.setMultiSelect(true);
        twinCol.setImmediate(true);
        twinCol.addListener(this);
        twinCol.setLeftColumnCaption("Mentess with Pending Action Plan");
        twinCol.setRightColumnCaption("Enrolled Mentees");
        return twinCol;
    }
}
