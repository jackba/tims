/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.infrastructure.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author boniface
 */
public class DateUtil {

    private Date currentDate;

    public Date getDate(String dateLocale)  {



       if (dateLocale!=null) {
            String pattern = "EEE MMM dd HH:mm:ss z yyyy";
            String pattern1 = "yyyy-MM-dd";
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            try {
                currentDate = df.parse(dateLocale);
            } catch (ParseException ex) {

                SimpleDateFormat df1 = new SimpleDateFormat(pattern1);
                try {
                    currentDate = df1.parse(dateLocale);
                } catch (ParseException ex1) {
                    Logger.getLogger(DateUtil.class.getName()).log(Level.SEVERE, null, ex1);
                }
                Logger.getLogger(DateUtil.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
           currentDate=null;
        }



        return currentDate;
    }
}
