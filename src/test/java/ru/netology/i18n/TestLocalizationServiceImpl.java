package ru.netology.i18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;

public class TestLocalizationServiceImpl {

    @Test
    void test_locale_rus(){

        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();

        String text = localizationService.locale(Country.RUSSIA);
        Assertions.assertTrue(isCyrillic(text));
    }

    @Test
    void test_locale_usa(){

        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();

        String text = localizationService.locale(Country.USA);
        Assertions.assertFalse(isCyrillic(text));

    }

    /**
     * если в text есть хоть 1 буква из кириллицы возвращает true
     */
    static public boolean isCyrillic(String text){
        return text.chars()
                .mapToObj(Character.UnicodeBlock::of)
                .anyMatch(b -> b.equals(Character.UnicodeBlock.CYRILLIC));
    }
}
