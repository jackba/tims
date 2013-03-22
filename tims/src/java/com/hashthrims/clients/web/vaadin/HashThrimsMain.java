/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin;

import com.hashthrims.clients.web.vaadin.components.Header;
import com.hashthrims.clients.web.vaadin.components.TabAccordian;
import com.hashthrims.clients.web.vaadin.views.LandingPage;
import com.hashthrims.infrastructure.conf.GetContext;
import com.vaadin.Application;
import com.vaadin.terminal.Sizeable;
import com.vaadin.terminal.gwt.server.HttpServletRequestListener;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author boniface
 */
public class HashThrimsMain extends Application implements HttpServletRequestListener {
    

    public HorizontalSplitPanel mainView = new HorizontalSplitPanel();
    private static ThreadLocal<HashThrimsMain> threadLocal = new ThreadLocal<HashThrimsMain>();
    final VerticalLayout rootLayout = new VerticalLayout();
    final Window root = new Window("Training Human Resource Information Systems", rootLayout);
    @Autowired
    private AuthenticationManager authenticationManager;
    private ApplicationContext ctx;
    private Authentication auth;

    @Override
    public void init() {
        setInstance(this);
        setTheme("reindeer");
        setMainWindow(new LoginWindow());
    }

    public static HashThrimsMain getInstance() {
        return threadLocal.get();
    }

    // Set the current application instance 	
    public static void setInstance(HashThrimsMain application) {
        if (getInstance() == null) {
            threadLocal.set(application);
        }
    }

    @Override
    public void onRequestStart(HttpServletRequest request, HttpServletResponse response) {
        HashThrimsMain.setInstance(this);

    }

    @Override
    public void onRequestEnd(HttpServletRequest request, HttpServletResponse response) {
        threadLocal.remove();
    }

    public Authentication authenticate(String login, String password) throws Exception {

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(login, password);
        ctx = GetContext.getApplicationContext();
        authenticationManager = (AuthenticationManager) ctx.getBean("authenticationManager");

        setAuth(authenticationManager.authenticate(token));

        if (getAuth() != null) {
            SecurityContextHolder.getContext().setAuthentication(getAuth());
            loadProtectedResources();
            return getAuth();
        }

        throw new Exception("failed to Login");
    }

    private  void loadProtectedResources() {

        final TabAccordian accordian = new TabAccordian(this);
        final LandingPage dashBoard = new LandingPage(this);
        // Add the 
        rootLayout.setSizeFull();
        rootLayout.setMargin(false);
        Header header = new Header(this);
        root.addComponent(header.getHeader());
        mainView.setSplitPosition(200, Sizeable.UNITS_PIXELS);
        mainView.setFirstComponent(accordian);
        mainView.setSecondComponent(dashBoard);
        root.addComponent(mainView);
        ((VerticalLayout) root.getContent()).setExpandRatio(mainView, 1.0F);
        setMainWindow(root);
    }

    /**
     * @return the auth
     */
    public Authentication getAuth() {
        return auth;
    }

    /**
     * @param auth the auth to set
     */
    public void setAuth(Authentication auth) {
        this.auth = auth;
    }
}
