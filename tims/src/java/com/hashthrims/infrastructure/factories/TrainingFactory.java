/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.infrastructure.factories;

import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.domain.Contacts;
import com.hashthrims.domain.EmployeeMentoring;
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
import com.hashthrims.domain.traininglist.MentoringSessionType;
import com.hashthrims.domain.traininglist.MentoringTheme;
import com.hashthrims.domain.traininglist.Mentors;
import com.hashthrims.domain.traininglist.ScheduledCourses;
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

//    public EmployeeMentoring createTraining(String notes, boolean retraining, boolean competency) {
//        EmployeeMentoring t = new EmployeeMentoring();
////        this value must also be generated
//        ScheduledCourses schedule = new ScheduledCourses();
//        Date requestedDate = new Date();
////        TrainingCourseEvaluation courseEvaluation = (new TrainingCourseEvaluationFactory()).;
//
//        TrainingCourseRequestors courseRequestor;
//        t.setNotes(notes);
//        t.setRequestedDate(requestedDate);
//        t.setRetraining(retraining);
//        t.setCompetency(competency);
////        courseEvaluation
//        t.setCourseEvaluation(new TrainingCourseEvaluation());
////        courseRequestor
//        t.setCourseRequestor(new TrainingCourseRequestors());
//        t.setSchedule(schedule);
//        return t;
//    }
    public ScheduledCourses createScheduledCourses(int numOfStuds, Date startDate, Date endDate, String classNotes, Country classLocation, String classSite, String district) {
        ScheduledCourses sc = new ScheduledCourses();
        sc.setNumOfStuds(numOfStuds);
        sc.setClassNotes(classNotes);
        sc.setClassSite(classSite);
        sc.setStartDate(startDate);
        sc.setEndDate(endDate);
        sc.setClassLocation(classLocation);

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
        MentoringSession ms = new MentoringSession();
        List<MentoringFunders> funders = new ArrayList<MentoringFunders>();
        List<MentoringCompetencies> comps = new ArrayList<MentoringCompetencies>();
        List<MentoringMentors> mentoringMentors = new ArrayList<MentoringMentors>();


        ms.setSessionName(simpleFields.get("sessionName"));
        ms.setMentoringNotes(simpleFields.get("mentoringNotes"));

        Long themeId = Long.parseLong(simpleFields.get("mentoringTheme").toString());
        MentoringTheme theme = data.getMentoringThemeService().find(themeId);
        ms.setMentoringTheme(theme);

        Long instId = Long.parseLong(simpleFields.get("institutionName").toString());
        TrainingInstitution ti = data.getTrainingInstitutionService().find(instId);
        ms.setInstitutionName(ti);

        Status status = data.getStatusService().getByPropertyName("status", simpleFields.get("sessionStatus"));
        ms.setSessionStatus(status);

        Long sessionId = Long.parseLong(simpleFields.get("sessionType").toString());
        MentoringSessionType menttype = data.getMentoringSessionTypeService().find(sessionId);
        ms.setMentoringSessionType(menttype);

        for (String funderName : trainingFunders) {
            TrainingFunder f = data.getTrainingFunderService().getByPropertyName("trainingFunderName", funderName);
            MentoringFunders cf = new MentoringFunders();
            cf.setFundersName(f.getTrainingFunderName());
            cf.setFundersId(f.getId());
            funders.add(cf);
        }


        for (String competencyList : competencies) {
            CompetencyList l = data.getCompetencyList().getByPropertyName("compName", competencyList);
            MentoringCompetencies cc = new MentoringCompetencies();
            cc.setCompetencyId(l.getId());
            cc.setCompetencyName(l.getComp_name());
            comps.add(cc);
        }

        for (String mentor : mentors) {
            Long mentorId = Long.parseLong(mentor);
            Mentors m = data.getMentorsService().find(mentorId);
            MentoringMentors mm = new MentoringMentors();
            mm.setMentorsId(m.getId());
            mentoringMentors.add(mm);

        }
        ms.setMentoringMentors(mentoringMentors);
        ms.setMentoringFunders(funders);
        ms.setMentoringCompetencies(comps);




        return ms;
    }

    public MentoringSession updateMentoringSessions(Map<String, String> simpleFields,
            List<String> competencies, List<String> trainingFunders, List<String> mentors, Long mentoringId) {
        MentoringSession mSession = loadMentoringSessions(mentoringId);

        //Reset Competencies and Funders
        data.getMentoringSessionService().restFundersAndCompetencies(mSession);

        MentoringSession ms = loadMentoringSessions(mentoringId);
        ms.setSessionName(simpleFields.get("sessionName"));
        ms.setMentoringNotes(simpleFields.get("mentoringNotes"));


        Long themeId = Long.parseLong(simpleFields.get("mentoringTheme").toString());
        MentoringTheme theme = data.getMentoringThemeService().find(themeId);
        ms.setMentoringTheme(theme);

        Long instId = Long.parseLong(simpleFields.get("institutionName").toString());
        TrainingInstitution ti = data.getTrainingInstitutionService().find(instId);
        ms.setInstitutionName(ti);

        Status status = data.getStatusService().getByPropertyName("status", simpleFields.get("sessionStatus"));
        ms.setSessionStatus(status);

        Long sessionId = Long.parseLong(simpleFields.get("sessionType").toString());
        MentoringSessionType menttype = data.getMentoringSessionTypeService().find(sessionId);
        ms.setMentoringSessionType(menttype);

        for (String funderName : trainingFunders) {
            TrainingFunder f = data.getTrainingFunderService().getByPropertyName("trainingFunderName", funderName);
            MentoringFunders cf = new MentoringFunders();
            cf.setFundersName(f.getTrainingFunderName());
            cf.setFundersId(f.getId());
            ms.getMentoringFunders().add(cf);
        }


        for (String competencyList : competencies) {
            CompetencyList l = data.getCompetencyList().getByPropertyName("compName", competencyList);
            MentoringCompetencies cc = new MentoringCompetencies();
            cc.setCompetencyId(l.getId());
            cc.setCompetencyName(l.getComp_name());
            ms.getMentoringCompetencies().add(cc);
        }

        for (String mentor : mentors) {
            Long mentorId = Long.parseLong(mentor);
            Mentors m = data.getMentorsService().find(mentorId);
            MentoringMentors mm = new MentoringMentors();
            mm.setMentorsId(m.getId());
            ms.getMentoringMentors().add(mm);

        }


        return ms;
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
}
