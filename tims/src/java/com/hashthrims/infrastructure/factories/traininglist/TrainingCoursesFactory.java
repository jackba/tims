/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.infrastructure.factories.traininglist;

import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.domain.EmployeeCourses;
import com.hashthrims.domain.positions.Status;
import com.hashthrims.domain.employeelist.CompetencyList;
import com.hashthrims.domain.regionlist.Country;
import com.hashthrims.domain.regionlist.Province;
import com.hashthrims.domain.traininglist.CourseCompetencies;
import com.hashthrims.domain.traininglist.CourseCriteria;
import com.hashthrims.domain.traininglist.CourseFunders;
import com.hashthrims.domain.traininglist.CourseTargetGroup;
import com.hashthrims.domain.traininglist.CourseTypeName;
import com.hashthrims.domain.traininglist.Criteria;
import com.hashthrims.domain.traininglist.MentoringField;
import com.hashthrims.domain.traininglist.MentoringSessionType;
import com.hashthrims.domain.traininglist.MentoringTheme;
import com.hashthrims.domain.traininglist.Mentors;
import com.hashthrims.domain.traininglist.ScheduledCourses;
import com.hashthrims.domain.traininglist.TargetGroup;
import com.hashthrims.domain.traininglist.TrainingCourseCategory;
import com.hashthrims.domain.traininglist.TrainingCourseRequestors;
import com.hashthrims.domain.traininglist.TrainingCourseStatus;
import com.hashthrims.domain.traininglist.TrainingCourses;
import com.hashthrims.domain.traininglist.TrainingFunder;
import com.hashthrims.domain.traininglist.TrainingInstitution;
import com.hashthrims.domain.traininglist.TrainingInstructors;
import com.hashthrims.infrastructure.conf.GetContext;
import com.hashthrims.services.CompetencyService;
import com.hashthrims.services.CountryService;
import com.hashthrims.services.CourseTypeService;
import com.hashthrims.services.ScheduledCoursesService;
import com.hashthrims.services.TrainingCourseCategoryService;
import com.hashthrims.services.TrainingCourseRequestorsService;
import com.hashthrims.services.TrainingCourseStatusService;
import com.hashthrims.services.TrainingCoursesService;
import com.hashthrims.services.TrainingFunderService;
import com.hashthrims.services.TrainingInstitutionService;
import com.hashthrims.services.TrainingInstructorsService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author stud
 */
public class TrainingCoursesFactory {

    private ApplicationContext ctx = GetContext.getApplicationContext();
    private TrainingCoursesService trainingCoursesService;
    private ScheduledCoursesService scheduledCoursesService;
    private TrainingCourseRequestorsService trainingCourseRequestorsService;
    private TrainingCourseStatusService trainingCourseStatusService;
    private CompetencyService competencyService;
    private TrainingFunderService trainingFunderService;
    private TrainingInstitutionService institutionService;
    private TrainingCourseCategoryService courseCategiryService;
    private TrainingInstructorsService instructorsService;
    private CountryService countryService;
    private ScheduledCoursesService scheduleService;
    private CourseTypeService courseTypeNameService;
    private ClientDataService data = new ClientDataService();

    public ScheduledCourses createScheduledCourses(String classNotes, String classSite, List<TrainingInstructors> instructor, Country classLoc, String district, int numStudents, Date start, Date end) {
        ScheduledCourses tc = new ScheduledCourses();
        tc.setClassInstructor(instructor);
        tc.setClassLocation(classLoc);
        tc.setClassNotes(classNotes);
        tc.setClassSite(classSite);
        tc.setDistrict(district);
        tc.setEndDate(end);
        tc.setStartDate(start);
        tc.setNumOfStuds(numStudents);
        return tc;
    }

    public TrainingInstructors createTrainingInstructors(String countryName, String code, List<Province> province, int numericCode, boolean loc, boolean primaryCountry) {
        TrainingInstructors tc = new TrainingInstructors();
        tc.setInstructorName(countryName);
        return tc;
    }

    public Country createCountry(String countryName, String code, List<Province> province, int numericCode, boolean loc, boolean primaryCountry) {
        Country tc = new Country();
        tc.setAlphaCode(code);
        tc.setCountryName(countryName);
        tc.setLocation(loc);
        tc.setNumericCode(numericCode);
        tc.setPrimaryCountry(primaryCountry);
        tc.setProvince(province);
        return tc;
    }

  

    public TrainingCourseCategory createTrainingCourseCategory(String catName) {
        TrainingCourseCategory tc = new TrainingCourseCategory();
        tc.setCategoryName(catName);

        return tc;
    }

    public TrainingCourseStatus createTrainingCourseStatus(String status) {
        TrainingCourseStatus s = new TrainingCourseStatus();
        s.setStatusName(status);
        return s;
    }

