/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.infrastructure.factories;

import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.domain.Contacts;
import com.hashthrims.domain.EmployeeMentoring;
import com.hashthrims.domain.Person;
import com.hashthrims.domain.employeelist.CompetencyEvaluation;
import com.hashthrims.domain.employeelist.CompetencyList;
import com.hashthrims.domain.employeelist.MentorsRoles;
import com.hashthrims.domain.positions.Status;
import com.hashthrims.domain.regionlist.City;
import com.hashthrims.domain.regionlist.Country;
import com.hashthrims.domain.traininglist.ContiuingEducationCourse;
import com.hashthrims.domain.traininglist.MentoringCompetencies;
import com.hashthrims.domain.traininglist.MentoringField;
import com.hashthrims.domain.traininglist.MentoringFunders;
import com.hashthrims.domain.traininglist.MentoringMentors;
import com.hashthrims.domain.traininglist.MentoringSession;
import com.hashthrims.domain.traininglist.MentoringSessionObjective;
import com.hashthrims.domain.traininglist.MentoringSessionTheme;
import com.hashthrims.domain.traininglist.MentoringSessionType;
import com.hashthrims.domain.traininglist.SessionType;
import com.hashthrims.domain.traininglist.MentoringTheme;
import com.hashthrims.domain.traininglist.Mentors;
import com.hashthrims.domain.traininglist.ScheduledCourses;
import com.hashthrims.domain.traininglist.SessionAreasOfStrengthening;
import com.hashthrims.domain.traininglist.TrainingCourseCategory;
import com.hashthrims.domain.traininglist.TrainingCourseEvaluation;
import com.hashthrims.domain.traininglist.TrainingCourseRequestors;
import com.hashthrims.domain.traininglist.TrainingFunder;
import com.hashthrims.domain.traininglist.TrainingInstitution;
import com.hashthrims.infrastructure.conf.GetContext;
import com.hashthrims.services.CityService;
import com.hashthrims.services.CompetencyEvaluationService;
import com.hashthrims.services.ContiuingEducationCourseService;
import com.hashthrims.services.CountryService;
import com.hashthrims.services.ScheduledCoursesService;
import com.hashthrims.services.TrainingCourseCategoryService;
import com.hashthrims.services.TrainingCourseEvaluationService;
import com.hashthrims.services.TrainingCourseRequestorsService;
import com.hashthrims.services.TrainingFunderService;
import com.hashthrims.services.TrainingInstitutionService;
import com.hashthrims.services.TrainingService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author abismail
 */
public class TrainingFactory {

    private ApplicationContext ctx = GetContext.getApplicationContext();
    private TrainingService trainingService;
    private ScheduledCoursesService scheduledCoursesService;
    private CountryService countryService;
    private TrainingCourseEvaluationService trainingCourseEvaluationService;
    private CompetencyEvaluationService competencyEvaluationService;
    private TrainingCourseRequestorsService trainingCourseRequestorsService;
    private ContiuingEducationCourseService contiuingEducationCourseService;
    private TrainingCourseCategoryService categoryService;
    private TrainingFunderService trainingFunderService;
    private TrainingInstitutionService institutionService;
    private CityService cityService;
    private static ClientDataService data = new ClientDataService();

    public ScheduledCourses createScheduledCourses(int numOfStuds, Date startDate, Date endDate, String classNotes, Country classLocation, String classSite, String district) {
        ScheduledCourses sc = new ScheduledCourses();
        sc.setNumOfStuds(numOfStuds);
        sc.setStartDate(startDate);
        sc.setEndDate(endDate);

        return sc;
    }

    public TrainingCourseCategory createTrainingCourseCategory(String categoryName) {
        TrainingCourseCategory sc = new TrainingCourseCategory();
        sc.setCategoryName(categoryName);

        return sc;
    }

    public ScheduledCourses loadScheduledCourses(Long id) {
        scheduledCoursesService = (ScheduledCoursesService) ctx.getBean("scheduledCoursesService");
        ScheduledCourses sc = scheduledCoursesService.find(id);
        return sc;
    }

    public TrainingCourseCategory loadTrainingCourseCategory(Long id) {
        categoryService = (TrainingCourseCategoryService) ctx.getBean("trainingCourseCategoryService");
        TrainingCourseCategory sc = categoryService.find(id);
        return sc;
    }

