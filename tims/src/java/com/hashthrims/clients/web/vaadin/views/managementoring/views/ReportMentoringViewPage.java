/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.managementoring.views;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.managementoring.ManageMentoringMenuView;
import com.hashthrims.clients.web.vaadin.views.managementoring.views.windows.actionplans.views.NewActionPlanWindow;
import com.hashthrims.clients.web.vaadin.views.managementoring.views.windows.actionplans.views.ReviewActionPlanWindow;
import com.hashthrims.domain.EmployeeMentoring;
import com.hashthrims.domain.Person;
import com.hashthrims.domain.employeelist.CompetencyList;
import com.hashthrims.domain.offices.Facility;
import com.hashthrims.domain.offices.FacilityGrouping;
import com.hashthrims.domain.traininglist.MentoringCompetencies;
import com.hashthrims.domain.traininglist.MentoringSession;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.ListSelect;
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
public class ReportMentoringViewPage extends VerticalLayout implements ValueChangeListener, Button.ClickListener {

    private final ClientDataService data = new ClientDataService();
    private final Table mentoringSessions = new Table();
    private final HorizontalLayout clusterLabel = new HorizontalLayout();
    private final HorizontalLayout twinColumn = new HorizontalLayout();
    private final HorizontalLayout footer = new HorizontalLayout();
    private final HorizontalLayout menteesLayout = new HorizontalLayout();
    private final Button addCompetenciesButton = new Button("Report Mentoring ");
    private final ListSelect competencies = new ListSelect("Select Mentoring Competencies");
    private static String clusterName;
    private NewActionPlanWindow actionPlanSubWindow;
    private ReviewActionPlanWindow reviewActionPlanWindow;
    private final HashThrimsMain main;
    private Long mentoringSessionId;
    private Collection<Long> competenciesId;

    public ReportMentoringViewPage(HashThrimsMain app) {
        main = app;
        setSizeFull();
        clusterLabel.setSizeFull();
        twinColumn.setSizeFull();
        footer.setSizeFull();
        addCompetenciesButton.setSizeFull();
        menteesLayout.setSizeFull();
        addCompetenciesButton.addListener((ClickListener) this);
        competencies.addListener((ValueChangeListener) this);
        updateList(competencies);
        List<MentoringSession> scheduledSessions = data.getMentoringSessionService().findAll();
        setUpTable(mentoringSessions, scheduledSessions);
        addComponent(mentoringSessions);
        addComponent(clusterLabel);
        addComponent(menteesLayout);
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
            mentoringSessionId = sessionId;
            clusterLabel.removeAllComponents();
            footer.removeAllComponents();
            setTwinColumnAndClusterName(property.getValue());
            Panel panel = getPanel();
            clusterLabel.addComponent(panel);
            footer.addComponent(addCompetenciesButton);
            Table mt = getMenteesTable(sessionId);
            menteesLayout.removeAllComponents();
            menteesLayout.addComponent(mt);
        }

        if (property == competencies) {
            competenciesId = (Collection<Long>) property.getValue();
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

    private void updateList(ListSelect competencies) {
        competencies.setSizeFull();
        List<CompetencyList> comps = data.getCompetencyList().findAll();
        for (CompetencyList comp : comps) {
            competencies.setNewItemsAllowed(false);
            competencies.setMultiSelect(true);
            competencies.addListener((ValueChangeListener) this);
            competencies.setNullSelectionAllowed(false);
            competencies.addItem(comp.getId());
            competencies.setItemCaption(comp.getId(), comp.getComp_name());
            twinColumn.addComponent(competencies);
        }
    }

    @Override
    public void buttonClick(ClickEvent event) {
        final Button source = event.getButton();
        if (source == addCompetenciesButton) {
            MentoringSession session = data.getMentoringSessionService().find(mentoringSessionId);
            for (Long competencyListId : competenciesId) {
                CompetencyList competency = data.getCompetencyList().find(competencyListId);
                MentoringCompetencies mentoringSession = new MentoringCompetencies();
                mentoringSession.setCompetencyId(competencyListId);
                mentoringSession.setCompetencyName(competency.getComp_name());
                session.getMentoringCompetencies().add(mentoringSession);
            }
            data.getMentoringSessionService().merge(session);
            main.mainView.setSecondComponent(new ManageMentoringMenuView(main, "REPORT"));
        }
    }

    private Table getMenteesTable(final Long mentoringSessionId) {
        Table table = new Table();
        table.setSizeFull();
        table.addContainerProperty("Surname", String.class, null);
        table.addContainerProperty("First Name", String.class, null);
        table.addContainerProperty("Number of Action Plans", Integer.class, null);
        table.addContainerProperty("Review Action Plan", Button.class, null);
        table.addContainerProperty("Add New Action Plan", Button.class, null);
        Set<Person> sessionMentees = new HashSet<Person>();
        List<Person> people = data.getPersonService().findAll();
        for (Person person : people) {
            List<EmployeeMentoring> mentoring = person.getMentoring();

            for (EmployeeMentoring employeeMentoring : mentoring) {
                if (hasMentoringSession(employeeMentoring, mentoringSessionId)) {
                    sessionMentees.add(person);

                }
            }
        }
        for (final Person person : sessionMentees) {
            Button actionPlanLink = new Button("New Action Plan", new Button.ClickListener() {

                @Override
                public void buttonClick(ClickEvent event) {
                    actionPlanSubWindow = new NewActionPlanWindow(person, mentoringSessionId, main);
                    main.getMainWindow().addWindow(actionPlanSubWindow);
                }
            });
            Button revieActionPlan = new Button("Review Action Plan", new Button.ClickListener() {

                @Override
                public void buttonClick(ClickEvent event) {
                    reviewActionPlanWindow = new ReviewActionPlanWindow(person, mentoringSessionId, main);
                    main.getMainWindow().addWindow(reviewActionPlanWindow);
                }
            });

            revieActionPlan.setStyleName(Reindeer.BUTTON_LINK);
            actionPlanLink.setStyleName(Reindeer.BUTTON_LINK);
            table.addItem(new Object[]{person.getPersonName(),
                        person.getPersonSurname(),
                        person.getActionPlans().size(),
                        revieActionPlan,
                        actionPlanLink
                    }, person.getId());
        }

        return table;

    }

    private boolean hasMentoringSession(EmployeeMentoring employeeMentoring, Long mentoringSessionId) {
        boolean hasMentoringSession = false;
        if (employeeMentoring != null) {
            return checkMentoringSession(employeeMentoring.getMentoringSession(), mentoringSessionId);
        }
        return hasMentoringSession;
    }

    private boolean checkMentoringSession(MentoringSession mentoringSession, Long mentoringSessionId) {
        boolean inSession = false;

        if (mentoringSession != null) {
            if (mentoringSession.getId().equals(mentoringSessionId)) {
                inSession = true;
            }
        }
        return inSession;
    }
}
