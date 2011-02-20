/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.users.views;


import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.users.form.UsersForm;
import com.hashthrims.clients.web.vaadin.views.users.model.UsersBean;
import com.hashthrims.clients.web.vaadin.views.users.tables.UserTable;
import com.hashthrims.domain.Users;
import com.hashthrims.infrastructure.factories.UsersFactory;
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
public class UsersViewPage extends VerticalLayout implements
        ClickListener, ValueChangeListener {

    private final HashThrimsMain main;
    private final Form form;
    private final UsersForm pform;
    private static final ClientDataService data = new ClientDataService();
    private final UserTable table;
    private final UsersFactory factory = data.getUsersFactory();

    public UsersViewPage(HashThrimsMain app) {
        main = app;
        setSizeFull();
        pform = new UsersForm();
        form = pform.createCadreFrom();

        // Add Listeners
        pform.getSave().addListener((ClickListener) this);
        pform.getEdit().addListener((ClickListener) this);
        pform.getCancel().addListener((ClickListener) this);
        pform.getDelete().addListener((ClickListener) this);
        pform.getUpdate().addListener((ClickListener) this);

        UsersBean bean = new UsersBean();
        BeanItem item = new BeanItem(bean);
        form.setItemDataSource(item);
        form.setVisibleItemProperties(pform.orderList());

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
            final UsersBean user = new UsersBean();
            user.setEmail(record.getItemProperty("Job Title").toString());
           // user.setEnabled(record.getItemProperty("Job Code").toString());
            user.setFirstname(record.getItemProperty("Job Description").toString());
//            job.setSalaryGrade(record.getItemProperty("Salary Grade").getValue().toString());
            user.setLastname(record.getItemProperty("Classification").getValue().toString());
            user.setMiddlename(record.getItemProperty("Cadre").getValue().toString());
            user.setId(new Long(table.getValue().toString()));
            if (user != form.getItemDataSource()) {
                final BeanItem item = new BeanItem(user);
                form.setItemDataSource(item);
                form.setVisibleItemProperties(pform.orderList());

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
        if (source == pform.getSave()) {
            saveNewDistrict(form);
         //   main.mainView.setSecondComponent(new CreateUsersStructureMenuView(main, "Users"));
        } else if (source == pform.getEdit()) {
            form.setReadOnly(false);
            pform.getSave().setVisible(false);
            pform.getEdit().setVisible(false);
            pform.getCancel().setVisible(true);
            pform.getDelete().setVisible(false);
            pform.getUpdate().setVisible(true);
        } else if (source == pform.getCancel()) {
         //   main.mainView.setSecondComponent(new CreateUsersStructureMenuView(main, "Users"));
        } else if (source == pform.getUpdate()) {
            saveEditedDistrict(form);

         //   main.mainView.setSecondComponent(new CreateUsersStructureMenuView(main, "Users"));

        } else if (source == pform.getDelete()) {
            deleteDistrict(form);
        //    main.mainView.setSecondComponent(new CreateUsersStructureMenuView(main, "Users"));

        }

    }

    public void saveNewDistrict(Form form) {

        final String title = form.getField("title").getValue().toString();
        final String description = form.getField("description").getValue().toString();
        final String code = form.getField("code").getValue().toString();
//        String salaryGrade = form.getField("salaryGrade").getValue().toString();
        final String jobClassification = form.getField("jobClassification").getValue().toString();
        final String cadre = form.getField("cadre").getValue().toString();

       // final Users d = factory.createJob(title, description, code, jobClassification, cadre);
       // data.getUserservice().persist(d);

    }

    public void saveEditedDistrict(Form form) {

        final String title = form.getField("title").getValue().toString();
        final String description = form.getField("description").getValue().toString();
        final String code = form.getField("code").getValue().toString();
        //final String salaryGrade = form.getField("salaryGrade").getValue().toString();
        final String jobClassification = form.getField("jobClassification").getValue().toString();
        final String cadre = form.getField("cadre").getValue().toString();
        final Long UsersId = Long.parseLong(form.getField("UsersId").getValue().toString());
       // final Users j = factory.updateJob(title, description, code, jobClassification, cadre, UsersId);
      //  data.getUserservice().merge(j);
    }

    public void deleteDistrict(Form form) {
        final Long UsersId = Long.parseLong(form.getField("UsersId").getValue().toString());
        final Users c = factory.loadUsers(UsersId);
       // data.getUserservice().remove(c);
    }
}
