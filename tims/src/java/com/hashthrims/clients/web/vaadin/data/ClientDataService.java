/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.data;

import com.hashthrims.infrastructure.conf.GetContext;
import com.hashthrims.infrastructure.factories.ApplicationsFactory;
import com.hashthrims.infrastructure.factories.EmployeeFactory;
import com.hashthrims.infrastructure.factories.LanguagesFactory;
import com.hashthrims.infrastructure.factories.LocationFactory;
import com.hashthrims.infrastructure.factories.PersonFactory;
import com.hashthrims.infrastructure.factories.RolesFactory;
import com.hashthrims.infrastructure.factories.StatusFactory;
import com.hashthrims.infrastructure.factories.SubjectFactory;
import com.hashthrims.infrastructure.factories.TrainingFactory;
import com.hashthrims.infrastructure.factories.UsersFactory;
import com.hashthrims.infrastructure.factories.employeelist.AccidentTypeListFactory;
import com.hashthrims.infrastructure.factories.employeelist.BenefitFrequencyFactory;
import com.hashthrims.infrastructure.factories.employeelist.BenefitTypeFactory;
import com.hashthrims.infrastructure.factories.employeelist.CompetencyEvaluationFactory;
import com.hashthrims.infrastructure.factories.employeelist.CompetencyListFactory;
import com.hashthrims.infrastructure.factories.employeelist.CompetencyTypeFactory;
import com.hashthrims.infrastructure.factories.employeelist.DegreeFactory;
import com.hashthrims.infrastructure.factories.employeelist.DepartureReasonsFactory;
import com.hashthrims.infrastructure.factories.employeelist.DisciplineActionListFactory;
import com.hashthrims.infrastructure.factories.employeelist.EducationTypeFactory;
import com.hashthrims.infrastructure.factories.employeelist.EducationsFactory;
import com.hashthrims.infrastructure.factories.employeelist.IdentificationTypeFactory;
import com.hashthrims.infrastructure.factories.employeelist.LanguageFactory;
import com.hashthrims.infrastructure.factories.employeelist.LanguageProficiencyFactory;
import com.hashthrims.infrastructure.factories.employeelist.MaritulStatusListFactory;
import com.hashthrims.infrastructure.factories.jobs.CadresFactory;
import com.hashthrims.infrastructure.factories.jobs.ClassificationFactory;
import com.hashthrims.infrastructure.factories.jobs.JobsFactory;
import com.hashthrims.infrastructure.factories.offices.OrganisationListFactory;
import com.hashthrims.infrastructure.factories.positions.PositionsFactory;
import com.hashthrims.infrastructure.factories.traininglist.TrainingCoursesFactory;
import com.hashthrims.services.AccidentTypeListService;
import com.hashthrims.services.AddressTypeService;
import com.hashthrims.services.ApplicationsService;
import com.hashthrims.services.BenefitFrequencyService;
import com.hashthrims.services.BenefitTypeService;
import com.hashthrims.services.CadresService;
import com.hashthrims.services.CityService;
import com.hashthrims.services.ClassificationsService;
import com.hashthrims.services.ClustersService;
import com.hashthrims.services.CompetencyEvaluationService;
import com.hashthrims.services.CompetencyListService;
import com.hashthrims.services.CompetencyService;
import com.hashthrims.services.CompetencyTypeService;
import com.hashthrims.services.ContactsService;
import com.hashthrims.services.ContiuingEducationCourseService;
import com.hashthrims.services.CountryService;
import com.hashthrims.services.CountyService;
import com.hashthrims.services.CourseCriteriaService;
import com.hashthrims.services.CourseTargetGroupService;
import com.hashthrims.services.CourseTypeService;
import com.hashthrims.services.CriteriaService;
import com.hashthrims.services.CurrencyService;
import com.hashthrims.services.DegreeService;
import com.hashthrims.services.DepartmentService;
import com.hashthrims.services.DepartureReasonsService;
import com.hashthrims.services.DisciplinaryActionService;
import com.hashthrims.services.DisciplineActionTypeListService;
import com.hashthrims.services.DistrictService;
import com.hashthrims.services.EducationHistoryService;
import com.hashthrims.services.EducationService;
import com.hashthrims.services.EducationTypeService;
import com.hashthrims.services.EmployeeBenefitsService;
import com.hashthrims.services.EmployeePositionService;
import com.hashthrims.services.EmployeeService;
import com.hashthrims.services.EmploymentHistoryService;
import com.hashthrims.services.FacilityService;
import com.hashthrims.services.FacilityTypeService;
import com.hashthrims.services.GenderListService;
import com.hashthrims.services.IdentificationTypeService;
import com.hashthrims.services.IdentitiesService;
import com.hashthrims.services.JobsService;
import com.hashthrims.services.LangauageProficiencyService;
import com.hashthrims.services.LanguageService;
import com.hashthrims.services.EmployeeLanguagesService;
import com.hashthrims.services.EmployeeMentoringService;
import com.hashthrims.services.GlobalPositionsService;
import com.hashthrims.services.MaritalStatusListService;
import com.hashthrims.services.MentorExpertiseAreaService;
import com.hashthrims.services.MentoringAreasListService;
import com.hashthrims.services.MentoringCompetenciesService;
import com.hashthrims.services.MentoringFieldService;
import com.hashthrims.services.MentoringFundersService;
import com.hashthrims.services.MentoringObjectiveService;
import com.hashthrims.services.MentoringSessionService;
import com.hashthrims.services.MentoringSessionTypeService;
import com.hashthrims.services.MentoringThemeService;
import com.hashthrims.services.MentorsRolesService;
import com.hashthrims.services.MentorsService;
import com.hashthrims.services.NodesService;
import com.hashthrims.services.OrganisationTrainersService;
import com.hashthrims.services.PersonService;
import com.hashthrims.services.PositionSalarySourcesService;
import com.hashthrims.services.PositionTypesService;
import com.hashthrims.services.PositionsService;
import com.hashthrims.services.ProfessionalRegistrationService;
import com.hashthrims.services.RaceListService;
import com.hashthrims.services.RegionService;
import com.hashthrims.services.RegistrationBodyService;
import com.hashthrims.services.RolesService;
import com.hashthrims.services.SalaryGradesService;
import com.hashthrims.services.SalarySourcesService;
import com.hashthrims.services.ScheduledCoursesService;
import com.hashthrims.services.StatusService;
import com.hashthrims.services.SubjectService;
import com.hashthrims.services.TargetGroupService;
import com.hashthrims.services.TrainingCourseCategoryService;
import com.hashthrims.services.TrainingCourseEvaluationService;
import com.hashthrims.services.TrainingCourseRequestorsService;
import com.hashthrims.services.TrainingCourseStatusService;
import com.hashthrims.services.TrainingCoursesService;
import com.hashthrims.services.TrainingFunderService;
import com.hashthrims.services.TrainingInstitutionService;
import com.hashthrims.services.TrainingInstructorsService;
import com.hashthrims.services.TrainingService;
import com.hashthrims.services.UsersService;
import com.hashthrims.services.WorkPlaceAccidentsService;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author boniface
 */
