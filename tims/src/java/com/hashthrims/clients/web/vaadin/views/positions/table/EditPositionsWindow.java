/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.positions.table;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.positions.forms.position.FacilityPositionsGridForm;
import com.hashthrims.clients.web.vaadin.views.positions.model.PositionBean;
import com.hashthrims.clients.web.vaadin.views.positions.model.dtos.StringValues;
import com.hashthrims.domain.jobs.Jobs;
import com.hashthrims.domain.offices.Department;
import com.hashthrims.domain.offices.Facility;
import com.hashthrims.domain.positions.PositionTypes;
import com.hashthrims.domain.positions.Positions;
import com.hashthrims.domain.positions.Status;
import com.hashthrims.infrastructure.factories.employeelist.EducationsFactory;
import com.hashthrims.infrastructure.factories.positions.PositionsFactory;
import com.hashthrims.infrastructure.util.DataFieldsUtil;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.Select;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author boniface
 */
public class EditPositionsWindow extends Window implements ClickListener {

    private final HashThrimsMain main;
    private final Positions position;
    private static final ClientDataService data = new ClientDataService();
    private final DataFieldsUtil fieldValues = new DataFieldsUtil();
    private final Button update = new Button(" Update facility Position");
    private final Button cancel = new Button("Cancel Update Operation");
    private final Form formData;
    private final PositionsFactory factory = data.getPositionFactory();

    public EditPositionsWindow(Positions p, PositionBean bean, HashThrimsMain app) {
        main = app;
        position = p;

        final Form form = new FacilityPositionsGridForm();
        form.setFormFieldFactory(new FormFields());

        final BeanItem item = new BeanItem(bean);

        form.setItemDataSource(item);


        update.addListener((ClickListener) this);
        cancel.addListener((ClickListener) this);

        HorizontalLayout formFooter = new HorizontalLayout();
        formFooter.addComponent(update);
        formFooter.addComponent(cancel);
        form.setFooter(formFooter);

        formData = form;
        setModal(true);
        setClosable(true);
        setHeight("450px");
        setWidth("600px");
        setPositionX(200);
        setPositionY(100);
        setCaption("Edit Facility Position");
        addComponent(form);
    }

    class FormFields extends DefaultFieldFactory implements Property.ValueChangeListener {

        private Select selectSupervisor;
        private ListSelect selectFacilities;
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
                    selectJob.setItemCaption(jb.getId(), jb.getJob_tittle());
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
            } else if ("facilities".equals(propertyId)) {
                selectFacilities = new ListSelect("Select Facilities:");
                selectFacilities.setImmediate(true);
                selectFacilities.setNewItemsAllowed(false);
                selectFacilities.setWidth("500");
                selectFacilities.setHeight("100");
                selectFacilities.setNullSelectionAllowed(false);
                selectFacilities.setMultiSelect(true);
                facilities = data.getFacilityService().findAll();
                Collections.sort(facilities);
                for (Facility f : facilities) {
                    selectFacilities.addItem(f.getId());
                    selectFacilities.setItemCaption(f.getId(), f.getFacilityName());
                }
                selectFacilities.setRequired(true);
                return selectFacilities;
            } else if ("department".equals(propertyId)) {
                departments = data.getDepartmentService().findAll();
                selectDepartment = new Select("Department:");
                selectDepartment.addListener(this);
                selectDepartment.setImmediate(true);
                for (Department department : departments) {
                    selectDepartment.addItem(department.getId());
                    selectDepartment.setItemCaption(department.getId(), department.getDeptName());
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
                    selectPositionType.setItemCaption(postype.getId(), postype.getPosTypeName());
                }
                selectPositionType.setNewItemsAllowed(false);
                selectPositionType.setWidth("250");
                selectPositionType.setRequired(true);
                return selectPositionType;
            } else if ("positionStatus".equals(propertyId)) {
                statuses = data.getStatusService().findAll();
                selectPositionStatus = new Select("Position Status:");
                for (Status stat : statuses) {
                    if ("VACANT".equals(stat.getStatus())) {
                        selectPositionStatus.addItem(stat.getId());
                        selectPositionStatus.setItemCaption(stat.getId(), stat.getStatus());
                    }
                }
                selectPositionStatus.setNewItemsAllowed(false);
                selectPositionStatus.setWidth("250");
                selectPositionStatus.setRequired(true);
                return selectPositionStatus;
            } else if ("positionCode".equals(propertyId)) {
                field = new TextField("Position Code:");
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

    @Override
    public void buttonClick(ClickEvent event) {
        final Button source = event.getButton();

        if (source == cancel) {
            close();
        } else if (source == update) {
            updatePosition(formData);
            close();
        }
    }

    private void updatePosition(Form form) {
        final String positionCode = fieldValues.getStringFields(form.getField("positionCode").getValue());
        final String positionComments = fieldValues.getStringFields(form.getField("positionComments").getValue());
        final StringValues val = new StringValues();
        val.setPositionCode(positionCode);
        val.setPositionComments(positionComments);

        final Long facililty = fieldValues.getLongFields(form.getField("facililty").getValue());
        final Long supervisor = fieldValues.getLongFields(form.getField("supervisor").getValue());
        final Long department = fieldValues.getLongFields(form.getField("department").getValue());
        final Long job = fieldValues.getLongFields(form.getField("job").getValue());
        final Long positionType = fieldValues.getLongFields(form.getField("positionType").getValue());
        final Long positionStatus = fieldValues.getLongFields(form.getField("positionStatus").getValue());

        final Map<String, Long> dfields = new HashMap<String, Long>();
        dfields.put("department", department);
        dfields.put("facililty", facililty);
        dfields.put("job", job);
        dfields.put("positionStatus", positionStatus);
        dfields.put("positionType", positionType);
        dfields.put("supervisor", supervisor);

        final Long positionId = Long.parseLong(form.getField("positionId").getValue().toString());
        final Positions p = factory.updatePosition(val, dfields, positionId);
        data.getPositionsService().merge(p);
    }
}
