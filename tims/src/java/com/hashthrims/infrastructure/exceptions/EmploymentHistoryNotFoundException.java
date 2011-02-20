/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.infrastructure.exceptions;

/**
 *
 * @author abismail
 */
public class EmploymentHistoryNotFoundException extends Exception{
    public String toString(){
        return "no employee history exists for this employee";
    }

    public void printStackTrace(){
        super.printStackTrace();
    }
}
