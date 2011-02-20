/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.people.views.details.education;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.components.ButtonLink;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.people.views.details.education.form.EducationHistoryBean;
import com.hashthrims.domain.Person;
import com.hashthrims.infrastructure.util.TimsUtil;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

/**
 *
 * @author boniface
 */
public class EducationHistoryPage extends VerticalLayout implements ClickListener, ValueChangeListener {

    private final HashThrimsMain main;
    private final Button addEducationHistory = ButtonLink.getButton("Add Education History");
    private EducationHistoryWindow subwindow;
    private final ClientDataService data = new ClientDataService();
    private final TimsUtil st = new TimsUtil();
    private final EducationHistoryTable table;

    public EducationHistoryPage(final Person person, HashThrimsMain app) {
        main = app;
        setSizeFull();
        table = new EducationHistoryTable(app, person);

        //register Listeners
        addEducationHistory.addListener((ClickListener) this);


        EducationHistoryBean bean = new EducationHistoryBean();
        subwindow = new EducationHistoryWindow(person, bean, main);

        final Label headerLabel = new Label("Education History :  ");
        headerLabel.addStyleName(Reindeer.LABEL_H2);
        final HorizontalLayout menu = new HorizontalLayout();
        menu.addComponent(headerLabel);


        menu.addComponent(addEducationHistory);
        menu.setSpacing(true);

        addComponent(menu);
        addComponent(table);
    }

    @Override
    public void buttonClick(ClickEvent event) {
        final Button source = event.getButton();
        if (source == addEducationHistory) {
            if (subwindow.getParent() != null) {
                // window is already showing
                main.getMainWindow().showNotification(
                        "Window is already open");
            } else {
                // Open the subwindow by adding it to the parent
                // window
                main.getMainWindow().addWindow(subwindow);
            }
        }
    }

    @Override
    public void valueChange(ValueChangeEvent event) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
