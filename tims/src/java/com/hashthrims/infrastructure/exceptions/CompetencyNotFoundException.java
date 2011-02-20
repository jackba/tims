/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.infrastructure.exceptions;

/**
 *
 * @author administrator.dat
 */
public class CompetencyNotFoundException  extends Exception{
    public String toString(){
        return "The competency type you were searching for does not exist";
    }

    public void printStackTrace(){
        super.printStackTrace();
    }
}
