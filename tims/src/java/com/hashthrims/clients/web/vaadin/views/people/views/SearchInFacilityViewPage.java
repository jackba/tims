/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.people.views;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.people.forms.SearchPersonInFacilityForm;
import com.hashthrims.clients.web.vaadin.views.positions.ManagePositionsMenuView;
import com.hashthrims.clients.web.vaadin.views.positions.model.SalarySourcesBean;
import com.hashthrims.clients.web.vaadin.views.positions.table.SalarySourcesTable;

import com.hashthrims.domain.positions.SalarySources;
import com.hashthrims.infrastructure.factories.positions.PositionsFactory;

import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Form;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author boniface
 */
public class SearchInFacilityViewPage extends VerticalLayout implements
        ClickListener, ValueChangeListener {

    private HashThrimsMain main;
    private Form form;
    private SearchPersonInFacilityForm pform;
      private static ClientDataService data = new ClientDataService();
    private SalarySourcesTable table;

    public SearchInFacilityViewPage(HashThrimsMain app) {
        main = app;
        setSizeFull();
        pform = new SearchPersonInFacilityForm ();
        form = pform.createSearchPersonInFacilityForm();

        // Add Listeners
   

        SalarySourcesBean bean = new SalarySourcesBean();
        BeanItem item = new BeanItem(bean);
        form.setItemDataSource(item);
        

        addComponent(form);
        setComponentAlignment(form, Alignment.TOP_CENTER);
        table = new SalarySourcesTable(main);
        table.addListener((ValueChangeListener) this);
        addComponent(table);

    }

    @Override
    public void valueChange(ValueChangeEvent event) {
        Property property = event.getProperty();

        if (property == table) {
            Item record = table.getItem(table.getValue());
            SalarySourcesBean salSources = new SalarySourcesBean();
            salSources.setSalarySourcesName(record.getItemProperty("Salary Source Name").toString());
            salSources.setSalarySourcesId(new Long(table.getValue().toString()));

            if (salSources != form.getItemDataSource()) {
                BeanItem item = new BeanItem(salSources);
                form.setItemDataSource(item);
                // BUG enabling this Disables form.setVisibleItemProperties(cf.orderList());

                form.setReadOnly(true);
                //Buttons Behaviou
                ;
            }

        }
    }

    @Override
    public void buttonClick(ClickEvent event) {
        final Button source = event.getButton();
        
            main.mainView.setSecondComponent(new ManagePositionsMenuView(main, "SOURCE"));

       

    }

    public void saveNewSalarySource(Form form) {
        PositionsFactory factory = data.getPositionFactory();
        String salarySourceName = form.getField("salarySourcesName").getValue().toString();
        SalarySources s = factory.createSalarySource(salarySourceName);
        data.getSalarySourcesService().persist(s);
    }

    public void saveEditedSalarySource(Form form) {
        PositionsFactory factory = data.getPositionFactory();
        String salarySourceName = form.getField("salarySourcesName").getValue().toString();
        Long salarySourceId = Long.parseLong(form.getField("salarySourcesId").getValue().toString());
        SalarySources s = factory.updateSalarySource(salarySourceName, salarySourceId);
        data.getSalarySourcesService().merge(s);
    }

    public void deleteSalarySource(Form form) {
        PositionsFactory factory = data.getPositionFactory();
        Long salarySourceId = Long.parseLong(form.getField("salarySourcesId").getValue().toString());
        SalarySources c = factory.loadSalarySource(salarySourceId);
        data.getSalarySourcesService().remove(c);
    }
}
