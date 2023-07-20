package com.aryunin.VacationPayCalculator.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
@Slf4j
public class ResourcesService {
    private final ResourceLoader resourceLoader;
    private Resource holidayResource;

    public ResourcesService(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
        loadResources();
    }

    private void loadResources() {
        log.info("loading resources");
        holidayResource = resourceLoader.getResource("classpath:static/holidays.txt");
        log.info("resources loaded");
    }

    public String getHolidaysAsString() {
        try {
            return holidayResource.getContentAsString(StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
