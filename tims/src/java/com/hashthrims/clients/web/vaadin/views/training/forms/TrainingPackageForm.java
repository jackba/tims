/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.clients.web.vaadin.views.training.forms;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.models.TrainingBean;
import com.vaadin.Application;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.themes.Runo;
import com.vaadin.ui.*;
import java.util.*;
/**
 *
 * @author abismail
 */
public class TrainingPackageForm extends Form implements ClickListener{
private Button save = new Button("Save", (ClickListener) this);
    private Button cancel = new Button("Cancel", (ClickListener) this);
    private Button edit = new Button("Edit", (ClickListener) this);
    private boolean newPersonInfoMode = false;
    private HashThrimsMain main;
     private static ClientDataService data = new ClientDataService();
    //private Form form;
    private HorizontalLayout footer;
    private static final String COMMON_FIELD_WIDTH = "12em";
    private GridLayout ourLayout;
private Form form;
    public TrainingPackageForm(HashThrimsMain app) {
        main = app;
        setSizeFull();
        form = new Form();
        form.setImmediate(true);
        form.setCaption("Job ");
        TrainingBean bean = new TrainingBean();


        form.setFormFieldFactory(new FormFieldFactory() {

            @Override
            public Field createField(Item item, Object propertyId, Component uiContext) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });
        BeanItem item = new BeanItem(bean);
        form.setItemDataSource(item);

        setLayout(ourLayout);
        setCaption("Personal Information");
        setWriteThrough(false); // we want explicit 'apply'
        setInvalidCommitted(false); // no invalid values in datamodel
        setDescription("Use This Form to Set or Update Person Information");
//        PersonalInformationBean bean = new PersonalInformationBean();
//        BeanItem item = new BeanItem(bean);
//        setItemDataSource(item);
        setFormFieldFactory(new TrainingPackageFieldFactory());

        footer = new HorizontalLayout();
        footer.setSpacing(true);
        footer.addComponent(save);
        footer.addComponent(cancel);
        footer.addComponent(edit);
        footer.setVisible(true);
        footer.setMargin(true);

        // Determines which properties are shown, and in which order:
        List order = new ArrayList();
        order.add("firstname");
        order.add("surname");
        order.add("othernames");
        order.add("nationality");
        order.add("residency");


        setVisibleItemProperties(order);
        setWriteThrough(false);



        setFooter(footer);
        setStyleName(Runo.PANEL_LIGHT);

    }

    @Override
    protected void attachField(Object propertyId, Field field) {
        if (propertyId.equals("firstname")) {
            ourLayout.addComponent(field, 0, 0);
        } else if (propertyId.equals("surname")) {
            ourLayout.addComponent(field, 0, 1);
        } else if (propertyId.equals("othernames")) {
            ourLayout.addComponent(field, 0, 2);
        } else if (propertyId.equals("nationality")) {
            ourLayout.addComponent(field, 1, 0);
        } else if (propertyId.equals("residency")) {
            ourLayout.addComponent(field, 1, 1);
        }
    }

    @Override
    public void buttonClick(ClickEvent event) {
        final Button source = event.getButton();

        if (source == save) {
            setReadOnly(true);
            save.setVisible(false);
            edit.setVisible(true);
            //Customer cust = data.getCustomer();
            //cust.getAddress().setCity(form.getField("city").getValue().toString());
            // data.getService().merge(cust);

        } else if (source == cancel) {
            setReadOnly(true);
            edit.setVisible(true);
            save.setVisible(false);

        } else if (source == edit) {
            setReadOnly(false);
            edit.setVisible(false);
            save.setVisible(true);
        }


    }

    static class TrainingPackageFieldFactory extends DefaultFieldFactory {

        @Override
        public Field createField(Item item, Object propertyId,
                Component uiContext) {

            Field field = super.createField(item, propertyId, uiContext);


            if ("firstname".equals(propertyId)) {
                field = new TextField("First Name:");
                ((TextField) field).setColumns(30);
                ((TextField) field).setNullRepresentation("");
            } else if ("surname".equals(propertyId)) {
                field = new TextField("Surname:");
                field.setStyleName(Runo.LABEL_H2);
                ((TextField) field).setColumns(30);
                ((TextField) field).setNullRepresentation("");
                ((TextField) field).setStyleName(Runo.LABEL_H2);

            } else if ("othernames".equals(propertyId)) {
                field = new TextField("Other Names");
                ((TextField) field).setColumns(30);
                ((TextField) field).setNullRepresentation("");
            } else if ("nationality".equals(propertyId)) {
                field = new TextField("Nationality");
                ((TextField) field).setColumns(30);
                ((TextField) field).setNullRepresentation("");
            } else if ("residency".equals(propertyId)) {
                field = new TextField("Residence");
                ((TextField) field).setColumns(30);
                ((TextField) field).setNullRepresentation("");
            }

            return field;
        }
    }

    public static class TabAccordian extends Accordion implements Button.ClickListener {

