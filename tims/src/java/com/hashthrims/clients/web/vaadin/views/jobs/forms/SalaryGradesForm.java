/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.jobs.forms;

import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.domain.regionlist.Currency;
import com.vaadin.data.Item;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Select;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.Runo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author boniface
 */
public class SalaryGradesForm {

    // Define Buttons
    private Button save = new Button("Save");
    private Button edit = new Button("Edit");
    private Button cancel = new Button("Cancel");
    private Button update = new Button("Update");
    private Button delete = new Button("Delete");
    //Define Footer
    private HorizontalLayout footer;
    private static final ClientDataService data = new ClientDataService();

    public SalaryGradesForm() {
    }

    public Form createSalaryGradesFrom() {
        final Form form = new Form();
        form.setCaption("Salary Grades");
        form.setImmediate(false);
        form.setFormFieldFactory(new SalaryGradesFieldFactory());

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
        final List order = new ArrayList();
        order.add("gradeName");
        order.add("startAmount");
        order.add("endAmount");
        order.add("midAmount");
        order.add("currentAmount");
        order.add("currency");
        order.add("notes");
        order.add("salaryGradeId");


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

    static class SalaryGradesFieldFactory extends DefaultFieldFactory {

        @Override
        public Field createField(Item item, Object propertyId,
                Component uiContext) {

            Field field = super.createField(item, propertyId, uiContext);

            if ("gradeName".equals(propertyId)) {
                field = new TextField("Grade Name:");
                field.setStyleName(Runo.LABEL_H2);
                ((TextField) field).setColumns(30);
                ((TextField) field).setNullRepresentation("");
                ((TextField) field).setStyleName(Runo.LABEL_H2);
                ((TextField) field).setRequired(true);
                ((TextField) field).setRequiredError("Please Enter JGrade Name");
            } else if ("startAmount".equals(propertyId)) {
                field = new TextField("Start Amount:");
                field.setStyleName(Runo.LABEL_H2);
                ((TextField) field).setColumns(30);
                ((TextField) field).setNullRepresentation("");
                ((TextField) field).setStyleName(Runo.LABEL_H2);
                ((TextField) field).setRequired(true);
                ((TextField) field).setRequiredError("Please Enter Start Amount");
            } else if ("endAmount".equals(propertyId)) {
                field = new TextField("End Amount:");
                field.setStyleName(Runo.LABEL_H2);
                ((TextField) field).setColumns(30);
                ((TextField) field).setNullRepresentation("");
                ((TextField) field).setStyleName(Runo.LABEL_H2);
                ((TextField) field).setRequired(true);
                ((TextField) field).setRequiredError("Please Enter End Amount");
            } else if ("midAmount".equals(propertyId)) {
                field = new TextField("Mid Amount:");
                field.setStyleName(Runo.LABEL_H2);
                ((TextField) field).setColumns(30);
                ((TextField) field).setNullRepresentation("");
                ((TextField) field).setStyleName(Runo.LABEL_H2);
                ((TextField) field).setRequired(true);
                ((TextField) field).setRequiredError("Please Enter Mid Amount");
            } else if ("currentAmount".equals(propertyId)) {
                field = new TextField("Current Amount:");
                field.setStyleName(Runo.LABEL_H2);
                ((TextField) field).setColumns(30);
                ((TextField) field).setNullRepresentation("");
                ((TextField) field).setStyleName(Runo.LABEL_H2);
                ((TextField) field).setRequired(true);
                ((TextField) field).setRequiredError("Please Enter Salary Current Amount");
            } else if ("currency".equals(propertyId)) {
                List<Currency> currencies = data.getCurrencyService().findAll();
                Select select = new Select("Currency");
                for (Currency currency : currencies) {
                    select.addItem(currency.getCurrencySymbol());
                }
                select.setNewItemsAllowed(true);
                select.setWidth("300");
                select.setRequired(true);
                return select;
            } else if ("notes".equals(propertyId)) {
                field = new TextField("Notes:");
                field.setStyleName(Runo.LABEL_H2);
                ((TextField) field).setColumns(30);
                ((TextField) field).setNullRepresentation("");
                ((TextField) field).setStyleName(Runo.LABEL_H2);
                ((TextField) field).setRequired(true);
                ((TextField) field).setRequiredError("Please Enter Notes");
            } else if ("salaryGradeId".equals(propertyId)) {
                field = new TextField("SalaGrade ID:");
                field.setStyleName(Runo.LABEL_H2);
                ((TextField) field).setVisible(false);
            }
            return field;
        }
    }
}
