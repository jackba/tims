/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.managetraining.windows.traineeprofle;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.views.managetraining.util.PeopleUtil;
import com.hashthrims.clients.web.vaadin.views.managetraining.windows.UpdatePositionWindow;
import com.hashthrims.domain.Person;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;

/**
 *
 * @author boniface
 */
public class TraineePersonalInformation extends VerticalLayout implements Button.ClickListener {

    private final GridLayout personGrid = new GridLayout(4, 4);
    private Button editPosition = new Button("Edit Position");
    private final PeopleUtil util = new PeopleUtil();
    private final HashThrimsMain main;
    private final Person person;

    public TraineePersonalInformation(Person trainee, HashThrimsMain app) {
        person=trainee;
        main = app;
        setSizeFull();
        personGrid.setSizeFull();

        editPosition.addListener((Button.ClickListener) this);

        personGrid.addComponent(new Label(" <h3> <u> Participant Profile </u></h3>", Label.CONTENT_XHTML), 0, 0, 1, 0);
        personGrid.addComponent(editPosition, 2, 0, 3, 0);

        personGrid.addComponent(new Label(" <b>Last Name </b>", Label.CONTENT_XHTML), 0, 1);
        personGrid.addComponent(new Label(person.getPersonSurname()), 1, 1);

        personGrid.addComponent(new Label(" <b>Middle Name</b>", Label.CONTENT_XHTML), 0, 2);
        personGrid.addComponent(new Label(person.getPersonOtherName()), 1, 2);

        personGrid.addComponent(new Label(" <b>First Name</b>", Label.CONTENT_XHTML), 0, 3);
        personGrid.addComponent(new Label(person.getPersonName()), 1, 3);


        personGrid.addComponent(new Label(" <b>Facility: </b>", Label.CONTENT_XHTML), 2, 1);
        personGrid.addComponent(new Label(getFacility(person)), 3, 1);

        personGrid.addComponent(new Label(" <b>Position: </b>", Label.CONTENT_XHTML), 2, 2);
        personGrid.addComponent(new Label(getPosition(person)), 3, 2);

        personGrid.addComponent(new Label(" <b>Position Code: </b>", Label.CONTENT_XHTML), 2, 3);
        personGrid.addComponent(new Label(getPositionCode(person)), 3, 3);


        addComponent(new Label("<hr>", Label.CONTENT_XHTML));

        addComponent(new Label("<center> <H1> Person Profile </H1> </center>", Label.CONTENT_XHTML));

        addComponent(new Label("<hr>", Label.CONTENT_XHTML));

        addComponent(personGrid);

        addComponent(new Label("<hr>", Label.CONTENT_XHTML));

        addComponent(new Label("<center> <H1> " + person.getPersonName() + " " + person.getPersonSurname() + "'s Previous Courses </H1> </center>", Label.CONTENT_XHTML));

        addComponent(new TraineeCoursesTable(person));

        addComponent(new Label("<hr>", Label.CONTENT_XHTML));

    }

    private String getFacility(Person person) {
        if (person != null) {
            return util.getFacilityForPerson(person);
        }
        return null;
    }

    private String getPosition(Person person) {
        if (person != null) {
            return util.getPositionForPerson(person);
        }
        return null;

    }

    private String getPositionCode(Person person) {
        if (person != null) {
            return util.getPositionCodeForPerson(person);
        }
        return null;
    }

    public Button getEditPosition() {
        return editPosition;
    }

    public void setEditPosition(Button editPosition) {
        this.editPosition = editPosition;
    }

    @Override
    public void buttonClick(ClickEvent event) {
        final Button source = event.getButton();
        if (source == editPosition) {
            main.getMainWindow().addWindow(new UpdatePositionWindow(person,main));
        }
    }
}
