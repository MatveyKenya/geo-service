package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import ru.netology.entity.Country;


public class TestGeoServiceImpl {

    @Test
    void test_byIp_rus(){
        String ipRussia = "172.157.48.100";
        Country expectedRussia = Country.RUSSIA;
        GeoServiceImpl geoService = new GeoServiceImpl();

        Country result = geoService.byIp(ipRussia).getCountry();
        Assertions.assertEquals(expectedRussia, result);
    }

    @Test
    void test_byIp_usa(){
        String ipUSA = "96.85.89.75";
        Country expectedUSA = Country.USA;
        GeoServiceImpl geoService = new GeoServiceImpl();

        Country result = geoService.byIp(ipUSA).getCountry();
        Assertions.assertEquals(expectedUSA, result);
    }

    @Test
    void test_byIp_local(){
        String ipLocalHost = "127.0.0.1";
        GeoServiceImpl geoService = new GeoServiceImpl();

        Country result = geoService.byIp(ipLocalHost).getCountry();
        Assertions.assertNull(result);
    }

    @Test
    void test_byCoordinates(){
        GeoServiceImpl geoService = new GeoServiceImpl();

        Assertions.assertThrows(RuntimeException.class,
                () ->geoService.byCoordinates(45, 45));
    }

}
