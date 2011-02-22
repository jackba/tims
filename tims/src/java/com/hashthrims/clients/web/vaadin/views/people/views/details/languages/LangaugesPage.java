/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.people.views.details.languages;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.components.ButtonLink;
import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.people.views.PersonDetailsView;
import com.hashthrims.clients.web.vaadin.views.people.views.details.languages.form.LanguagesBean;
import com.hashthrims.domain.EmployeeLanguages;
import com.hashthrims.domain.Person;
import com.hashthrims.infrastructure.util.TimsUtil;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author boniface
 */
public class LangaugesPage extends VerticalLayout implements ClickListener, ValueChangeListener {

    private final HashThrimsMain main;
    private final Button identityLink = ButtonLink.getButton("Add Language");
    private LanguagesWindow subwindow;
    private final ClientDataService data = new ClientDataService();
    private final TimsUtil st = new TimsUtil();

    public LangaugesPage(final Person person, HashThrimsMain app) {
        main = app;
        setSizeFull();

        //register Listeners
        identityLink.addListener((ClickListener) this);


        LanguagesBean bean = new LanguagesBean();
        subwindow = new LanguagesWindow(person, bean, main);

        final Label headerLabel = new Label("Languages :  ");
        headerLabel.addStyleName(Reindeer.LABEL_H2);
        final HorizontalLayout menu = new HorizontalLayout();
        menu.addComponent(headerLabel);


        menu.addComponent(identityLink);
        menu.setSpacing(true);

        Table table = new Table();
        table.removeAllItems();
        table.setSizeFull();


        List<EmployeeLanguages> list = null;
        if (person.getLanguages()!=null) {
            list = person.getLanguages();
        } else {
            list=new ArrayList<EmployeeLanguages>();
        }

        table.addContainerProperty("Language", String.class, null);
        table.addContainerProperty("Writing", String.class, null);
        table.addContainerProperty("Reading", String.class, null);
        table.addContainerProperty("Speaking", String.class, null);
        table.addContainerProperty("Edits", Button.class, null);
        table.addContainerProperty("Deletes", Button.class, null);


        table.setNullSelectionAllowed(false);
        table.setSelectable(true);
        table.setImmediate(true);


        for (final EmployeeLanguages values : list) {

            Button edit = new Button("edit", new Button.ClickListener() {

                @Override
                public void buttonClick(ClickEvent event) {
                    LanguagesBean bean = getBean();
                    subwindow = new LanguagesWindow(person, bean, main);
                    main.getMainWindow().addWindow(subwindow);

                }

                private LanguagesBean getBean() {
                    LanguagesBean bean = new LanguagesBean();
                    bean.setLanguage(st.getlanguageId(values.getLanguage()));
                    bean.setReading(values.getReading());
                    bean.setSpeaking(values.getSpeaking());
                    bean.setWriting(values.getWriting());
                    bean.setId(values.getId());
                    return bean;
                }
            });
            Button delete = new Button("Delete", new Button.ClickListener() {

                @Override
                public void buttonClick(ClickEvent event) {
                    person.getLanguages().remove(values);
                    data.getPersonService().merge(person);
                    final PersonDetailsView view = new PersonDetailsView(person, main, "LANG");
                    main.mainView.setSecondComponent(view);

                }
            });

            edit.setStyleName(Reindeer.BUTTON_LINK);
            delete.setStyleName(Reindeer.BUTTON_LINK);




            table.addItem(new Object[]{
                        st.getLanguageName(values.getLanguage()),
                        values.getWriting(),
                        values.getReading(),
                        values.getSpeaking(),
                        edit,
                        delete}, values.getId());

        }

        addComponent(menu);
        addComponent(table);
    }

    @Override
    public void buttonClick(ClickEvent event) {
        final Button source = event.getButton();
        if (source == identityLink) {
            if (subwindow.getParent() != null) {
                // window is already showing
                main.getMainWindow().showNotification(
                        "Window is already open");
            } else {
                // Open the subwindow by adding it to the parent
                // window
                main.getMainWindow().addWindow(subwindow);
            }
        }
    }

    @Override
    public void valueChange(ValueChangeEvent event) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
