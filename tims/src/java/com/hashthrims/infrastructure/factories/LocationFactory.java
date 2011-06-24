/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.infrastructure.factories;

import com.hashthrims.domain.offices.Clusters;
import com.hashthrims.domain.offices.Nodes;
import com.hashthrims.domain.regionlist.AddressType;
import com.hashthrims.domain.regionlist.City;
import com.hashthrims.domain.regionlist.Country;
import com.hashthrims.domain.regionlist.County;
import com.hashthrims.domain.regionlist.Currency;
import com.hashthrims.domain.regionlist.District;
import com.hashthrims.domain.regionlist.Province;
import com.hashthrims.infrastructure.conf.GetContext;
import com.hashthrims.services.AddressTypeService;
import com.hashthrims.services.CityService;
import com.hashthrims.services.ClustersService;
import com.hashthrims.services.CountryService;
import com.hashthrims.services.CountyService;
import com.hashthrims.services.CurrencyService;
import com.hashthrims.services.DistrictService;
import com.hashthrims.services.NodesService;
import com.hashthrims.services.RegionService;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author boniface
 */
public class LocationFactory {

    private CountryService countryService;
    private RegionService regionService;
    private CountyService countyService;
    private DistrictService districtService;
    private CityService cityService;
    private CurrencyService currencyService;
    private AddressTypeService addressTypeService;
    private NodesService nodesService;
    private ClustersService clustersService;
    private ApplicationContext ctx = GetContext.getApplicationContext();

    public Country createCountry(String countryName, String alphaCode, int numericCode) {
        Country c = new Country();
        c.setCountryName(countryName);
        c.setAlphaCode(alphaCode);
        c.setNumericCode(numericCode);
        c.setLocation(true);
        c.setPrimaryCountry(true);
        return c;
    }

