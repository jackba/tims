/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.uploads.mentoring.views;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.managetraining.util.PeopleUtil;
import com.hashthrims.domain.Person;
import com.hashthrims.domain.employeelist.CompetencyType;
import com.hashthrims.domain.offices.Facility;
import com.hashthrims.domain.traininglist.CourseTypeName;
import com.hashthrims.domain.traininglist.MentoringAreasList;
import com.hashthrims.domain.traininglist.MentoringObjective;
import com.hashthrims.domain.traininglist.MentoringTheme;
import com.hashthrims.domain.traininglist.SessionType;
import com.hashthrims.domain.traininglist.TrainingFunder;
import com.hashthrims.domain.traininglist.TrainingInstitution;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author bulelani
 */
public class MentoringSpreadSheetCodesViewPage extends VerticalLayout {

    private HashThrimsMain main;
    private VerticalLayout courseTypeLayout = new VerticalLayout();
    private static ClientDataService data = new ClientDataService();
    final Button excelExportButton = new Button("Export to Excel");

    public MentoringSpreadSheetCodesViewPage(HashThrimsMain app) {
        main = app;
        setSizeFull();











        List<TrainingInstitution> institutions = data.getTrainingInstitutionService().findAll();
        List<TrainingFunder> funders = data.getTrainingFunderService().findAll();
        List<MentoringTheme> themes = data.getMentoringThemeService().findAll();
        List<SessionType> mentoringSessionType = data.getMentoringSessionTypeService().findAll();
        List<MentoringAreasList> areasOfStrenthening = data.getMentoringAreasListService().findAll();
        List<MentoringObjective> mentoringObjectives = data.getMentoringObjectiveService().findAll();
        List<Person> mentors = new PeopleUtil().getMentors(data.getPersonService().findAll());
        List<CompetencyType> mentoringSubjectArea = data.getCompetencyTypeService().findAll();

        List<Facility> facilities = data.getFacilityService().findAll();
        Collections.sort(facilities);


        final Table mentorinhgThemesTable = getMentorinhgThemesTable(themes);
        final Table mentoringSessionTypeTable = getMentoringSessionTypeTable(mentoringSessionType);
        final Table areasOfStrentheningTable = getAreasOfStrentheningTable(areasOfStrenthening);
        final Table mentoringObjectivesTable = getMentoringObjectivesTable(mentoringObjectives);
        final Table mentorsTable = getMentorsTable(mentors);


        final Table facilitiesTable = getFacilitiesTable(facilities);

        final Table mentoringSubjectAreaTable = getMentoringSubjectAreaTable(mentoringSubjectArea);

        final Table institutionsTable = getInstitutionsTable(institutions);
        final Table fundersTable = getFundersTable(funders);

        courseTypeLayout.addComponent(mentorinhgThemesTable);
        courseTypeLayout.addComponent(new Label("<hr/>", Label.CONTENT_XHTML));
        courseTypeLayout.addComponent(mentoringSessionTypeTable);
        courseTypeLayout.addComponent(new Label("<hr/>", Label.CONTENT_XHTML));
        courseTypeLayout.addComponent(institutionsTable);
        courseTypeLayout.addComponent(new Label("<hr/>", Label.CONTENT_XHTML));
        courseTypeLayout.addComponent(fundersTable);
        courseTypeLayout.addComponent(new Label("<hr/>", Label.CONTENT_XHTML));
        courseTypeLayout.addComponent(areasOfStrentheningTable);
        courseTypeLayout.addComponent(new Label("<hr/>", Label.CONTENT_XHTML));
        courseTypeLayout.addComponent(mentoringObjectivesTable);
        courseTypeLayout.addComponent(new Label("<hr/>", Label.CONTENT_XHTML));
        courseTypeLayout.addComponent(mentorsTable);
        courseTypeLayout.addComponent(new Label("<hr/>", Label.CONTENT_XHTML));
        courseTypeLayout.addComponent(mentoringSubjectAreaTable);
        courseTypeLayout.addComponent(new Label("<hr/>", Label.CONTENT_XHTML));
        courseTypeLayout.addComponent(facilitiesTable);
        courseTypeLayout.addComponent(new Label("<hr/>", Label.CONTENT_XHTML));



        //courseTypeLayout.addComponent(excelExportButton);

        addComponent(courseTypeLayout);


    }

