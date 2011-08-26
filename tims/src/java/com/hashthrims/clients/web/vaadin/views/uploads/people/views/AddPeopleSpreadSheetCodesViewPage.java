/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.uploads.people.views;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.domain.employeelist.GenderList;
import com.hashthrims.domain.employeelist.MentorsRoles;
import com.hashthrims.domain.employeelist.RaceList;
import com.hashthrims.domain.traininglist.MentoringField;
import com.hashthrims.domain.traininglist.TrainingCourseCategory;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import java.util.List;

/**
 *
 * @author bulelani
 */
public class AddPeopleSpreadSheetCodesViewPage extends VerticalLayout {

    private HashThrimsMain main;
    private VerticalLayout courseTypeLayout = new VerticalLayout();
    private static ClientDataService data = new ClientDataService();
    final Button excelExportButton = new Button("Export to Excel");

    public AddPeopleSpreadSheetCodesViewPage(HashThrimsMain app) {
        main = app;
        setSizeFull();


        List<GenderList> genderLists = data.getGenderListService().findAll();
        List<RaceList> races = data.getRaceListService().findAll();
        List<MentorsRoles> roles = data.getMentorsRolesService().findAll();
        List<MentoringField> mentoringArea = data.getMentoringFieldService().findAll();
        List<TrainingCourseCategory> expertise = data.getTrainingCourseCategoryService().findAll();

        final Table genderListTable = getGenderListTable(genderLists);
        final Table raceListTable = getRacesListTable(races);
        final Table mentoringAreaTable = getMentoringAreaTable(mentoringArea);
        final Table rolesListTable = getRolesListTable(roles);
        final Table expertiseTable = getExpertiseListTable(expertise);

        courseTypeLayout.addComponent(genderListTable);
        courseTypeLayout.addComponent(new Label("<hr/>", Label.CONTENT_XHTML));
        courseTypeLayout.addComponent(raceListTable);
        courseTypeLayout.addComponent(new Label("<hr/>", Label.CONTENT_XHTML));
        courseTypeLayout.addComponent(mentoringAreaTable);
        courseTypeLayout.addComponent(new Label("<hr/>", Label.CONTENT_XHTML));
        courseTypeLayout.addComponent(rolesListTable);
        courseTypeLayout.addComponent(new Label("<hr/>", Label.CONTENT_XHTML));
        courseTypeLayout.addComponent(expertiseTable);
        courseTypeLayout.addComponent(new Label("<hr/>", Label.CONTENT_XHTML));

        //courseTypeLayout.addComponent(excelExportButton);

        addComponent(courseTypeLayout);


    }


    private Table getGenderListTable(List<GenderList> genderLists) {
        Table table = new Table();
        table.setSizeFull();
        table.setHeight(100, UNITS_PIXELS);
        table.addContainerProperty("Gender Name", String.class, null);
        table.addContainerProperty("Spread Sheet Code", Long.class, null);
        for (GenderList competencyType : genderLists) {
            table.addItem(new Object[]{competencyType.getGender(), competencyType.getId()}, competencyType.getId());
        }
        return table;
    }

    private Table getRacesListTable(List<RaceList> races) {
        Table table = new Table();
        table.setSizeFull();
        table.setHeight(100, UNITS_PIXELS);
        table.addContainerProperty("Race Name", String.class, null);
        table.addContainerProperty("Spread Sheet Code", Long.class, null);
        for (RaceList competencyType : races) {
            table.addItem(new Object[]{competencyType.getRaceName(), competencyType.getId()}, competencyType.getId());
        }
        return table;
    }

    private Table getMentoringAreaTable(List<MentoringField> mentoringArea) {
        Table table = new Table();
        table.setSizeFull();
        table.setHeight(100, UNITS_PIXELS);
        table.addContainerProperty("Mentoring Area Name", String.class, null);
        table.addContainerProperty("Spread Sheet Code", Long.class, null);
        for (MentoringField competencyType : mentoringArea) {
            table.addItem(new Object[]{competencyType.getFieldName(), competencyType.getId()}, competencyType.getId());
        }
        return table;
    }

    private Table getRolesListTable(List<MentorsRoles> roles) {
        Table table = new Table();
        table.setSizeFull();
        table.setHeight(100, UNITS_PIXELS);
        table.addContainerProperty("Role Name", String.class, null);
        table.addContainerProperty("Spread Sheet Code", Long.class, null);
        for (MentorsRoles competencyType : roles) {
            table.addItem(new Object[]{competencyType.getMentorsRolesName(), competencyType.getId()}, competencyType.getId());
        }
        return table;
    }

    private Table getExpertiseListTable(List<TrainingCourseCategory> expertise) {
        Table table = new Table();
        table.setSizeFull();
        table.setHeight(100, UNITS_PIXELS);
        table.addContainerProperty("Area of Expertise Name", String.class, null);
        table.addContainerProperty("Spread Sheet Code", Long.class, null);
        for (TrainingCourseCategory competencyType : expertise) {
            table.addItem(new Object[]{competencyType.getCategoryName(), competencyType.getId()}, competencyType.getId());
        }
        return table;
    }
}
