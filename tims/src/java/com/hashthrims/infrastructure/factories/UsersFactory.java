/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.infrastructure.factories;

import com.hashthrims.domain.Roles;
import com.hashthrims.domain.Users;
import com.hashthrims.infrastructure.conf.GetContext;
import com.hashthrims.services.UsersService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author abismail
 */
public class UsersFactory {
private UsersService usersService;


private ApplicationContext ctx = GetContext.getApplicationContext();

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





    public Users loadUser(Long id) {
        usersService = (UsersService) ctx.getBean("usersService");
        Users c = usersService.find(id);
        return c;
    }

    public Users createNewUser(Map<String, String> userValues, List<String> userRoles, boolean activitate) {
        final Users user = new Users();
        final List<Roles> roles = new ArrayList<Roles>();
        user.setRoles(roles);
       
        user.setEmail(userValues.get("email"));
        user.setEnabled(activitate);
        user.setFirstname(userValues.get("firstname"));
        user.setMiddlename(userValues.get("middlename"));
        user.setLastname(userValues.get("lastname"));
        user.setPasswd(userValues.get("passwd"));
       
        for (String rol : userRoles) {
            final Roles role = new Roles();
            role.setRoleName(rol);
            role.setEmail(user.getEmail());
            user.getRoles().add(role);
        }
        
        return user;
    }

    public Users updateUser(Map<String, String> userValues, List<String> userRoles, boolean activitate, Long id) {
        final Users user = loadUser(id);
        final Roles role = new Roles();
        user.setEmail(userValues.get("email"));
        user.setEnabled(activitate);
        user.setFirstname(userValues.get("firstname"));
        user.setMiddlename(userValues.get("middlename"));
        user.setLastname(userValues.get("lastname"));
        role.setEmail(user.getEmail());
        List<Roles> roles = user.getRoles();
        roles.clear();

        for (String rol : userRoles) {
            role.setRoleName(rol);
             user.getRoles().add(role);
        }

        return user;
    }

}