    private Table getMentorinhgThemesTable(List<MentoringTheme> lists) {
        Table table = new Table();
        table.setSizeFull();
        table.setHeight(100, UNITS_PIXELS);
        table.addContainerProperty("Mentoring Theme", String.class, null);
        table.addContainerProperty("Spread Sheet Code", Long.class, null);
        for (MentoringTheme value : lists) {
            table.addItem(new Object[]{value.getMentoringTheme(), value.getId()}, value.getId());
        }
        return table;
    }

    private Table getMentoringSessionTypeTable(List<SessionType> lists) {
        Table table = new Table();
        table.setSizeFull();
        table.setHeight(100, UNITS_PIXELS);
        table.addContainerProperty("Session Type", String.class, null);
        table.addContainerProperty("Spread Sheet Code", Long.class, null);
        for (SessionType value : lists) {
            table.addItem(new Object[]{value.getSessionTypeName(), value.getId()}, value.getId());
        }
        return table;
    }

    private Table getAreasOfStrentheningTable(List<MentoringAreasList> lists) {
        Table table = new Table();
        table.setSizeFull();
        table.setHeight(100, UNITS_PIXELS);
        table.addContainerProperty("Mentoring Areas", String.class, null);
        table.addContainerProperty("Spread Sheet Code", Long.class, null);
        for (MentoringAreasList value : lists) {
            table.addItem(new Object[]{value.getAreasofStrenthening(), value.getId()}, value.getId());
        }
        return table;
    }

    private Table getMentoringObjectivesTable(List<MentoringObjective> lists) {
        Table table = new Table();
        table.setSizeFull();
        table.setHeight(100, UNITS_PIXELS);
        table.addContainerProperty("Mentoring Objective", String.class, null);
        table.addContainerProperty("Spread Sheet Code", Long.class, null);
        for (MentoringObjective value : lists) {
            table.addItem(new Object[]{value.getMentoringObjective(), value.getId()}, value.getId());
        }
        return table;
    }

    private Table getMentorsTable(List<Person> lists) {
        Table table = new Table();
        table.setSizeFull();
        table.setHeight(100, UNITS_PIXELS);
        table.addContainerProperty("Mentors Names", String.class, null);
        table.addContainerProperty("Spread Sheet Code", Long.class, null);
        for (Person value : lists) {
            table.addItem(new Object[]{value.getPersonSurname() + " " + value.getPersonName(), value.getId()}, value.getId());
        }
        return table;
    }

    private Table getMentoringSubjectAreaTable(List<CompetencyType> lists) {
        Table table = new Table();
        table.setSizeFull();
        table.setHeight(100, UNITS_PIXELS);
        table.addContainerProperty("Mentoring Subject Areas", String.class, null);
        table.addContainerProperty("Spread Sheet Code", Long.class, null);
        for (CompetencyType value : lists) {
            table.addItem(new Object[]{value.getComp_name_typ(), value.getId()}, value.getId());
        }
        return table;
    }

    private Table getFacilitiesTable(List<Facility> lists) {
        Table table = new Table();
        table.setSizeFull();
        table.setHeight(100, UNITS_PIXELS);
        table.addContainerProperty("Mentoring Venue", String.class, null);
        table.addContainerProperty("Spread Sheet Code", Long.class, null);
        for (Facility value : lists) {
            table.addItem(new Object[]{value.getFacilityName(), value.getId()}, value.getId());
        }
        return table;
    }

    private Table getInstitutionsTable(List<TrainingInstitution> lists) {
        Table table = new Table();
        table.setSizeFull();
        table.setHeight(100, UNITS_PIXELS);
        table.addContainerProperty("Training Institution", String.class, null);
        table.addContainerProperty("Spread Sheet Code", Long.class, null);
        for (TrainingInstitution value : lists) {
            table.addItem(new Object[]{value.getTrainingInstitution(), value.getId()}, value.getId());
        }
        return table;
    }

    private Table getFundersTable(List<TrainingFunder> lists) {
        Table table = new Table();
        table.setSizeFull();
        table.setHeight(100, UNITS_PIXELS);
        table.addContainerProperty("Training Funders", String.class, null);
        table.addContainerProperty("Spread Sheet Code", Long.class, null);
        for (TrainingFunder value : lists) {
            table.addItem(new Object[]{value.getTrainingFunderName(), value.getId()}, value.getId());
        }
        return table;
    }
}
