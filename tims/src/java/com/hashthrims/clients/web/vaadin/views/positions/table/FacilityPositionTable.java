/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.positions.table;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.positions.ManagePositionsMenuView;
import com.hashthrims.clients.web.vaadin.views.positions.model.PositionBean;
import com.hashthrims.domain.jobs.Jobs;
import com.hashthrims.domain.offices.Department;
import com.hashthrims.domain.offices.Facility;
import com.hashthrims.domain.positions.PositionTypes;
import com.hashthrims.domain.positions.Positions;
import com.hashthrims.domain.positions.Status;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Table;
import com.vaadin.ui.themes.Reindeer;
import java.util.List;

/**
 *
 * @author boniface
 */
public class FacilityPositionTable extends Table {

    private final static ClientDataService data = new ClientDataService();
    private final HashThrimsMain main;
    private  EditPositionsWindow subWindow;

    public FacilityPositionTable(HashThrimsMain app, List<Positions> positions) {
        main = app;
        setSizeFull();
        // Define the names and data types of columns.
        addContainerProperty("Position Title", String.class, null);
        addContainerProperty("Position Status", String.class, null);
        addContainerProperty("Position Code", String.class, null);
        addContainerProperty("Position Type", String.class, null);
        addContainerProperty("Department", String.class, null);
        addContainerProperty("Facility", String.class, null);
        addContainerProperty("Edit", Button.class, null);
        addContainerProperty("Delete", Button.class, null);

        for (final Positions position : positions) {

            Button edit = new Button("Edit Position");
            edit.setData(position.getId());
            edit.addListener(new Button.ClickListener() {

                @Override
                public void buttonClick(ClickEvent event) {
                    editPosition(position);
                }

                private void editPosition(Positions p) {
                    PositionBean bean = getBean(p);
                    subWindow = new EditPositionsWindow(p, bean, main);
                    main.getMainWindow().addWindow(subWindow);

                }

                private PositionBean getBean(Positions p) {
                    final PositionBean positionBean = new PositionBean();

                    positionBean.setPositionCode(p.getPositionCode());

                    positionBean.setPositionComments(p.getPositionComments());


                    positionBean.setFacililty(getFacilityId(p.getFacililty()));
                    positionBean.setSupervisor(getSupervisorId(p.getSupervisor()));
                    positionBean.setDepartment(getDepartmentId(p.getDepartment()));
                    positionBean.setJob(getJobId(p.getJob()));
                    positionBean.setPositionType(getPositionTypeId(p.getPositionType()));
                    positionBean.setPositionStatus(getPositionStatusId(p.getPositionStatus()));
                    positionBean.setPositionId(p.getId());


                    return positionBean;
                }

                private Long getFacilityId(Facility facililty) {
                    if (facililty!=null) 
                        return facililty.getId();
                    return null;
                        
                    
                }

                private Long getSupervisorId(Positions supervisor) {
                    if(supervisor!=null)
                        return supervisor.getId();
                    return null;
                }

                private Long getDepartmentId(Department department) {
                    if(department!=null)
                        return department.getId();
                    return null;
                                
                }

                private Long getJobId(Jobs job) {
                    if(job!=null)
                        return job.getId();
                    return null;
                }

                private Long getPositionTypeId(PositionTypes positionType) {
                    if(positionType!=null)
                        return positionType.getId();
                    return null;
                }

                private Long getPositionStatusId(Status positionStatus) {
                    if(positionStatus!=null)
                        return positionStatus.getId();
                    return null;
                }

               
            });


            Button delete = new Button("Delete");
            delete.setData(position.getId());
            delete.addListener(new Button.ClickListener() {

                @Override
                public void buttonClick(ClickEvent event) {
                    data.getPositionsService().remove(position);
                    getWindow().showNotification("Position With Code " + " " + position.getPositionCode() + " Has Been Deleted");
                    main.mainView.setSecondComponent(new ManagePositionsMenuView(main, "FACILITIES"));

                }
            });
            delete.setStyleName(Reindeer.BUTTON_LINK);
            edit.setStyleName(Reindeer.BUTTON_LINK);


            addItem(new Object[]{
                        jobTitle(position.getJob()),
                        status(position.getPositionStatus()),
                        position.getPositionCode(),
                        postionType(position.getPositionType()),
                        derpartment(position.getDepartment()),
                        facilityName(position.getFacililty()),
                        edit,
                        delete
                    }, position.getId());

        }
        // Allow selecting items from the table.
        setSelectable(true);
        // Send changes in selection immediately to server.
        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);
        setNullSelectionAllowed(false);
        setColumnCollapsingAllowed(true);
    }

    private String facilityName(Facility facility) {
        String valueName = null;
        if (facility != null) {
            valueName = facility.getFacilityName();
        } else {
            valueName = null;
        }
        return valueName;
    }

    private Object supervisor(Positions supervisor) {
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
