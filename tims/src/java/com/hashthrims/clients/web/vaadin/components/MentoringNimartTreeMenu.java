/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.components;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.views.users.ManageUsersMenuView;
import com.hashthrims.clients.web.vaadin.views.users.views.PasswordResetViewPage;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.Tree;

/**
 *
 * @author boniface
 */
public class MentoringNimartTreeMenu extends Tree implements ItemClickListener {

    private HashThrimsMain main;
    public static final Object MANAGE_USERS = "Manage Users";
    public static final Object CHANGE_PASSWORD = "Reset User Password";

    public MentoringNimartTreeMenu(HashThrimsMain app) {
        this.main = app;

        addItem(MANAGE_USERS);
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
            if (MANAGE_USERS.equals(itemId)) {
                showManageViewPage();
            } else if (CHANGE_PASSWORD.equals(itemId)) {
                showChangePasswordViewPage();
            }
        }
    }

    private void showChangePasswordViewPage() {
        PasswordResetViewPage w = new PasswordResetViewPage(main);
        main.mainView.setSecondComponent(w);
    }

    private void showManageViewPage() {
        ManageUsersMenuView w = new ManageUsersMenuView(main, "USER");
        main.mainView.setSecondComponent(w);
    }
}
