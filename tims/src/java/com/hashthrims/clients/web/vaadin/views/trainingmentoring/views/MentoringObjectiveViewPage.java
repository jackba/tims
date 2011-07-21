/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.trainingmentoring.views;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.trainingmentoring.TrainingMentoringMenuView;
import com.hashthrims.clients.web.vaadin.views.trainingmentoring.forms.MentoringObjectiveForm;

import com.hashthrims.clients.web.vaadin.views.trainingmentoring.model.MentoringObjectiveBean;
import com.hashthrims.clients.web.vaadin.views.trainingmentoring.tables.MentoringObjectiveTable;
import com.hashthrims.domain.traininglist.MentoringAreasList;
import com.hashthrims.domain.traininglist.MentoringObjective;
import com.hashthrims.infrastructure.factories.traininglist.TrainingCoursesFactory;
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
 * @author boniface
 */
public class MentoringObjectiveViewPage extends VerticalLayout implements
        ClickListener, ValueChangeListener {

    private final HashThrimsMain main;
    private final Form form;
    private final MentoringObjectiveForm pform;
    private static final ClientDataService data = new ClientDataService();
    final TrainingCoursesFactory factory = data.getTrainingCoursesFactory();
    private final MentoringObjectiveTable table;

    public MentoringObjectiveViewPage(HashThrimsMain app) {
        main = app;
        setSizeFull();
        pform = new MentoringObjectiveForm();
        form = pform.createMentoringObjectiveForm();


        // Add Listeners
        pform.getSave().addListener((ClickListener) this);
        pform.getEdit().addListener((ClickListener) this);
        pform.getCancel().addListener((ClickListener) this);
        pform.getDelete().addListener((ClickListener) this);
        pform.getUpdate().addListener((ClickListener) this);

        final MentoringObjectiveBean bean = new MentoringObjectiveBean();
        final BeanItem item = new BeanItem(bean);
        form.setItemDataSource(item);
        form.setVisibleItemProperties(pform.orderList());

        addComponent(form);
        setComponentAlignment(form, Alignment.TOP_CENTER);
        table = new MentoringObjectiveTable(main);
        table.addListener((ValueChangeListener) this);
        addComponent(table);

    }

    @Override
    public void valueChange(ValueChangeEvent event) {
        final Property property = event.getProperty();

        if (property == table) {
            final Item record = table.getItem(table.getValue());
            final MentoringObjectiveBean mentoringObjective = new MentoringObjectiveBean();
            mentoringObjective.setMentoringObjective(record.getItemProperty("Mentoring Objectives List").toString());
            mentoringObjective.setId(new Long(table.getValue().toString()));


            if (mentoringObjective != form.getItemDataSource()) {
                final BeanItem item = new BeanItem(mentoringObjective);
                form.setItemDataSource(item);
                form.setVisibleItemProperties(pform.orderList());
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
            saveNewMentoringObjective(form);
            main.mainView.setSecondComponent(new TrainingMentoringMenuView(main, "OBJ"));
        } else if (source == pform.getEdit()) {
            form.setReadOnly(false);
            pform.getSave().setVisible(false);
            pform.getEdit().setVisible(false);
            pform.getCancel().setVisible(true);
            pform.getDelete().setVisible(false);
            pform.getUpdate().setVisible(true);
        } else if (source == pform.getCancel()) {
            main.mainView.setSecondComponent(new TrainingMentoringMenuView(main, "OBJ"));
        } else if (source == pform.getUpdate()) {
            saveEditedMentoringObjective(form);

            main.mainView.setSecondComponent(new TrainingMentoringMenuView(main, "OBJ"));

        } else if (source == pform.getDelete()) {
            deleteMentoringObjective(form);
            main.mainView.setSecondComponent(new TrainingMentoringMenuView(main, "OBJ"));

        }

    }

    public void saveNewMentoringObjective(Form form) {      
        final String mentoringObjective = form.getField("mentoringObjective").getValue().toString();
        final MentoringObjective c = factory.createMentoringObjective(mentoringObjective);
        data.getMentoringObjectiveService().persist(c);
    }

    public void saveEditedMentoringObjective(Form form) {
        final String mentoringObjective = form.getField("mentoringObjective").getValue().toString();
        final Long sessionId = Long.parseLong(form.getField("id").getValue().toString());
        final MentoringObjective c = factory.updateMentoringObjective(mentoringObjective, sessionId);
        data.getMentoringObjectiveService().merge(c);
    }

    public void deleteMentoringObjective(Form form) {
        final Long mentoringId = Long.parseLong(form.getField("id").getValue().toString());
        final MentoringObjective c = factory.loadMentoringObjective(mentoringId);
        data.getMentoringObjectiveService().remove(c);
    }
}
