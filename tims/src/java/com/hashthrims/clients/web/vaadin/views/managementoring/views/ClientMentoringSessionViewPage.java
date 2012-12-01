/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.managementoring.views;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.managementoring.ManageMentoringMenuView;

import com.hashthrims.clients.web.vaadin.views.trainingmentoring.forms.MentoringSessionForm;
import com.hashthrims.clients.web.vaadin.views.trainingmentoring.model.MentoringSessionBean;
import com.hashthrims.clients.web.vaadin.views.trainingmentoring.tables.MentoringSessionTable;
import com.hashthrims.domain.traininglist.MentoringFunders;
import com.hashthrims.domain.traininglist.MentoringMentors;
import com.hashthrims.domain.traininglist.MentoringSession;
import com.hashthrims.domain.traininglist.MentoringSessionObjective;
import com.hashthrims.domain.traininglist.MentoringSessionTheme;
import com.hashthrims.domain.traininglist.MentoringSessionType;
import com.hashthrims.domain.traininglist.SessionAreasOfStrengthening;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author boniface
 */
public class ClientMentoringSessionViewPage extends VerticalLayout implements
        ClickListener, ValueChangeListener {

    private final HashThrimsMain main;
    private final Form form;
    private final MentoringSessionForm pform;
    private static final ClientDataService data = new ClientDataService();
    private final TrainingFactory factory = data.getTrainingFactory();
    private final DataFieldsUtil fieldValues = new DataFieldsUtil();
    private final MentoringSessionTable table;
    private final TimsUtil st = new TimsUtil();

    public ClientMentoringSessionViewPage(HashThrimsMain app) {
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
            final MentoringSession c = factory.loadMentoringSessions(new Long(table.getValue().toString()));

            mentoringSession.setId(c.getId());
            mentoringSession.setSessionName(c.getSessionName());
            mentoringSession.setStartDate(c.getStartDate());
            mentoringSession.setEndDate(c.getEndDate());
            mentoringSession.setComments(c.getMentoringNotes());
            mentoringSession.setInstitutionName(c.getInstitutionName().getId());

            List<MentoringFunders> f = c.getMentoringFunders();
            List<Long> fundersIds = new ArrayList<Long>();
            for (MentoringFunders mf : f) {
                fundersIds.add(mf.getFundersId());
            }
            mentoringSession.setMentoringFunders(fundersIds);

            List<MentoringSessionObjective> objectives = c.getMentoringObjective();
            List<Long> objectivesIds = new ArrayList<Long>();
            for (MentoringSessionObjective objective : objectives) {
                objectivesIds.add(objective.getMentoringObjectiveId());
                mentoringSession.setMentoringObjectives(objectivesIds);
            }

            List<SessionAreasOfStrengthening> areas = c.getSessionAreasOfStrengthening();
            List<Long> areasIds = new ArrayList<Long>();
            for (SessionAreasOfStrengthening area : areas) {
                areasIds.add(area.getAreasOfStrentheningId());
                mentoringSession.setMentoringObjectives(areasIds);
            }

            List<MentoringSessionType> mts = c.getMentoringSessionType();
            List<Long> mtIds = new ArrayList<Long>();
            for (MentoringSessionType mt : mts) {
                mtIds.add(mt.getMentoringSessionType());
            }
            mentoringSession.setMentoringSessionType(mtIds);



            List<MentoringSessionTheme> mths = c.getMentoringSessionTheme();
            List<Long> mthIds = new ArrayList<Long>();
            for (MentoringSessionTheme mth : mths) {
                mtIds.add(mth.getSessionMentoringTheme());
            }
            mentoringSession.setMentoringThemes(mthIds);


            mentoringSession.setMentoringVenue(c.getMentoringVenue().getId());

            List<MentoringMentors> mentors = c.getMentoringMentors();
            List<Long> mentorsIds = new ArrayList<Long>();
            for (MentoringMentors mentor : mentors) {
                mentorsIds.add(mentor.getMentorsId());
            }
            mentoringSession.setSessionMentors(mentorsIds);
            //mentoringSession.setSessionStatus(c.getSessionStatus().getId());
            mentoringSession.setMentoringSubjectArea(c.getMentoringSubjectArea_CompetencyType());





            if (mentoringSession != form.getItemDataSource()) {
                final BeanItem item = new BeanItem(mentoringSession);
                form.setItemDataSource(item);


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
            main.mainView.setSecondComponent(new ManageMentoringMenuView(main, "CREATE"));
        } else if (source == pform.getEdit()) {
            form.setReadOnly(false);
            pform.getSave().setVisible(false);
            pform.getEdit().setVisible(false);
            pform.getCancel().setVisible(true);
            pform.getDelete().setVisible(false);
            pform.getUpdate().setVisible(true);
        } else if (source == pform.getCancel()) {
            main.mainView.setSecondComponent(new ManageMentoringMenuView(main, "CREATE"));
        } else if (source == pform.getUpdate()) {
            saveEditedMentoringSession(form);

            main.mainView.setSecondComponent(new ManageMentoringMenuView(main, "CREATE"));

        } else if (source == pform.getDelete()) {
            deleteMentoringSession(form);
            main.mainView.setSecondComponent(new ManageMentoringMenuView(main, "CREATE"));

        }

    }

    public void saveNewMentoringSession(Form form) {

        final String sessionName = getStringvalue(form.getField("sessionName").getValue());
        final String mentoringNotes = getStringvalue(form.getField("comments").getValue());
        final Date startDate = fieldValues.getDateFields(form.getField("startDate").getValue());
        final Date endDate = fieldValues.getDateFields(form.getField("startDate").getValue());
        final Map<String, Date> dates = new HashMap<String, Date>();
        dates.put("startDate", startDate);
        dates.put("endDate", endDate);

        final Long mentoringSubjectArea = Long.parseLong(form.getField("mentoringSubjectArea").getValue().toString());
        final Long institutionName = Long.parseLong(form.getField("institutionName").getValue().toString());
        final Long mentoringVenue = Long.parseLong(form.getField("mentoringVenue").getValue().toString());

        final Map<String, Long> ids = new HashMap<String, Long>();
        ids.put("mentoringSubjectArea", mentoringSubjectArea);
        ids.put("institutionName", institutionName);
        ids.put("mentoringVenue", mentoringVenue);


        final Object mentoringFunders = form.getField("mentoringFunders").getValue();
        final Object sessionMentors = form.getField("sessionMentors").getValue();
        final Object mentoringObjectives = form.getField("mentoringObjectives").getValue();
        final Object mentoringThemes = form.getField("mentoringThemes").getValue();
        final Object mentoringSessionType = form.getField("mentoringSessionType").getValue();
        final Object areasOfStrenthening = form.getField("areasOfStrenthening").getValue();



        final List<Long> mentoringFundersIds = fieldValues.getSelectListLongFields(mentoringFunders);
        final List<Long> sessionMentorsIds = fieldValues.getSelectListLongFields(sessionMentors);
        final List<Long> mentoringObjectivesIds = fieldValues.getSelectListLongFields(mentoringObjectives);
        final List<Long> mentoringThemesIds = fieldValues.getSelectListLongFields(mentoringThemes);
        final List<Long> mentoringSessionTypeIds = fieldValues.getSelectListLongFields(mentoringSessionType);
        final List<Long> areasOfStrentheningIds = fieldValues.getSelectListLongFields(areasOfStrenthening);

        final Map<String, List<Long>> lists = new HashMap<String, List<Long>>();
        lists.put("mentoringFundersIds", mentoringFundersIds);
        lists.put("sessionMentorsIds", sessionMentorsIds);
        lists.put("mentoringObjectivesIds", mentoringObjectivesIds);
        lists.put("mentoringThemesIds", mentoringThemesIds);
        lists.put("mentoringSessionTypeIds", mentoringSessionTypeIds);
        lists.put("areasOfStrentheningIds", areasOfStrentheningIds);

        final Map<String, String> names = new HashMap<String, String>();
        names.put("sessionName", sessionName);
        names.put("comments", mentoringNotes);

        final MentoringSession c = factory.createMentoringSession(names, dates, ids, lists);
        data.getMentoringSessionService().persist(c);
    }

    public void saveEditedMentoringSession(Form form) {
        final Long mentoringId = Long.parseLong(form.getField("id").getValue().toString());


        final String sessionName = getStringvalue(form.getField("sessionName").getValue());
        final String mentoringNotes = getStringvalue(form.getField("comments").getValue());
        final Date startDate = fieldValues.getDateFields(form.getField("startDate").getValue());
        final Date endDate = fieldValues.getDateFields(form.getField("startDate").getValue());
        final Map<String, Date> dates = new HashMap<String, Date>();
        dates.put("startDate", startDate);
        dates.put("endDate", endDate);

        final Long mentoringSubjectArea = Long.parseLong(form.getField("mentoringSubjectArea").getValue().toString());
        final Long institutionName = Long.parseLong(form.getField("institutionName").getValue().toString());

        final Long mentoringVenue = Long.parseLong(form.getField("mentoringVenue").getValue().toString());

        final Map<String, Long> ids = new HashMap<String, Long>();
        ids.put("mentoringSubjectArea", mentoringSubjectArea);
        ids.put("institutionName", institutionName);
        ids.put("mentoringVenue", mentoringVenue);


        final Object mentoringFunders = form.getField("mentoringFunders").getValue();
        final Object sessionMentors = form.getField("sessionMentors").getValue();
        final Object mentoringObjectives = form.getField("mentoringObjectives").getValue();
        final Object mentoringThemes = form.getField("mentoringThemes").getValue();
        final Object mentoringSessionType = form.getField("mentoringSessionType").getValue();
        final Object areasOfStrenthening = form.getField("areasOfStrenthening").getValue();



        final List<Long> mentoringFundersIds = fieldValues.getSelectListLongFields(mentoringFunders);
        final List<Long> sessionMentorsIds = fieldValues.getSelectListLongFields(sessionMentors);
        final List<Long> mentoringObjectivesIds = fieldValues.getSelectListLongFields(mentoringObjectives);
        final List<Long> mentoringThemesIds = fieldValues.getSelectListLongFields(mentoringThemes);
        final List<Long> mentoringSessionTypeIds = fieldValues.getSelectListLongFields(mentoringSessionType);
        final List<Long> areasOfStrentheningIds = fieldValues.getSelectListLongFields(areasOfStrenthening);

        final Map<String, List<Long>> lists = new HashMap<String, List<Long>>();
        lists.put("mentoringFundersIds", mentoringFundersIds);
        lists.put("sessionMentorsIds", sessionMentorsIds);
        lists.put("mentoringObjectivesIds", mentoringObjectivesIds);
        lists.put("mentoringThemesIds", mentoringThemesIds);
        lists.put("mentoringSessionTypeIds", mentoringSessionTypeIds);
        lists.put("areasOfStrentheningIds", areasOfStrentheningIds);

        final Map<String, String> names = new HashMap<String, String>();
        names.put("sessionName", sessionName);
        names.put("comments", mentoringNotes);
        final MentoringSession c = factory.updateMentoringSessions(names, dates, ids, lists, mentoringId);
        data.getMentoringSessionService().merge(c);
    }

    public void deleteMentoringSession(Form form) {
        final Long mentoringSessionId = Long.parseLong(form.getField("id").getValue().toString());
        final MentoringSession c = factory.loadMentoringSessions(mentoringSessionId);
        data.getMentoringSessionService().remove(c);
    }

    private String getStringvalue(Object value) {
        if (value != null) {
            return value.toString();
        }
        return null;
    }
}
