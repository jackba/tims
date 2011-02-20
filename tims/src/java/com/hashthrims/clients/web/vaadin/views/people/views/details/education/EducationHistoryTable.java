/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.people.views.details.education;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.people.views.PersonDetailsView;
import com.hashthrims.clients.web.vaadin.views.people.views.details.education.form.EducationHistoryBean;
import com.hashthrims.domain.EducationHistory;
import com.hashthrims.domain.Person;
import com.hashthrims.domain.employeelist.Degree;
import com.hashthrims.domain.regionlist.Country;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Table;
import com.vaadin.ui.themes.Reindeer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author boniface
 */
public class EducationHistoryTable extends Table {

    private final ClientDataService data = new ClientDataService();
    private final HashThrimsMain main;
    private EducationHistoryWindow subwindow;

    public EducationHistoryTable(HashThrimsMain app, Person person) {
        this.main = app;

        setSizeFull();
        // Define the names and data types of columns.
        addContainerProperty("Degree Type", String.class, null);
        addContainerProperty("Major", String.class, null);
        addContainerProperty("Graduation Date", Date.class, null);
        addContainerProperty("Institution", String.class, null);
        addContainerProperty("Country", String.class, null);
        addContainerProperty("Edits", Button.class, null);
        addContainerProperty("Deletes", Button.class, null);
        // Add Data Columns
        final Person p = data.getPersonService().find(person.getId());

        List<EducationHistory> educationHistories = null;
        if (p.getEducationHistory()!=null) {
            educationHistories = p.getEducationHistory();
        } else {
            educationHistories= new ArrayList<EducationHistory> ();
        }

        for (final EducationHistory educationHistory : educationHistories) {

            Button edit = new Button("edit", new Button.ClickListener() {

                @Override
                public void buttonClick(ClickEvent event) {
                    EducationHistoryBean bean = getBean();
                    subwindow = new EducationHistoryWindow(p, bean, main);
                    main.getMainWindow().addWindow(subwindow);

                }

                private EducationHistoryBean getBean() {
                    EducationHistoryBean bean = new EducationHistoryBean();
                    bean.setCountry(getCountryId(educationHistory.getLocation()));
                    bean.setDegreeType(getDegreeId(educationHistory.getDegreeType()));
                    bean.setGraduation(educationHistory.getGraduation());
                    bean.setInstituteName(educationHistory.getInstituteNamwe());
                    bean.setMajor(educationHistory.getMajor());
                    bean.setId(educationHistory.getId());
                    return bean;
                }

                private Long getCountryId(Country location) {
                    if(location!=null)
                        return location.getId();
                    return null;
                }

                private Long getDegreeId(Degree degreeType) {
                    if(degreeType!=null)
                        return degreeType.getId();
                    return null;
                }
            });
            Button delete = new Button("Delete", new Button.ClickListener() {

                @Override
                public void buttonClick(ClickEvent event) {
                    data.getEducationHistoryService().remove(educationHistory);
                    final PersonDetailsView view = new PersonDetailsView(p, main, "EDU");
                    main.mainView.setSecondComponent(view);

                }
            });

            edit.setStyleName(Reindeer.BUTTON_LINK);
            delete.setStyleName(Reindeer.BUTTON_LINK);





            addItem(new Object[]{
                        getDegreeName(educationHistory.getDegreeType()),
                        educationHistory.getMajor(),
                        educationHistory.getGraduation(),
                        educationHistory.getInstituteNamwe(),
                        getCountry(educationHistory.getLocation()),
                        edit,
                        delete},
                    educationHistory.getId());

        }
        // Allow selecting items from the table.
        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);


    }

    private Object getDegreeName(Degree degreeType) {
        if(degreeType!=null)
            return degreeType.getDegree_name();
        return null;
    }

    private Object getCountry(Country location) {
       if(location!=null)
           return location.getCountryName();
       return null;
    }
}
