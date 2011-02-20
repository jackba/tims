/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.clients.web.spring.controllers;

import java.util.Date;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.ModelAndView;


/**
 *
 * @author boniface
 */
@Controller
public class IndexController {

    @RequestMapping("/index")
    public ModelAndView index() {
        System.out.println("THIS CONTROLLER IS CALLED HERRERERER !!!!!");
        String message = "Hello World, Spring 3.0! THIS IS A TEST MESSAGE";
        String now = (new Date()).toString();

        return new ModelAndView("WEB-INF/pages/index.jsp", "now", now);
    }
}
