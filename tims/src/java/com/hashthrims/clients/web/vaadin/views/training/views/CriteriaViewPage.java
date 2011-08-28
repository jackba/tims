/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.training.views;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.training.TrainingCoursesMenuView;
import com.hashthrims.clients.web.vaadin.views.training.forms.CriteriaForm;
import com.hashthrims.clients.web.vaadin.views.training.model.CriteriaBean;
import com.hashthrims.clients.web.vaadin.views.training.tables.CriteriaTable;
import com.hashthrims.domain.traininglist.Criteria;
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
 * @author bulelani
 */
public class CriteriaViewPage extends VerticalLayout implements
        ClickListener, ValueChangeListener{

    private HashThrimsMain main;
    private Form form;
    private CriteriaForm pform;
     private static ClientDataService data = new ClientDataService();
    private CriteriaTable table;

    public CriteriaViewPage(HashThrimsMain app) {
        main = app;
        setSizeFull();
        pform = new CriteriaForm();
        form = pform.createCriteriaForm();

        // Add Listeners
        pform.getSave().addListener((ClickListener) this);
        pform.getEdit().addListener((ClickListener) this);
        pform.getCancel().addListener((ClickListener) this);
        pform.getDelete().addListener((ClickListener) this);
        pform.getUpdate().addListener((ClickListener) this);

       CriteriaBean bean = new CriteriaBean();
        BeanItem item = new BeanItem(bean);
        form.setItemDataSource(item);
        form.setVisibleItemProperties(pform.orderList());

        addComponent(form);
        setComponentAlignment(form, Alignment.TOP_CENTER);
        table = new CriteriaTable(main);
        table.addListener((ValueChangeListener) this);
        addComponent(table);

    }

    @Override
    public void valueChange(ValueChangeEvent event) {
        Property property = event.getProperty();

        if (property == table) {
            Item record = table.getItem(table.getValue());
          CriteriaBean criteria = new CriteriaBean();
            criteria.setCriteria(record.getItemProperty("Selection Criteria").toString());
            criteria.setCriteriaId(new Long(table.getValue().toString()));
           
            if (criteria != form.getItemDataSource()) {
                BeanItem item = new BeanItem(criteria);
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
            saveNewCriteria(form);
            main.mainView.setSecondComponent(new TrainingCoursesMenuView(main, "CRITERIA"));
        } else if (source == pform.getEdit()) {
            form.setReadOnly(false);
            pform.getSave().setVisible(false);
            pform.getEdit().setVisible(false);
            pform.getCancel().setVisible(true);
            pform.getDelete().setVisible(false);
            pform.getUpdate().setVisible(true);
        } else if (source == pform.getCancel()) {
            main.mainView.setSecondComponent(new TrainingCoursesMenuView(main, "CRITERIA"));
        } else if (source == pform.getUpdate()) {
            saveEditedCriteria(form);

            main.mainView.setSecondComponent(new TrainingCoursesMenuView(main, "CRITERIA"));

        } else if (source == pform.getDelete()) {
            deleteCriteria(form);
            main.mainView.setSecondComponent(new TrainingCoursesMenuView(main, "CRITERIA"));

        }

    }

    public void saveNewCriteria(Form form) {
        TrainingCoursesFactory factory = data.getTrainingCoursesFactory();
        String criteria = form.getField("criteria").getValue().toString();
        Criteria c = factory.createCriteria(criteria);
        data.getCriteriaService().persist(c);
    }

    public void saveEditedCriteria(Form form) {
       TrainingCoursesFactory factory = data.getTrainingCoursesFactory();
        String criteria = form.getField("criteria").getValue().toString();      
        Long criteriaId = Long.parseLong(form.getField("criteriaId").getValue().toString());
        Criteria c = factory.updatedCriteria(criteria, criteriaId);
        data.getCriteriaService().merge(c);
    }

    public void deleteCriteria(Form form) {
       TrainingCoursesFactory factory = data.getTrainingCoursesFactory();
        Long criteriaId = Long.parseLong(form.getField("criteriaId").getValue().toString());
        Criteria c = factory.loadCriteria(criteriaId);
        data.getCriteriaService().remove(c);
    }
}
