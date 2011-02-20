/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.infrastructure.exceptions;

/**
 *
 * @author abismail
 */
public class EmployeePositionNotFoundException extends Exception{
    public String toString(){
        return "The employee position you were searching for does not exist";
    }

    public void printStackTrace(){
        super.printStackTrace();
    }
}
