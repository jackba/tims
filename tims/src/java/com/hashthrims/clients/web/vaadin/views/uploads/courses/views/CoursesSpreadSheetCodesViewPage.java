/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.uploads.courses.views;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.domain.employeelist.CompetencyList;
import com.hashthrims.domain.positions.Status;
import com.hashthrims.domain.traininglist.CourseTypeName;
import com.hashthrims.domain.traininglist.Criteria;
import com.hashthrims.domain.traininglist.TargetGroup;
import com.hashthrims.domain.traininglist.TrainingCourseCategory;
import com.hashthrims.domain.traininglist.TrainingFunder;
import com.hashthrims.domain.traininglist.TrainingInstitution;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import java.util.List;

/**
 *
 * @author bulelani
 */
public class CoursesSpreadSheetCodesViewPage extends VerticalLayout {

    private HashThrimsMain main;
    private VerticalLayout courseTypeLayout = new VerticalLayout();
    private static ClientDataService data = new ClientDataService();
    final Button excelExportButton = new Button("Export to Excel");

    public CoursesSpreadSheetCodesViewPage(HashThrimsMain app) {
        main = app;
        setSizeFull();


        List<CourseTypeName> coursetypes = data.getCourseTypeNameService().findAll();
        List<TrainingCourseCategory> categories = data.getTrainingCourseCategoryService().findAll();
        List<Status> statuses = data.getStatusService().findAll();
        List<TrainingInstitution> institutions = data.getTrainingInstitutionService().findAll();
        List<TrainingFunder> funders = data.getTrainingFunderService().findAll();
        List<CompetencyList> competenciesLists = data.getCompetencyList().findAll();
        List<TargetGroup> targetGroupList = data.getTargetGroupService().findAll();
        List<Criteria> criterias = data.getCriteriaService().findAll();

        final Table courseTypeListTable = getCourseTypeListTable(coursetypes);
        final Table categoriesTable = getCategoriesTable(categories);
        final Table institutionsTable = getInstitutionsTable(institutions);
        final Table fundersTable = getFundersTable(funders);
        final Table competenciesTable = getCompetenciesTable(competenciesLists);
        final Table targetGroupListTable = getTargetGroupListTable(targetGroupList);
        final Table criteriaTable = getCriteriaTable(criterias);
        final Table statusesTable = getStatusesTable(statuses);

        courseTypeLayout.addComponent(courseTypeListTable);
        courseTypeLayout.addComponent(new Label("<hr/>", Label.CONTENT_XHTML));
        courseTypeLayout.addComponent(categoriesTable);
        courseTypeLayout.addComponent(new Label("<hr/>", Label.CONTENT_XHTML));
        courseTypeLayout.addComponent(institutionsTable);
        courseTypeLayout.addComponent(new Label("<hr/>", Label.CONTENT_XHTML));
        courseTypeLayout.addComponent(fundersTable);
        courseTypeLayout.addComponent(new Label("<hr/>", Label.CONTENT_XHTML));
        courseTypeLayout.addComponent(competenciesTable);
        courseTypeLayout.addComponent(new Label("<hr/>", Label.CONTENT_XHTML));
        courseTypeLayout.addComponent(targetGroupListTable);
        courseTypeLayout.addComponent(new Label("<hr/>", Label.CONTENT_XHTML));
        courseTypeLayout.addComponent(criteriaTable);
        courseTypeLayout.addComponent(new Label("<hr/>", Label.CONTENT_XHTML));
        courseTypeLayout.addComponent(statusesTable);
        courseTypeLayout.addComponent(new Label("<hr/>", Label.CONTENT_XHTML));


        //courseTypeLayout.addComponent(excelExportButton);

        addComponent(courseTypeLayout);


    }

    private Table getCourseTypeListTable(List<CourseTypeName> lists) {
        Table table = new Table();
        table.setSizeFull();
        table.setHeight(100, UNITS_PIXELS);
        table.addContainerProperty("Type of Training", String.class, null);
        table.addContainerProperty("Spread Sheet Code", Long.class, null);
        for (CourseTypeName value : lists) {
            table.addItem(new Object[]{value.getCourseType(), value.getId()}, value.getId());
        }
        return table;
    }

    private Table getCategoriesTable(List<TrainingCourseCategory> lists) {
        Table table = new Table();
        table.setSizeFull();
        table.setHeight(100, UNITS_PIXELS);
        table.addContainerProperty("Training Field Name", String.class, null);
        table.addContainerProperty("Spread Sheet Code", Long.class, null);
        for (TrainingCourseCategory value : lists) {
            table.addItem(new Object[]{value.getCategoryName(), value.getId()}, value.getId());
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
        table.addContainerProperty("Funder Name", String.class, null);
        table.addContainerProperty("Spread Sheet Code", Long.class, null);
        for (TrainingFunder value : lists) {
            table.addItem(new Object[]{value.getTrainingFunderName(), value.getId()}, value.getId());
        }
        return table;
    }

    private Table getCompetenciesTable(List<CompetencyList> lists) {
        Table table = new Table();
        table.setSizeFull();
        table.setHeight(100, UNITS_PIXELS);
        table.addContainerProperty("Competency  Name", String.class, null);
        table.addContainerProperty("Spread Sheet Code", Long.class, null);
        for (CompetencyList value : lists) {
            table.addItem(new Object[]{value.getComp_name(), value.getId()}, value.getId());
        }
        return table;
    }

    private Table getTargetGroupListTable(List<TargetGroup> lists) {
        Table table = new Table();
        table.setSizeFull();
        table.setHeight(100, UNITS_PIXELS);
        table.addContainerProperty("Target Group", String.class, null);
        table.addContainerProperty("Spread Sheet Code", Long.class, null);
        for (TargetGroup value : lists) {
            table.addItem(new Object[]{value.getTargetGroupName(), value.getId()}, value.getId());
        }
        return table;
    }

    private Table getCriteriaTable(List<Criteria> lists) {
        Table table = new Table();
        table.setSizeFull();
        table.setHeight(100, UNITS_PIXELS);
        table.addContainerProperty("Criteria", String.class, null);
        table.addContainerProperty("Spread Sheet Code", Long.class, null);
        for (Criteria value : lists) {
            table.addItem(new Object[]{value.getSelectionCriteria(), value.getId()}, value.getId());
        }
        return table;
    }

    private Table getStatusesTable(List<Status> lists) {
        Table table = new Table();
        table.setSizeFull();
        table.setHeight(100, UNITS_PIXELS);
        table.addContainerProperty("Coures Status ", String.class, null);
        table.addContainerProperty("Spread Sheet Code", Long.class, null);
        for (Status value : lists) {
            table.addItem(new Object[]{value.getStatus(), value.getId()}, value.getId());
        }
        return table;
    }
}
