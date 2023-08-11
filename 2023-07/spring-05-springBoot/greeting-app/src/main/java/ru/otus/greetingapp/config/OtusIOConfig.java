package ru.otus.greetingapp.config;

import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;
import ru.otus.io.config.LocaleProvider;

import java.util.Locale;

@ToString
@ConfigurationProperties(prefix = "otus.custom")
public class OtusIOConfig implements LocaleProvider {

    private final boolean ioEnabled;
    private final Locale locale;

    @ConstructorBinding
    public OtusIOConfig(boolean ioEnabled, String locale) {
        this.ioEnabled = ioEnabled;
        this.locale = Locale.forLanguageTag(locale);
    }

    @Override
    public Locale getCurrent() {
        return locale;
    }

    public boolean isIoEnabled() {
        return ioEnabled;
    }
}
