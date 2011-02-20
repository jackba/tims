/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.infrastructure.factories.jobs;

import com.hashthrims.domain.jobs.Cadres;
import com.hashthrims.infrastructure.conf.GetContext;
import com.hashthrims.services.CadresService;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author stud
 */
public class CadresFactory {

    private CadresService cadresService;
    private ApplicationContext ctx = GetContext.getApplicationContext();

   public Cadres createCadres(String cadresName)
    {
        Cadres c = new Cadres();
        c.setCadres_name(cadresName);

        return c;
    }
   public Cadres loadCadres(Long id) {
        cadresService = (CadresService) ctx.getBean("cadresService");
        Cadres c = cadresService.find(id);
        return c;
    }
   


}
