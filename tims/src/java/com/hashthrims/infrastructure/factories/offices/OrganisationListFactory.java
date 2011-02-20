/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.infrastructure.factories.offices;

import com.hashthrims.domain.Contacts;
import com.hashthrims.domain.offices.Department;
import com.hashthrims.domain.offices.Facility;
import com.hashthrims.domain.offices.FacilityType;
import com.hashthrims.domain.offices.RegistrationBody;
import com.hashthrims.domain.regionlist.City;
import com.hashthrims.domain.regionlist.Country;
import com.hashthrims.infrastructure.conf.GetContext;
import com.hashthrims.services.CityService;
import com.hashthrims.services.ContactsService;
import com.hashthrims.services.CountryService;
import com.hashthrims.services.CountyService;
import com.hashthrims.services.DepartmentService;
import com.hashthrims.services.FacilityService;
import com.hashthrims.services.FacilityTypeService;
import com.hashthrims.services.RegistrationBodyService;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author boniface
 */
public class OrganisationListFactory {

    private DepartmentService departmentService;
    private FacilityService facilityService;
    private FacilityTypeService facilityTypeService;
    private CountryService countryService;
    private CountyService countyService;
    private ContactsService contactService;
    private RegistrationBodyService registrationBodyService;
    private ApplicationContext ctx = GetContext.getApplicationContext();
    private RegistrationBodyService regisrationBodyService;
    private CityService cityService;

    public FacilityType createFacilityType(String facilityName) {
        FacilityType ft = new FacilityType();
        ft.setFacilityName(facilityName);
        return ft;

    }

    public FacilityType loadFacilityType(Long id) {
        facilityTypeService = (FacilityTypeService) ctx.getBean("facilityTypeService");
        FacilityType ft = facilityTypeService.find(id);
        return ft;
    }

    public Facility createFacility(String facilityName, String contactInfo, FacilityType facilityType, Country country, Contacts contacts) {
        Facility f = new Facility();
        f.setFacilityName(facilityName);
        //f.setContactInfo(contactInfo);
        f.setContact(contacts);
        //f.setCountry(country);
        f.setFacilityType(facilityType);
        return f;
    }

    public Facility loadFacility(Long id) {
        facilityService = (FacilityService) ctx.getBean("facilityService");
        Facility f = facilityService.find(id);
        return f;
    }

    public RegistrationBody registrationBody(String name) {
        RegistrationBody r = new RegistrationBody();
        r.setName(name);
        return r;
    }

    public Department loadDepartment(Long id) {
        departmentService = (DepartmentService) ctx.getBean("departmentService");
        Department d = departmentService.find(id);
        return d;
    }

    public Country createCountry(String countryName, String alphaCode, int numericCode) {
        Country c = new Country();
        c.setCountryName(countryName);
        c.setAlphaCode(alphaCode);
        c.setNumericCode(numericCode);
        c.setLocation(true);
        c.setPrimaryCountry(true);
        return c;
    }

    public Country loadCountry(Long id) {
        countryService = (CountryService) ctx.getBean("countryService");
        Country c = countryService.find(id);
        return c;
    }

    public Department createDepartment(String deptNam) {
        Department r = new Department();
        r.setDeptName(deptNam);
        return r;
    }

    public Facility createFacility(String contactinfo, Contacts contact, Country country, String facilittype, String facilityname) {
        Facility d = new Facility();
        d.setContact(contact);
       // d.setContactInfo(contactinfo);
        //d.setCountry(country);
        d.setFacilityName(facilittype);
        d.setFacilityName(facilityname);
        facilityService = (FacilityService) ctx.getBean("facilityService");
        facilityService.persist(d);
        Facility e = facilityService.find(d.getId());
        return e;
    }

 

    public Contacts createContacts(String address, String cellnumber, String email, Facility facility, String fax, String mailingAddress, String notes, String telephoneNumber) {
        Contacts c = new Contacts();
        c.setAddressType(address);
        c.setCellnumber(cellnumber);
        c.setEmail(email);
       // c.setFacility(facility);
        c.setFaxnumber(fax);
        c.setMailingAddress(mailingAddress);
        c.setNotes(notes);
        c.setTelephoneNumber(telephoneNumber);
        return c;
    }