    public TrainingCourseEvaluation createTrainingCourseEvaluation(String evaluationName) {
        TrainingCourseEvaluation tce = new TrainingCourseEvaluation();
        tce.setEvaluationName(evaluationName);
        return tce;
    }

    public CompetencyEvaluation createCompetencyEvaluation(String competencyEvaluation) {
        CompetencyEvaluation e = new CompetencyEvaluation();
        e.setCompt_type_name(competencyEvaluation);
        return e;
    }

    public CompetencyEvaluation loadCompetencyEvaluation(Long id) {
        competencyEvaluationService = (CompetencyEvaluationService) ctx.getBean("competencyEvaluationService");
        CompetencyEvaluation e = competencyEvaluationService.find(id);
        return e;
    }

    public TrainingCourseEvaluation loadTrainingCourseEvaluation(Long id) {
        trainingCourseEvaluationService = (TrainingCourseEvaluationService) ctx.getBean("trainingCourseEvaluationService");
        TrainingCourseEvaluation tce = trainingCourseEvaluationService.find(id);
        return tce;
    }

    public TrainingCourseRequestors createTrainingCourseRequestors(String requestorName) {
        TrainingCourseRequestors tcr = new TrainingCourseRequestors();
        tcr.setRequestorName(requestorName);
        return tcr;
    }

    public ContiuingEducationCourse createContiuingEducationCourse(String courseName, String hrs) {
        ContiuingEducationCourse tcr = new ContiuingEducationCourse();
        tcr.setNameOfContinueCourse(courseName);
        tcr.setCreditHours(hrs);
        return tcr;
    }

    public ContiuingEducationCourse loadContiuingEducationCourse(Long id) {
        contiuingEducationCourseService = (ContiuingEducationCourseService) ctx.getBean("contiuingEducationCourseService");
        ContiuingEducationCourse tcc = contiuingEducationCourseService.find(id);
        return tcc;
    }

    public TrainingCourseRequestors loadTrainingCourseRequestors(Long id) {
        trainingCourseRequestorsService = (TrainingCourseRequestorsService) ctx.getBean("trainingCourseRequestorsService");
        TrainingCourseRequestors tcc = trainingCourseRequestorsService.find(id);
        return tcc;
    }

    public EmployeeMentoring loadTraining(Long id) {
        trainingService = (TrainingService) ctx.getBean("trainingService");
        EmployeeMentoring t = trainingService.find(id);
        return t;

    }

    public TrainingCourseCategory updatedTrainingCourseCategory(String categoryName, Long id) {
        TrainingCourseCategory c = loadTrainingCourseCategory(id);
        c.setCategoryName(categoryName);
        c.setId(id);
        return c;
    }

    public ContiuingEducationCourse updatedContiuingEducationCourse(String categoryName, String creditHrs, Long id) {
        ContiuingEducationCourse c = loadContiuingEducationCourse(id);
        c.setCreditHours(creditHrs);
        c.setNameOfContinueCourse(categoryName);
        return c;
    }

    public TrainingCourseEvaluation updateTrainingCourseEvaluation(String courseEvaluationName, Long courseId) {
        TrainingCourseEvaluation tce = loadTrainingCourseEvaluation(courseId);
        tce.setEvaluationName(courseEvaluationName);
        return tce;
    }

    public TrainingFunder createTrainingFunder(String funderName, String cityName, Contacts contacts) {
        cityService = (CityService) ctx.getBean("cityService");
        City c = cityService.getByPropertyName("name", cityName);
        TrainingFunder f = new TrainingFunder();
        f.setContact(contacts);
        f.setTrainingFunderName(funderName);
        f.setCity(c);
        return f;

    }

    public TrainingInstitution loadTrainingInstitution(Long id) {
        institutionService = (TrainingInstitutionService) ctx.getBean("trainingInstitutionService");
        TrainingInstitution s = institutionService.find(id);
        return s;
    }

    public TrainingFunder loadTrainingFunder(Long id) {
        trainingFunderService = (TrainingFunderService) ctx.getBean("trainingFunderService");
        TrainingFunder s = trainingFunderService.find(id);
        return s;
    }