public class ClientDataService {

    private LocationFactory locationFactory;
    private StatusFactory statusFactory;
    private CountryService countryService;
    private RegionService regionService;
    private CountyService countyService;
    private DistrictService districtService;
    private CityService cityService;
    private CurrencyService currencyService;
    private AddressTypeService addressTypeService;
    private ApplicationContext ctx;
    private OrganisationListFactory organisationListFactory;
    private DepartmentService departmentService;
    private FacilityService facilityService;
    private RegistrationBodyService registrationBodyService;
    private FacilityTypeService facilityTypeService;
    private ContactsService contactsService;
    private EmployeeFactory employefactory;
    private IdentificationTypeService identificationTypeService;
    private LanguageService languageService;
    private MaritalStatusListService maritalStatusService;
    private BenefitTypeService benefitTypeService;
    private DepartureReasonsService departureReasonsService;
    private EmployeeService employeeService;
    private EmployeePositionService employeePositionService;
    private PersonService personService;
    private IdentitiesService identitiesService;
    private EmployeeBenefitsService employeeBenefitsService;
    private EmployeeLanguagesService languagesService;
    private CompetencyService competencyService;
    private ProfessionalRegistrationService professionalRegistrationService;
    private TrainingService trainingService;
    private DisciplinaryActionService disciplinaryActionService;
    private WorkPlaceAccidentsService workPlaceAccidentsService;
    private ApplicationsService applicationsService;
    private EducationHistoryService educationHistoryService;
    private EmploymentHistoryService employmentHistoryService;
    private DisciplineActionTypeListService discipleActionList;
    private StatusService statusService;
    private JobsFactory jobsFactory;
    private JobsService jobService;
    private CadresService cadresService;
    private ClassificationsService classifications;
    private SalaryGradesService salaryGradesService;
    private PositionsFactory positionFactory;
    private PositionTypesService positionType;
    private PositionsService positionsService;
    private GlobalPositionsService globalPositionsService;
    private SalarySourcesService salarySourcesService;
    private PositionSalarySourcesService positionSalarySourcesService;
    private AccidentTypeListFactory accidentTypeListFactory;
    private AccidentTypeListService accidentType;
    private ApplicationsFactory applicationsFactory;
    private BenefitFrequencyFactory benefitFrequencyFactory;
    private BenefitFrequencyService benefitFrequency;
    private BenefitTypeFactory benefitTypeFactory;
    private CadresFactory cadresFactory;
    private ClassificationFactory classificationFactory;
    private CompetencyEvaluationFactory competencyEvaluationFactory;
    private CompetencyEvaluationService competencyEvaluationService;
    private CompetencyListFactory competencyListFactory;
    private CompetencyListService competencyListService;
    private CompetencyTypeService competencyTypeService;
    private CompetencyTypeFactory competencyTypeFactory;
    private DegreeFactory degreeFactory;
    private DegreeService degreeService;
    private EducationTypeService educationTypeService;
    private DepartureReasonsFactory departureReasonsFactory;
    private DisciplineActionListFactory disciplineActionListFactory;
    private DisciplineActionTypeListService disciplineActionTypeListService;
    private EducationsFactory educationFactory;
    private EducationService educationService;
    private IdentificationTypeFactory identificationTypeFactory;
    private LanguageFactory languageFactory;
    private LanguageProficiencyFactory languageProficiencyFactory;
    private LangauageProficiencyService langauageProficiencyService;
    private LanguagesFactory languagesFactory;
    private MaritulStatusListFactory maritulStatusListFactory;
    private PersonFactory personFactory;
    private RolesFactory rolesFactory;
    private RolesService rolesService;
    private SubjectFactory subjectFactory;
    private SubjectService subjectService;
    private TrainingCoursesFactory trainingCoursesFactory;
    private TrainingCoursesService trainingCoursesService;
    private TrainingCourseStatusService trainingCourseStatusService;
    private TrainingCourseRequestorsService trainingCourseRequestorsService;
    private ScheduledCoursesService scheduledCoursesService;
    private CourseTypeService courseTypeNameService;
    private ContiuingEducationCourseService continuingEducationService;
    private TrainingCourseCategoryService trainingCourseCategoryService;
    private TrainingCourseEvaluationService trainingCourseEvaluationService;
    private TrainingFunderService trainingFunderService;
    private TrainingInstitutionService trainingInstitutionService;
    private TrainingInstructorsService trainingInstructorsService;
    private TrainingFactory trainingFactory;
    private UsersFactory usersFactory;
    private UsersService usersService;
    private EducationTypeFactory educationTypeFactory;
    private GenderListService genderListService;
    private MentoringCompetenciesService mentoringCompetenciesService;
    private MentoringFundersService mentoringFundersService;
    private MentoringSessionService mentoringSessionService;
    private MentorsService mentorsService;
    private MentoringFieldService mentoringFieldService;
    private MentoringThemeService mentoringThemeService;
    private MentoringSessionTypeService mentoringSessionTypeService;
    private EmployeeMentoringService employeeMentoringService;
    private NodesService nodesService;
    private ClustersService clustersService;
    private MentorsRolesService mentorsRolesService;
    private MentorExpertiseAreaService mentorExpertiseAreaService;
    private CourseCriteriaService courseCriteriaService;
    private CourseTargetGroupService courseTargetGroupService;
    private CriteriaService criteriaService;
    private OrganisationTrainersService organisationTrainersService;
    private TargetGroupService targetGroupService;
    private RaceListService raceListService;
    private MentoringAreasListService mentoringAreasListService;
    private MentoringObjectiveService mentoringObjectiveService;
   

