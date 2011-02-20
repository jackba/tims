/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.infrastructure.factories;

import java.util.List;

/**
 *
 * @author abismail
 */
public class HashEntityFactory extends FactoryChainHandler{
    FactoryEnum e;
    private List vars;
    HashEntityFactory(){
//        this.setSuccessor(new ApplicationsFactory());
    }

    public Object getEntity(FactoryEnum e, List vars) {
        this.e = e;
        return constructEntity();
    }

    @Override
    protected Object constructEntity() {
        return this.successor.getEntity(e, this);
    }

    /**
     * @return the vars
     */
    public List getVars() {
        return vars;
    }

    /**
     * @param vars the vars to set
     */
    public void setVars(List vars) {
        this.vars = vars;
    }

    @Override
    protected Object getEntity(FactoryEnum e, HashEntityFactory h) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
