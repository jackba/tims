/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.trainingmentoring.views;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.trainingmentoring.TrainingMentoringMenuView;

import com.hashthrims.clients.web.vaadin.views.trainingmentoring.forms.MentoringSessionTypeForm;
import com.hashthrims.clients.web.vaadin.views.trainingmentoring.model.MentoringSessionTypeBean;
import com.hashthrims.clients.web.vaadin.views.trainingmentoring.tables.MentoringSessionTypeTable;
import com.hashthrims.domain.traininglist.SessionType;
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
public class MentoringSessionTypeViewPage extends VerticalLayout implements
        ClickListener, ValueChangeListener {

    private final HashThrimsMain main;
    private final Form form;
    private final MentoringSessionTypeForm pform;
    private static final ClientDataService data = new ClientDataService();
    final TrainingCoursesFactory factory = data.getTrainingCoursesFactory();
    private final MentoringSessionTypeTable table;

    public MentoringSessionTypeViewPage(HashThrimsMain app) {
        main = app;
        setSizeFull();
        pform = new MentoringSessionTypeForm();
        form = pform.createMentoringSessionTypeForm();


        // Add Listeners
        pform.getSave().addListener((ClickListener) this);
        pform.getEdit().addListener((ClickListener) this);
        pform.getCancel().addListener((ClickListener) this);
        pform.getDelete().addListener((ClickListener) this);
        pform.getUpdate().addListener((ClickListener) this);

        final MentoringSessionTypeBean bean = new MentoringSessionTypeBean();
        final BeanItem item = new BeanItem(bean);
        form.setItemDataSource(item);
        form.setVisibleItemProperties(pform.orderList());

        addComponent(form);
        setComponentAlignment(form, Alignment.TOP_CENTER);
        table = new MentoringSessionTypeTable(main);
        table.addListener((ValueChangeListener) this);
        addComponent(table);

    }

    @Override
    public void valueChange(ValueChangeEvent event) {
        final Property property = event.getProperty();

        if (property == table) {
            final Item record = table.getItem(table.getValue());
            final MentoringSessionTypeBean mentoringSessionType = new MentoringSessionTypeBean();
            mentoringSessionType.setMentoringSessionType(record.getItemProperty("Tools and Methods").toString());
            mentoringSessionType.setId(new Long(table.getValue().toString()));


            if (mentoringSessionType != form.getItemDataSource()) {
                final BeanItem item = new BeanItem(mentoringSessionType);
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
            saveNewMentoringSessionType(form);
            main.mainView.setSecondComponent(new TrainingMentoringMenuView(main, "TYPE"));
        } else if (source == pform.getEdit()) {
            form.setReadOnly(false);
            pform.getSave().setVisible(false);
            pform.getEdit().setVisible(false);
            pform.getCancel().setVisible(true);
            pform.getDelete().setVisible(false);
            pform.getUpdate().setVisible(true);
        } else if (source == pform.getCancel()) {
            main.mainView.setSecondComponent(new TrainingMentoringMenuView(main, "TYPE"));
        } else if (source == pform.getUpdate()) {
            saveEditedMentoringSessionType(form);

            main.mainView.setSecondComponent(new TrainingMentoringMenuView(main, "TYPE"));

        } else if (source == pform.getDelete()) {
            deleteScheduleCourses(form);
            main.mainView.setSecondComponent(new TrainingMentoringMenuView(main, "TYPE"));

        }

    }

    public void saveNewMentoringSessionType(Form form) {      
        final String mentoringSessionType = form.getField("mentoringSessionType").getValue().toString();
        final SessionType c = factory.createMentoringSessionType(mentoringSessionType);
        data.getMentoringSessionTypeService().persist(c);
    }

    public void saveEditedMentoringSessionType(Form form) {
        final String mentoringSessionType = form.getField("mentoringSessionType").getValue().toString();
        final Long sessionId = Long.parseLong(form.getField("id").getValue().toString());
        final SessionType c = factory.updateMentoringSessionType(mentoringSessionType, sessionId);
        data.getMentoringSessionTypeService().merge(c);
    }

    public void deleteScheduleCourses(Form form) {
        final Long mentoringId = Long.parseLong(form.getField("id").getValue().toString());
        final SessionType c = factory.loadMentoringSessionType(mentoringId);
        data.getMentoringSessionTypeService().remove(c);
    }
}
