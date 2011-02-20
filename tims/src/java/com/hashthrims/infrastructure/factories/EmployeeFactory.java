/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.infrastructure.factories;

import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.domain.*;
import com.hashthrims.domain.employeelist.BenefitFrequency;
import com.hashthrims.domain.employeelist.BenefitType;
import com.hashthrims.domain.employeelist.CompetencyEvaluation;
import com.hashthrims.domain.employeelist.CompetencyList;
import com.hashthrims.domain.employeelist.DepartureReasons;
import com.hashthrims.domain.employeelist.DisciplineActionTypeList;
import com.hashthrims.domain.employeelist.GenderList;
import com.hashthrims.domain.employeelist.IdentificationType;
import com.hashthrims.domain.employeelist.Language;
import com.hashthrims.domain.employeelist.MaritalStatusList;
import com.hashthrims.domain.jobs.SalaryGrade;
import com.hashthrims.domain.offices.Facility;

import com.hashthrims.domain.offices.RegistrationBody;
import com.hashthrims.domain.positions.Positions;
import com.hashthrims.domain.positions.SalarySources;
import com.hashthrims.domain.regionlist.Country;
import com.hashthrims.domain.traininglist.MentoringCompetencies;
import com.hashthrims.domain.traininglist.MentoringMentors;
import com.hashthrims.domain.traininglist.MentoringSession;
import com.hashthrims.domain.traininglist.MentoringSessionType;
import com.hashthrims.domain.traininglist.Mentors;
import com.hashthrims.domain.traininglist.TrainingCourseRequestors;
import com.hashthrims.domain.traininglist.TrainingCourses;
import com.hashthrims.infrastructure.conf.GetContext;
import com.hashthrims.services.ApplicationsService;
import com.hashthrims.services.BenefitTypeService;
import com.hashthrims.services.CompetencyService;
import com.hashthrims.services.ContactsService;
import com.hashthrims.services.DepartureReasonsService;
import com.hashthrims.services.DisciplinaryActionService;
import com.hashthrims.services.DisciplineActionTypeListService;
import com.hashthrims.services.EducationHistoryService;
import com.hashthrims.services.EmployeeBenefitsService;
import com.hashthrims.services.EmployeeService;
import com.hashthrims.services.EmploymentHistoryService;
import com.hashthrims.services.GenderListService;
import com.hashthrims.services.IdentificationTypeService;
import com.hashthrims.services.IdentitiesService;
import com.hashthrims.services.EmployeeLanguagesService;
import com.hashthrims.services.MaritalStatusListService;
import com.hashthrims.services.PersonService;
import com.hashthrims.services.ProfessionalRegistrationService;
import com.hashthrims.services.TrainingService;
import com.hashthrims.services.WorkPlaceAccidentsService;
import org.springframework.context.ApplicationContext;
import java.util.*;

/**
 *
 * @author boniface
 */
public class EmployeeFactory {

    private EmployeeService employeeService;
    private PersonService personService;
    private ContactsService contactService;
    private CompetencyService competencyService;
    private IdentitiesService identitiesService;
    private EmployeeBenefitsService benefitsService;
    private EmployeeLanguagesService languageService;
    private ProfessionalRegistrationService professionalRegistrationService;
    private TrainingService trainingService;
    private DisciplinaryActionService disciplinaryActionService;
    private WorkPlaceAccidentsService workPlaceAccidentsService;
    private ApplicationsService applicationsService;
    private EducationHistoryService educationHistoryService;
    private EmploymentHistoryService employmentHistoryService;
    private DisciplineActionTypeListService disciplineActionTypeList;
    private IdentificationTypeService identificationTypeService;
    private MaritalStatusListService maritalStatusService;
    private BenefitTypeService benefitTypeService;
    private DepartureReasonsService departureReasonService;
    private DisciplineActionTypeListService disciplineActionListService;
    private GenderListService genderListService;
    private ApplicationContext ctx = GetContext.getApplicationContext();
    private ClientDataService data = new ClientDataService();

