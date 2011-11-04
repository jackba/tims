/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.people.tables;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.people.ManagePeopleMenuView;
import com.hashthrims.clients.web.vaadin.views.people.forms.PersonEditForm;
import com.hashthrims.clients.web.vaadin.views.people.models.PersonBean;
import com.hashthrims.clients.web.vaadin.views.people.views.PersonDetailsView;
import com.hashthrims.domain.Person;
import com.hashthrims.domain.PersonRoles;
import com.hashthrims.infrastructure.util.GetUserCredentials;
import com.hashthrims.infrastructure.util.TimsUtil;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.themes.Reindeer;
import java.util.List;

/**
 *
 * @author stud
 */
public class NewPersonTable extends Table {

    private static ClientDataService data = new ClientDataService();
    private final GetUserCredentials user = new GetUserCredentials();
    private HashThrimsMain main;
    private TimsUtil st = new TimsUtil();
    private List<Person> persons;

    public NewPersonTable(HashThrimsMain app, List<Person> p) {
        this.main = app;
        persons = p;
        // Make Table fill all space
        setSizeFull();
        // Define the names and data types of columns.
        addContainerProperty("First Name", String.class, null);
        addContainerProperty("Surname", String.class, null);
        addContainerProperty("Position Title", String.class, null);
        addContainerProperty("Roles", String.class, null);
        addContainerProperty("Facility", String.class, null);
        addContainerProperty("Details", Button.class, null);
        // addContainerProperty("Edit", Button.class, null);
        if (user.isUserWithRole("ROLE_ADMIN")) {
            addContainerProperty("Delete", Button.class, null);
        }


        // Add Data Columns
        // List<Person> persons = data.getPersonService().findAll();
        for (final Person person : persons) {
            Button detailsField = new Button("show details");
            detailsField.setData(person.getId());
            detailsField.addListener(new Button.ClickListener() {

                @Override
                public void buttonClick(ClickEvent event) {
                    // Get the item identifier from the user-defined data.
                    Long itemId = (Long) event.getButton().getData();
                    main.mainView.setSecondComponent(new PersonDetailsView(person, main, ""));
                }
            });

            Button edit = new Button("Edit Person");
            edit.setData(person.getId());
            edit.addListener(new Button.ClickListener() {

                @Override
                public void buttonClick(ClickEvent event) {
                    // Get the item identifier from the user-defined data.
                    Long itemId = (Long) event.getButton().getData();
                    editPerson(person);
                }

                private void editPerson(Person person) {
                    PersonBean b = getBean(person);
//                    City city = data.getCityService().getByPropertyName("name", b.getResidence());
//                    b.setCountryName(city.getDistrict().getCounty().getProvince().getCountry().getCountryName());
//                    b.setProvinceName(city.getDistrict().getCounty().getProvince().getRegionName());
//                    b.setCountyName(city.getDistrict().getCounty().getCountyName());
//                    b.setDistrictName(city.getDistrict().getDistrictName());
                    main.mainView.setSecondComponent(new PersonEditForm(main, b));

//                    

                }
            });


            Button delete = new Button("Delete");
            delete.setData(person.getId());
            delete.addListener(new Button.ClickListener() {

                @Override
                public void buttonClick(ClickEvent event) {
                   
                    deleteDialog();
                    

                }

                private void deleteDialog() {

                    final Window deleteDialog = new Window("About to Delete a Record");
                    deleteDialog.setModal(true);
                    deleteDialog.setStyleName(Reindeer.WINDOW_BLACK);
                    deleteDialog.setWidth("260px");
                    deleteDialog.setResizable(false);
                    deleteDialog.setClosable(false);
                    deleteDialog.setDraggable(false);
                    deleteDialog.setCloseShortcut(KeyCode.ESCAPE, null);

                    Label deleteTextQuestion = new Label(
                            "Are you sure you want to Delete This Record?",
                            Label.CONTENT_XHTML);
                    deleteDialog.addComponent(deleteTextQuestion);

                    HorizontalLayout buttons = new HorizontalLayout();
                    buttons.setSpacing(true);
                    Button yes = new Button("Yes", new Button.ClickListener() {

                        @Override
                        public void buttonClick(ClickEvent event) {
                            data.getPersonService().remove(person);
                            main.mainView.setSecondComponent(new ManagePeopleMenuView(main, "SEARCH"));
                            main.getMainWindow().removeWindow(deleteDialog);
                        }
                    });
                    yes.setStyleName(Reindeer.BUTTON_DEFAULT);
                    yes.focus();
                    buttons.addComponent(yes);
                    Button no = new Button("No", new Button.ClickListener() {

                        @Override
                        public void buttonClick(ClickEvent event) {
                             main.mainView.setSecondComponent(new ManagePeopleMenuView(main, "SEARCH"));
                             main.getMainWindow().removeWindow(deleteDialog);
                        }
                    });
                    buttons.addComponent(no);

                    deleteDialog.addComponent(buttons);
                    ((VerticalLayout) deleteDialog.getContent()).setComponentAlignment(buttons,
                            "center");
                    ((VerticalLayout) deleteDialog.getContent()).setSpacing(true);

                    main.getMainWindow().addWindow(deleteDialog);
                }
            });
            delete.setStyleName(Reindeer.BUTTON_LINK);
            edit.setStyleName(Reindeer.BUTTON_LINK);
            detailsField.setStyleName(Reindeer.BUTTON_LINK);

            if (user.isUserWithRole("ROLE_ADMIN")) {
                addItem(new Object[]{person.getPersonName(),
                            person.getPersonSurname(),
                            st.getPositionTitle(person.getPosition()),
                            getRoleName(person.getPersonRoles()),
                            st.getFacilityName(person.getPosition()),
                            detailsField,
                            delete}, person.getId());
            } else {

                addItem(new Object[]{person.getPersonName(),
                            person.getPersonSurname(),
                            st.getPositionTitle(person.getPosition()),
                            getRoleName(person.getPersonRoles()),
                            st.getFacilityName(person.getPosition()),
                            detailsField
                        }, person.getId());

            }
        }

        // Allow selecting items from the table.
        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);

    }

    private PersonBean getBean(Person person) {

        PersonBean bean = new PersonBean();
        bean.setPersonId(person.getId());
        bean.setPersonName(person.getPersonName());
        bean.setPersonOtherName(person.getPersonOtherName());
        bean.setPersonSurname(person.getPersonSurname());
        bean.setResidence(st.getResidence(person.getResidence()));
        bean.setGender(st.getGender(person.getDemography()));


        bean.setDob(st.getDob(person.getDemography()));
        return bean;
    }

    private Object getRoleName(List<PersonRoles> personRoles) {
        StringBuilder roles = new StringBuilder();
        for (PersonRoles role : personRoles) {
            roles.append(role.getRoleName());
            roles.append(", ");

        }
        return roles;
    }
}
