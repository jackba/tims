/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.services;

import com.hashthrims.domain.traininglist.MentoringSession;

/**
 *
 * @author boniface
 */
public interface MentoringSessionService  extends Services<MentoringSession, Long>{

    public MentoringSession restFundersAndCompetencies(MentoringSession mSession);

}
