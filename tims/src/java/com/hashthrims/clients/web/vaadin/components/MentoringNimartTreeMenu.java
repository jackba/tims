/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.components;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.views.nimart.data.ManageNimartDataMenuView;
import com.hashthrims.clients.web.vaadin.views.nimart.mentees.ManageNimartMenteesMenuView;
import com.hashthrims.clients.web.vaadin.views.nimart.mentors.ManageNimartMentorsMenuView;
import com.hashthrims.clients.web.vaadin.views.nimart.reports.ManageNimartReportsMenuView;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.Tree;

/**
 *
 * @author boniface
 */
public class MentoringNimartTreeMenu extends Tree implements ItemClickListener {

    private HashThrimsMain main;
    public static final Object MANAGE_NIMART_DATA = "Set-Up NIMART Data";
    public static final Object MANAGE_NIMART_MENTORS = "Manage NIMART Mentors";
    public static final Object MANAGE_NIMART_MENTEES = "Manage NIMART Mentees";
    public static final Object MANAGE_NIMART_REPORTS = "Manage NIMART Reports";

    public MentoringNimartTreeMenu(HashThrimsMain app) {
        this.main = app;

        addItem(MANAGE_NIMART_DATA);
        addItem(MANAGE_NIMART_MENTORS);
        addItem(MANAGE_NIMART_MENTEES);
        addItem(MANAGE_NIMART_REPORTS);
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
            if (MANAGE_NIMART_DATA.equals(itemId)) {
                showNimartDataViewPage();
            } else if (MANAGE_NIMART_MENTORS.equals(itemId)) {
                showNimartMentorsViewPage();
            } else if (MANAGE_NIMART_MENTEES.equals(itemId)) {
                showNimartMenteesViewPage();
            } else if (MANAGE_NIMART_REPORTS.equals(itemId)) {
                showNimartReportViewPage();
            }
        }
    }

    private void showNimartDataViewPage() {
        ManageNimartDataMenuView w = new ManageNimartDataMenuView(main, "CAT");
        main.mainView.setSecondComponent(w);
    }

    private void showNimartMentorsViewPage() {
        ManageNimartMentorsMenuView w = new ManageNimartMentorsMenuView(main, "USER");
        main.mainView.setSecondComponent(w);
    }

    private void showNimartMenteesViewPage() {
        ManageNimartMenteesMenuView w = new ManageNimartMenteesMenuView(main, "USER");
        main.mainView.setSecondComponent(w);
    }

    private void showNimartReportViewPage() {
        ManageNimartReportsMenuView w = new ManageNimartReportsMenuView(main, "USER");
        main.mainView.setSecondComponent(w);
    }
}
