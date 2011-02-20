/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.people.views.details.registration;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.components.ButtonLink;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.people.views.PersonDetailsView;
import com.hashthrims.clients.web.vaadin.views.people.views.details.registration.form.RegistrationBean;
import com.hashthrims.domain.Person;
import com.hashthrims.domain.ProfessionalRegistration;
import com.hashthrims.domain.offices.RegistrationBody;
import com.hashthrims.infrastructure.util.TimsUtil;
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
public class ProfessionalRegistrationPage extends VerticalLayout implements ClickListener, ValueChangeListener {

    private final HashThrimsMain main;
    private final Button registrationLink = ButtonLink.getButton("Add Professional Registration");
    private ProfessionalRegistrationWindow subwindow;
    private final ClientDataService data = new ClientDataService();
    private final TimsUtil st = new TimsUtil();

    public ProfessionalRegistrationPage(final Person person, HashThrimsMain app) {
        main = app;
        setSizeFull();

        //register Listeners
        registrationLink.addListener((ClickListener) this);


        RegistrationBean bean = new RegistrationBean();
        subwindow = new ProfessionalRegistrationWindow(person, bean, main);

        final Label headerLabel = new Label("Professional Registration :  ");
        headerLabel.addStyleName(Reindeer.LABEL_H2);
        final HorizontalLayout menu = new HorizontalLayout();
        menu.addComponent(headerLabel);


        menu.addComponent(registrationLink);
        menu.setSpacing(true);

        Table table = new Table();
        table.removeAllItems();
        table.setSizeFull();


        List<ProfessionalRegistration> list = null;
        if (person.getProfessionalRegistration()!=null) {
            list = person.getProfessionalRegistration();
        } else {
             list = new ArrayList<ProfessionalRegistration>();
        }

        table.addContainerProperty("Registration Body", String.class, null);
        table.addContainerProperty("Registration Number", String.class, null);
        table.addContainerProperty("Licence Number", String.class, null);
        table.addContainerProperty("Registration Date", String.class, null);
        table.addContainerProperty("Expriration Date", String.class, null);
        table.addContainerProperty("Edits", Button.class, null);
        table.addContainerProperty("Deletes", Button.class, null);


        table.setNullSelectionAllowed(false);
        table.setSelectable(true);
        table.setImmediate(true);


        for (final ProfessionalRegistration values : list) {

            Button edit = new Button("edit", new Button.ClickListener() {

                @Override
                public void buttonClick(ClickEvent event) {
                    RegistrationBean bean = getBean();
                    subwindow = new ProfessionalRegistrationWindow(person, bean, main);
                    main.getMainWindow().addWindow(subwindow);

                }

                private RegistrationBean getBean() {
                    RegistrationBean bean = new RegistrationBean();
                    bean.setExpirationDate(values.getExpirationDate());
                    bean.setLicenceNumber(values.getRegistrationNumber());
                    bean.setRegistrationBody(getRegistrationBodyId(values.getRegistrationBody()));
                    bean.setRegistrationDate(values.getRegistrationDate());
                    bean.setRegistrationNumber(values.getRegistrationNumber());
                    bean.setId(values.getId());
                    return bean;
                }

                private Long getRegistrationBodyId(RegistrationBody registrationBody) {
                    if(registrationBody!=null)
                        return registrationBody.getId();
                    return null;
                }
            });
            Button delete = new Button("Delete", new Button.ClickListener() {

                @Override
                public void buttonClick(ClickEvent event) {
                    data.getProfessionalRegistrationService().remove(values);
                    final Person p = data.getPersonService().find(person.getId());
                    final PersonDetailsView view = new PersonDetailsView(p, main, "REG");
                    main.mainView.setSecondComponent(view);

                }
            });

            edit.setStyleName(Reindeer.BUTTON_LINK);
            delete.setStyleName(Reindeer.BUTTON_LINK);




            table.addItem(new Object[]{
                        getRegistrationBodyName(values.getRegistrationBody()),
                        values.getRegistrationNumber(),
                        values.getLicenceNumber(),
                        values.getRegistrationDate(),
                        values.getExpirationDate(),
                        edit,
                        delete}, values.getId());

        }

        addComponent(menu);
        addComponent(table);
    }

    @Override
    public void buttonClick(ClickEvent event) {
        final Button source = event.getButton();
        if (source == registrationLink) {
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

    private Object getRegistrationBodyName(RegistrationBody registrationBody) {
       if(registrationBody!=null)
           return registrationBody.getName();
       return null;
    }
}