    public TrainingFunder updateTrainingFunder(String funderName, String cityName, Contacts contacts, Long funderId) {
        cityService = (CityService) ctx.getBean("cityService");
        City c = cityService.getByPropertyName("name", cityName);
        TrainingFunder f = loadTrainingFunder(funderId);
        f.setContact(contacts);
        f.setTrainingFunderName(funderName);
        f.setCity(c);
        return f;
    }

    public TrainingInstitution createTrainingInstitution(String institutionName, String cityName, Contacts contacts) {
        cityService = (CityService) ctx.getBean("cityService");
        City c = cityService.getByPropertyName("name", cityName);
        TrainingInstitution f = new TrainingInstitution();
        f.setContact(contacts);
        f.setTrainingInstitution(institutionName);
        f.setCity(c);
        return f;
    }

    public TrainingInstitution updateTrainingInstitution(String institutionName, String cityName, Contacts contacts, Long institutionId) {
        cityService = (CityService) ctx.getBean("cityService");
        City c = cityService.getByPropertyName("name", cityName);
        TrainingInstitution f = loadTrainingInstitution(institutionId);
        f.setContact(contacts);
        f.setTrainingInstitution(institutionName);
        f.setCity(c);
        return f;
    }

    public MentoringField createMentoringField(String fieldName) {
        MentoringField m = new MentoringField();
        m.setFieldName(fieldName);
        return m;
    }

    public MentoringField updatedMentoringField(String fieldName, Long mentoringId) {
        MentoringField m = loadMentoringField(mentoringId);
        m.setFieldName(fieldName);
        return m;
    }

    public MentoringField loadMentoringField(Long mentoringId) {

        MentoringField m = data.getMentoringFieldService().find(mentoringId);
        return m;
    }

    public MentoringSession createMentoringSession(Map<String, String> simpleFields,
            List<String> competencies, List<String> trainingFunders, List<String> mentors) {





        return null;
    }

    public MentoringSession updateMentoringSessions(Map<String, String> simpleFields,
            List<String> competencies, List<String> trainingFunders, List<String> mentors, Long mentoringId) {
        MentoringSession mSession = loadMentoringSessions(mentoringId);

        return mSession;
    }

    public MentoringSession loadMentoringSessions(Long MentoringSessionId) {
        MentoringSession m = data.getMentoringSessionService().find(MentoringSessionId);
        return m;
    }

    public MentorsRoles createMentorsRoles(String fieldName) {
        MentorsRoles m = new MentorsRoles();
        m.setMentorsRolesName(fieldName);
        return m;
    }

    public MentorsRoles updatedMentorsRoles(String fieldName, Long mentoringId) {
        MentorsRoles m = loadMentorsRoles(mentoringId);
        m.setMentorsRolesName(fieldName);
        return m;
    }

    public MentorsRoles loadMentorsRoles(Long mentoringId) {
        return data.getMentorsRolesService().find(mentoringId);
    }

    public MentoringSession createMentoringSession(Map<String, String> names, Map<String, Date> dates, Map<String, Long> ids, Map<String, List<Long>> lists) {
        MentoringSession session = new MentoringSession();

        session.setSessionName(names.get("sessionName"));
        session.setMentoringNotes(names.get("comments"));

        session.setStartDate(dates.get("startDate"));
        session.setEndDate(dates.get("endDate"));


        session.setInstitutionName(data.getTrainingInstitutionService().find(ids.get("institutionName")));
        session.setSessionStatus(data.getStatusService().find(ids.get("sessionStatus")));
        session.setMentoringVenue(data.getFacilityService().find(ids.get("mentoringVenue")));
        session.setMentoringSubjectArea_CompetencyType(ids.get("mentoringSubjectArea"));




        List<Long> mentoringFundersIds = lists.get("mentoringFundersIds");
        for (Long id : mentoringFundersIds) {
            TrainingFunder funder = data.getTrainingFunderService().find(id);
            MentoringFunders mf = new MentoringFunders();
            mf.setFundersId(funder.getId());
            mf.setFundersName(funder.getTrainingFunderName());
            session.getMentoringFunders().add(mf);

        }

        List<Long> sessionMentorsIds = lists.get("sessionMentorsIds");
        for (Long id : sessionMentorsIds) {
            MentoringMentors m = new MentoringMentors();
            m.setMentorsId(id);
            session.getMentoringMentors().add(m);

        }

        List<Long> mentoringObjectivesIds = lists.get("mentoringObjectivesIds");
        MentoringSessionObjective objective = new MentoringSessionObjective();
        for (Long id : mentoringObjectivesIds) {
            objective.setMentoringObjectiveId(id);
            session.getMentoringObjective().add(objective);
        }

        List<Long> mentoringThemesIds = lists.get("mentoringThemesIds");
        MentoringSessionTheme stheme = new MentoringSessionTheme();
        for (Long id : mentoringThemesIds) {
            stheme.setSessionMentoringTheme(id);
            session.getMentoringSessionTheme().add(stheme);
        }

        List<Long> mentoringSessionTypeIds = lists.get("mentoringSessionTypeIds");
        MentoringSessionType mst = new MentoringSessionType();
        for (Long id : mentoringSessionTypeIds) {
            mst.setMentoringSessionType(id);
            session.getMentoringSessionType().add(mst);
        }

        List<Long> areasOfStrentheningIds = lists.get("areasOfStrentheningIds");
        SessionAreasOfStrengthening area = new SessionAreasOfStrengthening();
        for (Long id : areasOfStrentheningIds) {
            area.setAreasOfStrentheningId(id);
            session.getSessionAreasOfStrengthening().add(area);
        }

        return session;
    }