    public RaceListService getRaceListService() {
        ctx = GetContext.getApplicationContext();
        raceListService = (RaceListService) ctx.getBean("raceListService");
        return raceListService;
    }
    
     public MentoringObjectiveService getMentoringObjectiveService() {
        ctx = GetContext.getApplicationContext();
        mentoringObjectiveService = (MentoringObjectiveService) ctx.getBean("mentoringObjectiveService");
        return mentoringObjectiveService;
    }

    public MentoringSessionTypeService getMentoringSessionTypeService() {
        ctx = GetContext.getApplicationContext();
        mentoringSessionTypeService = (MentoringSessionTypeService) ctx.getBean("mentoringSessionTypeService");
        return mentoringSessionTypeService;
    }

    public MentoringAreasListService getMentoringAreasListService() {
        ctx = GetContext.getApplicationContext();
        mentoringAreasListService = (MentoringAreasListService) ctx.getBean("mentoringAreasListService");
        return mentoringAreasListService;
    }

    public EmployeeMentoringService getEmployeeMentoringService() {
        ctx = GetContext.getApplicationContext();
        employeeMentoringService = (EmployeeMentoringService) ctx.getBean("employeeMentoringService");
        return employeeMentoringService;
    }

    public MentoringCompetenciesService getMentoringCompetenciesService() {
        ctx = GetContext.getApplicationContext();
        mentoringCompetenciesService = (MentoringCompetenciesService) ctx.getBean("mentoringCompetenciesService");
        return mentoringCompetenciesService;
    }

    public MentoringFundersService getMentoringFundersService() {
        ctx = GetContext.getApplicationContext();
        mentoringFundersService = (MentoringFundersService) ctx.getBean("mentoringFundersService");
        return mentoringFundersService;
    }

    public MentorsService getMentorsService() {
        ctx = GetContext.getApplicationContext();
        mentorsService = (MentorsService) ctx.getBean("mentorsService");
        return mentorsService;
    }