    public Employee createEmployee(List<WorkPlaceAccidents> accidents, List<Applications> applications, List<EmployeeBenefits> benefits, List<EmployeeCourses> competencies, List<DisciplinaryAction> disciplinaryActions, List<EducationHistory> educationHistory, List<EmploymentHistory> employmentHistory, List<Identities> employeeIdentities, List<EmployeeLanguages> languages, EmployeePosition position, List<ProfessionalRegistration> registrations, List<EmployeeMentoring> trainingCourses) {
        Employee c = new Employee();
       
        employeeService = (EmployeeService) ctx.getBean("employeeService");
        employeeService.persist(c);
        Employee e = employeeService.find(c.getId());
        return e;
    }

    public Person createPerson(String personName, String personSurname, String personOtherName, String personNationality, Country residence, List<Contacts> contacts, Demography demography) {

        Person c = new Person();
        c.setPersonName(personName);
        c.setPersonSurname(personSurname);
        c.setContacts(contacts);
        // c.setResidence(residence);
        //c.setPersonNationality(personNationality);
        return c;
    }

    public Contacts createContacts(String mailingAddress, String telephoneNumber, String cellnumber, String faxnumber, String email, String notes, String addressType, Facility facility) {

        Contacts c = new Contacts();
        c.setAddressType(addressType);
        c.setCellnumber(cellnumber);
        c.setEmail(email);
        c.setFaxnumber(faxnumber);
        c.setMailingAddress(mailingAddress);
        c.setNotes(notes);
        c.setTelephoneNumber(telephoneNumber);
        // c.setFacility(facility);
        return c;
    }

    public EmployeeCourses createEmployeeCourse(Map<String, Long> st, Map<String,Date> dates,String retraining) {

        EmployeeCourses c = new EmployeeCourses();
        //String First
        TrainingCourses tc = data.getTrainingCoursesService().find(st.get("course"));

        c.setCourse(tc);
//        CompetencyEvaluation ce = data.getCompetencyEvaluationService().getByPropertyName("compTypeName", st.get("evaluation"));
//        c.setEvaluation(ce);
        TrainingCourseRequestors cr = data.getTrainingCourseRequestorsType().find( st.get("requestor"));
        c.setRequestor(cr);
        
        c.setCourseEndDate(dates.get("courseEndDate"));
        c.setCourseStartDate(dates.get("courseStartDate"));
        c.setDateRequested(dates.get("dateRequested"));
        c.setRetraining(retraining);
      

        return c;
    }

    public DisciplinaryAction createDisciplinaryAction(Date dateOfDiscussion, DisciplineActionTypeList disciplinaryActionTypeList, Date endOfApplicability, String notes, String peoplePresent, Date startOfApplicability) {
        DisciplinaryAction c = new DisciplinaryAction();
        c.setDateOfDiscussion(dateOfDiscussion);
        c.setEmpDisciplinaryAction(disciplinaryActionTypeList);
        c.setEndofAplicability(endOfApplicability);
        c.setNotes(notes);
        c.setPeoplePresent(peoplePresent);
        c.setSatrtofAplicability(startOfApplicability);
        return c;
    }

    public Identities createIdentities(String idType, String idValue) {

        Identities c = new Identities();
        c.setIdType(idType);
        c.setIdValue(idValue);


        return c;
    }

    public EmployeeBenefits createEmployeeBenefits(BenefitType benefits, SalarySources source, SalaryGrade amount, BenefitFrequency frequency, Date startDate, Date endDate) {

        EmployeeBenefits c = new EmployeeBenefits();
        c.setAmount(amount);
        c.setBenefits(benefits);
        c.setEndDate(endDate);
        c.setFrequency(frequency);
        c.setSource(source);
        c.setStartDate(startDate);

        return c;
    }

    public EmployeeLanguages createLanguages(Language language, String writing, String reading, String speaking) {
        EmployeeLanguages c = new EmployeeLanguages();
        c.setLanguage(language);
        c.setReading(reading);
        c.setSpeaking(speaking);
        c.setWriting(writing);
        return c;
    }

