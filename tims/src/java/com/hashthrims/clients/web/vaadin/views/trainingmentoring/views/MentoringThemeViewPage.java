/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.trainingmentoring.views;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.trainingmentoring.TrainingMentoringMenuView;

import com.hashthrims.clients.web.vaadin.views.trainingmentoring.forms.MentoringThemeForm;
import com.hashthrims.clients.web.vaadin.views.trainingmentoring.model.MentoringThemeBean;
import com.hashthrims.clients.web.vaadin.views.trainingmentoring.tables.MentoringThemeTable;
import com.hashthrims.domain.traininglist.MentoringTheme;
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
public class MentoringThemeViewPage extends VerticalLayout implements
        ClickListener, ValueChangeListener {

    private final HashThrimsMain main;
    private final Form form;
    private final MentoringThemeForm pform;
    private static final ClientDataService data = new ClientDataService();
    private final MentoringThemeTable table;
    private final TrainingCoursesFactory factory = data.getTrainingCoursesFactory();

    public MentoringThemeViewPage(HashThrimsMain app) {
        main = app;
        setSizeFull();
        pform = new MentoringThemeForm();
        form = pform.createMentoringThemeForm();

        // Add Listeners
        pform.getSave().addListener((ClickListener) this);
        pform.getEdit().addListener((ClickListener) this);
        pform.getCancel().addListener((ClickListener) this);
        pform.getDelete().addListener((ClickListener) this);
        pform.getUpdate().addListener((ClickListener) this);

        final MentoringThemeBean bean = new MentoringThemeBean();
        final BeanItem item = new BeanItem(bean);
        form.setItemDataSource(item);
        form.setVisibleItemProperties(pform.orderList());

        addComponent(form);
        setComponentAlignment(form, Alignment.TOP_CENTER);
        table = new MentoringThemeTable(main);
        table.addListener((ValueChangeListener) this);
        addComponent(table);

    }

    @Override
    public void valueChange(ValueChangeEvent event) {
        final Property property = event.getProperty();

        if (property == table) {
            final Item record = table.getItem(table.getValue());
            final MentoringThemeBean mentoringTheme = new MentoringThemeBean();
            mentoringTheme.setMentoringField(record.getItemProperty("Mentoring Field").toString());
            mentoringTheme.setMentoringTheme(record.getItemProperty("Mentoring Theme").toString());
            mentoringTheme.setId(new Long(table.getValue().toString()));


            if (mentoringTheme != form.getItemDataSource()) {
                final BeanItem item = new BeanItem(mentoringTheme);
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
            saveNewMentoringTheme(form);
            main.mainView.setSecondComponent(new TrainingMentoringMenuView(main, "THEME"));
        } else if (source == pform.getEdit()) {
            form.setReadOnly(false);
            pform.getSave().setVisible(false);
            pform.getEdit().setVisible(false);
            pform.getCancel().setVisible(true);
            pform.getDelete().setVisible(false);
            pform.getUpdate().setVisible(true);
        } else if (source == pform.getCancel()) {
            main.mainView.setSecondComponent(new TrainingMentoringMenuView(main, "THEME"));
        } else if (source == pform.getUpdate()) {
            saveEditedMentoringTheme(form);

            main.mainView.setSecondComponent(new TrainingMentoringMenuView(main, "THEME"));

        } else if (source == pform.getDelete()) {
            deleteScheduleCourses(form);
            main.mainView.setSecondComponent(new TrainingMentoringMenuView(main, "THEME"));

        }

    }

    public void saveNewMentoringTheme(Form form) {
        
        final String mentoringTheme = form.getField("mentoringTheme").getValue().toString();
        final String mentoringField = form.getField("mentoringField").getValue().toString();
        final MentoringTheme c = factory.createMentoringTheme(mentoringTheme, mentoringField);
        data.getMentoringThemeService().persist(c);
    }

    public void saveEditedMentoringTheme(Form form) {
        final String mentoringTheme = form.getField("mentoringTheme").getValue().toString();
        final String mentoringField = form.getField("mentoringField").getValue().toString();
        final Long MentoringThemeId = Long.parseLong(form.getField("id").getValue().toString());
        final MentoringTheme c = factory.updateMentoringTheme(mentoringTheme, mentoringField, MentoringThemeId);
        data.getMentoringThemeService().merge(c);
    }

    public void deleteScheduleCourses(Form form) {
       
        final Long mentoringId = Long.parseLong(form.getField("id").getValue().toString());
        final MentoringTheme c = factory.loadMentoringTheme(mentoringId);
        data.getMentoringThemeService().remove(c);
    }
}