    public MentoringSessionService getMentoringSessionService() {
        ctx = GetContext.getApplicationContext();
        mentoringSessionService = (MentoringSessionService) ctx.getBean("mentoringSessionService");
        return mentoringSessionService;
    }

    public MentoringThemeService getMentoringThemeService() {
        ctx = GetContext.getApplicationContext();
        mentoringThemeService = (MentoringThemeService) ctx.getBean("mentoringThemeService");
        return mentoringThemeService;
    }

    public MentoringFieldService getMentoringFieldService() {
        ctx = GetContext.getApplicationContext();
        mentoringFieldService = (MentoringFieldService) ctx.getBean("mentoringFieldService");
        return mentoringFieldService;
    }

    /**
     * @return the education User Factory
     */
    public EducationTypeFactory getEducationTypeFactory() {
        educationTypeFactory = new EducationTypeFactory();
        return educationTypeFactory;
    }

    /**
     * @return the factory
     */
    public UsersFactory getUsersFactory() {
        usersFactory = new UsersFactory();
        return usersFactory;
    }

    public UsersService getUsersService() {
        ctx = GetContext.getApplicationContext();
        usersService = (UsersService) ctx.getBean("usersService");
        return usersService;
    }

    public AddressTypeService getAddressTypeService() {
        ctx = GetContext.getApplicationContext();
        addressTypeService = (AddressTypeService) ctx.getBean("addressTypeService");
        return addressTypeService;
    }

    public TrainingFactory getTrainingFactory() {
        trainingFactory = new TrainingFactory();
        return trainingFactory;
    }

    /**
     * @return the continuingEducationCourseService
     */
    public ContiuingEducationCourseService getContinuingEducationCourseService() {
        ctx = GetContext.getApplicationContext();
        continuingEducationService = (ContiuingEducationCourseService) ctx.getBean("contiuingEducationCourseService");
        return continuingEducationService;
    }

    /**
     * @return the trainingCourseCategoryService
     */
    public TrainingCourseCategoryService getTrainingCourseCategoryService() {
        ctx = GetContext.getApplicationContext();
        trainingCourseCategoryService = (TrainingCourseCategoryService) ctx.getBean("trainingCourseCategoryService");
        return trainingCourseCategoryService;
    }

    /**
     * @return the trainingCourseEvaluationService
     */
    public TrainingCourseEvaluationService getTrainingCourseEvaluationService() {
        ctx = GetContext.getApplicationContext();
        trainingCourseEvaluationService = (TrainingCourseEvaluationService) ctx.getBean("trainingCourseEvaluationService");
        return trainingCourseEvaluationService;
    }

    /**
     * @return the trainingFunderService
     */
    public com.hashthrims.services.TrainingFunderService getTrainingFunderService() {
        ctx = GetContext.getApplicationContext();
        trainingFunderService = (TrainingFunderService) ctx.getBean("trainingFunderService");
        return trainingFunderService;
    }

    /**
     * @return the trainingInstitutionService
     */
    public com.hashthrims.services.TrainingInstitutionService getTrainingInstitutionService() {
        ctx = GetContext.getApplicationContext();
        trainingInstitutionService = (TrainingInstitutionService) ctx.getBean("trainingInstitutionService");
        return trainingInstitutionService;
    }

    /**
     * @return the trainingInstructorsService
     */
    public com.hashthrims.services.TrainingInstructorsService getTrainingInstructorsService() {
        ctx = GetContext.getApplicationContext();
        trainingInstructorsService = (TrainingInstructorsService) ctx.getBean("trainingInstructorsService");
        return trainingInstructorsService;
    }

    public ContiuingEducationCourseService getContiuingEducationCourseService() {
        ctx = GetContext.getApplicationContext();
        continuingEducationService = (ContiuingEducationCourseService) ctx.getBean("continuingEducationService");
        return continuingEducationService;
    }

    /**
     * @return the factory
     */
    public TrainingCoursesFactory getTrainingCoursesFactory() {

        trainingCoursesFactory = new TrainingCoursesFactory();
        return trainingCoursesFactory;

    }

    public TrainingCoursesService getTrainingCoursesService() {
        ctx = GetContext.getApplicationContext();
        trainingCoursesService = (TrainingCoursesService) ctx.getBean("trainingCoursesService");
        return trainingCoursesService;
    }

    public TrainingCourseStatusService getTrainingCourseStatusType() {
        ctx = GetContext.getApplicationContext();
        trainingCourseStatusService = (TrainingCourseStatusService) ctx.getBean("trainingCourseStatusService");
        return trainingCourseStatusService;
    }

    public TrainingCourseRequestorsService getTrainingCourseRequestorsType() {
        ctx = GetContext.getApplicationContext();
        trainingCourseRequestorsService = (TrainingCourseRequestorsService) ctx.getBean("trainingCourseRequestorsService");
        return trainingCourseRequestorsService;
    }

