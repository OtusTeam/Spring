package ru.otus.spring.domain;

public class SystemInfo {
    private final String osName;
    private final String timeZone;
    private final String osArch;
    private final int processorsCount;

    public SystemInfo(String osName, String timeZone, String osArch, int processorsCount) {
        this.osName = osName;
        this.timeZone = timeZone;
        this.osArch = osArch;
        this.processorsCount = processorsCount;
    }

    public String getOsName() {
        return osName;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public String getOsArch() {
        return osArch;
    }

    public int getProcessorsCount() {
        return processorsCount;
    }
}
