/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.managementoring.forms;

import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.domain.Person;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.TwinColSelect;
import com.vaadin.ui.themes.Runo;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author boniface
 */
public class EnrollMenteesForm {
    private static ClientDataService data = new ClientDataService();
    private Collection<Long> mentees;
    public EnrollMenteesForm() {
    }
    public Form createEnrollMenteesForm() {
        Form form = new EnrollMenteesGridForm();
        form.setImmediate(false);
        form.setFormFieldFactory(new EnrollMenteesFieldFactory());
        return form;
    }

    /**
     * @return the mentees
     */
    public Collection<Long> getMentees() {
        return mentees;
    }

    /**
     * @param mentees the mentees to set
     */
    public void setMentees(Collection<Long> mentees) {
        this.mentees = mentees;
    }
    class EnrollMenteesFieldFactory extends DefaultFieldFactory implements Property.ValueChangeListener {
        private TwinColSelect selectCourse = new TwinColSelect();
        @Override
        public Field createField(Item item, Object propertyId,
                Component uiContext) {
            Field field = super.createField(item, propertyId, uiContext);
            if ("id".equals(propertyId)) {
                field = new TextField("Short Course ID:");
                ((TextField) field).setVisible(false);
            } else if ("menteesId".equals(propertyId)) {
                List<Person> people = data.getPersonService().findAll();
                Collections.sort(people);
                getSelectCourse().addListener((ValueChangeListener) this);
                getSelectCourse().setImmediate(true);
                getSelectCourse().setLeftColumnCaption("Mantees For Enrollment");
                getSelectCourse().setRightColumnCaption("Enrolled Mentees");
                for (Person person : people) {
                    getSelectCourse().addItem(person.getId());
                    getSelectCourse().setItemCaption(person.getId(), person.getPersonSurname() + " " + person.getPersonName());
                    
                }
                getSelectCourse().setNewItemsAllowed(false);
                
                getSelectCourse().setSizeFull();
                // selectCourse.setRequired(true);
                return getSelectCourse();
            }
            return field;

        }
        @Override
        public void valueChange(ValueChangeEvent event) {
             final Property property = event.getProperty();
             if (property==getSelectCourse()) {
                 mentees=(Collection<Long>)property.getValue();
            }
            
           
        }

        /**
         * @return the selectCourse
         */
        public TwinColSelect getSelectCourse() {
            return selectCourse;
        }

        /**
         * @param selectCourse the selectCourse to set
         */
        public void setSelectCourse(TwinColSelect selectCourse) {
            this.selectCourse = selectCourse;
        }
    }
    class EnrollMenteesGridForm extends Form {
        private GridLayout layout;
        public EnrollMenteesGridForm() {
            setSizeFull();
            layout = new GridLayout(1, 4);
            layout.setSizeFull();
            layout.setMargin(true);
            layout.setSpacing(true);
            layout.setStyleName(Runo.CSSLAYOUT_SHADOW);
            setLayout(layout);
        }
        @Override
        protected void attachField(Object propertyId, Field field) {
            if (propertyId.equals("menteesId")) {
                layout.addComponent(field, 0, 0);
            } else if (propertyId.equals("id")) {
                layout.addComponent(field, 0, 1);

            }
        }
    }
}
