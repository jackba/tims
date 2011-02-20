/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.infrastructure.factories;

import com.hashthrims.domain.Identities;
import com.hashthrims.infrastructure.conf.GetContext;
import com.hashthrims.services.IdentitiesService;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author abismail
 */
public class IdentitiesFactory {
  private ApplicationContext ctx = GetContext.getApplicationContext();
    private IdentitiesService identitiesService;

    public Identities createIdentities(String idType, String idValue) {
        Identities s = new Identities();
        s.setIdType(idType);
        s.setIdValue(idValue);
        return s;
    }

    public Identities loadIdentities(Long id) {
        identitiesService = (IdentitiesService) ctx.getBean("identitiesService");
        Identities s = identitiesService.find(id);
        return s;
    }
}
