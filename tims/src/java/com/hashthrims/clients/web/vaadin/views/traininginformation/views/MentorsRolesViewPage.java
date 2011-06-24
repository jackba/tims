/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.traininginformation.views;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.traininginformation.TrainingInformationMenuView;
import com.hashthrims.clients.web.vaadin.views.traininginformation.forms.MentorsRolesForm;
import com.hashthrims.clients.web.vaadin.views.traininginformation.model.MentorsRolesBean;
import com.hashthrims.clients.web.vaadin.views.traininginformation.table.MentorsRolesTable;
import com.hashthrims.domain.employeelist.MentorsRoles;
import com.hashthrims.infrastructure.factories.TrainingFactory;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Form;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author bulelani
 */
public class MentorsRolesViewPage extends VerticalLayout implements
        ClickListener, ValueChangeListener {

    private HashThrimsMain main;
    private Form form;
    private MentorsRolesForm pform;
    private static ClientDataService data = new ClientDataService();
    private MentorsRolesTable table;

    public MentorsRolesViewPage(HashThrimsMain app) {
        main = app;
        setSizeFull();
        pform = new MentorsRolesForm();
        form = pform.createMentorsRolesForm();

        // Add Listeners
        pform.getSave().addListener((ClickListener) this);
        pform.getEdit().addListener((ClickListener) this);
        pform.getCancel().addListener((ClickListener) this);
        pform.getDelete().addListener((ClickListener) this);
        pform.getUpdate().addListener((ClickListener) this);

        MentorsRolesBean bean = new MentorsRolesBean();
        BeanItem item = new BeanItem(bean);
        form.setItemDataSource(item);
        form.setVisibleItemProperties(pform.orderList());

        addComponent(form);
        setComponentAlignment(form, Alignment.TOP_CENTER);
        table = new MentorsRolesTable(main);
        table.addListener((ValueChangeListener) this);
        addComponent(table);

    }

    @Override
    public void valueChange(ValueChangeEvent event) {
        Property property = event.getProperty();

        if (property == table) {
            Item record = table.getItem(table.getValue());
            MentorsRolesBean mentorsRoles = new MentorsRolesBean();
            mentorsRoles.setMentorsRoles(record.getItemProperty("Mentors Roles").toString());
            mentorsRoles.setId(new Long(table.getValue().toString()));

            if (mentorsRoles != form.getItemDataSource()) {
                BeanItem item = new BeanItem(mentorsRoles);
                form.setItemDataSource(item);
                // BUG enabling this Disables form.setVisibleItemProperties(cf.orderList());

                form.setReadOnly(true);
                //Buttons Behaviou
                pform.getSave().setVisible(false);
                pform.getEdit().setVisible(true);
                pform.getCancel().setVisible(true);
                pform.getDelete().setVisible(true);
                pform.getUpdate().setVisible(false);
            }

        }
    }

    @Override
    public void buttonClick(ClickEvent event) {
        final Button source = event.getButton();
        if (source == pform.getSave()) {
            saveNewMentorsRoles(form);
            main.mainView.setSecondComponent(new TrainingInformationMenuView(main, "ROLES"));
        } else if (source == pform.getEdit()) {
            form.setReadOnly(false);
            pform.getSave().setVisible(false);
            pform.getEdit().setVisible(false);
            pform.getCancel().setVisible(true);
            pform.getDelete().setVisible(false);
            pform.getUpdate().setVisible(true);
        } else if (source == pform.getCancel()) {
            main.mainView.setSecondComponent(new TrainingInformationMenuView(main, "ROLES"));
        } else if (source == pform.getUpdate()) {
            saveEditedMentorsRoles(form);

            main.mainView.setSecondComponent(new TrainingInformationMenuView(main, "ROLES"));

        } else if (source == pform.getDelete()) {
            deleteMentorsRoles(form);
            main.mainView.setSecondComponent(new TrainingInformationMenuView(main, "ROLES"));

        }

    }

    public void saveNewMentorsRoles(Form form) {
        TrainingFactory factory = data.getTrainingFactory();
        String fieldName = form.getField("mentorsRoles").getValue().toString();
        MentorsRoles c = factory.createMentorsRoles(fieldName);
        data.getMentorsRolesService().persist(c);
    }

    public void saveEditedMentorsRoles(Form form) {
        TrainingFactory factory = data.getTrainingFactory();
        String fieldName = form.getField("mentorsRoles").getValue().toString();
        Long mentoringId = Long.parseLong(form.getField("id").getValue().toString());
        MentorsRoles c = factory.updatedMentorsRoles(fieldName, mentoringId);
        data.getMentorsRolesService().merge(c);
    }

    public void deleteMentorsRoles(Form form) {
        TrainingFactory factory = data.getTrainingFactory();
        Long mentoringId = Long.parseLong(form.getField("id").getValue().toString());
        MentorsRoles c = factory.loadMentorsRoles(mentoringId);
        data.getMentorsRolesService().remove(c);
    }
}
