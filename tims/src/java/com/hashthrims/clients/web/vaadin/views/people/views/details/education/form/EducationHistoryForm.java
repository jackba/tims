/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.people.views.details.education.form;

import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.domain.employeelist.Degree;
import com.hashthrims.domain.regionlist.Country;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.terminal.Sizeable;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Select;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.Runo;
import java.util.List;

/**
 *
 * @author boniface
 */
public class EducationHistoryForm {

    // Define Buttons
    private Button save = new Button("Save");
    private Button cancel = new Button("Cancel");
    private Button update = new Button("Update");
    //Define Footer
    private HorizontalLayout footer;
    private static ClientDataService data = new ClientDataService();

    public EducationHistoryForm() {
    }

    public Form createEducationHistoryFrom() {
        Form form = new EducationHistoryGridForm();
        form.setImmediate(false);
        form.setFormFieldFactory(new EducationHistoryFieldFactory());


        // Add Listeners

        footer = new HorizontalLayout();
        footer.setSpacing(true);
        footer.addComponent(save);
        footer.addComponent(update);
        footer.addComponent(cancel);


        footer.setVisible(true);
        footer.setMargin(true);
        // Determines which properties are shown, and in which order:

        update.setVisible(false);
        save.setVisible(false);
        form.setWriteThrough(false);
        form.setFooter(footer);

        return form;
    }

    class EducationHistoryFieldFactory extends DefaultFieldFactory implements Property.ValueChangeListener {

        private Select selectCountryList;
        private Select selectInstitutionList;
        private Select selectDegreeTypeList;

        @Override
        public Field createField(Item item, Object propertyId,
                Component uiContext) {

            Field field = super.createField(item, propertyId, uiContext);
           
            if ("id".equals(propertyId)) {
                field = new TextField("Education History ID:");
                ((TextField) field).setVisible(false);
            } else if ("instituteName".equals(propertyId)) {
                field = new TextField("Institution Name:");
                ((TextField) field).setColumns(20);
                ((TextField) field).setNullRepresentation("");
                ((TextField) field).setRequired(true);
                ((TextField) field).setRequiredError("Please Enter Value");
            } else if ("country".equals(propertyId)) {
                List<Country> countries = data.getCountryService().findAll();
                selectCountryList = new Select("Country:");
                for (Country country : countries) {
                    selectCountryList.addItem(country.getId());
                    selectCountryList.setItemCaption(country.getId(), country.getCountryName());
                }
                selectCountryList.setNewItemsAllowed(false);
                selectCountryList.addListener(this);
                selectCountryList.setImmediate(true);
                selectCountryList.setWidth("250");
                selectCountryList.setRequired(true);
                return selectCountryList;
            } else if ("degreeType".equals(propertyId)) {
                List<Degree> degrees = data.getDegreeName().findAll();
                selectDegreeTypeList = new Select("Degree Type:");
                for (Degree degree : degrees) {
                    selectDegreeTypeList.addItem(degree.getId());
                    selectDegreeTypeList.setItemCaption(degree.getId(), degree.getDegree_name());
                }
                selectDegreeTypeList.setNewItemsAllowed(false);
                selectDegreeTypeList.addListener(this);
                selectDegreeTypeList.setImmediate(true);
                selectDegreeTypeList.setWidth("250");
                selectDegreeTypeList.setRequired(true);
                return selectDegreeTypeList;
            } else if ("major".equals(propertyId)) {
                field = new TextField("Major:");
                ((TextField) field).setColumns(20);
                ((TextField) field).setNullRepresentation("");
                ((TextField) field).setRequired(true);
                ((TextField) field).setRequiredError("Please Enter Value");
            } else if ("graduation".equals(propertyId)) {
                  field = new DateField("Graduation Date:");
                ((DateField) field).setRequired(true);
                ((DateField) field).setRequiredError("Please Enter Value");
                ((DateField) field).setWidth(250, Sizeable.UNITS_PIXELS);
            }
            return field;

        }

        @Override
        public void valueChange(ValueChangeEvent event) {
            Property property = event.getProperty();

        }
    }

    class EducationHistoryGridForm extends Form {

        private GridLayout layout;

        public EducationHistoryGridForm() {
            setCaption("Education History Form");
            layout = new GridLayout(2, 4);
            layout.setMargin(true);
            layout.setSpacing(true);

            //layout.setSizeFull();
            //layout.setStyleName(Runo.LAYOUT_DARKER);
            layout.setStyleName(Runo.CSSLAYOUT_SHADOW);
            HorizontalLayout firstHeader = new HorizontalLayout();
            firstHeader.setSizeFull();
            firstHeader.setStyleName(Runo.LAYOUT_DARKER);
            Label firstHeaderlabel = new Label("Education History Information");
            firstHeaderlabel.setStyleName(Runo.LABEL_H2);
            firstHeader.addComponent(firstHeaderlabel);
            layout.addComponent(firstHeader, 0, 0, 1, 0);
            layout.setComponentAlignment(firstHeader, Alignment.MIDDLE_CENTER);

            setLayout(layout);

        }

        @Override
        protected void attachField(Object propertyId, Field field) {

            if (propertyId.equals("instituteName")) {
                layout.addComponent(field, 0, 1);
            } else if (propertyId.equals("country")) {
                layout.addComponent(field, 1, 1);
            } else if (propertyId.equals("graduation")) {
                layout.addComponent(field, 0, 3);
            } else if (propertyId.equals("degreeType")) {
                layout.addComponent(field, 1, 2);
            } else if (propertyId.equals("major")) {
                layout.addComponent(field, 0, 2);
            } else if (propertyId.equals("id")) {
                layout.addComponent(field, 1, 3);
            }

        }
    }

    /**
     * @return the save
     */
    public Button getSave() {
        return save;
    }

    /**
     * @param save the save to set
     */
    public void setSave(Button save) {
        this.save = save;
    }

    /**
     * @return the cancel
     */
    public Button getCancel() {
        return cancel;
    }

    /**
     * @param cancel the cancel to set
     */
    public void setCancel(Button cancel) {
        this.cancel = cancel;
    }

    /**
     * @return the update
     */
    public Button getUpdate() {
        return update;
    }

    /**
     * @param update the update to set
     */
    public void setUpdate(Button update) {
        this.update = update;
    }

    /**
     * @param footer the footer to set
     */
    public void setFooter(HorizontalLayout footer) {
        this.footer = footer;
    }
}
