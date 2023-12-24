package ru.otus.hw.config;

import lombok.Getter;
import lombok.Setter;

import java.util.Locale;
import java.util.Map;

@Setter
// Использовать @ConfigurationProperties.
// Сейчас класс соответствует файлу настроек. Чтобы они сюда отобразились нужно только правильно разместить аннотации
public class AppProperties implements TestConfig, TestFileNameProvider, LocaleConfig {

    @Getter
    private int rightAnswersCountToPass;

    @Getter
    private Locale locale;

    private Map<String, String> fileNameByLocaleTag;

    public void setLocale(String locale) {
        this.locale = Locale.forLanguageTag(locale);
    }

    @Override
    public String getTestFileName() {
        return fileNameByLocaleTag.get(locale.toLanguageTag());
    }
}
