/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.trainingmentoring.views;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.trainingmentoring.TrainingMentoringMenuView;
import com.hashthrims.clients.web.vaadin.views.trainingmentoring.forms.MentoringAreasListTypeForm;

import com.hashthrims.clients.web.vaadin.views.trainingmentoring.forms.MentoringSessionTypeForm;
import com.hashthrims.clients.web.vaadin.views.trainingmentoring.model.MentoringAreasListBean;
import com.hashthrims.clients.web.vaadin.views.trainingmentoring.model.MentoringSessionTypeBean;
import com.hashthrims.clients.web.vaadin.views.trainingmentoring.tables.MentoringAreasListTable;
import com.hashthrims.clients.web.vaadin.views.trainingmentoring.tables.MentoringSessionTypeTable;
import com.hashthrims.domain.traininglist.MentoringAreasList;
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
public class MentoringAreasListViewPage extends VerticalLayout implements
        ClickListener, ValueChangeListener {

    private final HashThrimsMain main;
    private final Form form;
    private final MentoringAreasListTypeForm pform;
    private static final ClientDataService data = new ClientDataService();
    final TrainingCoursesFactory factory = data.getTrainingCoursesFactory();
    private final MentoringAreasListTable table;

    public MentoringAreasListViewPage(HashThrimsMain app) {
        main = app;
        setSizeFull();
        pform = new MentoringAreasListTypeForm();
        form = pform.createMentoringAreasListForm();


        // Add Listeners
        pform.getSave().addListener((ClickListener) this);
        pform.getEdit().addListener((ClickListener) this);
        pform.getCancel().addListener((ClickListener) this);
        pform.getDelete().addListener((ClickListener) this);
        pform.getUpdate().addListener((ClickListener) this);

        final MentoringAreasListBean bean = new MentoringAreasListBean();
        final BeanItem item = new BeanItem(bean);
        form.setItemDataSource(item);
        form.setVisibleItemProperties(pform.orderList());

        addComponent(form);
        setComponentAlignment(form, Alignment.TOP_CENTER);
        table = new MentoringAreasListTable(main);
        table.addListener((ValueChangeListener) this);
        addComponent(table);

    }

    @Override
    public void valueChange(ValueChangeEvent event) {
        final Property property = event.getProperty();

        if (property == table) {
            final Item record = table.getItem(table.getValue());
            final MentoringAreasListBean mentoringAreasList = new MentoringAreasListBean();
            mentoringAreasList.setAreasOfStrenthening(record.getItemProperty("Mentoring Areas List").toString());
            mentoringAreasList.setId(new Long(table.getValue().toString()));


            if (mentoringAreasList != form.getItemDataSource()) {
                final BeanItem item = new BeanItem(mentoringAreasList);
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
            saveNewMentoringAreasList(form);
            main.mainView.setSecondComponent(new TrainingMentoringMenuView(main, "ST"));
        } else if (source == pform.getEdit()) {
            form.setReadOnly(false);
            pform.getSave().setVisible(false);
            pform.getEdit().setVisible(false);
            pform.getCancel().setVisible(true);
            pform.getDelete().setVisible(false);
            pform.getUpdate().setVisible(true);
        } else if (source == pform.getCancel()) {
            main.mainView.setSecondComponent(new TrainingMentoringMenuView(main, "ST"));
        } else if (source == pform.getUpdate()) {
            saveEditedMentoringAreasList(form);

            main.mainView.setSecondComponent(new TrainingMentoringMenuView(main, "ST"));

        } else if (source == pform.getDelete()) {
            deleteMentoringAreasList(form);
            main.mainView.setSecondComponent(new TrainingMentoringMenuView(main, "ST"));

        }

    }

    public void saveNewMentoringAreasList(Form form) {      
        final String areasOfStrenthening = form.getField("areasOfStrenthening").getValue().toString();
        final MentoringAreasList c = factory.createMentoringAreasList(areasOfStrenthening);
        data.getMentoringAreasListService().persist(c);
    }

    public void saveEditedMentoringAreasList(Form form) {
        final String areasOfStrenthening = form.getField("areasOfStrenthening").getValue().toString();
        final Long sessionId = Long.parseLong(form.getField("id").getValue().toString());
        final MentoringAreasList c = factory.updateMentoringAreasList(areasOfStrenthening, sessionId);
        data.getMentoringAreasListService().merge(c);
    }

    public void deleteMentoringAreasList(Form form) {
        final Long mentoringId = Long.parseLong(form.getField("id").getValue().toString());
        final MentoringAreasList c = factory.loadMentoringAreasList(mentoringId);
        data.getMentoringAreasListService().remove(c);
    }
}
