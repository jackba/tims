/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.people.views.details.languages;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.people.views.PersonDetailsView;
import com.hashthrims.clients.web.vaadin.views.people.views.details.languages.form.LanguagesBean;
import com.hashthrims.clients.web.vaadin.views.people.views.details.languages.form.LanguagesForm;
import com.hashthrims.domain.EmployeeLanguages;
import com.hashthrims.domain.Person;
import com.hashthrims.domain.employeelist.Language;
import com.hashthrims.infrastructure.factories.LanguagesFactory;
import com.hashthrims.infrastructure.util.DataFieldsUtil;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Form;
import com.vaadin.ui.Window;
import java.util.HashMap;
import java.util.Map;


/**
 *
 * @author boniface
 */
public class LanguagesWindow extends Window implements ClickListener {

    private final HashThrimsMain main;
    private final Person person;
    private static final ClientDataService data = new ClientDataService();
    private final DataFieldsUtil fieldValues = new DataFieldsUtil();
    private final Form formData;
    private final LanguagesForm pform = new LanguagesForm();
    LanguagesFactory factory = data.getLanguagesFactory();

    public LanguagesWindow(Person p, LanguagesBean bean, HashThrimsMain app) {
        main = app;
        person = p;



        final Form form = pform.createLanguagesFrom();
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
        setCaption("Add or Edit Languages");
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
            updateLanguages(formData);
            final Person p = data.getPersonService().find(person.getId());
            final PersonDetailsView view = new PersonDetailsView(p, main,"LANG");
            main.mainView.setSecondComponent(view);
            close();

        } else if (source == pform.getSave()) {
            saveLanguages(formData);
            final PersonDetailsView view = new PersonDetailsView(person, main,"LANG");
            main.mainView.setSecondComponent(view);
            close();

        }

    }



    private void updateLanguages(Form formData) {
        final Long id = Long.parseLong(formData.getField("id").getValue().toString());
        final Long language = Long.parseLong(formData.getField("language").getValue().toString());
        final String speaking = fieldValues.getStringFields(formData.getField("speaking").getValue());
        final String reading = fieldValues.getStringFields(formData.getField("reading").getValue());
        final String writing = fieldValues.getStringFields(formData.getField("writing").getValue());
        final Language  lang = data.getLanguageService().find(language);
        Map<String,String> vals = new HashMap<String,String>();
        vals.put("speaking", speaking);
        vals.put("reading", reading);
        vals.put("writing", writing);
        EmployeeLanguages employeeLanguage  = factory.updateLanguages(lang, vals,id);
        //person.getLanguages().add(employeeLanguage);
        data.getEmployeeLanguagesService().merge(employeeLanguage);
    }

    private void saveLanguages(Form formData) {
        final Long language = Long.parseLong(formData.getField("language").getValue().toString());
        final String speaking = fieldValues.getStringFields(formData.getField("speaking").getValue());
        final String reading = fieldValues.getStringFields(formData.getField("reading").getValue());
        final String writing = fieldValues.getStringFields(formData.getField("writing").getValue());
        final Language  lang = data.getLanguageService().find(language);
        Map<String,String> vals = new HashMap<String,String>();
        vals.put("speaking", speaking);
        vals.put("reading", reading);
        vals.put("writing", writing);
        EmployeeLanguages employeeLanguage  = factory.createLanguages(lang, vals);
        person.getLanguages().add(employeeLanguage);
        data.getPersonService().merge(person);
    }
}