    public ProfessionalRegistration createProfessionalRegistration(RegistrationBody registrationBody, String registrationNumber, Date registrationDate, String licenceNumber, Date expirationDate) {
        ProfessionalRegistration c = new ProfessionalRegistration();
        c.setExpirationDate(expirationDate);
        c.setLicenceNumber(licenceNumber);
        c.setRegistrationBody(registrationBody);
        c.setRegistrationDate(registrationDate);
        c.setRegistrationNumber(registrationNumber);

        return c;
    }

    public Applications createApplications(Positions position, Date startDate, SalaryGrade desiredWage, boolean employmentyContract, String availability, String adverSource, String additionalSkills, String felony, String felonyDetails) {

        Applications c = new Applications();
        c.setAdditionalSkills(additionalSkills);
        c.setAdverSource(adverSource);
        c.setAvailability(availability);
        c.setDesiredWage(desiredWage);
        c.setEmploymentyContract(employmentyContract);
        c.setFelony(felony);
        c.setFelonyDetails(felonyDetails);
        c.setPosition(position);
        c.setStartDate(startDate);
        return c;
    }

    public Person updatePerson(String personName, String personSurname, String personOtherName, String personNationality, Country residence, List<Contacts> contacts, Demography demography, Long id) {
        employeeService = (EmployeeService) ctx.getBean("employeeService");
        Employee e = employeeService.find(id);

        employeeService.merge(e);
        return null;
    }

    public Contacts updateContacts(String mailingAddress, String telephoneNumber, String cellnumber, String faxnumber, String email, String notes, String addressType, Facility facility, Long id) {
        employeeService = (EmployeeService) ctx.getBean("employeeService");
        Employee e = employeeService.find(id);
        Contacts c = null;
        c.setAddressType(addressType);
        c.setCellnumber(cellnumber);
        c.setEmail(email);
        c.setFaxnumber(faxnumber);
        c.setMailingAddress(mailingAddress);
        c.setNotes(notes);
        c.setTelephoneNumber(telephoneNumber);
        // c.setFacility(facility);
        return c;
    }

    public EmployeeCourses updateEmployeeCourse(Map<String, Long> st, Map<String,Date> dates, String retraining) {

        EmployeeCourses c = loadEmployeeCourse(st.get("id"));
        //String First
        TrainingCourses tc = data.getTrainingCoursesService().find(st.get("course"));

        c.setCourse(tc);
//        CompetencyEvaluation ce = data.getCompetencyEvaluationService().getByPropertyName("compTypeName", st.get("evaluation"));
//        c.setEvaluation(ce);
        TrainingCourseRequestors cr = data.getTrainingCourseRequestorsType().find( st.get("requestor"));
        c.setRequestor(cr);

        c.setCourseEndDate(dates.get("courseEndDate"));
        c.setCourseStartDate(dates.get("courseStartDate"));
        c.setDateRequested(dates.get("dateRequested"));
        c.setRetraining(retraining);

        return c;
    }

    public Identities updateIdentities(String idType, String idValue) {

        Identities c = new Identities();
        c.setIdType(idType);
        c.setIdValue(idValue);


        return c;
    }

    public EmployeeBenefits updateEmployeeBenefits(BenefitType benefits, SalarySources source, SalaryGrade amount, BenefitFrequency frequency, Date startDate, Date endDate) {

        EmployeeBenefits c = new EmployeeBenefits();
        c.setAmount(amount);
        c.setBenefits(benefits);
        c.setEndDate(endDate);
        c.setFrequency(frequency);
        c.setSource(source);
        c.setStartDate(startDate);
        return c;
    }

    public EmployeeLanguages updateLanguages(Language language, String writing, String reading, String speaking) {
        EmployeeLanguages c = new EmployeeLanguages();
        c.setLanguage(language);
        c.setReading(reading);
        c.setSpeaking(speaking);
        c.setWriting(writing);
        return c;
    }

