/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims;

import com.hashthrims.application.webservices.rest.resources.hashthrims.util.LanguageForm;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;
import com.hashthrims.domain.employeelist.Language;
import java.util.Collection;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
/**
 *
 * @author abismail
 */
public abstract class LanguageResource {
    public abstract Collection<Language> getLanguages();

    public abstract Language getLanguage(Long id);

    public abstract Language getLanguage(@Context Request request,RequestForm form);

    public abstract Response createLanguage(@Context Request request,LanguageForm form);

     public abstract Response updateLanguage(@Context Request request,Language languages);
}
