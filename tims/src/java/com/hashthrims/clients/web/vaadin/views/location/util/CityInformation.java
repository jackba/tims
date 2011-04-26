/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.location.util;

import com.hashthrims.domain.regionlist.City;
import com.hashthrims.domain.regionlist.Country;
import com.hashthrims.domain.regionlist.County;
import com.hashthrims.domain.regionlist.District;
import com.hashthrims.domain.regionlist.Province;

/**
 *
 * @author boniface
 */
public class CityInformation {

    public static String getCityName(City city) {
        if (city != null) {
            return city.getName();
        }
        return null;
    }

    public static String getDistrictName(City city) {
        if (city != null) {
            return getDistrictName(city.getDistrict());
        }
        return null;

    }

    public static String getCountyName(City city) {
        if (city != null) {
            return getCountyNameFromDistrict(city.getDistrict()); //getCounty().getCountyName();
        }
        return null;
    }

    public static String getProvinceName(City city) {
        if (city != null) {
            return getProvinceNameFromDistrict(city.getDistrict());// .getCounty().getProvince().getRegionName();
        }
        return null;
    }

     public static String getCountryName(City city) {
        if (city != null) {
            return getCountryNameFromDistrict(city.getDistrict());//.getCounty().getProvince().getCountry().getCountryName();
        }
        return null;
    }

    private static String getDistrictName(District district) {
        if(district!=null)
            return district.getDistrictName();
        return null;
    }

    private static String getCountyNameFromDistrict(District district) {
       if(district!=null )
           return getCountyNameFromCounty(district.getCounty());//.getCountyName();
       return null;
    }

    private static String getCountyNameFromCounty(County county) {
        if(county!=null)
            return county.getCountyName();
        return null;
    }

    private static String getProvinceNameFromDistrict(District district) {
        if(district!=null)
            return getProvinceNameFromCounty(district.getCounty());
        return null;
    }

    private static String getProvinceNameFromCounty(County county) {
        if(county!=null)
            return getProvinceNameFromProvince(county.getProvince());
        return null;
    }

    private static String getProvinceNameFromProvince(Province province) {
        if(province!=null)
            return province.getRegionName();
        return null;
    }

    private static String getCountryNameFromDistrict(District district) {
        if(district!=null)
            return getCountryNameFromCounty(district.getCounty());
        return null;
    }

    private static String getCountryNameFromCounty(County county) {
        if(county!=null)
            return getCountryFromProvince(county.getProvince());
        return null;
    }

    private static String getCountryFromProvince(Province province) {
        if(province!=null)
            return getCoutryNameFromCountry(province.getCountry());
        return null;
    }

    private static String getCoutryNameFromCountry(Country country) {
        if(country!=null)
            return country.getCountryName();
        return null;
    }

}
