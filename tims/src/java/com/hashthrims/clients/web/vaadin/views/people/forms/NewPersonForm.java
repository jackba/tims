/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.people.forms;

import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.domain.employeelist.GenderList;
import com.hashthrims.domain.employeelist.MentorsRoles;
import com.hashthrims.domain.employeelist.RaceList;
import com.hashthrims.domain.offices.Facility;
import com.hashthrims.domain.positions.Positions;
import com.hashthrims.domain.traininglist.MentoringField;
import com.hashthrims.domain.traininglist.TrainingCourseCategory;
import com.hashthrims.infrastructure.util.TimsUtil;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
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
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author boniface
 */
public class NewPersonForm {

    // Define Buttons
    private Button save = new Button("Save");
    private Button edit = new Button("Edit");
    private Button cancel = new Button("Cancel");
    private Button update = new Button("Update");
    private Button delete = new Button("Delete");
    //Define Footer
    private HorizontalLayout footer;
    private static final ClientDataService data = new ClientDataService();
    private final TimsUtil st = new TimsUtil();

    public NewPersonForm() {
    }

    public Form createNewPersonFrom() {
        final Form form = new NewPersonGridForm();
        form.setImmediate(false);
        form.setFormFieldFactory(new NewPersonFieldFactory());
        //form.setValidationVisible(true);


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

    class NewPersonFieldFactory extends DefaultFieldFactory implements Property.ValueChangeListener {

        private Select selectGenderList;
        private Select selectRaceList;
        private ListSelect selectExpertise = new ListSelect("Expertise");
        private ListSelect mentoringAreas = new ListSelect("Mentoring Area");
        private Select selectAddressTypeList;
        private ListSelect selectRoles;
        private Facility facility;
        private List<Positions> positions;
        private Select selectFacilityList;
        private Select selectPositionList;

        @Override
        public Field createField(Item item, Object propertyId,
                Component uiContext) {

            Field field = super.createField(item, propertyId, uiContext);
            if ("id".equals(propertyId)) {
                field = new TextField("Person ID:");
                ((TextField) field).setVisible(false);
            } else if ("firstname".equals(propertyId)) {
                field = new TextField("First Name:");
                ((TextField) field).setColumns(20);
                ((TextField) field).setNullRepresentation("");
                ((TextField) field).setRequired(true);
                ((TextField) field).setRequiredError("Please Enter Person Name");
                ((TextField) field).setValidationVisible(true);
            } else if ("surname".equals(propertyId)) {
                field = new TextField("Surname:");
                ((TextField) field).setColumns(20);
                ((TextField) field).setNullRepresentation("");
                ((TextField) field).setRequired(true);
                ((TextField) field).setRequiredError("Please Enter Value");
            } else if ("othername".equals(propertyId)) {
                field = new TextField("Other Name :");
                ((TextField) field).setColumns(20);
                ((TextField) field).setNullRepresentation("");
                ((TextField) field).setRequiredError("Please Enter Value");
            } else if ("genderId".equals(propertyId)) {
                List<GenderList> genderLists = data.getGenderListService().findAll();
                selectGenderList = new Select("Gender");
                for (GenderList gender : genderLists) {
                    selectGenderList.addItem(gender.getId());
                    selectGenderList.setItemCaption(gender.getId(), gender.getGender());
                }
                selectGenderList.setNewItemsAllowed(false);
                selectGenderList.addListener(this);
                selectGenderList.setImmediate(true);
                selectGenderList.setWidth("250");
                selectGenderList.setRequired(true);
                return selectGenderList;
            } else if ("raceid".equals(propertyId)) {
                List<RaceList> races = data.getRaceListService().findAll();
                selectRaceList = new Select("Person Race");
                for (RaceList race : races) {
                    selectRaceList.addItem(race.getId());
                    selectRaceList.setItemCaption(race.getId(), race.getRaceName());
                }
                selectRaceList.setNewItemsAllowed(false);
                selectRaceList.addListener(this);
                selectRaceList.setRequired(true);
                selectRaceList.setImmediate(true);
                selectRaceList.setWidth("250");
                return selectRaceList;
            } else if ("dob".equals(propertyId)) {
                 field = new DateField("Date of Birth (dd/mm/yyyy):");
               
                ((DateField) field).setDateFormat("dd/MM/yyyy");
                ((DateField) field).setRequiredError("Please Enter Value");
                ((DateField) field).setWidth(250, Sizeable.UNITS_PIXELS);
            } else if ("competencyFieldId".equals(propertyId)) {
                mentoringAreas.setNewItemsAllowed(false);
                mentoringAreas.addListener(this);
                mentoringAreas.setMultiSelect(true);
                mentoringAreas.setImmediate(true);
                mentoringAreas.setWidth("250");
                return mentoringAreas;
            } else if ("expertiseId".equals(propertyId)) {
                selectExpertise.setNewItemsAllowed(false);
                selectExpertise.setMultiSelect(true);
                selectExpertise.addListener(this);
                selectExpertise.setImmediate(true);
                selectExpertise.setWidth("250");
                return selectExpertise;
            } else if ("rolesId".equals(propertyId)) {
                List<MentorsRoles> roles = data.getMentorsRolesService().findAll();
                selectRoles = new ListSelect("Select Roles");
                for (MentorsRoles role : roles) {
                    selectRoles.addItem(role.getId());
                    selectRoles.setItemCaption(role.getId(), role.getMentorsRolesName());
                }
                selectRoles.addListener((ValueChangeListener) this);
                selectRoles.setNewItemsAllowed(false);
                selectRoles.setNullSelectionAllowed(true);
                if (!selectRoles.isReadOnly()) {
                    selectRoles.setMultiSelect(true);
                }
                selectRoles.addListener(this);
                selectRoles.setImmediate(true);
                selectRoles.setWidth("250");
                return selectRoles;
            } else if ("idValue".equals(propertyId)) {
                field = new TextField("Indentity Value:");
                ((TextField) field).setColumns(20);
                ((TextField) field).setNullRepresentation("");
              
                ((TextField) field).setRequiredError("Please Enter Value");
            } else if ("idType".equals(propertyId)) {
                //List<String> idTypes = data.getAddressTypeService().findAll();
                selectAddressTypeList = new Select("Identity Type:");

                selectAddressTypeList.addItem("National ID");
                selectAddressTypeList.addItem("Registration ID");
                selectAddressTypeList.addItem("Passport ID");
                selectAddressTypeList.addItem("Employee ID");
                selectAddressTypeList.addItem("Other ID");

                selectAddressTypeList.setNewItemsAllowed(false);
                selectAddressTypeList.addListener(this);
                selectAddressTypeList.setImmediate(true);
                selectAddressTypeList.setWidth("250");
                return selectAddressTypeList;
            } else if ("facilityId".equals(propertyId)) {
                List<Facility> facilities = data.getFacilityService().findAll();
                Collections.sort(facilities);
                selectFacilityList = new Select("Facility");
                for (Facility fac : facilities) {
                    selectFacilityList.addItem(fac.getId());
                    selectFacilityList.setItemCaption(fac.getId(), fac.getFacilityName());
                }
                selectFacilityList.setNewItemsAllowed(false);
                selectFacilityList.addListener(this);
                selectFacilityList.setImmediate(true);
                selectFacilityList.setWidth("250");
                selectFacilityList.setRequired(true);

                return selectFacilityList;
            } else if ("positionId".equals(propertyId)) {
                
                selectPositionList = new Select("Position");
                selectPositionList.setNewItemsAllowed(false);
                selectPositionList.setRequired(true);
                selectPositionList.addListener(this);
                if (facility != null) {
                    positions = facility.getPositions();
                    for (Positions pos : positions) {

                        if (st.checkPositionAvalaibality(pos)) {
                            selectPositionList.addItem(pos.getId());
                            selectPositionList.setItemCaption(pos.getId(), pos.getPositionCode());
                            
                            
                        }
                    }
                } else {
                }
                selectPositionList.setImmediate(true);
                selectPositionList.setWidth("250");

                return selectPositionList;
            } else if ("startDate".equals(propertyId)) {
                field = new DateField("Date Started (dd/mm/yyyy):");
                
                 ((DateField) field).setDateFormat("dd/MM/yyyy");
                ((DateField) field).setRequiredError("Please Enter Value");
                ((DateField) field).setWidth(250, Sizeable.UNITS_PIXELS);
            } else if ("telephoneNumber".equals(propertyId)) {
                field = new TextField("Telephone Number:");

                ((TextField) field).setColumns(20);
                ((TextField) field).setNullRepresentation("");

              
                ((TextField) field).setRequiredError("Please Enter Value");
            } else if ("cellnumber".equals(propertyId)) {
                field = new TextField("Cell Number:");

                ((TextField) field).setColumns(20);
                ((TextField) field).setNullRepresentation("");

              
                ((TextField) field).setRequiredError("Please Enter Value");
            } else if ("faxnumber".equals(propertyId)) {
                field = new TextField("fax Number:");

                ((TextField) field).setColumns(20);
                ((TextField) field).setNullRepresentation("");
             
                ((TextField) field).setRequiredError("Please Enter Value");
            } else if ("email".equals(propertyId)) {
                field = new TextField("Email :");

                ((TextField) field).setColumns(20);
                ((TextField) field).setNullRepresentation("");

              
                ((TextField) field).setRequiredError("Please Enter Value");
            }


            return field;

        }

        @Override
        public void valueChange(ValueChangeEvent event) {
            Property property = event.getProperty();

            if (property == selectFacilityList) {
                if (property.getValue() != null) {
                    facility = data.getFacilityService().find(Long.parseLong(property.getValue().toString()));
                    positions = facility.getPositions();
                    if (!selectPositionList.isReadOnly()) {
                        selectPositionList.removeAllItems();
                    }
                    for (Positions pos : positions) {
                        if (st.checkPositionAvalaibality(pos)) {
                            selectPositionList.addItem(pos.getId());
                            selectPositionList.setItemCaption(pos.getId(), pos.getPositionCode());
                        }
                    }
                } else {
                    if (!selectPositionList.isReadOnly()) {
                        selectPositionList.removeAllItems();
                    }
                }
            }

            if (property == selectRoles) {
                if (!selectExpertise.isReadOnly()) {
                    selectExpertise.removeAllItems();
                }
                if (!mentoringAreas.isReadOnly()) {
                    mentoringAreas.removeAllItems();
                }
                Collection<Long> ids = (Collection<Long>) property.getValue();
                for (Long id : ids) {
                    if (isMentor(id)) {
                        List<MentoringField> fields = data.getMentoringFieldService().findAll();
                        for (MentoringField f : fields) {
                            mentoringAreas.addItem(f.getId());
                            mentoringAreas.setItemCaption(f.getId(), f.getFieldName());
                        }

                    }

                    if (isResourPerson(id)) {
                        List<TrainingCourseCategory> categories = data.getTrainingCourseCategoryService().findAll();
                        for (TrainingCourseCategory category : categories) {
                            selectExpertise.addItem(category.getId());
                            selectExpertise.setItemCaption(category.getId(), category.getCategoryName());
                        }
                    }

                }

            }


        }

        private boolean isMentor(Long id) {
            boolean isMentor = false;
            MentorsRoles role = data.getMentorsRolesService().find(id);
            if (role != null) {
                if (role.getMentorsRolesName().equalsIgnoreCase("Mentor")) {
                    isMentor = true;
                }
            }
            return isMentor;
        }

        private boolean isResourPerson(Long id) {
            boolean isResourPerson = false;
            MentorsRoles role = data.getMentorsRolesService().find(id);
            if (role != null) {

                if (role.getMentorsRolesName().equalsIgnoreCase("Resource Person")) {
                    isResourPerson = true;
                }
            }
            return isResourPerson;
        }
    }

    class NewPersonGridForm extends Form {

        private GridLayout layout;

        public NewPersonGridForm() {
            setCaption("New Person Form");
            layout = new GridLayout(3, 10);
            layout.setMargin(true);
            layout.setSpacing(true);


            layout.setStyleName(Runo.CSSLAYOUT_SHADOW);
            HorizontalLayout firstHeader = new HorizontalLayout();
            firstHeader.setSizeFull();
            firstHeader.setStyleName(Runo.LAYOUT_DARKER);
            Label firstHeaderlabel = new Label("Person Demographics");
            firstHeaderlabel.setStyleName(Runo.LABEL_H2);
            firstHeader.addComponent(firstHeaderlabel);
            layout.addComponent(firstHeader, 0, 0, 2, 0);
            layout.setComponentAlignment(firstHeader, Alignment.MIDDLE_CENTER);


            HorizontalLayout formFooter = new HorizontalLayout();
            formFooter.setSizeFull();
            formFooter.setStyleName(Runo.LAYOUT_DARKER);
            layout.addComponent(formFooter, 0, 9, 1, 9);

            setLayout(layout);

        }

        @Override
        protected void attachField(Object propertyId, Field field) {
            if (propertyId.equals("surname")) {
                layout.addComponent(field, 0, 1);
            } else if (propertyId.equals("othername")) {
                layout.addComponent(field, 1, 1);
            } else if (propertyId.equals("firstname")) {
                layout.addComponent(field, 2, 1);


            } else if (propertyId.equals("dob")) {
                layout.addComponent(field, 0, 2);
            } else if (propertyId.equals("genderId")) {
                layout.addComponent(field, 1, 2);
            } else if (propertyId.equals("raceid")) {
                layout.addComponent(field, 2, 2);


            } else if (propertyId.equals("facilityId")) {
                layout.addComponent(field, 0, 3);
            } else if (propertyId.equals("positionId")) {
                layout.addComponent(field, 1, 3);
            } else if (propertyId.equals("startDate")) {
                layout.addComponent(field, 2, 3);


            } else if (propertyId.equals("idType")) {
                layout.addComponent(field, 0, 4);
            } else if (propertyId.equals("idValue")) {
                layout.addComponent(field, 1, 4);
            } else if (propertyId.equals("email")) {
                layout.addComponent(field, 2, 4);




            } else if (propertyId.equals("cellnumber")) {
                layout.addComponent(field, 0, 5);
            } else if (propertyId.equals("telephoneNumber")) {
                layout.addComponent(field, 1, 5);
            } else if (propertyId.equals("faxnumber")) {
                layout.addComponent(field, 2, 5);






            } else if (propertyId.equals("rolesId")) {
                layout.addComponent(field, 0, 6, 0, 7);
            } else if (propertyId.equals("expertiseId")) {
                layout.addComponent(field, 1, 6, 1, 7);
            } else if (propertyId.equals("competencyFieldId")) {
                layout.addComponent(field, 2, 6, 2, 7);
            } else if (propertyId.equals("id")) {
                layout.addComponent(field, 0, 8);
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