    public ProfessionalRegistration updateProfessionalRegistration(RegistrationBody registrationBody, String registrationNumber, Date registrationDate, String licenceNumber, Date expirationDate) {
        ProfessionalRegistration c = new ProfessionalRegistration();
        c.setExpirationDate(expirationDate);
        c.setLicenceNumber(licenceNumber);
        c.setRegistrationBody(registrationBody);
        c.setRegistrationDate(registrationDate);
        c.setRegistrationNumber(registrationNumber);

        return c;
    }

    public Applications updateApplications(Positions position, Date startDate, SalaryGrade desiredWage, boolean employmentyContract, String availability, String adverSource, String additionalSkills, String felony, String felonyDetails) {

        Applications c = new Applications();
        c.setAdditionalSkills(additionalSkills);
        c.setAdverSource(adverSource);
        c.setAvailability(availability);
        c.setDesiredWage(desiredWage);
        c.setEmploymentyContract(employmentyContract);
        c.setFelony(felony);
        c.setFelonyDetails(felonyDetails);
        c.setPosition(position);
        c.setStartDate(startDate);
        return c;
    }

    public Employee loadEmployee(Long id) {
        employeeService = (EmployeeService) ctx.getBean("employeeService");
        Employee c = employeeService.find(id);
        return c;
    }

    public Person loadPerson(Long id) {
        personService = (PersonService) ctx.getBean("personService");
        Person c = personService.find(id);
        return c;
    }

    public Contacts loadContacts(Long id) {
        contactService = (ContactsService) ctx.getBean("contactService");
        Contacts c = contactService.find(id);
        return c;
    }

    public EmployeeCourses loadCompetency(Long id) {
        competencyService = (CompetencyService) ctx.getBean("competencyService");
        EmployeeCourses c = competencyService.find(id);
        return c;
    }

    public Identities loadIdentities(Long id) {
        identitiesService = (IdentitiesService) ctx.getBean("employeeService");
        Identities c = identitiesService.find(id);
        return c;
    }

    public EmployeeBenefits loadEmployeeBenefits(Long id) {
        benefitsService = (EmployeeBenefitsService) ctx.getBean("benefitsService");
        EmployeeBenefits c = benefitsService.find(id);
        return c;
    }

    public EmployeeLanguages loadLanguages(Long id) {
        languageService = (EmployeeLanguagesService) ctx.getBean("languageService");
        EmployeeLanguages c = languageService.find(id);
        return c;
    }

    public ProfessionalRegistration loadProfessionalRegistration(Long id) {
        professionalRegistrationService = (ProfessionalRegistrationService) ctx.getBean("professionalRegistrationService");
        ProfessionalRegistration c = professionalRegistrationService.find(id);
        return c;
    }

    public EmployeeMentoring loadTraining(Long id) {
        trainingService = (TrainingService) ctx.getBean("trainingService");
        EmployeeMentoring c = trainingService.find(id);
        return c;
    }

    public DisciplinaryAction loadDisciplinaryAction(Long id) {
        disciplinaryActionService = (DisciplinaryActionService) ctx.getBean("disciplinaryActionService");
        DisciplinaryAction c = disciplinaryActionService.find(id);
        return c;
    }

    public WorkPlaceAccidents loadWorkPlaceAccidents(Long id) {
        workPlaceAccidentsService = (WorkPlaceAccidentsService) ctx.getBean("workPlaceAccidentsService");
        WorkPlaceAccidents c = workPlaceAccidentsService.find(id);
        return c;
    }

    public Applications loadApplications(Long id) {
        applicationsService = (ApplicationsService) ctx.getBean("applicationsService");
        Applications c = applicationsService.find(id);
        return c;
    }

    public EducationHistory loadEducationHistory(Long id) {
        educationHistoryService = (EducationHistoryService) ctx.getBean("educationHistoryService");
        EducationHistory c = educationHistoryService.find(id);
        return c;
    }

