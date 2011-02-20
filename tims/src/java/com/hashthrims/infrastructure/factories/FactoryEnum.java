/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.infrastructure.factories;

/**
 *
 * @author abismail
 */

//this enum was constructed to be used when determining which factory to use, this way you don't have to go to the class everytime to check which case a string variable should be in or the format of that string, etc
public enum FactoryEnum {
    APPLICATIONS, COMPETENCY, EMPLOYEE, LANGUAGES, CONTACTS, DEMOGRAPHY, DISCIPLINARY_ACTION, EDUCATION, EDUCATION_HISTORY, EMPLOYEE_BENEFITS, EMPLOYEE_POSITION, EMPLOYMENT_HISTORY, IDENTITIES, PERSON, PROFESSIONAL_REGISTRATION, ROLES, STATUS, TRAINING, USERS, WORK_PLACE_ACCIDENTS;
}
