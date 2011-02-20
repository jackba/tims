/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.people.views.details.identitification;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.people.views.PersonDetailsView;
import com.hashthrims.clients.web.vaadin.views.people.views.details.identitification.form.IdentitiesBean;
import com.hashthrims.clients.web.vaadin.views.people.views.details.identitification.form.IdentitiesForm;
import com.hashthrims.domain.Identities;
import com.hashthrims.domain.Person;
import com.hashthrims.infrastructure.util.DataFieldsUtil;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Form;
import com.vaadin.ui.Window;
import java.util.List;

/**
 *
 * @author boniface
 */
public class IdentitiesWindow extends Window implements ClickListener {

    private final HashThrimsMain main;
    private final Person person;
    private static final ClientDataService data = new ClientDataService();
    private final DataFieldsUtil fieldValues = new DataFieldsUtil();
    private final Form formData;
    private final IdentitiesForm pform = new IdentitiesForm();

    public IdentitiesWindow(Person p, IdentitiesBean bean, HashThrimsMain app) {
        main = app;
        person = p;



        final Form form = pform.createIndentitiesFrom();
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
        setCaption("Add or Edit Identities");
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
            updateIdentity(formData);
            final PersonDetailsView view = new PersonDetailsView(person, main,"IDS");
            main.mainView.setSecondComponent(view);
            close();

        } else if (source == pform.getSave()) {
            saveIdentity(formData);
            final PersonDetailsView view = new PersonDetailsView(person, main,"IDS");
            main.mainView.setSecondComponent(view);
            close();

        }

    }

    private void updateIdentity(Form formData) {
        final Long id = Long.parseLong(formData.getField("id").getValue().toString());
        final String idType = fieldValues.getStringFields(formData.getField("idType").getValue());
        final String idValue = fieldValues.getStringFields(formData.getField("idValue").getValue());
        final Identities i = data.getIdentitiesService().find(id);
        i.setIdType(idType);
        i.setIdValue(idValue);
        person.getIdentities().add(i);
        data.getPersonService().merge(person);

    }

    private void saveIdentity(Form formData) {
        final String idType = fieldValues.getStringFields(formData.getField("idType").getValue());
        final String idValue = fieldValues.getStringFields(formData.getField("idValue").getValue());
        final Identities id = new Identities();
        id.setIdType(idType);
        id.setIdValue(idValue);
        person.getIdentities().add(id);
        data.getPersonService().merge(person);
    }
}
