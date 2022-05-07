package ru.otus.spring.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.domain.SystemInfo;

@RestController
public class SystemInfoController {

    @GetMapping("/server/system/info")
    public SystemInfo getServerSystemInfo(SystemInfo systemInfo) {
        return systemInfo;
    }
}
