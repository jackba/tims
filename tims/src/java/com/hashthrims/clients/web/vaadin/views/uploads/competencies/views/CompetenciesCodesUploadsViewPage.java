/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.uploads.competencies.views;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.domain.employeelist.CompetencyType;
import com.vaadin.addon.tableexport.ExcelExport;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import java.util.List;

/**
 *
 * @author bulelani
 */
public class CompetenciesCodesUploadsViewPage extends VerticalLayout {

    private HashThrimsMain main;
    private VerticalLayout courseTypeLayout = new VerticalLayout();
    private static ClientDataService data = new ClientDataService();
    final Button excelExportButton = new Button("Export to Excel");

    public CompetenciesCodesUploadsViewPage(HashThrimsMain app) {
        main = app;
        setSizeFull();

        List<CompetencyType> subjectArea = data.getCompetencyTypeService().findAll();

        final Table table = getTable(subjectArea);



        final ThemeResource export = new ThemeResource("../images/table-excel.png");

        excelExportButton.setIcon(export);

        excelExportButton.addListener(new ClickListener() {

            private static final long serialVersionUID = -73954695086117200L;
            private ExcelExport excelExport;

            @Override
            public void buttonClick(final ClickEvent event) {
                excelExport = new ExcelExport(table);
                excelExport.excludeCollapsedColumns();
                excelExport.setReportTitle("Codes For Competency Type");
                excelExport.export();
            }
        });


        courseTypeLayout.addComponent(table);
        courseTypeLayout.addComponent(new Label("<hr/>", Label.CONTENT_XHTML));

        //courseTypeLayout.addComponent(excelExportButton);

        addComponent(courseTypeLayout);


    }

    private Table getTable(List<CompetencyType> subjectAreas) {
        Table table = new Table();
        table.setSizeFull();
        table.addContainerProperty("Subject Area", String.class, null);
        table.addContainerProperty("Spread Sheet Code", Long.class, null);
        for (CompetencyType competencyType : subjectAreas) {
            table.addItem(new Object[]{competencyType.getComp_name_typ(), competencyType.getId()}, competencyType.getId());
        }
        return table;
    }
}
