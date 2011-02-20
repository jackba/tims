/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.location.tables;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;

import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.SelectedTabChangeEvent;
import com.vaadin.ui.TabSheet.Tab;
import com.vaadin.ui.VerticalLayout;




/**
 *
 * @author bulelani
 */
public class LocationViewPage extends VerticalLayout implements
       TabSheet.SelectedTabChangeListener  {

    private HashThrimsMain main;
    private Form form;
    private HorizontalLayout footer;
    Panel p = new Panel(" View Pages ");

     private TabSheet t;

    public LocationViewPage(HashThrimsMain app) {
//        main = app;
//        setSizeFull();
//        form = new Form();
//        form.setImmediate(true);
//
//        footer = new HorizontalLayout();
//        footer.setSpacing(true);
//
//        footer.setVisible(true);
//        footer.setMargin(true);
//
//        form.setWriteThrough(false);
//        form.setFooter(footer);
//        addComponent(form);
//        setComponentAlignment(form, Alignment.TOP_CENTER);
//
//        // Tab 1 content
//        VerticalLayout l1 = new VerticalLayout();
//        l1.setMargin(true);
//       // l1.addComponent(form);
//        l1.addComponent(new CountryViewPage(main));


//        // Tab 2 content
//        VerticalLayout l2 = new VerticalLayout();
//        l2.setMargin(true);
//        l2.addComponent(new ProvinceViewPage(main));
//        // Tab 3 content
//        VerticalLayout l3 = new VerticalLayout();
//        l3.setMargin(true);
//        l3.addComponent(new CityViewPage());
//
//        VerticalLayout l4 = new VerticalLayout();
//        l4.setMargin(true);
//
//        l4.addComponent(new CountyViewPage());

//        VerticalLayout l5 = new VerticalLayout();
//        l5.setMargin(true);
//        l5.addComponent(new LanguageViewPage(main));
//        l5.addComponent(new LanguagesViewPage(main));

      


        t = new TabSheet();
        t.setHeight("100%");
        t.setWidth("100%");


       // t.addTab(l1, "Country", null);
//        t.addTab(l2, "Province", null);
//        t.addTab(l3, "City ", null);
//        t.addTab(l4, "County", null);
////        t.addTab(l5, "Region", null);
       
        t.addListener(this);
        addComponent(t);

       

    }

    @Override
    public void selectedTabChange(SelectedTabChangeEvent event) {
        TabSheet tabsheet = event.getTabSheet();
        Tab tab = tabsheet.getTab(tabsheet.getSelectedTab());


    }

}
