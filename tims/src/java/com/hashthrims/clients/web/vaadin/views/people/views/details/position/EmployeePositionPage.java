/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.people.views.details.position;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.components.ButtonLink;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.people.views.PersonDetailsView;
import com.hashthrims.clients.web.vaadin.views.people.views.details.position.form.EmployeePositionBean;
import com.hashthrims.domain.EmployeePosition;
import com.hashthrims.domain.Person;
import com.hashthrims.domain.offices.Facility;
import com.hashthrims.domain.positions.Positions;
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
public class EmployeePositionPage extends VerticalLayout implements ClickListener, ValueChangeListener {

    private final HashThrimsMain main;
    private final Button setPositionLink = ButtonLink.getButton("Set Position");
    private EmployeePositionWindow subwindow;
    private final ClientDataService data = new ClientDataService();
    private final TimsUtil st = new TimsUtil();

    public EmployeePositionPage(final Person person, HashThrimsMain app) {
        main = app;
        setSizeFull();

        //register Listeners
        setPositionLink.addListener((ClickListener) this);


        EmployeePositionBean bean = new EmployeePositionBean();
        subwindow = new EmployeePositionWindow(person, bean, main);

        final Label headerLabel = new Label("Employee Position :  ");
        headerLabel.addStyleName(Reindeer.LABEL_H2);
        final HorizontalLayout menu = new HorizontalLayout();
        menu.addComponent(headerLabel);


        menu.addComponent(setPositionLink);
        menu.setSpacing(true);

        final Table table = new Table();
        table.setSizeFull();


        List<EmployeePosition> list = null;
        if (person.getPosition()!=null) {
            list = person.getPosition();
        } else {
            list= new ArrayList<EmployeePosition>();
        }
        table.addContainerProperty("Position", String.class, null);
        table.addContainerProperty("Start Date", String.class, null);
        table.addContainerProperty("End Date", String.class, null);
        table.addContainerProperty("Position Status", String.class, null);
        table.addContainerProperty("Edits", Button.class, null);
        table.addContainerProperty("Deletes", Button.class, null);


       
        


        for (final EmployeePosition values : list) {

            Button edit = new Button("edit", new Button.ClickListener() {

                @Override
                public void buttonClick(ClickEvent event) {
                    EmployeePositionBean bean = getBean();
                    subwindow = new EmployeePositionWindow(person, bean, main);
                    main.getMainWindow().addWindow(subwindow);

                }

                private EmployeePositionBean getBean() {
                    EmployeePositionBean bean = new EmployeePositionBean();
                    bean.setEnddate(values.getEnddate());
                    bean.setPosition(getPositionId(values.getPosition()));
                    bean.setStartDate(values.getStartDate());
                    bean.setStatus(values.getStatus());
                    //bean.setFacility(getFacilityNameId(values.getPosition()));
                    bean.setId(values.getId());
                    return bean;
                }

                private Long getPositionId(Positions position) {
                   if(position!=null)
                       return position.getId();
                   return null;
                }

                private Long getFacilityNameId(Positions position) {
                    if(position!=null)
                        return getFacilityId(position.getFacililty());
                    return null;
                }

                private Long getFacilityId(Facility facililty) {
                    if(facililty!=null)
                        return facililty.getId();
                    return null;
                }
            });
            Button delete = new Button("Delete", new Button.ClickListener() {

                @Override
                public void buttonClick(ClickEvent event) {
                    person.getPosition().remove(values);
                    data.getPersonService().merge(person);
                    final PersonDetailsView view = new PersonDetailsView(person, main, "POS");
                    main.mainView.setSecondComponent(view);

                }
            });

            edit.setStyleName(Reindeer.BUTTON_LINK);
            delete.setStyleName(Reindeer.BUTTON_LINK);




            table.addItem(new Object[]{
                        getPositionName(values.getPosition()),
                        values.getStartDate(),
                        values.getEnddate(),
                        values.getStatus(),
                        edit,
                        delete}, values.getId());

        }

        addComponent(menu);
        table.setSelectable(true);
        table.setImmediate(true);
        addComponent(table);
    }

    @Override
    public void buttonClick(ClickEvent event) {
        final Button source = event.getButton();
        if (source == setPositionLink) {
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

    private String getPositionName(Positions position) {
       if(position!=null)
           return position.getPositionCode();
       return null;
    }
}
