/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.people.views.details.course.form;

import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.domain.traininglist.TrainingCourseRequestors;
import com.hashthrims.domain.traininglist.TrainingCourses;
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
public class ScheduleCourseForm {

    // Define Buttons
    private Button save = new Button("Save");
    private Button cancel = new Button("Cancel");
    private Button update = new Button("Update");
    //Define Footer
    private HorizontalLayout footer;
    private static ClientDataService data = new ClientDataService();

    public ScheduleCourseForm() {
    }

    public Form createShortCourseFrom() {
        Form form = new ShortCourseGridForm();
        form.setImmediate(false);
        form.setFormFieldFactory(new ShortCourseFieldFactory());


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

    class ShortCourseFieldFactory extends DefaultFieldFactory implements Property.ValueChangeListener {

        private Select selectCourse;
        private Select selectRetraining;
        private Select selectRequestors;

        @Override
        public Field createField(Item item, Object propertyId,
                Component uiContext) {
            Field field = super.createField(item, propertyId, uiContext);
            if ("id".equals(propertyId)) {
                field = new TextField("Short Course ID:");
                ((TextField) field).setVisible(false);
            } else if ("course".equals(propertyId)) {
                List<TrainingCourses> courses = data.getTrainingCoursesService().findAll();
                selectCourse = new Select("Course:");
                for (TrainingCourses co : courses) {
                    selectCourse.addItem(co.getId());
                    selectCourse.setItemCaption(co.getId(), co.getCourseName());
                }
                selectCourse.setNewItemsAllowed(false);
                selectCourse.addListener(this);
                selectCourse.setImmediate(true);
                selectCourse.setWidth("250");
                selectCourse.setRequired(true);
                return selectCourse;
            } else if ("retraining".equals(propertyId)) {
                selectRetraining = new Select("Retraining ? :");
                selectRetraining.addItem("YES");
                selectRetraining.addItem("NO");
                selectRetraining.setNewItemsAllowed(false);
                selectRetraining.addListener(this);
                selectRetraining.setImmediate(true);
                selectRetraining.setWidth("250");
                selectRetraining.setRequired(true);
                return selectRetraining;
            } else if ("requestor".equals(propertyId)) {
                List<TrainingCourseRequestors> requestors = data.getTrainingCourseRequestorsType().findAll();
                selectRequestors = new Select("Requestors:");
                for (TrainingCourseRequestors request : requestors) {
                    selectRequestors.addItem(request.getId());
                    selectRequestors.setItemCaption(request.getId(), request.getRequestorName());
                }
                selectRequestors.setNewItemsAllowed(false);
                selectRequestors.addListener(this);
                selectRequestors.setImmediate(true);
                selectRequestors.setWidth("250");
                selectRequestors.setRequired(true);
                return selectRequestors;
            } else if ("dateRequested".equals(propertyId)) {
                field = new DateField("Date Training Requested:");
                ((DateField) field).setRequired(true);
                ((DateField) field).setDateFormat("yyyy-MM-dd");
                ((DateField) field).setRequiredError("Please Enter Value");
                ((DateField) field).setWidth(250, Sizeable.UNITS_PIXELS);
            } else if ("courseEndDate".equals(propertyId)) {
                field = new DateField("Date Course Ends:");
                ((DateField) field).setRequired(true);
                ((DateField) field).setDateFormat("yyyy-MM-dd");
                ((DateField) field).setRequiredError("Please Enter Value");
                ((DateField) field).setWidth(250, Sizeable.UNITS_PIXELS);
            } else if ("courseStartDate".equals(propertyId)) {
                field = new DateField("Date Course Starts:");
                ((DateField) field).setRequired(true);
                ((DateField) field).setDateFormat("yyyy-MM-dd");
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

    class ShortCourseGridForm extends Form {

        private GridLayout layout;

        public ShortCourseGridForm() {
            setCaption("Schedule Training Course Form");
            layout = new GridLayout(2, 5);
            layout.setMargin(true);
            layout.setSpacing(true);

            //layout.setSizeFull();
            //layout.setStyleName(Runo.LAYOUT_DARKER);
            layout.setStyleName(Runo.CSSLAYOUT_SHADOW);
            HorizontalLayout firstHeader = new HorizontalLayout();
            firstHeader.setSizeFull();
            firstHeader.setStyleName(Runo.LAYOUT_DARKER);
            Label firstHeaderlabel = new Label("Schedule Training Course");
            firstHeaderlabel.setStyleName(Runo.LABEL_H2);
            firstHeader.addComponent(firstHeaderlabel);
            layout.addComponent(firstHeader, 0, 0, 1, 0);
            layout.setComponentAlignment(firstHeader, Alignment.MIDDLE_CENTER);
            setLayout(layout);
        }

        @Override
        protected void attachField(Object propertyId, Field field) {

            if (propertyId.equals("course")) {
                layout.addComponent(field, 0, 1);
            } else if (propertyId.equals("retraining")) {
                layout.addComponent(field, 0, 2);
            } else if (propertyId.equals("requestor")) {
                layout.addComponent(field, 0, 3);
            } else if (propertyId.equals("dateRequested")) {
                layout.addComponent(field, 1, 3);
            } else if (propertyId.equals("courseStartDate")) {
                layout.addComponent(field, 1, 1);
            } else if (propertyId.equals("courseEndDate")) {
                layout.addComponent(field, 1, 2);
            } else if (propertyId.equals("id")) {
                layout.addComponent(field, 0, 4);
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
