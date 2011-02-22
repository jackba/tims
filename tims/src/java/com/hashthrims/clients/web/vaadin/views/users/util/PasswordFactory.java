/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.users.util;

import com.hashthrims.application.utilities.PasswordEncrypt;
import com.hashthrims.application.utilities.PasswordGenerator;

/**
 *
 * @author boniface
 */
public class PasswordFactory {

    public static String EncryptPassword(String password) {
        return PasswordEncrypt.encrypt(password);
    }
    public static String getNewPassword() {
        String passwd = new PasswordGenerator().getPassword();
        return EncryptPassword(passwd);
    }
}
