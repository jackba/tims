/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.infrastructure.factories;

import com.hashthrims.domain.Roles;
import com.hashthrims.domain.Users;
import com.hashthrims.infrastructure.conf.GetContext;
import com.hashthrims.services.UsersService;
import java.util.List;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author abismail
 */
public class UsersFactory {
private UsersService usersService;


private ApplicationContext ctx = GetContext.getApplicationContext();

    public Users createUsers(boolean enabled, String firstName, String lastName, String middleName, String password, List<Roles> roles, String userName){
        usersService = (UsersService) ctx.getBean("usersService");
        Users c = new Users();
        c.setEnabled(enabled);
        c.setFirstname(firstName);
        c.setLastname(lastName);
        c.setMiddlename(middleName);
        c.setPasswd(password);
        c.setRoles(roles);
       
        usersService.persist(c);
        return c;
    }

    public Users updateUsers(boolean enabled, String firstName, String lastName, String middleName, String password, List<Roles> roles, String userName, Long id){
        usersService = (UsersService) ctx.getBean("usersService");
        Users c = usersService.find(id);
        c.setEnabled(enabled);
        c.setFirstname(firstName);
        c.setLastname(lastName);
        c.setMiddlename(middleName);
        c.setPasswd(password);
        c.setRoles(roles);
       
        usersService.merge(c);
        return c;
    }

    public Roles createRoles(String description, String roleName, Users user){
        Roles r = new Roles();
       
        r.setRoleName(roleName);
        r.setUser(user);
       
        return r;
    }

    public Roles updateRoles(String description, String roleName, Users user, Long id){
        usersService = (UsersService) ctx.getBean("usersService");
        Roles r = (Roles)usersService.find(id).getRoles();
        
        r.setRoleName(roleName);
        r.setUser(user);
       
        usersService.find(id).setRoles((List<Roles>) r);
        return r;
    }

    public Users loadUsers(Long id) {
        usersService = (UsersService) ctx.getBean("usersService");
        Users c = usersService.find(id);
        return c;
    }
}