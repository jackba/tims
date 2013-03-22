/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.reports.people.views;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.reports.people.forms.MentoredDateSearchCombo;
import com.hashthrims.clients.web.vaadin.views.reports.people.forms.TrainedDateSearchCombo;
import com.hashthrims.clients.web.vaadin.views.reports.people.forms.TrainedLocationCombo;
import com.hashthrims.clients.web.vaadin.views.reports.people.tables.PeopleTable;
import com.vaadin.addon.tableexport.ExcelExport;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import java.text.DateFormat;

/**
 *
 * @author boniface
 */
public class MentoredPeopleViewPage extends VerticalLayout implements
        ClickListener, ValueChangeListener {

    private HashThrimsMain main;
    private static ClientDataService data = new ClientDataService();
    private DateFormat dateFormatter = DateFormat.getDateInstance(DateFormat.SHORT);
    private Table table = new Table();
    private final TrainedLocationCombo locationCombo = new TrainedLocationCombo();
    private final MentoredDateSearchCombo dateSearchCombo = new MentoredDateSearchCombo();

    public MentoredPeopleViewPage(HashThrimsMain app) {
        final ThemeResource export = new ThemeResource("../images/table-excel.png");
        final Button excelExportButton = new Button("Export to Excel");
        excelExportButton.setIcon(export);



        main = app;
        table.setSizeFull();
        dateSearchCombo.getSearchButton().addListener((ClickListener) this);
        addComponent(locationCombo);
        addComponent(new Label("<hr/>", Label.CONTENT_XHTML));
        addComponent(dateSearchCombo);
        addComponent(new Label("<hr/>", Label.CONTENT_XHTML));
        addComponent(table);

        excelExportButton.addListener(new ClickListener() {
            private static final long serialVersionUID = -73954695086117200L;
            private ExcelExport excelExport;

            @Override
            public void buttonClick(final ClickEvent event) {
                excelExport = new ExcelExport(table);
                excelExport.excludeCollapsedColumns();
                excelExport.setReportTitle("Report From: " + dateSearchCombo.getStartDate().getValue() + " TO " + dateSearchCombo.getEndDate().getValue());
                excelExport.export();
            }
        });

        addComponent(excelExportButton);

    }

    @Override
    public void buttonClick(ClickEvent event) {
        final Button source = event.getButton();
        if (source == dateSearchCombo.getSearchButton()) {

            if (dateSearchCombo.getStartDate().getValue() == null || dateSearchCombo.getEndDate().getValue() == null) {
                main.mainView.getWindow().showNotification("Start and End Dates ", "Enter Start and End Dates ", Window.Notification.TYPE_ERROR_MESSAGE);
            } else {
                getTableReport(locationCombo, dateSearchCombo);
            }

        }
    }

    @Override
    public void valueChange(ValueChangeEvent event) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void getTableReport(TrainedLocationCombo locationCombo, MentoredDateSearchCombo dateSearchCombo) {
        removeComponent(table);
//        table = new PeopleTable().getTable(locationCombo, dateSearchCombo);
        addComponent(table);
    }
}
