/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.trainingmentoring.forms;

import com.hashthrims.clients.web.vaadin.views.training.forms.*;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.domain.traininglist.MentoringField;
import com.hashthrims.domain.traininglist.MentoringTheme;
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
public class MentoringThemeForm {

  // Define Buttons
    private Button save = new Button("Save");
    private Button edit = new Button("Edit");
    private Button cancel = new Button("Cancel");
    private Button update = new Button("Update");
    private Button delete = new Button("Delete");
    //Define Footer
    private HorizontalLayout footer;
     private static final ClientDataService data = new ClientDataService();

    public MentoringThemeForm() {
    }

    public Form createMentoringThemeForm() {
        final Form form = new Form();
        form.setCaption("Mentoring Theme");
        form.setImmediate(false);
        form.setFormFieldFactory(new MentoringThemeFieldFactory());

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
        order.add("mentoringTheme");
        order.add("mentoringField");
        order.add("id");

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


    static class MentoringThemeFieldFactory extends DefaultFieldFactory {
     private Select selectMentoringTheme;
        @Override
        public Field createField(Item item, Object propertyId,
                Component uiContext) {

            Field field = super.createField(item, propertyId, uiContext);
            if ("id".equals(propertyId)) {
                field = new TextField("Mentoring   ID:");
       
                ((TextField) field).setVisible(false);

            }
            if ("mentoringTheme".equals(propertyId)) {
                field = new TextField("Mentoring Theme:");
                ((TextField) field).setColumns(30);
                ((TextField) field).setNullRepresentation("");
                ((TextField) field).setRequired(true);
                ((TextField) field).setRequiredError("Please Enter Value");
            }if ("mentoringField".equals(propertyId)) {
               List<MentoringField> fields = data.getMentoringFieldService().findAll();
                selectMentoringTheme = new Select("Mentoring Subject Area");
                for (MentoringField fieldName : fields) {
                    selectMentoringTheme.addItem(fieldName.getFieldName());
                }
                selectMentoringTheme.setNewItemsAllowed(false);
                selectMentoringTheme.setImmediate(true);
                selectMentoringTheme.setWidth("250");
                selectMentoringTheme.setRequired(true);
                return selectMentoringTheme;
            }
      return field;

        }
    }
}