    public ScheduledCoursesService getScheduledCoursesType() {
        ctx = GetContext.getApplicationContext();
        scheduledCoursesService = (ScheduledCoursesService) ctx.getBean("scheduledCoursesService");
        return scheduledCoursesService;
    }

    /**
     * @return the courseTypeNameService
     */
    public CourseTypeService getCourseTypeNameService() {
        ctx = GetContext.getApplicationContext();
        courseTypeNameService = (CourseTypeService) ctx.getBean("courseTypeNameService");
        return courseTypeNameService;
    }

    /**
     * @return the factory
     */
    public SubjectFactory getSubjectFactory() {
        subjectFactory = new SubjectFactory();
        return subjectFactory;

    }

    /**
     * @return the educationService
     */
    public SubjectService getSubjectService() {
        ctx = GetContext.getApplicationContext();
        subjectService = (SubjectService) ctx.getBean("subjectService");
        return subjectService;
    }

    /**
     * @return the factory
     */
    public RolesFactory getRolesFactory() {
        rolesFactory = new RolesFactory();
        return rolesFactory;
    }

    public RolesService getRolesService() {
        ctx = GetContext.getApplicationContext();
        rolesService = (RolesService) ctx.getBean("rolesService");
        return rolesService;
    }

    /**
     * @return the factory
     */
    public PersonFactory getPersonFactory() {
        personFactory = new PersonFactory();
        return personFactory;
    }

    /**
     * @return the factory
     */
    public MaritulStatusListFactory getMaritulStatusListFactory() {
        maritulStatusListFactory = new MaritulStatusListFactory();
        return maritulStatusListFactory;

    }

    public LanguagesFactory getLanguagesFactory() {
        languagesFactory = new LanguagesFactory();
        return languagesFactory;
    }

    /**
     * @return the factory
     */
    public LanguageProficiencyFactory getLanguageProficiencyFactory() {

        languageProficiencyFactory = new LanguageProficiencyFactory();
        return languageProficiencyFactory;

    }

    /**
     * @return the educationType
     */
    public LangauageProficiencyService getLanguageProficiency() {
        ctx = GetContext.getApplicationContext();
        langauageProficiencyService = (LangauageProficiencyService) ctx.getBean("langauageProficiencyService");
        return langauageProficiencyService;
    }

    /**
     * @return the factory
     */
    public LanguageFactory getLanguageFactory() {
        languageFactory = new LanguageFactory();
        return languageFactory;
    }

    /**
     * @return the factory
     */
    public IdentificationTypeFactory getIdentificationTypeFactory() {
        identificationTypeFactory = new IdentificationTypeFactory();
        return identificationTypeFactory;
    }

    /**
     * @return the educationService
     */
    public EducationService getEducationService() {
        ctx = GetContext.getApplicationContext();
        educationService = (EducationService) ctx.getBean("educationService");
        return educationService;
    }

    /**
     * @return the educationFactory
     */
    public EducationsFactory getEducationsFactory() {
        educationFactory = new EducationsFactory();
        return educationFactory;
    }

    /**
     * @return the factory
     */
    public DisciplineActionListFactory getDisciplineActionListFactory() {
        disciplineActionListFactory = new DisciplineActionListFactory();
        return disciplineActionListFactory;

    }

    /**
     * @return the educationType
     */
    public DisciplineActionTypeListService getDisciplineActionTypeListService() {
        ctx = GetContext.getApplicationContext();
        disciplineActionTypeListService = (DisciplineActionTypeListService) ctx.getBean("disciplineActionListService");
        return disciplineActionTypeListService;
    }

    /**
     * @return the factory
     */
    public DepartureReasonsFactory getDepartureReasonsFactory() {
        departureReasonsFactory = new DepartureReasonsFactory();
        return departureReasonsFactory;

    }

    /**
     * @return the factory
     */
    public DegreeFactory getDegreeFactory() {

        degreeFactory = new DegreeFactory();
        return degreeFactory;

    }

    /**
     * @return the educationType
     */
    public DegreeService getDegreeName() {
        ctx = GetContext.getApplicationContext();
        degreeService = (DegreeService) ctx.getBean("degreeService");
        return degreeService;
    }

    public EducationTypeService getEducationTypeService() {
        ctx = GetContext.getApplicationContext();
        educationTypeService = (EducationTypeService) ctx.getBean("educationTypeService");
        return educationTypeService;
    }

    public CompetencyTypeFactory getCompetencyTypeFactory() {
        competencyTypeFactory = new CompetencyTypeFactory();
        return competencyTypeFactory;

    }

    /**
     * @return the factory
     */
    public CompetencyListFactory getCompetencyListFactory() {

        competencyListFactory = new CompetencyListFactory();
        return competencyListFactory;

    }

    /**
     * @return the educationType
     */
    public CompetencyListService getCompetencyList() {
        ctx = GetContext.getApplicationContext();
        competencyListService = (CompetencyListService) ctx.getBean("competencyListService");
        return competencyListService;
    }

