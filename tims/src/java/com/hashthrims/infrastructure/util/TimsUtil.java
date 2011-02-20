/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.infrastructure.util;

import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.domain.Contacts;
import com.hashthrims.domain.Demography;
import com.hashthrims.domain.EmployeePosition;
import com.hashthrims.domain.employeelist.CompetencyEvaluation;
import com.hashthrims.domain.employeelist.CompetencyType;
import com.hashthrims.domain.employeelist.Language;
import com.hashthrims.domain.jobs.Jobs;
import com.hashthrims.domain.offices.Facility;
import com.hashthrims.domain.offices.FacilityType;
import com.hashthrims.domain.positions.Positions;
import com.hashthrims.domain.positions.Status;
import com.hashthrims.domain.regionlist.City;
import com.hashthrims.domain.traininglist.CourseCompetencies;
import com.hashthrims.domain.traininglist.CourseTypeName;
import com.hashthrims.domain.traininglist.MentoringCompetencies;
import com.hashthrims.domain.traininglist.MentoringSession;
import com.hashthrims.domain.traininglist.MentoringSessionType;
import com.hashthrims.domain.traininglist.TrainingCourseCategory;
import com.hashthrims.domain.traininglist.TrainingCourseRequestors;
import com.hashthrims.domain.traininglist.TrainingCourses;
import com.hashthrims.domain.traininglist.TrainingInstitution;
import com.vaadin.data.Property;
import java.util.Date;
import java.util.List;

/**
 *
 * @author boniface
 */
public class TimsUtil {
    private final ClientDataService data = new ClientDataService();

    public String getVal(CompetencyType competence) {
        if (competence != null) {
            return competence.getComp_name_typ();
        }
        return null;
    }

    public String getCourseLabel(TrainingCourses course) {
        if (course != null) {
            return course.getCourseName();
        }
        return null;
    }

    public String getRequestor(TrainingCourseRequestors requestor) {
        if (requestor != null) {
            return requestor.getRequestorName();
        }
        return null;
    }

    public String getEvaluation(CompetencyEvaluation evaluation) {
        if (evaluation != null) {
            return evaluation.getCompt_type_name();
        }
        return null;
    }

    public String getCompetencies(TrainingCourses course) {
        if (course != null) {
            return getCourseCompetencies(course.getCourseCompetencies());
        }
        return null;
    }

    public String getDateString(Date courseStartDate) {
        if (courseStartDate != null) {
            return courseStartDate.toString();
        }
        return null;
    }

    public String getResidence(City residence) {
        if (residence != null) {
            return residence.getName();
        }
        return null;
    }

    public String getGender(Demography demography) {
        if (demography != null) {
            return demography.getGender();
        }
        return null;
    }

    public Date getDob(Demography demography) {
        if (demography != null) {
            return demography.getDob();
        }
        return null;
    }

    public String getCategoryName(TrainingCourseCategory courseCategory) {
        if (courseCategory != null) {
            return courseCategory.getCategoryName();
        }
        return null;
    }

    public String getStatus(Status courseStatus) {
        if (courseStatus != null) {
            return courseStatus.getStatus();
        }
        return null;
    }

    public String getCourseType(CourseTypeName courseType) {
        if (courseType != null) {
            return courseType.getCourseType();
        }
        return null;
    }

    public String getTrainingInstitution(TrainingInstitution institutionName) {
        if (institutionName != null) {
            return institutionName.getTrainingInstitution();
        }
        return null;
    }

    public String getDobLabel(Demography demography) {
        if (demography != null) {
            return demography.getDob().toString();
        }
        return null;
    }

    public String getTypeOfMentoringSession(MentoringSession mentoringSession) {
        if (mentoringSession != null) {
            return getMentoringType(mentoringSession.getMentoringSessionType());
        }

        return null;
    }

    public String getVenue(Facility venue) {
        if (venue != null) {
            return venue.getFacilityName();
        }
        return null;
    }

    public String getEmployeeCompetencies(MentoringSession mentoringSession) {
        if (mentoringSession != null) {
            return employeeCompetencies(mentoringSession.getMentoringCompetencies());
        }
        return null;

    }

    private String employeeCompetencies(List<MentoringCompetencies> mentoringCompetencies) {
        if (mentoringCompetencies.size() > 0) {
            StringBuilder comps = new StringBuilder();
            for (MentoringCompetencies mentoringCompetencies1 : mentoringCompetencies) {
                comps.append(mentoringCompetencies1.getCompetencyName());
                comps.append(" | ");

            }
            return comps.toString();
        }
        return null;
    }

