/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.positions.views;

import com.hashthrims.clients.web.vaadin.data.ClientDataService;
import com.hashthrims.clients.web.vaadin.views.positions.model.dtos.StringValues;
import com.hashthrims.domain.offices.Facility;
import com.hashthrims.domain.positions.Positions;
import com.hashthrims.infrastructure.factories.positions.PositionsFactory;
import java.util.List;
import java.util.Map;
import java.util.concurrent.RecursiveAction;

/**
 *
 * @author boniface
 */
public class Task extends RecursiveAction {

    private static final ClientDataService data = new ClientDataService();
    private List<Facility> facilities ;
    private final PositionsFactory factory = data.getPositionFactory();
    private StringValues val;
    private Map<String, Long> dfields;

    public Task(List<Facility> data, StringValues val, Map<String, Long> dfields) {
        this.val = val;
        this.dfields = dfields;
        this.facilities=data;
    }

    @Override
    protected void compute() {
        for (Facility facility : facilities) {
            final String positionComments = "No Comments";
            val.setPositionComments(positionComments);
            Long facililty = facility.getId();
            dfields.put("facililty", facililty);
            final Positions p = factory.createPosition(val, dfields);
            data.getPositionsService().persist(p);
        }

    }
}
