package ru.netology.sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;
import org.mockito.*;
import ru.netology.i18n.LocalizationServiceImpl;
import ru.netology.i18n.TestLocalizationServiceImpl;

import java.util.HashMap;
import java.util.Map;

public class TestMessageSenderImpl {

    @Test
    void test_send_rus(){

        final String IP_RUS = "172.0.32.11";
        final String TEXT_RUS = "Привет дружище!";

        GeoServiceImpl geoService = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoService.byIp(IP_RUS))
                .thenReturn(new Location(null, Country.RUSSIA, null, 0));

        LocalizationServiceImpl localizationService = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationService.locale(Country.RUSSIA))
                .thenReturn(TEXT_RUS);

        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<>();

        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, IP_RUS);
        Assertions.assertTrue(TestLocalizationServiceImpl
                .isCyrillic(messageSender.send(headers)));
    }

    @Test
    void test_send_usa(){

        final String IP_USA = "96.123.12.19";
        final String TEXT_USA = "Hi, Body!";

        GeoServiceImpl geoService = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoService.byIp(IP_USA))
                .thenReturn(new Location(null, Country.USA, null, 0));

        LocalizationServiceImpl localizationService = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationService.locale(Country.USA))
                .thenReturn(TEXT_USA);

        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<>();

        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, IP_USA);
        Assertions.assertFalse(TestLocalizationServiceImpl
                .isCyrillic(messageSender.send(headers)));
    }

}
