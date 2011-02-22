/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.users.views;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.jobs.CreateJobsStructureMenuView;
import com.hashthrims.clients.web.vaadin.views.jobs.model.CadreBean;
import com.hashthrims.clients.web.vaadin.views.users.form.UsersForm;
import com.hashthrims.clients.web.vaadin.views.users.model.UsersBean;
import com.hashthrims.clients.web.vaadin.views.users.tables.UserTable;
import com.hashthrims.infrastructure.factories.jobs.JobsFactory;
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
public class RolesViewPage extends VerticalLayout implements
        ClickListener, ValueChangeListener {

    private final HashThrimsMain main;
    private final Form form;
    private final UsersForm cf;
    private static final ClientDataService data = new ClientDataService();
    private final UserTable table;
    private final JobsFactory factory = data.getJobsFactory();

    public RolesViewPage(HashThrimsMain app) {
        main = app;
        setSizeFull();
        cf = new UsersForm();
        form = cf.createUserForm();

        // Add Listeners
        cf.getSave().addListener((ClickListener) this);
        cf.getEdit().addListener((ClickListener) this);
        cf.getCancel().addListener((ClickListener) this);
        cf.getDelete().addListener((ClickListener) this);
        cf.getUpdate().addListener((ClickListener) this);

        final UsersBean bean = new UsersBean();
        final BeanItem item = new BeanItem(bean);
        form.setItemDataSource(item);
        form.setVisibleItemProperties(cf.orderList());

        addComponent(form);
        setComponentAlignment(form, Alignment.TOP_CENTER);
        table = new UserTable(main);
        table.addListener((ValueChangeListener) this);
        addComponent(table);

    }

    @Override
    public void valueChange(ValueChangeEvent event) {
        final Property property = event.getProperty();
        if (property == table) {
            final Item record = table.getItem(table.getValue());
            final CadreBean cadre = new CadreBean();
            cadre.setName(record.getItemProperty("Role Name").toString());
            cadre.setCadreId(new Long(table.getValue().toString()));
            if (cadre != form.getItemDataSource()) {
                final BeanItem item = new BeanItem(cadre);
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
        if (source == cf.getSave()) {
            saveNewCadre(form);
            main.mainView.setSecondComponent(new CreateJobsStructureMenuView(main, "CADRE"));
        } else if (source == cf.getEdit()) {
            form.setReadOnly(false);
            cf.getSave().setVisible(false);
            cf.getEdit().setVisible(false);
            cf.getCancel().setVisible(true);
            cf.getDelete().setVisible(false);
            cf.getUpdate().setVisible(true);
        } else if (source == cf.getCancel()) {
            main.mainView.setSecondComponent(new CreateJobsStructureMenuView(main, "CADRE"));
        } else if (source == cf.getUpdate()) {
            saveEditedCountry(form);

            main.mainView.setSecondComponent(new CreateJobsStructureMenuView(main, "CADRE"));

        } else if (source == cf.getDelete()) {
            deleteCadre(form);
            main.mainView.setSecondComponent(new CreateJobsStructureMenuView(main, "CADRE"));

        }

    }

    public void saveNewCadre(Form form) {
        final String name = form.getField("name").getValue().toString();
        //final Users c = factory.createUsers(name);
        //data.getUsersService().persist(c);

    }

    public void saveEditedCountry(Form form) {
        final String name = form.getField("name").getValue().toString();
        final Long cadreId = Long.parseLong(form.getField("cadreId").getValue().toString());
        //final Users c = factory.updatedUsers(name, cadreId);
        //data.getUsersService().merge(c);
    }

    public void deleteCadre(Form form) {
        final Long cadreId = Long.parseLong(form.getField("cadreId").getValue().toString());
       // final Users c = factory.loadUsers(cadreId);
       // data.getUsersService().remove(c);
    }
}