    public EmploymentHistory loadEmploymentHistory(Long id) {
        employmentHistoryService = (EmploymentHistoryService) ctx.getBean("employmentHistoryService");
        EmploymentHistory c = employmentHistoryService.find(id);
        return c;
    }

    public DisciplinaryAction updateDisciplinaryAction(Date dateOfDiscussion,
            String disciplinaryActionTypeList, Date endOfApplicability, String notes, String peoplePresent, Date startOfApplicability, Long Id) {
        DisciplinaryAction c = loadDisciplinaryAction(Id);
        disciplineActionTypeList = (DisciplineActionTypeListService) ctx.getBean("discipleneActionListService");
        DisciplineActionTypeList d = disciplineActionTypeList.getByPropertyName("empDisciplinaryAction", disciplinaryActionTypeList);
        c.setDateOfDiscussion(dateOfDiscussion);
        c.setEmpDisciplinaryAction(d);
        c.setEndofAplicability(endOfApplicability);
        c.setNotes(notes);
        c.setPeoplePresent(peoplePresent);
        c.setSatrtofAplicability(startOfApplicability);
        return c;
    }

    public IdentificationType createIdentificationType(String identityType) {
        IdentificationType c = new IdentificationType();
        c.setIdentity_name_type(identityType);
        return c;
    }

    public IdentificationType loadIdentificationType(Long id) {
        identificationTypeService = (IdentificationTypeService) ctx.getBean("identificationTypeService");
        IdentificationType c = identificationTypeService.find(id);
        return c;
    }

    public Language createLanguage(String language) {
        Language r = new Language();
        r.setLanguage_name(language);
        return r;
    }

    public MaritalStatusList createMaritalStatusList(String status) {
        MaritalStatusList d = new MaritalStatusList();
        d.setStatus_name(status);
        return d;
    }

    public MaritalStatusList loadMaritalStatusList(Long id) {
        maritalStatusService = (MaritalStatusListService) ctx.getBean("maritalStatusListService");
        MaritalStatusList d = maritalStatusService.find(id);
        return d;
    }

    public BenefitType createBenefitType(String benefitType) {
        BenefitType c = new BenefitType();
        c.setBenefit_type_name(benefitType);

        return c;
    }

    public BenefitType loadBenefitType(Long id) {
        benefitTypeService = (BenefitTypeService) ctx.getBean("benefitTypeService");
        BenefitType c = benefitTypeService.find(id);
        return c;
    }

    public DepartureReasons createDepartureReasons(String reason) {
        DepartureReasons c = new DepartureReasons();
        c.setReason_name(reason);



        return c;
    }

    public DepartureReasons loadDepartureReasons(Long id) {
        departureReasonService = (DepartureReasonsService) ctx.getBean("departureReasonService");
        DepartureReasons c = departureReasonService.find(id);
        return c;
    }

    public MaritalStatusList updatedMaritalStatusList(String status, Long id) {
        MaritalStatusList c = loadMaritalStatusList(id);
        c.setStatus_name(status);
        return c;
    }

    public IdentificationType updatedIdentificationType(String identityNameType, Long id) {
        IdentificationType c = loadIdentificationType(id);
        c.setIdentity_name_type(identityNameType);
        return c;
    }

    public BenefitType updatedBenefitType(String benefitTypeName, Long id) {
        BenefitType c = loadBenefitType(id);
        c.setBenefit_type_name(benefitTypeName);
        return c;
    }

    public DisciplineActionTypeList createDisciplineActionList(String disciplineName) {
        DisciplineActionTypeList c = new DisciplineActionTypeList();
        c.setDisplineName(disciplineName);

        return c;
    }

    public DisciplineActionTypeList loadDisciplinaryActionListService(Long id) {
        disciplineActionListService = (DisciplineActionTypeListService) ctx.getBean("disciplineActionListService");
        DisciplineActionTypeList d = disciplineActionListService.find(id);
        return d;
    }

