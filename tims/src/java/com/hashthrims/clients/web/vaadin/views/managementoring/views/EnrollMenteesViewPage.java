/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.managementoring.views;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.managementoring.ManageMentoringMenuView;
import com.hashthrims.clients.web.vaadin.views.managementoring.forms.EnrollMenteesForm;
import com.hashthrims.clients.web.vaadin.views.managementoring.model.EnrollMenteesBean;
import com.hashthrims.clients.web.vaadin.views.managetraining.util.PeopleUtil;
import com.hashthrims.domain.EmployeeMentoring;
import com.hashthrims.domain.Person;
import com.hashthrims.domain.offices.Facility;
import com.hashthrims.domain.offices.FacilityGrouping;
import com.hashthrims.domain.traininglist.MentoringSession;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author boniface
 */
public class EnrollMenteesViewPage extends VerticalLayout implements ValueChangeListener, ClickListener {

    private final ClientDataService data = new ClientDataService();
    private final Table mentoringSessions = new Table();
    private final HorizontalLayout clusterLabel = new HorizontalLayout();
    private final HorizontalLayout twinColumn = new HorizontalLayout();
    private final HorizontalLayout footer = new HorizontalLayout();
    private final Button enrollIntoMentoring = new Button("Enroll Into Mentoring Program");
    private static String clusterName;
    private final HashThrimsMain main;
    private final Form form;
    private final EnrollMenteesForm pform;
    private Long sessionMentoringId;

    public EnrollMenteesViewPage(HashThrimsMain app) {


        main = app;

        pform = new EnrollMenteesForm();

        form = pform.createEnrollMenteesForm();

        setSizeFull();
        clusterLabel.setSizeFull();
        twinColumn.setSizeFull();
        enrollIntoMentoring.addListener((ClickListener) this);

        footer.setSizeFull();
        enrollIntoMentoring.setSizeFull();
        List<MentoringSession> scheduledSessions = data.getMentoringSessionService().findAll();
        setUpTable(mentoringSessions, scheduledSessions);
        addComponent(mentoringSessions);

        addComponent(clusterLabel);

        form.setSizeFull();
        addComponent(form);
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
                        mentoringSession.getStartDate(),
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
            sessionMentoringId = sessionId;
            clusterLabel.removeAllComponents();
            footer.removeAllComponents();
            setTwinColumnAndClusterName(property.getValue());
            Panel panel = getPanel();
            clusterLabel.addComponent(panel);
            footer.addComponent(enrollIntoMentoring);
            EnrollMenteesBean bean = getMenteesBean(sessionId);
            BeanItem item = new BeanItem(bean);
            form.setItemDataSource(item);
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

    private EnrollMenteesBean getMenteesBean(Long sessionId) {

        EnrollMenteesBean bean = new EnrollMenteesBean();
        Set<Long> mentessId = new HashSet<Long>();
        List<Person> menteesInCluster = new PeopleUtil().getClusterPeopleWithPendingActionPlan(sessionId);
        for (Person person : menteesInCluster) {
            mentessId.add(person.getId());
        }
        bean.setMenteesId(mentessId);
        return bean;

    }

    @Override
    public void buttonClick(ClickEvent event) {
        final Button source = event.getButton();
        if (source == enrollIntoMentoring) {
            enrollMentees(sessionMentoringId, pform.getMentees());
            main.mainView.setSecondComponent(new ManageMentoringMenuView(main, "ENROLL"));
        }
    }

    private void enrollMentees(Long sessionMentoringId, Collection<Long> mentees) {
        MentoringSession mentoringSession = data.getMentoringSessionService().find(sessionMentoringId);
        EmployeeMentoring employeementoring = new EmployeeMentoring();
        employeementoring.setMentoringSession(mentoringSession);
        for (Long menteeId : mentees) {
            Person p = data.getPersonService().find(menteeId);
            p.getMentoring().add(employeementoring);
            data.getPersonService().merge(p);
        }
    }
}
