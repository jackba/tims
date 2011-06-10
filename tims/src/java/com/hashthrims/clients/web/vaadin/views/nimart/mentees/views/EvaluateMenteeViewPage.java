/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.nimart.mentees.views;

import com.hashthrims.clients.web.vaadin.views.nimart.data.views.*;
import com.hashthrims.clients.web.vaadin.views.users.views.*;
import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.users.ManageUsersMenuView;
import com.hashthrims.clients.web.vaadin.views.users.form.UsersForm;
import com.hashthrims.clients.web.vaadin.views.users.model.UsersBean;
import com.hashthrims.clients.web.vaadin.views.users.tables.UserTable;
import com.hashthrims.application.utilities.PasswordFactory;
import com.hashthrims.domain.Roles;
import com.hashthrims.domain.Users;
import com.hashthrims.infrastructure.factories.UsersFactory;
import com.hashthrims.infrastructure.util.DataFieldsUtil;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author boniface
 */
public class EvaluateMenteeViewPage extends VerticalLayout implements
        ClickListener, ValueChangeListener {

    private final HashThrimsMain main;
    private final Form form;
    private final UsersForm pform;
    private static final ClientDataService data = new ClientDataService();
    private final UserTable table;
    private final UsersFactory factory = data.getUsersFactory();
    private DataFieldsUtil fieldValues = new DataFieldsUtil();

    public EvaluateMenteeViewPage(HashThrimsMain app) {
        main = app;
        setSizeFull();
        pform = new UsersForm();
        form = pform.createUserForm();

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

       // addComponent(form);
       // setComponentAlignment(form, Alignment.TOP_CENTER);
        table = new UserTable(main);
        table.addListener((ValueChangeListener) this);
        //addComponent(table);

    }

    @Override
    public void valueChange(ValueChangeEvent event) {
        final Property property = event.getProperty();

        final List<String> formRoles = new ArrayList<String>();

        if (property == table) {
            final Item record = table.getItem(table.getValue());
            final UsersBean user = new UsersBean();
            user.setEmail(record.getItemProperty("Username").toString());
            user.setFirstname(record.getItemProperty("FirstName").toString());
            user.setLastname(record.getItemProperty("Last Name").getValue().toString());
            user.setMiddlename(fieldValues.getStringFields(record.getItemProperty("Middle Name(s)").getValue()));
            Users userId = data.getUsersService().find(new Long(table.getValue().toString()));
            user.setEnabled(userId.isEnabled());
            List<Roles> userRoles = userId.getRoles();
            for (Roles role : userRoles) {
                formRoles.add(role.getRoleName());
            }
            user.setRoles(formRoles);
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
            saveNewUser(form);
            main.mainView.setSecondComponent(new ManageUsersMenuView(main, "USERS"));
        } else if (source == pform.getEdit()) {
            form.setReadOnly(false);
            pform.getSave().setVisible(false);
            pform.getEdit().setVisible(false);
            pform.getCancel().setVisible(true);
            pform.getDelete().setVisible(false);
            pform.getUpdate().setVisible(true);
        } else if (source == pform.getCancel()) {
            main.mainView.setSecondComponent(new ManageUsersMenuView(main, "USERS"));
        } else if (source == pform.getUpdate()) {
            updateNewUser(form);

            main.mainView.setSecondComponent(new ManageUsersMenuView(main, "USERS"));

        } else if (source == pform.getDelete()) {
            deleteUser(form);
            main.mainView.setSecondComponent(new ManageUsersMenuView(main, "USERS"));

        }

    }

    public void saveNewUser(Form form) {

        final String email = form.getField("email").getValue().toString();
        final String firstname = form.getField("firstname").getValue().toString();
        final String passwd = PasswordFactory.getNewStaticPassword();
        final String middlename = fieldValues.getStringFields(form.getField("middlename").getValue());
        final String lastname = form.getField("lastname").getValue().toString();
        final String enabled = form.getField("enabled").getValue().toString();
        final boolean activitate = Boolean.valueOf(enabled);
        Map<String, String> userValues = new HashMap<String, String>();
        userValues.put("email", email);
        userValues.put("firstname", firstname);
        userValues.put("passwd", passwd);
        userValues.put("middlename", middlename);
        userValues.put("lastname", lastname);

        Object roles = form.getField("roles").getValue();
        List<String> userRoles = fieldValues.getSelectListFields(roles);
        final Users user = factory.createNewUser(userValues, userRoles, activitate);
        data.getUsersService().persist(user);

    }

    public void updateNewUser(Form form) {
        final String email = form.getField("email").getValue().toString();
        final Long id = Long.parseLong(form.getField("id").getValue().toString());
        final String firstname = form.getField("firstname").getValue().toString();     
        final String middlename = fieldValues.getStringFields(form.getField("middlename").getValue());
        final String lastname = form.getField("lastname").getValue().toString();
        final String enabled = form.getField("enabled").getValue().toString();
        final boolean activitate = Boolean.valueOf(enabled);





        Map<String, String> userValues = new HashMap<String, String>();
        userValues.put("email", email);
        userValues.put("firstname", firstname);
        userValues.put("middlename", middlename);
        userValues.put("lastname", lastname);
        userValues.put("enabled", enabled);

        Object roles = form.getField("roles").getValue();
        List<String> userRoles = fieldValues.getSelectListFields(roles);

        final Users user = factory.updateUser(userValues, userRoles, activitate, id);
        data.getUsersService().merge(user);


    }

    public void deleteUser(Form form) {
        final Long id = Long.parseLong(form.getField("id").getValue().toString());
        final Users c = factory.loadUser(id);
        data.getUsersService().remove(c);
    }
}
