/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.repository.jdbc;

/**
 *
 * @author boniface
 */
public interface SnapshotDataDAO {

    public long getNumberOfPeople();

    public long getNumberOfMales();

    public long getNumberofFemales();

    public long getNumberofCourses();

    public long getNumberScheduledCourses();

    public long getNumberofVacancies();
}
