/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.infrastructure.exceptions;

/**
 *
 * @author abismail
 */
public class TrainingCourseCategoryNotFoundException   extends Exception{
    public String toString(){
        return "The TrainingCourseCategory type you were searching for does not exist";
    }

    public void printStackTrace(){
        super.printStackTrace();
    }
}
