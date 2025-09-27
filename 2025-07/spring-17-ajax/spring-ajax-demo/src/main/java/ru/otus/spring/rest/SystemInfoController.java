package ru.otus.spring.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.domain.SystemInfo;

@RestController
public class SystemInfoController {

    @GetMapping("api/server/system/info")
    public ResponseEntity<SystemInfo> getServerSystemInfo(SystemInfo systemInfo) {
        return ResponseEntity.ok(systemInfo);
    }
}
