/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims;

import com.hashthrims.application.webservices.rest.resources.hashthrims.util.LanguageProficiencyForm;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;
import com.hashthrims.domain.employeelist.LanguageProficiency;
import java.util.Collection;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
/**
 *
 * @author abismail
 */
public abstract class LanguageProficiencyResource {
    public abstract Collection<LanguageProficiency> getLanguageProficiencies();

    public abstract LanguageProficiency getLanguageProficiency(Long id);

    public abstract LanguageProficiency getLanguageProficiency(@Context Request request,RequestForm form);

    public abstract Response createLanguageProficiency(@Context Request request,LanguageProficiencyForm form);

     public abstract Response updateLanguageProficiency(@Context Request request,LanguageProficiency languageProficiency);
}
