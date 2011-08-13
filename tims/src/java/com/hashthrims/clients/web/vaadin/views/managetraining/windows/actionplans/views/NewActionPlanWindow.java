/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.managetraining.windows.actionplans.views;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.domain.EmployeeActionPlan;
import com.hashthrims.domain.Person;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.Reindeer;
import java.util.Date;

/**
 *
 * @author boniface
 */
public class NewActionPlanWindow extends Window implements ClickListener {

    private final FormLayout form = new FormLayout();
    private final TextArea actionPlan = new TextArea("Action Plan");
    private final DateField actionPlanDate = new DateField("Date: ");
    private final TextArea actionPlanReview = new TextArea("Action Plan Review");
    private final HorizontalLayout footer = new HorizontalLayout();
    private final Button addPlan = new Button("Submit Action Plan");
    private final Button cancel = new Button("Cancel");
    private final HashThrimsMain main;
    private final Person person;
    private static ClientDataService data = new ClientDataService();
    private final Long mentoringSessionId;

    public NewActionPlanWindow(Person p, Long sessionId, HashThrimsMain app) {
        main = app;
        mentoringSessionId = sessionId;
        person = p;

        addPlan.addListener((ClickListener) this);
        cancel.addListener((ClickListener) this);
        footer.setSizeFull();
        footer.addComponent(addPlan);
        footer.setComponentAlignment(addPlan, Alignment.MIDDLE_CENTER);
        footer.addComponent(cancel);
        footer.setComponentAlignment(cancel, Alignment.MIDDLE_CENTER);


        form.setSizeFull();
        setModal(true);
        setClosable(true);
        setHeight("800px");
        setWidth("800px");
        setPositionX(200);
        setPositionY(100);
        setCaption("New Mentoring Action Plan");


        final Panel panel = new Panel("New Plan After Mentoring ");
        panel.addStyleName(Reindeer.LAYOUT_BLUE);


        panel.setSizeFull();



        form.setMargin(true);
        actionPlan.setSizeFull();
        actionPlanReview.setSizeFull();
        actionPlanDate.setSizeFull();
        form.addComponent(actionPlan);
        form.addComponent(actionPlanDate);

        panel.setContent(form);
        addComponent(panel);
        addComponent(footer);
    }

    @Override
    public void buttonClick(ClickEvent event) {
        final Button source = event.getButton();
        if (source == cancel) {
            close();
        }
        if (source == addPlan) {
            EmployeeActionPlan plan = new EmployeeActionPlan();
            plan.setActionPlan((String) actionPlan.getValue());
            plan.setActionPlanDate((Date) actionPlanDate.getValue());
            plan.setStatus("PENDING");
            plan.setMentoringSessionId(mentoringSessionId);
            person.getActionPlans().add(plan);
            data.getPersonService().merge(person);
            close();

        }
    }
}