    private HashThrimsMain main;
    public static final String MANAGE_PEOPLE = "Manage People";
    public static final String SEARCH = "Search";
    public static final String REPORTS = "Reports";
    public static final String CONFIGURE_SYSTEM = "Configure System";
    public static final String SYSTEM_USERS = "System Users";
    public static final String COURSES = " Courses";
    public static final String CHANGE_PASSWORD = "Change Password";
    private Button blistPeople = new Button("List People");
    private Button baddPerson = new Button("Add New Person");
    private Button bsearchForPeople = new Button("Search For People");
    private Button bsearchForPosition = new Button("Search For Position");
    private Button blistCourses = new Button("List Courses");
    private Button baddCourse = new Button("Add Course");
    private Button bupdateCourse = new Button("Update Course");
    private Button bscheduleCourse = new Button("Schedule Training");
    private Button baddUser = new Button("Add User");
    private Button bupdateUser = new Button("Update User");
    private Button buserLogs = new Button("See User Logs");
    private Button bchangePassword = new Button("Change Password");

    public TabAccordian(HashThrimsMain app) {
        main = app;
        setSizeFull();

        VerticalLayout managePeople = new VerticalLayout();
        blistPeople = new Button("List People");
        baddPerson = new Button("Add New Person");
        blistPeople.addListener((ClickListener) this);
        baddPerson.addListener((ClickListener) this);

        managePeople.addComponent(blistPeople);
        managePeople.addComponent(baddPerson);
        addTab(managePeople, MANAGE_PEOPLE, null);


        VerticalLayout search = new VerticalLayout();
        bsearchForPeople = new Button("Search For People");
        bsearchForPosition = new Button("Search For Position");
        bsearchForPeople.addListener((ClickListener) this);
        bsearchForPosition.addListener((ClickListener) this);


        search.addComponent(bsearchForPeople);
        search.addComponent(bsearchForPosition);
        addTab(search, SEARCH, null);

//        VerticalLayout reports = new VerticalLayout();
//        ReportsTree reportTree = new ReportsTree();
//        reports.addComponent(reportTree);
//        addTab(reports, REPORTS, null);


        VerticalLayout courses = new VerticalLayout();
        blistCourses = new Button("List Courses");
        baddCourse = new Button("Add Course");
        bupdateCourse = new Button("Update Course");
        bscheduleCourse = new Button("Schedule Training");
        blistCourses.addListener((ClickListener) this);
        baddCourse.addListener((ClickListener) this);
        bupdateCourse.addListener((ClickListener) this);
        bscheduleCourse.addListener((ClickListener) this);

        courses.addComponent(blistCourses);
        courses.addComponent(baddCourse);
        courses.addComponent(bupdateCourse);
        courses.addComponent(bscheduleCourse);
        addTab(courses, COURSES, null);

//        VerticalLayout configureSystem = new VerticalLayout();
//        ConfigureTree configureTree = new ConfigureTree(main);
//        configureSystem.addComponent(configureTree);
//
//        addTab(configureSystem, CONFIGURE_SYSTEM, null);

        VerticalLayout systemUsers = new VerticalLayout();
        baddUser = new Button("Add User");
        bupdateUser = new Button("Update User");
        buserLogs = new Button("See User Logs");
        baddUser.addListener((ClickListener) this);
        bupdateUser.addListener((ClickListener) this);
        buserLogs.addListener((ClickListener) this);

        systemUsers.addComponent(baddUser);
        systemUsers.addComponent(bupdateUser);
        systemUsers.addComponent(buserLogs);
        addTab(systemUsers, SYSTEM_USERS, null);




        VerticalLayout password = new VerticalLayout();
        bchangePassword = new Button("Change Password");
        password.addComponent(bchangePassword);
        bchangePassword.addListener((ClickListener) this);

        bchangePassword.getStyleName();
        addTab(password, CHANGE_PASSWORD, null);

    }

    @Override
    public void buttonClick(ClickEvent event) {
        final Button source = event.getButton();

//        if (source == baddCourse) {
//            showAddCourseForm();
//        } else if (source == baddUser) {
//            showAddUserForm();
//        } else if (source == bchangePassword) {
//            showCHangePasswordForm();
//        } else if (source == blistCourses) {
//            showCoursesList();
//        } else if (source == blistPeople) {
//            showPeopleList();
//        } else if (source == bscheduleCourse) {
//            showScheduleCoursePage();
//        } else if (source == bsearchForPeople) {
//            showPeopleSearchForm();
//        } else if (source == bsearchForPosition) {
//            showPositionSearchForm();
//        } else if (source == bupdateCourse) {
//            showCourseUpdatePage();
//        } else if (source == bupdateUser) {
//            showUpdateUserPage();
//        } else if (source == buserLogs) {
//            showUserLogPage();
//        }else if (source == baddPerson) {
//            showAddNewPersonForm();
//        }
    }
    }
public static class DisplayTable extends Application {
        private Table tblDataTable = null;
        private List qcSQL = new ArrayList();
        private TrainingPackageForm main;

        @Override
        public void init() {
            tblDataTable = new Table();

            tblDataTable.setData((Object) qcSQL);
            tblDataTable.setVisible(true);
            tblDataTable.setEnabled(true);
            tblDataTable.setCaption("Vaadin");

            Label label = new Label("Hello Vaadin user");
//            create a method in the main class in wherein you can add the table, then use that method below to add this class
//            main.addComponent(label);
//            main.addComponent(tblDataTable);
//            setMainWindow(main);
       }
    }
}
