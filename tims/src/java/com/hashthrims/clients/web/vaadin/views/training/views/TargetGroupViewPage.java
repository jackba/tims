/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.training.views;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.training.TrainingCoursesMenuView;
import com.hashthrims.clients.web.vaadin.views.training.forms.TargetGroupForm;
import com.hashthrims.clients.web.vaadin.views.training.model.TargetGroupBean;
import com.hashthrims.clients.web.vaadin.views.training.tables.TargetGroupTable;
import com.hashthrims.clients.web.vaadin.views.traininginformation.TrainingInformationMenuView;
import com.hashthrims.clients.web.vaadin.views.traininginformation.forms.TrainingCourseCategoryForm;
import com.hashthrims.clients.web.vaadin.views.traininginformation.model.TrainingCourseCategoryBean;
import com.hashthrims.clients.web.vaadin.views.traininginformation.table.TrainingCourseCategoryTable;
import com.hashthrims.domain.traininglist.TargetGroup;
import com.hashthrims.domain.traininglist.TrainingCourseCategory;
import com.hashthrims.infrastructure.factories.TrainingFactory;
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
public class TargetGroupViewPage extends VerticalLayout implements
        ClickListener, ValueChangeListener {

    private HashThrimsMain main;
    private Form form;
    private TargetGroupForm pform;
    private static ClientDataService data = new ClientDataService();
    private TargetGroupTable table;

    public TargetGroupViewPage(HashThrimsMain app) {
        main = app;
        setSizeFull();
        pform = new TargetGroupForm();
        form = pform.createTrainingCourseCategoryForm();

        // Add Listeners
        pform.getSave().addListener((ClickListener) this);
        pform.getEdit().addListener((ClickListener) this);
        pform.getCancel().addListener((ClickListener) this);
        pform.getDelete().addListener((ClickListener) this);
        pform.getUpdate().addListener((ClickListener) this);

        TargetGroupBean bean = new TargetGroupBean();
        BeanItem item = new BeanItem(bean);
        form.setItemDataSource(item);
        form.setVisibleItemProperties(pform.orderList());

        addComponent(form);
        setComponentAlignment(form, Alignment.TOP_CENTER);
        table = new TargetGroupTable(main);
        table.addListener((ValueChangeListener) this);
        addComponent(table);

    }

    @Override
    public void valueChange(ValueChangeEvent event) {
        Property property = event.getProperty();

        if (property == table) {
            Item record = table.getItem(table.getValue());
            TargetGroupBean targetGroup = new TargetGroupBean();
            targetGroup.setTargetGroup(record.getItemProperty("Target Group").toString());
            targetGroup.setTargetGroupId(new Long(table.getValue().toString()));

            if (targetGroup != form.getItemDataSource()) {
                BeanItem item = new BeanItem(targetGroup);
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
            saveNewTargetGroup(form);
            main.mainView.setSecondComponent(new TrainingCoursesMenuView(main, "TARGET"));
        } else if (source == pform.getEdit()) {
            form.setReadOnly(false);
            pform.getSave().setVisible(false);
            pform.getEdit().setVisible(false);
            pform.getCancel().setVisible(true);
            pform.getDelete().setVisible(false);
            pform.getUpdate().setVisible(true);
        } else if (source == pform.getCancel()) {
            main.mainView.setSecondComponent(new TrainingCoursesMenuView(main, "TARGET"));
        } else if (source == pform.getUpdate()) {
            saveEditedTargetGroup(form);

            main.mainView.setSecondComponent(new TrainingCoursesMenuView(main, "TARGET"));

        } else if (source == pform.getDelete()) {
            deleteTargetGroup(form);
            main.mainView.setSecondComponent(new TrainingCoursesMenuView(main, "TARGET"));

        }

    }

    public void saveNewTargetGroup(Form form) {
        TrainingCoursesFactory factory = data.getTrainingCoursesFactory();
        String targetGroup = form.getField("targetGroup").getValue().toString();
        TargetGroup c = factory.createTargetGroup(targetGroup);
        data.getTargetGroupService().persist(c);
    }

    public void saveEditedTargetGroup(Form form) {
        TrainingCoursesFactory factory = data.getTrainingCoursesFactory();
        String targetGroup = form.getField("targetGroup").getValue().toString();
        Long targetGroupId = Long.parseLong(form.getField("targetGroupId").getValue().toString());
        TargetGroup c = factory.updatedTargetGroup(targetGroup, targetGroupId);
        data.getTargetGroupService().merge(c);
    }

    public void deleteTargetGroup(Form form) {
        TrainingCoursesFactory factory = data.getTrainingCoursesFactory();
        Long targetGroupId = Long.parseLong(form.getField("targetGroupId").getValue().toString());
        TargetGroup c = factory.loadTargetGroup(targetGroupId);
        data.getTargetGroupService().remove(c);
    }
}