    public Country updatedCountry(String countryName, String alphaCode, int numericCode, Long id) {
        Country c = loadCountry(id);
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
// Province Factories

    public Country createProvince(String provinceName, String provinceCode, String country) {
        countryService = (CountryService) ctx.getBean("countryService");
        Country c = countryService.getByPropertyName("countryName", country);
        Province p = new Province();
        p.setRegionCode(provinceCode);
        p.setRegionName(provinceName);
        c.getProvince().add(p);
        return c;
    }

    public Country updateProvince(String provinceName, String provinceCode, String country, Long id) {
        Province p = loadRegion(id);
        countryService = (CountryService) ctx.getBean("countryService");
        Country c = countryService.getByPropertyName("countryName", country);
        p.setRegionCode(provinceCode);
        p.setRegionName(provinceName);
        c.getProvince().add(p);
        return c;
    }

    public Province loadRegion(Long id) {
        regionService = (RegionService) ctx.getBean("regionService");
        Province r = regionService.find(id);
        return r;
    }

    //County Factories
    public District createDistrict(String districtName, String districtCode, County county) {
        District d = new District();
        d.setDistrictCode(districtCode);
        d.setDistrictName(districtName);
        d.setCounty(county);
        return d;
    }

    public District loadDistrict(Long id) {
        districtService = (DistrictService) ctx.getBean("districtService");
        District d = districtService.find(id);
        return d;
    }

    public County createCounty(String countyName, String countyCode, Province province) {
        County c = new County();
        c.setCountyName(countyName);
        c.setCountyCode(countyCode);
        c.setProvince(province);
        return c;
    }

    public County loadCounty(Long id) {
        countyService = (CountyService) ctx.getBean("countyService");
        County c = countyService.find(id);
        return c;
    }

    public City createCity(String cityName, String cityCode, District district) {
        City c = new City();
        c.setName(cityName);
        c.setCityCode(cityCode);
        c.setDistrict(district);
        return c;
    }

    public City loadCity(Long id) {
        cityService = (CityService) ctx.getBean("cityService");
        City c = cityService.find(id);
        return c;
    }

    public Province createCounty(String countyName, String countyCode, String province) {
        regionService = (RegionService) ctx.getBean("regionService");
        Province p = regionService.getByPropertyName("regionName", province);
        County c = new County();
        c.setCountyName(countyName);
        c.setCountyCode(countyCode);
        p.getCounty().add(c);

        return p;
    }

    public Province updateCounty(String countyName, String countyCode, String province, Long countryId) {
        County c = loadCounty(countryId);
        regionService = (RegionService) ctx.getBean("regionService");
        Province p = regionService.getByPropertyName("regionName", province);
        c.setCountyCode(countyCode);
        c.setCountyName(countyName);
        p.getCounty().add(c);
        return p;
    }

    public County createDistrict(String districtName, String districtCode, String county) {
        countyService = (CountyService) ctx.getBean("countyService");
        County p = countyService.getByPropertyName("countyName", county);
        District c = new District();
        c.setDistrictName(districtName);
        c.setDistrictCode(districtCode);
        p.getDistrict().add(c);

        return p;
    }

    public County updateDistrict(String districtName, String districtCode, String county, Long districtId) {
        District d = loadDistrict(districtId);
        countyService = (CountyService) ctx.getBean("countyService");
        County c = countyService.getByPropertyName("countyName", county);
        d.setDistrictName(districtName);
        d.setDistrictCode(districtCode);
        c.getDistrict().add(d);
        return c;
    }

    public District createCity(String cityName, String cityCode, String district) {
        districtService = (DistrictService) ctx.getBean("districtService");
        District d = districtService.getByPropertyName("districtName", district);
        City c = new City();
        c.setName(cityName);
        c.setCityCode(cityCode);
        d.getCities().add(c);

        return d;
    }

    public District updateCity(String cityName, String cityCode, String district, Long districtId) {
        City c = loadCity(districtId);
        districtService = (DistrictService) ctx.getBean("districtService");
        District d = districtService.getByPropertyName("districtName", district);
        c.setName(cityName);
        c.setCityCode(cityCode);
        d.getCities().add(c);
        return d;
    }

    public Currency createCurrency(String currencyName, String currencySymbol, String currencyCode) {
        Currency c = new Currency();
        c.setCurrencyName(currencyName);
        c.setCurrencySymbol(currencySymbol);
        c.setCurrencyCode(currencyCode);
        return c;
    }

    public Currency updatedCurrency(String currencyName, String currencySymbol, String currencyCode, Long currencyId) {
        Currency c = loadCurrency(currencyId);
        c.setCurrencyName(currencyName);
        c.setCurrencySymbol(currencySymbol);
        c.setCurrencyCode(currencyCode);

        return c;
    }

    public Currency loadCurrency(Long currencyId) {
        currencyService = (CurrencyService) ctx.getBean("currencyService");
        Currency c = currencyService.find(currencyId);
        return c;
    }

    public AddressType updatedAddressType(String addressTypeName, Long addressTypeId) {
        AddressType a = loadAddressType(addressTypeId);
        a.setAddressTypeName(addressTypeName);
        return a;
    }

    public AddressType createAddressType(String addressTypeName) {
        AddressType a = new AddressType();
        a.setAddressTypeName(addressTypeName);
        return a;
    }

    public AddressType loadAddressType(Long addressTypeId) {
        addressTypeService = (AddressTypeService) ctx.getBean("addressTypeService");
        AddressType c = addressTypeService.find(addressTypeId);
        return c;
    }

    public Clusters createClusters(String clustersName) {
        clustersService = (ClustersService) ctx.getBean("clustersService");
        Clusters c = new Clusters();
        c.setClusterName(clustersName);
        return c;
    }

    public Clusters updatedClusters(String clustersName, Long clustersId) {
        Clusters a = loadClusters(clustersId);
        a.setClusterName(clustersName);
        return a;
    }

    public Clusters loadClusters(Long clustersId) {
        clustersService = (ClustersService) ctx.getBean("clustersService");
        Clusters c = clustersService.find(clustersId);
        return c;
    }

    public Nodes createNodes(String nodesName) {
        nodesService = (NodesService) ctx.getBean("nodesService");
        Nodes c = new Nodes();
        c.setNodesName(nodesName);
        return c;
    }

    public Nodes updatedNodes(String nodesName, Long nodesId) {
        Nodes a = loadNodes(nodesId);
        a.setNodesName(nodesName);
        return a;
    }

    public Nodes loadNodes(Long nodesId) {
        nodesService = (NodesService) ctx.getBean("nodesService");
        Nodes n = nodesService.find(nodesId);
        return n;
    }
}
