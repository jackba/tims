/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.training.forms;

import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.domain.employeelist.CompetencyList;
import com.hashthrims.domain.positions.Status;
import com.hashthrims.domain.traininglist.CourseTypeName;
import com.hashthrims.domain.traininglist.Criteria;
import com.hashthrims.domain.traininglist.TargetGroup;
import com.hashthrims.domain.traininglist.TrainingCourseCategory;
import com.hashthrims.domain.traininglist.TrainingFunder;
import com.hashthrims.domain.traininglist.TrainingInstitution;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.Select;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.Runo;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author boniface
 */
public class CourseForm {

    // Define Buttons
    private Button save = new Button("Save");
    private Button edit = new Button("Edit");
    private Button cancel = new Button("Cancel");
    private Button update = new Button("Update");
    private Button delete = new Button("Delete");
    //Define Footer
    private HorizontalLayout footer;
    private static ClientDataService data = new ClientDataService();

    public CourseForm() {
    }

    public Form createCourseFrom() {
        Form form = new CourseGridForm();
        form.setImmediate(true);
        form.setValidationVisible(true);
        form.setFormFieldFactory(new CourseFieldFactory());


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

    class CourseFieldFactory extends DefaultFieldFactory implements Property.ValueChangeListener {

        private Select selectCourseType;
        private Select selectCourseCategory;
        private Select selectTraniningInstitution;
        private Select selectCourseStatus;
        private Select selectCriteria;
        private ListSelect selectTrainingFunders;
        private ListSelect selectTargetGroup;
        private ListSelect selectCompetencies;
        private List<TrainingFunder> funders;
        private List<CompetencyList> competenciesLists;
        private List<TargetGroup> targetGroupList;
        

        @Override
        public Field createField(Item item, Object propertyId,
                Component uiContext) {

            Field field = super.createField(item, propertyId, uiContext);

            if ("courseId".equals(propertyId)) {
                field = new TextField("Course ID:");
                ((TextField) field).setVisible(false);
            } else if ("courseType".equals(propertyId)) {
                List<CourseTypeName> coursetypes = data.getCourseTypeNameService().findAll();
                selectCourseType = new Select("Type of Training");
                for (CourseTypeName coursetype : coursetypes) {
                    selectCourseType.addItem(coursetype.getCourseType());
                }
                selectCourseType.setNewItemsAllowed(false);
                selectCourseType.setImmediate(true);
                selectCourseType.setWidth("250");
                selectCourseType.setRequired(true);
                selectCourseType.setValidationVisible(true);
                return selectCourseType;
            } else if ("courseCatergory".equals(propertyId)) {
                List<TrainingCourseCategory> categories = data.getTrainingCourseCategoryService().findAll();
                selectCourseCategory = new Select("Training Field:");
                selectCourseCategory.setImmediate(true);
                for (TrainingCourseCategory trainingCourseCategory : categories) {
                    selectCourseCategory.addItem(trainingCourseCategory.getCategoryName());
                }
                selectCourseCategory.setNewItemsAllowed(true);
                selectCourseCategory.setWidth("250");
                selectCourseCategory.setRequired(false);
                
                return selectCourseCategory;
            } else if ("courseStatus".equals(propertyId)) {

                List<Status> statuses = data.getStatusService().findAll();
                selectCourseStatus = new Select("Course Status:");
                for (Status status : statuses) {

                    if (("OPEN".equalsIgnoreCase(status.getStatus())|"CLOSED".equalsIgnoreCase(status.getStatus()))) {
                        selectCourseStatus.addItem(status.getStatus());
                    }
                }
                selectCourseStatus.setImmediate(true);
                selectCourseStatus.setNewItemsAllowed(true);
                selectCourseStatus.setWidth("250");
                selectCourseStatus.setRequired(true);
               
                return selectCourseStatus;
            } else if ("trainingInstitution".equals(propertyId)) {
                List<TrainingInstitution> institutions = data.getTrainingInstitutionService().findAll();
                selectTraniningInstitution = new Select("Training Institution:");
                for (TrainingInstitution trainingInstitution : institutions) {
                    selectTraniningInstitution.addItem(trainingInstitution.getTrainingInstitution());
                }
                selectTraniningInstitution.setImmediate(true);
                selectTraniningInstitution.setNewItemsAllowed(true);
                selectTraniningInstitution.setWidth("250");
                selectTraniningInstitution.setRequired(true);
               
                return selectTraniningInstitution;

            } else if ("trainingFunder".equals(propertyId)) {
                funders = data.getTrainingFunderService().findAll();
                selectTrainingFunders = new ListSelect("Training Funders:");
                for (TrainingFunder ss : funders) {
                    selectTrainingFunders.addItem(ss.getTrainingFunderName());
                }
                selectTrainingFunders.setNewItemsAllowed(false);
                selectTrainingFunders.setWidth("250");
                selectTrainingFunders.setHeight("100");
                selectTrainingFunders.setRequired(true);
                selectTrainingFunders.setNullSelectionAllowed(false);
                selectTrainingFunders.setMultiSelect(true);
                selectTrainingFunders.setImmediate(true);
                return selectTrainingFunders;
            } else if ("competency".equals(propertyId)) {
                competenciesLists = data.getCompetencyList().findAll();
                Collections.sort(competenciesLists);
                selectCompetencies = new ListSelect("Competencies:");
                for (CompetencyList ss : competenciesLists) {
                    selectCompetencies.addItem(ss.getComp_name());
                }
                selectCompetencies.setNewItemsAllowed(false);
                selectCompetencies.setWidth("500");
                selectCompetencies.setHeight("100");
                selectCompetencies.setRequired(true);
                selectCompetencies.setNullSelectionAllowed(false);
                selectCompetencies.setMultiSelect(true);
                selectCompetencies.setImmediate(true);
                return selectCompetencies;
            } else if ("courseNotes".equals(propertyId)) {
                field = new TextField("Course Notes :");
                ((TextField) field).setWidth("250");
                ((TextField) field).setHeight("100");
                ((TextField) field).setNullRepresentation("");
                ((TextField) field).setRequired(true);              
            } else if ("courseName".equals(propertyId)) {
                field = new TextField("Course Name:");

                ((TextField) field).setColumns(20);
                ((TextField) field).setNullRepresentation("");

                ((TextField) field).setRequired(true);
                ((TextField) field).setRequiredError("Please Enter Course Name");
            }  else if ("targetGroup".equals(propertyId)) {
                targetGroupList = data.getTargetGroupService().findAll();
                selectTargetGroup = new ListSelect("Target Group:");
                for (TargetGroup ss : targetGroupList ) {
                    selectTargetGroup.addItem(ss.getTargetGroupName());
                }
                selectTargetGroup.setNewItemsAllowed(false);
                selectTargetGroup.setWidth("250");
                selectTargetGroup.setHeight("100");
                selectTargetGroup.setRequired(true);
                selectTargetGroup.setNullSelectionAllowed(false);
                selectTargetGroup.setMultiSelect(true);
                selectTargetGroup.setImmediate(true);
                return selectTargetGroup;
            }  else if ("criteria".equals(propertyId)) {
                List<Criteria> criterias = data.getCriteriaService().findAll();
                selectCriteria = new Select("Select Criteria:");
                for (Criteria criteria : criterias) {
                    selectCriteria.addItem(criteria.getSelectionCriteria());
                }
                selectCriteria.setImmediate(true);
                selectCriteria.setNewItemsAllowed(true);
                selectCriteria.setWidth("250");
                selectCriteria.setRequired(true);
               
                return selectCriteria;
            }

            return field;

        }

        @Override
        public void valueChange(ValueChangeEvent event) {
            Property property = event.getProperty();

        }
    }

    class CourseGridForm extends Form {

        private GridLayout layout;

        public CourseGridForm() {
            setCaption("Course Form");
            layout = new GridLayout(2, 10);
            layout.setMargin(true);
            layout.setSpacing(true);


            layout.setStyleName(Runo.CSSLAYOUT_SHADOW);
            HorizontalLayout firstHeader = new HorizontalLayout();
            firstHeader.setSizeFull();
            firstHeader.setStyleName(Runo.LAYOUT_DARKER);
            Label firstHeaderlabel = new Label("Add Course");
            firstHeaderlabel.setStyleName(Runo.LABEL_H2);
            firstHeader.addComponent(firstHeaderlabel);
            layout.addComponent(firstHeader, 0, 0, 1, 0);
            layout.setComponentAlignment(firstHeader, Alignment.MIDDLE_CENTER);


            HorizontalLayout secondHeader = new HorizontalLayout();
            secondHeader.setSizeFull();
            secondHeader.setStyleName(Runo.LAYOUT_DARKER);
            Label secondHeaderLabel = new Label("Compentencies");
            secondHeaderLabel.setStyleName(Runo.LABEL_H2);
            secondHeader.addComponent(secondHeaderLabel);
            layout.addComponent(secondHeader, 0, 6, 1, 6);
            layout.setComponentAlignment(secondHeader, Alignment.MIDDLE_CENTER);


            HorizontalLayout formFooter = new HorizontalLayout();
            formFooter.setSizeFull();
            formFooter.setStyleName(Runo.LAYOUT_DARKER);
            layout.addComponent(formFooter, 0, 9, 1, 9);

            setLayout(layout);

        }

        @Override
        protected void attachField(Object propertyId, Field field) {

            if (propertyId.equals("courseName")) {
                layout.addComponent(field, 0, 2);
            } else if (propertyId.equals("trainingInstitution")) {
                layout.addComponent(field, 0, 3);
            } else if (propertyId.equals("courseType")) {
                layout.addComponent(field, 0, 1);
            } else if (propertyId.equals("courseCatergory")) {
                layout.addComponent(field, 1, 1);
            } else if (propertyId.equals("courseStatus")) {
                layout.addComponent(field, 1, 2);
            }else if (propertyId.equals("trainingFunder")) {
                layout.addComponent(field, 0, 4, 0, 5);
            } else if (propertyId.equals("competency")) {
                layout.addComponent(field, 0, 7, 1, 7);
            } else if (propertyId.equals("targetGroup")) {
                layout.addComponent(field, 1, 4, 1, 5);
            } else if (propertyId.equals("courseId")) {
                layout.addComponent(field, 0, 8);

            } else if (propertyId.equals("criteria")) {
                layout.addComponent(field, 1, 3);

            }


        }
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
}