    public TrainingCourseStatus loadTrainingCourseStatus(Long id) {
        trainingCourseStatusService = (TrainingCourseStatusService) ctx.getBean("trainingCourseStatusService");
        TrainingCourseStatus s = trainingCourseStatusService.find(id);
        return s;
    }

    public TrainingCourseCategory loadTrainingCourseCategory(Long id) {
        courseCategiryService = (TrainingCourseCategoryService) ctx.getBean("courseCategiryService");
        TrainingCourseCategory s = courseCategiryService.find(id);
        return s;
    }

    public TrainingInstitution loadTrainingInstitution(Long id) {
        institutionService = (TrainingInstitutionService) ctx.getBean("institutionService");
        TrainingInstitution s = institutionService.find(id);
        return s;
    }

    public TrainingFunder loadTrainingFunder(Long id) {
        trainingFunderService = (TrainingFunderService) ctx.getBean("trainingFunderService");
        TrainingFunder s = trainingFunderService.find(id);
        return s;
    }

    public EmployeeCourses loadCompetency(Long id) {
        competencyService = (CompetencyService) ctx.getBean("competencyService");
        EmployeeCourses s = competencyService.find(id);
        return s;
    }

    public Country loadCountry(Long id) {
        countryService = (CountryService) ctx.getBean("countryService");
        Country s = countryService.find(id);
        return s;
    }

    public TrainingInstructors loadTrainingInstructors(Long id) {
        instructorsService = (TrainingInstructorsService) ctx.getBean("instructorsService");
        TrainingInstructors s = instructorsService.find(id);
        return s;
    }

    public TrainingCourses loadTrainingCourses(Long id) {
        trainingCoursesService = (TrainingCoursesService) ctx.getBean("trainingCoursesService");
        TrainingCourses tc = trainingCoursesService.find(id);
        return tc;
    }

    public ScheduledCourses createScheduledCourses(int numOfStuds, Date startDate, Date endDate, String classNotes, Country classLocation, String classSite, String district, String instructorName, String country) {

        countryService = (CountryService) ctx.getBean("countryService");
        Country countr = countryService.getByPropertyName("countryName", country);
        instructorsService = (TrainingInstructorsService) ctx.getBean("instructorsService");
        TrainingInstructors cdr = instructorsService.getByPropertyName("instructorName", instructorName);

        ScheduledCourses sc = new ScheduledCourses();
        sc.setNumOfStuds(numOfStuds);
        sc.setClassNotes(classNotes);
        sc.setClassSite(classSite);
        sc.setStartDate(startDate);
        sc.setEndDate(endDate);
        sc.setClassLocation(classLocation);

        return sc;
    }

    public ScheduledCourses loadScheduledCourses(Long id) {
        scheduledCoursesService = (ScheduledCoursesService) ctx.getBean("scheduledCoursesService");
        ScheduledCourses sc = scheduledCoursesService.find(id);
        return sc;
    }

    public TrainingCourseRequestors createTrainingCourseRequestors(String requestorName) {
        TrainingCourseRequestors tcr = new TrainingCourseRequestors();
        tcr.setRequestorName(requestorName);
        return tcr;
    }

    public TrainingCourseRequestors loadTrainingCourseRequestors(Long id) {
        trainingCourseRequestorsService = (TrainingCourseRequestorsService) ctx.getBean("trainingCourseRequestorsService");
        TrainingCourseRequestors tcc = trainingCourseRequestorsService.find(id);
        return tcc;
    }

    public TrainingCourseRequestors updatedTrainingCourseRequestors(String requestorName, Long id) {
        TrainingCourseRequestors c = loadTrainingCourseRequestors(id);
        c.setRequestorName(requestorName);
        c.setId(id);
        return c;
    }

    public TrainingCourseStatus updatedTrainingCourseStatus(String courseStatus, Long id) {
        TrainingCourseStatus c = loadTrainingCourseStatus(id);
        c.setStatusName(courseStatus);
        c.setId(id);
        return c;
    }

    public TrainingCourses updateTrainingCourses(String units, String competency, String cosName, String cosNotes, String category, String topic, String institutionName, String funder, Long cosId) {
        TrainingCourses cos = loadTrainingCourses(cosId);
        courseCategiryService = (TrainingCourseCategoryService) ctx.getBean("courseCategiryService");
        TrainingCourseCategory salary = courseCategiryService.getByPropertyName("categoryName", category);
        institutionService = (TrainingInstitutionService) ctx.getBean("institutionService");
        TrainingInstitution cdr = institutionService.getByPropertyName("institutionName", institutionName);
        trainingFunderService = (TrainingFunderService) ctx.getBean("trainingFunderService");
        TrainingFunder f = trainingFunderService.getByPropertyName("funder", funder);
        competencyService = (CompetencyService) ctx.getBean("competencyService");
        EmployeeCourses comp = competencyService.getByPropertyName("competencyName", competency);

        cos.setCourseCategory(salary);
        cos.setCourseName(cosName);
        cos.setCourseNotes(cosNotes);
        cos.setCourseTopic(topic);


        return cos;
    }

