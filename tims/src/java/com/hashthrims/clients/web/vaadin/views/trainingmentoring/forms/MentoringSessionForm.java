/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.trainingmentoring.forms;

import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.managetraining.util.PeopleUtil;
import com.hashthrims.domain.Person;
import com.hashthrims.domain.employeelist.CompetencyType;
import com.hashthrims.domain.offices.Facility;
import com.hashthrims.domain.positions.Status;
import com.hashthrims.domain.traininglist.MentoringAreasList;
import com.hashthrims.domain.traininglist.MentoringObjective;
import com.hashthrims.domain.traininglist.SessionType;
import com.hashthrims.domain.traininglist.MentoringTheme;
import com.hashthrims.domain.traininglist.TrainingFunder;
import com.hashthrims.domain.traininglist.TrainingInstitution;
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
        private Select selectInstitutionName;
        private Select selectMentoringSessionStatus;
        private Select selectFacility;
        private ListSelect selectSessionType;
        private ListSelect selectTheme;
        private ListSelect selectTrainingFunders;
        private ListSelect mentoringObjectivesList;
        private ListSelect selectMentors;
        private ListSelect selectAreasofStrenthening;
        private List<TrainingFunder> funders;

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
            } else if ("startDate".equals(propertyId)) {
                field = new DateField("Start Date:");
                ((DateField) field).setRequired(true);
                ((DateField) field).setRequiredError("Please Enter Value");
                ((DateField) field).setWidth(250, Sizeable.UNITS_PIXELS);
            } else if ("endDate".equals(propertyId)) {
                field = new DateField("End Date:");
                ((DateField) field).setRequired(true);
                ((DateField) field).setRequiredError("Please Enter Value");
                ((DateField) field).setWidth(250, Sizeable.UNITS_PIXELS);
            } else if ("sessionMentors".equals(propertyId)) {
                List<Person> mentors = new PeopleUtil().getMentors(data.getPersonService().findAll());
                selectMentors = new ListSelect("Session Mentors:");
                selectMentors.setImmediate(true);
                for (Person mentor : mentors) {
                    selectMentors.addItem(mentor.getId());
                    selectMentors.setItemCaption(mentor.getId(), mentor.getPersonName() + " " + mentor.getPersonSurname());
                }
                selectMentors.setNewItemsAllowed(false);
                selectMentors.setWidth("250");
                selectMentors.setHeight("100");
                selectMentors.setNullSelectionAllowed(false);
                selectMentors.setMultiSelect(true);
                selectMentors.setImmediate(true);
                return selectMentors;
            } else if ("mentoringVenue".equals(propertyId)) {
                List<Facility> fields = data.getFacilityService().findAll();
                Collections.sort(fields);
                selectFacility = new Select("Mentoring Venue:");
                selectFacility.addListener(this);
                selectFacility.setImmediate(true);
                for (Facility fd : fields) {
                    selectFacility.addItem(fd.getId());
                    selectFacility.setItemCaption(fd.getId(), fd.getFacilityName());
                }
                selectFacility.setNewItemsAllowed(false);
                selectFacility.setWidth("250");
                selectFacility.setRequired(true);
                selectFacility.setNullSelectionAllowed(false);
                return selectFacility;
            } else if ("sessionStatus".equals(propertyId)) {
                List<Status> statuses = data.getStatusService().findAll();
                selectMentoringSessionStatus = new Select("Status:");
                for (Status status : statuses) {
                    selectMentoringSessionStatus.addItem(status.getId());
                    selectMentoringSessionStatus.setItemCaption(status.getId(), status.getStatus());
                }
                selectMentoringSessionStatus.setImmediate(true);
                selectMentoringSessionStatus.setNewItemsAllowed(true);
                selectMentoringSessionStatus.setWidth("250");
                selectMentoringSessionStatus.setRequired(true);
                return selectMentoringSessionStatus;
            } else if ("institutionName".equals(propertyId)) {
                List<TrainingInstitution> fields = data.getTrainingInstitutionService().findAll();
                selectInstitutionName = new Select("Training Institution:");
                selectInstitutionName.addListener(this);
                selectInstitutionName.setImmediate(true);
                for (TrainingInstitution fd : fields) {
                    selectInstitutionName.addItem(fd.getId());
                    selectInstitutionName.setItemCaption(fd.getId(), fd.getTrainingInstitution());
                }
                selectInstitutionName.setNewItemsAllowed(false);
                selectInstitutionName.setWidth("250");
                selectInstitutionName.setRequired(true);
                selectInstitutionName.setNullSelectionAllowed(false);
                return selectInstitutionName;

            } else if ("mentoringFunders".equals(propertyId)) {
                funders = data.getTrainingFunderService().findAll();
                selectTrainingFunders = new ListSelect("Training Funders:");
                for (TrainingFunder ss : funders) {
                    selectTrainingFunders.addItem(ss.getId());
                    selectTrainingFunders.setItemCaption(ss.getId(), ss.getTrainingFunderName());
                }
                selectTrainingFunders.setNewItemsAllowed(false);
                selectTrainingFunders.setWidth("250");
                selectTrainingFunders.setHeight("100");
                selectTrainingFunders.setRequired(true);
                selectTrainingFunders.setNullSelectionAllowed(false);
                selectTrainingFunders.setMultiSelect(true);
                selectTrainingFunders.setImmediate(true);
                return selectTrainingFunders;
            } else if ("mentoringObjectives".equals(propertyId)) {
                List<MentoringObjective> mentoringObjectives = data.getMentoringObjectiveService().findAll();
                mentoringObjectivesList = new ListSelect("Mentoring Objectives: ");
                for (MentoringObjective mentoringObjective : mentoringObjectives) {
                    mentoringObjectivesList.addItem(mentoringObjective.getId());
                    mentoringObjectivesList.setItemCaption(mentoringObjective.getId(), mentoringObjective.getMentoringObjective());
                }
                mentoringObjectivesList.setNewItemsAllowed(false);
                mentoringObjectivesList.setWidth("500");
                mentoringObjectivesList.setHeight("100");
                mentoringObjectivesList.setRequired(true);
                mentoringObjectivesList.setNullSelectionAllowed(false);
                mentoringObjectivesList.setMultiSelect(true);
                mentoringObjectivesList.setImmediate(true);
                return mentoringObjectivesList;
            } else if ("mentoringThemes".equals(propertyId)) {
                List<MentoringTheme> themes = data.getMentoringThemeService().findAll();
                selectTheme = new ListSelect("Mentoring Theme:");
                for (MentoringTheme theme : themes) {
                    selectTheme.addItem(theme.getId());
                    selectTheme.setItemCaption(theme.getId(), theme.getMentoringTheme());
                }
                selectTheme.setNewItemsAllowed(false);
                selectTheme.setWidth("500");
                selectTheme.setHeight("100");
                selectTheme.setRequired(true);
                selectTheme.setNullSelectionAllowed(false);
                selectTheme.setMultiSelect(true);
                selectTheme.setImmediate(true);
                return selectTheme;
            } else if ("areasOfStrenthening".equals(propertyId)) {
                List<MentoringAreasList> areas = data.getMentoringAreasListService().findAll();
                selectAreasofStrenthening = new ListSelect("Areas of Strenthening:");
                for (MentoringAreasList area : areas) {
                    selectAreasofStrenthening.addItem(area.getId());
                    selectAreasofStrenthening.setItemCaption(area.getId(), area.getAreasofStrenthening());
                }
                selectAreasofStrenthening.setNewItemsAllowed(false);
                selectAreasofStrenthening.setWidth("500");
                selectAreasofStrenthening.setHeight("100");
                selectAreasofStrenthening.setRequired(true);
                selectAreasofStrenthening.setNullSelectionAllowed(false);
                selectAreasofStrenthening.setMultiSelect(true);
                selectAreasofStrenthening.setImmediate(true);
                return selectAreasofStrenthening;
            } else if ("mentoringSessionType".equals(propertyId)) {
                List<SessionType> sts = data.getMentoringSessionTypeService().findAll();
                selectSessionType = new ListSelect("Memntoring Session Type:");
                for (SessionType st : sts) {
                    selectSessionType.addItem(st.getId());
                    selectSessionType.setItemCaption(st.getId(), st.getSessionTypeName());
                }
                selectSessionType.setNewItemsAllowed(false);
                selectSessionType.setWidth("500");
                selectSessionType.setHeight("100");
                selectSessionType.setRequired(true);
                selectSessionType.setNullSelectionAllowed(false);
                selectSessionType.setMultiSelect(true);
                selectSessionType.setImmediate(true);
                return selectSessionType;
            } else if ("mentoringSubjectArea".equals(propertyId)) {
                List<CompetencyType> fields = data.getCompetencyTypeService().findAll();
                selectCompetencyType = new Select("Mentoring Subject Area:");
                selectCompetencyType.addListener(this);
                selectCompetencyType.setImmediate(true);
                for (CompetencyType fd : fields) {
                    selectCompetencyType.addItem(fd.getId());
                    selectCompetencyType.setItemCaption(fd.getId(), fd.getComp_name_typ());
                }
                selectCompetencyType.setNewItemsAllowed(false);
                selectCompetencyType.setWidth("250");
                selectCompetencyType.setRequired(true);
                selectCompetencyType.setNullSelectionAllowed(false);
                return selectCompetencyType;
            }
            return field;
        }

        @Override
        public void valueChange(ValueChangeEvent event) {
            final Property property = event.getProperty();
            if (property == selectCompetencyType) {
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
            } else if (propertyId.equals("mentoringSubjectArea")) {
                layout.addComponent(field, 1, 1);
            } else if (propertyId.equals("institutionName")) {
                layout.addComponent(field, 0, 2);
            } else if (propertyId.equals("mentoringVenue")) {
                layout.addComponent(field, 1, 2);
            } else if (propertyId.equals("startDate")) {
                layout.addComponent(field, 0, 3);
            } else if (propertyId.equals("endDate")) {
                layout.addComponent(field, 1, 3);
            } else if (propertyId.equals("mentoringFunders")) {
                layout.addComponent(field, 0, 4, 0, 5);
            } else if (propertyId.equals("sessionMentors")) {
                layout.addComponent(field, 1, 4, 1, 5);
            } else if (propertyId.equals("mentoringSessionType")) {
                layout.addComponent(field, 0, 6, 1, 6);
            } else if (propertyId.equals("mentoringObjectives")) {
                layout.addComponent(field, 0, 7, 1, 7);
            } else if (propertyId.equals("mentoringThemes")) {
                layout.addComponent(field, 0, 8, 1, 8);
            } else if (propertyId.equals("areasOfStrenthening")) {
                layout.addComponent(field, 0, 9, 1, 9);
            } else if (propertyId.equals("id")) {
                layout.addComponent(field, 0, 10);
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
