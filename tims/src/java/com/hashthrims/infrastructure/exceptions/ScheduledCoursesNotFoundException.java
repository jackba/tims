/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.infrastructure.exceptions;

/**
 *
 * @author abismail
 */
public class ScheduledCoursesNotFoundException   extends Exception{
    public String toString(){
        return "The ScheduledCourses type you were searching for does not exist";
    }

    public void printStackTrace(){
        super.printStackTrace();
    }
}