    public CompetencyTypeService getCompetencyTypeService() {
        ctx = GetContext.getApplicationContext();
        competencyTypeService = (CompetencyTypeService) ctx.getBean("competencyTypeService");
        return competencyTypeService;
    }

    public CompetencyEvaluationService getCompetencyEvaluationService() {
        ctx = GetContext.getApplicationContext();
        competencyEvaluationService = (CompetencyEvaluationService) ctx.getBean("competencyEvaluationService");
        return competencyEvaluationService;
    }

    /**
     * @return the factory
     */
    public CompetencyEvaluationFactory getCompetencyEvaluationFactory() {
        competencyEvaluationFactory = new CompetencyEvaluationFactory();
        return competencyEvaluationFactory;

    }

    /**
     * @return the factory
     */
    public ClassificationFactory getClassificationFactory() {

        classificationFactory = new ClassificationFactory();
        return classificationFactory;

    }

    /**
     * @return the factory
     */
    public CadresFactory getCadresFactory() {

        cadresFactory = new CadresFactory();
        return cadresFactory;
    }

    /**
     * @return the factory
     */
    public BenefitTypeFactory getBenefitTypeFactory() {

        benefitTypeFactory = new BenefitTypeFactory();
        return benefitTypeFactory;

    }

    /**
     * @return the factory
     */
    public BenefitFrequencyFactory getBenefitFrequencyFactory() {

        benefitFrequencyFactory = new BenefitFrequencyFactory();
        return benefitFrequencyFactory;

    }

    /**
     * @return the educationType
     */
    public BenefitFrequencyService getBenefitType() {
        ctx = GetContext.getApplicationContext();
        benefitFrequency = (BenefitFrequencyService) ctx.getBean("benefitFrequencyService");
        return benefitFrequency;
    }

    public ApplicationsFactory getApplicationsFactory() {
        applicationsFactory = new ApplicationsFactory();
        return applicationsFactory;
    }

    public ApplicationsService getApplicationsService() {
        ctx = GetContext.getApplicationContext();
        applicationsService = (ApplicationsService) ctx.getBean("applicationsService");
        return applicationsService;
    }

    /**
     * @return the factory
     */
    public AccidentTypeListFactory getAccidentTypeListFactory() {
        accidentTypeListFactory = new AccidentTypeListFactory();
        return accidentTypeListFactory;

    }

    /**
     * @return the educationType
     */
    public AccidentTypeListService getAccidentType() {
        ctx = GetContext.getApplicationContext();
        accidentType = (AccidentTypeListService) ctx.getBean("accidentTypeListService");
        return accidentType;
    }

    /**
     * @return the educationType
     */
    public PositionTypesService getPositionTypes() {
        ctx = GetContext.getApplicationContext();
        positionType = (PositionTypesService) ctx.getBean("positionTypesService");
        return positionType;
    }

    /**
     * @return the positionFactory
     */
    public PositionsFactory getPositionFactory() {
        positionFactory = new PositionsFactory();
        return positionFactory;
    }

    /**
     * @param positionFactory the positionFactory to set
     */
    public void setPositionFactory(final PositionsFactory positionFactory) {
        this.positionFactory = positionFactory;
    }

    /**
     * @return the positionsService
     */
    public PositionsService getPositionsService() {
        ctx = GetContext.getApplicationContext();
        positionsService = (PositionsService) ctx.getBean("positionsService");
        return positionsService;
    }

    /**
     * @return the positionsService
     */
    public GlobalPositionsService getGlobalPositionsService() {
        ctx = GetContext.getApplicationContext();
        globalPositionsService = (GlobalPositionsService) ctx.getBean("globalPositionsService");
        return globalPositionsService;
    }

    public SalarySourcesService getSalarySourcesService() {
        ctx = GetContext.getApplicationContext();
        salarySourcesService = (SalarySourcesService) ctx.getBean("salarySourcesService");
        return salarySourcesService;
    }

    /**
     * @return the factory
     */
    public JobsFactory getJobsFactory() {

        jobsFactory = new JobsFactory();
        return jobsFactory;

    }

    /**
     * @return the educationType
     */
    public JobsService getJobService() {
        ctx = GetContext.getApplicationContext();
        jobService = (JobsService) ctx.getBean("jobsService");
        return jobService;
    }

    public CadresService getCadresService() {
        ctx = GetContext.getApplicationContext();
        cadresService = (CadresService) ctx.getBean("cadresService");
        return cadresService;
    }

    public ClassificationsService getclassificationsServive() {
        ctx = GetContext.getApplicationContext();
        classifications = (ClassificationsService) ctx.getBean("classificationsService");
        return classifications;
    }

    public SalaryGradesService getsalaryGradesService() {
        ctx = GetContext.getApplicationContext();
        salaryGradesService = (SalaryGradesService) ctx.getBean("salaryGradesService");
        return salaryGradesService;
    }

