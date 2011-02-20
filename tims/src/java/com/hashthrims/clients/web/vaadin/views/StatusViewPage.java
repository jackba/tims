/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.models.StatusBean;
import com.hashthrims.domain.positions.Status;
import com.hashthrims.infrastructure.factories.StatusFactory;
import com.vaadin.data.Item;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanItem;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author boniface
 */
public class StatusViewPage extends VerticalLayout implements
        ClickListener, ValueChangeListener, ItemClickListener {

    private Button save = new Button("Save");
    private Button edit = new Button("Edit");
    private Button cancel = new Button("Cancel");
    
    private HashThrimsMain main;
    private Form form;
    private HorizontalLayout footer;
    private static ClientDataService data = new ClientDataService();
    Panel p = new Panel(" View Pages ");

    public StatusViewPage(HashThrimsMain app) {
        main = app;
        setSizeFull();
        form = new Form();
        form.setImmediate(true);
        form.setCaption("Status ");
        StatusBean bean = new StatusBean();

        form.setFormFieldFactory(new StatusFieldFactory());
        BeanItem item = new BeanItem(bean);
        form.setItemDataSource(item);

        // Add Listeners
        save.addListener((ClickListener) this);


        footer = new HorizontalLayout();
        footer.setSpacing(true);
        footer.addComponent(save);
        footer.addComponent(cancel);
        footer.addComponent(edit);
        footer.setVisible(true);
        footer.setMargin(true);

        // Determines which properties are shown, and in which order:
        List order = new ArrayList();
        order.add("status");
        



        form.setVisibleItemProperties(order);
        form.setWriteThrough(false);
        form.setFooter(footer);
        addComponent(form);
        setComponentAlignment(form, Alignment.TOP_CENTER);
        

    }

    static class StatusFieldFactory extends DefaultFieldFactory {

        @Override
        public Field createField(Item item, Object propertyId,
                Component uiContext) {

            Field field = super.createField(item, propertyId, uiContext);


            if ("status".equals(propertyId)) {
                field = new TextField("Status:");
                ((TextField) field).setColumns(30);
                ((TextField) field).setNullRepresentation("");
                ((TextField) field).setRequired(true);
                ((TextField) field).setRequiredError("Please Enter Status");
            } 

            return field;
        }
    }

    @Override
    public void valueChange(ValueChangeEvent event) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void buttonClick(ClickEvent event) {
        final Button source = event.getButton();

        if (source == save) {
            saveNewStatus(form);
            main.mainView.setSecondComponent(new StatusViewPage(main));
        } else if (source == edit) {
            //showProvinceViewPage();
        } else if (source == cancel) {
            main.mainView.setSecondComponent(new StatusViewPage(main));
        }
    }

    @Override
    public void itemClick(ItemClickEvent event) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void saveNewStatus(Form form) {
        
        StatusFactory factory = data.getStatusFactory();
        String status = form.getField("status").getValue().toString();

        Status s = factory.createStatus(status);
        data.getStatusService().persist(s);
       

        

    }

    public void saveEditedLanguage(Form form) {
    }
}