    public MentoringSession updateMentoringSessions(Map<String, String> names, Map<String, Date> dates, Map<String, Long> ids, Map<String, List<Long>> lists, Long mentoringId) {

        MentoringSession session = loadMentoringSessions(mentoringId);
        //Reset Competencies and Funders
        data.getMentoringSessionService().restFundersAndCompetencies(session);
        session.setSessionName(names.get("sessionName"));
        session.setMentoringNotes(names.get("comments"));
        session.setStartDate(dates.get("startDate"));
        session.setEndDate(dates.get("endDate"));


        session.setInstitutionName(data.getTrainingInstitutionService().find(ids.get("institutionName")));
        session.setSessionStatus(data.getStatusService().find(ids.get("sessionStatus")));
        session.setMentoringVenue(data.getFacilityService().find(ids.get("mentoringVenue")));
        session.setMentoringSubjectArea_CompetencyType(ids.get("mentoringSubjectArea"));




        List<Long> mentoringFundersIds = lists.get("mentoringFundersIds");
        for (Long id : mentoringFundersIds) {
            TrainingFunder funder = data.getTrainingFunderService().find(id);
            MentoringFunders mf = new MentoringFunders();
            mf.setFundersId(funder.getId());
            mf.setFundersName(funder.getTrainingFunderName());
            session.getMentoringFunders().add(mf);

        }

        List<Long> sessionMentorsIds = lists.get("sessionMentorsIds");
        for (Long id : sessionMentorsIds) {
            MentoringMentors m = new MentoringMentors();
            m.setMentorsId(id);
            session.getMentoringMentors().add(m);

        }

        List<Long> mentoringObjectivesIds = lists.get("mentoringObjectivesIds");
        MentoringSessionObjective objective = new MentoringSessionObjective();
        for (Long id : mentoringObjectivesIds) {
            objective.setMentoringObjectiveId(id);
            session.getMentoringObjective().add(objective);
        }

        List<Long> mentoringThemesIds = lists.get("mentoringThemesIds");
        MentoringSessionTheme stheme = new MentoringSessionTheme();
        for (Long id : mentoringThemesIds) {
            stheme.setSessionMentoringTheme(id);
            session.getMentoringSessionTheme().add(stheme);
        }

        List<Long> mentoringSessionTypeIds = lists.get("mentoringSessionTypeIds");
        MentoringSessionType mst = new MentoringSessionType();
        for (Long id : mentoringSessionTypeIds) {
            mst.setMentoringSessionType(id);
            session.getMentoringSessionType().add(mst);
        }
        List<Long> areasOfStrentheningIds = lists.get("areasOfStrentheningIds");
        SessionAreasOfStrengthening area = new SessionAreasOfStrengthening();
        for (Long id : areasOfStrentheningIds) {
            area.setAreasOfStrentheningId(id);
            session.getSessionAreasOfStrengthening().add(area);
        }

        return session;
    }
}
