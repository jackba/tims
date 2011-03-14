/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.users.views;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.users.form.UserPasswordChangeForm;
import com.hashthrims.clients.web.vaadin.views.users.model.PasswordChangeBean;
import com.hashthrims.clients.web.vaadin.views.users.util.PasswordFactory;
import com.hashthrims.domain.Users;
import com.hashthrims.infrastructure.factories.UsersFactory;
import com.hashthrims.infrastructure.util.DataFieldsUtil;
import com.hashthrims.infrastructure.util.GetUserCredentials;
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
public class PasswordChangeViewPage extends VerticalLayout implements
        ClickListener {

    private final HashThrimsMain main;
    private final Form form;
    private final UserPasswordChangeForm pform;
    private static final ClientDataService data = new ClientDataService();
    private final UsersFactory factory = data.getUsersFactory();
    private DataFieldsUtil fieldValues = new DataFieldsUtil();
    private final String username = new GetUserCredentials().username();

    public PasswordChangeViewPage(HashThrimsMain app) {
        main = app;
        setSizeFull();
        pform = new UserPasswordChangeForm();
        form = pform.createUserForm();

        // Add Listeners
        pform.getSave().addListener((ClickListener) this);
        pform.getCancel().addListener((ClickListener) this);

        PasswordChangeBean bean = new PasswordChangeBean();
        BeanItem item = new BeanItem(bean);
        form.setItemDataSource(item);
        form.setVisibleItemProperties(pform.orderList());

        addComponent(form);
        setComponentAlignment(form, Alignment.TOP_CENTER);


    }

    @Override
    public void buttonClick(ClickEvent event) {
        final Button source = event.getButton();
        if (source == pform.getSave()) {
            if (arePasswordsCorrect(form)) {
                getWindow().showNotification("Your Password", "Has Been Changed",
                        Notification.TYPE_WARNING_MESSAGE);
                PasswordChangeViewPage w = new PasswordChangeViewPage(main);
                updateUserPassword(form);
                main.mainView.setSecondComponent(w);
            } else {
                if (!isCurrentPasswordCorrect(form)) {
                    getWindow().showNotification("Your Current Password is", "INCORRECT!!!",
                            Notification.TYPE_WARNING_MESSAGE);
                    PasswordChangeViewPage w = new PasswordChangeViewPage(main);
                    main.mainView.setSecondComponent(w);
                }
                if (!doNewPasswordsMatch(form)) {
                    getWindow().showNotification("Your New Password ", "DOES NOT MATCH THE CONFIRM PASSWORD!!!",
                            Notification.TYPE_WARNING_MESSAGE);
                    PasswordChangeViewPage w = new PasswordChangeViewPage(main);
                    main.mainView.setSecondComponent(w);
                }
            }
        } else if (source == pform.getCancel()) {
            PasswordChangeViewPage w = new PasswordChangeViewPage(main);
            getWindow().showNotification("Your Password ", "Has Not Changed",
                    Notification.TYPE_WARNING_MESSAGE);
            main.mainView.setSecondComponent(w);
        }
    }

    public void updateUserPassword(Form form) {
        final Users user = data.getUsersService().getByPropertyName("email", username);
        data.getUsersService().merge(user);
    }
    private boolean arePasswordsCorrect(Form form) {
        boolean arePasswordsCorrect = false;
        if (isCurrentPasswordCorrect(form) && doNewPasswordsMatch(form)) {
            arePasswordsCorrect = true;
        }
        return arePasswordsCorrect;
    }
    private boolean isCurrentPasswordCorrect(Form form) {
        boolean isPasswordCurrentCorrect=false;
        final String currentPassword = form.getField("currentPassword").getValue().toString();
        final String encryptPasswd = PasswordFactory.EncryptPassword(currentPassword);
        final Users user = data.getUsersService().getByPropertyName("email", username);
        if (encryptPasswd.equals(user.getPasswd())) {
            isPasswordCurrentCorrect=true;
        }
        return isPasswordCurrentCorrect;
    }
    private boolean doNewPasswordsMatch(Form form) {
        boolean doPassowrdsmatch = false;
        final String newPassword = form.getField("newPassword").getValue().toString();
        final String repeatPassword = form.getField("repeatPassword").getValue().toString();
        if (newPassword.endsWith(repeatPassword)) {
            doPassowrdsmatch = true;
        }
        return doPassowrdsmatch;
    }
}
