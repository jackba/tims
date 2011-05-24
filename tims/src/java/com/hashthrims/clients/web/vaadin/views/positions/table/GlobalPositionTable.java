/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.positions.table;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.domain.jobs.Jobs;
import com.hashthrims.domain.offices.Department;
import com.hashthrims.domain.positions.GlobalPositions;
import com.hashthrims.domain.positions.PositionTypes;
import com.hashthrims.domain.positions.Status;
import com.vaadin.ui.Table;
import java.util.List;

/**
 *
 * @author boniface
 */
public class GlobalPositionTable extends Table {

    private final static ClientDataService data = new ClientDataService();
    private final HashThrimsMain main;

    public GlobalPositionTable(HashThrimsMain app) {
        this.main = app;
        // Make Table fill all space
        setSizeFull();
        // Define the names and data types of columns.
        addContainerProperty("Position Title", String.class, null);
        addContainerProperty("Position Status", String.class, null);
        addContainerProperty("Position Code", String.class, null);
        addContainerProperty("Position Type", String.class, null);
        addContainerProperty("Department", String.class, null);
        addContainerProperty("Supervisor", String.class, null);


        List<GlobalPositions> positions = data.getGlobalPositionsService().findAll();
        for (GlobalPositions position : positions) {

            addItem(new Object[]{
                        jobTitle(position.getJob()),
                        status(position.getPositionStatus()),
                        position.getPositionCode(),
                        postionType(position.getPositionType()),
                        derpartment(position.getDepartment()),
                        supervisor(position.getSupervisor()),}, position.getId());

        }
        // Allow selecting items from the table.
        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);
        setNullSelectionAllowed(false);
        setColumnCollapsingAllowed(true);
    }

    private Object supervisor(GlobalPositions supervisor) {
        String valueName = null;
        if (supervisor != null) {
            valueName = getJobTitle(supervisor.getJob());
        } else {
            valueName = null;
        }
        return valueName;
    }

    private Object derpartment(Department department) {
        String valueName = null;
        if (department != null) {
            valueName = department.getDeptName();
        } else {
            valueName = null;
        }
        return valueName;
    }

    private Object jobTitle(Jobs job) {
        String valueName = null;
        if (job != null) {
            valueName = job.getJob_tittle();
        } else {
            valueName = null;
        }
        return valueName;
    }

    private Object postionType(PositionTypes positionType) {
        String valueName = null;
        if (positionType != null) {
            valueName = positionType.getPosTypeName();
        } else {
            valueName = null;
        }
        return valueName;
    }

    private Object status(Status positionStatus) {
        String valueName = null;
        if (positionStatus != null) {
            valueName = positionStatus.getStatus();
        } else {
            valueName = null;
        }
        return valueName;
    }

    private String getJobTitle(Jobs job) {
        if (job != null) {
            return job.getJob_tittle();
        }
        return null;
    }
}
