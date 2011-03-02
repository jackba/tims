/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.positions.forms;

import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.positions.forms.position.PositionGridForm;
import com.hashthrims.domain.positions.Status;
import com.hashthrims.domain.jobs.Jobs;
import com.hashthrims.domain.offices.Department;
import com.hashthrims.domain.offices.Facility;
import com.hashthrims.domain.positions.PositionTypes;
import com.hashthrims.domain.positions.Positions;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Select;
import com.vaadin.ui.TextField;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author boniface
 */
public class PositionForm {

    // Define Buttons
    private Button save = new Button("Save");
    private Button edit = new Button("Edit");
    private Button cancel = new Button("Cancel");
    private Button update = new Button("Update");
    private Button delete = new Button("Delete");
    //Define Footer
    private HorizontalLayout footer;
    private static final ClientDataService data = new ClientDataService();

    public PositionForm() {
    }

    public Form createPositionFrom() {
        final Form form = new PositionGridForm();
        form.setImmediate(false);



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
        form.setFormFieldFactory(new FormFields());

        return form;
    }

    class FormFields extends DefaultFieldFactory implements Property.ValueChangeListener {

        private Select selectSupervisor;
        private Select selectFacility;
        private Select selectDepartment;
        private Select selectJob;
        private Select selectPositionType;
        private Select selectPositionStatus;
        private List<Positions> positions;
        private List<Facility> facilities;
        private List<Department> departments;
        private List<Jobs> jobs;
        private List<PositionTypes> positionTypes;
        private List<Status> statuses;

        @Override
        public Field createField(Item item, Object propertyId,
                Component uiContext) {

            Field field = super.createField(item, propertyId, uiContext);

            if ("positionId".equals(propertyId)) {
                field = new TextField("Position ID:");
                ((TextField) field).setVisible(false);

            } else if ("job".equals(propertyId)) {
                jobs = data.getJobService().findAll();
                selectJob = new Select("Position Title:");
                for (Jobs jb : jobs) {
                    selectJob.addItem(jb.getId());
                    selectJob.setItemCaption(jb.getId(),jb.getJob_tittle());
                }
                selectJob.setNewItemsAllowed(false);
                selectJob.setWidth("250");
                selectJob.setRequired(true);
                return selectJob;
            } else if ("supervisor".equals(propertyId)) {
                positions = data.getPositionsService().findAll();
                selectSupervisor = new Select("Supervisor:");
                selectSupervisor.addListener(this);
                selectSupervisor.setImmediate(false);
                for (Positions position : positions) {
                    selectSupervisor.addItem(position.getId());
                    selectSupervisor.setItemCaption(position.getId(), getJobTitle(position.getJob()));
                }
                selectSupervisor.setNewItemsAllowed(false);
                selectSupervisor.setWidth("250");
                selectSupervisor.setRequired(true);
                return selectSupervisor;
            } else if ("facililty".equals(propertyId)) {
                selectFacility = new Select("Facility:");
                selectFacility.addListener(this);
                selectFacility.setImmediate(true);
                
                facilities = data.getFacilityService().findAll();
                Collections.sort(facilities);
                for (Facility f : facilities) {
                    selectFacility.addItem(f.getId());
                    selectFacility.setItemCaption(f.getId(),f.getFacilityName());
                }
                selectFacility.setNewItemsAllowed(false);
                selectFacility.setWidth("250");
                selectFacility.setRequired(true);
                return selectFacility;
            } else if ("department".equals(propertyId)) {
                departments = data.getDepartmentService().findAll();
                selectDepartment = new Select("Department:");
                selectDepartment.addListener(this);
                selectDepartment.setImmediate(true);
                for (Department department : departments) {
                    selectDepartment.addItem(department.getId());
                     selectDepartment.setItemCaption(department.getId(),department.getDeptName());
                }
                selectDepartment.setNewItemsAllowed(true);
                selectDepartment.setWidth("250");
                selectDepartment.setRequired(true);
                return selectDepartment;

            } else if ("positionType".equals(propertyId)) {
                positionTypes = data.getPositionTypes().findAll();
                selectPositionType = new Select("Position Type:");
                for (PositionTypes postype : positionTypes) {
                    selectPositionType.addItem(postype.getId());
                     selectPositionType.setItemCaption(postype.getId(),postype.getPosTypeName());
                }
                selectPositionType.setNewItemsAllowed(false);
                selectPositionType.setWidth("250");
                selectPositionType.setRequired(true);
                return selectPositionType;
            } else if ("positionStatus".equals(propertyId)) {
                statuses = data.getStatusService().findAll();
                selectPositionStatus = new Select("Position Status:");
                for (Status stat : statuses) {
                    selectPositionStatus.addItem(stat.getId());
                     selectPositionStatus.setItemCaption(stat.getId(),stat.getStatus());
                }
                selectPositionStatus.setNewItemsAllowed(false);
                selectPositionStatus.setWidth("250");
                selectPositionStatus.setRequired(true);
                return selectPositionStatus;
            } else if ("positionTitle".equals(propertyId)) {
                field = new TextField("Position Title:");
                ((TextField) field).setColumns(20);
                ((TextField) field).setNullRepresentation("");
                ((TextField) field).setRequired(true);
                ((TextField) field).setRequiredError("Please Enter Position Name");
            } else if ("positionCode".equals(propertyId)) {
                field = new TextField("Position Code:");
                ((TextField) field).setColumns(19);
                ((TextField) field).setNullRepresentation("");
                ((TextField) field).setRequired(true);
                ((TextField) field).setRequiredError("Please Enter Value");
            } else if ("positionComments".equals(propertyId)) {
                field = new TextField("Position Comments:");
                ((TextField) field).setColumns(19);
                ((TextField) field).setNullRepresentation("");
                ((TextField) field).setRequired(true);
                ((TextField) field).setRequiredError("Please Enter Value");

            }

            return field;

        }

        @Override
        public void valueChange(ValueChangeEvent event) {
            final Property property = event.getProperty();

        }

        private String getJobTitle(Jobs job) {
            if (job != null) {
                return job.getJob_tittle();
            }
            return null;
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