    /**
     * @return the currencyService
     */
    public CurrencyService getCurrencyService() {
        ctx = GetContext.getApplicationContext();
        currencyService = (CurrencyService) ctx.getBean("currencyService");
        return currencyService;
    }

    //Application  Factories
    public LocationFactory getClientDataFactory() {
        locationFactory = new LocationFactory();
        return locationFactory;
    }

    public EmployeeFactory getEmployeeFactory() {
        employefactory = new EmployeeFactory();
        return employefactory;
    }

    public StatusFactory getStatusFactory() {
        statusFactory = new StatusFactory();
        return statusFactory;
    }

    public OrganisationListFactory getOfficeFactory() {
        organisationListFactory = new OrganisationListFactory();
        return organisationListFactory;
    }

    /**
     * @return the educationType
     */
    public DisciplineActionTypeListService getDisciplineActionType() {
        ctx = GetContext.getApplicationContext();
        discipleActionList = (DisciplineActionTypeListService) ctx.getBean("disciplineActionListService");
        return discipleActionList;
    }

// Application Service Calls
    public CountryService getCountryService() {
        ctx = GetContext.getApplicationContext();
        countryService = (CountryService) ctx.getBean("countryService");
        return countryService;
    }

    public RegionService getRegionService() {
        ctx = GetContext.getApplicationContext();
        regionService = (RegionService) ctx.getBean("regionService");

        return regionService;
    }

    public DistrictService getDistrictService() {
        ctx = GetContext.getApplicationContext();
        districtService = (DistrictService) ctx.getBean("districtService");
        return districtService;
    }

    public CountyService getCountyService() {
        ctx = GetContext.getApplicationContext();
        countyService = (CountyService) ctx.getBean("countyService");
        return countyService;
    }

    public CityService getCityService() {
        ctx = GetContext.getApplicationContext();
        cityService = (CityService) ctx.getBean("cityService");
        return cityService;
    }

