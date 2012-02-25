/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.reports.people.tables;

import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.reports.people.forms.DateSearchCombo;
import com.hashthrims.clients.web.vaadin.views.reports.people.forms.LocationCombo;
import com.hashthrims.clients.web.vaadin.views.reports.people.util.PersonLocation;
import com.hashthrims.domain.EmployeeCourses;
import com.hashthrims.domain.Person;
import com.hashthrims.infrastructure.util.DataFieldsUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author boniface
 */
class PeopleData {

    ClientDataService data = new ClientDataService();
    DataFieldsUtil date = new DataFieldsUtil();

    public PeopleData() {
    }

    List<Person> getPeople(LocationCombo locationCombo, DateSearchCombo dateSearchCombo) {
        List<Person> people = new ArrayList<Person>();
        Date startDate = date.getDateFields(dateSearchCombo.getStartDate().getValue());
        Date endDate = date.getDateFields(dateSearchCombo.getEndDate().getValue());

        if (dateSearchCombo.getPositionsCombo().getValue() == null & dateSearchCombo.getTopicCombo().getValue() == null) {
            if (locationCombo.getFacility().getValue() != null) {
                List<Person> p = data.getPersonService().findAll();
                for (Person person : p) {
                    List<EmployeeCourses> courses = person.getCourses();
                    for (EmployeeCourses ec : courses) {
                        if (startDate.before(ec.getCourseStartDate()) & endDate.after(ec.getCourseEndDate())) {
                            if (locationCombo.getFacility().getValue().equals(new PersonLocation().getFacility(person.getPosition()))) {
                                people.add(person);
                            }
                        }
                    }
                }

            } else if (locationCombo.getCity().getValue() != null) {

                List<Person> p = data.getPersonService().findAll();
                for (Person person : p) {
                    List<EmployeeCourses> courses = person.getCourses();
                    for (EmployeeCourses ec : courses) {
                        if (startDate.before(ec.getCourseStartDate()) & endDate.after(ec.getCourseEndDate())) {
                            if (locationCombo.getCity().getValue().equals(new PersonLocation().getCity(person.getPosition()))) {
                                people.add(person);
                            }
                        }
                    }
                }


            } else if (locationCombo.getSubDistrict().getValue() != null) {
                List<Person> p = data.getPersonService().findAll();
                for (Person person : p) {
                    List<EmployeeCourses> courses = person.getCourses();
                    for (EmployeeCourses ec : courses) {
                        if (startDate.before(ec.getCourseStartDate()) & endDate.after(ec.getCourseEndDate())) {
                            if (locationCombo.getSubDistrict().getValue().equals(new PersonLocation().getSubDistrict(person.getPosition()))) {
                                people.add(person);
                            }
                        }
                    }
                }
            } else if (locationCombo.getDistrict().getValue() != null) {
                List<Person> p = data.getPersonService().findAll();
                for (Person person : p) {
                    List<EmployeeCourses> courses = person.getCourses();
                    for (EmployeeCourses ec : courses) {
                        if (startDate.before(ec.getCourseStartDate()) & endDate.after(ec.getCourseEndDate())) {
                            if (locationCombo.getDistrict().getValue().equals(new PersonLocation().getDistrict(person.getPosition()))) {
                                people.add(person);
                            }
                        }
                    }
                }
            } else {
                List<Person> p = data.getPersonService().findAll();
                for (Person person : p) {
                    List<EmployeeCourses> courses = person.getCourses();
                    for (EmployeeCourses ec : courses) {
                        if (startDate.before(ec.getCourseStartDate()) & endDate.after(ec.getCourseEndDate())) {
                            people.add(person);

                        }
                    }
                }
            }
        } else if (dateSearchCombo.getPositionsCombo().getValue() == null & dateSearchCombo.getTopicCombo().getValue() != null) {

            if (locationCombo.getFacility().getValue() != null) {
                List<Person> p = data.getPersonService().findAll();
                for (Person person : p) {
                    List<EmployeeCourses> courses = person.getCourses();
                    for (EmployeeCourses ec : courses) {
                        if (startDate.before(ec.getCourseStartDate()) & endDate.after(ec.getCourseEndDate())) {
                            if (locationCombo.getFacility().getValue().equals(new PersonLocation().getFacility(person.getPosition()))) {
                                if (new PersonLocation().isTrainingCourse(ec.getScheduledCourseSessionId(), dateSearchCombo.getTopicCombo().getValue())) {
                                    people.add(person);
                                }
                            }
                        }
                    }
                }
            } else {
                List<Person> p = data.getPersonService().findAll();
                for (Person person : p) {
                    List<EmployeeCourses> courses = person.getCourses();
                    for (EmployeeCourses ec : courses) {
                        if (startDate.before(ec.getCourseStartDate()) & endDate.after(ec.getCourseEndDate())) {

                            if (new PersonLocation().isTrainingCourse(ec.getScheduledCourseSessionId(), dateSearchCombo.getTopicCombo().getValue())) {
                                people.add(person);
                            }

                        }
                    }
                }

            }




        } else if (dateSearchCombo.getPositionsCombo().getValue() != null & dateSearchCombo.getTopicCombo().getValue() == null) {


            if (locationCombo.getFacility().getValue() != null) {

                List<Person> p = data.getPersonService().findAll();
                for (Person person : p) {
                    List<EmployeeCourses> courses = person.getCourses();
                    for (EmployeeCourses ec : courses) {
                        if (startDate.before(ec.getCourseStartDate()) & endDate.after(ec.getCourseEndDate())) {
                            if (locationCombo.getFacility().getValue().equals(new PersonLocation().getFacility(person.getPosition()))) {
                                if (new PersonLocation().isPosition(person.getPosition(), dateSearchCombo.getPositionsCombo().getValue())) {
                                    people.add(person);
                                }
                            }
                        }
                    }
                }

            } else {

                List<Person> p = data.getPersonService().findAll();
                for (Person person : p) {
                    List<EmployeeCourses> courses = person.getCourses();
                    for (EmployeeCourses ec : courses) {
                        if (startDate.before(ec.getCourseStartDate()) & endDate.after(ec.getCourseEndDate())) {

                            if (new PersonLocation().isPosition(person.getPosition(), dateSearchCombo.getPositionsCombo().getValue())) {
                                people.add(person);
                            }
                        }
                    }
                }
            }

        } else {
            if (locationCombo.getFacility().getValue() != null) {
                List<Person> p = data.getPersonService().findAll();
                for (Person person : p) {
                    List<EmployeeCourses> courses = person.getCourses();
                    for (EmployeeCourses ec : courses) {
                        if (startDate.before(ec.getCourseStartDate()) & endDate.after(ec.getCourseEndDate())) {
                            if (locationCombo.getFacility().getValue().equals(new PersonLocation().getFacility(person.getPosition()))) {
                                if (new PersonLocation().isPosition(person.getPosition(), dateSearchCombo.getPositionsCombo().getValue())) {
                                    if (new PersonLocation().isTrainingCourse(ec.getScheduledCourseSessionId(), dateSearchCombo.getTopicCombo().getValue())) {
                                        people.add(person);
                                    }
                                }
                            }
                        }
                    }
                }

            } else {
                List<Person> p = data.getPersonService().findAll();
                for (Person person : p) {
                    List<EmployeeCourses> courses = person.getCourses();
                    for (EmployeeCourses ec : courses) {
                        if (startDate.before(ec.getCourseStartDate()) & endDate.after(ec.getCourseEndDate())) {

                            if (new PersonLocation().isPosition(person.getPosition(), dateSearchCombo.getPositionsCombo().getValue())) {
                                if (new PersonLocation().isTrainingCourse(ec.getScheduledCourseSessionId(), dateSearchCombo.getTopicCombo().getValue())) {
                                    people.add(person);
                                }
                            }

                        }
                    }
                }

            }


        }


        return people;
    }
}
