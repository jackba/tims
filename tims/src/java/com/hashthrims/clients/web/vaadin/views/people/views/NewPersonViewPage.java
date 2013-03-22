/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.people.views;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.people.ManagePeopleMenuView;
import com.hashthrims.clients.web.vaadin.views.people.forms.NewPersonForm;
import com.hashthrims.clients.web.vaadin.views.people.models.NewPersonBean;
import com.hashthrims.clients.web.vaadin.views.people.tables.NewPersonTable;
import com.hashthrims.domain.MentorExpertiseArea;
import com.hashthrims.domain.Person;
import com.hashthrims.domain.PersonRoles;
import com.hashthrims.domain.employeelist.GenderList;
import com.hashthrims.domain.employeelist.RaceList;
import com.hashthrims.infrastructure.factories.PersonFactory;
import com.hashthrims.infrastructure.util.DataFieldsUtil;
import com.hashthrims.infrastructure.util.DateUtil;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Form;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window.Notification;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author boniface
 */
public class NewPersonViewPage extends VerticalLayout implements
        ClickListener, ValueChangeListener {

    private HashThrimsMain main;
    private NewPersonForm pform = new NewPersonForm();
    private Form form = pform.createNewPersonFrom();
    private static ClientDataService data = new ClientDataService();
    private NewPersonTable table;
    private DateUtil date = new DateUtil();
    private DataFieldsUtil fieldValues = new DataFieldsUtil();
    private Button tableButton = new Button();
    private final String SHOW_TABLE = "Show Table to Edit";
    private final String HIDE_TABLE = "Hide Table";

    public NewPersonViewPage(HashThrimsMain app) {


        main = app;
        setSizeFull();
        // Add Listeners
        pform.getSave().addListener((ClickListener) this);
        pform.getEdit().addListener((ClickListener) this);
        pform.getCancel().addListener((ClickListener) this);
        pform.getDelete().addListener((ClickListener) this);
        pform.getUpdate().addListener((ClickListener) this);
        tableButton.addListener((ClickListener) this);
        NewPersonBean bean = new NewPersonBean();
        BeanItem item = new BeanItem(bean);
        form.setItemDataSource(item);


        addComponent(form);
        setComponentAlignment(form, Alignment.TOP_CENTER);
        List<Person> persons = data.getPersonService().findAll();
        table = new NewPersonTable(main, persons);
        table.addListener((ValueChangeListener) this);
        tableButton.setSizeFull();
        tableButton.setCaption(SHOW_TABLE);
        addComponent(tableButton);

    }

    @Override
    public void valueChange(ValueChangeEvent event) {
        Property property = event.getProperty();

        if (property == table) {
            Item record = table.getItem(table.getValue());
            NewPersonBean bean = new NewPersonBean();
            Person p = data.getPersonService().find(new Long(table.getValue().toString()));
            bean.setId(p.getId());
            bean.setFirstname(p.getPersonName());
            bean.setSurname(p.getPersonSurname());
            bean.setOthername(p.getPersonOtherName());


            bean.setDob(p.getDemography().getDob());
            RaceList race = data.getRaceListService().getByPropertyName("raceName", p.getDemography().getRace());
            if (race != null) {
                bean.setRaceid(race.getId());
            }

            GenderList gender = data.getGenderListService().getByPropertyName("gender", p.getDemography().getGender());
            if (gender != null) {
                bean.setGenderId(gender.getId());
            }

            List<Long> exp = new ArrayList<Long>();
            List<MentorExpertiseArea> ea = p.getExpertiseArea();
            for (MentorExpertiseArea mentorExpertiseArea : ea) {
                exp.add(mentorExpertiseArea.getId());

            }

            bean.setExpertiseId(exp);

            List<Long> roles = new ArrayList<Long>();
            List<PersonRoles> rs = p.getPersonRoles();

            for (PersonRoles personRoles : rs) {
                roles.add(personRoles.getId());
            }

            bean.setRolesId(roles);




            if (bean != form.getItemDataSource()) {
                BeanItem item = new BeanItem(bean);
                form.setItemDataSource(item);
                // BUG enabling this Disables form.setVisibleItemProperties(cf.orderList());

                form.setReadOnly(true);
                //Buttons Behaviou
                pform.getSave().setVisible(false);
                pform.getEdit().setVisible(true);
                pform.getCancel().setVisible(true);
                pform.getDelete().setVisible(true);
                pform.getUpdate().setVisible(false);

                if ((p.getContacts().size() < 1 || p.getIdentities().size() < 1 || p.getPosition().size() < 1)) {
                    main.mainView.getWindow().showNotification("Caution", "The Person has some records for IDs, Contacts and Positions. These values will not update here. Please Update These values through Show details link. The rest of the Fields will update", Notification.TYPE_ERROR_MESSAGE);
                }
            }
        }
    }

    @Override
    public void buttonClick(ClickEvent event) {
        final Button source = event.getButton();
        if (source == pform.getSave()) {
            saveNewPerson(form);
            main.mainView.setSecondComponent(new ManagePeopleMenuView(main, "NEW"));
        } else if (source == pform.getEdit()) {
            form.setReadOnly(false);
            pform.getSave().setVisible(false);
            pform.getEdit().setVisible(false);
            pform.getCancel().setVisible(true);
            pform.getDelete().setVisible(false);
            pform.getUpdate().setVisible(true);
        } else if (source == pform.getCancel()) {

            main.mainView.setSecondComponent(new ManagePeopleMenuView(main, "NEW"));
        } else if (source == pform.getUpdate()) {
            saveEditedPerson(form);
            main.mainView.setSecondComponent(new ManagePeopleMenuView(main, "NEW"));

        } else if (source == pform.getDelete()) {
            deletePerson(form);
            main.mainView.setSecondComponent(new ManagePeopleMenuView(main, "NEW"));

        } else if (source == tableButton) {
            if (SHOW_TABLE.equals(tableButton.getCaption())) {
                tableButton.setCaption(HIDE_TABLE);
                addComponent(table);
            } else {
                tableButton.setCaption(SHOW_TABLE);
                removeComponent(table);
            }

        }
    }

    private void saveNewPerson(Form form) {
       
        Map<String, String> names = new HashMap<String, String>();
        Map<String, Collection<Long>> lists = new HashMap<String, Collection<Long>>();
        Map<String, Long> demo = new HashMap<String, Long>();
        Map<String, Date> dates = new HashMap<String, Date>();


        PersonFactory factory = data.getPersonFactory();
        String firstname = fieldValues.getStringFields(form.getField("firstname").getValue());
        String surname = fieldValues.getStringFields(form.getField("surname").getValue());
        String othername = fieldValues.getStringFields(form.getField("othername").getValue());

        String telephoneNumber = fieldValues.getStringFields(form.getField("telephoneNumber").getValue());
        String cellnumber = fieldValues.getStringFields(form.getField("cellnumber").getValue());
        String faxnumber = fieldValues.getStringFields(form.getField("faxnumber").getValue());
        String email = fieldValues.getStringFields(form.getField("email").getValue());
        String idType = fieldValues.getStringFields(form.getField("idType").getValue());
        String idValue = fieldValues.getStringFields(form.getField("idValue").getValue());

        names.put("firstname", firstname);
        names.put("surname", surname);
        names.put("othername", othername);
        names.put("telephoneNumber", telephoneNumber);
        names.put("cellnumber", cellnumber);
        names.put("faxnumber", faxnumber);
        names.put("email", email);
        names.put("idType", idType);
        names.put("idValue", idValue);


        Long genderId = fieldValues.getLongFields(form.getField("genderId").getValue());
        Long raceId = fieldValues.getLongFields(form.getField("raceid").getValue());
        Long positionId = fieldValues.getLongFields(form.getField("positionId").getValue());
        Long facilityId = fieldValues.getLongFields(form.getField("facilityId").getValue());
        demo.put("raceId", raceId);
        demo.put("genderId", genderId);
        demo.put("positionId", positionId);
        demo.put("facilityId", facilityId);

        Date dob = fieldValues.getDateFields(form.getField("dob").getValue());
        Date startDate = fieldValues.getDateFields(form.getField("startDate").getValue());
        dates.put("dob", dob);
        dates.put("startDate", startDate);


        Collection<Long> rolesid = fieldValues.getSelectListLongFields(form.getField("rolesId").getValue());
        Collection<Long> competencyFieldId = fieldValues.getSelectListLongFields(form.getField("competencyFieldId").getValue());
        Collection<Long> expertiseId = fieldValues.getSelectListLongFields(form.getField("expertiseId").getValue());

        lists.put("rolesid", rolesid);
        lists.put("competencyFieldId", competencyFieldId);
        lists.put("expertiseId", expertiseId);


        Person p = null;
        if (firstname != null || surname != null) {
            p = factory.createNewPerson(names, lists, demo, dates);
            data.getPersonService().persist(p);
            
        } else {
            throw new UnsupportedOperationException("First Name and Last Name needed");
        }


    }

    private void saveEditedPerson(Form form) {
        Map<String, String> names = new HashMap<String, String>();
        Map<String, Collection<Long>> lists = new HashMap<String, Collection<Long>>();
        Map<String, Long> demo = new HashMap<String, Long>();
        Map<String, Date> dates = new HashMap<String, Date>();


        PersonFactory factory = data.getPersonFactory();
        String firstname = fieldValues.getStringFields(form.getField("firstname").getValue());
        String surname = fieldValues.getStringFields(form.getField("surname").getValue());
        String othername = fieldValues.getStringFields(form.getField("othername").getValue());

        String telephoneNumber = fieldValues.getStringFields(form.getField("telephoneNumber").getValue());
        String cellnumber = fieldValues.getStringFields(form.getField("cellnumber").getValue());
        String faxnumber = fieldValues.getStringFields(form.getField("faxnumber").getValue());
        String email = fieldValues.getStringFields(form.getField("email").getValue());
        String idType = fieldValues.getStringFields(form.getField("idType").getValue());
        String idValue = fieldValues.getStringFields(form.getField("idValue").getValue());

        names.put("firstname", firstname);
        names.put("surname", surname);
        names.put("othername", othername);

        names.put("telephoneNumber", telephoneNumber);
        names.put("cellnumber", cellnumber);
        names.put("faxnumber", faxnumber);
        names.put("email", email);
        names.put("idType", idType);
        names.put("idValue", idValue);

        Long genderId = fieldValues.getLongFields(form.getField("genderId").getValue());
        Long raceId = fieldValues.getLongFields(form.getField("raceid").getValue());
        Long positionId = fieldValues.getLongFields(form.getField("positionId").getValue());
        Long facilityId = fieldValues.getLongFields(form.getField("facilityId").getValue());
        Long id = fieldValues.getLongFields(form.getField("id").getValue());
        demo.put("raceId", raceId);
        demo.put("genderId", genderId);
        demo.put("id", id);
        demo.put("positionId", positionId);
        demo.put("facilityId", facilityId);

        Date dob = fieldValues.getDbDateFields(form.getField("dob").getValue());
        Date startDate = fieldValues.getDateFields(form.getField("startDate").getValue());
        dates.put("dob", dob);
        dates.put("startDate", startDate);
        Collection<Long> rolesid = fieldValues.getSelectListLongFields(form.getField("rolesId").getValue());
        Collection<Long> competencyFieldId = fieldValues.getSelectListLongFields(form.getField("competencyFieldId").getValue());
        Collection<Long> expertiseId = fieldValues.getSelectListLongFields(form.getField("expertiseId").getValue());

        lists.put("rolesid", rolesid);
        lists.put("competencyFieldId", competencyFieldId);
        lists.put("expertiseId", expertiseId);


        Person p = null;
        if (firstname != null || surname != null) {
            p = factory.updateNewPerson(names, lists, demo, dates);
            data.getPersonService().merge(p);
        } else {
            throw new UnsupportedOperationException("First Name and Last Name needed");
        }


    }

    private void deletePerson(Form form) {
        PersonFactory factory = data.getPersonFactory();
        Long personId = Long.parseLong(form.getField("id").getValue().toString());
        Person p = factory.loadPerson(personId);
        data.getPersonService().remove(p);
    }
}
