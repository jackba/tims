/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.managetraining.windows.actionplans.form;

import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.vaadin.data.Item;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Select;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author boniface
 */
public class ActionPlanForm {

    // Define Buttons
    private Button save = new Button("Save");
    private Button edit = new Button("Edit");
    private Button cancel = new Button("Cancel");
    private Button update = new Button("Update");
    private Button delete = new Button("Delete");
    //Define Footer
    private HorizontalLayout footer;
    private static ClientDataService data = new ClientDataService();

    public ActionPlanForm() {
    }

    public Form createActionPlanForm() {
        Form form = new Form();
        form.setCaption("Review Action Plan Mentoring Form");
        form.setImmediate(false);
        form.setFormFieldFactory(new ActionPlanFactory());

        // Add Listeners

        footer = new HorizontalLayout();
        footer.setSpacing(true);
        footer.addComponent(save);
        footer.addComponent(cancel);
        footer.addComponent(edit);
        footer.addComponent(update);
        footer.addComponent(delete);
        footer.setVisible(true);
        footer.setMargin(true);
        // Determines which properties are shown, and in which order:
        edit.setVisible(false);
        update.setVisible(false);
        delete.setVisible(false);
        form.setWriteThrough(false);
        form.setFooter(footer);

        return form;
    }

    public List orderList() {
        List order = new ArrayList();
        order.add("id");
        order.add("actionPlanDate");
        order.add("actionPlan");
        order.add("status");
        order.add("actionPlanreview");
        order.add("reviewDate");
        return order;
    }

    /**
     * @return the delete
     */
    public Button getDelete() {
        return delete;
    }

    /**
     * @param delete the delete to set
     */
    public void setDelete(Button delete) {
        this.delete = delete;
    }

    /**
     * @return the save
     */
    public Button getSave() {
        return save;
    }

    /**
     * @param save the save to set
     */
    public void setSave(Button save) {
        this.save = save;
    }

    /**
     * @return the edit
     */
    public Button getEdit() {
        return edit;
    }

    /**
     * @param edit the edit to set
     */
    public void setEdit(Button edit) {
        this.edit = edit;
    }

    /**
     * @return the cancel
     */
    public Button getCancel() {
        return cancel;
    }

    /**
     * @param cancel the cancel to set
     */
    public void setCancel(Button cancel) {
        this.cancel = cancel;
    }

    /**
     * @return the update
     */
    public Button getUpdate() {
        return update;
    }

    /**
     * @param update the update to set
     */
    public void setUpdate(Button update) {
        this.update = update;
    }

    /**
     * @param footer the footer to set
     */
    public void setFooter(HorizontalLayout footer) {
        this.footer = footer;
    }

    static class ActionPlanFactory extends DefaultFieldFactory {

        @Override
        public Field createField(Item item, Object propertyId,
                Component uiContext) {

            Field field = super.createField(item, propertyId, uiContext);
            if ("actionPlan".equals(propertyId)) {
                field = new TextArea("Action Plan:");
                ((TextArea) field).setHeight("100px");
                ((TextArea) field).setWidth("500px");
                ((TextArea) field).setNullRepresentation("");
                ((TextArea) field).setRequired(true);
                ((TextArea) field).setRequiredError("Please Enter Value");
            } else if ("id".equals(propertyId)) {
                field = new TextField("Action Plan ID:");
                ((TextField) field).setVisible(false);
            } else if ("status".equals(propertyId)) {
                Select selectStatus = new Select("Action Plan Status:");
                selectStatus.addItem("PENDING");
                selectStatus.addItem("REVIEWED");
                selectStatus.setNewItemsAllowed(false);
                selectStatus.setImmediate(true);
                selectStatus.setWidth("500px");
                selectStatus.setRequired(true);
                return selectStatus;
            } else if ("actionPlanreview".equals(propertyId)) {
                field = new TextArea("Action Plan Review:");
                ((TextArea) field).setHeight("100px");
                ((TextArea) field).setWidth("500px");
                ((TextArea) field).setNullRepresentation("");
                ((TextArea) field).setRequired(true);
                ((TextArea) field).setRequiredError("Please Enter Value");
            } else if ("actionPlanDate".equals(propertyId)) {
                field = new DateField("Date:");
                ((DateField) field).setRequired(true);
                ((DateField) field).setRequiredError("Please Enter Value");
                ((DateField) field).setWidth("500px");
                ((DateField) field).setDateFormat("dd-MMMM-yyyy");
            } else if ("reviewDate".equals(propertyId)) {
                field = new DateField("Review Date:");
                ((DateField) field).setRequired(true);
                ((DateField) field).setWidth("500px");
                ((DateField) field).setDateFormat("dd-MMMM-yyyy");
            }


            return field;

        }
    }
}
