/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.components;

import com.hashthrims.clients.web.vaadin.HashThrimsMain;
import com.hashthrims.clients.web.vaadin.views.reports.ReportsMenuView;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.Tree;
/**
 *
 * @author boniface
 */
public class GenerateReportsTreeMenu extends Tree implements ItemClickListener{
     private HashThrimsMain main;
    public static final Object MANAGE_PEOPLE = "Manage Staff";
    public static final Object SEARCH_RECORD = "Search Records";
    public static final Object VIEW_REPORTS = "View Reports";
    public static final Object CONFIGURE_SYSTEM = "Configure System";
    public static final Object ADD_USERS = "Add System Users";
    public static final Object ADD_COURSES = "Add Courses";
    public static final Object CHANGE_PASSWORD = "Change Password";
    public GenerateReportsTreeMenu(HashThrimsMain app) {
     this.main= app;
 
      addItem(SEARCH_RECORD);
      addItem(VIEW_REPORTS);
        /*
         * We want items to be selectable but do not want the user to be able to
         * de-select an item.
         */
        setSelectable(true);
        setNullSelectionAllowed(false);

        // Make application handle item click events
        addListener((ItemClickListener) this);
    }
   
 @Override
  public void itemClick(ItemClickEvent event) {

  Object itemId = event.getItemId();
   if (itemId!= null) {
      if (VIEW_REPORTS.equals(itemId)) {
                showPositionTabViewPage();
            }
//      else if(VIEW_REPORTS.equals(itemId)) {
//                showPositionTabViewPage();
//            }
      

        }


      }
private void showPositionTabViewPage() {
    ReportsMenuView w = new ReportsMenuView(main,"POSITION");
//    ,"POSITION"
        main.mainView.setSecondComponent(w);
    }
}


