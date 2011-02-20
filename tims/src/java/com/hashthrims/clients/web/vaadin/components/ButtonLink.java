/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.components;

import com.vaadin.ui.Button;
import com.vaadin.ui.themes.Reindeer;

/**
 *
 * @author boniface
 */
public class ButtonLink {

    public static Button getButton(String st) {
        Button b = new Button(st);
        b.setStyleName(Reindeer.BUTTON_LINK);
        return b;
    }
}
