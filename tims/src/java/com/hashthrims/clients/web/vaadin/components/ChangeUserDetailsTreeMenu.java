/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.components;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.views.manageuserprofiles.ResetPasswordViewPage;
import com.hashthrims.clients.web.vaadin.views.manageuserprofiles.UpdateProfileViewPage;
import com.hashthrims.clients.web.vaadin.views.reports.ReportsMenuView;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.Tree;

/**
 *
 * @author boniface
 */
public class ChangeUserDetailsTreeMenu extends Tree implements ItemClickListener {

    private HashThrimsMain main;
    public static final Object ADD_UPDATE_PROFILE = "Update  Profile";
    public static final Object CHANGE_PASSWORD = "Change Password";

    public ChangeUserDetailsTreeMenu(HashThrimsMain app) {
        this.main = app;


        addItem(ADD_UPDATE_PROFILE);
        addItem(CHANGE_PASSWORD);
        /*
         * We want items to be selectable but do not want the user to be able to
         * de-select an item.
         */
        setSelectable(true);
        setNullSelectionAllowed(false);

        // Make application handle item click events
        addListener((ItemClickListener) this);
    }

    @Override
    public void itemClick(ItemClickEvent event) {

        Object itemId = event.getItemId();
        if (itemId != null) {
            if (CHANGE_PASSWORD.equals(itemId)) {
                showChangePasswordViewPage();
            } else if (ADD_UPDATE_PROFILE.equals(itemId)) {
                showUpdateProfileViewPage();
            }


        }


    }



    private void showChangePasswordViewPage() {
        ResetPasswordViewPage w = new ResetPasswordViewPage(main);
        main.mainView.setSecondComponent(w);
    }

    private void showUpdateProfileViewPage() {
       UpdateProfileViewPage w = new UpdateProfileViewPage(main);
        main.mainView.setSecondComponent(w);
    }
}
