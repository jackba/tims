/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.trainingmentoring.forms;

import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.domain.employeelist.CompetencyList;
import com.hashthrims.domain.employeelist.CompetencyType;
import com.hashthrims.domain.positions.Status;
import com.hashthrims.domain.traininglist.MentoringSessionType;
import com.hashthrims.domain.traininglist.MentoringTheme;
import com.hashthrims.domain.traininglist.Mentors;
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
import java.util.List;

/**
 *
 * @author boniface
 */
public class MentoringSessionForm {

    // Define Buttons
    private Button save = new Button("Save");
    private Button edit = new Button("Edit");
    private Button cancel = new Button("Cancel");
    private Button update = new Button("Update");
    private Button delete = new Button("Delete");
    //Define Footer
    private HorizontalLayout footer;
    private final static ClientDataService data = new ClientDataService();

    public MentoringSessionForm() {
    }

    public Form createMentoringSessionFrom() {
        final Form form = new MentoringSessionGridForm();
        form.setImmediate(false);
        form.setFormFieldFactory(new MentoringSessionFieldFactory());


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

    class MentoringSessionFieldFactory extends DefaultFieldFactory implements Property.ValueChangeListener {

        private Select selectCompetencyType;
        private Select selectTraniningInstitution;
        private Select selectMentoringSessionStatus;
        private Select selectSessionType;
        private Select selectTheme;
        private ListSelect selectTrainingFunders;
        private ListSelect selectCompetencies;
        private ListSelect selectMentors;
        private List<TrainingFunder> funders;
        private List<CompetencyList> competenciesLists;
        private List<Mentors> mentorsList;
        private CompetencyType c;

        @Override
        public Field createField(Item item, Object propertyId,
                Component uiContext) {

            Field field = super.createField(item, propertyId, uiContext);
            if ("id".equals(propertyId)) {
                field = new TextField("Mentoring Session ID:");
                ((TextField) field).setVisible(false);
            } else if ("sessionName".equals(propertyId)) {
                field = new TextField("Session Name:");
 
                ((TextField) field).setColumns(19);
                ((TextField) field).setNullRepresentation("");
    
                ((TextField) field).setRequired(true);
                ((TextField) field).setRequiredError("Please Enter Value");
            } else if ("sessionMentors".equals(propertyId)) {
                List<Mentors> mentors = data.getMentorsService().findAll();
                selectMentors = new ListSelect("Session Mentors:");
                selectMentors.setImmediate(true);
                for (Mentors mentor : mentors) {
                    selectMentors.addItem(mentor.getId());
                    selectMentors.setItemCaption(mentor.getId(), mentor.getFirstName() + " " + mentor.getLastName());
                }
                selectMentors.setNewItemsAllowed(false);
                selectMentors.setWidth("250");
                selectMentors.setHeight("100");
                selectMentors.setNullSelectionAllowed(false);
                selectMentors.setMultiSelect(true);
                selectMentors.setImmediate(true);
                return selectMentors;
            } else if ("sessionStatus".equals(propertyId)) {
                List<Status> statuses = data.getStatusService().findAll();
                selectMentoringSessionStatus = new Select("Status:");
                for (Status status : statuses) {
                    selectMentoringSessionStatus.addItem(status.getStatus());
                }
                selectMentoringSessionStatus.setImmediate(true);
                selectMentoringSessionStatus.setNewItemsAllowed(true);
                selectMentoringSessionStatus.setWidth("250");
                selectMentoringSessionStatus.setRequired(true);
                return selectMentoringSessionStatus;
            } else if ("institutionName".equals(propertyId)) {
                List<TrainingInstitution> institutions = data.getTrainingInstitutionService().findAll();
                selectTraniningInstitution = new Select("Training Institution:");
                for (TrainingInstitution trainingInstitution : institutions) {
                    selectTraniningInstitution.addItem(trainingInstitution.getId());
                    selectTraniningInstitution.setItemCaption(trainingInstitution.getId(), trainingInstitution.getTrainingInstitution());
                }
                selectTraniningInstitution.setImmediate(true);
                selectTraniningInstitution.setNewItemsAllowed(true);
                selectTraniningInstitution.setWidth("250");
                selectTraniningInstitution.setRequired(true);
                return selectTraniningInstitution;

            } else if ("mentoringFunders".equals(propertyId)) {
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
            } else if ("mentoringCompetencies".equals(propertyId)) {
                selectCompetencies = new ListSelect("Mentoring Competencies:");
                selectCompetencies.setNewItemsAllowed(false);
                selectCompetencies.setWidth("500");
                selectCompetencies.setHeight("100");
                selectCompetencies.setRequired(true);
                selectCompetencies.setNullSelectionAllowed(false);
                selectCompetencies.setMultiSelect(true);
                selectCompetencies.setImmediate(true);
                if (c != null) {
                    competenciesLists = c.getCompetencyList();
                    for (CompetencyList competencyList : competenciesLists) {
                        selectCompetencies.addItem(competencyList.getComp_name());
                    }
                } else {
                }
                return selectCompetencies;
            } else if ("mentoringNotes".equals(propertyId)) {
                field = new TextField("Mentoring Notes :");

                ((TextField) field).setWidth("500");
                //((TextField) field).setHeight("100");
                ((TextField) field).setNullRepresentation("");

                ((TextField) field).setRequired(true);
                ((TextField) field).setRequiredError("Please Enter  Notes");
            } else if ("mentoringTheme".equals(propertyId)) {
                List<MentoringTheme> themes = data.getMentoringThemeService().findAll();
                selectTheme = new Select("Mentoring Theme:");
                for (MentoringTheme theme : themes) {
                    selectTheme.addItem(theme.getId());
                    selectTheme.setItemCaption(theme.getId(), theme.getMentoringTheme());
                }
                selectTheme.setImmediate(true);
                selectTheme.setNewItemsAllowed(true);
                selectTheme.setWidth("250");
                selectTheme.setRequired(true);
                return selectTheme;
            } else if ("sessionType".equals(propertyId)) {
                List<MentoringSessionType> sts = data.getMentoringSessionTypeService().findAll();
                selectSessionType = new Select("Session Type:");
                for (MentoringSessionType st : sts) {
                    selectSessionType.addItem(st.getId());
                    selectSessionType.setItemCaption(st.getId(), st.getSessionTypeName());
                }
                selectSessionType.setImmediate(true);
                selectSessionType.setNewItemsAllowed(true);
                selectSessionType.setWidth("250");
                selectSessionType.setRequired(true);
                return selectSessionType;
            } else if ("competencyType".equals(propertyId)) {
                List<CompetencyType> fields = data.getCompetencyTypeService().findAll();
                selectCompetencyType = new Select("Subject Area:");
                selectCompetencyType.addListener(this);
                selectCompetencyType.setImmediate(true);
                for (CompetencyType fd : fields) {
                    selectCompetencyType.addItem(fd.getId());
                    selectCompetencyType.setItemCaption(fd.getId(), fd.getComp_name_typ());
                }
                selectCompetencyType.setNewItemsAllowed(false);
                selectCompetencyType.setWidth("500");
                selectCompetencyType.setRequired(true);
                return selectCompetencyType;
            }
            return field;
        }

        @Override
        public void valueChange(ValueChangeEvent event) {
            final Property property = event.getProperty();
            if (property == selectCompetencyType) {
                if (property.getValue() != null) {
                    c = data.getCompetencyTypeService().find(new Long(property.getValue().toString()));
                    competenciesLists = c.getCompetencyList();
                    if (!selectCompetencies.isReadOnly()) {
                        selectCompetencies.removeAllItems();
                    }
                    for (CompetencyList list : competenciesLists) {
                        selectCompetencies.addItem(list.getComp_name());
                    }
                } else {
                    if (!selectCompetencies.isReadOnly()) {
                        selectCompetencies.removeAllItems();
                    }
                }
            }
        }
    }

    class MentoringSessionGridForm extends Form {

        private GridLayout layout;

        public MentoringSessionGridForm() {
            setCaption("Mentoring Session Form");
            layout = new GridLayout(2, 12);
            layout.setMargin(true);
            layout.setSpacing(true);
            //layout.setSizeFull();
            //layout.setStyleName(Runo.LAYOUT_DARKER);
            layout.setStyleName(Runo.CSSLAYOUT_SHADOW);
            HorizontalLayout firstHeader = new HorizontalLayout();
            firstHeader.setSizeFull();
            firstHeader.setStyleName(Runo.LAYOUT_DARKER);
            Label firstHeaderlabel = new Label("Mentoring Session");
            firstHeaderlabel.setStyleName(Runo.LABEL_H2);
            firstHeader.addComponent(firstHeaderlabel);
            layout.addComponent(firstHeader, 0, 0, 1, 0);
            layout.setComponentAlignment(firstHeader, Alignment.MIDDLE_CENTER);
            HorizontalLayout secondHeader = new HorizontalLayout();
            secondHeader.setSizeFull();
            secondHeader.setStyleName(Runo.LAYOUT_DARKER);
            Label secondHeaderLabel = new Label("Session");
            secondHeaderLabel.setStyleName(Runo.LABEL_H2);
            secondHeader.addComponent(secondHeaderLabel);
            HorizontalLayout formFooter = new HorizontalLayout();
            formFooter.setSizeFull();
            formFooter.setStyleName(Runo.LAYOUT_DARKER);
            layout.addComponent(formFooter, 0, 11, 1, 11);
            setLayout(layout);
        }

        @Override
        protected void attachField(Object propertyId, Field field) {
            if (propertyId.equals("sessionName")) {
                layout.addComponent(field, 0, 1);
            } else if (propertyId.equals("mentoringTheme")) {
                layout.addComponent(field, 0, 2);
            } else if (propertyId.equals("sessionType")) {
                layout.addComponent(field, 1, 1);
            } else if (propertyId.equals("sessionStatus")) {
                layout.addComponent(field, 0, 3);
            } else if (propertyId.equals("sessionMentors")) { //--
                layout.addComponent(field, 1, 4, 1, 5);
            } else if (propertyId.equals("institutionName")) {
                layout.addComponent(field, 1, 2);
            } else if (propertyId.equals("mentoringNotes")) {
                layout.addComponent(field, 0, 6, 1, 6);
            } else if (propertyId.equals("mentoringFunders")) {
                layout.addComponent(field, 0, 4, 0, 5);
            } else if (propertyId.equals("competencyType")) {
                layout.addComponent(field, 0, 7, 1, 7);
            } else if (propertyId.equals("mentoringCompetencies")) {
                layout.addComponent(field, 0, 8, 1, 8);
            } else if (propertyId.equals("id")) {
                layout.addComponent(field, 0, 9);
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
