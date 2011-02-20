/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.manageuserprofiles;

import com.hashthrims.clients.web.vaadin.views.employeelists.qualifications.views.*;
import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.employeelists.EmployeeListMenuView;
import com.hashthrims.clients.web.vaadin.views.employeelists.qualifications.forms.DegreeForm;
import com.hashthrims.clients.web.vaadin.views.employeelists.qualifications.model.DegreeBean;
import com.hashthrims.clients.web.vaadin.views.employeelists.qualifications.table.DegreeTable;
import com.hashthrims.domain.employeelist.Degree;
import com.hashthrims.infrastructure.factories.employeelist.DegreeFactory;
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
public class ResetPasswordViewPage extends VerticalLayout implements
        ClickListener, ValueChangeListener {

    private HashThrimsMain main;
    private Form form;
    private DegreeForm pform;
    private static ClientDataService data = new ClientDataService();
    private DegreeTable table;

    public ResetPasswordViewPage(HashThrimsMain app) {
        main = app;
        setSizeFull();
        pform = new DegreeForm();
        form = pform.createDegreeForm();

        // Add Listeners
        pform.getSave().addListener((ClickListener) this);
        pform.getEdit().addListener((ClickListener) this);
        pform.getCancel().addListener((ClickListener) this);
        pform.getDelete().addListener((ClickListener) this);
        pform.getUpdate().addListener((ClickListener) this);

        DegreeBean bean = new DegreeBean();
        BeanItem item = new BeanItem(bean);
        form.setItemDataSource(item);
        form.setVisibleItemProperties(pform.orderList());

       // addComponent(form);
      //  setComponentAlignment(form, Alignment.TOP_CENTER);
//        table = new DegreeTable(main);
//        table.addListener((ValueChangeListener) this);
//        addComponent(table);

    }

    @Override
    public void valueChange(ValueChangeEvent event) {
        Property property = event.getProperty();

        if (property == table) {
            Item record = table.getItem(table.getValue());
            DegreeBean deg = new DegreeBean();
            deg.setDegreeName(record.getItemProperty("Email Address").toString());
            deg.setEducType(record.getItemProperty("User name ").toString());
            deg.setDegrId(new Long(table.getValue().toString()));
            if (deg != form.getItemDataSource()) {
                BeanItem item = new BeanItem(deg);
                form.setItemDataSource(item);
                // BUG enabling this Disables form.setVisibleItemProperties(cf.orderList());

                form.setReadOnly(true);
                //Buttons Behaviou
                pform.getSave().setVisible(false);
                pform.getEdit().setVisible(true);
                pform.getCancel().setVisible(true);
                pform.getDelete().setVisible(true);
                pform.getUpdate().setVisible(false);
            }

        }
    }

    @Override
    public void buttonClick(ClickEvent event) {
        final Button source = event.getButton();
        EmployeeListMenuView.qualSate = "RESET PASSWD";
        if (source == pform.getSave()) {
            saveNewDegree(form);
            main.mainView.setSecondComponent(new EmployeeListMenuView(main, EmployeeListMenuView.qualSate));
        } else if (source == pform.getEdit()) {
            form.setReadOnly(false);
            pform.getSave().setVisible(false);
            pform.getEdit().setVisible(false);
            pform.getCancel().setVisible(true);
            pform.getDelete().setVisible(false);
            pform.getUpdate().setVisible(true);
        } else if (source == pform.getCancel()) {
            main.mainView.setSecondComponent(new EmployeeListMenuView(main, EmployeeListMenuView.qualSate));
        } else if (source == pform.getUpdate()) {
            saveEditedDegree(form);

            main.mainView.setSecondComponent(new EmployeeListMenuView(main, EmployeeListMenuView.qualSate));

        } else if (source == pform.getDelete()) {
            deleteDegree(form);
            main.mainView.setSecondComponent(new EmployeeListMenuView(main, EmployeeListMenuView.qualSate));

        }

    }

    public void saveNewDegree(Form form) {
        DegreeFactory factory = data.getDegreeFactory();

        String degreeName = form.getField("degreeName").getValue().toString();
        String educType = form.getField("educType").getValue().toString();
        Degree d = factory.createDegree(degreeName, educType);
        data.getDegreeName().persist(d);

    }

    public void saveEditedDegree(Form form) {
        DegreeFactory factory = data.getDegreeFactory();
        String degreeName = form.getField("degreeName").getValue().toString();
        String educType = form.getField("educType").getValue().toString();
        Long degrId = Long.parseLong(form.getField("degrId").getValue().toString());
        Degree d = factory.updatedDegree(degreeName, educType, degrId);
        data.getDegreeName().merge(d);
    }

    public void deleteDegree(Form form) {
        DegreeFactory factory = data.getDegreeFactory();
        Long degrId = Long.parseLong(form.getField("degrId").getValue().toString());
        Degree d = factory.loadDegree(degrId);
        data.getDegreeName().remove(d);
    }
}
