/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.employeelists.competency.views;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.employeelists.EmployeeListMenuView;
import com.hashthrims.clients.web.vaadin.views.employeelists.competency.forms.CompetencyTypeForm;
import com.hashthrims.clients.web.vaadin.views.employeelists.competency.model.CompetencyTypeBean;
import com.hashthrims.clients.web.vaadin.views.employeelists.competency.table.CompetencyTypeTable;
import com.hashthrims.domain.employeelist.CompetencyType;
import com.hashthrims.infrastructure.factories.employeelist.CompetencyTypeFactory;
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
public class CompetencyTypeViewPage extends VerticalLayout implements
        ClickListener, ValueChangeListener {

    private HashThrimsMain main;
    private Form form;
    private CompetencyTypeForm cf;
   private static ClientDataService data = new ClientDataService();
    private CompetencyTypeTable table;

    public CompetencyTypeViewPage(HashThrimsMain app) {
        main = app;
        setSizeFull();
        cf = new CompetencyTypeForm();
        form = cf.createSalaryGradesFrom();

        // Add Listeners
        cf.getSave().addListener((ClickListener) this);
        cf.getEdit().addListener((ClickListener) this);
        cf.getCancel().addListener((ClickListener) this);
        cf.getDelete().addListener((ClickListener) this);
        cf.getUpdate().addListener((ClickListener) this);

        CompetencyTypeBean bean = new CompetencyTypeBean();
        BeanItem item = new BeanItem(bean);
        form.setItemDataSource(item);
        form.setVisibleItemProperties(cf.orderList());

        addComponent(form);
        setComponentAlignment(form, Alignment.TOP_CENTER);
        table = new CompetencyTypeTable(main);
        table.addListener((ValueChangeListener) this);
        addComponent(table);

    }

    @Override
    public void valueChange(ValueChangeEvent event) {
        Property property = event.getProperty();

        if (property == table) {
            Item record = table.getItem(table.getValue());
            CompetencyTypeBean competencyType = new CompetencyTypeBean();

            competencyType.setCompNameType(record.getItemProperty("Subject Area").toString());
            competencyType.setCompNameTypeId(new Long(table.getValue().toString()));

            if (competencyType != form.getItemDataSource()) {
                BeanItem item = new BeanItem(competencyType);
                form.setItemDataSource(item);
                // BUG enabling this Disables form.setVisibleItemProperties(cf.orderList());

                form.setReadOnly(true);
                //Buttons Behaviou
                cf.getSave().setVisible(false);
                cf.getEdit().setVisible(true);
                cf.getCancel().setVisible(true);
                cf.getDelete().setVisible(true);
                cf.getUpdate().setVisible(false);
            }

        }
    }

    @Override
    public void buttonClick(ClickEvent event) {

        final Button source = event.getButton();
        EmployeeListMenuView.compSate = "COMPETENCY TYPE";
        if (source == cf.getSave()) {
            saveNewCompetencyType(form);
            
            main.mainView.setSecondComponent(new EmployeeListMenuView(main, EmployeeListMenuView.compSate));
        } else if (source == cf.getEdit()) {
            form.setReadOnly(false);
            cf.getSave().setVisible(false);
            cf.getEdit().setVisible(false);
            cf.getCancel().setVisible(true);
            cf.getDelete().setVisible(false);
            cf.getUpdate().setVisible(true);
        } else if (source == cf.getCancel()) {
            main.mainView.setSecondComponent(new EmployeeListMenuView(main, EmployeeListMenuView.compSate));
        } else if (source == cf.getUpdate()) {
            saveEditedCompetencyType(form);
            main.mainView.setSecondComponent(new EmployeeListMenuView(main, EmployeeListMenuView.compSate));
        } else if (source == cf.getDelete()) {
            deleteCompetencyType(form);
            main.mainView.setSecondComponent(new EmployeeListMenuView(main, EmployeeListMenuView.compSate));

        }

    }

    public void saveNewCompetencyType(Form form) {
        CompetencyTypeFactory factory = data.getCompetencyTypeFactory();
        String competencyType = form.getField("compNameType").toString();
        CompetencyType c = factory.createCompetencyType(competencyType);
        data.getCompetencyTypeService().persist(c);

    }

    public void saveEditedCompetencyType(Form form) {
        CompetencyTypeFactory factory = data.getCompetencyTypeFactory();
        String competencyType = form.getField("compNameType").getValue().toString();
        Long compNameTypeId = Long.parseLong(form.getField("compNameTypeId").getValue().toString());
        CompetencyType sg = factory.updatedCompetencyType(competencyType, compNameTypeId);
        data.getCompetencyTypeService().merge(sg);
    }

   public void deleteCompetencyType(Form form) {
       CompetencyTypeFactory factory = data.getCompetencyTypeFactory();
       Long compId = Long.parseLong(form.getField("compNameTypeId").getValue().toString());
       CompetencyType c = factory.loadCompetencyType(compId);
        data.getCompetencyTypeService().remove(c);
    }
}
