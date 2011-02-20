/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.infrastructure.exceptions;

/**
 *
 * @author abismail
 */
public class TrainingInstructorsNotFoundException   extends Exception{
    public String toString(){
        return "The TrainingInstructors type you were searching for does not exist";
    }

    public void printStackTrace(){
        super.printStackTrace();
    }
}