    /**
     * @param currencyService the currencyService to set
     */
    public void setCurrencyService(final CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    public DepartmentService getDepartmentService() {
        ctx = GetContext.getApplicationContext();
        departmentService = (DepartmentService) ctx.getBean("departmentService");

        return departmentService;
    }

    public FacilityService getFacilityService() {
        ctx = GetContext.getApplicationContext();
        facilityService = (FacilityService) ctx.getBean("facilityService");
        return facilityService;
    }

    public FacilityTypeService getFacilityTypeService() {
        ctx = GetContext.getApplicationContext();
        facilityTypeService = (FacilityTypeService) ctx.getBean("facilityTypeService");
        return facilityTypeService;
    }

    public ContactsService getContactsService() {
        ctx = GetContext.getApplicationContext();
        contactsService = (ContactsService) ctx.getBean("contactsService");
        return contactsService;
    }

    public RegistrationBodyService getRegistrationBodyService() {
        ctx = GetContext.getApplicationContext();
        registrationBodyService = (RegistrationBodyService) ctx.getBean("registrationBodyService");
        return registrationBodyService;
    }

    public IdentificationTypeService getIdentificationTypeService() {
        ctx = GetContext.getApplicationContext();
        identificationTypeService = (IdentificationTypeService) ctx.getBean("identificationTypeService");
        return identificationTypeService;
    }

    public LanguageService getLanguageService() {
        ctx = GetContext.getApplicationContext();
        languageService = (LanguageService) ctx.getBean("languageService");

        return languageService;
    }

    public MaritalStatusListService getMaritalStatusListService() {
        ctx = GetContext.getApplicationContext();
        maritalStatusService = (MaritalStatusListService) ctx.getBean("maritalStatusListService");
        return maritalStatusService;
    }

    public BenefitTypeService getBenefitTypeService() {
        ctx = GetContext.getApplicationContext();
        benefitTypeService = (BenefitTypeService) ctx.getBean("benefitTypeService");
        return benefitTypeService;
    }

    public GenderListService getGenderListService() {
        ctx = GetContext.getApplicationContext();
        genderListService = (GenderListService) ctx.getBean("genderListService");
        return genderListService;
    }

    public DepartureReasonsService getDepartureReasonsService() {
        ctx = GetContext.getApplicationContext();
        departureReasonsService = (DepartureReasonsService) ctx.getBean("departureReasonsService");
        return departureReasonsService;
    }

    public EmployeePositionService getEmployeePositionService() {
        ctx = GetContext.getApplicationContext();
        employeePositionService = (EmployeePositionService) ctx.getBean("employeePositionService");
        return employeePositionService;
    }

    public PersonService getPersonService() {
        ctx = GetContext.getApplicationContext();
        personService = (PersonService) ctx.getBean("personService");

        return personService;
    }

    public IdentitiesService getIdentitiesService() {
        ctx = GetContext.getApplicationContext();
        identitiesService = (IdentitiesService) ctx.getBean("identitiesService");
        return identitiesService;
    }

    public EmployeeBenefitsService getEmployeeBenefitsService() {
        ctx = GetContext.getApplicationContext();
        employeeBenefitsService = (EmployeeBenefitsService) ctx.getBean("employeeBenefitsService");
        return employeeBenefitsService;
    }

    public EmployeeLanguagesService getEmployeeLanguagesService() {
        ctx = GetContext.getApplicationContext();
        languagesService = (EmployeeLanguagesService) ctx.getBean("employeeLanguagesService");
        return languagesService;
    }

    public CompetencyService getEmployeeCourseService() {
        ctx = GetContext.getApplicationContext();
        competencyService = (CompetencyService) ctx.getBean("competencyService");
        return competencyService;
    }

    public ProfessionalRegistrationService getProfessionalRegistrationService() {
        ctx = GetContext.getApplicationContext();
        professionalRegistrationService = (ProfessionalRegistrationService) ctx.getBean("professionalRegistrationService");
        return professionalRegistrationService;
    }

    public TrainingService gettrainingService() {
        ctx = GetContext.getApplicationContext();
        trainingService = (TrainingService) ctx.getBean("trainingService");
        return trainingService;
    }

    public DisciplinaryActionService getDisciplinaryActionService() {
        ctx = GetContext.getApplicationContext();
        disciplinaryActionService = (DisciplinaryActionService) ctx.getBean("disciplinaryactionService");
        return disciplinaryActionService;
    }

    public WorkPlaceAccidentsService getWorkPlaceAccidentsService() {
        ctx = GetContext.getApplicationContext();
        workPlaceAccidentsService = (WorkPlaceAccidentsService) ctx.getBean("workPlaceAccidentsService");
        return workPlaceAccidentsService;
    }

    public EducationHistoryService getEducationHistoryService() {
        ctx = GetContext.getApplicationContext();
        educationHistoryService = (EducationHistoryService) ctx.getBean("educationHistoryService");
        return educationHistoryService;
    }

    public EmploymentHistoryService getEmploymentHistoryService() {
        ctx = GetContext.getApplicationContext();
        employmentHistoryService = (EmploymentHistoryService) ctx.getBean("employmentHistoryService");
        return employmentHistoryService;
    }

    public EmployeeService getEmployeeService() {
        ctx = GetContext.getApplicationContext();
        employeeService = (EmployeeService) ctx.getBean("employeeService");
        return employeeService;
    }

    /**
     * @return the statusService
     */
    public StatusService getStatusService() {
        ctx = GetContext.getApplicationContext();
        statusService = (StatusService) ctx.getBean("statusService");
        return statusService;
    }

    /**
     * @return the positionSalarySourcesService
     */
    public PositionSalarySourcesService getPositionSalarySourcesService() {
        ctx = GetContext.getApplicationContext();
        positionSalarySourcesService = (PositionSalarySourcesService) ctx.getBean("positionSalarySourcesService");
        return positionSalarySourcesService;
    }

    public NodesService getNodesService() {
        ctx = GetContext.getApplicationContext();
        nodesService = (NodesService) ctx.getBean("nodesService");
        return nodesService;
    }

    public ClustersService getClustersService() {
        ctx = GetContext.getApplicationContext();
        clustersService = (ClustersService) ctx.getBean("clustersService");
        return clustersService;
    }

    public MentorsRolesService getMentorsRolesService() {
        ctx = GetContext.getApplicationContext();
        mentorsRolesService = (MentorsRolesService) ctx.getBean("mentorsRolesService");
        return mentorsRolesService;
    }

    public MentorExpertiseAreaService getMentorExpertiseArea() {
        ctx = GetContext.getApplicationContext();
        mentorExpertiseAreaService = (MentorExpertiseAreaService) ctx.getBean("mentorExpertiseAreaService");
        return mentorExpertiseAreaService;
    }

    public CourseCriteriaService getCourseCriteriaService() {
        ctx = GetContext.getApplicationContext();
        courseCriteriaService = (CourseCriteriaService) ctx.getBean("courseCriteriaService");
        return courseCriteriaService;
    }

    public CourseTargetGroupService getCourseTargetGroupService() {
        ctx = GetContext.getApplicationContext();
        courseTargetGroupService = (CourseTargetGroupService) ctx.getBean("courseTargetGroupService");
        return courseTargetGroupService;
    }

    public CriteriaService getCriteriaService() {
        ctx = GetContext.getApplicationContext();
        criteriaService = (CriteriaService) ctx.getBean("criteriaService");
        return criteriaService;
    }

    public OrganisationTrainersService getOrganisationTrainersService() {
        ctx = GetContext.getApplicationContext();
        organisationTrainersService = (OrganisationTrainersService) ctx.getBean("organisationTrainersService");
        return organisationTrainersService;
    }

    public TargetGroupService getTargetGroupService() {
        ctx = GetContext.getApplicationContext();
        targetGroupService = (TargetGroupService) ctx.getBean("targetGroupService");
        return targetGroupService;
    }
}
