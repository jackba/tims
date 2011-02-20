/*
 *
 * This Factory is still to be completed
 *
 *
 *
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.infrastructure.factories.positions;

import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.positions.model.dtos.DependantFields;
import com.hashthrims.clients.web.vaadin.views.positions.model.dtos.StringValues;
import com.hashthrims.domain.Contacts;
import com.hashthrims.domain.Employee;
import com.hashthrims.domain.EmployeePosition;
import com.hashthrims.domain.jobs.Jobs;
import com.hashthrims.domain.offices.Department;
import com.hashthrims.domain.offices.Facility;
import com.hashthrims.domain.offices.FacilityType;
import com.hashthrims.domain.positions.PositionTypes;
import com.hashthrims.domain.positions.Positions;
import com.hashthrims.domain.positions.SalarySources;
import com.hashthrims.domain.positions.Status;
import com.hashthrims.domain.regionlist.Country;
import com.hashthrims.infrastructure.conf.GetContext;
import com.hashthrims.services.DepartmentService;
import com.hashthrims.services.EmployeeService;
import com.hashthrims.services.FacilityService;
import com.hashthrims.services.JobsService;
import com.hashthrims.services.PositionSalarySourcesService;
import com.hashthrims.services.PositionTypesService;
import com.hashthrims.services.PositionsService;
import com.hashthrims.services.SalaryGradesService;
import com.hashthrims.services.SalarySourcesService;
import com.hashthrims.services.StatusService;
import java.util.Date;
import java.util.Map;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author boniface
 */
public class PositionsFactory {

    private PositionsService positionsService;
    private FacilityService facilityService;
    private DepartmentService departmentService;
    private EmployeeService employeeService;
    private ApplicationContext ctx = GetContext.getApplicationContext();
    private SalarySourcesService salarySourceService;
    private PositionTypesService positionTypeService;
    private StatusService statusService;
    private SalaryGradesService salaryGradesService;
    private PositionSalarySourcesService positionSalarySourcesService;
    private JobsService jobsService;
    private final ClientDataService data = new ClientDataService();

    public PositionTypes createPositionTypes(String posTypeName) {
        PositionTypes p = new PositionTypes();
        p.setPosTypeName(posTypeName);
        return p;
    }

    public PositionTypes loadPositionTypes(Long id) {
        positionTypeService = (PositionTypesService) ctx.getBean("positionTypesService");
        PositionTypes pt = positionTypeService.find(id);
        return pt;

    }

    public PositionTypes updatedPositionTypes(String positionTypes, Long id) {
        PositionTypes p = loadPositionTypes(id);
        p.setPosTypeName(positionTypes);
        return p;
    }

    public SalarySources createSalarySource(String salSourceName) {
        SalarySources s = new SalarySources();
        s.setSalSourceName(salSourceName);
        return s;
    }

    public SalarySources loadSalarySource(Long id) {
        salarySourceService = (SalarySourcesService) ctx.getBean("salarySourcesService");
        SalarySources s = salarySourceService.find(id);
        return s;
    }

    public Positions createPosition(String positionCode, String positionTitle, String positionDesc, Date postdate, String positionComments, String intrerviewComments, Date propoasedEndDate, Date propoasedHireDate, SalarySources salarySources, Employee employee, Department department, PositionTypes positionTypes, Facility facility) {
        Positions r = new Positions();
        r.setPositionCode(positionCode);

        r.setDepartment(department);
        r.setFacililty(facility);
        r.setPositionType(positionTypes);

        //r.setSalarySource(salarySources);


        return r;
    }

    public Positions loadPositions(Long id) {
        positionsService = (PositionsService) ctx.getBean("positionsService");
        Positions r = positionsService.find(id);
        return r;
    }

    public Department createDepartment(String deptName) {
        Department d = new Department();
        d.setDeptName(deptName);
        return d;
    }

    public Department loadDepartment(Long id) {
        departmentService = (DepartmentService) ctx.getBean("departmentService");
        Department d = departmentService.find(id);
        return d;
    }

    public Facility createFacility(String facilityName, String contactInfo, FacilityType facilityType, Country country, Contacts contacts) {
        Facility f = new Facility();
        f.setFacilityName(facilityName);
        //f.setContactInfo(contactInfo);
        f.setContact(contacts);
        //f.setCountry(country);
        f.setFacilityType(facilityType);

        return f;
    }

    public Facility loadFacility(Long id) {
        facilityService = (FacilityService) ctx.getBean("facilityService");
        Facility f = facilityService.find(id);
        return f;
    }

