/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.uploads.people.views;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.domain.employeelist.CompetencyType;
import com.hashthrims.domain.jobs.Jobs;
import com.hashthrims.domain.offices.Facility;
import com.hashthrims.domain.positions.Positions;
import com.hashthrims.infrastructure.util.TimsUtil;
import com.vaadin.addon.tableexport.ExcelExport;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bulelani
 */
public class AssignPositionsUploadsViewPage extends VerticalLayout implements
        ValueChangeListener {

    private HashThrimsMain main;
    private VerticalLayout comboLayout = new VerticalLayout();
    private VerticalLayout tableLayout = new VerticalLayout();
    private static ClientDataService data = new ClientDataService();
    final Button excelExportButton = new Button("Export to Excel");
    private final ComboBox facilitiesList = new ComboBox("Select Facility ");
    private List<Positions> positions;
    private final TimsUtil st = new TimsUtil();

    public AssignPositionsUploadsViewPage(HashThrimsMain app) {
        main = app;
        setSizeFull();
        facilitiesList.setWidth("350px");
        facilitiesList.addListener(this);
        facilitiesList.setImmediate(true);
        facilitiesList.setNullSelectionAllowed(true);


        List<Facility> facilities = data.getFacilityService().findAll();
        for (Facility facility : facilities) {
            facilitiesList.setItemCaption(facility.getId(), facility.getFacilityName());
            facilitiesList.addItem(facility.getId());
            

        }



        final Table table = new Table(); //getTable(subjectArea);



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


        comboLayout.addComponent(facilitiesList);
        comboLayout.addComponent(new Label("<hr/>", Label.CONTENT_XHTML));
        addComponent(comboLayout);
        addComponent(tableLayout);




    }

    private Table getTable(List<Positions> possitions) {
        Table table = new Table();
        table.setSizeFull();
        table.addContainerProperty("Position Name", String.class, null);
         table.addContainerProperty("Position Code", String.class, null);
        table.addContainerProperty("Spread Sheet Code", Long.class, null);
        for (Positions position : possitions) {
            table.addItem(new Object[]{ getJobTitle(position.getJob()),position.getPositionCode(), position.getId()}, position.getId());
        }
        return table;
    }

    @Override
    public void valueChange(ValueChangeEvent event) {
        Property property = event.getProperty();
        List<Positions> ps = new ArrayList<Positions>();
        if (property == facilitiesList) {
            tableLayout.removeAllComponents();
            if (property.getValue() != null) {
                Facility facility = data.getFacilityService().find(Long.parseLong(property.getValue().toString()));
                positions = facility.getPositions();
                for (Positions pos : positions) {
                    if (st.checkPositionAvalaibality(pos)) {
                        ps.add(pos);
                    }
                }
                Table t = getTable(ps);
                tableLayout.addComponent(t);

            } else {
                tableLayout.removeAllComponents();
            }
        }

    }

    private String getJobTitle(Jobs job) {
        if(job!=null)
            return job.getJob_tittle();
        return null;
    }
}
