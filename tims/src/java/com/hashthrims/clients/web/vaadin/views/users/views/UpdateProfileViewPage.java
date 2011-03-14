/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.users.views;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.users.form.UpdateProfileForm;
import com.hashthrims.clients.web.vaadin.views.users.model.UsersBean;
import com.hashthrims.domain.Users;
import com.hashthrims.infrastructure.util.DataFieldsUtil;
import com.hashthrims.infrastructure.util.GetUserCredentials;
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
public class UpdateProfileViewPage extends VerticalLayout implements
        ClickListener {

    private final HashThrimsMain main;
    private final Form form;
    private final UpdateProfileForm pform;
    private static final ClientDataService data = new ClientDataService();
    private DataFieldsUtil fieldValues = new DataFieldsUtil();

    public UpdateProfileViewPage(HashThrimsMain app) {
        main = app;
        setSizeFull();
        pform = new UpdateProfileForm();
        form = pform.createUserForm();

        // Add Listeners
        pform.getEdit().addListener((ClickListener) this);
        pform.getCancel().addListener((ClickListener) this);
        pform.getUpdate().addListener((ClickListener) this);
        final String username = new GetUserCredentials().username();
        UsersBean bean = getUserBane(username);
        BeanItem item = new BeanItem(bean);
        form.setItemDataSource(item);
        form.setVisibleItemProperties(pform.orderList());
        form.setReadOnly(true);
        addComponent(form);
        setComponentAlignment(form, Alignment.TOP_CENTER);

    }

    @Override
    public void buttonClick(ClickEvent event) {
        final Button source = event.getButton();
        if (source == pform.getEdit()) {
            form.setReadOnly(false);
            pform.getEdit().setVisible(false);
            pform.getCancel().setVisible(true);
            pform.getUpdate().setVisible(true);
        } else if (source == pform.getCancel()) {
            UpdateProfileViewPage w = new UpdateProfileViewPage(main);
            main.mainView.setSecondComponent(w);
        } else if (source == pform.getUpdate()) {
            updateNewUser(form);
            UpdateProfileViewPage w = new UpdateProfileViewPage(main);
            main.mainView.setSecondComponent(w);
        }
    }

    public void updateNewUser(Form form) {
        final String email = fieldValues.getStringFields(form.getField("email").getValue());

        Users user = data.getUsersService().getByPropertyName("email", email);
        final String firstname = fieldValues.getStringFields(form.getField("firstname").getValue());
        final String middlename = fieldValues.getStringFields(form.getField("middlename").getValue());
        final String lastname = fieldValues.getStringFields(form.getField("lastname").getValue());
        user.setEmail(email);
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setMiddlename(middlename);
        data.getUsersService().merge(user);
    }

    private UsersBean getUserBane(String username) {

        UsersBean bean = new UsersBean();
        Users user = data.getUsersService().getByPropertyName("email", username);
        bean.setEmail(user.getEmail());
        bean.setFirstname(user.getFirstname());
        bean.setLastname(user.getLastname());
        bean.setMiddlename(user.getMiddlename());
        return bean;
    }
}
