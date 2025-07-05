package com.example.logservice.controllers;

import com.example.logservice.models.LogEntry;
import com.example.logservice.repositories.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/logs")
public class LogController {

    @Autowired
    private LogRepository logRepository;

    @PostMapping
    public LogEntry saveLog(@RequestBody LogEntry logEntry) {
        return logRepository.save(logEntry);
    }
}
