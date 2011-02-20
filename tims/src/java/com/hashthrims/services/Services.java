/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.services;

/**
 *
 * @author boniface
 */
import java.util.List;

public interface Services<S, ID> {

    public S find(ID id);

    public void persist(S entity);

    public void merge(S entity);

    public void remove(S entity);

    public List<S> findAll();

    public List<S> findInRange(int firstResult, int maxResults);

    public long count();

    public S getByPropertyName(String propertyName, String propertyValue);

    public List<S> getEntitiesByProperName(String propertyName, String propertyValue);
}
