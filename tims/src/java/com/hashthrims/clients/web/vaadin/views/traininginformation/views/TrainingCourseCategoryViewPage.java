/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.traininginformation.views;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.traininginformation.TrainingInformationMenuView;
import com.hashthrims.clients.web.vaadin.views.traininginformation.forms.TrainingCourseCategoryForm;
import com.hashthrims.clients.web.vaadin.views.traininginformation.model.TrainingCourseCategoryBean;
import com.hashthrims.clients.web.vaadin.views.traininginformation.table.TrainingCourseCategoryTable;
import com.hashthrims.domain.traininglist.TrainingCourseCategory;
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
public class TrainingCourseCategoryViewPage extends VerticalLayout implements
        ClickListener, ValueChangeListener{

    private HashThrimsMain main;
    private Form form;
    private TrainingCourseCategoryForm pform;
     private static ClientDataService data = new ClientDataService();
    private TrainingCourseCategoryTable table;

    public TrainingCourseCategoryViewPage(HashThrimsMain app) {
        main = app;
        setSizeFull();
        pform = new TrainingCourseCategoryForm();
        form = pform.createTrainingCourseCategoryForm();

        // Add Listeners
        pform.getSave().addListener((ClickListener) this);
        pform.getEdit().addListener((ClickListener) this);
        pform.getCancel().addListener((ClickListener) this);
        pform.getDelete().addListener((ClickListener) this);
        pform.getUpdate().addListener((ClickListener) this);

       TrainingCourseCategoryBean bean = new TrainingCourseCategoryBean();
        BeanItem item = new BeanItem(bean);
        form.setItemDataSource(item);
        form.setVisibleItemProperties(pform.orderList());

        addComponent(form);
        setComponentAlignment(form, Alignment.TOP_CENTER);
        table = new TrainingCourseCategoryTable(main);
        table.addListener((ValueChangeListener) this);
        addComponent(table);

    }

    @Override
    public void valueChange(ValueChangeEvent event) {
        Property property = event.getProperty();

        if (property == table) {
            Item record = table.getItem(table.getValue());
          TrainingCourseCategoryBean category = new TrainingCourseCategoryBean();
            category.setCourseCatergory(record.getItemProperty("Training Field").toString());
            category.setCategoryId(new Long(table.getValue().toString()));
           
            if (category != form.getItemDataSource()) {
                BeanItem item = new BeanItem(category);
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
            saveNewTrainingCourseCategory(form);
            main.mainView.setSecondComponent(new TrainingInformationMenuView(main,"CATEGORY"));
        } else if (source == pform.getEdit()) {
            form.setReadOnly(false);
            pform.getSave().setVisible(false);
            pform.getEdit().setVisible(false);
            pform.getCancel().setVisible(true);
            pform.getDelete().setVisible(false);
            pform.getUpdate().setVisible(true);
        } else if (source == pform.getCancel()) {
            main.mainView.setSecondComponent(new TrainingInformationMenuView(main,"CATEGORY"));
        } else if (source == pform.getUpdate()) {
            saveEditedTrainingCourseCategory(form);

            main.mainView.setSecondComponent(new TrainingInformationMenuView(main,"CATEGORY"));

        } else if (source == pform.getDelete()) {
            deleteTrainingCourseCategory(form);
            main.mainView.setSecondComponent(new TrainingInformationMenuView(main,"CATEGORY"));

        }

    }

    public void saveNewTrainingCourseCategory(Form form) {
        TrainingFactory factory = data.getTrainingFactory();
        String category = form.getField("courseCatergory").getValue().toString();
        TrainingCourseCategory c = factory.createTrainingCourseCategory(category);
        data.getTrainingCourseCategoryService().persist(c);
    }

    public void saveEditedTrainingCourseCategory(Form form) {
        TrainingFactory factory = data.getTrainingFactory();
        String categoryName = form.getField("courseCatergory").getValue().toString();      
        Long categoryId = Long.parseLong(form.getField("categoryId").getValue().toString());
        TrainingCourseCategory  c = factory.updatedTrainingCourseCategory(categoryName, categoryId);
        data.getTrainingCourseCategoryService().merge(c);
    }

    public void deleteTrainingCourseCategory(Form form) {
        TrainingFactory factory = data.getTrainingFactory();
        Long categoryId = Long.parseLong(form.getField("categoryId").getValue().toString());
        TrainingCourseCategory c = factory.loadTrainingCourseCategory(categoryId);
        data.getTrainingCourseCategoryService().remove(c);
    }
}
