/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.traininginformation.views;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.traininginformation.TrainingInformationMenuView;
import com.hashthrims.clients.web.vaadin.views.traininginformation.forms.TrainingCourseEvaluationForm;
import com.hashthrims.clients.web.vaadin.views.traininginformation.model.TrainingCourseEvaluationBean;
import com.hashthrims.clients.web.vaadin.views.traininginformation.table.TrainingCourseEvaluationTable;
import com.hashthrims.domain.traininglist.TrainingCourseEvaluation;
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
 * @author boniface
 */
public class TrainingCourseEvaluationViewPage extends VerticalLayout implements
        ClickListener, ValueChangeListener {

    private HashThrimsMain main;
    private Form form;
    private TrainingCourseEvaluationForm pform;
     private static ClientDataService data = new ClientDataService();
    private TrainingCourseEvaluationTable table;

    public TrainingCourseEvaluationViewPage(HashThrimsMain app) {
        main = app;
        setSizeFull();
        pform = new TrainingCourseEvaluationForm();
        form = pform.createTrainingCourseEvaluationForm();

        // Add Listeners
        pform.getSave().addListener((ClickListener) this);
        pform.getEdit().addListener((ClickListener) this);
        pform.getCancel().addListener((ClickListener) this);
        pform.getDelete().addListener((ClickListener) this);
        pform.getUpdate().addListener((ClickListener) this);

        TrainingCourseEvaluationBean bean = new TrainingCourseEvaluationBean();
        BeanItem item = new BeanItem(bean);
        form.setItemDataSource(item);
        form.setVisibleItemProperties(pform.orderList());

        addComponent(form);
        setComponentAlignment(form, Alignment.TOP_CENTER);
        table = new TrainingCourseEvaluationTable(main);
        table.addListener((ValueChangeListener) this);
        addComponent(table);

    }

    @Override
    public void valueChange(ValueChangeEvent event) {
        Property property = event.getProperty();

        if (property == table) {
            Item record = table.getItem(table.getValue());
            TrainingCourseEvaluationBean courseEvaluation = new TrainingCourseEvaluationBean();
            courseEvaluation.setCourseEvaluationId(new Long(table.getValue().toString()));
            courseEvaluation.setCourseEvaluationName(record.getItemProperty("Evaluation Name").toString());
            if (courseEvaluation != form.getItemDataSource()) {
                BeanItem item = new BeanItem(courseEvaluation);
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
            saveNewTrainingCourseEvaluation(form);
            main.mainView.setSecondComponent(new TrainingInformationMenuView(main, "EVALUATION"));
        } else if (source == pform.getEdit()) {
            form.setReadOnly(false);
            pform.getSave().setVisible(false);
            pform.getEdit().setVisible(false);
            pform.getCancel().setVisible(true);
            pform.getDelete().setVisible(false);
            pform.getUpdate().setVisible(true);
        } else if (source == pform.getCancel()) {
            main.mainView.setSecondComponent(new TrainingInformationMenuView(main, "EVALUATION"));
        } else if (source == pform.getUpdate()) {
            saveEditedTrainingCourseEvaluation(form);

            main.mainView.setSecondComponent(new TrainingInformationMenuView(main, "EVALUATION"));

        } else if (source == pform.getDelete()) {
            deleteTrainingCourseEvaluation(form);
            main.mainView.setSecondComponent(new TrainingInformationMenuView(main, "EVALUATION"));

        }

    }

    public void saveNewTrainingCourseEvaluation(Form form) {
        TrainingFactory factory = data.getTrainingFactory();
        String courseEvaluationName = form.getField("courseEvaluationName").getValue().toString();
        TrainingCourseEvaluation c = factory.createTrainingCourseEvaluation(courseEvaluationName);
        data.getTrainingCourseEvaluationService().persist(c);
    }

    public void saveEditedTrainingCourseEvaluation(Form form) {
        TrainingFactory factory = data.getTrainingFactory();
    
        String courseEvaluationName = form.getField("courseEvaluationName").getValue().toString();
        Long courseId = Long.parseLong(form.getField("courseEvaluationId").getValue().toString());
         TrainingCourseEvaluation c = factory.updateTrainingCourseEvaluation(courseEvaluationName,courseId );
         data.getTrainingCourseEvaluationService().merge(c);
    }

    public void deleteTrainingCourseEvaluation(Form form) {
        TrainingFactory factory = data.getTrainingFactory();
        Long courseId = Long.parseLong(form.getField("courseEvaluationId").getValue().toString());
        TrainingCourseEvaluation c = factory.loadTrainingCourseEvaluation(courseId);
        data.getTrainingCourseEvaluationService().remove(c);
    }
}