    public Positions updatedPositions(String positionCode, String positionTitle, String positionDesc, String positionComments, String intrerviewComments, Long positionsId) {
        Positions p = loadPositions(positionsId);

        p.setPositionCode(positionCode);

        p.setPositionComments(positionComments);

        return p;
    }

    public SalarySources updateSalarySource(String salarySourceName, Long salarySourceId) {
        SalarySources s = loadSalarySource(salarySourceId);
        s.setSalSourceName(salarySourceName);
        return s;
    }

    public Positions createPosition(StringValues val, Map<String, Long> dfields) {
        Positions position = new Positions();



        //SringValues

        position.setPositionCode(val.getPositionCode());
        position.setPositionComments(val.getPositionComments());


        //DependValues
        departmentService = (DepartmentService) ctx.getBean("departmentService");
        Department d = departmentService.find(dfields.get("department"));
        position.setDepartment(d);

        facilityService = (FacilityService) ctx.getBean("facilityService");
        Facility f = facilityService.find(dfields.get("facililty"));
        position.setFacililty(f);

        positionTypeService = (PositionTypesService) ctx.getBean("positionTypesService");
        PositionTypes pt = positionTypeService.find(dfields.get("positionType"));
        position.setPositionType(pt);

        statusService = (StatusService) ctx.getBean("statusService");
        Status status = statusService.find(dfields.get("positionStatus"));
        position.setPositionStatus(status);


        positionsService = (PositionsService) ctx.getBean("positionsService");
        Positions pos = positionsService.find(dfields.get("supervisor"));
        position.setSupervisor(pos);


        //DependValues
        jobsService = (JobsService) ctx.getBean("jobsService");
        Jobs job = jobsService.find(dfields.get("job"));
        position.setJob(job);

        return position;
    }

    public Positions updatePosition(StringValues val, Map<String, Long> dfields, Long positionId) {

        positionsService = (PositionsService) ctx.getBean("positionsService");
        Positions position = positionsService.find(positionId);

        //SringValues

        position.setPositionCode(val.getPositionCode());
        position.setPositionComments(val.getPositionComments());

        //DependValues
        //DependValues
        departmentService = (DepartmentService) ctx.getBean("departmentService");
        Department d = departmentService.find(dfields.get("department"));
        position.setDepartment(d);

        facilityService = (FacilityService) ctx.getBean("facilityService");
        Facility f = facilityService.find(dfields.get("facililty"));
        position.setFacililty(f);

        positionTypeService = (PositionTypesService) ctx.getBean("positionTypesService");
        PositionTypes pt = positionTypeService.find(dfields.get("positionType"));
        position.setPositionType(pt);

        statusService = (StatusService) ctx.getBean("statusService");
        Status status = statusService.find(dfields.get("positionStatus"));
        position.setPositionStatus(status);


        positionsService = (PositionsService) ctx.getBean("positionsService");
        Positions pos = positionsService.find(dfields.get("supervisor"));
        position.setSupervisor(pos);


        //DependValues
        jobsService = (JobsService) ctx.getBean("jobsService");
        Jobs job = jobsService.find(dfields.get("job"));
        position.setJob(job);

        return position;
    }

    public EmployeePosition createEmployeePosition(Positions postion, String status, Date startDate, Date endDate) {
        EmployeePosition emppos = new EmployeePosition();
        emppos.setEnddate(endDate);
        emppos.setPosition(postion);
        emppos.setStartDate(startDate);
        emppos.setStatus(status);
        return emppos;
    }

    public EmployeePosition updateEmployeePosition(Positions position, String status, Date startDate, Date endDate, Long id) {
        EmployeePosition emppos = loadEmployeePosition(id);
        emppos.setEnddate(endDate);

        Positions newPosition = position;
        Positions oldPosition = emppos.getPosition();


        emppos.getPosition().equals(position);

        if (newPosition.equals(oldPosition)) {
            emppos.setPosition(newPosition);
        } else {
            final Status filed = data.getStatusService().getByPropertyName("status", "FILLED");
            final Status vacant = data.getStatusService().getByPropertyName("status", "VACANT");
            oldPosition.setPositionStatus(vacant);
            data.getPositionsService().merge(oldPosition);
            newPosition.setPositionStatus(filed);
            data.getPositionsService().merge(newPosition);
            emppos.setPosition(newPosition);
        }
        emppos.setStartDate(startDate);
        emppos.setStatus(status);
        return emppos;
    }
    private EmployeePosition loadEmployeePosition(Long id) {
        EmployeePosition ep = data.getEmployeePositionService().find(id);
        return ep;
    }
}
