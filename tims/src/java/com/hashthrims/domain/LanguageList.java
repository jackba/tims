/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.domain;

import java.util.*;
import java.io.*;
/**
 *
 * @author abismail
 * this class is merely for use in the "AddEmployee" class and is not supposed to be a table,
 * but acts as an associative entity if you'd like to think of it as such
 */
public class LanguageList implements Serializable{
    private List<String> languages;
    private int numberOfLanguages;

    public LanguageList(){
        numberOfLanguages = 0;
        languages = new ArrayList<String>();
    }

    /**
     * @return the languages
     */
    public List<String> getLanguages() {
        return languages;
    }

    public String getLanguage(String lang) {
//        usage: if((languageList.getLanguage(userSpecifiedLanguage)).equals(null)){sout("this employee does not speak" + userSpecifiedLanguage;)}
        return languages.contains(lang) ? languages.get(languages.indexOf(lang)) : null;
    }

    /**
     * @param languages the languages to set
     */
    public void addLanguage(String language) {
        numberOfLanguages++;
        this.languages.add(language);
    }

    /**
     * @return the numberOfLanguages
     */
    public int getNumberOfLanguages() {
        return numberOfLanguages;
    }
}