    public ScheduledCourses updateScheduledCourses(String instructorName, String country, String classNotes, String classSite, String district, Date start, Date end, int num, Long cosId) {
        ScheduledCourses cos = loadScheduledCourses(cosId);
        countryService = (CountryService) ctx.getBean("countryService");
        Country countr = countryService.getByPropertyName("countryName", country);
        instructorsService = (TrainingInstructorsService) ctx.getBean("instructorsService");
        TrainingInstructors cdr = instructorsService.getByPropertyName("instructorName", instructorName);
        cos.setClassNotes(classNotes);
        cos.setClassSite(classSite);
        cos.setDistrict(district);
        cos.setEndDate(end);
        cos.setNumOfStuds(num);
        cos.setStartDate(start);



        return cos;
    }

    public CourseTypeName createCourseTypeName(String courseTypeName) {
        CourseTypeName ct = new CourseTypeName();
        ct.setCourseType(courseTypeName);
        return ct;
    }

    public CourseTypeName updateCourseTypeName(String courseTypeName, Long courseTypeId) {
        CourseTypeName ct = loadCourseTypeName(courseTypeId);
        ct.setCourseType(courseTypeName);
        return ct;
    }

    public CourseTypeName loadCourseTypeName(Long courseTypeId) {
        courseTypeNameService = (CourseTypeService) ctx.getBean("courseTypeNameService");
        CourseTypeName s = courseTypeNameService.find(courseTypeId);
        return s;
    }

    public TrainingCourses createTrainingCourses(Map<String, String> simpleFields, List<String> competencies, List<String> trainingFunders,List<String> targetGroups) {
        TrainingCourses course = new TrainingCourses();
        List<CourseFunders> funders = new ArrayList<CourseFunders>();
        ArrayList<CourseTargetGroup> targets = new ArrayList<CourseTargetGroup>();
        List<CourseCompetencies> comps = new ArrayList<CourseCompetencies>();

        course.setCourseName(simpleFields.get("courseName"));
        CourseCriteria criteria = new CourseCriteria();
        criteria.setCriteria(simpleFields.get("courseCriteria"));
        course.setCourseCriteria(criteria);

        CourseTypeName cty = data.getCourseTypeNameService().getByPropertyName("courseType", simpleFields.get("courseType"));
        course.setCourseType(cty);


        TrainingInstitution ti = data.getTrainingInstitutionService().getByPropertyName("trainingInstitution", simpleFields.get("trainingInstitution"));
        course.setInstitutionName(ti);

        Status status = data.getStatusService().getByPropertyName("status", simpleFields.get("courseStatus"));
        course.setCourseStatus(status);

        TrainingCourseCategory cat = data.getTrainingCourseCategoryService().getByPropertyName("categoryName", simpleFields.get("courseCatergory"));
        course.setCourseCategory(cat);

        for (String funderName : trainingFunders) {
            TrainingFunder f = data.getTrainingFunderService().getByPropertyName("trainingFunderName", funderName);
            CourseFunders cf = new CourseFunders();
            cf.setFundersId(f.getId());
            cf.setFundersName(funderName);
            funders.add(cf);
        }


        for (String competencyList : competencies) {
            CompetencyList l = data.getCompetencyList().getByPropertyName("compName", competencyList);
            CourseCompetencies cc = new CourseCompetencies();
            cc.setCompetencyId(l.getId());
            cc.setCompetencyName(l.getComp_name());
            comps.add(cc);

        }
        
        for (String targetGroup : targetGroups) {
            TargetGroup f = data.getTargetGroupService().getByPropertyName("targetGroupName", targetGroup);
            CourseTargetGroup cf = new CourseTargetGroup();
            cf.setTargerGroupId(f.getId());
            cf.setTargetGroup(f.getTargetGroupName());
            targets.add(cf);
        }

        course.setCourseFunders(funders);
        course.setCourseCompetencies(comps);
        course.setCourseTargetGroup(targets);




        return course;
    }

