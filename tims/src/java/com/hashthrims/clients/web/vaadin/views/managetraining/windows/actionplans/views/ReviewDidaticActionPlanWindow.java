/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.managetraining.windows.actionplans.views;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.managementoring.views.windows.actionplans.form.ActionPlanForm;
import com.hashthrims.clients.web.vaadin.views.managementoring.views.windows.actionplans.model.ActionPlanBean;
import com.hashthrims.clients.web.vaadin.views.managementoring.views.windows.actionplans.table.ActionPlansTable;
import com.hashthrims.domain.EmployeeActionPlan;
import com.hashthrims.domain.Person;
import com.hashthrims.infrastructure.util.DataFieldsUtil;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.Window;
import java.util.Date;
import java.util.List;

/**
 *
 * @author boniface
 */
public class ReviewDidaticActionPlanWindow extends Window implements
        ClickListener, ValueChangeListener {

    private final HorizontalLayout footer = new HorizontalLayout();
    private final Button cancel = new Button("Cancel");
    private final HashThrimsMain main;
    private final Table actionPlantable;
    private final Person person;
    private static ClientDataService data = new ClientDataService();
    private final DataFieldsUtil fieldValues = new DataFieldsUtil();
    private final Form form;
    private final ActionPlanForm pform;

    public ReviewDidaticActionPlanWindow(Person p, Long sessionId, HashThrimsMain app) {
        setModal(true);
        setClosable(true);
        setHeight("600px");
        setWidth("800px");
        setPositionX(200);
        setPositionY(100);
        setCaption("New Mentoring Action Plan");
        main = app;
        pform = new ActionPlanForm();
        form = pform.createActionPlanForm();

        // Add Listeners
        pform.getSave().addListener((ClickListener) this);
        pform.getEdit().addListener((ClickListener) this);
        pform.getCancel().addListener((ClickListener) this);
        pform.getDelete().addListener((ClickListener) this);
        pform.getUpdate().addListener((ClickListener) this);
        person = p;
        pform.getSave().setVisible(false);

        final ActionPlanBean bean = new ActionPlanBean();
        final BeanItem item = new BeanItem(bean);
        form.setItemDataSource(item);
        form.setVisibleItemProperties(pform.orderList());
        form.setReadOnly(true);

        addComponent(form);

        actionPlantable = new ActionPlansTable(main, person);
        actionPlantable.addListener((ValueChangeListener) this);
        addComponent(actionPlantable);

        addComponent(footer);
    }

    @Override
    public void buttonClick(ClickEvent event) {
        final Button source = event.getButton();
        if (source == cancel) {
            close();
        }

        if (source == pform.getSave()) {
            close();
        } else if (source == pform.getEdit()) {
            form.setReadOnly(false);
            pform.getSave().setVisible(false);
            pform.getEdit().setVisible(false);
            pform.getCancel().setVisible(true);
            pform.getDelete().setVisible(false);
            pform.getUpdate().setVisible(true);
        } else if (source == pform.getCancel()) {
            close();
        } else if (source == pform.getUpdate()) {
            saveEditedFacilityType(form);

            close();

        } else if (source == pform.getDelete()) {
            deleteFacilityType(form);
            close();

        }
    }
    @Override
    public void valueChange(ValueChangeEvent event) {
        final Property property = event.getProperty();
        if (property == actionPlantable) {
            final Item record = actionPlantable.getItem(actionPlantable.getValue());
            final ActionPlanBean actionBean = new ActionPlanBean();

            actionBean.setId(new Long(actionPlantable.getValue().toString()));
            actionBean.setActionPlan(record.getItemProperty("Action Plan").toString());
            actionBean.setActionPlanreview(record.getItemProperty("Review").toString());
            actionBean.setActionPlanDate(fieldValues.getDateFromObject(record.getItemProperty("Date")));
            actionBean.setReviewDate(fieldValues.getDateFromObject(record.getItemProperty("Review Date")));
            actionBean.setStatus(record.getItemProperty("Status").toString());
            if (actionBean != form.getItemDataSource()) {
                final BeanItem item = new BeanItem(actionBean);
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

    private void saveEditedFacilityType(Form form) {
        final Long id = Long.parseLong(form.getField("id").getValue().toString());
        final Date actionPlansDate = fieldValues.getDateFields(form.getField("actionPlanDate").getValue());
        final Date reviewDate = fieldValues.getDateFields(form.getField("reviewDate").getValue());
        final String actionPlans = fieldValues.getStringFields(form.getField("actionPlan").getValue());
        final String actionPlanreview = fieldValues.getStringFields(form.getField("actionPlanreview").getValue());
        final String status = fieldValues.getStringFields(form.getField("status").getValue());
        List<EmployeeActionPlan> plans = person.getActionPlans();
        for (EmployeeActionPlan plan : plans) {
            if (plan.getId().equals(id)) {
                plan.setActionPlan(actionPlans);
                plan.setActionPlanDate(actionPlansDate);
                plan.setActionPlanreview(actionPlanreview);
                plan.setReviewPlanDate(reviewDate);
                plan.setStatus(status);
            }
        }
        data.getPersonService().merge(person);
    }

    private void deleteFacilityType(Form form) {
        EmployeeActionPlan deletePlan = null;
        final Long id = Long.parseLong(form.getField("id").getValue().toString());
        List<EmployeeActionPlan> plans = person.getActionPlans();
        for (EmployeeActionPlan plan : plans) {
            if (plan.getId().equals(id)) {
                deletePlan = plan;
            }
        }
        person.getActionPlans().remove(deletePlan);
        data.getPersonService().merge(person);
    }
}
