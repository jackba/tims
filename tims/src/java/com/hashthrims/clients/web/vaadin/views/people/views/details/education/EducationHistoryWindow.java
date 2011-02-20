/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.people.views.details.education;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.people.views.PersonDetailsView;
import com.hashthrims.clients.web.vaadin.views.people.views.details.education.form.EducationHistoryBean;
import com.hashthrims.clients.web.vaadin.views.people.views.details.education.form.EducationHistoryForm;
import com.hashthrims.domain.EducationHistory;
import com.hashthrims.domain.Person;
import com.hashthrims.infrastructure.factories.employeelist.EducationsFactory;
import com.hashthrims.infrastructure.util.DataFieldsUtil;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Form;
import com.vaadin.ui.Window;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author boniface
 */
public class EducationHistoryWindow extends Window implements ClickListener {

    private final HashThrimsMain main;
    private final Person person;
    private static final ClientDataService data = new ClientDataService();
    private final DataFieldsUtil fieldValues = new DataFieldsUtil();
    private final Form formData;
    private final EducationHistoryForm pform = new EducationHistoryForm();
    EducationsFactory factory = data.getEducationsFactory();

    public EducationHistoryWindow(Person p, EducationHistoryBean bean, HashThrimsMain app) {
        main = app;
        person = p;



        final Form form = pform.createEducationHistoryFrom();
        final BeanItem item = new BeanItem(bean);

        pform.getSave().addListener((ClickListener) this);
        pform.getUpdate().addListener((ClickListener) this);
        pform.getCancel().addListener((ClickListener) this);
        form.setItemDataSource(item);
        formData = form;
        setModal(true);
        setClosable(true);
        setHeight("450px");
        setWidth("600px");
        setPositionX(200);
        setPositionY(100);
        setCaption("Add or Edit EducationHistory");
        if (bean.getId() != null) {
            pform.getUpdate().setVisible(true);
        } else {
            pform.getSave().setVisible(true);
        }
        addComponent(form);
    }

    @Override
    public void buttonClick(ClickEvent event) {
        final Button source = event.getButton();

        if (source == pform.getCancel()) {
            close();
        } else if (source == pform.getUpdate()) {
            updateEducationHistory(formData);
            final Person p = data.getPersonService().find(person.getId());
            final PersonDetailsView view = new PersonDetailsView(p, main, "EDU");
            main.mainView.setSecondComponent(view);
            close();

        } else if (source == pform.getSave()) {
            saveEducationHistory(formData);
            final PersonDetailsView view = new PersonDetailsView(person, main, "EDU");
            main.mainView.setSecondComponent(view);
            close();

        }

    }


    private void updateEducationHistory(Form formData) {
        final Long id = Long.parseLong(formData.getField("id").getValue().toString());
        final Long country = Long.parseLong(formData.getField("country").getValue().toString());
        final String instituteName = fieldValues.getStringFields(formData.getField("instituteName").getValue());
        final String major = fieldValues.getStringFields(formData.getField("major").getValue());
        final Date graduationDate = fieldValues.getDateFields(formData.getField("graduation").getValue());
        final Long degreeType = Long.parseLong(formData.getField("degreeType").getValue().toString());
        Map<String, String> vals = new HashMap<String, String>();
        vals.put("instituteName", instituteName);
        vals.put("major", major);
        EducationHistory educationHistory = factory.updateEducationHistory(country, vals,graduationDate,degreeType,id );

        data.getEducationHistoryService().merge(educationHistory);
    }



    private void saveEducationHistory(Form formData) {
        final Long country = Long.parseLong(formData.getField("country").getValue().toString());
        final String instituteName = fieldValues.getStringFields(formData.getField("instituteName").getValue());
        final String major = fieldValues.getStringFields(formData.getField("major").getValue());
        final Date graduationDate = fieldValues.getDateFields(formData.getField("graduation").getValue());
        final Long degreeType = Long.parseLong(formData.getField("degreeType").getValue().toString());
        Map<String, String> vals = new HashMap<String, String>();
        vals.put("instituteName", instituteName);
        vals.put("major", major);
        EducationHistory educationHistory = factory.createEducationHistory(country, vals,graduationDate,degreeType );
        person.getEducationHistory().add(educationHistory);
        data.getPersonService().merge(person);
    }
}