    public TrainingCourses updateTrainingCourses(Map<String, String> simpleFields, List<String> competencies, List<String> trainingFunders,List<String> targetGroup, Long courseId) {
        TrainingCourses cs = loadTrainingCourses(courseId);
        //Reset The current Data
        data.getTrainingCoursesService().resetFundsAndCompetencies(cs);
        TrainingCourses course = loadTrainingCourses(courseId);

        course.setCourseName(simpleFields.get("courseName"));
        CourseCriteria criteria = new CourseCriteria();
        criteria.setCriteria(simpleFields.get("courseCriteria"));
        course.setCourseCriteria(criteria);

        CourseTypeName cty = data.getCourseTypeNameService().getByPropertyName("courseType", simpleFields.get("courseType"));
        course.setCourseType(cty);


        TrainingInstitution ti = data.getTrainingInstitutionService().getByPropertyName("trainingInstitution", simpleFields.get("trainingInstitution"));
        course.setInstitutionName(ti);

        Status status = data.getStatusService().getByPropertyName("status", simpleFields.get("courseStatus"));
        course.setCourseStatus(status);

        TrainingCourseCategory cat = data.getTrainingCourseCategoryService().getByPropertyName("categoryName", simpleFields.get("courseCatergory"));
        course.setCourseCategory(cat);

        for (String funderName : trainingFunders) {
            TrainingFunder f = data.getTrainingFunderService().getByPropertyName("trainingFunderName", funderName);
            CourseFunders cf = new CourseFunders();
            cf.setFundersId(f.getId());
            cf.setFundersName(funderName);
            course.getCourseFunders().add(cf);
        }
        for (String competencyList : competencies) {
            CompetencyList l = data.getCompetencyList().getByPropertyName("compName", competencyList);
            CourseCompetencies cc = new CourseCompetencies();
            cc.setCompetencyId(l.getId());
            cc.setCompetencyName(l.getComp_name());
            course.getCourseCompetencies().add(cc);
        }
        return course;
    }

    public Mentors createMentor(String firstName, String lastName, String title, String qualification) {
       Mentors m = new Mentors();
       m.setFirstName(firstName);
       m.setLastName(lastName);
       m.setQualification(qualification);
       m.setTitle(title);
       return m;
    }

    public Mentors updateMentor(String firstName, String lastName, String title, String qualification, Long mentorID) {
        Mentors m = loadMentor(mentorID);
        m.setFirstName(firstName);
        m.setLastName(lastName);
        m.setQualification(qualification);
        m.setTitle(title);
        return m;
    }

    public Mentors loadMentor(Long mentorId) {
        Mentors m = data.getMentorsService().find(mentorId);
        return m;
    }

    public MentoringTheme loadMentoringTheme(Long mentoringId) {

       MentoringTheme m = data.getMentoringThemeService().find(mentoringId);
       return m;
    }

    public MentoringTheme updateMentoringTheme(String mentoringTheme, String mentoringField, Long MentoringThemeId) {
        MentoringTheme m = loadMentoringTheme(MentoringThemeId);
        MentoringField mf = data.getMentoringFieldService().getByPropertyName("fieldName", mentoringField);
        m.setMentoringField(mf);
        m.setMentoringTheme(mentoringTheme);

        return m;
    }

    public MentoringTheme createMentoringTheme(String mentoringTheme, String mentoringField) {
        MentoringTheme m = new MentoringTheme();
        MentoringField mf = data.getMentoringFieldService().getByPropertyName("fieldName", mentoringField);
        m.setMentoringField(mf);
        m.setMentoringTheme(mentoringTheme);

        return m;
    }

    public MentoringSessionType createMentoringSessionType(String mentoringSessionType) {
        MentoringSessionType mt = new MentoringSessionType();
        mt.setSessionTypeName(mentoringSessionType);
        return mt;
    }

    public MentoringSessionType updateMentoringSessionType(String mentoringSessionType, Long sessionId) {
        MentoringSessionType mt = loadMentoringSessionType(sessionId);
        mt.setSessionTypeName(mentoringSessionType);
        return mt;
    }

    public MentoringSessionType loadMentoringSessionType(Long mentoringId) {
        MentoringSessionType mt = data.getMentoringSessionTypeService().find(mentoringId);
        return mt;
    }

    public Criteria createCriteria(String criteria) {
        Criteria c = new Criteria();
        c.setSelectionCriteria(criteria);
        return c;
    }

    public Criteria updatedCriteria(String criteria, Long criteriaId) {
        Criteria c = loadCriteria(criteriaId);
        c.setSelectionCriteria(criteria);
        return c;
    }

    public Criteria loadCriteria(Long criteriaId) {
        Criteria c = data.getCriteriaService().find(criteriaId);
        return c;
    }

    public TargetGroup createTargetGroup(String targetGroup) {
        TargetGroup tg = new TargetGroup();
        tg.setTargetGroupName(targetGroup);
        return tg;
    }

 
    public TargetGroup updatedTargetGroup(String targetGroup, Long targetGroupId) {
        TargetGroup tg = loadTargetGroup(targetGroupId);
        tg.setTargetGroupName(targetGroup);
        return tg;
    }

    public TargetGroup loadTargetGroup(Long targetGroupId) {
        TargetGroup tg = data.getTargetGroupService().find(targetGroupId);
        return tg;
    }
}
