/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.people.views.details.course.form;

import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.domain.employeelist.CompetencyEvaluation;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.terminal.Sizeable;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Select;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.Runo;
import java.util.List;

/**
 *
 * @author boniface
 */
public class EvaluateCourseForm {

    // Define Buttons
    private Button save = new Button("Save");
    private Button cancel = new Button("Cancel");
    private Button update = new Button("Update");
    //Define Footer
    private HorizontalLayout footer;
    private static ClientDataService data = new ClientDataService();

    public EvaluateCourseForm() {
    }

    public Form createShortCourseFrom() {
        Form form = new EvaluateCourseGridForm();
        form.setImmediate(false);
        form.setFormFieldFactory(new EvaluateCourseFieldFactory());


        // Add Listeners

        footer = new HorizontalLayout();
        footer.setSpacing(true);
        footer.addComponent(save);
        footer.addComponent(update);
        footer.addComponent(cancel);


        footer.setVisible(true);
        footer.setMargin(true);
        // Determines which properties are shown, and in which order:

        update.setVisible(false);
        save.setVisible(false);
        form.setWriteThrough(false);
        form.setFooter(footer);

        return form;
    }

    class EvaluateCourseFieldFactory extends DefaultFieldFactory implements Property.ValueChangeListener {

        private Select selectEvaluation;

        @Override
        public Field createField(Item item, Object propertyId,
                Component uiContext) {
            Field field = super.createField(item, propertyId, uiContext);
            if ("id".equals(propertyId)) {
                field = new TextField("Short Course ID:");
                ((TextField) field).setVisible(false);
            } else if ("evaluation".equals(propertyId)) {
                List<CompetencyEvaluation> competencyEvaluation = data.getCompetencyEvaluationService().findAll();
                selectEvaluation = new Select("Evaluation:");
                for (CompetencyEvaluation co : competencyEvaluation) {
                    selectEvaluation.addItem(co.getId());
                    selectEvaluation.setItemCaption(co.getId(), co.getCompt_type_name());
                }
                selectEvaluation.setNewItemsAllowed(false);
                selectEvaluation.addListener(this);
                selectEvaluation.setImmediate(true);
                selectEvaluation.setWidth("250");
                selectEvaluation.setRequired(true);
                return selectEvaluation;
            } else if ("evaluationDate".equals(propertyId)) {
                field = new DateField("Date of Evaluation:");
                ((DateField) field).setRequired(true);
                ((DateField) field).setRequiredError("Please Enter Value");
                ((DateField) field).setWidth(250, Sizeable.UNITS_PIXELS);
            }

            return field;

        }

        @Override
        public void valueChange(ValueChangeEvent event) {
            Property property = event.getProperty();

        }
    }

    class EvaluateCourseGridForm extends Form {

        private GridLayout layout;

        public EvaluateCourseGridForm() {
            setCaption("Submit Course Evaluation Form");
            layout = new GridLayout(2, 3);
            layout.setMargin(true);
            layout.setSpacing(true);
            layout.setStyleName(Runo.CSSLAYOUT_SHADOW);
            HorizontalLayout firstHeader = new HorizontalLayout();
            firstHeader.setSizeFull();
            firstHeader.setStyleName(Runo.LAYOUT_DARKER);
            Label firstHeaderlabel = new Label("Evaluate Course");
            firstHeaderlabel.setStyleName(Runo.LABEL_H2);
            firstHeader.addComponent(firstHeaderlabel);
            layout.addComponent(firstHeader, 0, 0, 1, 0);
            layout.setComponentAlignment(firstHeader, Alignment.MIDDLE_CENTER);
            setLayout(layout);
        }

        @Override
        protected void attachField(Object propertyId, Field field) {

            if (propertyId.equals("evaluation")) {
                layout.addComponent(field, 0, 1);
            } else if (propertyId.equals("evaluationDate")) {
                layout.addComponent(field, 1, 1);
            } else if (propertyId.equals("id")) {
                layout.addComponent(field, 0, 2);
            }
        }
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
}
