/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views;

import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.vaadin.ui.Label;

/**
 *
 * @author boniface
 */
public class LandingPageData {

 
    private static ClientDataService data = new ClientDataService();

    public Label getHeader() {
        return new Label(" <font color=red><H1><CENTER>The System Snapshot Statistics</CENTER> </H1> </font>", Label.CONTENT_XHTML);
    }

    public Label getLine() {
        return new Label(" <hr/>", Label.CONTENT_XHTML);
    }

    public Label getPeopleLabel() {
        return new Label(" <font color=green><H1><CENTER><u>People</u></CENTER> </H1> </font>", Label.CONTENT_XHTML);
    }

    public Label getCourseLabel() {
        return new Label(" <font color=green><H1><CENTER><u>Courses</u></CENTER> </H1> </font>", Label.CONTENT_XHTML);
    }

    public Label getTotalPeopleLabel() {
        return new Label(" <font color=blue><H3>Number of People on the System: </H3> </font>", Label.CONTENT_XHTML);
    }

    public Label getTotalPeopleData() {
        return null;
    }

    public Label getTotalCourses() {
        return new Label(" <font color=blue><H3>Number of Courses on the System: </H3> </font>", Label.CONTENT_XHTML);
    }

    public Label getTotalCoursesData() {
        return null;
    }

    public Label getScheduledCoursesThisMonth() {
        return new Label(" <font color=blue><H3>Scheduled Course This Month: </H3> </font>", Label.CONTENT_XHTML);
    }

    public Label getScheduledCoursesThisMonthData() {
        return null;
    }

    public Label getGetMaleLabel() {
        return new Label(" <font color=blue><H3>Males On the system: </H3> </font>", Label.CONTENT_XHTML);
    }

    public Label getGetMaleLabelData() {
        return null;
    }

    public Label getGetFemaleLabel() {
        return new Label(" <font color=blue><H3>Females On the system: </H3> </font>", Label.CONTENT_XHTML);
    }

    public Label getFemaleLabelData() {
        return null;
    }

    public Label getUnknownGenderLabel() {
        return new Label(" <font color=blue><H3>Unkown Gender: </H3> </font>", Label.CONTENT_XHTML);
    }

    public Label getUnknownGenderLabelData() {
        return null;
    }

    public Label getCoursesOpenThisMonth() {
        return new Label(" <font color=blue><H3> Courses Open  This Month: </H3> </font>", Label.CONTENT_XHTML);
    }

    public Label getCoursesOpenThisMonthData() {
        return null;
    }
    
    public Label getCoursesFullThisMonth() {
        return new Label(" <font color=blue><H3> Courses Full  This Month: </H3> </font>", Label.CONTENT_XHTML);
    }

    public Label getCoursesFullThisMonthData() {
        return null;
    }
    
    
     public Label getCourseMentors() {
        return new Label(" <font color=blue><H3> Course Mentors on the System: </H3> </font>", Label.CONTENT_XHTML);
    }

    public Label getCourseMentorsData() {
        return null;
    }
    
    public long getPeopleData(){
        return data.getSnapshotDataService().getNumberOfPeople();
    }
    
     public long getNumberOfMales(){
        return data.getSnapshotDataService().getNumberOfMales();
    }
     
      public long getNumberofFemales(){
        return data.getSnapshotDataService().getNumberofFemales();
    }
      
       public long getNumberofCourses(){
        return data.getSnapshotDataService().getNumberofCourses();
    }
       
        public long getNumberofVacancies(){
        return data.getSnapshotDataService().getNumberofVacancies();
    }
        
         public long getNumberScheduledCourses(){
        return data.getSnapshotDataService().getNumberScheduledCourses();
    }
    
    
    
}
