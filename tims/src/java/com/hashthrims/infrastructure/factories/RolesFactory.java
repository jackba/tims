/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.infrastructure.factories;

import com.hashthrims.domain.Roles;
import com.hashthrims.domain.Users;
import com.hashthrims.infrastructure.conf.GetContext;
import com.hashthrims.services.RolesService;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author abismail
 */
public class RolesFactory {
private RolesService rolesService;


private ApplicationContext ctx = GetContext.getApplicationContext();

    public Roles createRoles(String description, String roleName, Users user){
        Roles c = new Roles();
        c.setRoleName(roleName);
       
       
        return c;
    }

    public Roles loadRoles(Long id) {
        rolesService = (RolesService) ctx.getBean("rolesService");
        Roles c = rolesService.find(id);
        return c;
    }
}