    public Contacts loadContacts(Long id) {
        contactService = (ContactsService) ctx.getBean("contactService");
        Contacts c = contactService.find(id);
        return c;
    }

    public RegistrationBody createRegistrationBody(String name) {
        RegistrationBody c = new RegistrationBody();
        c.setName(name);
        return c;
    }

    public RegistrationBody loadRegistrationBody(Long id) {
        registrationBodyService = (RegistrationBodyService) ctx.getBean("registrationBodyService");
        RegistrationBody c = registrationBodyService.find(id);
        return c;
    }

    public Contacts updateContacts(String address, String cellnumber, String email, Facility facility, String fax, String mailingAddress, String notes, String telephoneNumber, Long id) {
        facilityService = (FacilityService) ctx.getBean("facilityService");
        Facility e = facilityService.find(id);
        Contacts c = e.getContact();
        c.setAddressType(address);
        c.setCellnumber(cellnumber);
        c.setEmail(email);
       // c.setFacility(facility);
        c.setFaxnumber(fax);
        c.setMailingAddress(mailingAddress);
        c.setNotes(notes);
        c.setTelephoneNumber(telephoneNumber);
        return c;
    }

    public Facility updateFacility(String facilityName, String facility, Long id) {
        FacilityType p = loadFacilityType(id);
        facilityService = (FacilityService) ctx.getBean("facilityService");
        Facility c = facilityService.getByPropertyName("facilityName", facility);
        FacilityType d = new FacilityType();
        d.setFacilityName(facilityName);
        facilityService.merge(c);
        return c;
    }

    public Country updateCountry(String countryName, String alphaCode, int numericCode, Long id) {
        facilityService = (FacilityService) ctx.getBean("facilityService");
        Facility e = facilityService.find(id);
        Country c = e.getCity().getDistrict().getCounty().getProvince().getCountry();
        c.setCountryName(countryName);
        c.setAlphaCode(alphaCode);
        c.setNumericCode(numericCode);
        c.setLocation(true);
        c.setPrimaryCountry(true);
        return c;
    }

    public Department updateDepartment(String deptNam) {
        Department r = new Department();
        r.setDeptName(deptNam);
        return r;
    }

    public RegistrationBody updateRegistrationBody(String name) {
        RegistrationBody c = new RegistrationBody();
        c.setName(name);
        return c;
    }

    public Department updateDepartment(String departmentName, Long departmentId) {
        Department d = loadDepartment(departmentId);
        d.setDeptName(departmentName);
        return d;
    }

    public RegistrationBody updateRegistrationBody(String registrationBodyName, Long registrationBodyId) {
        RegistrationBody b = loadRegistrationBody(registrationBodyId);
        b.setName(registrationBodyName);
        return b;

    }

    public FacilityType updateFacilityType(String facilityTypeName, Long facilityTypeId) {
        FacilityType f = loadFacilityType(facilityTypeId);
        f.setFacilityName(facilityTypeName);
        return f;
    }


    //BUGGY CITY RETRIEVAL NOT UNIQUE
    public Facility createFacility(String facilityTypeName, String facilityName, String cityName, Contacts contacts) {
        
        facilityTypeService = (FacilityTypeService) ctx.getBean("facilityTypeService");
        FacilityType fp = facilityTypeService.getByPropertyName("facilityName", facilityTypeName);
        cityService = (CityService) ctx.getBean("cityService");
        City c = cityService.getByPropertyName("name", cityName);
        Facility f = new Facility();
        f.setContact(contacts);
        f.setFacilityName(facilityName);
        f.setFacilityType(fp);
        f.setCity(c);

        return f;


    }

    public Facility updatedFacility(String facilityTypeName, String facilityName, String cityName, Contacts contacts, Long facilityId) {
        Facility f = loadFacility(facilityId);
        Long contactId = f.getContact().getId();
        contacts.setId(contactId);
        

        facilityTypeService = (FacilityTypeService) ctx.getBean("facilityTypeService");
        FacilityType fp = facilityTypeService.getByPropertyName("facilityName", facilityTypeName);
        cityService = (CityService) ctx.getBean("cityService");
        City c = cityService.getByPropertyName("name", cityName);

        f.setContact(contacts);
        f.setFacilityName(facilityName);
        f.setFacilityType(fp);
        f.setCity(c);

        return f;
    }
}