    private String getCourseCompetencies(List<CourseCompetencies> courseCompetencies) {
        if (courseCompetencies.size() > 0) {
            StringBuilder comps = new StringBuilder();
            for (CourseCompetencies courseCompetencies1 : courseCompetencies) {
                comps.append(courseCompetencies1.getCompetencyName());
                comps.append(" | ");
            }
            return comps.toString();
        }
        return null;
    }

    public String getFacilityTypeName(FacilityType facilityType) {
        if (facilityType != null) {
            return facilityType.getFacilityName();
        }
        return null;
    }

    public String getCityName(City city) {
        if (city != null) {
            return city.getName();
        }
        return null;
    }

    public String getTelephoneNumber(Contacts contact) {
        if (contact != null) {
            return contact.getTelephoneNumber();
        }
        return null;
    }

    public String getEmail(Contacts contact) {
        if (contact != null) {
            return contact.getEmail();
        }
        return null;
    }

    public String getMailingAdress(Contacts contact) {
        if (contact != null) {
            return contact.getMailingAddress();
        }
        return null;
    }

    public String getCellNumber(Contacts contact) {
        if (contact != null) {
            return contact.getCellnumber();
        }
        return null;
    }

    public String getFaxNumber(Contacts contact) {
        if (contact != null) {
            return contact.getFaxnumber();
        }
        return null;
    }

    public String getContactNotes(Contacts contact) {
        if (contact != null) {
            return contact.getNotes();
        }
        return null;
    }

    public String getPositionTitle(List<EmployeePosition> position) {
        if (position!=null) {
            for (EmployeePosition employeePosition : position) {
                return getJob(employeePosition.getPosition());//.getJob().getJob_tittle();
            }
            return null;
        } else {
            return null;
        }
    }

    public String getFacilityName(List<EmployeePosition> position) {
        if (position!=null) {
            for (EmployeePosition employeePosition : position) {
                return getFacilityPosition(employeePosition.getPosition());//.getFacililty().getFacilityName();
            }
            return null;
        } else {
            return null;
        }
    }

    private String getJob(Positions position) {
        if (position != null) {
            return getJobTitle(position.getJob());
        }
        return null;

    }

    private String getFacilityPosition(Positions position) {
       if(position!=null)
           return getFacility(position.getFacililty());
       return null;
    }

    private String getJobTitle(Jobs job) {
        if(job!=null)
            return job.getJob_tittle();
        return null;
    }

    private String getFacility(Facility facililty) {
        if(facililty!=null)
            return facililty.getFacilityName();
        return null;
    }

    public boolean checkPositionAvalaibality(Positions pos) {
      if(pos!=null){
          if(checkPositionStatus(pos.getPositionStatus()))
              return true;
        }
       
        return false;
    }

    private boolean checkPositionStatus(Status positionStatus) {
       if(positionStatus!=null){
           if(positionStatus.getStatus().equals("VACANT"))
               return true;
       }
       return false;
    }

    public Object getSubjectArea(MentoringSession mentoringSession) {
        if(mentoringSession!=null)
            return null;
        //LEAVE SUBJECT AREA OUT FOR NOW 
        return null;
    }

    private String getMentoringType(MentoringSessionType mentoringSessionType) {
        if(mentoringSessionType!=null)
            return mentoringSessionType.getSessionTypeName();
        return null;
    }

    public Long getMentoringSessionId(MentoringSession mentoringSession) {
        if(mentoringSession!=null)
            return mentoringSession.getId();
        return null;
    }

    public Long getFacilityTypeId(Facility venue) {
       if(venue!=null)
           return getIdFacilityType(venue.getFacilityType());
       return null;
    }

     public Long getFacilityId(Facility venue) {
       if(venue!=null)
           return venue.getId();
       return null;
    }

    private Long getIdFacilityType(FacilityType facilityType) {
        if(facilityType!=null)
            return facilityType.getId();
        return null;
    }

    public Long getlanguageId(Language language) {
        if(language!=null)
            return language.getId();
        return null;
    }

    public Object getLanguageName(Language language) {
        if(language!=null)
            return language.getLanguage_name();
        return null;
    }

    public String getTableValues(Property itemProperty) {
        if(itemProperty!=null)
            return itemProperty.toString();
        return null;
    }
}
