/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.infrastructure.factories;

/**
 *
 * @author abismail
 */
public abstract class FactoryChainHandler<T> {
    //more parameters preferably ina list, must be parsed to this class for the factory to use in order to construct the entity
    FactoryEnum e;
    FactoryChainHandler successor;
    public void setSuccessor(FactoryChainHandler s){
        this.successor = s;
    }

    protected abstract Object getEntity(FactoryEnum e, HashEntityFactory h);
    protected abstract T constructEntity();
}
