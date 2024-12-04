package com.micronaut.simple;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller("/logger")
public class LogController {
    private static final Logger log = LoggerFactory.getLogger(LogController.class);

    @Get
    public String logExample() {
        log.info("Log endpoint was accessed.");
        return "Check log for details.";
    }
}
