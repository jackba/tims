/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.trainingmentoring.views;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;

import com.hashthrims.clients.web.vaadin.views.trainingmentoring.TrainingMentoringMenuView;
import com.hashthrims.clients.web.vaadin.views.trainingmentoring.forms.MentoringSessionForm;
import com.hashthrims.clients.web.vaadin.views.trainingmentoring.model.MentoringSessionBean;
import com.hashthrims.clients.web.vaadin.views.trainingmentoring.tables.MentoringSessionTable;
import com.hashthrims.domain.traininglist.MentoringSession;
import com.hashthrims.infrastructure.factories.TrainingFactory;
import com.hashthrims.infrastructure.util.DataFieldsUtil;
import com.hashthrims.infrastructure.util.TimsUtil;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Form;
import com.vaadin.ui.VerticalLayout;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author boniface
 */
public class MentoringSessionViewPage extends VerticalLayout implements
        ClickListener, ValueChangeListener {

    private final HashThrimsMain main;
    private final Form form;
    private final MentoringSessionForm pform;
    private static final ClientDataService data = new ClientDataService();
    private final TrainingFactory factory = data.getTrainingFactory();
    private final DataFieldsUtil fieldValues = new DataFieldsUtil();
    private final MentoringSessionTable table;
    private final TimsUtil st = new TimsUtil();

    public MentoringSessionViewPage(HashThrimsMain app) {
        main = app;
        setSizeFull();
        pform = new MentoringSessionForm();
        form = pform.createMentoringSessionFrom();

        // Add Listeners
        pform.getSave().addListener((ClickListener) this);
        pform.getEdit().addListener((ClickListener) this);
        pform.getCancel().addListener((ClickListener) this);
        pform.getDelete().addListener((ClickListener) this);
        pform.getUpdate().addListener((ClickListener) this);

        final MentoringSessionBean bean = new MentoringSessionBean();
        final BeanItem item = new BeanItem(bean);
        form.setItemDataSource(item);

        addComponent(form);
        setComponentAlignment(form, Alignment.TOP_CENTER);
        table = new MentoringSessionTable(main);
        table.addListener((ValueChangeListener) this);
        addComponent(table);

    }

    @Override
    public void valueChange(ValueChangeEvent event) {
        final Property property = event.getProperty();

        if (property == table) {
            final Item record = table.getItem(table.getValue());
            MentoringSessionBean mentoringSession = new MentoringSessionBean();
            mentoringSession.setId(new Long(table.getValue().toString()));
            mentoringSession.setSessionName(st.getTableValues(record.getItemProperty("Session Name")));
            mentoringSession.setSessionType(st.getTableValues(record.getItemProperty("Session Type")));
            mentoringSession.setMentoringTheme(st.getTableValues(record.getItemProperty("Mentoring Theme")));
            mentoringSession.setSessionStatus(st.getTableValues(record.getItemProperty("Status")));
            mentoringSession.setMentoringNotes(st.getTableValues(record.getItemProperty("Notes")));
            mentoringSession.setInstitutionName(st.getTableValues(record.getItemProperty("Training Institution")));




            if (mentoringSession != form.getItemDataSource()) {
                final BeanItem item = new BeanItem(mentoringSession);
                form.setItemDataSource(item);
                // BUG enabling this Disables form.setVisibleItemProperties(cf.orderList());

                form.setReadOnly(true);
                //Buttons Behaviou
                pform.getSave().setVisible(false);
                pform.getEdit().setVisible(true);
                pform.getCancel().setVisible(true);
                pform.getDelete().setVisible(true);
                pform.getUpdate().setVisible(false);
            }
        }
    }

    @Override
    public void buttonClick(ClickEvent event) {
        final Button source = event.getButton();
        if (source == pform.getSave()) {
            saveNewMentoringSession(form);
            main.mainView.setSecondComponent(new TrainingMentoringMenuView(main, "SESSION"));
        } else if (source == pform.getEdit()) {
            form.setReadOnly(false);
            pform.getSave().setVisible(false);
            pform.getEdit().setVisible(false);
            pform.getCancel().setVisible(true);
            pform.getDelete().setVisible(false);
            pform.getUpdate().setVisible(true);
        } else if (source == pform.getCancel()) {
            main.mainView.setSecondComponent(new TrainingMentoringMenuView(main, "SESSION"));
        } else if (source == pform.getUpdate()) {
            saveEditedMentoringSession(form);

            main.mainView.setSecondComponent(new TrainingMentoringMenuView(main, "SESSION"));

        } else if (source == pform.getDelete()) {
            deleteMentoringSession(form);
            main.mainView.setSecondComponent(new TrainingMentoringMenuView(main, "SESSION"));

        }

    }

    public void saveNewMentoringSession(Form form) {

        final String sessionName = form.getField("sessionName").getValue().toString();
        final String sessionType = form.getField("sessionType").getValue().toString();
        final String mentoringTheme = form.getField("mentoringTheme").getValue().toString();
        final String institutionName = form.getField("institutionName").getValue().toString();
        final String sessionStatus = form.getField("sessionStatus").getValue().toString();
        final String mentoringNotes = form.getField("mentoringNotes").getValue().toString();

        final Map<String, String> simpleFields = new HashMap<String, String>();
        simpleFields.put("sessionName", sessionName);
       
        simpleFields.put("mentoringTheme", mentoringTheme);
        simpleFields.put("institutionName", institutionName);
        simpleFields.put("sessionStatus", sessionStatus);
        simpleFields.put("mentoringNotes", mentoringNotes);
        simpleFields.put("sessionType", sessionType);

       final Object sessionMentors = form.getField("sessionMentors").getValue();
       final List<String> mentors = fieldValues.getSelectListFields(sessionMentors);

        final Object competency = form.getField("mentoringCompetencies").getValue();
        final Object trainingFunder = form.getField("mentoringFunders").getValue();
        final List<String> competencies = fieldValues.getSelectListFields(competency);
        final List<String> trainingFunders = fieldValues.getSelectListFields(trainingFunder);

        final MentoringSession c = factory.createMentoringSession(simpleFields, competencies, trainingFunders,mentors);
        data.getMentoringSessionService().persist(c);
    }

    public void saveEditedMentoringSession(Form form) {


        final String sessionName = form.getField("sessionName").getValue().toString();
        final String sessionType = form.getField("sessionType").getValue().toString();
       
        final String mentoringTheme = form.getField("mentoringTheme").getValue().toString();
        final String institutionName = form.getField("institutionName").getValue().toString();
        final String sessionStatus = form.getField("sessionStatus").getValue().toString();
        final String mentoringNotes = form.getField("mentoringNotes").getValue().toString();

        final Map<String, String> simpleFields = new HashMap<String, String>();
        simpleFields.put("sessionName", sessionName);
        simpleFields.put("mentoringTheme", mentoringTheme);
        simpleFields.put("institutionName", institutionName);
        simpleFields.put("sessionStatus", sessionStatus);
        simpleFields.put("mentoringNotes", mentoringNotes);
        simpleFields.put("sessionType", sessionType);


        final Object sessionMentors = form.getField("sessionMentors").getValue();
        final List<String> mentors = fieldValues.getSelectListFields(sessionMentors);
        final Object competency = form.getField("mentoringCompetencies").getValue();
        final Object trainingFunder = form.getField("mentoringFunders").getValue();
        final List<String> competencies = fieldValues.getSelectListFields(competency);
        final List<String> trainingFunders = fieldValues.getSelectListFields(trainingFunder);


        final Long mentoringId = Long.parseLong(form.getField("id").getValue().toString());

        final MentoringSession c = factory.updateMentoringSessions(simpleFields, competencies, trainingFunders,mentors, mentoringId);
        data.getMentoringSessionService().merge(c);
    }

    public void deleteMentoringSession(Form form) {
        final Long MentoringSessionId = Long.parseLong(form.getField("id").getValue().toString());
        final MentoringSession c = factory.loadMentoringSessions(MentoringSessionId);
        data.getMentoringSessionService().remove(c);
    }
}
