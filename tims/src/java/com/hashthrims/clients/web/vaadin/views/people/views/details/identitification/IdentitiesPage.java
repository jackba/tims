/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.people.views.details.identitification;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.components.ButtonLink;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.people.views.PersonDetailsView;
import com.hashthrims.clients.web.vaadin.views.people.views.details.identitification.form.IdentitiesBean;
import com.hashthrims.domain.Identities;
import com.hashthrims.domain.Person;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author boniface
 */
public class IdentitiesPage extends VerticalLayout implements ClickListener, ValueChangeListener {

    private final HashThrimsMain main;
    private final Button identityLink = ButtonLink.getButton("Add Identity");
    private final Button editLink = ButtonLink.getButton("Edit");
    private final Button deleteLink = ButtonLink.getButton("Delete");
    private IdentitiesWindow subwindow;
    private final ClientDataService data = new ClientDataService();

    public IdentitiesPage(final Person person, HashThrimsMain app) {
        main = app;
        setSizeFull();

        //register Listeners
        identityLink.addListener((ClickListener) this);


        IdentitiesBean bean = new IdentitiesBean();
        subwindow = new IdentitiesWindow(person, bean, main);

        final Label headerLabel = new Label("Indentities :  ");
        headerLabel.addStyleName(Reindeer.LABEL_H2);
        final HorizontalLayout menu = new HorizontalLayout();
        menu.addComponent(headerLabel);


        menu.addComponent(identityLink);
        menu.setSpacing(true);

        Table table = new Table();
        table.removeAllItems();
        table.setSizeFull();


        List<Identities> indentities = null;
        if (person.getIdentities()!=null) {
            indentities = person.getIdentities();
        } else {
             indentities = new ArrayList<Identities>();
        }
        table.addContainerProperty("Identity Type", String.class, null);
        table.addContainerProperty("Identity value", String.class, null);
        table.addContainerProperty("Edits", Button.class, null);
        table.addContainerProperty("Deletes", Button.class, null);


        table.setNullSelectionAllowed(false);
        table.setSelectable(true);
        table.setImmediate(true);


        for (final Identities values : indentities) {

            Button edit = new Button("edit", new Button.ClickListener() {

                @Override
                public void buttonClick(ClickEvent event) {
                    IdentitiesBean bean = getBean();
                    subwindow = new IdentitiesWindow(person, bean, main);
                    main.getMainWindow().addWindow(subwindow);

                }

                private IdentitiesBean getBean() {
                    IdentitiesBean bean = new IdentitiesBean();
                    bean.setIdType(values.getIdType());
                    bean.setIdValue(values.getIdValue());
                    bean.setId(values.getId());
                    return bean;
                }
            });
            Button delete = new Button("Delete", new Button.ClickListener() {

                @Override
                public void buttonClick(ClickEvent event) {
                    person.getIdentities().remove(values);
                    data.getPersonService().merge(person);
                    final PersonDetailsView view = new PersonDetailsView(person, main, "IDS");
                    main.mainView.setSecondComponent(view);

                }
            });

            edit.setStyleName(Reindeer.BUTTON_LINK);
            delete.setStyleName(Reindeer.BUTTON_LINK);




            table.addItem(new Object[]{
                        values.getIdType(),
                        values.getIdValue(),
                        edit,
                        delete}, values.getId());

        }

        addComponent(menu);
        addComponent(table);
    }

    @Override
    public void buttonClick(ClickEvent event) {
        final Button source = event.getButton();
        if (source == identityLink) {
            if (subwindow.getParent() != null) {
                // window is already showing
                main.getMainWindow().showNotification(
                        "Window is already open");
            } else {
                // Open the subwindow by adding it to the parent
                // window
                main.getMainWindow().addWindow(subwindow);
            }
        } else if (source == editLink) {
        } else if (source == deleteLink) {
        }
    }

    @Override
    public void valueChange(ValueChangeEvent event) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
