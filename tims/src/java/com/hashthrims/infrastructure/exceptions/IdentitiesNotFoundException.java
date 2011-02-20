/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.infrastructure.exceptions;

/**
 *
 * @author abismail
 */
public class IdentitiesNotFoundException extends Exception{
    public String toString(){
        return "No identity is entered for this person in the database";
    }

    public void printStackTrace(){
        super.printStackTrace();
    }
}
