/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.traininginformation.views;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.traininginformation.TrainingInformationMenuView;
import com.hashthrims.clients.web.vaadin.views.traininginformation.forms.MentoringFieldForm;
import com.hashthrims.clients.web.vaadin.views.traininginformation.model.MentoringFieldBean;
import com.hashthrims.clients.web.vaadin.views.traininginformation.table.MentoringFieldTable;
import com.hashthrims.domain.traininglist.MentoringField;
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
public class MentoringFieldViewPage extends VerticalLayout implements
        ClickListener, ValueChangeListener {

    private HashThrimsMain main;
    private Form form;
    private MentoringFieldForm pform;
    private static ClientDataService data = new ClientDataService();
    private MentoringFieldTable table;

    public MentoringFieldViewPage(HashThrimsMain app) {
        main = app;
        setSizeFull();
        pform = new MentoringFieldForm();
        form = pform.createMentoringFieldForm();

        // Add Listeners
        pform.getSave().addListener((ClickListener) this);
        pform.getEdit().addListener((ClickListener) this);
        pform.getCancel().addListener((ClickListener) this);
        pform.getDelete().addListener((ClickListener) this);
        pform.getUpdate().addListener((ClickListener) this);

        MentoringFieldBean bean = new MentoringFieldBean();
        BeanItem item = new BeanItem(bean);
        form.setItemDataSource(item);
        form.setVisibleItemProperties(pform.orderList());

        addComponent(form);
        setComponentAlignment(form, Alignment.TOP_CENTER);
        table = new MentoringFieldTable(main);
        table.addListener((ValueChangeListener) this);
        addComponent(table);

    }

    @Override
    public void valueChange(ValueChangeEvent event) {
        Property property = event.getProperty();

        if (property == table) {
            Item record = table.getItem(table.getValue());
            MentoringFieldBean mentoringField = new MentoringFieldBean();
            mentoringField.setFieldName(record.getItemProperty("Mentoring Field").toString());
            mentoringField.setMentoringId(new Long(table.getValue().toString()));

            if (mentoringField != form.getItemDataSource()) {
                BeanItem item = new BeanItem(mentoringField);
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
            saveNewMentoringField(form);
            main.mainView.setSecondComponent(new TrainingInformationMenuView(main, "MENTORING"));
        } else if (source == pform.getEdit()) {
            form.setReadOnly(false);
            pform.getSave().setVisible(false);
            pform.getEdit().setVisible(false);
            pform.getCancel().setVisible(true);
            pform.getDelete().setVisible(false);
            pform.getUpdate().setVisible(true);
        } else if (source == pform.getCancel()) {
            main.mainView.setSecondComponent(new TrainingInformationMenuView(main, "MENTORING"));
        } else if (source == pform.getUpdate()) {
            saveEditedMentoringField(form);

            main.mainView.setSecondComponent(new TrainingInformationMenuView(main, "MENTORING"));

        } else if (source == pform.getDelete()) {
            deleteMentoringField(form);
            main.mainView.setSecondComponent(new TrainingInformationMenuView(main, "MENTORING"));

        }

    }

    public void saveNewMentoringField(Form form) {
        TrainingFactory factory = data.getTrainingFactory();
        String fieldName = form.getField("fieldName").getValue().toString();
        MentoringField c = factory.createMentoringField(fieldName);
        data.getMentoringFieldService().persist(c);
    }

    public void saveEditedMentoringField(Form form) {
        TrainingFactory factory = data.getTrainingFactory();
        String fieldName = form.getField("fieldName").getValue().toString();
        Long mentoringId = Long.parseLong(form.getField("mentoringId").getValue().toString());
        MentoringField c = factory.updatedMentoringField(fieldName, mentoringId);
        data.getMentoringFieldService().merge(c);
    }

    public void deleteMentoringField(Form form) {
        TrainingFactory factory = data.getTrainingFactory();
        Long mentoringId = Long.parseLong(form.getField("mentoringId").getValue().toString());
        MentoringField c = factory.loadMentoringField(mentoringId);
        data.getMentoringFieldService().remove(c);
    }
}
