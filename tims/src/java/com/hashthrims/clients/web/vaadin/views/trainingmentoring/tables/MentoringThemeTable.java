/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.clients.web.vaadin.views.trainingmentoring.tables;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.trainingmentoring.util.MentoringUtil;
import com.hashthrims.domain.traininglist.MentoringTheme;
import com.vaadin.ui.*;
import java.util.List;
/**
 *
 * @author stud
 */
public class MentoringThemeTable extends Table{
  private static final ClientDataService data = new ClientDataService();
  private HashThrimsMain main;
  private final MentoringUtil st = new MentoringUtil();

    public MentoringThemeTable(HashThrimsMain app) {
        this.main = app;
        // Make Table fill all space
        setSizeFull();
        // Define the names and data types of columns.
        addContainerProperty("Mentoring Field", String.class, null);
         addContainerProperty("Mentoring Theme", String.class, null);

        // Add Data Columns
        List<MentoringTheme> mentoringThemes = data.getMentoringThemeService().findAll();
        for (MentoringTheme theme : mentoringThemes) {
            addItem(new Object[]{st.getFieldName(theme.getMentoringField()),
                                 theme.getMentoringTheme()}, theme.getId());
        }

        // Allow selecting items from the table.
        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);


    }
}
