/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.users.views;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.users.form.ResetUserPasswordForm;
import com.hashthrims.clients.web.vaadin.views.users.model.UsersBean;
import com.hashthrims.clients.web.vaadin.views.users.util.PasswordFactory;
import com.hashthrims.domain.Users;
import com.hashthrims.infrastructure.util.DataFieldsUtil;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Form;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window.Notification;

/**
 *
 * @author boniface
 */
public class PasswordResetViewPage extends VerticalLayout implements
        ClickListener {

    private final HashThrimsMain main;
    private final Form form;
    private final ResetUserPasswordForm pform;
    private static final ClientDataService data = new ClientDataService();
    private DataFieldsUtil fieldValues = new DataFieldsUtil();

    public PasswordResetViewPage(HashThrimsMain app) {
        main = app;
        setSizeFull();
        pform = new ResetUserPasswordForm();
        form = pform.createUserForm();

        // Add Listeners
        pform.getSave().addListener((ClickListener) this);
        pform.getCancel().addListener((ClickListener) this);


        UsersBean bean = new UsersBean();
        BeanItem item = new BeanItem(bean);
        form.setItemDataSource(item);
        form.setVisibleItemProperties(pform.orderList());

        addComponent(form);
        setComponentAlignment(form, Alignment.TOP_CENTER);

        //addComponent(table);

    }

    @Override
    public void buttonClick(ClickEvent event) {
        final Button source = event.getButton();
        if (source == pform.getSave()) {
            resetUserPassword(form);
            PasswordResetViewPage w = new PasswordResetViewPage(main);
            main.mainView.setSecondComponent(w);
        } else if (source == pform.getCancel()) {
            getWindow().showNotification("User Password", "HAS NOT BEEN RESET",
                    Notification.TYPE_WARNING_MESSAGE);
            PasswordResetViewPage w = new PasswordResetViewPage(main);
            main.mainView.setSecondComponent(w);
        }
    }

    public void resetUserPassword(Form form) {
        final String email = fieldValues.getStringFields(form.getField("email").getValue());
        Users user = data.getUsersService().getByPropertyName("email", email);
        final String encryptPasswd = PasswordFactory.getNewStaticPassword();
        if (user != null) {
            user.setPasswd(encryptPasswd);
            data.getUsersService().merge(user);
            getWindow().showNotification("User Password with " + email, "HAS  BEEN RESET",
                    Notification.TYPE_WARNING_MESSAGE);
        } else {
            getWindow().showNotification("No Person with Username ", email,
                    Notification.TYPE_WARNING_MESSAGE);
        }

    }
}