    public DisciplineActionTypeList updateDisciplineActionList(String displineName, Long disciplinaryTypeListId) {
        DisciplineActionTypeList d = loadDisciplinaryActionListService(disciplinaryTypeListId);
        d.setDisplineName(displineName);
        return d;
    }

    public GenderList createGenderList(String genderListName) {
        GenderList g = new GenderList();
        g.setGender(genderListName);
        return g;
    }

    public GenderList updatedGenderList(String genderListName, Long GenderListId) {
        GenderList g = loadGenderList(GenderListId);
        g.setGender(genderListName);
        return g;
    }

    public GenderList loadGenderList(Long genderListId) {
        genderListService = (GenderListService) ctx.getBean("genderListService");
        GenderList g = genderListService.find(genderListId);
        return g;
    }

    public EmployeeMentoring updateMentoringSession(Map<String, Long> val, Date mentoringDate, Long id) {
        EmployeeMentoring c = loadEmployeeMentoring(id);
        MentoringSession tc = data.getMentoringSessionService().find(val.get("mentoringSession"));
        c.setMentoringSession(tc);
        Facility facility = data.getFacilityService().find(val.get("venue"));
        c.setVenue(facility);
        c.setHoursSpent(val.get("hoursSpent"));
        c.setPatientsInitiated(val.get("patientsInitiated"));
        c.setMentoringDate(mentoringDate);
        return c;

    }

    public EmployeeMentoring saveMentoringSession(Map<String, Long> val, Date mentoringDate) {
       
        EmployeeMentoring c = new EmployeeMentoring();
        // Create a mentoring Session
        MentoringSession tc = data.getMentoringSessionService().find(val.get("mentoringSession"));
        c.setMentoringSession(tc);
        Facility facility = data.getFacilityService().find(val.get("venue"));
        c.setVenue(facility);
        c.setHoursSpent(val.get("hoursSpent"));
        c.setPatientsInitiated(val.get("patientsInitiated"));
        c.setMentoringDate(mentoringDate);
        return c;
    }

    private EmployeeMentoring loadEmployeeMentoring(Long id) {
        EmployeeMentoring m = data.gettrainingService().find(id);
        return m;
    }

    public EmployeeMentoring createMentoringSession(Map<String, Long> val, Date mentoringDate,  List<String> mentors, List<String> competencies,Person person) {
       EmployeeMentoring c = new EmployeeMentoring();
       // Create a Mentoring Session
        List<MentoringCompetencies> comps = new ArrayList<MentoringCompetencies>();
        List<MentoringMentors> mentoringMentors = new ArrayList<MentoringMentors>();
         MentoringSession ms = new MentoringSession();


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
        ms.setMentoringCompetencies(comps);
        ms.setMentoringMentors(mentoringMentors);
        MentoringSessionType mentoringSessionType = data.getMentoringSessionTypeService().find(val.get("typeOfSession"));
        ms.setMentoringSessionType(mentoringSessionType);
        ms.setSessionName(person.getId()+"-"+person.getPersonSurname()+" "+person.getPersonName());

        data.getMentoringSessionService().persist(ms);



       //final MentoringSession c = factory.updateMentoringSessions(simpleFields, competencies, trainingFunders,mentors, mentoringId);
        Facility facility = data.getFacilityService().find(val.get("venue"));
        c.setVenue(facility);
        c.setHoursSpent(val.get("hoursSpent"));
        c.setPatientsInitiated(val.get("patientsInitiated"));
        c.setMentoringDate(mentoringDate);
        c.setMentoringSession(ms);

        return c;
    }

    private EmployeeCourses loadEmployeeCourse(Long id) {
        return data.getEmployeeCourseService().find(id);
    }

    public EmployeeCourses updateEvalaution(Long id, Long evaluation, Date date) {
        EmployeeCourses ec = loadEmployeeCourse(id);
        CompetencyEvaluation eval = data.getCompetencyEvaluationService().find(evaluation);
        ec.setEvaluation(eval);
        ec.setLastEvaluated(date);
        return ec;
    }
}
