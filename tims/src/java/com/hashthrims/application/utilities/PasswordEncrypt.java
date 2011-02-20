/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.application.utilities;


import java.security.*;
import java.util.logging.*;

/**
 *
 * @author abismail
 */
public class PasswordEncrypt {

    public static String encrypt(String freeText) {
        StringBuilder getString = new StringBuilder();
        try {
            MessageDigest msg = MessageDigest.getInstance("MD5", "SUN");
            byte bs[] = freeText.getBytes();
            byte digest[] = msg.digest(bs);
            for (int i = 0; i < digest.length; ++i) {
                getString.append(Integer.toHexString(0x0100 + (digest[i] & 0x00FF)).substring(1));
            }
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(PasswordEncrypt.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchProviderException ex) {
            Logger.getLogger(PasswordEncrypt.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getString.toString();
    }
}
