/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.repository.jdbc.Impl;

import com.hashthrims.repository.jdbc.SnapshotDataDAO;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author boniface
 */
@Repository("snapshotDataDAO")
public class SnapshotDataDAOImpl implements SnapshotDataDAO {

    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public long getNumberOfPeople() {
        setDataSource(dataSource);
        String query = "SELECT  count(*) FROM  public.person;";
        return jdbcTemplate.queryForLong(query);
    }

    @Override
    public long getNumberOfMales() {
        setDataSource(dataSource);
        String query = "SELECT count(*) FROM  "
                + "public.person, public.demography "
                + "WHERE   person.demography_id = demography.id "
                + "  AND demography.gender='Male'";
        return jdbcTemplate.queryForLong(query);
    }

    @Override
    public long getNumberofFemales() {
        setDataSource(dataSource);
                String query = "SELECT count(*) FROM  "
                + "public.person, public.demography "
                + "WHERE   person.demography_id = demography.id "
                + "  AND demography.gender='Female'";
        return jdbcTemplate.queryForLong(query);
    }

    @Override
    public long getNumberofCourses() {
        setDataSource(dataSource);
        String query = "SELECT   count(*) FROM public.trainingcourses";
        return jdbcTemplate.queryForLong(query);
    }

    @Override
    public long getNumberScheduledCourses() {
        setDataSource(dataSource);
        String query = "SELECT count(*) FROM  "
                + " public.scheduledcourses "
                + "WHERE scheduledcourses.startdate > now()::date ";
        return jdbcTemplate.queryForLong(query);
    }

    @Override
    public long getNumberofVacancies() {
        setDataSource(dataSource);
        String query = "SELECT count(*) FROM  "
                + " public.scheduledcourses "
                + "WHERE scheduledcourses.startdate > now()::date "
                + "AND scheduledcourses.coursecapacity > scheduledcourses.numofstuds";
        return jdbcTemplate.queryForLong(query);
    }
}
