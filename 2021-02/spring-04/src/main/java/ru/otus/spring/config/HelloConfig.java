package ru.otus.spring.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "hello")
@Component
public class HelloConfig {

    private String stringToPrint;

    private int count;

    private PrefixesAndSuffixes other;

    public String getStringToPrint() {
        return stringToPrint;
    }

    public void setStringToPrint(String stringToPrint) {
        this.stringToPrint = stringToPrint;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public PrefixesAndSuffixes getOther() {
        return other;
    }

    public void setOther(PrefixesAndSuffixes other) {
        this.other = other;
    }

    public static class PrefixesAndSuffixes {
        private String prefix;
        private String suffix;

        public String getPrefix() {
            return prefix;
        }

        public void setPrefix(String prefix) {
            this.prefix = prefix;
        }

        public String getSuffix() {
            return suffix;
        }

        public void setSuffix(String suffix) {
            this.suffix = suffix;
        }
    }
}
